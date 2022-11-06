
function validateForm(){

    var nome = document.getElementById("name");
    var login = document.getElementById("login");
    var email = document.getElementById("email");
    var endereco = document.getElementById("endereco");
    var senha = document.getElementById("psw");
    var senha_r = document.getElementById("psw-repeat");

    auxiliarAtividade8(nome);
    auxiliarAtividade8(login);
    auxiliarAtividade8(email);
    auxiliarAtividade8(endereco);
    auxiliarAtividade8(senha);
    auxiliarAtividade8(senha_r);

    if(senha.value != senha_r.value){
        alert('Senhas repetida diferente da digitada anteriormente')
        return false;
    }

    var hasLowerCase = false;
    var hasUpperCase = false;
    var hasSpecial = false;
    var hasMinLen = false;

    if (senha.value.length >3){
        hasMinLen = true;
    }else{
        alert('Tamanho invalido')
        return false;
    }

    for(i=0; i<senha.value.length; i++){
        if (senha.value[i].toUpperCase() === senha.value[i] && senha.value[i].toLowerCase()!=senha.value[i]){
            hasUpperCase = true;
        }

        if (senha.value[i].toUpperCase() != senha.value[i] && senha.value[i].toLowerCase()===senha.value[i]){
            hasLowerCase = true;
        }

        if(senha.value[i].toUpperCase() === senha.value[i].toLowerCase()){
            hasSpecial = true;
        }
    }

    if (hasLowerCase && hasUpperCase && hasSpecial && hasMinLen){
        return true
    }else{
        alert('Senha fraca')
        return false;
    }
}

function auxiliarAtividade8(el){ 

    if (el == null) {
        alert("O elemento não existe");
        return false;
    }
    /* testa se o valor é vazio ou formado por apenas espaços em branco */
    if (el.value == null || el.value.length == 0 || (/^\s+$/.test(el.value))) {
        alert("Valor vazio ou espaços em branco digitados")
        return false;
    } 
}