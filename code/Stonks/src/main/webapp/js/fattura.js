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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //  button crea fattura
    $('#btn-crea-fattura').click(() => {
        $.ajax({
            url: '/fattura/crea.html',
            method: 'get'
        })
        .done((html) => {
            //  svuota il tag main e appende il form per creare una nuova fattura
            $('main').empty().append(html);
            //  invia al controller l'utente loggato
            var user = JSON.parse(localStorage.getItem('user'));
            //  impostare automaticamente la data di oggi
            var date = new Date();
            var month = date.getMonth()+1;
            if (month < 10) month = "0" + month;
            var day = date.getDate();
            if (day < 10) day = "0" + day;
            var today = date.getFullYear()+'-'+month+'-'+day;
            $('#input-data').val(today);
            //  richiesta della lista dei conti associati
            var whatIWant = 'conti';
            user = user.idPersona;
            $.ajax({
                url: '/fattura/crea',
                method: 'post',
                data: {user, whatIWant}
            })
            //  il controller risponde con i conti collegati all'utente loggato
            .done((lista) => {
                console.log('conti')
                var select = document.getElementById("input-conto");
                $('#input-conto').empty();
                lista.forEach(element => {
                    var option = document.createElement("option");
                    option.value = `${element.id}`;
                    option.text = `${element.nome}`;
                    select.add(option);
                });
                    
                //  richiesta della lista delle persone associate
                whatIWant = 'persone';
                $.ajax({
                    url: '/fattura/crea',
                    method: 'post',
                    data: {user, whatIWant}
                })
                //  il controller risponde con le persone collegate all'utente loggato
                .done((lista) => {
                    console.log('persone')
                    var select = document.getElementById("input-persona");
                    $('#input-persona').empty();
                    lista.forEach(element => {
                        var option = document.createElement("option");
                        option.value = `${element.id}`;
                        option.text = `${element.nome} ${element.cognome}`;
                        select.add(option);
                    });
                })
                .fail(() => {
                    console.log('problema')
                });
            })
            .fail(() => {
                console.log('problema')
            });

            //  load the first article
            $.ajax({
                url: '/parts/articolo.html',
                method: 'get'
            })
            .done((html) => {
                var numeroArticoli = 1;
                $('#articoli').append(html
                    .replace('§numero0§', numeroArticoli)
                    .replace('§numero1§', numeroArticoli)
                    .replace('§numero2§', numeroArticoli)
                    .replace('§numero3§', numeroArticoli)
                    .replace('§numero4§', numeroArticoli)
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
                            .replace('§numero0§', numeroArticoli)
                            .replace('§numero1§', numeroArticoli)
                            .replace('§numero2§', numeroArticoli)
                            .replace('§numero3§', numeroArticoli)
                            .replace('§numero4§', numeroArticoli)
                            .replace('§btn-text§', '-')
                            .replace('§click§', rimuoviQuestoDiv)
                        );
                        console.log('hai aggiunto l\'articolo', numeroArticoli);
                    });
                });
            });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // submit button - salva fattura
            $('#btn-submit').click(() => {
                // prendo ogni articolo singolarmente
                var numArticoliInseriti = $('.articolo').length
                var count = 1;
                var numElementiEsistentiGiaToccati = 0;
                var articoli = [];
                while (true) {
                    var myArt = 'articolo-list-item-' + count;
                    var element = !!document.getElementById(myArt);
                    if (element) {
                        var questoArticolo = `{ descrizione: '` + $(myArt) + `', quantita: ` + myArt + `, prezzo: ` + myArt + ` }, `
                        console.log(questoArticolo)
                        articoli.push(questoArticolo);
                        numElementiEsistentiGiaToccati++;
                    }
                    if (numElementiEsistentiGiaToccati == numArticoliInseriti) break;
                }
                console.log(`tutti gli articoli`, articoli);



                /*
                $('.articolo').forEach(element => {
                    console.log()
                });

                array.forEach(element => {
                    
                });
                //  creo un contenitore per tutti gli articoli
                var articoli = [];  //  var articoli = new Array(numArticoliInseriti)
                for (let index = 0; index < numArticoliInseriti; index++) {
                    //  const element = array[index];
                    articoli.push(index);
                }
    */
                //  var articoli = [
                //      { descrizione: 'piadina', quantita: 3, prezzo: 7 },
                //      { descrizione: 'pita', quantita: 10, prezzo: 3 },
                //      { descrizione: 'kebab', quantita: 2, prezzo: 5 },
                //  ];
                articoli = JSON.stringify({ 'articoli': articoli });
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