$(() => {
    // Richiedo la lista di tutte la fatture dell'utente loggato
    $.ajax({
        url: '/archivio/getAllMineInvoices',
        method: 'get'
    })
    .done(function(listaFatture) {
        console.log(`done`, listaFatture);
        
        /*
        listaFatture.forEach(element => {
            $('#ul-fatture').append(`<li>ID: ${element.id}</li>`);              
            console.log('ho aggiunto un list item');
        });
        */

        /*
        // Unordered List
        $('#ul-fatture').empty();
        listaFatture.forEach(element => {
            $('#ul-fatture').append(`<li>ID: ${element.id}</li>`);
        });
        console.log('ho terminato la UL');
        */

        //  DataTable
        $('#tblFatture').DataTable({
            data: listaFatture,
            columns: [
                {title: 'ID', data: 'id'},
                {title: 'Data', data: 'data'},
                {title: 'Scadenza', data: 'scadenza'},
//                    {title: 'Fattura cliente', data: 'fatturaCliente'},
                {title: 'Persona', data: 'persona'},
                {title: 'Nota', data: 'nota'},
//                    {title: 'Conto', data: 'conto'},
                {title: 'IVA', data: 'iva'},
                {title: 'Lordo', data: 'lordo'},
            ]
        });
        

    })
    .fail(() => {
        console.log(`fail`);
    })
});