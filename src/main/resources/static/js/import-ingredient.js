let importIngredient = {};

importIngredient.initImportIngredients = function () {
    $("#import-ingredients-datatables").DataTable({
        ajax: {
            url: "http://localhost:8080/api/importIngredients",
            method: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {data: "ingredient.name", name: "ingredient.name", title: "Tên nguyên liệu", orderable: true},
            {data: "dateJoin",name:"dateJoin",title: "Ngày nhập"},
            {data: "price" , name: "price", title: "Giá"},
            {data: "quantity", name: "quantity", title: "Số lượng"},
            {data: "ingredient.unit.name", name: "ingredient.unit.name", title: "Đơn vị"},
            {data: "totalPrice", name: "totalPrice", title: "Tổng Giá"},
            {data: "comment", name: "comment", title: "Ghi chú"},
            {
                data: "id", name: "action", title: "Chức năng", orderable: false,
                "render": function (data, type, row, meta) {
                    return "<a href='javascript:;' title='Chỉnh sửa nguyên liệu' onclick='importIngredient.get("+ data +")'><i class='fa fa-edit'></i></a> " +
                        "<a href='javascript:;' title='remove product' onclick='importIngredient.delete("+ data +")' ><i class='fa fa-trash'></i></a>"
                }
            },
        ],
        // moment("dateJoin").fomat;
        order: [[1, "desc"]],
    });
}


// show modal
importIngredient.addNew = function(){
    $('#modalTitle').html("Thêm mới nguyên liệu")
    $('#modalAddEdit').modal('show');
};

// reset form
importIngredient.resetForm =  function(){
    $('#formAddEditIngredients')[0].reset();
    $('#nameIngredients').val('');
    $('#productCode').val('');
    $('#price').val('');
    $('#quantity').val('');
    $('#comment').val('');
}

//show list select option
importIngredient.initListIngredients = function(){
    $.ajax({
        url : "http://localhost:8080/api/ingredients",
        method : "GET",
        dataType : "json",
        success : function(data){
            $('#nameIngredients').empty();
            $.each(data, function(i, v){
                $('#nameIngredients').append(
                    "<option value='"+ v.id +"'>"+ v.name +"</option>"
                );
            });
        }
    });
}

// lấy id

importIngredient.get = function (id) {
    $.ajax({
        url: "http://localhost:8080/api/importIngredients/" + id,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#formAddEditIngredients')[0].reset();
            $('#modalTitle').html("Chỉnh sửa nguyên liệu");
            $('#nameIngredients').val(data.ingredient.id);
            $('#price').val(data.price);
            $('#quantity').val(data.quantity);
            $('#comment').val(data.comment);
            $('#id').val(data.id);
            $('#modalAddEdit').modal('show');
        }
    });
};

// add and save

importIngredient.save = function(){
    if ($('#formAddEditIngredients')){
        if (!$('#id').val()){
            let importIngredients = {};
            importIngredients.price = Number($('#price').val());
            importIngredients.quantity = Number($('#quantity').val());
            importIngredients.totalPrice = Number(importIngredients.price * importIngredients.quantity);
            importIngredients.comment = $('#comment').val();

            let ingredientObj = {};
            ingredientObj.id = Number($("#nameIngredients").val());
            ingredientObj.name = $("#nameIngredients option:selected").html();
            importIngredients.ingredient = ingredientObj;

            $.ajax({
                url : "http://localhost:8080/api/importIngredients",
                method : "POST",
                dataType : "json",
                contentType : "application/json",
                data : JSON.stringify(importIngredients),

                success : function(data){
                    toastr.success("Thêm mới thành công");
                    $('#modalAddEdit').modal('hide');
                    $("#import-ingredients-datatables").DataTable().ajax.reload();

                },
                error: function (data){
                    // valid price
                    $('#err-price').html(data.responseJSON.price);
                    $('#err-quantity').html(data.responseJSON.quantity);

                }
            });
        }
        else {
            let importIngredients = {};
            importIngredients.price = Number($('#price').val());
            importIngredients.quantity = Number($('#quantity').val());
            importIngredients.totalPrice = Number(importIngredients.price * importIngredients.quantity);
            importIngredients.comment = $('#comment').val();
            importIngredients.id = Number($('#id').val());

            let ingredientObj = {};
            ingredientObj.id = Number($("#nameIngredients").val());
            ingredientObj.name = $("#nameIngredients option:selected").html();
            importIngredients.ingredient = ingredientObj;

            $.ajax({
                url: "http://localhost:8080/api/importIngredients/" + importIngredients.id,
                method: "PUT",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(importIngredients),
                success: function () {
                    toastr.success("Cập nhật thành công");
                    $('#modalAddEdit').modal('hide');
                    $("#import-ingredients-datatables").DataTable().ajax.reload();
                }
            });
        }
    }
}

// delete
importIngredient.delete = function(id){
    bootbox.confirm({
        title: "Xóa nguyên liệu",
        message: "Bạn có chắc chắn muốn xóa nguyên liệu này không ???",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> No'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Yes'
            }
        },
        callback: function (result) {
            if(result){
                $.ajax({
                    url : "http://localhost:8080/api/importIngredients/" + id,
                    method: "DELETE",
                    dataType : "json",
                    success : function(){
                        toastr.success("Xóa thành công");
                        $('#modalAddEdit').modal('hide');
                        $("#import-ingredients-datatables").DataTable().ajax.reload();
                    }
                });
            }
        }
    });
}




$(document).ready(function () {
    importIngredient.initImportIngredients();
    importIngredient.initListIngredients();
});
