$('.message a').click(function(){
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
 });

 
document.getElementById("submit-form-reg").addEventListener("click", function(event) {
    event.preventDefault(); // Evitar el comportamiento predeterminado del enlace
    document.getElementById("register-form").submit(); 
});
 
document.getElementById("submit-form-log").addEventListener("click", function(event) {
    event.preventDefault(); // Evitar el comportamiento predeterminado del enlace
    document.getElementById("login-form").submit(); 
});