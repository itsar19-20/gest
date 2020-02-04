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

    //  button crea fattura
    $('#btn-crea-fattura').click(() => {
        $.ajax({
            url: '/fattura/crea.html',
            method: 'get'
        })
        .done((html) => {
            //  svuota il tag main e appende il form per creare una nuova fattura
            $('main').empty().append(html);

            //  load the first article
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
                
                // add another article
                $('#btn-article-1').click(() => {
                    numeroArticoli++;
                    $.ajax({
                        url: '/parts/articolo.html',
                        method: 'get'
                    })
                    .done((html) => {
                        //  the next line allows you to remove the newly added article
                        var rimuoviQuestoDiv = '$(\'#articolo-list-item-' + numeroArticoli + '\').remove();';
                        console.log(rimuoviQuestoDiv);
                        $('#articoli').append(html
                            .replace('§numero§', numeroArticoli)
                            .replace('§numero§', numeroArticoli)
                            .replace('§btn-text§', '-')
                            .replace('§click§', rimuoviQuestoDiv)
                        );
                        console.log('hai aggiunto l\'articolo', numeroArticoli);
                    });
                });
            });

            //  invia al controller l'utente loggato
            var user = JSON.parse(localStorage.getItem('user'));
            user = user.idPersona;
            $.ajax({
                url: '/fattura/crea',
                method: 'post',
                data: {user}
            })
            //  il controller risponde con i conti e le persone collegate all'utente loggato
            .done((conti, persone) => {
                //  QUA
                console.log('ho invitato l\'utente al controller')
            });

            // submit button - salva fattura
            var articoli = [
                { descrizione: 'piadina', quantita: 3, prezzo: 7 },
                { descrizione: 'pita', quantita: 10, prezzo: 3 },
                { descrizione: 'kebab', quantita: 2, prezzo: 5 },
            ];
            articoli = JSON.stringify({ 'articoli': articoli });
            $('#btn-submit').click(() => {
                $.ajax({
                    url: '/fattura/salva',
                    method: 'post',
                    //  mapping the datas for the servlet controller
                    data: {
                        tipoFattura: $('#input-tipo-fattura').val(),
                        conto: $('#input-conto').val(),
                        persona: $('#input-persona').val(),
                        data: $('#input-data').val(),
                        scadenza: $('#input-scadenza').val(),
                        note: $('#input-note').val(),
                        articoli,
        
                        // articoli
                        descrizione: $('#input-articolo-descrizione').val(),
                        quantita: $('#input-articolo-quantita').val(),
                        prezzo: $('#input-articolo-prezzo').val(),
                    }
                    /*
                    success: function () {
                        //  code
                    }
                    failure: function () {
                        //  code
                    }
                    */
                })
                .done((fattura) => {
                    console.log('fattura salvata');
                })
                .fail((fattura) => {
                    console.log('fattura non salvata');
                    alert('La fattura non è stata salvata,'+'\n'+'prego inserire tutti i campi.');
                });
            });
        });
    });
});