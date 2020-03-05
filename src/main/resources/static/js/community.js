function post() {
    var id = $("#question_id").val();
    var content = $("#comment_content").val();
    // debugger;
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": id,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            //评论成功，则关闭评论框
            /*if (response.code == 200) {
                $("#comment_section").hide();
            }*/

            //评论成功，刷新页面
            if (response.code == 200) {
                window.location.reload();
            } else {
                //如果是未登录则确认是否登陆
                if (response.code == 2003) {
                    //是否登陆
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        debugger;
                        //打开登陆界面
                        window.open("https://github.com/login/oauth/authorize?client_id=d8663cbd9f4f2b2040f0&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        //登陆后存储一个closable变量在浏览器中
                        window.localStorage.setItem("closable","true");
                    }
                } else {
                    alert(response.message)
                }
            }
        },
        dataType: "json "
    });
}
