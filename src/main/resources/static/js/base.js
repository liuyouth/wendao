$(function () {
    // toastr.options = {
    //     "closeButton": true,
    //     "debug": false,
    //     "progressBar": true,
    //     "preventDuplicates": false,
    //     "positionClass": "toast-top-right",
    //     "onclick": null,
    //     "showDuration": "400",
    //     "hideDuration": "1000",
    //     "timeOut": "3000",
    //     "extendedTimeOut": "1000",
    //     "showEasing": "swing",
    //     "hideEasing": "linear",
    //     "showMethod": "fadeIn",
    //     "hideMethod": "fadeOut"
    // }
    // if (window.location.pathname === "/login" || window.location.pathname === "/register") {
    //     console.log("window.location.pathname " + window.location.pathname)
    // } else if (!isLogin()) {
    //     goTo("login")
    // }
})

function getFormData($form) {
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function (n, i) {
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function logOut() {

    setToken("");
    goTo("login")
}
function setToken(token) {
    localStorage.setItem('token', token);
}
function getToken() {
    return localStorage.getItem("token");

}
function isLogin() {
    var token = localStorage.getItem("token");
    if (token !== null && token !== undefined && token !== "") {
        return true;
    }
    return false;
}

function goTo(url) {
    if (url === undefined)
        url = "";
    window.location.href = "/" + url;
}
// var baseUrl = 'http://192.168.1.7:8080/';
var baseUrl = 'http://127.0.0.1:8080/';
function ajaxx(type,url,data,success,error) {
    $.ajax({
        type: type,
        contentType: "application/json",
        url: baseUrl+url,
        headers: {
            "authorization": getToken()
        },
        data: data,
        success:function (data) {
            success(data);
        },
        error:error
    });
}

function ajaxx(type,url,data,success,error,ss) {
    $.ajax({
        type: type,
        contentType: "application/json",
        url: url,
        headers: {
            "authorization": getToken()
        },
        data: data,
        success:function (data) {
            success(data);
        },
        error:error
    });
}


var util = {
    serializeObject: function (form) {
        var formEL = $(form);
        var o = {};
        var a = formEL.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    },

    fillFormData: function (form, obj, isStatus) {
        var formEL = $(form);
        if (obj !== null && obj !== undefined) {
            $.each(obj, function (index, item) {
                formEL.find("[name=" + index + "]").val(item);
            });
        }
    },
    empty: function (data) {
        if (null == data || "" == data) return true;
        else return false;
    }
};
