$(document).ready( function () {


$('#bottone').click(function(){
     
    var user = JSON.parse(localStorage.getItem('user')  );
    user = user.id;

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
            });
            console.log(fatture[0].data);

          // console.log(fatture[0].data); 
           //var dataS=new Date(fatture[0].data) ;

           //toLocaleDateString() -> giorno/mese/anno in numero
          // console.log(dataS.toLocaleDateString());
    		
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
               localStorage.removeItem("fatturaDaPagare");
               localStorage.setItem("fatturaDaPagare",JSON.stringify(dati));
               location.href='./pagamento.html';
                
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


});