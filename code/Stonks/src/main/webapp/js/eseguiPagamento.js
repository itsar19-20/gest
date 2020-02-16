$(document).ready( function () {

    $('#btnPagamento').click(function(){
        var value=  parseFloat($('#inputImporto').val()).toFixed(2);

 
        console.log(value);
        if( value>0 ){
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
            var prevPagamento=JSON.parse(localStorage.getItem('prevPagamento'));
            localStorage.removeItem('prevPagamento');
            console.log(prevPagamento);
            if(prevPagamento != null){
                console.log(prevPagamento.giaPagato);
                console.log(value);
                console.log(pagamento.fattura.lordo);
                if(parseFloat(prevPagamento.giaPagato) + parseFloat(value) >= parseFloat(pagamento.fattura.lordo)){
                    console.log("e maggiore 1");
                    console.log(prevPagamento.giaPagato + value);
                    value=parseFloat(pagamento.fattura.lordo)-parseFloat(prevPagamento.giaPagato);
                }
            }else{
                if(value >= pagamento.fattura.lordo){
                    console.log("è maggiore 2");
                    value = pagamento.fattura.lordo;
                }
            }
            
            //value= ParseFloat(value).toFixed(2);
            //pagamento.giaPagato=ParseFloat(pagamento.giaPagato).toFixed(2);


            if(pagamento.pagato==true){
                pagamento.pagato="sì";
            }else{
                pagamento.pagato="no";
            }
            value=parseFloat(value).toFixed(2);
            pagamento.giaPagato=parseFloat(pagamento.giaPagato).toFixed(2);

            $('#datiPagamento').append(`<ul><li>importo versato: ${value} €</li><li> gia pagato: ${pagamento.giaPagato} €</li>
           <li> completato: ${pagamento.pagato}</li></ul>`);

            $('#modalPagamento').modal();

            
            
        });


        }else{
            console.log("valore inserito non accettabile");
            alert("Importo inserito non accettabile");
        }

        
        
        });

       

    });