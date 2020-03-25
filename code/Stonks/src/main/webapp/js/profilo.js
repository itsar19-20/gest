$(() => {
    var user = JSON.parse(localStorage.getItem('user'));
    $('#generale-nome').text(user.username);
    user= user.id;
    var idPersona, idConto;

    ////////////      PERSONE      ////////////
 
    $('#list-persone-list').click(() => { listaPersone(); });

    function listaPersone() {
        $.ajax({
            cache: false,
            type: 'GET',
            timeout: 1000,
            url: '/profilo/persone',
            data: { user },
            dataType: 'json',
            success: (data) => { persone(data); },
            error: (data) => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
        });
    }

    function persone(p) {
        var numeroPerone = 0;
        $('#persone-lista').empty();
        $('#persone-lista').append(`
            <li class="list-group-item">
                <span>Persone aggiunte:</span>
                <span id="persone-numero"></span>
                <button 
                    id="btn-persone-aggiungi" 
                    class="btn btn-success float-right btn-image btn-image-add" 
                    data-toggle="modal" data-target="#modal">
                </button>
            </li>
        `);
        p.forEach(i => {
            numeroPerone++;
            $('#persone-lista').append(`
                <li id="li-${i.id}" class="list-group-item">
                    <span>${i.cognome}</span>
                    <span>${i.nome}</span>
                    <button id="e${i.id}" class="btn btn-warning float-right btn-image btn-image-edit" data-toggle="modal" data-target="#modal"></button>
                </li>
            `);
            if (i.eliminabile == true) {
                $(`#li-${i.id}`).append(`
                    <button id="d${i.id}" class="btn btn-danger float-right btn-persone-elimena btn-image btn-image-delete"></button>
                `);
            }
        });
        $('#persone-numero').text(numeroPerone);

        //Aggiungi
        $("#btn-persone-aggiungi").click(() => {
            pulisciModal();
            $('#modalTitle').text('Aggiungi');
            $(`#btnAggiungi`).show(); 
        });
        
        // Modifica
        $(".btn-image-edit").click(function(event) {
            var idBtn = event.target.id;
            idPersona = '';
            for (var i = 1; i < idBtn.length; i++) {
                idPersona += idBtn.charAt(i);
            }
            $.ajax({
                cache: false,
                type: 'GET',
                timeout: 1000,
                url: '/profilo/persone',
                data: { user, idPersona },
                dataType: 'json',
                success: (data) => { 
                    pulisciModal();
                    $('#modalTitle').text('Modifica');
                    $(`#btnModifica`).show();
                    caricaPersona(data); 
                },
                error: (data) => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
            });
        });

        // Elimina
        $(".btn-image-delete").click(function(event) {
            var idBtn = event.target.id;
            idPersona = '';
            for (var i = 1; i < idBtn.length; i++) {
                idPersona += idBtn.charAt(i);
            }
            if (confirm('Sei davvero sicuro di volere eliminare questa persona.\nNon potrai più tornare indiatro, procedere?')) eliminaPersona(idPersona);
        });
    }
    
    // Aggiungi
    $('#btnAggiungi').click(() => { nomeCognomeValidi(); });
    
    function aggiungiPersona() {  
        creaOggettoPersona();
        var persona = creaOggettoPersona();
        persona = JSON.stringify(persona);
        $.ajax({
            cache: false,
            type: 'POST',
            timeout: 1000,
            url: '/profilo/persone',
            dataType: 'JSON',
            data: { persona },
            success: (p) => {
                if (p != null) {
                    tuttoVerde();
                    $('#btnAggiungi').addClass('disabled');
                    listaPersone();
                    var num = $('#persone-numero').text();
                    $('#persone-numero').text(++num);
                } else { alert('Si è verificato un problema con il server.\nLa persona non è stata eliminata.\nPerfavore riprovare.'); }
            },
            error: () => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
        });
    }

    // Modifica
    function caricaPersona(p) {
        $(`#inputName`).val(p.nome);
        $(`#inputSurname`).val(p.cognome);
        $(`#inputMail`).val(p.mail);
        $(`#inputPhone`).val(p.telefono);
        $(`#inputAddress`).val(p.indirizzo);
        $(`#inputPIVA`).val(p.pIVA);
    }

    $('#btnModifica').click(() => { nomeCognomeValidi(); });

    function modificaPersona() {
        creaOggettoPersona();
        var persona = creaOggettoPersona();
        persona.id = idPersona;
        persona = JSON.stringify(persona);
        $.ajax({
            cache: false,
            type: 'PUT',
            timeout: 1000,
            url: '/profilo/persone',
            data: { persona },
            success: (msg) => {
                if (msg == 'ok') {
                    tuttoVerde();
                    listaPersone();
                } else alert ('Si è verificato un errore con il server.\nProva ad aggiornare la pagina.');
            },
            error: () => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
        });
    }

    function pulisciModal(){
        $(`#inputName`).val(``);
        $(`#inputSurname`).val(``);
        $(`#inputMail`).val(``);
        $(`#inputPhone`).val(``);
        $(`#inputAddress`).val(``);
        $(`#inputPIVA`).val(``);
        $(`#btnAggiungi`).hide();
        $(`#btnModifica`).hide();
        $('#btnAggiungi').removeClass('disabled');
        $('#btnModifica').removeClass('disabled');
        pulisciValidatori();
    }

    function pulisciValidatori () {
        $(`#inputName`).removeClass('is-valid');
        $(`#inputSurname`).removeClass('is-valid');
        $(`#inputMail`).removeClass('is-valid');
        $(`#inputPhone`).removeClass('is-valid');
        $(`#inputAddress`).removeClass('is-valid');
        $(`#inputPIVA`).removeClass('is-valid');
        $(`#btnAggiungi`).removeClass('is-valid');
        $(`#btnModifica`).removeClass('is-valid');
    }
    
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

    function tuttoVerde() {
        $(`#inputName`).addClass('is-valid');
        $(`#inputSurname`).addClass('is-valid');
        $(`#inputMail`).addClass('is-valid');
        $(`#inputPhone`).addClass('is-valid');
        $(`#inputAddress`).addClass('is-valid');
        $(`#inputPIVA`).addClass('is-valid');
        $(`#btnAggiungi`).addClass('is-valid');
        $(`#btnModifica`).addClass('is-valid');
    }

    function nomeCognomeValidi() {
        pulisciValidatori();
        $('#inputName').removeClass('is-invalid');
        $('#inputSurname').removeClass('is-invalid');
        if($('#inputName').val() && $('#inputSurname').val()) {
            if ($('#modalTitle').text() == 'Aggiungi') aggiungiPersona();
            else modificaPersona();
            return;
        } else {
            if(!$('#inputName').val()) $('#inputName').addClass('is-invalid');
            if(!$('#inputSurname').val()) $('#inputSurname').addClass('is-invalid');
        }
    }

    // Elimina
    function eliminaPersona(idPersona) {
        $.ajax({
            cache: false,
            type: 'DELETE',
            timeout: 1000,
            url: '/profilo/persone' + '?idPersona=' + idPersona,
            success: (msg) => {
                if (msg == 'ok') {
                    $('#li-' + idPersona).remove();
                    var num = $('#persone-numero').text();
                    $('#persone-numero').text(--num);
                } else { alert('Si è verificato un problema con il server.\nLa persona non è stata eliminata.\nPerfavore riprovare.'); }
            },
            error: () => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
        });
    }

    ////////////      CONTI      ////////////

    $('#list-conti-list').click(() => { listaConti(); });

    function listaConti() {
        $.ajax({
            cache: false,
            type: 'GET',
            timeout: 1000,
            url: '/profilo/conti',
            data: { user },
            dataType: 'json',
            success: (data) => { conti(data); },
            error: () => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
        });
    }

    function conti(c) {
        var numeroConti = 0;
        $('#conti-lista').empty();
        $('#conti-lista').append(`
            <li class="list-group-item">
                <span>Conti registrati:</span>
                <span id="conti-numero"></span>
                <button 
                    id="btn-conti-aggiungi" 
                    class="btn btn-success float-right btn-image btn-image-add" 
                    data-toggle="modal" data-target="#modalConto">
                </button>
            </li>
        `);
        c.forEach(i => {
            numeroConti++;
            $('#conti-lista').append(`
                <li id="cli-${i.id}" class="list-group-item">
                    <div class="form-row">
                        <input id="ci-${i.id}" type="text" class="form-control col" value="${i.nome}">
                        <div class="invalid-feedback">Lunghezza minima 4 caratteri.</div>
                        <dic class="col">
                            <button id="ce${i.id}" class="btn btn-warning float-right btn-image btn-image-edit edit-nome"></button>
                        </div>
                    </div>
                    <div class="form-row">
                        <input id="cip-${i.id}" type="text" class="form-control col" value="${i.prefisso}" placeholder="Prefisso numero fattura">
                        <div class="invalid-feedback">Questo campo non può essere vuoto.</div>
                        <dic class="col">
                            <button id="cep${i.id}" class="btn btn-warning float-right btn-image btn-image-edit edit-prefisso"></button>
                        </div>
                    </div>
                </li>
            `);
            if (i.eliminabile == true) {
                $(`#cli-${i.id}`).append(`
                    <button id="cd${i.id}" class="btn btn-danger btn-conto-elimena btn-image btn-image-delete float-right"></button>
                `);
            }
        });
        $('#conti-numero').text(numeroConti);

        // Aggiungi
        $('#btn-conti-aggiungi').click(() => {
            $('#inputNomeConto').val('');
            $('#inputNomeConto').removeClass('is-valid');
            $('#inputNomeConto').removeClass('is-invalid');
            $('#btnAggiungiConto').removeClass('disabled');
        });

        // Questo click genera una ridondanza esponenziale fino al ricaricamento della pagina
        // che #!%$%§ù !!!11!
        $('#btnAggiungiConto').click(() => { inviaConto() });

        function inviaConto() {
            $('#inputNomeConto').removeClass('is-valid');
            $('#inputNomeConto').removeClass('is-invalid');
            var nomeNuovoConto = $('#inputNomeConto').val();
            if (nomeNuovoConto.length > 3) {
                var conto = {
                    nome: nomeNuovoConto,
                    utente: user,
                    saldoDisponibile: 0,
                    saldoUtile: 0
                };
                conto = JSON.stringify(conto);
                $.ajax({
                    cache: false,
                    type: 'POST',
                    timeout: 1000,
                    url: '/profilo/conti',
                    data: { conto },
                    success: (data) => {
                        if (data == 'ok') {
                            $('#btnAggiungiConto').addClass('disabled');
                            $('#inputNomeConto').addClass('is-valid');
                            listaConti();
                        } else alert('Si è verificato un problema con il server.\nPerfavore riprovare.');
                    },
                    error: () => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
                });
            } else {
                $('#inputNomeConto').addClass('is-invalid');
            }
        }

        // Modifica
        $(".edit-nome").click(function(event) {
            $('input').removeClass('is-valid');
            $('input').removeClass('is-invalid');
            var idBtn = event.target.id;
            idConto = '';
            for (var i = 2; i < idBtn.length; i++) {
                idConto += idBtn.charAt(i);
            }
            var nomeNuovoConto = $(`#ci-${idConto}`).val();
            var put = 'mp,e;'
            if (nomeNuovoConto.length > 3) {
                nome = nomeNuovoConto;
                $.ajax({
                    cache: false,
                    type: 'PUT',
                    timeout: 1000,
                    url: '/profilo/conti',
                    data: { nome, put, idConto },
                    success: (data) => {
                        if (data != null) {
                            $('#ci-' + idConto).addClass('is-valid');
                        } else alert('Si è verificato un problema con il server.\nPerfavore riprovare.');
                    },
                    error: () => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
                });
            } else {
                $('#ci-' + idConto).addClass('is-invalid');
            }
        });

        // Modifica prefisso
        $(".edit-prefisso").click(function(event) {
            $('input').removeClass('is-valid');
            $('input').removeClass('is-invalid');
            var idBtn = event.target.id;
            idConto = '';
            for (var i = 3; i < idBtn.length; i++) {
                idConto += idBtn.charAt(i);
            }
            var prefisso = $('#cip-' + idConto).val();
            if (prefisso.length > 0) {
                var put = 'prefisso';
                $.ajax({
                    cache: false,
                    type: 'PUT',
                    timeout: 1000,
                    url: '/profilo/conti',
                    data: { idConto, put, prefisso },
                    success: (msg) => {
                        if (msg == 'ok') {
                            $('#cip-' + idConto).addClass('is-valid');
                        } else if (msg == 'no') {
                            alert('Non è possibile modificare il prefisso,\nperchè già in uso quest\'anno.')
                        } else {
                            alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); 
                        }
                    },
                    error: () => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
                });
            } else 
                $('#cip-' + idConto).addClass('is-invalid');
        });

        // Elimina
        $(".btn-image-delete").click(function(event) {
            var idBtn = event.target.id;
            idConto = '';
            for (var i = 2; i < idBtn.length; i++) {
                idConto += idBtn.charAt(i);
            }
            if (confirm('Sei davvero sicuro di volere eliminare questo conto.\nNon potrai più tornare indiatro, procedere?')) {
                $.ajax({
                    cache: false,
                    type: 'DELETE',
                    timeout: 1000,
                    url: '/profilo/conti' + '?idConto=' + idConto,
                    success: (msg) => {
                        if (msg == 'ok') {
                            listaConti();
                        } else { alert('Si è verificato un problema con il server.\nLa persona non è stata eliminata.\nPerfavore riprovare.'); }
                    },
                    error: () => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
                });
            }
        });

    }

    ////////////      PROFILO      ////////////

    $('#list-profilo-list').click(() => {
        $.ajax({
            cache: false,
            type: 'GET',
            timeout: 1000,
            url: '/profilo/profilo',
            data: { user },
            dataType: 'json',
            success: (data) => { profilo(data); },
            error: (data) => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
        });
    });

    function profilo(u) {
        $('#profilo-nome-utente').val(u.username);
        $('#profilo-nome').val(u.nome);
        $('#profilo-cognome').val(u.cognome);
        $('#profilo-mail').val(u.mail);
        $('#profilo-tel').val(u.telefono);
        $('#profilo-piva').val(u.pIVA);
        $('#profilo-indirizzo').val(u.indirizzo);
        $('#profilo-cambia-nome-utente').click(() => { cambiaNomeUtente(); });
        $('#profilo-cambia-password').click(() => { cambiaPassword(); });
        $('#profilo-cambia-dati-anagrafici').click(() => { cambiaDatiAnagrafici(); });
        $('#btn-prefisso').click(() => { cambiaPrefisso(); });
    }

    function cambiaNomeUtente() {
        var nuovoNomeUtente = $('#profilo-nome-utente').val();
        if (nuovoNomeUtente.length > 3) {
            var put = 'username';
            $.ajax({
                cache: false,
                type: 'PUT',
                timeout: 1000,
                url: '/profilo/profilo',
                data: { user, put, nuovoNomeUtente },
                success: (data) => {
                    if (data == 'ok') {
                        $('#profilo-nome-utente').removeClass('is-invalid');
                        $('#profilo-nome-utente').addClass('is-valid');
                        $('#profilo-nome-utente').val(nuovoNomeUtente);
                        $('#showUserName').text(`Ciao ` + nuovoNomeUtente);
                    } else {
                        $('#profilo-cambia-nome-utente-invalid-feedback').empty();
                        $('#profilo-cambia-nome-utente-invalid-feedback').append('Nome utente non disponibile.');
                    }
                },
                error: (data) => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
            });
        } else {
            $('#profilo-cambia-nome-utente-invalid-feedback').empty();
            $('#profilo-cambia-nome-utente-invalid-feedback').append('Nome utente troppo corto. Lunghezza minima 4 caratteri.');
            $('#profilo-nome-utente').addClass('is-invalid');
        }
    }

    function cambiaPassword() {
        $('#profilo-vecchia-password').removeClass('is-valid');
        $('#profilo-nuova-password').removeClass('is-valid');
        $('#profilo-nuova-password-controllo').removeClass('is-valid')
        $('#profilo-vecchia-password').removeClass('is-invalid');
        $('#profilo-nuova-password').removeClass('is-invalid');
        $('#profilo-nuova-password-controllo').removeClass('is-invalid');
        var vecchiaPassword = $('#profilo-vecchia-password').val();
        var nuovaPassword = $('#profilo-nuova-password').val();
        var nuovaPasswordControllo = $('#profilo-nuova-password-controllo').val();
        if (nuovaPassword.length > 7) {
            if (nuovaPassword == nuovaPasswordControllo) {
                var put = 'password';
                $.ajax({
                    cache: false,
                    type: 'PUT',
                    timeout: 1000,
                    url: '/profilo/profilo',
                    data: { user, put, vecchiaPassword, nuovaPassword },
                    success: (data) => {
                        if (data == 'ok') {
                            $('#profilo-vecchia-password').val(nuovaPassword);
                            $('#profilo-nuova-password').val('');
                            $('#profilo-nuova-password-controllo').val('');
                            $('#profilo-vecchia-password').addClass('is-valid');
                            $('#profilo-nuova-password').addClass('is-valid');
                            $('#profilo-nuova-password-controllo').addClass('is-valid');
                        } else {
                            $('#profilo-vecchia-password').addClass('is-invalid');
                            $('#profilo-nuova-password').removeClass('is-invalid');
                            $('#profilo-nuova-password-controllo').removeClass('is-invalid');
                            $('#profilo-cambia-password-invalid-feedback').append('La vecchia password non è corretta.');
                        }
                    },
                    error: (data) => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
                });
            } else {
                $('#profilo-cambia-password-invalid-feedback').empty();
                $('#profilo-cambia-password-invalid-feedback').append('Le nuove password non corrispondono.');
                $('#profilo-nuova-password').addClass('is-invalid');
                $('#profilo-nuova-password-controllo').addClass('is-invalid');
            }
        } else {
            $('#profilo-cambia-password-invalid-feedback').empty();
            $('#profilo-cambia-password-invalid-feedback').append('Password troppo corta. Lunghezza minima 8 caratteri.');
            $('#profilo-nuova-password').addClass('is-invalid');
            $('#profilo-nuova-password-controllo').addClass('is-invalid');
        }
    }

    function cambiaDatiAnagrafici() {
        $('#profilo-nome').removeClass('is-valid');
        $('#profilo-cognome').removeClass('is-valid');
        $('#profilo-mail').removeClass('is-valid');
        $('#profilo-tel').removeClass('is-valid');
        $('#profilo-piva').removeClass('is-valid');
        $('#profilo-indirizzo').removeClass('is-valid');
        $('#profilo-nome').removeClass('is-invalid');
        $('#profilo-cognome').removeClass('is-invalid');
        $('#profilo-mail').removeClass('is-invalid');
        $('#profilo-tel').removeClass('is-invalid');
        if (!$('#profilo-nome').val() || !$('#profilo-cognome').val()) {
            if (!$('#profilo-nome').val()) $('#profilo-nome').addClass('is-invalid');
            if (!$('#profilo-cognome').val()) $('#profilo-cognome').addClass('is-invalid');
            if (!$('#profilo-mail').val()) $('#profilo-mail').addClass('is-invalid');
            if (!$('#profilo-tel').val()) $('#profilo-tel').addClass('is-invalid');
        } else {
            updatedUser = JSON.parse(localStorage.getItem('user'));
            updatedUser.nome = $('#profilo-nome').val();
            updatedUser.cognome = $('#profilo-cognome').val();
            updatedUser.mail = $('#profilo-mail').val();
            updatedUser.telefono = $('#profilo-tel').val();
            updatedUser.pIVA = $('#profilo-piva').val();
            updatedUser.indirizzo = $('#profilo-indirizzo').val();
            updatedUser = JSON.stringify(updatedUser);
            var put = 'anagrafe';
            $.ajax({
                cache: false,
                type: 'PUT',
                timeout: 1000,
                url: '/profilo/profilo',
                data: { user, put, updatedUser },
                success: (data) => {
                    if (data == 'ok') {
                        $('#profilo-nome').addClass('is-valid');
                        $('#profilo-cognome').addClass('is-valid');
                        $('#profilo-mail').addClass('is-valid');
                        $('#profilo-tel').addClass('is-valid');
                        $('#profilo-piva').addClass('is-valid');
                        $('#profilo-indirizzo').addClass('is-valid');
                    } else { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
                },
                error: (data) => { alert('Si è verificato un problema con il server.\nPerfavore riprovare.'); }
            });
        }
    }
});