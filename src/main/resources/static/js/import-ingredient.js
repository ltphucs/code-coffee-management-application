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
            {data: "ingredient.name", name: "ingredient.name", title: "Tên nguyên liệu", orderable: false},
            {data: "dateJoin",name:"dateJoin",title: "Ngày nhập" },
            {data: "price", name: "price", title: "Giá"},
            {data: "quantity", name: "quantity", title: "Số lượng"},
            {data: "ingredient.unit.name", name: "ingredient.unit.name", title: "Đơn vị", orderable: false},
            {data: "totalPrice", name: "totalPrice", title: "Tổng Giá"},
            {data: "comment", name: "comment", title: "Ghi chú"},
            {
                data: "id", name: "action", title: "Chức năng",
                "render": function (data, type, row, meta) {
                    return "<a href='javascript:;' title='edit product'><i class='fa fa-edit'></i></a> " +
                        "<a href='javascript:;' title='remove product' ><i class='fa fa-trash'></i></a>"
                }
            },
        ],
        order: [[1, "desc"]]
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

// add and save

importIngredient.save = function(){
    if ($('#id').val() == 0){
        let importIngredients = {};
        importIngredients.price = Number($('#price').val());
        importIngredients.quantity = Number($('#quantity').val());
        importIngredients.totalPrice =Number(importIngredients.price * importIngredients.quantity);
        importIngredients.comment = $('#comment').val();

        var ingredientObj = {};
        ingredientObj.id = Number($("#nameIngredients").val());
        ingredientObj.name = $("#nameIngredients option:selected").html();
        importIngredients.ingredient = ingredientObj;

        $.ajax({
            url : "http://localhost:8080/api/importIngredients",
            method : "POST",
            dataType : "json",
            contentType : "application/json",
            data : JSON.stringify(importIngredients),
            done: function(){
                console.log("POST DONE");
                $('#modalAddEdit').modal('hide');
                $("#import-ingredients-datatables").DataTable().ajax.reload();
            },
            success : function(data){
                $('#modalAddEdit').modal('hide');
                $("#import-ingredients-datatables").DataTable().ajax.reload();

            }
        });
    }
    else {
        let importIngredients = {};
        importIngredients.price = Number($('#price').val());
        importIngredients.quantity = Number($('#quantity').val());

    }
}


$(document).ready(function () {
    importIngredient.initImportIngredients();
    importIngredient.initListIngredients();
});
