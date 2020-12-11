// busca de dados de endereço utilizando o CEP
const cep = document.querySelector("#cep")

const showData = (result)=>{
    for(const campo in result){
        if(document.querySelector("#"+campo)){
            document.querySelector("#"+campo).value = result[campo]            
        }

    }
}

cep.addEventListener("blur",(e)=>{
    let search = cep.value.replace("-","")
    const options = {
        method: 'GET',
        mode: 'cors',
        cache: 'default'
    }
    fetch(`http://viacep.com.br/ws/${search}/json`, options)
    .then(response =>{response.json()
        .then(data => showData(data))
        })
    .catch(e => console.log('Deu erro:' + e,message)) 
})


// função utilizada para atualizar o valor do campo de pontos no cadastro de dependentes
function updateTextInput(val) {
    document.getElementById('tcTextInput').value=val+"pts."; 
}

// payload do post de Usuario
let usuario = 
    {
    usu_cpf:  "",
    usu_nome: "",
    email: "",
    usu_cep: "",
    usu_tipo_endereco: "",
    usu_nome_end: "",
    usu_numero: "",
    usu_bairro: "",
    usu_cidade: "",
    usu_uf: "",
    usu_tel_celular: "",
    usu_tel_fixo: "",
    usu_senha: "",
    usu_status: "1",
    usu_complemento: "",
    role:["user"]
    };

let dependente = {      
        
    dep_num_seq: "" ,
    dep_nome: "",
    dep_usuario: "",
    dep_senha: "",
    dep_url_foto: "",
    usuario_usu_id: "" ,
    tarefas: [] 
   };


// funcao proximo faz a validação dos campos, 
// insere os dados na variavel data, e prossegue para proxima pagina.

function proximo(){
    usuario.usu_nome = formuser.nome.value;
    usuario.usu_cpf = formuser.cpf.value;
    usuario.usu_tel_fixo = formuser.telefone.value;
    usuario.usu_tel_celular = formuser.celular.value;
    usuario.email = formuser.email.value;
    usuario.usu_senha = formuser.senha.value;

    // if(usuario.usu_nome == "" || usuario.usu_cpf == "" || usuario.usu_tel_celular == "" || usuario.usu_email == "" || usuario.usu_senha == ""){
    //     console.log('Preencha os campos.');
    //     formuser.focus();
    //     return false;
    // } else{
        console.log(usuario)
        var div = document.getElementById("tcCadastroPagina1");
        var proximadiv = document.getElementById("tcCadastroPagina2");
        div.style.display = 'none';
        proximadiv.style.display = 'block';
    // }
}


function proximo2(){
    usuario.usu_cep = formuser2.cep.value.replace("-", "");
    usuario.usu_tipo_endereco = formuser2.tipo.value;
    usuario.usu_nome_end = formuser2.logradouro.value;
    usuario.usu_numero = formuser2.numero.value;
    usuario.usu_bairro = formuser2.bairro.value;
    usuario.usu_cidade = formuser2.localidade.value;
    usuario.usu_uf = formuser2.uf.value;
    usuario.usu_complemento = formuser2.complemento.value;

    // if(usuario.usu_cep == "" || usuario.usu_tipo_endereco == "" || usuario.usu_nome_end == "" || usuario.usu_numero == "" || usuario.usu_bairro == "" || usuario.usu_cidade == "" || usuario.usu_uf == ""){
    //     console.log('Preencha os campos.');
    //     formuser.focus();
    //     return false;
    // }
    //  else{
        console.log(usuario)
        var div = document.getElementById("tcCadastroPagina2");
        var proximadiv = document.getElementById("tcCadastroPagina3");
        div.style.display = 'none';
        proximadiv.style.display = 'block';
    // }
}

// funcao envia os dados com o metodo poste passando a variavel
function enviarDados(){
    dependente.dep_num_seq = "1"
    dependente.dep_nome = formuser3.nome.value;
    dependente.dep_usuario = formuser3.usuario.value;
    dependente.dep_senha = formuser3.senha.value;
    dependente.dep_url_foto = formuser3.foto.value;
    dependente.usuario_usu_id = "3"

    fetch('http://localhost:8085/api/v1/usuario', {
        method: 'POST',
        mode: 'no-cors',
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify(usuario),
    })
    .then((response) => response.json())
    .then((usuario) => {
        console.log('Success' + usuario);
    })
    .catch((error) => {
        console.error('Error:', error);
    });

}

function cadastroDep(){
    fetch('http://localhost:8085/api/v1/dependente', {
        method: 'POST',
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify(dependente),
    })
    .then((response) => response.json())
    .then((dependente) => {
        console.log('Success' + dependente);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

const fileInput = document.getElementById("file_input")

fileInput.addEventListener("change", (event)=>{
    const img = event.target.files[0]

    const formData = new FormData()
    formData.append("image", img)

    fetch('http://localhost:8085/upload', {
        method: 'POST',
        body: formData,
        mode: "no-cors"
    })
    .then(() => console.log("arquivo enviado com sucesso"))
    .catch(err => console.log("erro ao enviar arquivo" + err))
})

