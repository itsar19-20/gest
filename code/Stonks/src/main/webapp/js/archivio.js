$(() => {
    // Richiedo la lista di tutte la fatture dell'utente loggato
    $.ajax({
        url: '/archivio/getAllMineInvoices',
        method: 'get'
    })
    .done(function(listaFatture) {
        console.log(listaFatture);
        // Per ogni fattura ricevuta
        listaFatture.forEach(element => {
            // Converto la data in un formato umano
            var date = new Date(element.data);
            var month = date.getMonth()+1;
            if (month < 10) month = "0" + month;
            var day = date.getDate();
            if (day < 10) day = "0" + day;
            var convertedDate = day+'-'+month+'-'+date.getFullYear();
            element.data = convertedDate;
            // Aggingo una formattazione al numero di giorni di scadenza
            element.scadenza += ` gg`;
            // Converto in 'si' o 'no' il booean 'pagata'
            element.pagata ? element.pagata = `si` : element.pagata = `no`;
            // Converto in 'cliente' o 'fornitore' l'apposito boolean
            element.eUnaFatturaCliente ? element.eUnaFatturaCliente = `cliente` : element.eUnaFatturaCliente = `fornitore`;
            // Estraggo dalla Persona il suo nome e cognome
            element.persona = element.persona.nome + ` ` + element.persona.cognome;
            // Estraggo dal conto il suo nome
            element.conto = element.conto.nome;
            // Calocolo il numero degli articoli
            element.numeroArticoli = element.articolo.length;
            // Ricavo l'IVA dal lordo e lo formatto
            element.iva *= element.lordo;
            element.iva = `€ ` + element.iva;
            // Formatto il lordo
            element.lordo = `€ ` + element.lordo;
            // Inserisco l'oggetto in una nuova righa della tabella
            $('#tblFatture').append(`
                <tr data-id="${element.id}" class="line" data-toggle="modal" data-target="#modal">
                    <td>${element.numeroFattura}</td>
                    <td>${element.data}</td>
                    <td>${element.scadenza}</td>
                    <td>${element.pagata}</td>
                    <td>${element.eUnaFatturaCliente}</td>
                    <td>${element.persona}</td>
                    <td>${element.conto}</td>
                    <td>${element.numeroArticoli}</td>
                    <td>${element.lordo}</td>
                    <td>${element.iva}</td>
                    <td>${element.nota}</td>
                </tr>
            `);

        });
        $( "#tblFatture" ).on( "click", ".line", function() {
            var idFattura = $(this).data('id');
            console.log(idFattura);
            $(`#modalTitle`).text(idFattura);
        });
    })
    .fail(() => {
        console.log(`fail`);
    })

    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
      })

    function MyPopUpWin(idFattura) {
        var myHeight = window.screen.height / 2;
        var myWidth = window.screen.width / 2;
        //Open the window.
        var win2 = window.open("/","Archivio - Fat-" + idFattura,"status=no,height=" + myHeight + ",width=" + myWidth + ",resizable=yes,screenX=" + (myWidth/2) + ",screenY=" + (myHeight/2) + ",toolbar=no,menubar=no,scrollbars=no,location=no,directories=no");
        win2.focus();
        }

});