/**
 * 重构关键方法
 * **/
function comment2reply(id, type, content) {
    if (!content) {
        alert("不能回复空内容！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": id,
            "content": content,
            "type": type
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
                        // debugger;
                        //打开登陆界面
                        window.open("https://github.com/login/oauth/authorize?client_id=d8663cbd9f4f2b2040f0&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        //登陆后存储一个closable变量在浏览器中
                        window.localStorage.setItem("closable", "true");
                    }
                } else {
                    alert(response.message)
                }
            }
        },
        dataType: "json "
    });
}

/**
 * 提交问题回复
 * **/
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    console.log(content);
    comment2reply(questionId, 1, content);
}

/**
 * 提交二级评论
 * **/
function comment(e) {
    var id = e.getAttribute("data-id");
    var content = $("#input-" + id).val();
    comment2reply(id, 2, content);
}

/**
 * 二级评论展开与收缩
 */
function collapse(e) {
    /* 方法1
    //获取当前点击的元素id
    var id = e.getAttribute("data-id");
    //添加/删除该id的in class
    $("#comment" + id).toggleClass("in");
    */

    //获取当前点击的评论id
    var id = e.getAttribute("data-id");
    //获取二级评论的div
    var subComments = $("#comment" + id);
    //获取二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //折叠二级评论
        subComments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        /*二级评论最外层div*/
        var subCommentContainer = $("#comment" + id);
        /*避免重复展示*/
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            subComments.addClass("in");
            //标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {   /*function -> 发送成功后的回调函数，data是返回的数据*/
                $.each(data.data.reverse(), function (key, comment) {
                    /*循环添加每个评论*/

                    /*media-left*/
                    var mediaLeft = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    /*media-body*/
                    var mediaHead = $("<h5/>", {
                        "class": "media-heading",
                        html: comment.user.name
                    })
                    var mediaContent = $("<div/>", {
                        html: comment.content
                    });
                    var mediaMenu = $("<div/>", {
                        "class": "menu",
                    }).append($("<spann/>", {
                        "class": "menu-comment-time",
                        html: moment(comment.gmtCreate).format("YYYY-MM-DD,hh:mm:ss")
                    }));
                    var mediaBody = $("<div/>", {
                        "class": "media-body"
                    }).append(mediaHead).append(mediaContent).append(mediaMenu);

                    /*comment*/
                    var comment = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12"
                    }).append(mediaLeft).append(mediaBody);

                    /*commit to Container*/
                    subCommentContainer.prepend(comment);
                });


                //展开二级评论
                subComments.addClass("in");
                //标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}

/*选择标签*/
function selectTag(e) {
    var value = e.getAttribute("data-tag")
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}

/*展示标签*/
function showSelectTag() {
    $("#select-tag").show();
}