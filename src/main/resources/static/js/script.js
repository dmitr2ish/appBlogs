$(function () {
    updateTable();
    $('#savePost').on('click', event => addPost(event));
})

function updateTable() {
    $('#posts').empty();
    $('#next').empty();
    let currentId = 0;
    $.ajax({
        type: 'GET',
        url: '/api/post/first/',
        error: function () {
            $('#posts')
                .append(
                    "<p>" + "В данном блоге постов нет. Добавьте новый пост." + "</p>"
                )
        },
        success: function (postList) {
            postList.forEach(post =>
                $('#posts')
                    .append(
                        "<div class=\"card\">" +
                        "<div class=\"card-body\" >" +
                        "<h5 class=\"card-title\" style='display: none'>" +
                        (currentId = post.id) +
                        "</h5>" +

                        "<h6 class=\"card-subtitle mb-2 text-muted\">" +
                        post.date + "/" + post.time +
                        "</h6>" +

                        "<p class=\"card-text\">" +
                        post.content +
                        "</p>" +
                        "</div>" +
                        "</div>"))

            $('#next').append(
                "<button type=\"button\" class=\"btn btn-secondary\" onclick=\"updateNextTable(" + currentId + ")\">" +
                "Далее" +
                "</button>"
            )
        }
    });
}

function updateNextTable(id) {
    $('#posts').empty();
    $('#next').empty();

    let currentId = 0;
    $.ajax({
        type: 'GET',
        url: '/api/post/next/' + id,
        error: function () {
            alert("Постов больше нет, вы будете перенаправлены в начало списка");
            location.reload();
        },
        success: function (postList) {
            postList.forEach(post =>
                $('#posts')
                    .append(
                        "<div class=\"card\">" +
                        "<div class=\"card-body\" >" +
                        "<h5 class=\"card-title\" style='display: none'>" +
                        (currentId = post.id) +
                        "</h5>" +

                        "<h6 class=\"card-subtitle mb-2 text-muted\">" +
                        post.date + "/" + post.time +
                        "</h6>" +

                        "<p class=\"card-text\">" +
                        post.content +
                        "</p>" +
                        "</div>" +
                        "</div>"))

            $('#next').append(
                "<button type=\"button\" class=\"btn btn-secondary\" onclick=\"updateNextTable(" + currentId + ")\">" +
                "Далее" +
                "</button>"
            )
        }
    });
}

function addPost(e) {
    let str = getVal();
    const checkSpace = str.trim() == '';
    const checkMaxSize = str.toString().length > 200;

    if (checkSpace){
        alert("Содержание пусто");
    } else if (checkMaxSize) {
        alert("Вы превысили 200 символов в содержании");
    } else {
        e.preventDefault();

        let post = {
            content: $('#addContent').val()
        }

        $.ajax({
            url: '/api/post/add',
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(post),
            complete: function () {
                $('#newPostModal').modal('hide');
                updateTable();
            }
        })
    }
}

function removeDataBase() {
    $.ajax({
        url: '/api/post/delete',
        method: 'DELETE',
        complete: function () {
            updateTable();
        }
    })
}

//Getting value from textarea
function getVal(){
    return document.getElementById('addContent').value;
}