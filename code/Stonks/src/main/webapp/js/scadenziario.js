$(document).ready( function () {
var user = JSON.parse(localStorage.getItem('user')  );
user = user.id;

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


           
            $("#cercaPagamenti").attr("disabled",true);
            
          // console.log(fatture[0].data); 
           //var dataS=new Date(fatture[0].data) ;

           //toLocaleDateString() -> giorno/mese/anno in numero
          // console.log(dataS.toLocaleDateString());
          if($.fn.DataTable.isDataTable('#tblPagamenti'))  {
              //$('#tblPagamenti').DataTable().clear().destroy();
              //console.log("datatable ancora");
              $('#tblPagamenti').DataTable().clear();
              $('#tblPagamenti').DataTable().destroy();

          }
            
            
        
           var table= $('#tblPagamenti').DataTable({
                

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
                console.log(dati);
               // $('#idDiv').show();
               // $('#fatturaSelezionata').text(dati.idFattura);
               if(dati!=undefined){
                    localStorage.removeItem("fatturaDaPagare");
                    localStorage.setItem("fatturaDaPagare",JSON.stringify(dati));
                    location.href='./pagamento.html';
                }else{
                    console.log("è undefined")
                }
                
           });
           
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
  
            }

            var valSett=$('#settimane').val();
            var valMesi=$('#mesi').val();
            let myCanvas =document.getElementById("myCanvas").getContext("2d");
            if(valSett>=0){
                
               var entSett=[];
               var uscSett=[];
               for(var i=0;i<7;i++){
                    entSett.push(0);
                    uscSett.push(0);
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
                        }else{

                        entSett[giornoSett-1]+=f.lordo;
                        }
                    }else{
                        if(giornoSett===0){
                            uscSett[6]+=f.lordo;
                        }else{
                            uscSett[giornoSett-1]+=f.lordo;

                        }
                        
                    }

                    

                });
                
                
              
                let myLabels =["Lunedì","Martedì","Mercoledì","Giovedì","Venerdì","Sabato","Domenica"];
               
                let myData=entSett;
                let myData2=uscSett;

                let chart=new Chart(myCanvas,{
                    type: "bar",
                    data: {
                        labels: myLabels,
                        datasets:[{
                            label: "Entrate",
                            data: myData,
                            backgroundColor: "green",
            
            
                        },{
                            label: "Uscite",
                            data: myData2,
                            backgroundColor: "red",
            
            
            
                        }]
            
            
                    },
                    options :{
                        title:{
                            display: true,
                            text: "Settimana"
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

                    for(var i=0;i<numGiorni;i++){
                        
                        myLabels.push((i+1).toString());
                        entMes.push(0);
                        uscMes.push(0);

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
    
                        if(f.eUnaFatturaCliente){
                            entMes[giornoMese-1]+=f.lordo;
                        
                        }else {
                    
                            uscMes[giornoMese-1]+=f.lordo;
    
                        }
    
                    });
                    
                    
                let myData=entMes;
                let myData2=uscMes;
                //let myData2=[17000,4000,5000,20000,10000,15000,7000,12000,230000,19000];

                let chart=new Chart(myCanvas,{
                    type: "bar",
                    data: {
                        labels: myLabels,
                        datasets:[{
                            label: "Entrate",
                            data: myData,
                            backgroundColor: "red",
            
            
                        },{
                            label: "Uscite",
                            data: myData2,
                            backgroundColor: "blue",
            
                        }]
            
            
                    },
                    options :{
                        title:{
                            display: true,
                            text: meseStringa.toString()
                        }
            
                    }
            
            
            
                });


                }
            




         }else{
             alert("non ci sono fatture da mostrare");
         }

     })
     /*
    let myCanvas =document.getElementById("myCanvas").getContext("2d");
    let myLabels =["Roma","Milano","Napoli","Torino","Palermo","Genova","Bologna","Firenze","Bari","Catania"];
    let myData = [2800000,1200000,900000,1100000,400000,550000,300000,750000,250000,340000];

    let myData2=[1700000,400000,500000,200000,100000,1500000,700000,1200000,2300000,190000];

    let chart=new Chart(myCanvas,{
        type: "bar",
        data: {
            labels: myLabels,
            datasets:[{
                label: "Entrate",
                data: myData,
                backgroundColor: "red",


            },{
                label: "Uscite",
                data: myData2,
                backgroundColor: "blue",



            }]


        },
        options :{

        }



    });
    */
});


});