$(() => {
    var user = JSON.parse(localStorage.getItem('user'));
    user= user.id;
    $('#list-profilo-list').click(() => {
        $.ajax({
            cache: false,
            type: 'GET',
            timeout: 1000,
            url: '/profilo/profilo',
            data: { user },
            dataType: 'json',
            success: (data) => { profilo(data); },
            error: (data) => { console.log(data); }
        });
    });

    function profilo(u) {
        console.log(u);
        $('#profilo-nome-utente').val(u.username);
        $('#profilo-cambia-nome-utente').click(() => { cambiaNomeUtente(); });
        $('#profilo-cambia-password').click(() => { cambiaPassword(); });
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
});