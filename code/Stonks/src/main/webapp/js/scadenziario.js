document.body.style.backgroundColor = "#99FFFF";
document.getElementById("settimane").disabled = true;
document.getElementById('settimane').style.color="#babab5";
var meseList=document.getElementById('mesi');
var settimanaList=document.getElementById('settimane');
var switchMese=document.getElementById('switchMese');
var switchSettimana=document.getElementById('switchSettimana');



$(document).ready( function () {
let chart=0;
var user = JSON.parse(localStorage.getItem('user')  );
user = user.id;
var table=0;


$('#bottone').click(function(){
    
    var checkMese=switchMese.checked;
    var checkSettimana=switchSettimana.checked;
    var nMesi=0;
    var nSett=0;
    if(checkMese){
        nMesi=meseList.options[meseList.selectedIndex].value;
        nSett=null;
    }else if(checkSettimana){
        nSett=settimanaList.options[settimanaList.selectedIndex].value;
        nMesi=null;
    }else{
        nMesi=null;
        nSett=null;
    }
   
    

	$.ajax({
       url: './scadenza',
       method: 'get',
       data: { 
               numMesi: nMesi,
               numSettimane: nSett,
               entrataUscita: $("input[name='entrataUscita']:checked").val(),
               user
           }
    })
    .done(function(fatture) {
        
        
    	
    	if(fatture.length>0){ 
            
         
          if($.fn.DataTable.isDataTable('#tblPagamenti'))  {
              

              $('#tblPagamenti').DataTable().clear();
              $('#tblPagamenti').DataTable().destroy();
              $("#tblPagamenti thead").remove();
              

          }
            
          if(chart!=0){
            chart.destroy();
            chart=0;
        }

            showDatatable(fatture);
        
           
    	}else{
            alert("non ci sono fatture da mostrare con queste impostazioni");
        }
           
        })
        .fail(function() {
            console.log('fail!!');
        })
        .always(function() {
            console.log('always!!');
        });
        console.log('dopo')


});


$('#btnGrafico').click(function(){
    var checkMese=switchMese.checked;
    var checkSettimana=switchSettimana.checked;
    var nMesi=0;
    var nSett=0;
    if(checkMese){
        nMesi=meseList.options[meseList.selectedIndex].value;
        nSett=null;
    }else if(checkSettimana){
        nSett=settimanaList.options[settimanaList.selectedIndex].value;
        nMesi=null;
    }else{
        nMesi=null;
        nSett=null;
    }



    $.ajax({
        url: './scadenza',
        method: 'get',
        data: { 
                numMesi: nMesi,
                numSettimane: nSett,
                entrataUscita: "null",
                user
            }
     })
     .done(function(fatture) {
         if(fatture.length>0){

            if($.fn.DataTable.isDataTable('#tblPagamenti'))  {
                
                $('#tblPagamenti').DataTable().clear();
                $('#tblPagamenti').DataTable().destroy();
                $("#tblPagamenti thead").remove();
  
            }
            if(chart!=0){
                chart.destroy();
                chart=0;
            }

            var valSett=nSett;
            var valMesi=nMesi;
            let myCanvas =document.getElementById("myCanvas").getContext("2d");
           
            if(valSett!=null){
                
               var entSett=[];
               var uscSett=[];
               var newListaFatture=[];
               for(var i=0;i<7;i++){
                    entSett.push(0);
                    uscSett.push(0);
                    newListaFatture[i]=new Array();
               }
              
               //localStorage.setItem("fatturaDaPagare",JSON.stringify(dati));
               var settimanaStorage = JSON.parse(localStorage.getItem('settimanaStorage'));

               console.log(settimanaStorage[nSett]);
              
               fatture.forEach(f => {


                    var data1=new Date(f.data);
                   
                   
                    data1.setDate(data1.getDate()+f.scadenza);

                    
                    var giornoSett=data1.getDay();
                   
                    

                    if(f.eUnaFatturaCliente){
                        if(giornoSett===0){
                            entSett[6]+=f.lordo;
                            newListaFatture[6].push(f);
                        }else{

                        entSett[giornoSett-1]+=f.lordo;
                        newListaFatture[giornoSett-1].push(f);
                        }
                    }else{
                        if(giornoSett===0){
                            uscSett[6]+=f.lordo;
                            newListaFatture[6].push(f);
                        }else{
                            uscSett[giornoSett-1]+=f.lordo;
                            newListaFatture[giornoSett-1].push(f);

                        }
                        
                    }

                    

                });
                
                
              
                let myLabels =["Lunedì","Martedì","Mercoledì","Giovedì","Venerdì","Sabato","Domenica"];
               
                let myData=entSett;
                let myData2=uscSett;

                chart=new Chart(myCanvas,{
                    type: "bar",
                    data: {
                        labels: myLabels,
                        datasets:[{
                            label: "Entrate",
                            data: myData,
                            backgroundColor:"rgba(0,235,16,0.4)",
                            borderColor: "rgba(0, 235, 16, 1)",
                            borderWidth: 3,
                            hoverBackgroundColor:"rgba(0,235,16,0.6)",
                            hoverBorderWidth: 6,
            
            
                        },{
                            label: "Uscite",
                            data: myData2,
                            backgroundColor: "rgba(250, 0, 0, 0.4)",
                            borderColor: "rgba(250, 0, 0, 1)",
                            borderWidth: 3,
                            hoverBackgroundColor:"rgba(250,0,0,0.6)",
                            hoverBorderWidth: 6,
            
            
            
                        }]
            
            
                    },
                    options :{
                        title:{
                            display: true,
                            text: settimanaStorage[nSett]
                        },
                        onClick: function(e){
                            var element=this.getElementAtEvent(e);
                            getFattureGiorno(element,newListaFatture);
                        
                           
                    },
                    onHover: function(e){
                        var element=this.getElementAtEvent(e);
                        if(element[0]){
                            e.target.style.cursor ="pointer"
                          
                        }else{
                            e.target.style.cursor ="default"
                           
                        }
                    }
                }
                
                });

               
            }else if(valMesi!=null){
                    var entMes=[];
                    var uscMes=[];
                    let myLabels=[];
                    var tuttiMesi=["Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno","Luglio","Agosto",
                     "Settembre","Ottobre","Novembre","Dicembre"];

                   
                    
                    var dataMese=new Date();
                    
                    var result= parseInt(dataMese.getMonth())+ parseInt(valMesi);
                   
                    dataMese.setMonth(result);    
                  
                    var meseGrafico=dataMese.getMonth();
                    var numGiorni=0;

                    var meseStringa=tuttiMesi[meseGrafico];
                    if(meseGrafico===0||meseGrafico===2||meseGrafico===4||meseGrafico===6||meseGrafico===7||meseGrafico===9||meseGrafico===11){
                        numGiorni=31;
                       

                    }else if(meseGrafico===1){
                        var dataAnno=dataMese.getFullYear();
                        
                        if(dataAnno%4===0){
                           
                            numGiorni=29;
                        }else{
                            numGiorni=28;
                           
                        }
                        
                    }else{
                        numGiorni=30;
                       
                    }
                    var newListaFatture=[];
                    for(var i=0;i<numGiorni;i++){
                        
                        myLabels.push((i+1).toString());
                        entMes.push(0);
                        uscMes.push(0);
                        newListaFatture[i]=new Array();

                    }
                    

                    fatture.forEach(f => {


                        var data1=new Date(f.data);
                       
                       
                        data1.setDate(data1.getDate()+f.scadenza);
    
                        
    
                        var giornoMese=data1.getDate();
                       

                       
                        
                        newListaFatture[giornoMese-1].push(f);
                        
                        
    
                        if(f.eUnaFatturaCliente){
                            entMes[giornoMese-1]+=f.lordo;
                        
                        }else {
                    
                            uscMes[giornoMese-1]+=f.lordo;
    
                        }
    
                    });
                    
                    
                let myData=entMes;
                let myData2=uscMes;
                

                 chart=new Chart(myCanvas,{
                    type: "bar",
                    
                    data: {
                        labels: myLabels,
                        datasets:[{
                            label: "Entrate",
                            data: myData,
                            backgroundColor:"rgba(0,235,16,0.4)",
                            borderColor: "rgba(0, 235, 16, 1)",
                            borderWidth: 1,
                            hoverBackgroundColor:"rgba(0,235,16,0.6)",
                            hoverBorderWidth: 2,
                            
            
            
                        },{
                            label: "Uscite",
                            data: myData2,
                            backgroundColor: "rgba(250, 0, 0, 0.4)",
                            borderColor: "rgba(250, 0, 0, 1)",
                            borderWidth: 1,
                            hoverBackgroundColor:"rgba(250,0,0,0.6)",
                            hoverBorderWidth: 2,
                            
            
                        }]
            
            
                    },
                    options :{
                        
                          
                        title:{
                            display: true,
                            text: meseStringa.toString()
                        },
                        onClick: function(e){
                            var element=this.getElementAtEvent(e);
                            getFattureGiorno(element,newListaFatture);
                            
                            
                            
                        },
                        onHover: function(e){
                            var element=this.getElementAtEvent(e);
                            if(element[0]){
                                e.target.style.cursor ="pointer"
                              
                            }else{
                                e.target.style.cursor ="default"
                              
                            }
                        }
                       
                        
            
                    } 
                    
            
            
                });


                }
                function disattivaBtn(){
                    $("#cercaPagamenti").attr("disabled",true);
                    setTimeout(attivaBtn, 1000);
                    

					 }
				function attivaBtn(){
                    $("#cercaPagamenti").attr("disabled",false);
				}
				
				disattivaBtn();
               



         }else{
             alert("non ci sono fatture da mostrare");
         }

     })
   
});


function getFattureGiorno(element,newListaFatture){

   
   
    if(element[0]){
        var index=element[0]._index;
        var datasetIndex=element[0]._datasetIndex;
        
        
        var fatture=[];
        
        newListaFatture[index].forEach(f=>{
            var entrataUsc=false;
            if(datasetIndex===0){
                entrataUsc=true;
            }

            if(f.eUnaFatturaCliente==entrataUsc){
                fatture.push(f);
            }
             
        });
        
        chart.destroy();

        chart=0;

        showDatatable(fatture);

       
    }

}




function showDatatable(fatture){

    fatture.forEach(f => {
        var dataS=new Date(f.data).toLocaleDateString();
        f.data=dataS;
        
        if(f.eUnaFatturaCliente==true){
            f.eUnaFatturaCliente="entrata";
        }else{
            f.eUnaFatturaCliente="uscita";
        }

        var lordoStringa= parseFloat(f.lordo).toFixed(2);
         lordoStringa+=" €";
         f.lordo=lordoStringa;
    });
    


    table= $('#tblPagamenti').DataTable({
                

        data: fatture,

        
        columns: [
            { title: 'Numero fattura:',data: 'numeroFattura'},
            { title: 'Data', data: 'data'},
            { title: 'Scadenza: ', data: 'scadenza'},
            { title: 'Lordo',data: 'lordo'},
            { title: 'Tipo', data: 'eUnaFatturaCliente'}
           
        ]
        
    });
    $('#tblPagamenti tbody').on('click','tr',function(){
        var dati=table.row(this).data();
        
      

       if(dati!=undefined){
            localStorage.removeItem("fatturaDaPagare");
            localStorage.setItem("fatturaDaPagare",JSON.stringify(dati));
            location.href='./pagamento.html';
        }
        
   });

}




$("#switchMese").click(function(){
   
    if(switchMese.checked){
        
       
        meseList.disabled=false;
        settimanaList.disabled=true;
        
        
       
      
        meseList.style.color="black";
        settimanaList.style.color="#babab5";

       
           if(switchSettimana.checked){
           
            
            switchSettimana.checked=false;
        }
    }else{
       
       meseList.disabled=true;
        
       
        
        meseList.style.color="#babab5";
    }
    

});

$("#switchSettimana").click(function(){
   
        if(switchSettimana.checked){
            settimanaList.disabled=false;
            meseList.disabled=true;

        
       
    
            settimanaList.style.color="black";
            meseList.style.color="#babab5";

            if(switchMese.checked){
           
           
           switchMese.checked=false;
        }
         
    }else{
       settimanaList.disabled=true;
       settimanaList.style.color="#babab5";

    }

});

});