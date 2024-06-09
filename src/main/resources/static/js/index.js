function comment(a){

    document.getElementsByClassName("text-box")[a].style.visibility="visible";
    document.getElementsByClassName("text-box")[a].style.transition="all 2s";
}

function noncomment(b){

    document.getElementsByClassName("text-box")[b].style.visibility="hidden";
    document.getElementsByClassName("text-box")[b].style.transition="all 0.5s";
}

function openPopup(i) {
  	var modal = new bootstrap.Modal(document.getElementById('myModal'+i));
   	modal.show();
    
}


/*       CLICS           */
$(document).on('click', '#imgdestino', function(){
 	//var destino = $(this).closest('input');
 	/*var destinoInput = $(this).find('input');
	var destinoValue = destinoInput.val().trim();*/
	    var destinoValue = $(this).siblings('.cldestino').val().trim();

	
	$.ajax({
		type: 'POST',
		data: { destinoValue: destinoValue } ,
		url: '/tourist-places/clic',
		success: function(data){
			console.log(data.placeId)
        	
        },
        error: function(){
            console.log('Error al obtener el destino');
        },
	});
	
});

/*       LIKES           */

$(document).on('click', '.like', function(){
	var destinoValue = $(this).siblings('.cldestino').val().trim();
	var $likeCountElement = $(this).find('#number-likes');
    var $likeCountElement2 = $(this).closest('.reaction-modal').find('#number-likes');
	
	$.ajax({
		type: 'POST',
		data: { destinoValue: destinoValue } ,
		url: '/tourist-places/like',
		success: function(data){
			console.log(data.placeId)
			console.log(data.nlikes)
        	$likeCountElement.text(data.nlikes);
        	$likeCountElement2.text(data.nlikes + ' Me gusta');
        },
        error: function(){
            console.log('Error al obtener el destino');
        },
	});
	
});



var currentImage = 2

function changeImgR(index) {
    var contenedor = document.getElementsByClassName("comment-img")[index];
    var imagenPrincipal = contenedor.getAttribute("data-imagen-principal");
    // Aquí implementa la lógica para cambiar a la siguiente imagen basada en la imagen principal
    updateImage(imagenPrincipal);
    
	currentImage++; // Incrementa el número de imagen
	   if (currentImage > 3) {
	       firstImage(imagenPrincipal);
	       currentImage =1;
	    } 
}

function changeImgL(index) {
 var contenedor = document.getElementsByClassName("comment-img")[index];
    var imagenPrincipal = contenedor.getAttribute("data-imagen-principal");
    // Aquí implementa la lógica para cambiar a la imagen anterior basada en la imagen principal
    if (currentImage < 1) {
        currentImage = 2; // Si es menor que 1, vuelve a la última imagen
    }
        currentImage--; // Decrementa el número de imagen

    updateImage(imagenPrincipal);
}

function obtenerNombreBase(nombreImagen) {
    // Obtener el índice del último punto en el nombre de la imagen
    var lastIndex = nombreImagen.lastIndexOf(".");
    // Extraer la parte de la cadena desde el inicio hasta el último punto
    var nombreBase = nombreImagen.substring(0, lastIndex);
    return nombreBase;
}

function updateImage(imagenPrincipal) {
    var img = document.getElementById("img-comment");
    var nombreBase = obtenerNombreBase(imagenPrincipal);
    img.src = "../imagenes/"+ nombreBase + currentImage + ".jpg";
    
}

function firstImage(imagenPrincipal){
	var img = document.getElementById("img-comment");
	var nombreBase = obtenerNombreBase(imagenPrincipal);

	img.src = "../imagenes/"+ nombreBase + ".jpg";
}

window.addEventListener('scroll', function() {
    var navbar = document.getElementById('navbar');
    var scrollPosition = window.scrollY;

    if (scrollPosition > 500) {
       navbar.classList.add('fixed-nav', 'bg-dark-dark');
    } else if (scrollPosition > 100) {
       navbar.classList.add('fixed-nav', 'bg-dark');
       navbar.classList.remove('bg-dark-dark'); 
    } else {
       navbar.classList.remove('fixed-nav', 'bg-dark', 'bg-dark-dark');
    }
});

/*      COMENTARIOS          */
var usuarioActual = ""; 

$(document).on('click', '.submitcomment', function(){
    var form = $(this).closest('form');
    var formData = form.serialize();
        
    var comentarioInput = form.find('.comment');
	var comentarioValue = comentarioInput.val().trim(); 

    if (comentarioValue === "") {
        return; 
    }
    
    $.ajax({
		type: 'POST',
		data: formData,
		url: '/tourist-places/comment',
		success: function(data){
			console.log(data.MENSAJE)
        	var newCommentHtml = '<div class="incomment">';
        
            if (data.usern != null) {
                newCommentHtml += '<div><img id="mini-img" src="@{/imagenes/}' + data.user.userimage + '" alt="Imagen no encontrada">';
                newCommentHtml += '<strong>' + data.usern + '</strong>';
                
                newCommentHtml += '<p>' + data.comentario + '</p></div></div>';
	            $('.comments-modal').append(newCommentHtml);
	            $(".comment").val('');
            } else {
				
				var myModaal = new bootstrap.Modal(document.getElementById('myModaal'));
                myModaal.show();
				
				$(document).on('click', '.submitusername', function(){
				    var form = $(this).closest('form');
				    var formData = form.serialize();
				    
				    $.ajax({
						type: 'POST',
						data: formData,
						url: '/tourist-places/tempusername',
						success: function(data){
					
				            newCommentHtml += '<div><img id="mini-img" src="../imagenes/user-no-photo.png" alt="Imagen no encontrada">';
              				newCommentHtml += '<strong>'+data.username+'</strong>';
              				
              				newCommentHtml += '<p>' + comentarioValue + '</p></div></div>';
				            $('.comments-modal').append(newCommentHtml);
				            $(".comment").val('');
				            
				            var modal = new bootstrap.Modal(document.getElementById('myModaal'));
            				modal.hide();
				        },
				        error: function(){
				            console.log("mal");
				        },
					});
				});
				
                
            }
            
            
        },
        error: function(){
            console.log('Error al enviar el comentario');
        },
	});
});





/*
$(document).on('click', '.submitcomment', function(){
    var form = $(this).closest('form');
    var formData = form.serialize();

    var comentarioInput = form.find('.comment');
    var comentarioValue = comentarioInput.val().trim(); 
	var usuarioo = null;

    if (comentarioValue === "") {
        return; 
    }

	//Verificar usuario
	$.ajax({
		type: 'GET',
		url: 'tourist-places/userget',
		success: function(data){
			usuarioo = data.usern
			if(data.usern != null){
				console.log("usuarioo")
			}else{
				console.loh("usuario nulo")
			}

		}, error: function(){
			console.log("error")
		}
		
	})
/*
    // Llamar a la función para obtener el nombre de usuario
    obtenerNombreUsuario().then(function(usuarioActual) {
        var formData = form.serialize();
        // Realizar la llamada AJAX para enviar el comentario
        enviarComentario(formData, usuarioActual);
    }).catch(function(error) {
        console.error(error); // Manejar cualquier error que ocurra al obtener el nombre de usuario
    });*/
/*});*/

function obtenerNombreUsuario() {
    return new Promise(function(resolve, reject) {
        // Capturar el clic en el botón de envío
        $(document).on('click', '.submitusername', function(){
            // Obtener el valor del campo de entrada de texto
            var username = $('.username').val();
            
            // Crear los datos que se enviarán en la solicitud AJAX
            var formData = {
                username: username
            };
            
            // Realizar la llamada AJAX para enviar el nombre de usuario al servidor
            $.ajax({
                type: 'POST',
                data: formData,
                url: '/tourist-places/tempusername',
                success: function(data){
                    console.log("holaa" + data.username);
                    resolve(data.username);
                },
                error: function(){
                    console.log("mal");
                    reject("Error al obtener el nombre de usuario");
                },
            });
        });
    });
}

function enviarComentario(formData, usuarioActual) {
    $.ajax({
        type: 'POST',
        data: formData,
        url: '/tourist-places/comment',
        success: function(data){
            console.log(data.MENSAJE)
            var newCommentHtml = '<div class="incomment">';
            if (data.user != null) {
                newCommentHtml += '<div><img id="mini-img" src="@{/imagenes/}' + data.user.userimage + '" alt="Imagen no encontrada">';
                newCommentHtml += '<strong>' + data.usern + '</strong>';
            } else {
                var myModaal = new bootstrap.Modal(document.getElementById('myModaal'));
                myModaal.show();
                newCommentHtml += '<div><img id="mini-img" src="../imagenes/user-no-photo.png" alt="Imagen no encontrada">';
                newCommentHtml += '<strong>'+usuarioActual+'</strong>';
            }
            newCommentHtml += '<p>' + data.comentario + '</p></div></div>';
            $('.comments-modal').append(newCommentHtml);
            $(".comment").val('');
        },
        error: function(){
            console.log('Error al enviar el comentario');
        },
    });
}