let products = {};
let productLines = {};

products.initProductTable = function () {
    $("#products-datatables").DataTable({
        ajax: {
            url: "http://localhost:8080/api/products/",
            method: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {
                data: "name", name: "name", title: "Tên sản phẩm", orderable: true,
                "render": function (data, type, row) {
                    if (row.inventory < 11 && !row.ingredient) {
                        return `<span class="text-danger">${data}</span>`
                    } else return data;
                }
            },
            {
                data: "inventory", name: "inventory", title: "Số lượng trong kho",
                "render": function (data, type, row) {
                    if (data < 11 && !row.ingredient) {
                        return `<span class="text-danger">${data}</span>`
                    } else return data;
                }
            },
            {data: "price", name: "price", title: "Giá"},
            {data: "productLine.name", name: "productLine.name", title: "Dòng sản phẩm"},
            {
                data: "productStatus", name: "productStatus", title: "Trạng thái",
                "render": function (data, type, row) {
                    // console.log(row);
                    return products.renderProductStatusButton(data, row);
                }
            },
            {
                data: "id", name: "action", title: "Chức năng", orderable: false,
                "render": function (data, type, row, meta) {
                    return "<a class='mr-2' href='javascript:;' title='Chỉnh sửa' onclick='products.get(" + data + ")'><i class='fa fa-edit'></i></a> " +
                        "<a class='mr-2' href='javascript:;' title='Xóa' onclick='products.delete(" + data + ")' ><i class='fa fa-trash'></i></a>" +
                        "<a class='mr-2' href='javascript:;' title='Thông tin chi tiết' onclick='products.viewProduct(" + data + ")' ><i class='fas fa-eye'></i></a>"
                }
            },
        ],
        language: {
            "sProcessing": "Đang xử lý...",
            "sLengthMenu": "Xem _MENU_ mục",
            "sZeroRecords": "Không tìm thấy dòng nào phù hợp",
            "sInfo": "Đang xem _START_ đến _END_ trong tổng số _TOTAL_ mục",
            "sInfoEmpty": "Đang xem 0 đến 0 trong tổng số 0 mục",
            "sInfoFiltered": "(được lọc từ _MAX_ mục)",
            "sSearch": "Tìm kiếm:",
            "oPaginate": {
                "sFirst": "Đầu",
                "sPrevious": "Trước",
                "sNext": "Tiếp",
                "sLast": "Cuối"
            }
        }
    });
}


products.renderProductStatusButton = function (data, row) {
    if (data === "OUT_OF_STOCK") {
        return `<a href="javascript:;" class="btn btn-warning btn-icon-split" onclick="products.switchProductStatusButton(${row.id})">
                    <span class="icon text-white-50">
                        <i class="fas fa-exclamation-triangle"></i>
                    </span>
                    <span class="text">Hết hàng</span>
                </a>`;
    }
    if (data === "STOCKING") {
        return `<a href="javascript:;" class="btn btn-success btn-icon-split" onclick="products.switchProductStatusButton(${row.id})">
                    <span class="icon text-white-50">
                        <i class="fas fa-check"></i>
                    </span>
                    <span class="text">Còn hàng</span>
                </a>`;
    }
    if (data === "STOP_SELLING") {
        return `<a href="javascript:;" class="btn btn-danger btn-icon-split" onclick="products.switchProductStatusButton(${row.id})">
                    <span class="icon text-white-50">
                        <i class="fas fa-trash"></i>
                    </span>
                    <span class="text">Ngừng kinh doanh</span>
                </a>`;
    }
}

products.switchProductStatusButton = function (id) {
    $.ajax({
        url: `http://localhost:8080/api/products/${id}`,
        method: "GET",
        dataType: "json",
        success: function (data) {
            let productObj = data;
            switch (productObj.productStatus) {
                case `STOCKING`:
                    productObj.productStatus = `STOP_SELLING`;
                    break;
                case `STOP_SELLING`:
                    if (data.inventory > 0)
                        productObj.productStatus = `STOCKING`;
                    else productObj.productStatus = `OUT_OF_STOCK`;
                    break;
                case `OUT_OF_STOCK`:
                    if (data.inventory > 0)
                        productObj.productStatus = `STOCKING`;
                    else productObj.productStatus = `OUT_OF_STOCK`;
                    break;
            }
            products.updateProduct(productObj, productObj.id);
        },
        error: function (err) {
            console.log(err.responseJSON);
        }
    })

}


products.updateProduct = function (product, id) {
    $.ajax({
        url: `http://localhost:8080/api/products/${id}`,
        method: "PUT",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(product),
        success: function () {
            $("#products-datatables").DataTable().ajax.reload();
        },
        error: function (err) {
            console.log("err update product");
            console.log(err.responseJSON);
        }
    })
}

products.viewProduct = function (id) {
    $.ajax({
        url: "http://localhost:8080/api/products/" + id,
        method: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $('#formView')[0].reset();
            $('#modalViewTitle').html("Chi tiết sản phẩm");
            $('#productNameView').html(data.name);
            $('#imageView').html(
                `<img src="${data.image}" alt="Ảnh sản phẩm" style="max-width: 300px; max-height: 300px; width: 100%; height: 100%" class="img-thumbnail">`
            );
            $('#inventoryView').html(data.inventory);
            $('#priceView').html(data.price);
            $('#productLineView').html(data.productLine.name);
            $('#productIngredientView').html(products.renderIsIngredient(data));
            $('#modalView').modal('show');
        }
    })
}

products.renderProductStatus = function (status) {
    if (status === "OUT_OF_STOCK") {
        return `Hết hàng`;
    }
    if (status === "STOCKING") {
        return `Còn hàng`;
    }
    if (status === "STOP_SELLING") {
        return `Ngừng kinh doanh`;
    }
}


// datatable product line
products.innitProductLineTable = function () {
    $("#productLines-datatables").DataTable({
        ajax: {
            url: "http://localhost:8080/api/productLines",
            method: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {data: "name", name: "name", title: "Tên sản phẩm", orderable: true},
            {
                data: "id", name: "id", title: "Chức năng", orderable: false,
                "render": function (data, type, row, meta) {
                    return "<a href='javascript:;' title='edit product' onclick='productLines.get(" + data + ")'><i class='fa fa-edit'></i></a> "
                }
            },
        ],
        language: {
            "sProcessing": "Đang xử lý...",
            "sLengthMenu": "Xem _MENU_ mục",
            "sZeroRecords": "Không tìm thấy dòng nào phù hợp",
            "sInfo": "Đang xem _START_ đến _END_ trong tổng số _TOTAL_ mục",
            "sInfoEmpty": "Đang xem 0 đến 0 trong tổng số 0 mục",
            "sInfoFiltered": "(được lọc từ _MAX_ mục)",
            "sSearch": "Tìm kiếm:",
            "oPaginate": {
                "sFirst": "Đầu",
                "sPrevious": "Trước",
                "sNext": "Tiếp",
                "sLast": "Cuối"
            }
        }
    });
}

// table product line
productLines.listProductLine = function () {
    products.resetForm();
    $('#modalProductLine').modal('show')

    products.innitProductLineTable();
    $("#modalProductLine").on("shown.bs.modal", function (e) {
        $("#productLines-datatables")
            .DataTable()
            .columns.adjust()
            .responsive.recalc();
    });
}

products.showView = function () {
    $('#modalViewTitle').html("Chi tiết sản phẩm");
    products.resetForm()
    $('#modalView').modal('show')
}


// add new product line
productLines.addNewProductLine = function () {
    $('#formAddEditProductLines')[0].reset();
    $('#modalTitleProductLine').html("Thêm mới dòng sản phẩm");
    $('#modalAddEditProductLine').modal('show');
};


// add new product
products.addNew = function () {
    $('#formAddEdit')[0].reset();
    $('#modalTitle').html("Thêm sản phẩm mới");
    products.resetForm();
    $('#isIngredient').prop("disabled", false);
    $('#modalAddEdit').modal('show')
}

products.resetForm = function () {
    $('#formAddEdit')[0].reset();
    $('#multiImage').val('');
    $('#imgUrl').val('');
    $('#done-upload').empty();
    $('#productName').val('');
    $('#inventory').val('');
    $('#image').val('');
    $('#price').val('');
    $('#productLine.name').val('');

};

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


// delete product
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
    $.ajax({
        url: "http://localhost:8080/api/products/" + id,
        method: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $('#formAddEdit')[0].reset();
            $('#modalTitle').html("Sửa sản phẩm");
            $('#productName').val(data.name);
            $('#inventory').val(data.inventory);
            $('#done-upload').html(
                `<img src="${data.image}" style="max-width: 300px; max-height: 300px; width: 100%; height: 100%" alt="Ảnh sản phẩm" class="img-thumbnail">`
            );
            $('#price').val(data.price);
            $('#imgUrl').val(data.image);
            $('#multiImage').val(data.multiImage);
            $('#productLine').val(data.productLine.id);
            $('#productStatus').val(data.productStatus);
            $('#isIngredient option:selected').html(products.renderIsIngredient(data));
            $('#isIngredient').prop("disabled", true);
            $('#id').val(data.id);
            $("#save-btn").html(
                `<a href="javascript:;" class="btn btn-primary"
                               onclick="products.save()">Lưu</a>`
            );
            $('#modalAddEdit').modal('show');
        }
    });
};

products.renderIsIngredient = function (product) {
    if (product.ingredient) {
        return `Nguyên liệu pha chế`;
    } else return `Nguyên liệu thông thường`;
}

products.setProductStatus = function (inventory, product) {
    if (inventory < 1) {
        return product.productStatus = 'OUT_OF_STOCK';
    } else {
        return product.productStatus = 'STOCKING';
    }
}

products.saveImage = function () {
    let form = new FormData();
    form.append("file", $('#multiImage')[0].files[0]);
    $.ajax({
        url: "http://localhost:8080/api/upload",
        type: "POST",
        data: form,
        processData: false,
        contentType: false,
        success: function (data) {
            // products.resetForm();
            // $('#formAddEdit')[0].reset();
            $("#imgUrl").val(data.url);
            $("#done-upload").html(
                `<img src="${data.url}" alt="Ảnh sản phẩm" style="max-width: 300px; max-height: 300px; width: 100%; height: 100%" class="img-thumbnail">`
            );
            $("#save-btn").html(
                `<a href="javascript:;" class="btn btn-primary"
                               onclick="products.save()">Lưu</a>`
            );
        }
    });
}

products.setInventory = function (product) {
    if (product.ingredient) {
        return 1;
    } else if (!product.ingredient && product.inventory === 0) {
        return 0;
    } else
        return product.inventory;
}

products.save = function () {
    if ($("#formAddEdit")) {
        if (!$('#id').val()) {
            let productObj = {};
            productObj.name = $('#productName').val();
            productObj.price = Number($('#price').val());
            productObj.image = $('#imgUrl').val();
            productObj.ingredient = $('#isIngredient').val();
            productObj.inventory = 0;
            productObj.inventory = products.setInventory(productObj);
            productObj.productStatus = products.setProductStatus(productObj.inventory, productObj);

            let productLineObj = {};
            productLineObj.id = Number($('#productLine').val());
            productLineObj.name = $("#productLine option:selected").html();
            productObj.productLine = productLineObj;
            console.log(productObj);

            $.ajax({
                url: "http://localhost:8080/api/products",
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(productObj),
                success: function (data) {
                    toastr.success("Thêm mới thành công");
                    $('#modalAddEdit').modal('hide');
                    $("#products-datatables").DataTable().ajax.reload();
                },
                error: function () {
                    toastr.error("Lỗi thêm sản phẩm");
                }
            });
        } else {
            let productObj = {};
            productObj.id = Number($('#id').val());
            productObj.name = $('#productName').val();
            productObj.price = Number($('#price').val());
            productObj.image = $('#imgUrl').val();
            productObj.ingredient = $('#isIngredient').val();
            productObj.inventory = products.setInventory(productObj);
            productObj.productStatus = products.setProductStatus(productObj.inventory, productObj);

            let productLineObj = {};
            productLineObj.id = Number($('#productLine').val());
            productLineObj.name = $("#productLine option:selected").html();
            productObj.productLine = productLineObj;
            console.log(productObj);

            $.ajax({
                url: `http://localhost:8080/api/products/${productObj.id}`,
                method: "PUT",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(productObj),
                success: function (data) {
                    toastr.success("Cập nhật thành công")
                    $('#modalAddEdit').modal('hide');
                    $("#products-datatables").DataTable().ajax.reload();
                },
                error: function () {
                    console.log('loi update')
                }
            });
        }
    }
};


// lấy ra productline
productLines.get = function (idProdutLine) {
    $.ajax({
        url: "http://localhost:8080/api/productLines/" + idProdutLine,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#formAddEditProductLines')[0].reset();
            $('#modalTitleProductLine').html('Chỉnh sửa dòng sản phẩm');
            $('#name').val(data.name);
            $('#idProdutLine').val(data.id);
            $('#idProductline-2').val(data.id);
            $('#modalAddEditProductLine').modal('show');
        }
    });
}

// form add and save productline
productLines.save = function () {
    if ($('#idProductline-2').val()) {
        let productline = {};
        productline.name = $('#name').val();
        productline.id = Number($('#idProductline-2').val());

        $.ajax({
            url: "http://localhost:8080/api/productLines/" + productline.id,
            method: "PUT",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(productline),

            success: function (data) {
                toastr.success("Cập nhật thành công");
                $('#modalAddEditProductLine').modal('hide');
                $("#productLines-datatables").DataTable().ajax.reload();
            },
            error: function (data) {
                $('#err-nameProductline').html(data.responseJSON.name);
            }
        });
    } else {
        let productline = {};
        productline.name = $('#name').val();
        $.ajax({
            url: "http://localhost:8080/api/productLines",
            method: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(productline),

            success: function () {
                toastr.success("Thêm mới thành công");
                $('#modalAddEditProductLine').modal('hide');
                $("#productLines-datatables").DataTable().ajax.reload();
            },
            error: function (data) {
                $('#err-nameProductline').html(data.responseJSON.name);
            }

        });
    }
    // }
}


// delete dòng sản phẩm
productLines.delete = function (data) {
    bootbox.confirm({
        title: "Xóa dòng sản phẩm",
        message: "Bạn có chắc chắn muốn xóa dòng sản phẩm này không ???",
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
                    url: "http://localhost:8080/api/productLines/" + data,
                    method: "DELETE",
                    dataType: "json",
                    succes: function () {
                        toastr.success("Xóa thành công");
                        $('#modalAddEditProductLine').modal('hide');
                        $("#productLines-datatables").DataTable().ajax.reload();
                    }
                });
            }
        }
    });
}


products.init = function () {
    products.initProductTable();
    products.initProductLines();

};


$(document).ready(function () {
    products.init();
});