var project_name = "/nuannuan";
$(document).ready(function() {

    if($("body#cloth").length) {
        getClothList($("#action").val(), $("#item_table tr").eq(0).attr("type"), parseInt($("#pageUp_menu .pageIndex").html()), 10);

        $("tr.item_content").live("click", function(){
            var clothId = $(this).attr("cId");
            addToMyWardrobe(clothId);
        });

        /* 点击衣服种类 */
        $("#menu li").live("click", function(){
            getClothList($("#action").val(), $(this).attr("type"), 1, 10);
            $("#pageUp_menu .pageIndex").html(1);
        });
        /* 点击衣服种类 */
        $("input[type='radio']").bind("change", function(){
            $("#action").val($("input[type='radio']:checked").val());
            getClothList($("#action").val(), $("#item_table tr").eq(0).attr("type"), 1, 10);
            $("#pageUp_menu .pageIndex").html(1);
        });

        $("#pageUp_menu .preview").live("click", function(){
            var page = parseInt($("#pageUp_menu .pageIndex").html());
            if(page == 1) {
                return;
            }
            getClothList($("#action").val(), $("#item_table tr").eq(0).attr("type"), page - 1, 10);
            $("#pageUp_menu .pageIndex").html(parseInt($("#pageUp_menu .pageIndex").html()) -1);
        });

        $("#pageUp_menu .nextview").live("click", function(){
            getClothList($("#action").val(), $("#item_table tr").eq(0).attr("type"), parseInt($("#pageUp_menu .pageIndex").html()) + 1, 10);
            $("#pageUp_menu .pageIndex").html(parseInt($("#pageUp_menu .pageIndex").html()) + 1);
        });
    }

    $("#search_decoration").live("click", function(){
        getDecorationList();
    });

    $("#upload").live("click", function(){
        uploadData();
    });

    /* login.jsp*/
    $("#login").live("click", function(){
        if ($("#username").val() == "") {
            $(".error").html("请输入账号");
            return;
        }
        if ($("#password").val() == "") {
            $(".error").html("请输入密码");
            return;
        }
        $(".result").hide();
        $("#loginForm").submit();
    });

    $("#register_change").live("click", function(){
        $("#loginForm #passwordConfirm").show();
        $("#loginForm #register_change").hide();
        $("#loginForm #login_change").show();
        $("#loginForm #login").val("注册");
        $("#loginForm").attr("action", "register");
    });

    $("#login_change").live("click", function(){
        $("#loginForm #passwordConfirm").hide();
        $("#loginForm #login_change").hide();
        $("#loginForm #register_change").show();
        $("#loginForm #register").val("登录");
        $("#loginForm").attr("action", "login");
    });
});

//获取数据
function getClothList(action, type, pageIndex, pageSize) {
    var url = project_name + "/decoration/ajax/getClothList"
    $.ajax({
        type: "POST",
        url: url,
        contentType :  "application/x-www-form-urlencoded",
        dataType:"json",
        data: {
            action:action,
            type : type,
            pageIndex : pageIndex,
            pageSize : pageSize
        },
        success: function (result) {
            $("#item_table tr.item_content").remove();
            $("#item_table tr").eq(0).attr("type", type);
            for (var i = 0; i < result.length; i++) {
                var content = "<tr class='item_content' cId = '" + result[i].id + "'>";
                content += "<td>" + result[i].type + "</td>";
                content += "<td>" + result[i].name + "</td>";
                content += "<td>" + result[i].num + "</td>";
                content += "<td>" + result[i].level + "</td>";
                content += "<td>" + result[i].huali + "</td>";
                content += "<td>" + result[i].jianyue + "</td>";
                content += "<td>" + result[i].youya + "</td>";
                content += "<td>" + result[i].huopo + "</td>";
                content += "<td>" + result[i].chengshu + "</td>";
                content += "<td>" + result[i].keai + "</td>";
                content += "<td>" + result[i].xinggan + "</td>";
                content += "<td>" + result[i].qingchun + "</td>";
                content += "<td>" + result[i].qingliang + "</td>";
                content += "<td>" + result[i].baonuan + "</td>";
                content += "<td>" + result[i].label1 + "</td>";
                content += "<td>" + result[i].label2 + "</td>";
                content += "<td>" + result[i].getfrom + "</td></tr>";
                $("#item_table tr").eq(i).after(content);
            }
        },
        error: function (a, b, c) {
            console.log("error");
        }
    });
}

//获取数据
function addToMyWardrobe(cId) {
    var url = project_name + "/decoration/ajax/addCloth"
    $.ajax({
        type: "POST",
        url: url,
        contentType :  "application/x-www-form-urlencoded",
        dataType:"json",
        data: {
            clothId:cId
        },
        success: function (result) {
            if(result.success == "1") {
                $("tr.item_content[cId='"+ cId +"']").css({ "color": "#ff0011", "background": "blue" });
            }
        },
        error: function (a, b, c) {
            console.log("error");
        }
    });
}


//获取搭配结果
function getDecorationList() {
    var brief = $("#brief").val();
    var elegance = $("#elegance").val();
    var lovely = $("#lovely").val();
    var pure = $("#pure").val();
    var cool = $("#cool").val();
    if(brief == '0' && elegance =='0' && lovely =='0' && pure =='0' && cool =='0'){
        alert("不能为空");
        return;
    }
    var url = project_name + "/decoration/ajax/decorate"
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        data: {
            brief: brief,
            elegance: elegance,
            lovely: lovely,
            pure: pure,
            cool: cool
        },
        success: function (result) {
            $("#item_table tr.item_content").remove();
            for (var i = 0; i < result.length; i++) {
                var content = "<tr class='item_content' cId = '" + result[i].id + "'>";
                content += "<td>" + result[i].type + "</td>";
                content += "<td>" + result[i].name + "</td>";
                content += "<td>" + result[i].num + "</td>";
                content += "<td>" + result[i].level + "</td>";
                content += "<td>" + result[i].huali + "</td>";
                content += "<td>" + result[i].jianyue + "</td>";
                content += "<td>" + result[i].youya + "</td>";
                content += "<td>" + result[i].huopo + "</td>";
                content += "<td>" + result[i].chengshu + "</td>";
                content += "<td>" + result[i].keai + "</td>";
                content += "<td>" + result[i].xinggan + "</td>";
                content += "<td>" + result[i].qingchun + "</td>";
                content += "<td>" + result[i].qingliang + "</td>";
                content += "<td>" + result[i].baonuan + "</td>";
                content += "<td>" + result[i].label1 + "</td>";
                content += "<td>" + result[i].label2 + "</td>";
                content += "<td>" + result[i].getfrom + "</td></tr>";
                $("#item_table tr").eq(i).after(content);
            }
        },
        error: function (a, b, c) {
            console.log("error");
        }
    });
}

function uploadData(){
    $.ajaxFileUpload({
        url: project_name + "/cloth/ajax/uploadData",            //需要链接到服务器地址
        secureuri:false,
        fileElementId:'xlsUpload',                        //文件选择框的id属性
        dataType : 'json',
        success: function(data, status){

        },error: function (data, status, e){
            alert('上传图片异常');
        }
    });
}