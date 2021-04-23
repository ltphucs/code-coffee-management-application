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
                    return "<a class='mr-2' href='javascript:;' title='Chỉnh sửa' onclick='products.get(" + data + ")'><i class='fa fa-edit'></i></a> " +
                        "<a class='mr-2' href='javascript:;' title='Xóa' onclick='products.delete(" + data + ")' ><i class='fa fa-trash'></i></a>"+
                       "<a class='mr-2' href='javascript:;' title='Thông tin chi tiết' onclick='products.viewTable(" + data + ")' ><i class='fas fa-eye'></i></a>"
                }
            },
        ],
    });
}

products.viewTable = function (id){
    $.ajax({
        url: "http://localhost:8080/api/products/" + id,
        method: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $('#formView')[0].reset();
            $('#modalViewTitle').html("Chi tiết sản phẩm");
            $('#productNameView').val(data.product.name);
            $('#imageView').val(data.product.image);
            $('#inventoryView').val(data.product.inventory);
            $('#priceView').val(data.product.price);
            $('#productLineView').val(data.product.productLine.name);
            $('#productStatusView').val(data.product.productStatus);
            $('#idProduct').val(data.product.id);
            $('#modalView').modal('show');
        }
    });
}

products.showView = function (){
    $('#modalViewTitle').html("Chi tiết sản phẩm");
    products.resetForm()
    $('#modalView').modal('show')
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
            $('#inventory').val(data.product.inventory);
            $('#price').val(data.product.price);
            $('#image').val(data.product.image);
            $('#multiImage').val(data.product.multiImage);
            $('#productLine').val(data.product.productLine.id);
            $('#productStatus').val(data.product.productStatus);
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
            productObj.price = parseInt($('#price').val());
            productObj.image = $('#image').val();
            productObj.multiImage = $('#multiImage').val();
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
                success: function (data) {
                    console.log(data);
                    $('#modalAddEdit').modal('hide');
                    $("#products-datatables").DataTable().ajax.reload();

                }
            });
        } else {
            $.ajax({
                url: `http://localhost:8080/api/products/${$('#id').val()}`,
                method: "GET",
                dataType: "json",
                success: function (data){
                    let productObj = {};
                    productObj.name = $('#productName').val();
                    productObj.inventory = data.product.inventory;
                    productObj.price = Number($('#price').val());
                    productObj.image = $('#image').val();
                    productObj.multiImage = $('#multiImage').val();
                    productObj.productStatus = setStatus(productObj.inventory,productObj);
                    productObj.id = $('#id').val();

                    let productLineObj = {};
                    productLineObj.id = Number($("#productLine").val());
                    productLineObj.name = $("#productLine option:selected").html();
                    productObj.productLine = productLineObj;

                    $.ajax({
                        url: "http://localhost:8080/api/products/" + productObj.id,
                        method: "PUT",
                        dataType: "json",
                        contentType: "application/json",
                        data: JSON.stringify(productObj),
                        success: function (data) {
                            $('#modalAddEdit').modal('hide');
                            $("#products-datatables").DataTable().ajax.reload();
                        },
                        error: function (err){
                            console.log(err.responseJSON);
                        }

                    });
                }
            })

        }
    }
};

products.init = function () {
    products.initProductTable();
    products.initProductLines();

};


$(document).ready(function () {
    products.init();
    products.viewTable();
});