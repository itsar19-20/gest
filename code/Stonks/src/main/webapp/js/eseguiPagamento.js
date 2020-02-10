$(document).ready( function () {

    $('#btnPagamento').click(function(){
        var value=  parseFloat($('#inputImporto').val()).toFixed(2);


        console.log(value);
        if( value>=0 ){
            $('#idDiv').hide();
            console.log("value ok");
        
            var fatturaObj=JSON.parse(localStorage.getItem('fatturaDaPagare'));
            
        
            $.ajax({
            url: './paga',
            method: 'get',
             data: { 
                   nuovoImporto: value
               }
        })
        .done((pagamento)=>{
            console.log(pagamento.giaPagato);
            console.log("mostro il modal");
            $('#datiPagamento').append(` importo versato: ${value}, gia pagato: ${pagamento.giaPagato}
            ,id pagamento: ${pagamento.idPagamento}`);
            
            $('#modalPagamento').modal();

        });


        }else{
            console.log("valore inserito non accettabile");
            alert("Importo inserito non accettabile");
        }

        
        
        });
    });