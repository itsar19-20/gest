// aspetto che la pagina venga caricata
$(() => {

    var persona, idUltimaPersonaAggiunta,  istruzioniPerIlController;
    // Aggiungi persona
    $('#btn-add-new-persona').on('click',function(){
        // Il modal è già stato attivato dall'HTML da questo stesso bottone
        // nascondo gli elementi che ancora non servono
        cleanAllFields();
        
        // bottone aggiungi persona
        $(`#btnAggiungi`).on('click',() => {

            // controllo che sia presente almeno il nome o il cognome
            if($('#inputName').val() || $('#inputSurname').val()) {
                // sistemo lo stile grafico
                $(`#inputName`).css({"border": "lightgray 1px solid"});
                $(`#inputSurname`).css({"border": "lightgray 1px solid"});
                alert(`Persona aggiunta!`);
                
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
                    $(`#ilMioBelModalTitle`).text(`Hai aggunto una nuova persona`)
                    $(`#btnAggiungi`).hide();
                    $(`#btnModifica`).show();
                    $(`#btnAggiungiNuovaPersona`).show();
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
                        $('#ilMioBelModalTitle').text(`Modifiche salvate`);
                        alert(`Modifiche salvate`);                
                    })
                    .fail(() => {
                        console.log(`fail`);
                    });
                });

                // bottone per aggiungerne un'altra
                $(`#btnAggiungiNuovaPersona`).click(() => {
                    cleanAllFields();
                });
            } else {
                // mostro un aiuto grafico
                $(`#inputName`).css({"border": "red 2px solid"});
                $(`#inputSurname`).css({"border": "red 2px solid"});
                // mostro un messaggio d'errore
                alert(`Perfavore, inserire almeno il nome o il cognome`);
                // Se c'è il tempo togliere l'alert e cambiare il testo dei placeholder mostrandoli anche in rosso
            };
        });
    });
    
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

    function serializzaPersonaInJson() {
        persona = JSON.stringify({ 'persona' : persona });
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

    function cleanAllFields() {
        $(`#inputName`).val(``);
        $(`#inputSurname`).val(``);
        $(`#inputMail`).val(``);
        $(`#inputPhone`).val(``);
        $(`#inputAddress`).val(``);
        $(`#inputPIVA`).val(``);
        $(`#ilMioBelModalTitle`).text(`Aggiungi una nuova persona`);
        $(`#btnAggiungi`).show();
        $(`#btnModifica`).hide();
        $(`#btnAggiungiNuovaPersona`).hide();
    }

});