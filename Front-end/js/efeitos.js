$(document).ready(function() {
    $("#categorias").hide();
    $("#campo_categoria").hide();
    $("#div_editar_tarefas").hide();
    $("#div_msg_sucesso").hide();
    

    $(".ocultar_categoria").click(function() {
        $("#campo_categoria").hide(1000);
        $("#categorias").hide(1000);
    });
    $(".mostrar_categoria").click(function() {
       // $("#campo_categoria").show(1000);
        $("#categorias").show(800);
    });

    $(".ocultar_tarefas").click(function() {
        $("#div_editar_tarefas").fadeOut(1000);
        $("#div_msg_sucesso").fadeIn(2000);
        $("#div_msg_sucesso").fadeOut(5000);
        $(".input_form_editar_tarefa").val("");
        $(".input_form_editar_tarefa").prop('disabled', true);
        $(".input_form_editar_tarefa").css("background-color", "#C4C4C4");
    });

    $(".ocultar_tarefas_btn_cancelar").click(function() {
        $("#div_editar_tarefas").fadeOut(1000);

    });
    

    $(".mostrar_tarefas").click(function() {
        alert("Luciano");
        $("#div_editar_tarefas").fadeIn(2000);
        $('html, body').animate({
            scrollTop: $(".formulario_edicao").offset().top
        }, 2000);
    });

    $(".ocultar_tarefas").click(function() {
        $('html, body').animate({
            scrollTop: $(".msg_confirmacao_edicao").offset().top
        }, 2000);
    });


    $("#editar_tarefa").click(function() {
        //alert("teste");
        var valor = $(".input_form_editar_tarefa").prop('disabled');
        //console.log(valor);
        if (valor) {

            $(".input_form_editar_tarefa").prop('disabled', false); //Habilita todos os campos           
            $(".input_form_editar_tarefa").css("background-color", "#cfd0d2");
            $(".input_form_editar_tarefa").css("outline-color", "blue");
            $(".input_form_editar_tarefa").css("outline-color", "blue");



        } else {
            $(".input_form_editar_tarefa").prop('disabled', true); //Desabilita todos os campos
            $(".input_form_editar_tarefa").css("background-color", "#C4C4C4");
        }


    });





    $("#myForm").submit(function(event) {
        //console.log($(this).serializeArray());

        var dataArray = $("#myForm").serializeArray(),
            dataObj = {};

        $(dataArray).each(function(i, field) {
            //dataObj[field.name] = field.value;
            dataObj[i] = field.value;
            console.log(dataObj[i]);
        });

        event.preventDefault();
    });

});