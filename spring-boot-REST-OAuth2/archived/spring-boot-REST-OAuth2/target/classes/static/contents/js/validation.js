$(document).ready(function(){

    $("#submit").click(function(e){
        var name = $("#name").val();
        var email = $("#email").val();
        var sex = $("input[name='sex']").prop("checked");
        var subject = $("input[name='subjects[]']").text();
        var country = $('#countryList :selected').text();//$("input[name='country']").find("selected").text();

        function isSubjectChecked() {
            var checked = false;
            $(".subjects").each(function(index){
                if($(this).is(':checked')) {
                    checked = true;
                }
            });
            // alert(checked);
            return checked;
        }


        function isSexChecked() {
            var checked = false;
            $(".sex").each(function(index){
                if($(this).is(':checked')) {
                    checked = true;
                }
            });
            // alert(checked);
            return checked;
        }


        if(name===""){
            $("#msg-name").text("Name field is empty.");
            $("#msg-name").attr("class", "alert-danger");
            e.preventDefault();
        }else{
            $("#msg-name").text("");
        }
        if(email===""){
            $("#msg-email").text("Email field is empty.");
            $("#msg-email").attr("class", "alert-danger");
            e.preventDefault();
        } else if(validateEmail(email)===false){
            $("#msg-email").text("Invalid email was found.");
            $("#msg-email").attr("class", "alert-danger");
            e.preventDefault();
        }else{
            $("#msg-email").text("");
        }
        if(isSexChecked()===false){
            $("#msg-sex").text("Sex field is empty.");
            $("#msg-sex").attr("class", "alert-danger");
            e.preventDefault();
        }else{
            $("#msg-sex").text("");
        }
        if(isSubjectChecked()===false){
            $("#msg-subject").text("Subject field is empty.");
            $("#msg-subject").attr("class", "alert-danger");
            e.preventDefault();
        }else{
            $("#msg-subject").text("");
        }

        if(country==="" || typeof country == 'undefined'){
            $("#msg-country").text("Country field is empty.");
            $("#msg-country").attr("class", "alert-danger");
            e.preventDefault();
        }else{
            $("#msg-country").text("");
        }
    });

    function validateEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }
});