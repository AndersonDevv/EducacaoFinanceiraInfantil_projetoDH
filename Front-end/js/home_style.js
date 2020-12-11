/*
var usuario_status_logado = false;
var dados_usuario = window.localStorage.getItem("login");
var usuario_logou = window.localStorage.getItem("usuario_ativo");
var usuarioObjeto = JSON.parse(usuario);
console.log(usuario, usuarioObjeto)
usuario_status_logado = usuario_logou;
*/
//------------------------------------------- Carrossel -------------------------------------------

var intervalo = 3000;
function slide1(){
    document.getElementById("banner").src="images/carrossel_garota1_alterado.jpg";
    setTimeout("slide2()",intervalo);
}
function slide2(){
    document.getElementById("banner").src="images/carrossel_menino_moeda_1.jpg";
    setTimeout("slide3()",intervalo);
}
function slide3(){
 document.getElementById("banner").src="images/carrossel_familia1_alterado.jpg";
 setTimeout("slide1()",intervalo);
}

// ---------------------- Clique nos cards para levar as demais páginas
function ir_para_tarefas(){
  window.location.href = 'tarefas.html';
 }

function ir_para_continhas(){
    window.location.href = 'continhas.html';
   }

function ir_para_investimentos(){
    window.location.href = 'investimentos.html';
   }

function ir_para_videos(){
    window.location.href = 'https://www.youtube.com/watch?v=DVMy9ZGF3FU';
}

function ir_para_leitura(){
    window.location.href = 'http://telvatanajura.blogspot.com/2009/11/sugestao-de-leitura-que-despertam.html';
}
function ir_para_jogos(){
    //window.location.href = 'http://telvatanajura.blogspot.com/2009/11/sugestao-de-leitura-que-despertam.html';
}

   