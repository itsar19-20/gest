/*let getXMLFile =function(path,callback){
    let request =new XMLHttpRequest();
    request.open("GET",path);
    request.setRequestHeader("Content-Type","text/xml");
    request.onreadystatechange=function(){
        if(request.readyState===4 && request.status===200){
            callback(request.responseXML);
        }
    };
    request.send();


};

getXMLFile("./fattura1.xml",function(xml){
    console.log(xml);
});
*/

$(document).ready( function () {

    $("#btnLoad").on("click",function(){
        var inputFile=document.getElementById("myXML");
        var files=inputFile.files;
        var file=files[0];
        //console.log(file);
        var stringaFile="vuoto";
        var fr=new FileReader();
        fr.onload=function(){
             stringaFile =this.result;
            // console.log("stringa:");
            //console.log(stringaFile);
            if(stringaFile.includes("<Allegati>")){
                var posAllegati=stringaFile.indexOf("<Allegati>");
                console.log(posAllegati);
                var posAllegati2=stringaFile.indexOf("</Allegati>");
                stringaFile=stringaFile.substring(0,posAllegati)+stringaFile.substring(posAllegati2 + 11);
                

            }
            stringaFile=stringaFile.replace(/[^\x00-\x7F]/g, "");
            console.log(stringaFile);
            
            var user = JSON.parse(localStorage.getItem('user')  );
            user = user.id;

            var conto=$("#textConto").val();
            

            
            $.ajax({
                url: './leggiXML',
                method: 'post',
                data: { 
                        fileXML: stringaFile,
                        idUser: user,
                        idConto: conto

                    }
             });

        }
        fr.readAsText(file);

        
        /*
        

*/
    });


});