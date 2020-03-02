$(document).ready( function () {

    

$('#bottone').click(function(){

    
     
    var user = JSON.parse(localStorage.getItem('user')  );
    user = user.id;
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
                numMesi: "null",
                numSettimane: "null",
                entrataUscita: "null",
                user
            }
     })
     .done(function(fatture) {
         if(fatture.length>0){

            let myCanvas =document.getElementById("myCanvas").getContext("2d");
            


         }else{
             alert("nin ci sono fatture da mostrare");
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
                label: "Popolazione",
                data: myData,
                backgroundColor: "red",


            },{
                label: "Morti",
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