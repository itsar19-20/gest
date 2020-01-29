$(() => {

    //  load the first articol
    $.ajax({
        url: '/parts/articolo.html',
        method: 'get'
    })
    .done((html) => {
        $('#articoli').html(html);
    })

    // add another articol
    $('#btn-add-articol').click(() => {
        
    });

});