$(document).ready(function(){

   
carregar_tabela();

$("#formUsuario").validate({
   rules:{
      inputNome: 'required'
   }
});
   

$("#novaCategoria").on('click', function(event){
   //$("#inputId").val('');
  // $("#inputNome").val('');
   //alert("teste");
});



$("#btnSubmit").on('click', function(event){
  
   if($("#formDependente").valid()){
      submeter_formulario();
   }
});

function submeter_formulario(){
   var id = $("#inputId").val();
   var nome = $("#inputNome").val();
  
  
  
  
   if(id == "" || id == null){  
      //var id = Math.floor((Math.random() * 100000) + 1);
   var id = $("inputId").val();//Math.floor((Math.random() * 100000) + 1); 
   
   
   
   //alert("teste");

   let cat = {
      "cat_codigo": null,
      "cat_nome" : nome
      }

   fetch('http://localhost:8085/api/v1/categoria/',{
      //fetch('http://localhost:8085/api/v1/categoria/5', {
      //method: 'PUT',
      method: 'POST',
      mode: 'cors',
      headers: { 
          'Accept': 'application/json',
          'Content-Type': 'application/json' ,
          'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW1hZUB5YWhvby5jb20uYnIiLCJpYXQiOjE2MDYzOTI5ODEsImV4cCI6MTYwNjM5ODM4MX0.dtluga4ITTAUhs7yXkI-DzGtcFeNo4OruKVWWxDKxtCR4T5OmPzX822tHgY9ZsN2T7EuYdazbS3iohuDxA4zGQ'       
      },
    body: JSON.stringify(cat),
  })
  .then((response) => response.json())
  .then((response) => {
    // console.log('Loguin Efetuado com sucesso' + response);
     // variavel = response;
console.log(response); 
           
    var tr = "<tr>";
    tr += "<td class='td_id hidden'>" +response.cat_codigo + "</td>";
    tr += "<td class='td_nome'>" + response.cat_nome + "</td>";
    tr += "<td>";
    tr += "<button class='btn btn-editar-registro'><img src='images/editar.png' width='25' height='25' data-toggle='modal' data-target='#modalFormulario'></button>";
    tr += "<button class='btn btn-excluir-registro'><img src='images/delete.png' width='25'></button>";      
    tr += "</td>";
    tr += "</tr>";
    
    
    $("#categorias tbody").append(tr);
     
      
  })
  .catch((error) => {
      
    console.error('Error:', error);
      // mensagem de erro de loguin ; senha incorreta
  });


   $("#modalFormulario").modal('hide');
   
   
   }else{//Editando um registro
   
      let cat = {
         "cat_codigo": null,
         "cat_nome" : nome
         }
   
      fetch('http://localhost:8085/api/v1/categoria/'+id,{
         //fetch('http://localhost:8085/api/v1/categoria/5', {
         //method: 'PUT',
         method: 'PUT',
         mode: 'cors',
         headers: { 
             'Accept': 'application/json',
             'Content-Type': 'application/json' ,
             'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW1hZUB5YWhvby5jb20uYnIiLCJpYXQiOjE2MDYzOTI5ODEsImV4cCI6MTYwNjM5ODM4MX0.dtluga4ITTAUhs7yXkI-DzGtcFeNo4OruKVWWxDKxtCR4T5OmPzX822tHgY9ZsN2T7EuYdazbS3iohuDxA4zGQ'       
         },
       body: JSON.stringify(cat),
     })
     .then((response) => response.json())
     .then((response) => {
       // console.log('Loguin Efetuado com sucesso' + response);
        // variavel = response;
       console.log(response); 
              
           
            var tr = "<tr>";
            tr += "<td class='td_id hidde'>" + response[0].cat_codigo + "</td>";
            tr += "<td class='td_nome'>" +response[0].cat_nome + "</td>";
            tr += "<td>";
            tr += "<button class='btn btn-editar-registro'><img src='images/editar.png' width='25' height='25' data-toggle='modal' data-target='#modalFormulario'></button>";
            tr += "<button class='btn btn-excluir-registro'><img src='images/delete.png' width='25'></button>";      
            tr += "</td>";
            tr += "</tr>";
   
            $("#categorias tbody").append(tr);
        
         
     })
     .catch((error) => {
         
         console.error('Error:', error);
         // mensagem de erro de loguin ; senha incorreta
     });
    
   var trs = $("#categorias").find('tr');
   var row;
   $(trs).each(function(){
   var tmpId = $(this).find('.td_id').html();
   if(tmpId == id){
   row = $(this);
   }
   
   });
   
  $(row).find('.td_nome').html(nome);
   
   }

   $("#modalFormulario").modal('hide');  
}

$(document).on('click', ".btn-editar-registro", function(event){
//console.log(event);
var button = $(this);
var row = $(button).closest('tr');

var id = $(row).find('.td_id').html();
var nome = $(row).find('.td_nome').html();
//console.log(id + nome);

$("#inputId").val(id);
$("#inputNome").val(nome);


});


$(document).on('click', ".btn-excluir-registro", function(event){
   var button = $(this);
   var row = $(button).closest('tr');   
   var id = $(row).find('.td_id').html();  
   var nome = $(row).find('.td_nome').html();  
  console.log(id);
 
$.confirm({
   title: "Atenção!",
   type:'red',
   icon:'',
   content: 'Você deseja excluir a categoria ' +"<b>" + nome + "</b>?",
   buttons:{
      remover:{
        btnClass: 'btn-danger',
         text:'Remover', action:function(event){
//console.log("Removeu");
fetch('http://localhost:8085/api/v1/categoria/'+id,{
         //fetch('http://localhost:8085/api/v1/categoria/5', {
         //method: 'PUT',
         method: 'DELETE',
         mode: 'cors',
         headers: { 
             'Accept': 'application/json',
             'Content-Type': 'application/json' ,
             'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW1hZUB5YWhvby5jb20uYnIiLCJpYXQiOjE2MDYzOTI5ODEsImV4cCI6MTYwNjM5ODM4MX0.dtluga4ITTAUhs7yXkI-DzGtcFeNo4OruKVWWxDKxtCR4T5OmPzX822tHgY9ZsN2T7EuYdazbS3iohuDxA4zGQ'       
         },
      // body: JSON.stringify(cat),
     })
     .then((response) => response.json())
     .then((response) => {
       // console.log('Loguin Efetuado com sucesso' + response);
        // variavel = response;
       console.log(response);    
        
         
     })
     .catch((error) => {
         
         console.error('Error:', error);
         // mensagem de erro de loguin ; senha incorreta
     });
$(row).fadeOut(1000);
         }
      },
      cancelar:{
         text:'Cancelar',
         action:function(event){

         }
      }
   }
})


});



function carregar_tabela(){


   fetch('http://localhost:8085/api/v1/categoria/',{
      //fetch('http://localhost:8085/api/v1/categoria/5', {
      //method: 'PUT',
      method: 'GET',
      mode: 'cors',
      headers: { 
          'Accept': 'application/json',
          'Content-Type': 'application/json' ,
          'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW1hZUB5YWhvby5jb20uYnIiLCJpYXQiOjE2MDYzNDQzMzMsImV4cCI6MTYwNjM0OTczM30.zUzFWOpXDMK-x0D2QEh88MbsxgYSxuOEr6IY5o1Cng1eh8CZ9lPOLEEEiydIaXeJY2hy0NVWy8NLzprbg9QFUQ'       
      },
    //body: JSON.stringify(cat),
  })
  .then((response) => response.json())
  .then((response) => {
    // console.log('Loguin Efetuado com sucesso' + response);
     // variavel = response;
    //  console.log(response); 
      for(let i = 0; i < response.length; i++){      
        
         var tr = "<tr>";
         tr += "<td class='td_id hidden'>" + response[i].cat_codigo + "</td>";
         tr += "<td class='td_nome'>" +response[i].cat_nome + "</td>";
         tr += "<td>";
         tr += "<button class='btn btn-editar-registro'><img src='images/editar.png' width='25' height='25' data-toggle='modal' data-target='#modalFormulario'></button>";
         tr += "<button class='btn btn-excluir-registro'><img src='images/delete.png' width='25'></button>";                    
         tr += "</td>";
         tr += "</tr>";

         $("#categorias tbody").append(tr);
         

     }
      
  })
  .catch((error) => {
      
      console.error('Error:', error);
      // mensagem de erro de loguin ; senha incorreta
  });
  
}

});

