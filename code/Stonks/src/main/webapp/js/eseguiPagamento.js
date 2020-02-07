$(document).ready( function () {

    $('#btnPagamento').click(function(){
        var value=  parseFloat($('#inputImporto').val()).toFixed(2);


        console.log(value);
        if( value>=0 ){
            
            console.log("value ok");
        
            var fatturaObj=JSON.parse(localStorage.getItem('fatturaDaPagare'));
        
        
            $.ajax({
            url: './paga',
            method: 'get',
             data: { 
                   nuovoImporto: value
               }
        })
        }else{
            console.log("valore inserito non accettabile");
            alert("Importo inserito non accettabile");
        }

        
        
        });
    });