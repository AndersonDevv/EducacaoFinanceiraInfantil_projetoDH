
let teste;

document.addEventListener('DOMContentLoaded',(e)=>{
   
    teste = 1;
    
    })



function mostraConta(){

    let valor = 0;
    
    let dataIni = document.getElementById('Tat_data1').value;
    let dataFim = document.getElementById('Tat_data2').value;
    
    console.log(teste);
    console.log(dataIni);
    console.log(dataFim);

    const options = {
        method: 'GET',
        mode: 'cors',
        cache: 'default'
    }
    
   
    fetch(`http://localhost:8085/api/v1/extrato_conta_corrente/data_intervalo_conta/?data1=`+ dataIni + `&data2=` + dataFim + `&conta_corrente_cc_num_conta=` + teste, options)
    .then(response => {
        return response.json()
        
        })
        .then (jsonBody =>{
            let table = document.getElementById('tableExtrato')

            while (table.rows.length > 0)
                { table.deleteRow(0); }


            for (let i = 0; i < jsonBody.length; i++) {
                console.log(jsonBody[i].ext_data);
                console.log(jsonBody[i].ext_tipo);
                console.log(jsonBody[i].ext_descricao);
                console.log(jsonBody[i].ext_valor);
                valor = parseFloat(jsonBody[i].ext_valor).toFixed(2); 
                               
                if (jsonBody[i].ext_tipo == "D"){
                   
                    console.log("débito");
                    let row = `<tr id="registroVermelho">
                            
                            <td>${jsonBody[i].ext_data}</td>
                            <td>${jsonBody[i].ext_tipo}</td>
                            <td>R$${valor}</td>
                            <td>${jsonBody[i].ext_descricao}</td>
                            <td><img id="Tat_porquinho" src="images/emoticon2.png" ></td>
                            
                        </tr>`
                table.innerHTML += row
                    
                    
                } else{
                    console.log("crédito");
                    let row = `<tr id="registroVerde">
                            
                            <td>${jsonBody[i].ext_data}</td>
                            <td>${jsonBody[i].ext_tipo}</td>
                            <td>R$${valor}</td>
                            <td>${jsonBody[i].ext_descricao}</td>
                            <td><img id="Tat_porquinho" src="images/emoticon.png" ></td>
                        </tr>`
                table.innerHTML += row
                    
                }

                

                
                
                
            }
        });
        
    
}



document.addEventListener('DOMContentLoaded',(e)=>{
    let valor = 0;
    const options = {
        method: 'GET',
        mode: 'cors',
        cache: 'default'
    }
    
   
    fetch(`http://localhost:8085/api/v1/conta_corrente/` + teste, options)
    .then(r => r.json())
    .then(jsonBody => {
        document.querySelector('#cc_num_agencia').value = jsonBody.cc_num_agencia;
        document.querySelector('#cc_num_conta').value = jsonBody.cc_num_conta;
        valor = jsonBody.cc_saldo;
        document.querySelector('#cc_saldo').value = parseFloat(valor).toFixed(2);
        console.log(jsonBody);
        
        
    });
    //.catch(e => console.log('Deu erro:' + e,message))

    
})

document.addEventListener('DOMContentLoaded',(e)=>{
   let valor = 0;
    const options = {
        method: 'GET',
        mode: 'cors',
        cache: 'default'
    }

   
    fetch(`http://localhost:8085/api/v1/conta_poupanca/` + teste, options)
    .then(r => r.json())
    .then(jsonBody => {
        document.querySelector('#cp_num_agencia').value = jsonBody.cp_num_agencia;
        document.querySelector('#cp_num_conta').value = jsonBody.cp_num_conta;
        valor = jsonBody.cp_saldo;
        document.querySelector('#cp_saldo').value = parseFloat(valor).toFixed(2);
        console.log(jsonBody);
        
        
    });
})


const showDataDependente = (result)=>{
    for(const campo in result){
        if(document.querySelector("#"+campo)){
            document.querySelector("#"+campo).value = result[campo]            
        }

    }
}
document.addEventListener('DOMContentLoaded',(e)=>{
   
    const options = {
        method: 'GET',
       
        cache: 'default'
    }

   
    fetch(`http://localhost:8085/api/v1/dependente/` + teste, options)
    .then(response =>{response.json()
        .then(data => showDataDependente(data))
        })
    .catch(e => console.log('Deu erro:' + e,message))

    
})


let extrato_conta_corrente = {      
        
    contaCorrente_cc_num_conta: teste,
    ext_data: "",
    ext_descricao: "",
    ext_tipo: "",
    ext_valor: 0
    
   }


let conta_corrente = {      
        
    cc_num_conta: 1,
    cc_num_agencia: 123,
    cc_saldo: 300.0,
    cc_ativa: "1",
    cc_tarifa: 1.2
        
}
function enviarDeposito(){

    let valor = document.getElementById('valor_deposito').value;
    let descricao = document.getElementById('descricao_deposito').value;
    let saldo = document.getElementById('cc_saldo').value;
    console.log(descricao);
    console.log(valor);


    extrato_conta_corrente.contaCorrente_cc_num_conta = teste;

   
    data = new Date()
    let parseDataISO = Date.parse (data);
   
    extrato_conta_corrente.ext_data = parseDataISO;
    
    extrato_conta_corrente.ext_descricao = descricao;
    extrato_conta_corrente.ext_tipo = "C";
    extrato_conta_corrente.ext_valor = valor;


    conta_corrente.cc_num_conta= teste,
    conta_corrente.cc_num_agencia = 123,
    conta_corrente.cc_ativa = "1",
    conta_corrente.cc_tarifa = 1.2
    conta_corrente.cc_saldo = Number(saldo) + Number(valor)

    document.getElementById('cc_saldo').value = conta_corrente.cc_saldo;

    console.log(conta_corrente.cc_saldo);

        fetch('http://localhost:8085/api/v1/extrato_conta_corrente', {
        method: 'POST',
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify(extrato_conta_corrente),
    })
    .then((response) => response.json())
    .then((extrato_conta_corrente) => {
        console.log('Success' + extrato_conta_corrente);
    })
    .catch((error) => {
        console.error('Error:', error);
    });

    fetch('http://localhost:8085/api/v1/conta_corrente/'+ teste, {
        method: 'PUT',
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify(conta_corrente),
    })
    .then((response) => response.json())
    .then((conta_corrente) => {
        console.log('Success' + conta_corrente);
    })
    .catch((error) => {
        console.error('Error:', error);
    });

    let div = document.getElementById("Tat_operacao1");
    div.style.display = 'none';
}


function enviarSaque(){

    let valor = document.getElementById('valor_saque').value;
    let descricao = document.getElementById('descricao_saque').value;
    let saldo = document.getElementById('cc_saldo').value;
    console.log(descricao);
    console.log(valor);

    data = new Date()
    let parseDataISO = Date.parse (data);
   
    extrato_conta_corrente.contaCorrente_cc_num_conta = teste;
    extrato_conta_corrente.ext_data = parseDataISO;
    extrato_conta_corrente.ext_descricao = descricao;
    extrato_conta_corrente.ext_tipo = "D";
    extrato_conta_corrente.ext_valor = valor;


    conta_corrente.cc_num_conta= 1,
    conta_corrente.cc_num_agencia = 123,
    conta_corrente.cc_ativa = "1",
    conta_corrente.cc_tarifa = 1.2
    conta_corrente.cc_saldo = Number(saldo) - Number(valor);

    document.getElementById('cc_saldo').value = conta_corrente.cc_saldo;

    console.log(conta_corrente.cc_saldo);

    fetch('http://localhost:8085/api/v1/extrato_conta_corrente', {
        method: 'POST',
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify(extrato_conta_corrente),
    })
    .then((response) => response.json())
    .then((extrato_conta_corrente) => {
        console.log('Success' + extrato_conta_corrente);
    })
    .catch((error) => {
        console.error('Error:', error);
    });

    fetch('http://localhost:8085/api/v1/conta_corrente/' + teste, {
        method: 'PUT',
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify(conta_corrente),
    })
    .then((response) => response.json())
    .then((conta_corrente) => {
        console.log('Success' + conta_corrente);
    })
    .catch((error) => {
        console.error('Error:', error);
    });

    let div = document.getElementById("Tat_operacao3");
    div.style.display = 'none';
}

function enviarTransferencia(){
    if(document.getElementById('cc_p').checked) {
       console.log("conta corrente para poupança")

        


      }else if(document.getElementById('p_cc').checked) {
        console.log("poupança para conta corrente")
      }
}

function cancelarDeposito(){
    let div = document.getElementById("Tat_operacao1");
    div.style.display = 'none';
}

function cancelarSaque(){
    let div = document.getElementById("Tat_operacao3");
    div.style.display = 'none';
}




function mostraData(){

    const meses = ["Janeiro","Fevereiro", "Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"];
    let dataIni = document.getElementById('Tat_data1').value;
    let dataFinal = document.getElementById('Tat_data2').value;

   
    data = new Date(dataIni);
    dataFormatada = data.toLocaleDateString('pt-BR', {timeZone: 'UTC'});
    let mes = data.getMonth();
    let ano = data.getFullYear();
    //document.getElementById('Tat_dataEscolhida').value = meses[mes] + " - " + ano;


    data2 = new Date(dataFinal);
    //dataFormatada = data2.toLocaleDateString('pt-BR', {timeZone: 'UTC'});
    let mes2 = data2.getMonth();
    let ano2 = data2.getFullYear();
    //document.getElementById('Tat_dataEscolhida2').value = meses[mes2] + " - " + ano2;
}

function escolheCarinha(){
    let meuValor = document.getElementById("Tat_valorMovimentacao").value;
    if (meuValor <0){
        document.getElementById("Tat_valor").style.color = yellow;
    } else{
        document.getElementById("Tat_icone").src = "Images/emoticon.png";
    }
    
}

function mostraAtivacaoCartao(){
    var div = document.getElementById("Tat_operacaoCartao");
    var disp = div.style.display;
    div.style.display = disp == 'none' ? 'block' : 'none';
}

function paraDepositar(){
    var div = document.getElementById("Tat_operacao1");
    var disp = div.style.display;
    div.style.display = disp == 'none' ? 'block' : 'none';
}

function cad_rendaFixa(){
    var div = document.getElementById("Tat_cadastroRF");
    var disp = div.style.display;
    div.style.display = disp == 'none' ? 'block' : 'none';
}
function cad_rendaVariavel(){
    var div = document.getElementById("Tat_cadastroRV");
    var disp = div.style.display;
    div.style.display = disp == 'none' ? 'block' : 'none';
}



function paraTransferir(){
    var div = document.getElementById("Tat_operacao2");
    var disp = div.style.display;
    div.style.display = disp == 'none' ? 'block' : 'none';
}

function paraSacar(){
    var div = document.getElementById("Tat_operacao3");
    var disp = div.style.display;
    div.style.display = disp == 'none' ? 'block' : 'none';
}

function liberarCartao(){
    var div = document.getElementById("Tat_ContaSemCartao");
    var disp = div.style.display;
    div.style.display = disp == 'none' ? 'block' : 'none';
}

function mostraMsg(){
    var div = document.getElementById("Tat_informacoesConta1");
    div.style.display = 'block';

    var div2 = document.getElementById("Tat_botaoRoxo1");
    div2.style.display = 'none';

    var div3 =document.getElementById("Tat_carinha");
    div3.style.marginTop = "-50%";
    div3.style.marginLeft = "70%";
    div3.style.height = "50px";

    var div3 =document.getElementById("Tat_saldoEmConta1");
    div3.style.paddingLeft = "15%";
    div3.style.paddingTop = "2%";
}
function retiraMsg(){
    var div = document.getElementById("Tat_informacoesConta1");
    div.style.display = 'none';

    var div2 = document.getElementById("Tat_botaoRoxo1");
    div2.style.display = 'block ';
   
}

function mostraMsg2(){
    var div = document.getElementById("Tat_informacoesConta2");
    div.style.display = 'block';

    var div2 = document.getElementById("Tat_botaoRoxo12");
    div2.style.display = 'none';

    var div3 =document.getElementById("Tat_carinha");
    div3.style.marginTop = "-50%";
    div3.style.marginLeft = "70%";
    div3.style.height = "50px";

    var div3 =document.getElementById("Tat_saldoEmPoupanca");
    div3.style.paddingLeft = "15%";
    div3.style.paddingTop = "2%";
}
function retiraMsg2(){
    var div = document.getElementById("Tat_informacoesConta2");
    div.style.display = 'none';

    var div2 = document.getElementById("Tat_botaoRoxo12");
    div2.style.display = 'block ';
}

var p1 = document.getElementById("Tat_cardInvestimento1");
p1.onmouseover = function(){
    var div = document.getElementById("Tat_fundoImobiliario");
    div.style.display = 'block';
}
p1.onmouseout = function(){
    var div = document.getElementById("Tat_fundoImobiliario");
    div.style.display = 'none';
}

var p2 = document.getElementById("Tat_cardInvestimento2");
p2.onmouseover = function(){
    var div = document.getElementById("Tat_bolsaDeValores");
    div.style.display = 'block';
}
p2.onmouseout = function(){
    var div = document.getElementById("Tat_bolsaDeValores");
    div.style.display = 'none';
}

var p3 = document.getElementById("Tat_cardInvestimento3");
p3.onmouseover = function(){
    var div = document.getElementById("Tat_previdenciaPrivada");
    div.style.display = 'block';
}
p3.onmouseout = function(){
    var div = document.getElementById("Tat_previdenciaPrivada");
    div.style.display = 'none';
}

let rendafixa = {    
        
    rf_data_investimento:"" ,
    rf_data_resgate: "",
    rf_dep_codigo: "",
    rf_iof: "",
    rf_rendimento: "",
    rf_valor_investido: "" 
}

function cadastroRendaFixa(){

    data = new Date()
    let parseDataISO = Date.parse (data);
    rendafixa.rf_data_investimento = parseDataISO;
    rendafixa.rf_dep_codigo = teste;

    
    let dataFormatada = Date.parse(document.getElementById('rf_data_resgate').value);
    rendafixa.rf_data_resgate = dataFormatada;
    rendafixa.rf_iof = document.getElementById('rf_iof').value;
    rendafixa.rf_rendimento = document.getElementById('rf_rendimento').value;
    rendafixa.rf_valor_investido = document.getElementById('rf_valor_investido').value;

    

    fetch('http://localhost:8085/api/v1/rendafixa', {
        method: 'POST',
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify(rendafixa),
    })
    .then((response) => response.json())
    .then((rendafixa) => {
        console.log('Success' + rendafixa);
            
    })
    .catch((error) => {
        console.error('Error:', error);
    });

    let div = document.getElementById("Tat_cadastroRF");
    div.style.display = 'none';
    
}

function cancelarCadastroRF(){
    let div = document.getElementById("Tat_cadastroRF");
    div.style.display = 'none';
}


let rendaVariavel = {    
        
    rv_data_investimento:"" ,
    rv_porcentagem: "",
    rv_dep_codigo: "",
    rv_valor_investido: "" 
}

function cadastroRendaVariavel(){

    data = new Date()
    let parseDataISO = Date.parse (data);
    rendaVariavel.rv_data_investimento = parseDataISO;
    rendaVariavel.rv_dep_codigo = teste;
    
    rendaVariavel.rv_valor_investido = document.getElementById('rv_valor_investido').value;
    rendaVariavel.rv_porcentagem = document.getElementById('rv_porcentagem').value;

    

    fetch('http://localhost:8085/api/v1/rendavariavel/', {
        method: 'POST',
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify(rendaVariavel),
    })
    .then((response) => response.json())
    .then((rendaVariavel) => {
        console.log('Success' + rendaVariavel);
            
    })
    .catch((error) => {
        console.error('Error:', error);
    });

    let div = document.getElementById("Tat_cadastroRV");
    div.style.display = 'none';
    
}

function cancelarCadastroRV(){
    let div = document.getElementById("Tat_cadastroRV");
    div.style.display = 'none';
}

document.addEventListener('DOMContentLoaded',(e)=>{
    let valor = 0;
    const options = {
        method: 'GET',
        mode: 'cors',
        cache: 'default'
    }
    
   
    fetch(`http://localhost:8085/api/v1/rendafixa/` + teste, options)
    .then(r => r.json())
    .then(jsonBody => {
        valor = (jsonBody.rf_valor_investido*(1+(jsonBody.rf_rendimento/100)));
        document.querySelector('#total_rendaFixa').value = parseFloat(valor).toFixed(2);
        valor = jsonBody.rf_valor_investido;
        document.querySelector('#valorAplicado_rendaFixa').value = parseFloat(valor).toFixed(2);
        valor = (jsonBody.rf_valor_investido*(1+(jsonBody.rf_rendimento/100))*(1-(jsonBody.rf_iof/100)));
        document.querySelector('#rf_saldo').value = parseFloat(valor).toFixed(2);
        document.querySelector('#resgate_rendaFixa').value = jsonBody.rf_data_resgate;
        console.log(jsonBody);
        
        
    });
    //.catch(e => console.log('Deu erro:' + e,message))

    
})

document.addEventListener('DOMContentLoaded',(e)=>{
    let valor = 0;
    const options = {
        method: 'GET',
        mode: 'cors',
        cache: 'default'
    }
    
   
    fetch(`http://localhost:8085/api/v1/rendavariavel/` + teste, options)
    .then(r => r.json())
    .then(jsonBody => {
        valor = (jsonBody.rv_valor_investido*(1+(jsonBody.rv_porcentagem/100)));
        document.querySelector('#total_rendaVariavel').value = parseFloat(valor).toFixed(2);
        valor = jsonBody.rv_valor_investido;
        document.querySelector('#valorAplicado_rendaVariavel').value = parseFloat(valor).toFixed(2);
        valor = (jsonBody.rv_valor_investido*(1+(jsonBody.rv_porcentagem/100)));
        document.querySelector('#rv_saldo').value = parseFloat(valor).toFixed(2);
        document.querySelector('#dataInvestimento_rendaVariavel').value = jsonBody.rv_data_investimento;
    });
})

let cartao = {    
        
    car_numero: 0,
    car_ativo: "",
    car_data_vencimento: "",
    car_dep_codigo: "",
    car_limite: ""
}


function enviarAtivaCartao(){

    let desenhoCartao= document.getElementById('Tat_divDesenhoCartao');
    
    desenhoCartao.style.opacity = 1;

    let div = document.getElementById("Tat_operacaoCartao");
    div.style.display = 'none';

    let botao = document.getElementById("Tat_botaoRoxo13");
    botao.style.display = 'none';

    
    document.getElementById('Tat_fraseCartao').innerHTML = 'Atenção ao usar o Cartão de Crédito, não exagerar nas compras e pagar sempre em dia a fatura.';
    
}
    /*cartao.car_numero = 3;
    cartao.car_ativo = 1;
    cartao.car_dep_codigo = teste;
   
    cartao.car_limite = document.getElementById('car_limite').value;
    let dataFormatada = Date.parse(document.getElementById('car_data_vencimento').value);
    cartao.car_data_vencimento = dataFormatada;
    
    fetch('http://localhost:8085/api/v1/cartao', {
        method: 'POST',
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify(cartao),
    })
    .then((response) => response.json())
    .then((cartao) => {
        console.log('Success' + cartao);
            
    })
    .catch((error) => {
        console.error('Error:', error);
    });*/

    
    


function cancelarAtivaCartao(){
    let div = document.getElementById("Tat_operacaoCartao");
    div.style.display = 'none';
}

function mostraAtivaCartao(){
    let div = document.getElementById("Tat_operacaoCartao");
    div.style.display = 'block';
}


