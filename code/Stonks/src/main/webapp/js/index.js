/*

    DEL PROF (dal progetto 'registro elettronico')

$(() => {
    $.ajax({
        url: '/parts/header.html',
        method: 'get'
    })
    .done((html) => {
        $('header').html(html);
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
    });
});

*/

/* waits for the page to load */
console.log('prima riga di index.js')
$(() => {

    console.log('la pagina si è appena caricata')

    /* request and upload the header */
    $.ajax({
        url: '/parts/header.html',
        method: 'get'
    })
    .done((html) => {
        $('header').html(html);
    });

    /* request and upload the the footer */
    $.ajax({
        url: '/parts/footer.html',
        method: 'get'
    })
    .done((html) => {
        $('footer').html(html);
    });

});