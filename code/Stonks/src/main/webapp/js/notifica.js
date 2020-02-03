$(document).ready( function () {
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
});

function waitNotifica(){
	setTimeout(showNotifica, 3000);
	 }
function showNotifica(){
   $('#myModal').modal();
}