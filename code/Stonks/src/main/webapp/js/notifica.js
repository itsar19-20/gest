$(document).ready( function () {
	
		
		 var user = JSON.parse(localStorage.getItem('user'));
			user = user.id;
		//var accesso=localStorage.getItem('primoAccesso');
		//console.log(accesso);
		var dataOggi=new Date();
			//console.log(dataOggi);
			//localStorage.setItem('primoAccesso',false);
			var dataStorage=localStorage.getItem('dataStorage');
			console.log(dataOggi.getTime());
			console.log(dataStorage);
			var diff=Math.abs(dataOggi.getTime()- dataStorage);	
			console.log(diff);
			//3.600.000 millisecondi = 1 ora 
		if(diff>3600000)
		{

			localStorage.setItem("dataStorage",dataOggi.getTime());

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
			
        })
			
	} 
	
      



});
