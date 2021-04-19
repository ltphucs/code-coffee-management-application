var importIngredients = {};

importIngredients.initIngrements = function () {
    $("#importIngredients-datatables").DataTable({
        ajax: {
            url: "http://localhost:8080/api/importIngredients",
            method: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {data: "ingredient.name", name: "ingredient.name", title: "Tên nguyên liệu", orderable: true},
            {data: "dateJoin",name:"dateJoin",title: "Ngày nhập"},
            {data: "price", name: "price", title: "Giá"},
            {data: "quantity", name: "quantity", title: "Số lượng"},
            {data: "ingredient.unit.name", name: "ingredient.unit.name", title: "Đơn vị"},
            {data: "totalPrice", name: "totalPrice", title: "Tổng Giá"},
            {data: "comment", name: "comment", title: "Ghi chú"},
            {
                data: "id", name: "action", title: "Chức năng", orderable: false,
                "render": function (data, type, row, meta) {
                    var str = "<a href='javascript:;' title='edit product'><i class='fa fa-edit'></i></a> " +
                        "<a href='javascript:;' title='remove product' ><i class='fa fa-trash'></i></a>"
                    return str;
                }
            },
        ]
    });

}

importIngredients.addNew = function () {
    $('#modalTitle').html("Add new ingredient");
    importIngredients.resetForm();
    $('#modalAddEdit').modal('show');
};

importIngredients.resetForm = function () {
    $('#formAddEditIngredients')[0].reset();
    $('#unit.name').val('');

}

importIngredients.initListIngeredients = function () {
    $.ajax({
        url: "http://localhost:8080/api/ingredients",
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#nameIngredients').empty();
            console.log(data)
            $.each(data, function (i, v) {
                $('#nameIngredients').append(
                    `<option value='${v.id}'>${v.name}</option>`
                );
            });
        }
    });
};

importIngredients.save = function () {
    let id = parseInt($('#id').val());
    if ($("#formAddEditIngredients")) {
        if (id == 0) {
            let productObj = {};
            console.log(productObj);
            productObj.id = $('#nameIngredients').val();
            productObj.price = $('#price').val();
            productObj.quantity = $('#quantity').val();
            productObj.comment = $('#comment').val();

            $.ajax({
                url: "http://localhost:8080/api/ingredients/",
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(productObj),
                done: function () {
                    console.log("POST DONE");
                    $('#modalAddEdit').modal('hide');

                    $("#importIngredients-datatables").DataTable().ajax.reload();
                },
                success: function (data) {
                    console.log("POST success");
                    $('#formAddEditIngredients').modal('hide');
                    $("#importIngredients-datatables").DataTable().ajax.reload();
                }
            });
        } else {
            var productObj = {};
            productObj.name = $('#nameIngredients').val();

            //
            var productLineObj = {};
            productLineObj.id = $("#formAddEditIngredients").val();
            productLineObj.name = $("#formAddEditIngredients option:selected").html();
            productObj.productLine = productLineObj;

            $.ajax({
                url: "http://localhost:8080/api/ingredients/" + productObj.id,
                method: "PUT",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(productObj),
                success: function (data) {
                    $('#formAddEditIngredients  ').modal('hide');
                    $("#importIngredients-datatables").DataTable().ajax.reload();
                }
            });
        }
    }

//
}
importIngredients.init = function () {
    importIngredients.initIngrements();
    importIngredients.initListIngeredients();
    importIngredients.initListUnit();
};
$(document).ready(function () {
    importIngredients.init();
});
//