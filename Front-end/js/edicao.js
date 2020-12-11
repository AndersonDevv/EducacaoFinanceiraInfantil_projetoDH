let usuario;
window.onload = function(){
    fetch('http://localhost:8085/api/v1/usuario/1', {
        method: 'GET',
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
    })
    .then((response) => response.json())
    .then((response) => {
        usuario = response;
        console.log('Success' + response);
        console.log(usuario);
        dadosCadastrais.cep.value = usuario.usu_cep;
        dadosCadastrais.tipo.value = usuario.usu_tipo_endereco;
        dadosCadastrais.logradouro.value = usuario.usu_nome_end;
        dadosCadastrais.numero.value = usuario.usu_numero;
        dadosCadastrais.bairro.value = usuario.usu_bairro;
        dadosCadastrais.localidade.value = usuario.usu_cidade;
        dadosCadastrais.uf.value = usuario.usu_uf;
        dadosCadastrais.complemento.value = usuario.usu_complemento;  
    })
    .catch((error) => {
        console.error('Error:', error);
    });
    
    
}