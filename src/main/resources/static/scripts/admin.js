console.log("admin.js loaded.")
document.querySelector("#profileImage").addEventListener('change', function(event){
    var file = event.target.files[0];
    var reader = new FileReader();
    reader.onload = function(){
        document.getElementById("uploadedImagePreview").src=reader.result;
    };
    reader.readAsDataURL(file)
})