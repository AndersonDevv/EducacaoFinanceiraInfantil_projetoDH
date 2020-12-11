$(document).ready(function(){

var token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW1hZUB5YWhvby5jb20uYnIiLCJpYXQiOjE2MDY2MDQxMDUsImV4cCI6MTYwNjYxOTUwNX0.d_7SiT40Kt2gcO-_PZdenAcSE9BVnkOtru71BmTsIjDvMhcLG-1XbL-Xl591LcxQGzN_OLUTP6PjZsOEvoNHIQ";

//data = "2020-05-23"
//console.log(FormataStringDataEuropeu(data));

    

    carregar_dependetes();

    $("#formDependente").validate({
        rules:{
           inputNome: 'required'
        }
     });

     $("#novoDependente").on('click', function(event){
        $("#inputIdDep").val('');
        $("#inputNomeDep").val('');       
      $("#inputDate").val('');
       $("#inputGenero").val('');
       $("#inputUsuario").val('');
      $("#inputSenha").val('');
      $("#inputFoto").val('');
    
     });
     
        

    $("#btnSubmitDep").on('click', function(event){
  
       if($("#formDependente").valid()){
           submeter_formularioDep();
           
     }
     });
    
    
    function submeter_formularioDep(){

    
       var id = $("#inputIdDep").val();     
       var nome = $("#inputNomeDep").val();
       var data = $("#inputDate").val();     
       var genero = $("#inputGenero").val();
       var usuario = $("#inputUsuario").val();
       var senha = $("#inputSenha").val();
       var foto = document.getElementById("inputFoto").files[0].name; //$("#inputFoto").val();
     
       

        if(id == "" || id == null){  
          
        var id = $("inputIdDep").val();//Math.floor((Math.random() * 100000) + 1); 
       
        console.log(data);

        let dep = {
           "dep_codigo": null,
           "dep_nome" : nome,
           "dep_num_seq" : 1,
           "dep_senha" : senha,
           "dep_url_foto" : foto,
           "dep_usuario" : usuario, 
           "dep_data_nascimento" : data,
           "dep_genero" : genero, 
           "dep_pontos": 0,
           "usuario_usu_id": 1
           }
     
        fetch('http://localhost:8085/api/v1/dependente/',{           
           method: 'POST',
           mode: 'cors',
           headers: { 
               'Accept': 'application/json',
               'Content-Type': 'application/json' ,
               'Authorization': token,  
           },
         body: JSON.stringify(dep),
       })
       .then((response) => response.json())
       .then((response) => {
         
         console.log(response);                
             
        var card_dependente =  `<div class="col-12 col-sm-12 col-md-6 col-lg-4 d-flex justify-content-center">
        <div class="card_dependentes cards_query mt-4">
             <div class="mostrar_tarefas">
                 <img class="img-flui card_footer_dependentes" src="images/footer_card.png" alt="">
                 <a href="#"><img id="icon_editar" src="images/editar.png" alt="" data-toggle="modal" data-target="#modalDependente"></a>
                 <a href="#"><img id="icon_deletar" src="images/delete.png" alt=""></a>
                 <img id="img_dependente" src="images/fotos_dependentes/${foto}" alt="">
                 <span class="lu_span" id="nome_dependente">${nome}</span>
                 <span class="lu_span" id="total_tarefas_dependente">0&nbsp;Tarefas</span>
                 <span class="lu_span" id="pontos_acomulados">Pontos Acomulados</span>
                 <span class="lu_span" id="total_pontos_acomulados">0</span>
                 <input type="text" id="td_id_card_dep" class="hidden" value="${response.dep_codigo}"></input>
             </div>
         </div>
     </div>`;


     $("#card-dependente").append(card_dependente);


        
        var tr = "<tr>";
        tr += "<td class='td_id_dep hidden'>" + id + "</td>";
        tr += "<td class='td_foto'><img src='images/fotos_dependentes/" + foto + "' width='50' height='50' style='border-radius:50%'></td>";
        tr += "<td class='td_nome_dep'>" + nome + "</td>";
        tr += "<td class='td_senha'>" + senha + "</td>";
        tr += "<td class='td_usuario'>" + usuario + "</td>";
        tr += "<td class='td_data'>" + FormataStringDataEuropeu(data) + "</td>";
        tr += "<td class='td_genero'>" + genero + "</td>";
        tr += "<td>";
        tr += "<button class='btn btn-editar-registro-dep'><img src='images/editar.png' width='25' height='25' data-toggle='modal' data-target='#modalDependente'></button>";
        tr += "<button class='btn btn-excluir-registro'><img src='images/delete.png' width='25'></button>";      
        tr += "</td>";
        tr += "</tr>";       
        
        
        $("#table-dependente tbody").append(tr);
          
           
       })
       .catch((error) => {
           
           console.error('Error:', error);
        
       });     
     
        $("#modalDependente").modal('hide');
        
        
        }else{//Editando um registro
        
            let dep = {
                "dep_codigo": null,
                "dep_nome" : nome,
                "dep_num_seq" : 1,
                "dep_senha" : senha,
                "dep_url_foto" : foto,
                "dep_usuario" : usuario, 
                "dep_data_nascimento" : data,
                "dep_genero" : genero, 
                "dep_pontos": 0,
                "usuario_usu_id": 1
                }
        
           fetch('http://localhost:8085/api/v1/dependente/'+id,{              
              method: 'PUT',
              mode: 'cors',
              headers: { 
                  'Accept': 'application/json',
                  'Content-Type': 'application/json' ,
                  'Authorization': token,  
              },
            body: JSON.stringify(dep),
          })
          .then((response) => response.json())
          .then((response) => {

            console.log(response);            
                   
                
          /*            
         var tr =`<tr><td class="td_id_dep hidden">${response[i].dep_codigo}</td><td scope="row"><img src="images/fotos_dependentes/${response[i].dep_url_foto}"  style="width:50px; height:50px; border-radius: 50%;" alt=""></td><td>${response[i].dep_nome}</td><td>${response[i].dep_senha}</td><td>${response[i].dep_usuario}</td><td>${response[i].dep_data_nascimento}</td><td>${response[i].dep_genero}</td><td><div class="d-flex"><button class="btn btn-editar-registro-dep"><img src="images/editar.png" width="25" height="25" data-toggle="modal" data-target="#modalDependente"></button><button type="button" class="btn btn-excluir-registro-dep"><img src="images/delete.png" width="25" height="25"></button></div></td></tr>`;
         */

        var tr =`<tr><td class="td_id_dep hidden">${response[i].dep_codigo}</td><td class="td_foto" scope="row"><img src="images/fotos_dependentes/${response[i].dep_url_foto}"  style="width:50px; height:50px; border-radius: 50%;" alt=""></td><td class="td_nome_dep">${response[i].dep_nome}</td><td class="td_senha">${response[i].dep_senha}</td><td class="td_usuario">${response[i].dep_usuario}</td><td class="td_data">${response[i].dep_data_nascimento}</td><td class="td_genero">${response[i].dep_genero}</td><td><div class="d-flex"><button class="btn btn-editar-registro-dep"><img src="images/editar.png" width="25" height="25" data-toggle="modal" data-target="#modalDependente"></button><button type="button" class="btn btn-excluir-registro-dep"><img src="images/delete.png" width="25" height="25"></button></div></td></tr>`;

         $("#table-dependente tbody").append(tr);

        var card_dependente =  `<div class="col-12 col-sm-12 col-md-6 col-lg-4 d-flex justify-content-center" id="dep+${response[i].dep_codigo}">
                   <div class="card_dependentes cards_query mt-4">
                        <div class="mostrar_tarefas">
                            <img class="img-flui card_footer_dependentes" src="images/footer_card.png" alt="">
                            <a href="#"><img id="icon_editar" src="images/editar.png" alt="" data-toggle="modal" data-target="#modalDependente"></a>
                            <a href="#"><img id="icon_deletar" src="images/delete.png" alt=""></a>
                            <img id="img_dependente" src="images/fotos_dependentes/${response[i].dep_url_foto}" alt="">
                            <span class="lu_span" id="nome_dependente">${response[i].dep_nome}</span>
                            <span class="lu_span" id="total_tarefas_dependente">${response[i].tarefas.length}&nbsp;Tarefas</span>
                            <span class="lu_span" id="pontos_acomulados">Pontos Acomulados</span>
                            <span class="lu_span" id="total_pontos_acomulados">${response[i].dep_pontos}</span>
                            <input type="text" id="td_id_card_dep" class="hidden" value="${id}"></input>
                        </div>
                    </div>
                </div>`;
           
    
                $("#card-dependente").append(card_dependente);
             
              
          })
          .catch((error) => {
              
              console.error('Error:', error);
             
          });
         
        var trs = $("#dependente").find('tr');
        var row;
        $(trs).each(function(){
        var tmpId = $(this).find('.td_id_dep').html();
        if(tmpId == id){
        row = $(this);
        }
        
        });
        
        $(row).find('.td_nome_dep').html(nome);
        
        }
     
        $("#modalDependente").modal('hide');  
     }


     $(document).on('click', ".btn-editar-registro-dep", function(event){
       
        //alert("editar")
        var button = $(this);
        var row = $(button).closest('tr');


        var id = $(row).find('.td_id_dep').html();
        var nome = $(row).find('.td_nome_dep').html();
        var data = $(row).find('.td_data').html();
        var genero = $(row).find('.td_genero').html();
        var usuario = $(row).find('.td_usuario').html();
        var senha = $(row).find('.td_senha').html();
        var foto = $(row).find('.td_foto').html();

        /*
        Primeiro pego a data no padrão europeu, formato a data para o padrão americano. 
        Assim posso inserir ela no campo de edição de data e no banco de dados
        */
       // data = FormataStringData(data);
       //Acita uma data string no padrão americano
       // var date = new Date(data);       
       
      //  console.log(genero);
        
        
        $("#inputIdDep").val(id);
        $("#inputNomeDep").val(nome);        
       // document.getElementById('inputDate').value = date.dateToInput();
        $("#inputGenero").val(genero);
        $("#inputUsuario").val(usuario);
        $("#inputSenha").val(senha);
      
       
        });

        //Utilizado para inserir a data no input type data
        Date.prototype.dateToInput = function(){
            return this.getFullYear() + '-' + ('0' + (this.getMonth() + 1)).substr(-2,2) + '-' + ('0' + this.getDate()).substr(-2,2);
        }

       
     
     
    function carregar_dependetes(){
    
       fetch('http://localhost:8085/api/v1/dependente/',{
          
          method: 'GET',
          mode: 'cors',
          headers: { 
              'Accept': 'application/json',
              'Content-Type': 'application/json' ,
              'Authorization': token,       
          },
        //body: JSON.stringify(cat),
      })
      .then((response) => response.json())
      .then((response) => {
       
       
        console.log(response); 

        
       
          for(let i = 0; i < response.length; i++){    
           
            
         var tr =`<tr><td class="td_id_dep hidden">${response[i].dep_codigo}</td><td class="td_foto" scope="row"><img src="images/fotos_dependentes/${response[i].dep_url_foto}"  style="width:50px; height:50px; border-radius: 50%;" alt=""></td><td class="td_nome_dep">${response[i].dep_nome}</td><td class="td_senha">${response[i].dep_senha}</td><td class="td_usuario">${response[i].dep_usuario}</td><td class="td_data">${response[i].dep_data_nascimento}</td><td class="td_genero">${response[i].dep_genero}</td><td><div class="d-flex"><button class="btn btn-editar-registro-dep"><img src="images/editar.png" width="25" height="25" data-toggle="modal" data-target="#modalDependente"></button><button type="button" class="btn btn-excluir-registro-dep"><img src="images/delete.png" width="25" height="25"></button></div></td></tr>`;

         $("#table-dependente tbody").append(tr);


        var card_dependente =  `<div class="col-12 col-sm-12 col-md-6 col-lg-4 d-flex justify-content-center">
                   <div class="card_dependentes cards_query mt-4">
                        <div class="mostrar_tarefas">
                            <img class="img-flui card_footer_dependentes" src="images/footer_card.png" alt="">
                            <a href="#"><img id="icon_editar" src="images/editar.png" alt="" data-toggle="modal" data-target="#modalDependente"></a>
                            <a href="#"><img id="icon_deletar" src="images/delete.png" alt=""></a>
                            <img id="img_dependente" src="images/fotos_dependentes/${response[i].dep_url_foto}" alt="">
                            <span class="lu_span" id="nome_dependente">${response[i].dep_nome}</span>
                            <span class="lu_span" id="total_tarefas_dependente">${response[i].tarefas.length}&nbsp;Tarefas</span>
                            <span class="lu_span" id="pontos_acomulados">Pontos Acomulados</span>
                            <span class="lu_span" id="total_pontos_acomulados">${response[i].dep_pontos}</span>
                            <input type="text" class="td_id_card_dep hidden" value="${response[i].dep_codigo}"></input>
                        </div>
                    </div>
                </div>`;          
         
         

        $("#card-dependente").append(card_dependente);
       
             
    
         }
          
      })
      .catch((error) => {
          
          console.error('Error:', error);
         
      });
      
    }



    $(document).on('click', ".btn-excluir-registro-dep", function(event){
        var button = $(this);
        var row = $(button).closest('tr');   
        var id = $(row).find('.td_id_dep').html();  
        var nome = $(row).find('.td_nome_dep').html();  

       console.log(id);
   
     
     $.confirm({
        title: "Atenção!",
        type:'red',
        icon:'',
        content: 'Você deseja excluir o dependente ' +"<b>" + nome + "</b>?",
        buttons:{
           remover:{
             btnClass: 'btn-danger',
              text:'Remover', action:function(event){
    
     fetch('http://localhost:8085/api/v1/dependente/'+id,{              
              method: 'DELETE',
              mode: 'cors',
              headers: { 
                  'Accept': 'application/json',
                  'Content-Type': 'application/json' ,
                  'Authorization': token,   
              },
         
          })
          .then((response) => response.json())
          .then((response) => {
            
            console.log(response);    
         
              
          })
          .catch((error) => {
              
              console.error('Error:', error);
             
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



     function dateToEN(date)
     {	
         return date.split('-').reverse().join('-');
     }

 //Formata a data no padrão americano
 function FormataStringData(data) {
    var dia  = data.split("-")[0];
    var mes  = data.split("-")[1];
    var ano  = data.split("-")[4];
  
    return ano + '-' + ("0"+mes).slice(-2) + '-' + ("0"+dia).slice(-2);
    // Utilizo o .slice(-2) para garantir o formato com 2 digitos.
  }

   //Formata a data no padrão americano
   function FormataStringDataEuropeu(data) {
      
   
    var ano  = data.split("-")[0];
   //// console.log(data.split("-")[0]);
    var mes  = data.split("-")[1];
    //console.log(data.split("-")[1]);
    var dia  = data.split("-")[2];
    //console.log(data.split("-")[2]);
  
   // return ano + '-' + ("0"+mes).slice(-2) + '-' + ("0"+dia).slice(-2);
    return ("0"+dia).slice(-2) + '-' + ("0"+mes).slice(-2) + '-' + ano;
    // Utilizo o .slice(-2) para garantir o formato com 2 digitos.
  }
    
    });
    
    