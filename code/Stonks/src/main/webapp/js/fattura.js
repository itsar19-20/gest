// aspetto che la pagina venga caricata
$(() => {

    var ciao = `buongiorno`;

    //  invia al controller l'utente loggato
    var user = JSON.parse(localStorage.getItem('user'));
    //  impostare automaticamente la data di oggi
    var date = new Date();
    var month = date.getMonth()+1;
    if (month < 10) month = "0" + month;
    var day = date.getDate();
    if (day < 10) day = "0" + day;
    var today = date.getFullYear()+'-'+month+'-'+day;
    $('#input-data').val(today);
    //  richiesta della lista dei conti associati
    var whatIWant = 'conti';
    user = user.id;
    $.ajax({
        url: '/fattura/crea',
        method: 'post',
        data: {user, whatIWant}
    })
    //  il controller risponde con i conti collegati all'utente loggato
    .done((lista) => {
        var select = document.getElementById("input-conto");
        lista.forEach(element => {
        localStorage.setItem(`conto-${element.id}`, JSON.stringify(element));
            var option = document.createElement("option");
            option.value = `${element.id}`;
            option.text = `${element.nome}`;
            select.add(option);
        });
            
        //  richiesta della lista delle persone associate
        whatIWant = 'persone';
        $.ajax({
            url: '/fattura/crea',
            method: 'post',
            data: {user, whatIWant}
        })
        //  il controller risponde con le persone collegate all'utente loggato
        .done((lista) => {
            var select = document.getElementById("input-persona");
            lista.forEach(element => {
                localStorage.setItem(`persona-${element.id}`, JSON.stringify(element));
                var option = document.createElement("option");
                option.value = `${element.id}`;
                option.text = `${element.nome} ${element.cognome}`;
                select.add(option);
            });

            // richiedo al controller l'id minimo e massimo delle entita Conto e Persona
            // collegate all' utente loggato, per poterle poi eliminare dal localStorage
            whatIWant = `minMax`;
            $.ajax({
                url: `/fattura/crea`,
                method: 'post',
                data: {user, whatIWant}
            })
            .done((fromControler) => { localStorage.setItem(`minMax`, JSON.stringify(fromControler)); })
            .fail(() => { console.log(`problema nel caricamento dell'indice minMax`); });
        }) .fail(() => { console.log('problema nel caricamento delle persone') });
    }) .fail(() => { console.log('problema nel caricamento dei conti') });

    // Add the first
    var numeroArticoli = 1;
    localStorage.setItem(`numeroArticoli`, numeroArticoli);
    $.ajax({
        url: '/parts/articolo.html',
        method: 'get'
    })
    .done((html) => {
        $('#articoli').append(html
            .replace('onclick="§onclick§"', '')
            .replace(`§btn§`, `+`)
        );
    // add another article
    $('#btn-article-1').click(() => {
        numeroArticoli++;
        localStorage.setItem(`numeroArticoli`, numeroArticoli);
        $.ajax({
            url: '/parts/articolo.html',
            method: 'get'
        })
        .done((html) => {
            //  the next line allows you to remove the newly added article
            var rimuoviQuestoDiv = '$(\'#articolo-list-item-' + numeroArticoli + '\').remove();';
            var labelDescrizione = ` <label for="input-articolo-descrizione-1" class="input-group-text">Descrizione</label>`;
            var labelQuantita = `<label for="input-articolo-quantita-1" class="input-group-text">Quantità</label>`;
            var labelPrezzo = `<label for="input-articolo-prezzo-1" class="input-group-text">Prezzo</label>`;
            var labelButton = `<label for="btn-article-1">&nbsp;</label>`;
            var labelParziale = `<label for="parziale1" class="input-group-text">Parziale</label>`;
            var str = `input-articolo-`;
            $('#articoli').append(html
                .replace('articolo-list-item-1', 'articolo-list-item-' + numeroArticoli)
                .replace(labelDescrizione, '')
                .replace(str + 'descrizione-1', str + `descrizione-` + numeroArticoli)
                .replace(labelQuantita, '')
                .replace(str + `quantita-1`, str + `quantita-` + numeroArticoli)
                .replace(labelPrezzo, '')
                .replace(str + `prezzo-1`, str + `prezzo-` + numeroArticoli)
                .replace(labelButton, '')
                .replace(`btn-article-1`, `btn-article-` + numeroArticoli)
                .replace(`btn-success`, `btn-danger`)
                .replace(`§btn§`, `&nbsp;x&nbsp;`)
                .replace('§onclick§', rimuoviQuestoDiv)
                .replace(labelParziale, ``)
                .replace(`parziale1`, `parziale` + numeroArticoli)
            );
        });
    });
});

    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // submit button - salva fattura
    $('#btn-submit').click(() => {
        var lordo = $('#totale').text() + `F`;
        // creo l'oggetto fattura
        var fattura = {
            eUnaFatturaCliente: $('#input-tipo-fattura').val(),
            conto: JSON.parse(localStorage.getItem(`conto-` + $('#input-conto').val())),
            persona: JSON.parse(localStorage.getItem(`persona-` + $('#input-persona').val())),
            data: $('#input-data').val(),
            scadenza: $('#input-scadenza').val(),
            lordo: lordo,
            nota: $('#input-note').val(),           
        };
        // serializzo l'oggetto
        fattura = JSON.stringify({ 'fattura': fattura });
        // prendo ogni articolo singolarmente
        // serializzo manualmente un oggetto di oggietti in una stringa in formato json
        var numArticoliInseriti = $('.articolo').length
        var count = 1;
        var numElementiEsistentiGiaToccati = 0;
        var articoli = `{"articoli":[`;
        while (true) {
            var element = !!document.getElementById('articolo-list-item-' + count);
            if (element) {
                if (count > 1) articoli += `,`;
                var ia = `#input-articolo-`;
                var desc = $(ia + 'descrizione-' + count).val();
                var quan = $(ia + 'quantita-' + count).val();
                if (quan.charAt(0) == `.`) quan = '0' + quan;
                var prez = $(ia + 'prezzo-' + count).val();
                if (prez.charAt(0) == `.`) prez = '0' + prez;
                var parz = parseFloat($(`#parziale` + count).text());
                articoli += `{"descrizione":"` + desc + `","quantita":` + quan + `,"prezzo":`  + prez + `,"parziale":` + parz + `}`;
                numElementiEsistentiGiaToccati++;
            }
            if (numElementiEsistentiGiaToccati == numArticoliInseriti) {
                articoli += `]}`;
                break;
            };
            count++;
        }
        // invio al controller l'oggetto Fattura e l'oggetto di oggetti Articolo
        $.ajax({
            url: '/fattura/salva',
            method: 'post',
            //  mapping the datas for the servlet controller
            data: { fattura, articoli }
            /*
            success: function () {
                //  code
            }
            failure: function () {
                //  code
            }
            */
        })
        .done(() => {
            removeContiAndPersoneFromLocalStorage();
            location.href = `/`;
        })
        .fail(() => { console.log('fattura non salvata'); });
    });

    function removeContiAndPersoneFromLocalStorage() {
        // costruire un meccanismo che esegue una query che restituisce il numero massimo e minimo degli id
        // di conti e persone collegati all'utente loggato, così da impostare min e max del ciclo for
        var minMax = JSON.parse(localStorage.getItem(`minMax`));
        for (let i = minMax.min; i <= minMax.max; i++) {
            localStorage.removeItem(`conto-` + i);
            localStorage.removeItem(`persona-` + i);
        }
        localStorage.removeItem(`minMax`);
        localStorage.removeItem(`numertoArticoli`);
    }

});