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
    		
           var table= $('#tblPagamenti').DataTable({
                data: fatture,
                columns: [
                    { title: 'ID', data: 'id'},
                    { title: 'Data Pagamento', data: 'data'},
                    { title: 'Pagata', data: 'pagata'},
                    { title: 'Importo',data: 'lordo'},
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