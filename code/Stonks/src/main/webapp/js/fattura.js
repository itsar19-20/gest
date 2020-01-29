$(() => {

    //  load the first articol
    $.ajax({
        url: '/parts/articolo.html',
        method: 'get'
    })
    .done((html) => {
        $('#articoli').html(html);

        do {
            //  load un articol
            $.ajax({
                url: '/parts/articolo.html',
                method: 'get'
            })
            .done((html) => {
                $('#articoli').html(html);
            });
        } while ( $('#btn-add-articolo').click() );

    });

    //  load the first articol
    $('#btn-add-articolo').click(() => {

    });

});