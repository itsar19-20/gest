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
        });
        // DataTable
        $('#tblFatture').DataTable({
            data: listaFatture,
            columns: [
                {title: 'N° fattura', data: 'numeroFattura'},
                {title: 'Data', data: 'data'},
                {title: 'Scadenza', data: 'scadenza'},
                {title: 'Pagata', data: 'pagata'},
                {title: 'Tipo fattura', data: 'eUnaFatturaCliente'},
                {title: 'Persona', data: 'persona'},
                {title: 'Conto (Azienda)', data: 'conto'},
                {title: 'N° articoli', data: 'numeroArticoli'},
                {title: 'Lordo', data: 'lordo'},
                {title: 'di cui IVA', data: 'iva'},
                {title: 'Nota', data: 'nota'},
            ]
        });
        

    })
    .fail(() => {
        console.log(`fail`);
    })
});