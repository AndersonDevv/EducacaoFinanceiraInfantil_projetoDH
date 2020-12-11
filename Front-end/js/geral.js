// ---------------------VOLTAR PARA INICIO DA PAGINA -----------------------------------
function irParaInicioPagina() {
    window.scrollTo({top: 0, behavior: 'smooth'});
    //window.scrollTo(0, 0);
   
   }
   
   // --------------------------------MODAL PARA LOGAR NA PAGINA-------------------------------------------
   
   function chamar_modal(){
       var modal = document.getElementById("home_modal_conteiner");
       modal.style.display = 'flex';
   
       var modal_recuperar_senha = document.getElementById("home_modal_esqueci_senha");
       modal_recuperar_senha.style.display='none';
   };
    
   function fechar_modal(){
    var modal = document.getElementById("home_modal_conteiner");
    modal.style.display = 'none';
   };
    
   // --- Logando na página - ativado no click do botao entrar ------
   var usuario_status_logado = false;
   var usuario = {
   accessToken: "",
   email:"",
   id: "",
   roles:""
   }
   var dados_login={};
   var nome_usuario_logado ="";
   
   let botao_logar = document.getElementById("btn_submit");
   botao_logar.addEventListener('click', function(e){
      e.preventDefault();
   
   //- VERIFICANDO SE O LOGIN É REALIZADO COMO RESPONSAVEL OU COMO DEPENDENTE - LEITURA DO CHECKBOX -------
       var logUsuarioResponsavel = document.getElementById("responsavel_radio");
       var logUsuarioDependente = document.getElementById("crianca_radio");
       if(logUsuarioDependente.checked==true){
           console.log("selecinou criança ");
           
       }
       else{
           console.log("selecinou adul");
       }
   
      dados_login.email = document.getElementById("email").value;
      dados_login.usu_senha = document.getElementById("password").value;
      
      fetch('http://localhost:8085/api/v1/login', {
        method: 'POST',
        mode: 'cors',
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        'body': JSON.stringify(dados_login)
    })
        .then((response) => response.json())
        .then((response) => {    
            if(response.id > 0){
            var usuario_status_logado = true;
                logar_usuario(response.usu_nome,usuario_status_logado); 
                window.localStorage.setItem("dadosUsuarioLogado",JSON.stringify(response));
                window.localStorage.setItem("status_usuario",usuario_status_logado);
            }
        })
        .catch((error) => {
            console.log("Erro ao tentar logar!");
        }); 
   });
   /* -----------------------------CASO A SENHA E EMAIL ESTEJAM CERTOS --------------------------------- */ 
   
   function logar_usuario(nome_usuario_logado, usuarioLogou){
    var nomeUsu = nome_usuario_logado.split(" "); //cria um vetor com o nome do usuario. A posicao [0] vai ficar com o 1º nome:  Fulano de Tal -> NOME.[0] = fulano
    if(usuarioLogou == true || usuarioLogou=="true"|| usuarioLogou==="true"){
      
       let esconder_abrir_entrar = document.querySelectorAll("#esconder_abrir_entrar"); // esconde o botao Abrir e Entrar do navbar
               for(let i =0; i<esconder_abrir_entrar.length;i++){
                   esconder_abrir_entrar[i].style.display='none';
               }
       let esconder_modal_logar = document.getElementById("modal_logar"); //fecha o modal de log de usuario
       esconder_modal_logar.style.display='none';
   
       let modal = document.getElementById("home_modal_conteiner"); //fecha a tela escura do  modal
       modal.style.display = 'none';
   
       let aparecer_usuario = document.querySelectorAll("#esconder_usuario_logado"); //faz aparecer o nome do usuario e a seta no lugar dos 2 botoes que sumiram 
       for(let i =0; i<aparecer_usuario.length;i++){
           aparecer_usuario[i].style.display='flex';
       }
               
       let aparecer_nome_do_usuario = document.getElementById("mostrar_nome_usuario");
       aparecer_nome_do_usuario.innerHTML="Olá, " +nomeUsu[0]; //o nome do usuario retornado pelo banco é aplicado aqui e aparece no front.
   }
   
   }
   
   /*------------------------------------------ DESLOGANDO DA PÁGINA ------------------------------------------*/
   
   let status_modal_logado = false;
   function chamar_modal_usuario_logado(){
      
        let abrindo_modal_logado = document.getElementById("modal_usuario_logado");
        if(status_modal_logado == false){
        abrindo_modal_logado.style.display='flex';
        status_modal_logado = true;
        }
        else{
            abrindo_modal_logado.style.display='none';
            status_modal_logado = false;
        }
   }
   // -------------------------------MODAL ESQUECI SENHA  ---------------------------------
   
    let btn_esqueci_senha = document.getElementById("esquecerSenha");
   
    btn_esqueci_senha.addEventListener('click', function(e){
        let esconderLogin = document.getElementById("modal_logar");
        esconderLogin.style.display ='none';
   
        let mostrarRecuperarSenha = document.getElementById("home_modal_esqueci_senha");
        mostrarRecuperarSenha.style.display='block';
   
        let botaoFecharRecuperarSenha = document.getElementById("fechar_recuperar_senha");
        botaoFecharRecuperarSenha.addEventListener('click', function(){
            esconderLogin.style.display = 'block';  //quando fechar a recuperação de
        })
    });
   
// -------SEMPRE QUE A PAGINA CARREGAR ELA VERIFICARÁ SE HÁ DADOS SALVOS NO LOCAL STORAGE-------------------
   // function receberLocalStorage(){
        var dadosDeLog = JSON.parse(window.localStorage.getItem("dadosUsuarioLogado"));
        var statusUsuarioLogado = window.localStorage.getItem("status_usuario")
        logar_usuario(dadosDeLog.usu_nome,statusUsuarioLogado);
  //  }
// ------------------ ao clicar em SAIR --------------------------------------
    btn_sair = document.getElementById("deslogar");
    btn_sair.addEventListener('click',function(){
       // window.localStorage.clear();
       localStorage.removeItem("dadosUsuarioLogado"); //apaga o response proveniente do fetch
       localStorage.removeItem("status_usuario"); //remove o status de usuario logado = true
    })
   
    