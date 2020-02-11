// aspetto che la pagina venga caricata
$(() => {

    var ciao = `buongiorno`;

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
    user = user.id;
    $.ajax({
        url: '/fattura/crea',
        method: 'post',
        data: {user, whatIWant}
    })
    //  il controller risponde con i conti collegati all'utente loggato
    .done((lista) => {
        var select = document.getElementById("input-conto");
        lista.forEach(element => {
        localStorage.setItem(`conto-${element.id}`, JSON.stringify(element));
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
            var select = document.getElementById("input-persona");
            lista.forEach(element => {
                localStorage.setItem(`persona-${element.id}`, JSON.stringify(element));
                var option = document.createElement("option");
                option.value = `${element.id}`;
                option.text = `${element.nome} ${element.cognome}`;
                select.add(option);
            });

            // richiedo al controller l'id minimo e massimo delle entita Conto e Persona
            // collegate all' utente loggato, per poterle poi eliminare dal localStorage
            whatIWant = `minMax`;
            $.ajax({
                url: `/fattura/crea`,
                method: 'post',
                data: {user, whatIWant}
            })
            .done((fromControler) => {
                localStorage.setItem(`minMax`, JSON.stringify(fromControler));
            })
            .fail(() => {
                console.log(`problema nel caricamento dell'indice minMax`);
            });
        })
        .fail(() => {
            console.log('problema nel caricamento delle persone')
        });
    })
    .fail(() => {
        console.log('problema nel caricamento dei conti')
    });

    //  load the first article
    $.ajax({
        url: '/parts/articolo.html',
        method: 'get'
    })
    .done((html) => {
        var numeroArticoli = 1;
        localStorage.setItem(`numeroArticoli`, numeroArticoli);
        $('#articoli').append(html
            .replace('§numero0§', numeroArticoli)
            .replace('§numero1§', numeroArticoli)
            .replace('§numero2§', numeroArticoli)
            .replace('§numero3§', numeroArticoli)
            .replace('§numero4§', numeroArticoli)
            .replace('§numero5§', numeroArticoli)
            .replace('§btn-text§', '+')
            .replace('§click§', ''));
        
        // add another article
        $('#btn-article-1').click(() => {
            numeroArticoli++;
            localStorage.setItem(`numeroArticoli`, numeroArticoli)
            $.ajax({
                url: '/parts/articolo.html',
                method: 'get'
            })
            .done((html) => {
                //  the next line allows you to remove the newly added article
                var rimuoviQuestoDiv = '$(\'#articolo-list-item-' + numeroArticoli + '\').remove();';
                $('#articoli').append(html
                    .replace('§numero0§', numeroArticoli)
                    .replace('§numero1§', numeroArticoli)
                    .replace('§numero2§', numeroArticoli)
                    .replace('§numero3§', numeroArticoli)
                    .replace('§numero4§', numeroArticoli)
                    .replace('§numero5§', numeroArticoli)
                    .replace('§btn-text§', '-')
                    .replace('§click§', rimuoviQuestoDiv)
                );             
            });
        });
    });
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // bottone per aggiungere una nuova persona
    $(`#btn-add-new-persona`).click(() => {

        var persona;
        var idUltimaPersonaAggiunta;
        var istruzioniPerIlController;
        
        // aggiungi persona
        aggiungiPersona();
        function aggiungiPersona() {
            $.ajax({
                url: '/fattura/aggiungi-persona.html',
                method: 'get'
            })
            .done((html) => {
                //  svuota il tag main e appende il form per aggiungere una nuova persona
                $('main').empty().append(html);

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////

                // nascondo gli elementi che ancora non servono
                $(`#personaAggiunta`).hide();
                $(`#modificaAvventa`).hide();
                $(`#btnModifica`).hide();
                $(`#btnAggiungiNuovaPersona`).hide();
                $(`#btnChiudiPaginaAggiungiPersona`).hide();
                
                // bottone aggiungi persona
                $(`#btnAggiungi`).click(() => {
                    // controllo che sia presente almeno il nome o il cognome
                    if($('#inputName').val() || $('#inputSurname').val()) {
                        // sistemo lo stile grafico
                        $(`#inputName`).css({"border": "none"});
                        $(`#inputSurname`).css({"border": "none"});
                        
                        // creo l'oggetto persona
                        persona = creaOggettoPersona();
                        // serializzo l'oggetto in json
                        persona = serializzaPersonaInJson();
                        // istruisco il controller su cosa voglio
                        istruzioniPerIlController = 'crea';
                        // lo invio al controller
                        $.ajax({
                            url: '/fattura/aggiungi-persona',
                            method: 'post',
                            data: {persona, istruzioniPerIlController}
                        })
                        .done((personaAgginta) => {
                            personaAgginta = ricavaOggettoPersona(personaAgginta);
                            salvaPersonaNelLocalStorage(personaAgginta);
                            // confermo l'avvenuta aggiunta della persona e la mostro
                            $(`#aggungiPersona`).hide();
                            $(`#personaAggiunta`).show();
                            $(`#btnAggiungi`).hide();
                            $(`#btnModifica`).show();
                            $(`#btnAggiungiNuovaPersona`).show();
                            $(`#btnChiudiPaginaAggiungiPersona`).show();
                        })
                        .fail(() => {
                            console.log(`fail`);
                        });

                        // bottone modifica
                        $(`#btnModifica`).click(() => {
                            // ricreo l'oggetto persona
                            persona = creaOggettoPersona();
                            // gli assegno l'id della persona appena aggiunta al database
                            persona.id = idUltimaPersonaAggiunta;
                            // serializzo l'oggetto in json
                            persona = serializzaPersonaInJson()
                            // istruisco il controller
                            istruzioniPerIlController = 'modifica';
                            // invio il nuovo oggetto al controller
                            $.ajax({
                                url: '/fattura/aggiungi-persona',
                                method: 'post',
                                data: {persona, istruzioniPerIlController}
                            })
                            .done((personaAgginta) => {
                                personaAgginta = ricavaOggettoPersona(personaAgginta);
                                salvaPersonaNelLocalStorage(personaAgginta);
                                $('#personaAggiunta').hide();
                                $('#modificaAvventa').show();
                                alert(`Modifiche salvate`);                
                            })
                            .fail(() => {
                                console.log(`fail`);
                            });
                        });
    
                        // bottone per aggiungerne un'altra
                        $(`#btnAggiungiNuovaPersona`).click(() => {
                            // ricarico la pagina
                            aggiungiPersona();
                        });
                
                        // bottone ok
                        $(`#btnChiudiPaginaAggiungiPersona`).click(() => {
                            // chiudo la pagina per aggiungere nuove persone
                            location.href = "";
                        });
                    } else {
                        // mostro un aiuto grafico
                        $(`#inputName`).css({"border": "red 2px solid"});
                        $(`#inputSurname`).css({"border": "red 2px solid"});
                        // mostro un messaggio d'errore
                        alert(`Perfavore, inserire almeno il nome o il cognome`);
                    };
                });
            })
            .fail(() => {
                console.log(`fail`)
            });
        };

        function creaOggettoPersona() {
            var user = JSON.parse(localStorage.getItem(`user`));
            var persona = {
                nome: $(`#inputName`).val(),
                cognome: $(`#inputSurname`).val(),
                mail: $(`#inputMail`).val(),
                telefono: $(`#inputPhone`).val(),
                indirizzo: $(`#inputAddress`).val(),
                pIVA: $(`#inputPIVA`).val(),
                autore: user = user.id
            }
            return persona;
        }

        function ricavaOggettoPersona(oggetto) {
            // ricreo l'oggetto persona dalla stinga json inviata del controller
            personaAgginta = JSON.parse(oggetto);
            // memorizzo il suo id
            idUltimaPersonaAggiunta = personaAgginta.id;
            return personaAgginta;
        }

        function salvaPersonaNelLocalStorage(oggetto) {
            // aggingo la nuova persona al local storage
            localStorage.setItem(`persona-${oggetto.id}`, JSON.stringify(oggetto));
        }

        function serializzaPersonaInJson() {
            persona = JSON.stringify({ 'persona' : persona });
            return persona;
        }
    });
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // submit button - salva fattura
    $('#btn-submit').click(() => {
        var lordo = $('#totale').text() + `F`;
        // creo l'oggetto fattura
        var fattura = {
            eUnaFatturaCliente: $('#input-tipo-fattura').val(),
            conto: JSON.parse(localStorage.getItem(`conto-` + $('#input-conto').val())),
            persona: JSON.parse(localStorage.getItem(`persona-` + $('#input-persona').val())),
            data: $('#input-data').val(),
            scadenza: $('#input-scadenza').val(),
            lordo: lordo,
            nota: $('#input-note').val(),           
        };
        // serializzo l'oggetto
        fattura = JSON.stringify({ 'fattura': fattura });
        // prendo ogni articolo singolarmente
        // serializzo manualmente un oggetto di oggietti in una stringa in formato json
        var numArticoliInseriti = $('.articolo').length
        var count = 1;
        var numElementiEsistentiGiaToccati = 0;
        var articoli = `{"articoli":[`;
        while (true) {
            var element = !!document.getElementById('articolo-list-item-' + count);
            if (element) {
                if (count > 1) articoli += `,`;
                var ia = `#input-articolo-`;
                var desc = $(ia + 'descrizione-' + count).val();
                var quan = $(ia + 'quantita-' + count).val();
                var prez = $(ia + 'prezzo-' + count).val();
                articoli += `{"descrizione":"` + desc + `","quantita":` + quan + `,"prezzo":`  + prez + `}`;
                numElementiEsistentiGiaToccati++;
            }
            if (numElementiEsistentiGiaToccati == numArticoliInseriti) {
                articoli += `]}`;
                break;
            };
            count++;
        }
        // invio al controller l'oggetto Fattura e l'oggetto di oggetti Articolo
        $.ajax({
            url: '/fattura/salva',
            method: 'post',
            //  mapping the datas for the servlet controller
            data: { fattura, articoli }
            /*
            success: function () {
                //  code
            }
            failure: function () {
                //  code
            }
            */
        })
        .done(() => {
            removeContiAndPersoneFromLocalStorage();
            location.href = `/`;
        })
        .fail(() => {
            console.log('fattura non salvata');
        });
    });

        /*
    function allStorage() {
        var values = [],
            keys = Object.keys(localStorage),
            i = keys.length;
        while ( i-- ) {
            values.push( localStorage.getItem(keys[i]) );
        }
        return values;
    }
    console.log(allStorage());
    */

    function removeContiAndPersoneFromLocalStorage() {
        // costruire un meccanismo che esegue una query che restituisce il numero massimo e minimo degli id
        // di conti e persone collegati all'utente loggato, così da impostare min e max del ciclo for
        var minMax = JSON.parse(localStorage.getItem(`minMax`));
        for (let i = minMax.min; i <= minMax.max; i++) {
            localStorage.removeItem(`conto-` + i);
            localStorage.removeItem(`persona-` + i);
        }
        localStorage.removeItem(`minMax`);
        localStorage.removeItem(`numertoArticoli`);
    }

});