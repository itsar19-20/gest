// wait for the page to load
$(document).ready(function() {

    $('#btn-guarda-fatture').click(function() {
        console.log('#btn-guarda-fatture.click');
        $.ajax({
            url: '/fattura/guarda',
            method: 'get'
        })
        .done(function(listaFatture) {
            console.log('#btn-guarda-fatture.done', listaFatture);
            
            /*
            listaFatture.forEach(element => {
                $('#ul-fatture').append(`<li>ID: ${element.id}</li>`);              
                console.log('ho aggiunto un list item');
            });
            */

            /*
            // Unordered List
            $('#ul-fatture').empty();
            console.log('sono arrivato alla UL');
            listaFatture.forEach(element => {
                $('#ul-fatture').append(`<li>ID: ${element.id}</li>`);              
                console.log('ho aggiunto un list item');
            });
            console.log('ho terminato la UL');
            */

            //  DataTable
            /*
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
            */
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