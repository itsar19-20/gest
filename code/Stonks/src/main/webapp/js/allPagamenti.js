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
					$('#tblPagamenti').DataTable().clear();
					$('#tblPagamenti').DataTable().destroy();
					$("#tblPagamenti thead").remove();
					console.log("datatable ancora");
				}
				//$("#bottone").attr("disabled",true);
				let myCanvas =document.getElementById("myCanvas");
				let myContext=myCanvas.getContext("2d");
				myContext.clearRect(0, 0, myCanvas.width, myCanvas.height);
				
				pagamenti.forEach(p => {
					var dataP=new Date(p.fattura.data).toLocaleDateString();
					p.fattura.data=dataP;
					
					if(p.fattura.eUnaFatturaCliente==true){
						p.fattura.eUnaFatturaCliente="entrata";
					}else{
						p.fattura.eUnaFatturaCliente="uscita";
					}

					if(p.pagato){
						p.pagato="sì";
					}else{
						p.pagato="no";
					}
			
					var lordoStringa= parseFloat(p.fattura.lordo).toFixed(2);
					 lordoStringa+=" €";
					 p.fattura.lordo=lordoStringa;

					var giaPagato1= parseFloat(p.giaPagato).toFixed(2);
					giaPagato1+=" €";
					p.giaPagato=giaPagato1;
				});



				
				var table= $('#tblPagamenti').DataTable({
               
					data: pagamenti,
					
					columns: [
						{ title: 'Numero fattura:',data: 'fattura.numeroFattura'},
						{ title: 'Data fattura', data: 'fattura.data'},
						{ title: 'Scadenza: ', data: 'fattura.scadenza'},
						{ title: 'Lordo',data: 'fattura.lordo'},
						{ title: 'Tipo', data: 'fattura.eUnaFatturaCliente'},
						{ title:  'GiaPagato', data:'giaPagato'},
						{ title:  'Completato', data:'pagato'},
						
					   
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
