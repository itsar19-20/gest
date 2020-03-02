$(document).ready( function () {
	
	$('#cercaPagamenti').click(function(){
		var user = JSON.parse(localStorage.getItem('user')  );
		var userID = user.id;
		
		$.ajax({
			url: './allPagamenti',
			method: 'get',
			data: { 
					userID
				}
		 })
		 .done(function(pagamenti){
			if(pagamenti.length>0){
				if($.fn.DataTable.isDataTable('#tblPagamenti'))  {
					$('#tblPagamenti').DataTable().clear().destroy();
					console.log("datatable ancora");
				}
				$("#bottone").attr("disabled",true);



				
				var table= $('#tblPagamenti').DataTable({
               
					data: pagamenti,
					
					columns: [
						{ title: 'Numero fattura:',data: 'fattura.numeroFattura'},
						{ title: 'Data', data: 'fattura.data'},
						{ title: 'Scadenza: ', data: 'fattura.scadenza'},
						{ title: 'Lordo',data: 'fattura.lordo'},
						{ title: 'Tipo', data: 'fattura.eUnaFatturaCliente'},
						{ title:  'GiaPagato', data:'giaPagato'},
						{ title:  'Completato', data:'pagato'}
					   
					]
				});
				$('#tblPagamenti tbody').on('click','tr',function(){
					var dati=table.row(this).data();
					console.log(dati);
				   // $('#idDiv').show();
				   // $('#fatturaSelezionata').text(dati.idFattura);
				   localStorage.removeItem("pagamentoModifica");
				   localStorage.setItem("pagamentoModifica",JSON.stringify(dati));
				   location.href='./pagamentoMod.html';
					
			   });



			}else{
				alert("non è stato registrato alcun pagamento");
			}





		 })



	});
	


});
