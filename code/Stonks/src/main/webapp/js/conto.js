$(() => {
    var user = JSON.parse(localStorage.getItem('user'));
    user= user.id;
        $.ajax({
            url: '/conto',
            method: 'get',
            data: { user },
            datatype: "JSON"
        })
        .done(function(conti)  {
            conti.forEach(element => {
                $('#listaMia').append(`
                <li class="list-group-item">
                <div>Nome <span>${element.nome}</span></div>
                <div>Saldo Disponibile<span>${element.saldoDisponibile}</span></div>
                <div>Saldo Utile<span>${element.saldoUtile}</span></div>
                </li>                
                `);
            });

            })
        .fail(function() {
            console.log(btn, '.fail');
        })
        .always(function() {
            console.log(btn, '.always');
        });
        console.log(btn, '.EndClick');
  
});