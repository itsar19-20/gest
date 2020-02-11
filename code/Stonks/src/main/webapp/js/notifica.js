$(document).ready( function () {
	
		
		 var user = JSON.parse(localStorage.getItem('user'));
		    user = user.id;
		 
		$.ajax({
			url: './notifica',
			method: 'get',
			data:{
				listaNotifica: localStorage.getItem("anticipo"),
				user
			}
		})
		.done(function(listaFattura) {
			if(listaFattura.length>0){
			console.log("ciao");
			//console.log(listaFattura[0].lordo);
			//console.log(listaFattura[1].lordo);
			
			var table= $('#notificaTable').DataTable({
                data: listaFattura,
                columns: [
                    { title: 'ID', data: 'idFattura'},
                    { title: 'Data Pagamento', data: 'data'},
                    { title: 'Pagata', data: 'pagata'},
                    { title: 'Importo',data: 'lordo'},
                ]
			});
			
			$('#divNotifica').show();
			
			 $('#notificaTable tbody').on('click','tr',function(){
	                var dati=table.row(this).data();
	                console.log(dati);
	               // $('#idDiv').show();
	               // $('#fatturaSelezionata').text(dati.idFattura);
	               localStorage.removeItem("fatturaDaPagare");
	               localStorage.setItem("fatturaDaPagare",JSON.stringify(dati));
	               location.href='./pagamento.html';
	                
	           });


			 function waitNotifica(){
					setTimeout(showNotifica, 3000);
					 }
				function showNotifica(){
				   $('#myModal').modal();
				}
				
				waitNotifica();
			}
			/*var fattura = listaFattura[0];
			console.log("ciao");
            if (fattura.idFattura>0) {
				console.log("sono nell if");
            	console.log(fattura.idFattura);
				$('#idFattura').text(`${fattura.idFattura}`);
				
				//$('#idFattura').text(JSON.parse(localStorage.getItem("fatturaDaPagare")).idFattura);
				waitNotifica();
            } else {
            	console.log("non ce nessuna fattura");
            }
            localStorage.removeItem('fatturaDaPagare');
			localStorage.setItem('fatturaDaPagare',fattura);
            */
        })
			
	
	
      



});
