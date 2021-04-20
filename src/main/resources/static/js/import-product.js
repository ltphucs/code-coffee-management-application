let importProducts = {};

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
            {data: "comment", name: "comment", title: "ghi chú"},
            {
                data: "id", name: "action", title: "Chức năng", orderable: false,
                "render": function (data, type, row, meta) {
                    return "<a href='javascript:;' title='edit product' onclick='importProducts.get("+ data +")'><i class='fa fa-edit'></i></a> " +
                        "<a href='javascript:;' title='remove product' onclick='importProducts.delete(" + data + ")' ><i class='fa fa-trash'></i></a>"
                }
            },
        ]
    });
}

importProducts.addNew = function () {
    $('#modalTitle').html("Nhập sản phẩm");
    importProducts.resetForm();
    $('#modalAddEdit').modal('show');
}

importProducts.resetForm = function () {
    $('#formAddEdit')[0].reset();
    $('#product.name').val('');
    $('#quantity').val('');
    $('#price').val('');
    $('#totalPrice').val('');
    $('#comment').val('');
}

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
            $('#modalTitle').html("Edit product");
            $('#productName').val(data.product.id);
            $('#quantity').val(data.quantity);
            $('#price').val(data.price);
            $('#comment').val(data.comment);
            $('#id').val(data.id);
            $('#modalAddEdit').modal('show');
        }
    });
};

importProducts.initProduct = function () {
    $.ajax({
        url: "http://localhost:8080/api/products/",
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#products').empty();
            $.each(data, function (i, v) {
                $('#products').append(
                    "<option value='" + v.id + "'>" + v.name + "</option>"
                );
            })
        }
    })
}

importProducts.save = function () {
    if ($('#formAddEdit')) {
        if (!$('#id').val()) {
            let importProductObj = {};
            importProductObj.quantity = Number($('#quantity').val());
            importProductObj.price = Number($('#price').val());
            importProductObj.totalPrice = importProductObj.quantity * importProductObj.price;
            importProductObj.comment = $('#comment').val();

            let productObj = {};
            productObj.id = Number($("#products").val());
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
                    $("#importProducts-datatables").DataTable().ajax.reload();
                    // $("#importProducts-datatables").DataTable().ajax.reload();
                },
                success: function (data) {
                    console.log("POST success");
                    $('#modalAddEdit').modal('hide');
                    $("#importProducts-datatables").DataTable().ajax.reload();
                    // importProducts.initImportProductTable();
                }
            });
        } else {
            let importProductObj = {};
            importProductObj.quantity = Number($('#quantity').val());
            importProductObj.price = Number($('#price').val());
            importProductObj.totalPrice = importProductObj.quantity * importProductObj.price;
            importProductObj.comment = $('#comment').val();
            importProductObj.id = Number($('#id').val());

            let productObj = {};
            productObj.id =Number($("#products").val());
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
                    $("#importProducts-datatables").DataTable().ajax.reload();
                    // importProducts.initImportProductTable();
                }
            });
        }
    }
}

importProducts.delete = function(id){
    bootbox.confirm({
        title: "Remove product",
        message: "Do you want to remove this product?",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> No'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Yes'
            }
        },
        callback: function (result) {
            // console.log(id);
            if(result){
                $.ajax({
                    url : "http://localhost:8080/api/importProducts/" + id,
                    method: "DELETE",
                    dataType : "json",

                    success : function(data){
                        console.log("aaaaaaaa");
                        $('#modalAddEdit').modal('hide');
                        $("#importProducts-datatables").DataTable().ajax.reload();
                        // importProducts.initImportProductTable();
                    }
                });
            }
        }
    });
};

importProducts.init = function () {
    importProducts.initImportProductTable();
    importProducts.initProduct();
};

$(document).ready(function () {
    importProducts.init();
});