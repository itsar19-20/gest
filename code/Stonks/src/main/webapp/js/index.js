
//  index.js

// wait for the page to load
$(() => {

    //  se l'utente è loggato
    if (localStorage.getItem('user')) {
        //  se si trova nella pagina di login
        if (window.location.href.indexOf("login") > -1) {
            //  vai alla home
            location.href = '/';
        }
    //  se invece non è loggato
    } else {
        // se non sei gia nella pagina di login
        if (window.location.href.indexOf("login") > -1) {
            // non fare niente
        } else {
            //  altrimenti vai alla pagina di login
            location.href = '/login.html';
        }
    }

    //  load the header
    $.ajax({
        url: '/parts/header.html',
        method: 'get'
    })
    .done((html) => {
        $('header').html(html);
        //  mostra il nome dell'utente loggato
        var utente = JSON.parse(localStorage.getItem('user'));
        $('#showUserName').text(`Ciao ${utente.username}!`);
        /*
        //  meccanismo per nascondere e mostrare i menu sulla nav bar
        if (localStorage.getItem('user')) {
            $('#mnuLogin').hide();
            $('#mnuLogout').show();
            $('#mnuLogout').click(() => {
                localStorage.removeItem('user');
                location.href = '/';
            });
        } else {
            $('#mnuLogin').show();
            $('#mnuLogout').hide();
        }
        */
    });

    //  load the footer
    $.ajax({
        url: '/parts/footer.html',
        method: 'get'
    })
    .done((html) => {
        $('footer').html(html);
    });

});