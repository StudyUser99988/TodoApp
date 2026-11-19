$(function(){
    $('.todo-done-checkbox').on('change', function() {

        const todoId = $(this).data('todo-id');
        const todoDone = $(this).prop('checked');

        $.ajax({
            url: '/todo/done',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                todoId: todoId,
                todoDone: todoDone
            }),
            success: function() {
                console.log('更新成功');
            },
            error: function() {
                alert('更新に失敗しました');
            }
        });

    });
})