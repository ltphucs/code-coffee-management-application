let importProducts = {};
let product = {};

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
                    return "<a href='javascript:;' title='edit product' onclick='importProducts.get(" + data + ")'><i class='fa fa-edit'></i></a> " +
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

importProducts.initProduct = function (data) {
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

function setStatus(inventory, product) {
    if (inventory < 1) {
        return product.productStatus = 'OUT_OF_STOCK';
    } else {
        return product.productStatus = 'STOCKING';
    }
}

function updateProduct(product, id) {
    $.ajax({
        url: "http://localhost:8080/api/products/" + id,
        method: "PUT",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(product),
        success: function () {
            console.log("update product success");
        },
        error: function (mess) {
            console.log(mess.responseJSON);
        }
    });

}

importProducts.save = function () {
    if ($('#formAddEdit')) {
        if (!$('#id').val()) {
            $.ajax({
                url: "http://localhost:8080/api/products/" + Number($("#products").val()),
                method: "GET",
                dataType: "json",
                success: function (data) {
                    let importProductObj = {};
                    importProductObj.quantity = Number($('#quantity').val());
                    importProductObj.price = Number($('#price').val());
                    importProductObj.totalPrice = importProductObj.quantity * importProductObj.price;
                    importProductObj.comment = $('#comment').val();

                    let productObj = {};
                    productObj.id = Number($("#products").val());
                    productObj.name = $("#products option:selected").html();
                    productObj.inventory = data.product.inventory;
                    productObj.inventory += Number(importProductObj.quantity);
                    productObj.productStatus = setStatus(productObj.inventory, productObj);
                    productObj.price = data.product.price;

                    let productLine = {};
                    productLine.id = data.product.productLine.id;
                    productLine.name = data.product.productLine.name;
                    productObj.productLine = productLine;
                    productObj.image = data.product.image;
                    importProductObj.product = productObj;

                    $.ajax({
                        url: "http://localhost:8080/api/importProducts/",
                        method: "POST",
                        dataType: "json",
                        contentType: "application/json",
                        data: JSON.stringify(importProductObj),
                        success: function () {
                            updateProduct(productObj, productObj.id);
                            toastr.success("Nhập sản phẩm thành công");
                            $('#modalAddEdit').modal('hide');
                            $("#importProducts-datatables").DataTable().ajax.reload();

                        },
                        error: function () {
                            console.log("Toang create import product ");
                        }
                    });
                }
            });

        } else {
            $.ajax({
                url: "http://localhost:8080/api/products/" + Number($("#products").val()),
                method: "GET",
                dataType: "json",
                success: function (data) {
                    let importProductObj = {};
                    importProductObj.quantity = Number($('#quantity').val());
                    importProductObj.price = Number($('#price').val());
                    importProductObj.totalPrice = importProductObj.quantity * importProductObj.price;
                    importProductObj.comment = $('#comment').val();
                    importProductObj.id = Number($('#id').val());

                    let productObj = {};
                    productObj.id = Number($("#products").val());
                    productObj.name = $("#products option:selected").html();
                    productObj.inventory = data.product.inventory;
                    productObj.inventory += Number(importProductObj.quantity);
                    productObj.productStatus = setStatus(productObj.inventory, productObj);
                    productObj.price = data.product.price;

                    let productLine = {};
                    productLine.id = data.product.productLine.id;
                    productLine.name = data.product.productLine.name;
                    productObj.productLine = productLine;
                    productObj.image = data.product.image;
                    importProductObj.product = productObj;

                    $.ajax({
                        url: "http://localhost:8080/api/importProducts/" + importProductObj.id,
                        method: "PUT",
                        dataType: "json",
                        contentType: "application/json",
                        data: JSON.stringify(importProductObj),
                        success: function () {
                            updateProduct(productObj, productObj.id);
                            toastr.success(`Cập nhật "nhập sản phẩm" thành công`);
                            $('#modalAddEdit').modal('hide');
                            $("#importProducts-datatables").DataTable().ajax.reload();
                        },
                        error: function () {
                            console.log("Toang create import product ");
                        }
                    });
                }
            });
        }
    }
}

function deleteImportProduct(id) {
    $.ajax({
        url: "http://localhost:8080/api/importProducts/" + id,
        method: "DELETE",
        dataType: "json",
        success: function (data) {
            console.log(data);
            toastr.success(`Xóa "nhập sản phẩm" thành công`);
            $('#modalAddEdit').modal('hide');
            $("#importProducts-datatables").DataTable().ajax.reload();
        }
    })
}

importProducts.delete = function (id) {
    bootbox.confirm({
        title: "Xóa sản phẩm",
        message: "Bạn có muốn xóa đơn nhập hàng này?",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> No'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Yes'
            }
        },
        callback: function (result) {
            if (result) {
                $.ajax({
                    url: `http://localhost:8080/api/importProducts/${id}`,
                    method: "GET",
                    dataType: "json",
                    success: function (data) {
                        let productObj = data.product;
                        productObj.inventory -= data.quantity;
                        if (productObj.inventory < 0)
                            productObj.inventory = 0;
                        productObj.productStatus = setStatus(productObj.inventory, productObj);
                        // productObj.id = Number(data.product.id)
                        // productObj.name = $("#products option:selected").html();
                        // productObj.inventory = data.product.inventory;
                        // productObj.inventory -= Number(importProductObj.quantity);
                        // productObj.productStatus = setStatus(productObj.inventory, productObj);
                        // productObj.price = data.product.price;
                        //
                        // let productLine = {};
                        // productLine.id = data.product.productLine.id;
                        // productLine.name = data.product.productLine.name;
                        // productObj.productLine = productLine;
                        // productObj.image = data.product.image;
                        // importProductObj.product = productObj;
                        updateProduct(productObj, productObj.id);
                        console.log("update done");
                        deleteImportProduct(id);
                    }
                })
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