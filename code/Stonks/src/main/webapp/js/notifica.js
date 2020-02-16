$(document).ready( function () {
	
		
		 var users = JSON.parse(localStorage.getItem('user'));
			user = users.id;
			var firstAccess=users.dataOraUltimoLogin;
			console.log("data users: ");
			console.log(firstAccess);
		//var accesso=localStorage.getItem('primoAccesso');
		//console.log(accesso);
		var dataOggi=new Date();
			//console.log(dataOggi);
			//localStorage.setItem('primoAccesso',false); 
			var dataStorage=localStorage.getItem('dataStorage');
			console.log(dataOggi.getTime());
			console.log(dataStorage);
			var diff=Math.abs(dataOggi.getTime()- dataStorage);
			var diff2=Math.abs( dataOggi.getTime()- firstAccess);
			console.log(diff);
			//3.600.000 millisecondi = 1 ora 
		if( diff>3600000 || diff2< 3000)
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


			$.ajax({
				url: '/parts/notifica.html',
				method: 'get'
			})
			.done((html) => {
				$('#notificaHTML').html(html);

				listaFattura.forEach(f => {
					var dataS=new Date(f.data).toLocaleDateString();
					f.data=dataS;
					console.log(f.eUnaFatturaCliente);
					if(f.eUnaFatturaCliente==true){
						f.eUnaFatturaCliente="entrata";
					}else{
						f.eUnaFatturaCliente="uscita";
					}
					var lordoStringa= parseFloat(f.lordo).toFixed(2);
                 	lordoStringa+=" €";
                 	f.lordo=lordoStringa;

				});

				var table= $('#notificaTable').DataTable({
					data: listaFattura,
					columns: [
						{ title: 'Numero fattura', data: 'numeroFattura'},
						{ title: 'Data', data: 'data'},
						{ title: 'Scadenza: ', data: 'scadenza'},
						{ title: 'Lordo',data: 'lordo'},
						{ title: 'Tipo',data: 'eUnaFatturaCliente'},
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
