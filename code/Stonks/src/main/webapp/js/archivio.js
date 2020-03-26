$(() => {
    // Richiedo la lista di tutte la fatture dell'utente loggato
    var user = JSON.parse(localStorage.getItem('user'));
    user = user.id;
    $.ajax({
        url: `/archivio/getAllMineInvoices`,
        method: `get`,
        data: {user}
    })
    .done(function(listaFatture) {
        console.log(listaFatture);
        // Per ogni fattura ricevuta
        listaFatture.forEach(element => {
            // Converto la data in un formato umano
            var convertedDate = convertData(element.data);
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
            // Segnalo l'eventuale emissione di unna nota di credito
            element.notaDiCredito ? element.notaDiCredito = 'emessa' : element.notaDiCredito = 'no';
            // Inserisco l'oggetto in una nuova righa della tabella
            $('#tblFatture').append(`
                <tr data-id="${element.id}" class="line column" data-toggle="modal" data-target="#modal">
                    <td id="numeroFattura">${element.numeroFattura}</td>
                    <td id="ndr-${element.id}" class="column-small" data-toggle="tooltip" data-placement="bottom" title="Nota di credito">${element.notaDiCredito}</td>
                    <td class="column">${element.data}</td>
                    <td class="column">${element.scadenza}</td>
                    <td class="column">${element.pagata}</td>
                    <td class="column">${element.eUnaFatturaCliente}</td>
                    <td class="column">${element.persona}</td>
                    <td class="column">${element.conto}</td>
                    <td class="column">${element.numeroArticoli}</td>
                    <td class="column">${element.lordo}</td>
                    <!-- <td class="column">${element.iva}</td> -->
                    <!-- <td>${element.nota}</td> -->
                </tr>
            `);

        });
        // Al clic su una linea della tabella
        $( "#tblFatture" ).on( "click", ".line", function() {
            var id = $(this).data('id');
            $.ajax({
                cache: false,
                type: 'GET',
                timeout: 2000,
                url: '/archivio/SingleInvoice',
                data: { id },
                dataType: 'json',
                success: (data, textStatus, jqXHR) => { showInvoice(data); },
                error: (data, textStatus, jqXHR) => { console.log(data, textStatus, jqXHR); showProblem(); },
                complete: () => { console.log('End ajax request') },
            });
            function showInvoice(invoice) {
                $('.invoice-sheet').show();
                $('#btn-nota-credito').show();
                $('.problem-sheet').hide();

                invoice.notaDiCredito ? $('#btn-nota-credito').prop('disabled', true) : $('#btn-nota-credito').prop('disabled', false)

                $(`#modal-title`).text(invoice.numeroFattura);
                $(`#conto`).text(invoice.conto.nome);
                $(`#tipo-fattura`).text(invoice.eUnaFatturaCliente ? 'cliente' : 'fornitore');
                $(`#persona`).text(invoice.persona.nome + ' ' + invoice.persona.cognome);
                $(`#data`).text(convertData(invoice.data));
                $(`#scadenza`).text(invoice.scadenza + ' giorni');
                $('#tbl-articles').empty();
                invoice.articolo.forEach(element => {
                $('#tbl-articles').append(`<tr>
                    <td class="col-descrizione">${element.descrizione}</td>
                    <td>${element.quantita}</td>
                    <td>${'€ ' + element.prezzo}</td>
                    <td>${'€ ' + element.parziale}</td>
                </tr>`)
                });
                $(`#note`).text(invoice.nota);
                $(`#totale`).text('€ ' + invoice.lordo);
                $(`#iva`).text('€ ' + invoice.lordo * invoice.iva);
            }
            function showProblem() {
                $(`#modal-title`).text('Qualcosa non va...');
                $('.invoice-sheet').hide();
                $('#btn-nota-credito').hide();
                $('.problem-sheet').show();
            }
            $('#btn-nota-credito').click(() => {
                if (confirm('Stai per emettere una nota di credito,\nse decidi di continuare non sarà più possibile tornare indietro.\nVuoi procedere?')) {
                    $.ajax({
                        cache: false,
                        type: 'PUT',
                        timeout: 2000,
                        url: '/archivio/SingleInvoice',
                        data: { id },
                        dataType: '',
                        success: (data, textStatus, jqXHR) => {
                            alert('La nota di credito è stata emessa correttamente');
                            $('#btn-nota-credito').prop('disabled', true);
                            $('#ndr-' + id).text('emessa');
                        },
                        error: (data, textStatus, jqXHR) => { alert('Si è verififato un problema.\nRiprova.') },
                        complete: () => { console.log('End ajax request') },
                    });
                }
            });
        });
    }).fail(() => { console.log(`fail`); });


    /*
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
    });
    */

    function MyPopUpWin(idFattura) {
        var myHeight = window.screen.height / 2;
        var myWidth = window.screen.width / 2;
        //Open the window.
        var win2 = window.open("/","Archivio - Fat-" + idFattura,"status=no,height=" + myHeight + ",width=" + myWidth + ",resizable=yes,screenX=" + (myWidth/2) + ",screenY=" + (myHeight/2) + ",toolbar=no,menubar=no,scrollbars=no,location=no,directories=no");
        win2.focus();
        }

});

function convertData(element) {
    var date = new Date(element);
    var month = date.getMonth() + 1;
    if (month < 10)
        month = "0" + month;
    var day = date.getDate();
    if (day < 10)
        day = "0" + day;
    var convertedDate = date.getFullYear() + '/' + month + '/' + day;
    return convertedDate;
}
