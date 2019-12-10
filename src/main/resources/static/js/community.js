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
            if (response.code == 200) {
                $("#comment_section").hide();
            } else {
                alert(response.message)
            }
        },
        dataType: "json "
    });
}
