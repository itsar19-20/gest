$(() => {
    $('#btnSaldo').click(() => {
        $.ajax({
            url: '/conto',
            method: 'get',
            data: { 
                scelta: $('#idSaldo').val(),
            }
        })
        .done(function(saldo)  {
            console.log(btn, '.done', saldo);
            if (('#idSaldo').val() == 'disponibile'){
                $('#titolo').append('Disponibile');
                $('#paragrafo').append(saldo)
                    
            }else{
                $('#titolo').append('Utile');
                $('#paragrafo').append(saldo);
            }
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