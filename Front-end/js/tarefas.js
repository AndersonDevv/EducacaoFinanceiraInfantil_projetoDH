$(document).ready(function(){
    
    var token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW1hZUB5YWhvby5jb20uYnIiLCJpYXQiOjE2MDY2NDg3ODUsImV4cCI6MTYwNjY2NDE4NX0.I5tsT1SXunBQhQmHrK5vevmoklshWL5A84k1lQ-yj9ZVWESOHfUkhqkrkkAQTt7eM3BCdvV54q8oMZUt2THNag";

carregar_dependetes();

    function carregar_dependetes(){
    
        fetch('http://localhost:8085/api/v1/dependente/buscardependentesporusuarioid/1',{
           
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
   
    var tarefas;
    var li;
     var i,j;
     
     
     
     for(i = 0; i < response.length; i++){ 


tarefas=`<section id="background_dependente" class="d-flex justify-content-center align-items-center">
<h1 style="margin-top: -20px">${response[i].dep_nome}</h1>
</section>
<!--------------------------------------------------------------------------------------------------------->

<!-------------------------------------------Conteúdo Principal-------------------------------------------->
<main>
<div class="container-fluid mt-2">

    <!-------------------------------------------Status das Tarefas------------------------------------>
    <div class="row justify-content-center">
        <div class="">
            <div id="background_total_tarefas">
                <span class="numero_total">${response[i].tarefas.length}</span>
                <span class="soma_tarefas">Total Tarefas</span>
            </div>

        </div>
        <div class="">
            <div id="background_tarefas_canceladas">
                <span class="numero_total">0</span>
                <span class="soma_tarefas">Tarefas Canceladas</span>
            </div>

        </div>
        <div class="">
            <div id="background_tarefas_concluidas">
                <span class="numero_total">0</span>
                <span class="soma_tarefas">Tarefas Concluidas</span>
            </div>

        </div>
    </div>
    <!-------------------------------------------------------------------------------------------------->

    <!------------------------------------------Seção Cards com as Tarefas------------------------------>
    <div class="row justify-content-center">
        <div class="ul">
        <ul class="${response[i].dep_nome}"></ul>
                
                
            </ul>
        </div>
    </div>
    <!-------------------------------------------Fim Cards com as Tarefas------------------------------->

    <!-------------------------------------Seção Total de Pontos---------------------------------------->
    <section>
        <div class="text-center text-light pt-2 pb-2 pl-5 pr-5 " id="total_pontos">
            <div class="row d-flex justify-content-between align-items-center pt-2 ">
                <h1 class="texto ">Total De Pontos</h1>
                <h1 class="texto ">25</h1>
            </div>
        </div>
    </section>
    <!-------------------------------------------------------------------------------------------------->
</main>`

$(".container-tarefas").append(tarefas);


console.log("Dependente Nome: " + response[i].dep_nome);
for(j = 0; j < response[i].tarefas.length; j++){

   
   // console.log("Tarefas Nome: " + response[i].tarefas[j].tar_titulo );
    //console.log("Total Tarefas: " + response[i].tarefas.length );
    

     li=`<li>
                    <a>
                        <div class="d-flex justify-content-center">
                            <img class="icon_educacao" src="images/icon_educacao.png" alt="">
                            <h5 class="text-center card_titulo">${response[i].tarefas[j].tar_titulo}</h5>
                            <img class="icon_editar" src="images/icon_editar.png" alt="">
                        </div>

                        <h5 class="text-center card_categoria">Arrumar os Brinquedos</h5>
                        <h5 class="text-center card_descricao">Ao terminar de Brincar, organizar todos os brinquedos.</h5>

                        <div class="d-flex justify-content-center">
                            <span class="titulo_mes">Setembro</span>
                            <img class="img_mes" src="images/mancha_lilas.png" alt="">
                        </div>

                        <span class=" numberCircle clrMauve mt-1 "><span>DOM</span></span>
                        <span class="numberCircle clrGreen "><span>SEG</span></span>
                        <span class="numberCircle clrRose "><span>TER</span></span>
                        <span class="numberCircle clrOrange "><span>QUA</span></span>
                        <span class="numberCircle clrMauve "><span>QUI</span></span>
                        <span class="numberCircle clrRose "><span>SEX</span></span>
                        <span class="numberCircle clrOrange "><span>SÁB</span></span>

                        <div class="row justify-content-around pl-3 pr-3 ">
                            <span class="check "><i class="fa fa-check "></i></span>
                            <span class="check "><i class="fa fa-check "></i></span>
                            <span class="check "><i class="fa fa-check "></i></span>
                            <span class="check "><i class="fa fa-check "></i></span>
                            <span class="check "><i class="fa fa-check "></i></span>
                            <span class="check "><i class="fa fa-check "></i></span>
                            <span class="check "><i class="fa fa-check "></i></span>
                        </div>

                        <div class="d-flex justify-content-end mt-2 ">
                            <span>Pontos</span>
                            <span class="pontos ml-2 text-center ">25</span>
                        </div>
                    </a>
                </li>`
      let nome = "." + response[i].dep_nome;          
  $(nome).append(li);             
}


j=0;


    }


           
       })
       .catch((error) => {
           
           console.error('Error:', error);
          
       });
       
     }




  
    
});
    
    