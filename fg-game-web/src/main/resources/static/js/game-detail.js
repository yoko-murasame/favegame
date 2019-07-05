$(function () {

    $("#reply-submit").click(function () {
        var reply = {};
        var path = window.location.pathname;
        reply.replierId = [[${user.id}]];
        reply.toCommentId = $("#comment-id").html();
        reply.content = $("#reply-detail").val();
        console.log(reply);
        $.ajax({
            type: "post",
            url: path + "/addReply",
            data: reply,
            success: function (back) {
                location.reload();
//                    console.log(back);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");

            }
        });
    });

    $("#comment-submit").click(function () {
        var comment = {};
        var path = window.location.pathname;
        comment.gmComment = $("#comment-detail").val();
        comment.gmCritic = [[${user.id}]];
        comment.gmGameId = [[${game.data.id}]];
        console.log($(".body-rate-star").children()[0].className);
        if ($(".body-rate-star").children()[0].className == "review-add-stars-hover") {
            comment.gmRate = 2;
        }
        if ($(".body-rate-star").children()[1].className == "review-add-stars-hover") {
            comment.gmRate = 4;
        }
        if ($(".body-rate-star").children()[2].className == "review-add-stars-hover") {
            comment.gmRate = 6;
        }
        if ($(".body-rate-star").children()[3].className == "review-add-stars-hover") {
            comment.gmRate = 8;
        }
        if ($(".body-rate-star").children()[4].className == "review-add-stars-hover") {
            comment.gmRate = 10;
        }
        console.log(comment);
        $.ajax({
            type: "post",
            url: path + "/addComment",
            data: comment,
            success: function (back) {
                location.reload();
//                    console.log(back);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");

            }
        });
    });


    //初始化星星样式
    $(".review-add-stars p i").hover(function () {
        var index = $(this).index();
        $(this).parent().children().removeClass("review-add-stars-hover");
        $(this).prevAll().addClass("review-add-stars-hover");
        $(this).addClass("review-add-stars-hover");
        var that = $(".body-rate-star i")[index];
        $(that).parent().children().removeClass("review-add-stars-hover");
        $(that).prevAll().addClass("review-add-stars-hover");
        $(that).addClass("review-add-stars-hover");
        if (index == 0) {
            $(".review-add-stars span").text("讨厌");
            $(".body-rate-text").text("讨厌");
        } else if (index == 1) {
            $(".review-add-stars span").text("不喜欢");
            $(".body-rate-text").text("不喜欢");
        } else if (index == 2) {
            $(".review-add-stars span").text("还可以");
            $(".body-rate-text").text("还可以");
        } else if (index == 3) {
            $(".review-add-stars span").text("很不错")
            $(".body-rate-text").text("很不错");
        } else if (index == 4) {
            $(".review-add-stars span").text("棒极了");
            $(".body-rate-text").text("棒极了");
        }
    });

    $(".review-add-stars p i").click(function () {
        $('#comment-modal').modal("show");
    });
    $(".comment-btn-reply").click(function () {
        $('#reply-modal').modal("show");
        var path = $(this).parent().parent().parent().parent().prev().children()[0].src;
        var name = $(this).parent().parent().parent().parent().children()[0].childNodes[1].childNodes[1].innerText;
        var commentId = $(this).parent().parent().parent().parent().children()[0].childNodes[1].childNodes[3].innerText;
        $(".reply-img-right").attr('src', path);
        $(".reply-p-right").text(name);
        $("#comment-id").html(commentId);
    });
    $(".reply-btn-reply").click(function () {
        $('#reply-modal').modal("show");
        var path = $(this).parent().parent().parent().parent().prev().children()[0].src;
        var name = $(this).parent().parent().parent().parent().children()[0].childNodes[1].childNodes[0].innerText;
        var commentId = $(this).parent().parent().parent().parent().parent().parent().parent().parent().children()[0].childNodes[1].childNodes[3].innerText;
        $("#comment-id").html(commentId);
        $(".reply-img-right").attr('src', path);
        $(".reply-p-right").text(name);
    });
//        $(".review-add-stars p i").mouseleave(function () {
//            $(this).parent().children().removeClass("review-add-stars-hover");
//            $(".review-add-stars span").text("");
//        });

    $(".body-rate-star i").click(function () {
        var index = $(this).index();
        $(this).parent().children().removeClass("review-add-stars-hover");
        $(this).prevAll().addClass("review-add-stars-hover");
        $(this).addClass("review-add-stars-hover");
        var that = $(".review-add-stars p i")[index];
        $(that).parent().children().removeClass("review-add-stars-hover");
        $(that).prevAll().addClass("review-add-stars-hover");
        $(that).addClass("review-add-stars-hover");
        if (index == 0) {
            $(".review-add-stars span").text("讨厌");
            $(".body-rate-text").text("讨厌");
        } else if (index == 1) {
            $(".review-add-stars span").text("不喜欢");
            $(".body-rate-text").text("不喜欢");
        } else if (index == 2) {
            $(".review-add-stars span").text("还可以");
            $(".body-rate-text").text("还可以");
        } else if (index == 3) {
            $(".review-add-stars span").text("很不错")
            $(".body-rate-text").text("很不错");
        } else if (index == 4) {
            $(".review-add-stars span").text("棒极了");
            $(".body-rate-text").text("棒极了");
        }
    });

//        $(".body-rate-star i").mouseleave(function () {
//            $(this).prevAll().removeClass("review-add-stars-hover");
//            $(this).removeClass("review-add-stars-hover");
//            $(".body-rate-text").text("");
//        });
});