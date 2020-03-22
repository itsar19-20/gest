document.body.style.backgroundColor = "#99FFFF";
$(document).ready( function () {
let chart=0;
var user = JSON.parse(localStorage.getItem('user')  );
user = user.id;
var table=0;


$('#bottone').click(function(){

    
    console.log(user);

	$.ajax({
       url: './scadenza',
       method: 'get',
       data: { 
               numMesi: $('#mesi').val(),
               numSettimane: $('#settimane').val(),
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

    $.ajax({
        url: './scadenza',
        method: 'get',
        data: { 
                numMesi: $('#mesi').val(),
                numSettimane: $('#settimane').val(),
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

            var valSett=$('#settimane').val();
            var valMesi=$('#mesi').val();
            let myCanvas =document.getElementById("myCanvas").getContext("2d");
            if(valSett>=0){
                
               var entSett=[];
               var uscSett=[];
               var newListaFatture=[];
               for(var i=0;i<7;i++){
                    entSett.push(0);
                    uscSett.push(0);
                    newListaFatture[i]=new Array();
               }
              
              
              
               fatture.forEach(f => {


                    var data1=new Date(f.data);
                   
                    console.log("data: 1");
                    console.log(data1.toLocaleDateString());
                    data1.setDate(data1.getDate()+f.scadenza);

                    console.log("data: 2");
                    console.log(data1.toLocaleDateString());

                    var giornoSett=data1.getDay();
                    console.log(giornoSett);

                    

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
                            text: "Settimana"
                        },
                        onClick: function(e){
                            var element=this.getElementAtEvent(e);
                            getFattureGiorno(element,newListaFatture);
                        
                           
                    },
                    onHover: function(e){
                        var element=this.getElementAtEvent(e);
                        if(element[0]){
                            e.target.style.cursor ="pointer"
                           // console.log("preso");
                        }else{
                            e.target.style.cursor ="default"
                           // console.log("lasciato 1");
                        }
                    }
                }
                
            
            
                });

               


            }else if(valMesi>=0){
                    var entMes=[];
                    var uscMes=[];
                    let myLabels=[];
                    var tuttiMesi=["Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno","Luglio","Agosto",
                     "Settembre","Ottobre","Novembre","Dicembre"];

                    console.log("val mesi:");
                    
                    
                    var dataMese=new Date();
                    
                    var result= parseInt(dataMese.getMonth())+ parseInt(valMesi);
                    console.log("result mesi: ");
                    console.log(result);
                    dataMese.setMonth(result);    
                    console.log(dataMese.getMonth());
                    var meseGrafico=dataMese.getMonth();
                    var numGiorni=0;

                    var meseStringa=tuttiMesi[meseGrafico];
                    if(meseGrafico===0||meseGrafico===2||meseGrafico===4||meseGrafico===6||meseGrafico===7||meseGrafico===9||meseGrafico===11){
                        numGiorni=31;
                        console.log("mese con 31 giorni");

                    }else if(meseGrafico===1){
                        var dataAnno=dataMese.getFullYear();
                        console.log(dataAnno);
                        if(dataAnno%4===0){
                            console.log("febbraio bisestile");
                            numGiorni=29;
                        }else{
                            numGiorni=28;
                            console.log("febbraio normale");
                        }
                        
                    }else{
                        numGiorni=30;
                        console.log("mese con 30 giorni");
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
                       
                        console.log("data: 1");
                        console.log(data1.toLocaleDateString());
                        data1.setDate(data1.getDate()+f.scadenza);
    
                        console.log("data: 2");
                        console.log(data1.toLocaleDateString());
    
                        var giornoMese=data1.getDate();
                        console.log(giornoMese);

                       
                        
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
                               // console.log("preso");
                            }else{
                                e.target.style.cursor ="default"
                               // console.log("lasciato 1");
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

   
    console.log(element);
    if(element[0]){
        var index=element[0]._index;
        var datasetIndex=element[0]._datasetIndex;
        console.log(index);
        console.log(datasetIndex);  
        console.log(newListaFatture);
        
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
        console.log(f.eUnaFatturaCliente);
        if(f.eUnaFatturaCliente==true){
            f.eUnaFatturaCliente="entrata";
        }else{
            f.eUnaFatturaCliente="uscita";
        }

        var lordoStringa= parseFloat(f.lordo).toFixed(2);
         lordoStringa+=" €";
         f.lordo=lordoStringa;
    });
    console.log(fatture[0].data);

    
   
    //$("#cercaPagamenti").attr("disabled",true);



    table= $('#tblPagamenti').DataTable({
                

        data: fatture,

        
        columns: [
            { title: 'Numero fattura:',data: 'numeroFattura'},
            { title: 'Data', data: 'data'},
            { title: 'Scadenza: ', data: 'scadenza'},
            { title: 'Lordo',data: 'lordo'},
            { title: 'Tipo', data: 'eUnaFatturaCliente'}
           
        ]
        /*,
        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
            $('td', nRow).css('background-color', 'Red');
        }
        */
    });
    $('#tblPagamenti tbody').on('click','tr',function(){
        var dati=table.row(this).data();
        console.log(dati);
      

       if(dati!=undefined){
            localStorage.removeItem("fatturaDaPagare");
            localStorage.setItem("fatturaDaPagare",JSON.stringify(dati));
            location.href='./pagamento.html';
        }else{
            console.log("è undefined")
        }
        
   });

}




});