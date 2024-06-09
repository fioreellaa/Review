var btnNav = document.getElementById('btn-nav');

// Agregar un evento de cambio al botón de menú
btnNav.addEventListener('change', function() {
    // Obtener el contenedor principal
    var container = document.querySelector('.container');
    
    // Verificar si el botón de menú está marcado
    if (this.checked) {
        // Agregar una clase al contenedor para indicar que el menú está abierto
        container.classList.add('menu-open');
    } else {
        // Eliminar la clase del contenedor para indicar que el menú está cerrado
        container.classList.remove('menu-open');
    }
});


$(document).on('click', '.ubton', function(){
	var modal = new bootstrap.Modal(document.getElementById('addModal'));
   	modal.show();
});



$(document).on('click', '.updateUsuario', function(){
	var modal = new bootstrap.Modal(document.getElementById('editModal'));
   	modal.show();
   	
   	var $idUser = $(this).closest('.tr-table').find('.idUsuario').val();

   	console.log($idUser);
   	
   	$.ajax({
		type: 'GET',
		url: '/user/menu-admin/list/'+$idUser,
		success: function(data){
        	console.log(data.usermail)
        	$('#fullname').val(data.fullname);
            $('#usermail').val(data.usermail);
            $('#username').val(data.username);
            $('#userpassword').val(data.userpassword);
           $('#rol').val([]); // Opción para limpiar todas las selecciones previas
    
		    // Iterar sobre los roles del usuario y establecerlos como seleccionados en el select
		    data.roles.forEach(function(rol) {
		        $('#rol option[value="' + rol.id + '"]').prop('selected', true);
		    });
        },
        error: function(){
            console.log('Error al obtener el usuario');
        },
	});
});



const passwordInput = document.getElementById('userpassword');
const togglePassword = document.querySelector('.toggle-password');

togglePassword.addEventListener('click', function() {
    const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
    passwordInput.setAttribute('type', type);
    
    const eyeIcon = this.querySelector('i');
    eyeIcon.classList.toggle('fa-eye-slash');
    
    if (type === 'text') {
        eyeIcon.classList.remove('fa-eye');
        eyeIcon.classList.add('fa-eye-slash');
    } else {
        eyeIcon.classList.remove('fa-eye-slash');
        eyeIcon.classList.add('fa-eye');
    }
});