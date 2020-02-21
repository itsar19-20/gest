$(() => {
    $('#btnSaldo').click(() => {
        $.ajax({
            url: '/conto',
            method: 'get',
            data: { 
                scelta: $('#idSaldo').val(),
            }
        })
        .done(function()  {
            })
        .fail(function() {
            console.log(btn, '.fail');
        })
        .always(function() {
            console.log(btn, '.always');
        });
        console.log(btn, '.EndClick');
    });
  
});