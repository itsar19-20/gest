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
//                    {title: 'Fattura cliente', data: 'fatturaCliente'},
                    {title: 'Persona', data: 'persona'},
                    {title: 'Nota', data: 'nota'},
//                    {title: 'Conto', data: 'conto'},
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
        var numeroArticoli = 1;
        $('#articoli').append(html
            .replace('§numero§', numeroArticoli)
            .replace('§numero§', numeroArticoli)
            .replace('§btn-text§', '+'));
        

        // add another articol
        $('#btn-articol-1').click(() => {
            numeroArticoli++;
            

            $.ajax({
                url: '/parts/articolo.html',
                method: 'get'
            })
            .done((html) => {



                $('#articoli').append(html
                    .replace('§numero§', numeroArticoli)
                    .replace('§numero§', numeroArticoli)
                    .replace('§btn-text§', '-')
                    //.replace('§click§', 'alert(\'pippo\', \`${rimuoviDiv}\`);')
                    //.replace('§click§', 'console.log(rimuoviDiv, \'click\')')
                );

                // remove an articol
                var idArticoloListItem = '#articolo-list-item-' + numeroArticoli;
                $('#btn-articol-' + numeroArticoli).click(() =>{
                    console.log('hai premuto il bottone dell\'artiolo', numeroArticoli);
                    $(idArticoloListItem).remove();
                    numeroArticoli--;
                    console.log('un articolo è statorimosso, ora sono', numeroArticoli);
                });
            });
        });
    });

    // submit (crea fattura)
    $('#btn-submit').click(() => {
        $.ajax({
            url: '/fattura/crea',
            method: 'post',
            data: {
                tipoFattura: $('#input-tipo-fattura').val(),
                conto: $('#input-conto').val(),
                persona: $('#input-persona').val(),
                data: $('#input-data').val(),
                scadenza: $('#input-scadenza').val(),
                //  articoli
                note: $('#input-note').val(),
            }
        })
        .done((fattura) => {
            console.log('fattura creata');
            console.log(fattura);
        })
        .fail((fattura) => {
            console.log('fattura non creata');
        });
    });

});