$(document).ready( function () {
	var fatturaObj=JSON.parse(localStorage.getItem('fatturaDaPagare'));
	// rimuovo dal local storage visto che la fattura è in fatturaObj
	//localStorage.removeItem('fatturaDaPagare');
  
        $('#fatturaDaPagare').text(fatturaObj.numeroFattura);
        
        
        $(()=>{
		
            $.ajax({
                url: './cercaPagamento',
                method: 'get',
                data:{
                    fattura: fatturaObj.id
                }
                
                
            })
            .done(function(pagamento){

				
				localStorage.removeItem("prevPagamento");

				
                if(pagamento){
					var giaPagatoStringa= parseFloat(pagamento.giaPagato).toFixed(2);
                 	giaPagatoStringa+=" €";
                 	
                    
					$('#idGiaPagato').text(giaPagatoStringa);
					localStorage.setItem("prevPagamento",JSON.stringify(pagamento))

					if(window.location.href.indexOf("login")>0){
						localStorage.removeItem("prevPagamento");
					}
                     
                     
    
                }else{
                    $('#idGiaPagato').text("0.00 €");
                }
    
                
    
            });

        });
	
	/*

	
	$(()=>{
		
		$.ajax({
			url: './notifica',
			method: 'get',
			data:{
				listaNotifica: localStorage.getItem("anticipo")
			}
			
			
		})
		.done(function(listaFatture){
			if(!listaFatture.length==0){
				listaFatture.forEach(f => {
					console.log(f.idFattura);
					$('#idFattura').text(`${f.idFattura}`);
					waitNotifica();
				});

			}else{
				console.log("non ce nessuna fattura");
			}

			

		});
	});
	*/
	
});