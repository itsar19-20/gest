$(document).ready( function () {
    var inputImporto=$("#inputImporto");
    var listCorrect=["0","1","2","3","4","5","6","7","8","9",","];
    var virgolaPos="niente";
    var dopoVirgola="";
    var primaVirgola="";
    
/*

    $('#inputImporto').on('keypress',function(e){
        console.log("premuto key: ");
        console.log(e.key);
        console.log(this.val());

    });
    

*/
/*
imputImporto.on('click',function(e){
    console.log("cliccato");

})
*/


inputImporto.on('keypress',function(e){
   
    
    //console.log(e);
    
    var stringaImp=inputImporto.val().toString();
        
    

    if(e.key===","&& !stringaImp.includes(",")){



        virgolaPos=e.delegateTarget.selectionStart;
        var tmp=stringaImp.substring(virgolaPos);
        if(tmp.length>=3){
            virgolaPos="niente";
            e.preventDefault();
        }

        
    }else if(!stringaImp.includes(",")){
        virgolaPos="niente";

    }else if(stringaImp.includes(",")){
        virgolaPos=stringaImp.indexOf(",");
    }
   
    
    //console.log("virgola a: ");
    //console.log(virgolaPos);
    if(!listCorrect.includes(e.key)){

        
        
        e.preventDefault();
    }else if(e.key===","&& stringaImp.includes(",")){
        
        e.preventDefault();
    }else if(e.delegateTarget.selectionStart===0 && (e.key==="0"||e.key==",")){
        e.preventDefault();
    }

    
    
    if(virgolaPos!="niente"){
        //console.log("pass control 1");
        if(e.delegateTarget.selectionStart-virgolaPos>=3){
            e.preventDefault();
        }
    }
    if(virgolaPos==="niente"){
        dopoVirgola="";
    }
    if(listCorrect.includes(e.key) && e.key!=","){
        if(virgolaPos!="niente"){
            dopoVirgola=stringaImp.substring(virgolaPos+1);
            //console.log(dopoVirgola);
            if(e.delegateTarget.selectionStart>virgolaPos && dopoVirgola.length===2){
                e.preventDefault();

            }
        }
        
    }
    /*
    inputImporto.on('keydown', function(e) {
        var str=inputImporto.val().toString();
        console.log(str);
        str=str.replace('.','');
        console.log(str);
        var noVirgola=str;
        var siVirgola="";
        
        
        if(str.includes(",")){
            var vPos=str.indexOf(",");
             noVirgola=str.substring(0,vPos);
             siVirgola=str.substring(vPos);
            // console.log(siVirgola);
            // console.log(noVirgola);
        }
        

        
        
        
        
      });
      

    /*
    dopoVirgola= stringaImp.substring(0,virgolaPos);
    if(dopoVirgola.length==3 && e.delegateTarget.selectionStart<virgolaPos){

    }
*/

    
   

});

 
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