let products = {};
let productline = {};

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

// datatble product line
products.innitProductLineTable = function (){
    $("#productsline-datatables").DataTable({
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
                    return "<a href='javascript:;' title='edit product' onclick='productline.get("+ data +")'><i class='fa fa-edit'></i></a> "
                }
            },
        ],
    });
}

// table product line
products.listProductLine = function (){
    // products.resetForm();
    $('#modalProductLine').modal('show')

    products.innitProductLineTable();
    $("#modalProductLine").on("shown.bs.modal", function(e) {
        $("#unit-datatables")
            .DataTable()
            .columns.adjust()
            .responsive.recalc();
    });
}

// table list unit



// lấy ra productline
productline.get = function (idProdutLine){
    $.ajax({
       url: "http://localhost:8080/api/productLines/" + idProdutLine,
        method: "GET",
        dataType: "json",
        success: function (data){
            $('#formAddEditProductlines')[0].reset();
            $('#modalTitleProductLine').html('Chỉnh sửa dòng sản phẩm');
            $('#name').val(data.name);
            $('#idProdutLine').val(data.id);
            $('#idProductline-2').val(data.id);
            $('#modalAddEditProductLine').modal('show');
        }
    });
}

// form add and save productline
productline.save = function (){
        if ($('#idProductline-2').val()){
            let productline = {};
            productline.name = $('#name').val();
            productline.id = Number($('#idProductline-2').val());

            $.ajax({
               url: "http://localhost:8080/api/productLines/" + productline.id,
               method: "PUT",
               dataType: "json",
               contentType : "application/json",
               data: JSON.stringify(productline),

               success: function (data){
                   toastr.success("Cập nhật thành công");
                   $('#modalAddEditProductLine').modal('hide');
                   $("#productsline-datatables").DataTable().ajax.reload();
               },
                error: function (data){
                   $('#err-nameProductline').html(data.responseJSON.name);
                }
            });
        }
        else {
            let productline = {};
            productline.name = $('#name').val();
            $.ajax({
               url: "http://localhost:8080/api/productLines",
                method: "POST",
                dataType: "json",
                contentType : "application/json",
                data : JSON.stringify(productline),

                success: function (){
                    toastr.success("Thêm mới thành công");
                    $('#modalAddEditProductLine').modal('hide');
                    $("#productsline-datatables").DataTable().ajax.reload();
                },
                error: function (data){
                    $('#err-nameProductline').html(data.responseJSON.name);
                }

            });
        }
    // }
}


// delete dòng sản phẩm

productline.delete = function (data){
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
        callback: function (result){
           if (result){
               $.ajax({
                  url: "http://localhost:8080/api/productLines/" + data,
                   method: "DELETE",
                   dataType: "json",
                   succes: function (){
                       toastr.success("Xóa thành công");
                       $('#modalAddEditProductLine').modal('hide');
                       $("#productsline-datatables").DataTable().ajax.reload();
                   }
               });
           }
        }
    });
}

// add new product line
products.addNewProductLine = function(){
    $('#formAddEditProductlines')[0].reset();
    $('#modalTitleProductLine').html("Thêm mới dòng sản phẩm");
    $('#modalAddEditProductLine').modal('show');
};


// add new product
products.addNew = function () {
    $('#formAddEdit')[0].reset();
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

// select option productline
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