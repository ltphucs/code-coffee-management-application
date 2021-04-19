var importProducts = {};

importProducts.initImportProductTable = function () {
    $("#importProducts-datatables").DataTable({
        ajax: {
            url: "http://localhost:8080/api/importProducts/",
            method: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {data: "product.name", name: "product.name", title: "Tên sản phẩm", orderable: true},
            {data: "dateJoin", name: "dateJoin", title: "Ngày nhập"},
            {data: "quantity", name: "quantity", title: "Số lượng"},
            {data: "price", name: "price", title: "Giá"},
            {data: "totalPrice", name: "totalPrice", title: "Tổng giá"},
            {
                data: "id", name: "action", title: "Chức năng", orderable: false,
                "render": function (data, type, row, meta) {
                    var str = "<a href='javascript:;' title='edit product' onclick='importProducts.get("+ data +")'><i class='fa fa-edit'></i></a> " +
                        "<a href='javascript:;' title='remove product' onclick='importProducts.get("+ data +")' ><i class='fa fa-trash'></i></a>"
                    return str;
                }
            },
        ]
    });
}

importProducts.addNew = function () {
    $('#modalTitle').html("Add new import");
    importProducts.resetForm();
    $('#modalAddEdit').modal('show');

};

importProducts.resetForm = function () {
    $('#formAddEdit')[0].reset();
    $('#name').val('');
    $('#dateJoin').val('');
    $('#quantity').val('');
    $('#price').val('');
    $('#totalPrice').val('');
    //

}

importProducts.initProducts= function () {
    $.ajax({
        url: "http://localhost:8080/api/products/",
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#products').empty();

            $.each(data.productList, function (i, v) {

                $('#products').append(
                    `<option value='${v.id}'>${v.name}</option>`
                );
            });
        }
    });
};

importProducts.get = function (id) {
    console.log('get :' + id);

    $.ajax({
        url: "http://localhost:8080/api/importProducts/" + id,
        method: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $('#formAddEdit')[0].reset();
            //
            $('#modalTitle').html("Edit ImportProduct");
            $('#productName').val(data.product.id);
            $('#dateJoin').val(data.dateJoin);
            $('#quantity').val(data.quantity);
            $('#price').val(data.price);
            $('#id').val(data.id);

            $('#modalAddEdit').modal('show');
        }
    });
};

importProducts.save = function () {
    if ($("#formAddEdit")) {
        if (!$('#id').val()) {
            var importProductObj = {};
            // importProductObj.dateJoin = $('#dateJoin').val();
            importProductObj.quantity = $('#quantity').val();
            importProductObj.price = $('#price').val();
            importProductObj.totalPrice = importProductObj.quantity * importProductObj.price;
            //
            var productObj = {};
            productObj.id = $("#products").val();
            productObj.name = $("#products option:selected").html();
            importProductObj.product = productObj;

            $.ajax({
                url: "http://localhost:8080/api/importProducts/",
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(importProductObj),
                done: function () {
                    console.log("POST DONE");
                    $('#modalAddEdit').modal('hide');
                    $("#products-datatables").DataTable().ajax.reload();
                },
                success: function (data) {
                    console.log("POST success");
                    $('#modalAddEdit').modal('hide');
                    $("#products-datatables").DataTable().ajax.reload();

                }
            });
        } else {
            var importProductObj = {};
            importProductObj.dateJoin = $('#dateJoin').val();
            importProductObj.quantity = $('#quantity').val();
            importProductObj.price = $('#price').val();
            importProductObj.totalPrice = $('#totalPrice').val();
            importProductObj.id = $('#id').val();

            var productObj = {};
            productObj.id = $("#products").val();
            productObj.name = $("#products option:selected").html();
            importProductObj.product = productObj;

            $.ajax({
                url: "http://localhost:8080/api/importProducts/" + importProductObj.id,
                method: "PUT",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(importProductObj),
                success: function (data) {
                    $('#modalAddEdit').modal('hide');
                    $("#products-datatables").DataTable().ajax.reload();
                }
            });
        }
    }
};

importProducts.init = function () {
    importProducts.initImportProductTable();
    importProducts.initProducts();

};

$(document).ready(function () {
    importProducts.init();
});