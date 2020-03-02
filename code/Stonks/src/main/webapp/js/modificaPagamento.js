$(document).ready( function () {
    var pagamentoObj=JSON.parse( localStorage.getItem("pagamentoModifica"));
    console.log(pagamentoObj.giaPagato);
    localStorage.removeItem("pagamentoModifica");

    $('#numeroFattura').text(pagamentoObj.fattura.numeroFattura);

    var giaPagatoParse=parseFloat(pagamentoObj.giaPagato).toFixed(2);

    $('#idGiaPagato').text(giaPagatoParse);
    var isCompletato;
    if(pagamentoObj.pagato==true){
        isCompletato="sì";
    }else{
        isCompletato="no";
    }
    $('#completato').text(isCompletato);

    $('#btnModifica').click(function(){
      var imput=   parseFloat($('#inputImporto').val()).toFixed(2);

        if(imput >=0){
            if(pagamentoObj.pagato==true && imput < pagamentoObj.fattura.lordo){
                alert("Stai modificando un pagamento già completato. La data del pagamento verrà cancellata.Vuoi continuare?");
            }
            if(pagamentoObj.pagato==false && imput >=pagamentoObj.fattura.lordo){
                alert("L'importo inserito è maggiore del lordo della fattura. Il pagamento verrà completato.Vuoi continuare?");
            }
            $('#idDiv').hide();

            $.ajax({
                url: './modificaPagamento',
                method: 'get',
                data: { 
                        pagamento : pagamentoObj.fattura.id,
                        importoModifica: $("#inputImporto").val()
                    
                    }
             })
             .done(function(pagamento) {
                 console.log(pagamento.giaPagato);
                $("#modalPagamento").modal();

             })


        }else{
            alert("valore inserito non accettabile");
        }

       

    });

    $('#btnCancella').click(function(){
        alert("il pagamento verrà cancellato.Vuoi continuare?");
        $.ajax({
            url: './cancellaPagamento',
            method: 'get',
            data: { 
                    pagamento : pagamentoObj.fattura.id
                }
         })




    });


});