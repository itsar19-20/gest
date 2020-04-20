
$(() => {

    var utente, idUltimoUtenteAggiunto, istruzioniPerIlController;
    $('#btnRegistrati').on('click',() => {

        if($('#inputName').val() || ('#inputSurname').val() || ('#inputPIVA').val() || ('#inputMail').val() || ('#inputAddress').val() || ('#inputPhone').val() || ('#inputUsername').val() || ('#inputPassword').val()) {

            alert(`Utente Registrato!`);

            utente = creaOggettoUtente();
            utente = serializzaUtenteInJson();
            istruzioniPerIlController = 'crea';

            $.ajax({
                url: '/registrati',
                method: 'post',
                data: {utente, istruzioniPerIlController}
            })
            .done((utenteAggiunto) => {
                utenteAggiunto = ricavaOggettoUtente(utenteAggiunto);
                salvaUtenteNelLocalStorage(utenteAggiunto);
                })
            .fail(() => {
                console.log(`fail`);
            });
        }
    })
    

    function creaOggettoUtente() {
        var utente = {
            nome: $('#inputName').val(),
            cognome: $('#inputSurname').val(),
            pIVA: $('#inputPIVA').val(),
            mail: $('#inputMail').val(),
            indirizzo: $('#inputAddress').val(),
            telefono: $('#inputPhone').val(),
            username: $('#inputUsername').val(),
            password: $('#inputPassword').val()
        }
        return utente;
    }

    function serializzautenteInJson() {
        utente = JSON.stringify({ 'utente' : utente });
        return utente;
    }

    function ricavaOggettoUtente(oggetto) {
        UtenteAggiunto = JSON.parse(oggetto);
        idUltimoUtenteAggiunto = utenteAggiunto.id;
        return utenteAggiunto;
    }

    function salvaUtenteNelLocalStorage(oggetto) {
        localStorage.setItem(`utente-${oggetto.id}`, JSON.stringify(oggetto));
    }

});