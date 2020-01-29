
//  index.js

// wait for the page to load
$(() => {

    //  load the header
    $.ajax({
        url: '/parts/header.html',
        method: 'get'
    })
    .done((html) => {
        $('header').html(html);
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