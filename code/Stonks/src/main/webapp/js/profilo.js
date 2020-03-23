$(() => {
    var user = JSON.parse(localStorage.getItem('user'));
    user= user.id;

    ////////////      PERSONE      ////////////

    $('#list-persone-list').click(() => {
        $.ajax({
            cache: false,
            type: 'GET',
            timeout: 1000,
            url: '/profilo/persone',
            data: { user },
            dataType: 'json',
            success: (data) => { persone(data); },
            error: (data) => { alert('Si è verificato un problema con il server\nPerfavore riprovare.'); }
        });
    });

    function persone(p) {
        console.log(p);
        var numeroPerone = 0;
        $('#persone-lista').empty();
        $('#persone-lista').append(`
            <li class="list-group-item">
                <span>Persone aggiunte:</span>
                <span id="persone-numero"></span>
                <button id="btn-persone-aggiungi" class="btn btn-success float-right btn-image btn-image-add"></button>
            </li>
        `);
        p.forEach(i => {
            numeroPerone++;
            $('#persone-lista').append(`
                <li class="list-group-item">
                    <span>${i.nome}</span>
                    <span>${i.cognome}</span>
                    <button id="e${i.id}" class="btn btn-warning float-right btn-image btn-image-edit"></button>
                    <button data-id="${i.id}" class="btn btn-danger float-right btn-persone-elimena btn-image btn-image-delete"></button>
                </li>
            `);
        });
        $('#persone-numero').text(numeroPerone);
        $(".btn-image-edit").click(function(event) {
            var idBtn = event.target.id;
            var idPersona = '';
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
                success: (data) => { modificaPersona(data); },
                error: (data) => { alert('Si è verificato un problema con il server\nPerfavore riprovare.'); }
            });
        });
        $(".btn-image-delete").click(function(event) {
            var idBtn = event.target.id;
            var idPersona = '';
            for (var i = 1; i < idBtn.length; i++) {
                idPersona += idBtn.charAt(i);
            }
            $.ajax({
                cache: false,
                type: 'DELETE   ',
                timeout: 1000,
                url: '/profilo/persone',
                data: { user, idPersona },
                // dataType: 'json',
                success: (data) => { /*  */ },
                error: (data) => { alert('Si è verificato un problema con il server\nPerfavore riprovare.'); }
            });
        });
    }

    function modificaPersona(persona) {
        console.log(persona)
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
            error: (data) => { alert('Si è verificato un problema con il server\nPerfavore riprovare.'); }
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
                error: (data) => { alert('Si è verificato un problema con il server\nPerfavore riprovare.'); }
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
                    error: (data) => { alert('Si è verificato un problema con il server\nPerfavore riprovare.'); }
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
                    } else { alert('Si è verificato un problema con il server\nPerfavore riprovare.'); }
                },
                error: (data) => { alert('Si è verificato un problema con il server\nPerfavore riprovare.'); }
            });
        }
    }
});