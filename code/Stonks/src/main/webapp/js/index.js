//  index.js

// wait for the page to load
$(() => {

    //  se l'utente è loggato
    if (localStorage.getItem('user')) {
        //  se si trova nella pagina di login
        if (window.location.href.indexOf("login") > 0) {
            //  vai alla home
            location.href = '/';
        }
    //  se invece non è loggato
    } else {
        // se non sei gia nella pagina di login
        if (window.location.href.indexOf("login") > 0 || window.location.href.indexOf("registrati") > 0) {
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
        $('#showUserName').text(`Ciao ${utente.username}`);
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
       $('#btnLogout').click(() => {
            localStorage.removeItem('user');
            location.href = '/';
       });
    });

    //  load the footer
    $.ajax({
        url: '/parts/footer.html',
        method: 'get'
    })
    .done((html) => {
        $('footer').html(html);
    });

    // i remove the unused data from local storage
    // se non ti trovi nella pagina `/fattura/*` fai questo
    if (window.location.href.indexOf("/fattura/") < 0) removeContiAndPersoneFromLocalStorage();
    // rimuovo i dati, dei conti e delle persone collegate all'utente loggato, dal local storage 
    // utilizzati per la creazione di una fattura
    function removeContiAndPersoneFromLocalStorage() {
        // costruire un meccanismo che esegue una query che restituisce il numero massimo e minimo degli id
        // di conti e persone collegati all'utente loggato, così da impostare min e max del ciclo for
        var minMax = JSON.parse(localStorage.getItem(`minMax`));
        for (let i = minMax.min; i <= minMax.max; i++) {
            localStorage.removeItem(`conto-` + i);
            localStorage.removeItem(`persona-` + i);
        }
        localStorage.removeItem(`minMax`);
        localStorage.removeItem(`numeroArticoli`);
    }
});