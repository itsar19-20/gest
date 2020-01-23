// wait for the page to load
$(document).ready(function() {

    $('#btn-guarda-fatture').click(function() {
        console.log('#btn-guarda-fatture.click');
        $.ajax({
            url: '/fattura/guarda',
            method: 'get'
        })
        .done(function(listaFatture) {
            console.log('#btn-guarda-fatture.done');
 //           $('#ul-fatture').empty();
 //           listaFatture.forEach(element => {
 //               $('#ul-fatture').append(`<li>ID: ${element.id}</li>`)
 //           });
            $('#tbl-fatture').DataTable({
                data: listaFatture,
                columns: [
                    {title: 'ID', data: 'id'},
//                    {title: 'Data', data: 'data'},
//                    {title: 'Scadenza', data: 'scadenza'},
//                    {title: 'Fattura cliente', data: 'fatturaCliente'},
//                    {title: 'Persona', data: 'persona'},
//                    {title: 'Nota', data: 'nota'},
//                    {title: 'Conto', data: 'conto'},
//                    {title: 'IVA', data: 'iva'},
//                    {title: 'Lordo', data: 'lordo'},
//                    {title: 'Metodo di pagamento', data: 'metodoDiPagamento'},
                ]
            });
        })
        .fail(function() {
            console.log('#btn-guarda-fatture.fail');
        })
        .always(function() {
            console.log('#btn-guarda-fatture.always', listaFatture);
        });
        console.log('#btn-guarda-fatture.clickEnd');
    });

})