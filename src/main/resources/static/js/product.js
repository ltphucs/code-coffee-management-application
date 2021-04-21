let products = {};

products.initProductTable = function () {
    $("#products-datatables").DataTable({
        ajax: {
            url: "http://localhost:8080/api/products/",
            method: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {data: "name", name: "name", title: "Tên sản phẩm", orderable: true},
            {data: "inventory", name: "inventory", title: "Số lượng trong kho"},
            {data: "price", name: "price", title: "Giá"},
            {data: "productLine.name", name: "productLine.name", title: "Dòng sản phẩm"},
            {data: "productStatus", name: "productStatus", title: "Trạng thái"},
            {
                data: "id", name: "action", title: "Chức năng", orderable: false,
                "render": function (data, type, row, meta) {
                    return "<a href='javascript:;' title='edit product' onclick='products.get(" + data + ")'><i class='fa fa-edit'></i></a> " +
                        "<a href='javascript:;' title='remove product' onclick='products.delete(" + data + ")' ><i class='fa fa-trash'></i></a>"
                }
            },
        ],
    });
}

products.addNew = function () {
    $('#modalTitle').html("Thêm sản phẩm mới");
    products.resetForm();
    $('#modalAddEdit').modal('show')
}

products.resetForm = function () {
    $('#formAddEdit')[0].reset();
    $('#productName').val('');
    $('#inventory').val('');
    $('#image').val('');
    $('#price').val('');
    $('#productLine.name').val('');

}

products.initProductLines = function () {
    $.ajax({
        url: "http://localhost:8080/api/productLines/",
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#productLine').empty();
            $.each(data, function (i, v) {
                $('#productLine').append(
                    "<option value='" + v.id + "'>" + v.name + "</option>"
                );
            });
        }
    });
};

products.delete = function (id) {
    bootbox.confirm({
        title: "Xóa sản phẩm",
        message: "Bạn có muốn xóa sản phẩm này?",
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
            if (result) {
                $.ajax({
                    url: "http://localhost:8080/api/products/" + id,
                    method: "DELETE",
                    dataType: "json",

                    success: function (data) {
                        console.log("aaaaaaaa");
                        $('#modalAddEdit').modal('hide');
                        $("#products-datatables").DataTable().ajax.reload();
                        // importProducts.initImportProductTable();
                    }
                });
            }
        }
    });
};


products.get = function (id) {
    console.log('get :' + id);
    $.ajax({
        url: "http://localhost:8080/api/products/" + id,
        method: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $('#formAddEdit')[0].reset();
            //
            $('#modalTitle').html("Sửa sản phẩm");
            $('#productName').val(data.product.name);
            $('#price').val(data.product.price);
            $('#image').val(data.product.image);
            $('#productLine').val(data.product.productLine.id);
            $('#id').val(data.product.id);

            $('#modalAddEdit').modal('show');
        }
    });
};

function setStatus(inventory, product){
    if (inventory < 1){
        return product.productStatus = 'OUT_OF_STOCK';
    }
    else {
        return product.productStatus = 'STOCKING';
    }
}

products.save = function () {
    if ($("#formAddEdit")) {
        if (!$('#id').val()) {
            let productObj = {};
            productObj.name = $('#productName').val();
            // productObj.inventory = Number(0);
            productObj.price = Number($('#price').val());
            productObj.image = $('#image').val();
            // productObj.productStatus = setStatus(productObj.inventory, productObj);
            //
            let productLineObj = {};
            productLineObj.id = $("#productLine").val();
            productLineObj.name = $("#productLine option:selected").html();
            productObj.productLine = productLineObj;

            $.ajax({
                url: "http://localhost:8080/api/products/",
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(productObj),
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
            let productObj = {};
            productObj.name = $('#productName').val();
            productObj.price = Number($('#price').val());
            productObj.image = $('#image').val();
            productObj.id = $('#id').val();

            let productLineObj = {};
            productLineObj.id = Number($("#productLine").val());
            productLineObj.name = $("#productLine option:selected").html();
            productObj.productLine = productLineObj;

            let importProductObj = {};
            importProductObj.id = Number($("#importProduct").val());
            importProductObj.quantity = Number($("#importProduct").val());
            productObj.importProducts = importProductObj;

            productObj.inventory = Number($('#inventory').val()) + importProductObj.quantity;

            $.ajax({
                url: "http://localhost:8080/api/products/" + productObj.id,
                method: "PUT",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(productObj),
                success: function (data) {
                    $('#modalAddEdit').modal('hide');
                    $("#products-datatables").DataTable().ajax.reload();
                }
            });
        }
    }
};

products.init = function () {
    products.initProductTable();
    products.initProductLines();
};


$(document).ready(function () {
    products.init();
});