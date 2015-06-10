var project_name = "/nuannuan";
$(document).ready(function() {
    $("#menu li").live("click", function(){
        getItemList($(this).attr("type"), 1, 10);
        $("#pageUp_menu .pageIndex").html(1);
    });

    $("#pageUp_menu .preview").live("click", function(){
        var page = parseInt($("#pageUp_menu .pageIndex").html());
        if(page == 1) {
            return;
        }
        getItemList($("#item_table tr").eq(0).attr("type"), page - 1, 10);
        $("#pageUp_menu .pageIndex").html(parseInt($("#pageUp_menu .pageIndex").html()) -1);
    });

    $("#pageUp_menu .nextview").live("click", function(){
        getItemList($("#item_table tr").eq(0).attr("type"), parseInt($("#pageUp_menu .pageIndex").html()) + 1, 10);
        $("#pageUp_menu .pageIndex").html(parseInt($("#pageUp_menu .pageIndex").html()) + 1);
    });

    $("#search_decoration").live("click", function(){
        var param1 = $("#param1").val();
        var param2 = $("#param1").val();
        if(param1 =='' || param2 ==''){
            alert("不能为空");
            return;
        }
        getDecorationList(param1, param2);
    });
});

//获取数据
function getItemList(type, pageIndex, pageSize) {
    var url = project_name + "/decoration/getItemList"
    $.ajax({
        type: "POST",
        url: url,
        contentType :  "application/x-www-form-urlencoded",
        dataType:"json",
        data: {
            type : type,
            pageIndex : pageIndex,
            pageSize : pageSize
        },
        success: function (result) {
            $("#item_table tr.item_content").html('');
            $("#item_table tr").eq(0).attr("type", type);
            for(var i = 0; i < result.length; i++){
                var content = "<td>" + result[i].name + "</td>";
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
                content += "<td>" + result[i].baonuan + "</td>";
                content += "<td>" + result[i].label1 + "</td>";
                content += "<td>" + result[i].label2 + "</td>";
                content += "<td>" + result[i].getfrom + "</td>";
                $("#item_table tr.item_content").eq(i).html(content);
            }
        },
        error: function (a, b, c) {
            console.log("error");
        }
    });

}

//获取搭配结果
function getDecorationList(param1, param2) {
    var url = project_name + "/decoration/decorate"
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        data: {
            param1: param1,
            param2: param2
        },
        success: function (result) {
            $("#item_table tr.item_content").html('');
            for (var i = 0; i < result.length; i++) {
                var content = "<tr class=‘item_content’><td>" + result[i].name + "</td>";
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
                content += "<td>" + result[i].baonuan + "</td>";
                content += "<td>" + result[i].label1 + "</td>";
                content += "<td>" + result[i].label2 + "</td>";
                content += "<td>" + result[i].getfrom + "</td></tr>";
                $("#item_table tr").after(content);
            }
        },
        error: function (a, b, c) {
            console.log("error");
        }
    });
}