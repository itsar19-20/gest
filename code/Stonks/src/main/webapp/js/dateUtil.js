$(document).ready( function () {

    
    var currentDate=new Date();
    var meseValue=currentDate.getMonth();
    var giornoMese=currentDate.getDate();
    var giornoSett=currentDate.getDay();
    var annoCorrente=currentDate.getFullYear();

    

    annoStringa="/";



    
    listMesi=["GEN","FEB","MAR","APR","MAG","GIU","LUG","AGO",
    "SET","OTT","NOV","DIC"];

    fullMesi=["Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno","Luglio","Agosto",
    "Settembre","Ottobre","Novembre","Dicembre"];
    
    orderedMesi=[];
   
    
    if(meseValue!=0){

    for(var j=meseValue;j<12;j++){
        orderedMesi.push(listMesi[j]);
        
       
    }
    
    for(var z=0;z<meseValue;z++){
        orderedMesi.push(listMesi[z]);
        
        
        

    }
    

}else{
    orderedMesi=listMesi;
    annoStringa=annoStringa+annoCorrente.toString();
}
console.log(orderedMesi);

    for(var i=0;i<12;i++){
        if(i>11-meseValue){
            annoStringa=" "+(annoCorrente +1).toString();
        }
        else
        {
            annoStringa=" "+annoCorrente.toString();
            
        }
        document.getElementsByName('numMesi')[0].options[i].innerHTML = (orderedMesi[i].toString()+annoStringa);
        
    }

    contMese=meseValue;

    if(giornoSett==0){
        giornoSett=6;
    }else{
        giornoSett-=1;
    }
    console.log(giornoSett);

    currentDate.setDate(giornoMese-giornoSett);
    
    settimanaStorage=[];
    for(var i=0;i<11;i++){
        numLunedi=currentDate.getDate();
        currentDate.setDate(numLunedi+6);
        numDomenica=currentDate.getDate();

        var stringaLunedi="";
        var stringaDomenica="";
        var stringaStorage="";


        if(numLunedi<10){
            stringaLunedi=numLunedi.toString()+"&#160";
                
        }else{
            stringaLunedi=numLunedi.toString();
        }
        if(numDomenica<10){
            stringaDomenica=numDomenica.toString()+"&#160";
        }else{
            stringaDomenica=numDomenica.toString();
        }
        if(numLunedi<numDomenica){
            console.log("stesso mese");
            
            stringaSett=stringaLunedi+"-"+stringaDomenica+"&#160"+
            listMesi[contMese].toString();

            stringaStorage=stringaLunedi+"-"+stringaDomenica+" "+
            fullMesi[contMese].toString();

        }else{
            console.log("altro mese");
            contMese+=1;
            stringaSett=stringaLunedi+"&#160"+listMesi[contMese-1].toString()+" -"+
            stringaDomenica+"&#160"+listMesi[contMese].toString();

            stringaStorage=stringaLunedi+" "+fullMesi[contMese-1].toString()+" -"+
            stringaDomenica+" "+fullMesi[contMese].toString();
    
            
    }
    currentDate.setDate(numDomenica+1);
    settimanaStorage.push(stringaStorage);
    document.getElementsByName('numSettimane')[0].options[i].innerHTML=stringaSett;
    console.log(stringaSett);
}

localStorage.setItem('settimanaStorage',JSON.stringify(settimanaStorage));




});