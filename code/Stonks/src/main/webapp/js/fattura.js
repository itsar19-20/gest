$(() => {

    //  button guarda fatture
    $('#btn-guarda-fatture').click(function() {
        var btn = 'button guarda fatture '
        console.log(btn, '.click');
        $.ajax({
            url: '/fattura/guarda',
            method: 'get'
        })
        .done(function(listaFatture) {
            console.log(btn, '.done', listaFatture);
            
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
            $('#tbl-fatture').DataTable({
                data: listaFatture,
                columns: [
                    {title: 'ID', data: 'id'},
                    {title: 'Data', data: 'data'},
                    {title: 'Scadenza', data: 'scadenza'},
                    {title: 'Fattura cliente', data: 'fatturaCliente'},
                    {title: 'Persona', data: 'persona'},
                    {title: 'Nota', data: 'nota'},
                    {title: 'Conto', data: 'conto'},
                    {title: 'IVA', data: 'iva'},
                    {title: 'Lordo', data: 'lordo'},
                ]
            });
            

        })
        .fail(function() {
            console.log(btn, '.fail');
        })
        .always(function() {
            console.log(btn, '.always', listaFatture);
        });
        console.log(btn, '.EndClick');
    });

    //  load the first articol
    $.ajax({
        url: '/parts/articolo.html',
        method: 'get'
    })
    .done((html) => {
        var numeroArticoli = 'add-new';
        $('#ol-articoli').append(html.replace('§numero§', numeroArticoli).replace('§numero§', numeroArticoli).replace('§btn-text§', '+'));
        numeroArticoli = 1;
        console.log(numeroArticoli);

        // add another articol
        $('#btn-articol-add-new').click(() => {
            numeroArticoli++;
            console.log(numeroArticoli)

            $.ajax({
                url: '/parts/articolo.html',
                method: 'get'
            })
            .done((html) => {
                var rimuoviDiv = $('#articoli').remove( $('#articolo-list-item-' + `${numeroArticoli}`) );
                $('#ol-articoli').append(html.replace('§numero§', numeroArticoli).replace('§numero§', numeroArticoli).replace('§btn-text§', '-').replace('§click§', 'alert(\'pippo\', \`${rimuoviDiv}\`);'));

                // remove an articol

            });
        });
    });

});