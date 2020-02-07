// aspetto che la pagina venga caricata
$(() => {

    // nascondo i bottoni cha ancora non servono
    $(`#btnModifica`).hide();
    $(`#btnAggiungiNuovaPersona`).hide();
    $(`#btnChiudiPaginaAggiungiPersona`).hide();
    
    // bottone aggiungi persona
    $(`#btnAggiungi`).click(() => {
        // creo l'oggetto persona
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
        console.log(persona);
        // serializzo l'oggetto in json
        persona = JSON.stringify({ 'persona' : persona });
        console.log(persona);
        // lo invio al controller
        $.ajax({
            url: '/fattura/aggiungi-persona',
            method: 'post',
            data: {persona}
        })
        .done((personaAgginta) => {
            // aggingo la nuva persona al local storage
            localStorage.setItem(`persona-${personaAgginta.id}`, JSON.stringify(personaAgginta));
            // aggiorno l'indice per cancellare poi le persone dal local storage
            var minMax = JSON.parse(localStorage.getItem(`minMax`));
            minMax.max += 1;
            localStorage.setItem(`minMax`, JSON.stringify(minMax));
            // aggiungo la nuova persona alla select per creare le fatture
            // ?
            // confermo l'avvenuta aggiunta della persona e la mostro
            $(`#aggungiPersona`).empty().append(`La nuova persona<br/>è stata<br/>aggiunta`)
        })
        .fail(() => {
            console.log(`fail`);
        });
    });
});