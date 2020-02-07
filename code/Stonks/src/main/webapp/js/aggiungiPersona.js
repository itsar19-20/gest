// aspetto che la pagina venga caricata
$(() => {

    var persona;
    var idUltimaPersonaAggiunta;
    var istruzioniPerIlController;

    // nascondo gli elementi che ancora non servono
    $(`#personaAggiunta`).hide();
    $(`#modificaAvventa`).hide();
    $(`#btnModifica`).hide();
    $(`#btnAggiungiNuovaPersona`).hide();
    $(`#btnChiudiPaginaAggiungiPersona`).hide();
    
    // bottone aggiungi persona
    $(`#btnAggiungi`).click(() => {
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
            // aggiorno l'indice per cancellare poi le persone dal local storage
            var minMax = JSON.parse(localStorage.getItem(`minMax`));
            minMax.max += 1;
            localStorage.setItem(`minMax`, JSON.stringify(minMax));
            // aggiungo la nuova persona alla select per creare le fatture    //////////////////////////////////////////
            // ?
            // $('/fattura/ #input-persona').
            
            console.log(personaAgginta);
            var element = personaAgginta;
            var option = document.createElement("option");
            option.value = `${element.id}`;
            option.text = `${element.nome} ${element.cognome}`;
            console.log(option);

            var select = document.getElementById("/fattura/index.html #input-persona");
            console.log(select);

            select.add(option);
            

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
            // aggiungo la nuova persona alla select per creare le fatture
            // ?
            $('#personaAggiunta').hide();
            $('#modificaAvventa').show();
            alert(`Modifiche salvate`);

        })
        .fail(() => {
            console.log(`fail`);
        });
    });

    // bottone per aggiuhngerne un'altra
    $(`#btnAggiungiNuovaPersona`).click(() => {
        // ricarico la pagina
        location.href = "";
    });

    // bottone ok
    $(`#btnChiudiPaginaAggiungiPersona`).click(() => {
        // chiudo la pagina per aggiungere nuove persone
        close();
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