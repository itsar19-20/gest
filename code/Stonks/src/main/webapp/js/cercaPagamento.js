$(document).ready( function () {
    var fatturaObj=JSON.parse(localStorage.getItem('fatturaDaPagare'));
        $('#fatturaDaPagare').text(fatturaObj.id);
        
        


        $(()=>{
		
            $.ajax({
                url: './cercaPagamento',
                method: 'get',
                data:{
                    fattura: fatturaObj.id
                }
                
                
            })
            .done(function(pagamento){
                if(pagamento){
                    
					$('#idGiaPagato').text(pagamento.giaPagato);
					
                     
                     
    
                }else{
                    $('#idGiaPagato').text("0");
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