
$(() => {

   // var utente, idUltimoUtenteAggiunto, istruzioniPerIlController;
   
    $('#btnRegistrati').on('click',() => {

       // if($('#inputName').val() || ('#inputSurname').val() || ('#inputPIVA').val() || ('#inputMail').val() || ('#inputAddress').val() || ('#inputPhone').val() || ('#inputUsername').val() || ('#inputPassword').val()) {
            var valNome=$("#inputName").val();
            var valCognome=$("#inputSurname").val();
            var valPIVA=$("#inputPIVA").val();
            var valMail=$("#inputMail").val();
            var valIndirizzo=$('#inputAddress').val();
            var valTelefono=$('#inputPhone').val();
            var valUsername=$('#inputUsername').val();
            var valPassword=$('#inputPassword').val();

            var valConfermaPass=$('#inputCPassword').val();

            if(valNome && valCognome && valPIVA && valMail && valIndirizzo && valTelefono && valUsername && valPassword){
                console.log("i campi ci sono");
                if(valPassword.length<=7){
                    alert("la password deve avere almeno 8 caratteri");
                }else if(!/\d/.test(valPassword)){
                    alert("La password deve contenere almeno un numero");
                }else if(!/[A-Z]/.test(valPassword)){
                    alert("la password deve contenere almeno una lettera maiuscola");
                }else if(!/[a-z]/.test(valPassword)){
                    alert("la password deve contenere almeno una lettera minuscola");
                }else if(valConfermaPass!=valPassword){
                    alert("la password non coincide con quella di conferma");
                }else{
                   

                    $.ajax({
                        url: '/registrati',
                        method: 'post',
                        data: 
                        {
                            nome: valNome,
                            cognome: valCognome,
                            pIVA: valPIVA,
                            mail: valMail,
                            indirizzo: valIndirizzo,
                            telefono: valTelefono,
                            username: valUsername,
                            password: valPassword
                            //metodoDiRegistrazione: null
                        }
                    })
                    .done(function(user){
                        if(user){
                            if(user!="giaUsato"){
                            //alert("utente registrato");
                            
                            localStorage.setItem('user', JSON.stringify(user));
                            //location.href = '/';
                            $('#modalRegistrazione').modal();

                            }else{
                                alert("questo username è già in uso");
                            }
                        }else{
                            alert("registrazione fallita");
                        }
        
        
        
                    })
                }

                
            }else{
                alert("riempire tutti i campi");
            }

           

    
    });
            
    /*

    function creaOggettoUtente() {
        var utente = {
            nome: $('#inputName').val(),
            cognome: $('#inputSurname').val(),
            pIVA: $('#inputPIVA').val(),
            mail: $('#inputMail').val(),
            indirizzo: $('#inputAddress').val(),
            telefono: $('#inputPhone').val(),
            username: $('#inputUsername').val(),
            password: $('#inputPassword').val()
        }
        return utente;
    }
/*
    function serializzautenteInJson() {
        utente = JSON.stringify({ 'utente' : utente });
        return utente;
    }

    function ricavaOggettoUtente(oggetto) {
        UtenteAggiunto = JSON.parse(oggetto);
        idUltimoUtenteAggiunto = utenteAggiunto.id;
        return utenteAggiunto;
    }

    function salvaUtenteNelLocalStorage(oggetto) {
        localStorage.setItem(`utente-${oggetto.id}`, JSON.stringify(oggetto));
    }
*/
});