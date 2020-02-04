$(document).ready( function () {


$('#bottone').click(function(){
	$.ajax({
       url: './scadenza',
       method: 'get',
       data: { 
               numMesi: $('#mesi').val(),
               numSettimane: $('#settimane'),
               entrataUscita: $("input[name='entrataUscita']:checked").val()
               
           }
    })
    .done(function(pagamenti) {
            
            $('#listaPagamenti').empty();
            pagamenti.forEach(p => {
                $('#listaPagamenti').append(`<li>Pagamento: ${p.idPagamento} ${p.fattura.data}</li>`)
            });
            
              
            
            $('#tblPagamenti').DataTable({
                data: pagamenti,
                columns: [
                    { title: 'ID', data: 'idPagamento'},
                    { title: 'Fattura', data: 'fattura'},
                    { title: 'Data Pagamento', data: 'dataPagamento'},
                    { title: 'Già pagato', data: 'giaPagato'},
                    { title: 'Pagato',data: 'pagato'},
                ]
            });
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