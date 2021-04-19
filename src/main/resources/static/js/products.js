var products = {};

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
                    var str = "<a href='javascript:;' title='edit product' onclick='products.get("+ data +")'><i class='fa fa-edit'></i></a> " +
                        "<a href='javascript:;' title='remove product' onclick='products.delete("+ data +")' ><i class='fa fa-trash'></i></a>"
                    return str;
                }
            },
        ]
    });

}

products.addNew = function () {
    $('#modalTitle').html("Add new product");
    products.resetForm();
    $('#modalAddEdit').modal('show');
};

<<<<<<< HEAD
// products.initValidation = function () {
//     $("#modalAddEdit").validate({
//         rules: {
//             name: "required",
//             productLine: "required"
//         },
//         messages: {
//             productName: "Please enter your productName",
//             productLine: "Please enter your productLine",
//         }
//     });
//
// }

// products.resetForm = function () {
//     $('#formAddEdit')[0].reset();
//     $('#name').val('');
//     $('#inventory').val('');
//     $('#price').val('');
//     $('#productLine.name').val('');
//     $('#productStatus').val('');
//     //
//     var validator = $("#formAddEdit").validate();
//     validator.resetForm();
// }

products.initProductLines = function () {
    $.ajax({
        url: "http://localhost:8080/api/productLines",
=======
products.resetForm = function () {
    $('#formAddEdit')[0].reset();
    $('#name').val('');
    $('#inventory').val('');
    $('#price').val('');
    $('#productLine.name').val('');

    //
    // var validator = $("#formAddEdit");
    // validator.resetForm();
}

products.initProductLines = function () {
    $.ajax({
        url: "http://localhost:8080/api/productLines/",
>>>>>>> 799e6fc6d0916d25513c31ae756b4e64c7e0c3b0
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
            $('#modalTitle').html("Edit product");
            $('#productName').val(data.name);
            $('#inventory').val(data.inventory);
            $('#price').val(data.price);
            $('#image').val(data.image);
            $('#productLine').val(data.productLine.id);
            $('#id').val(data.id);

            $('#modalAddEdit').modal('show');
        }
    });
};

<<<<<<< HEAD
// products.save = function () {
//     if ($("#formAddEdit").valid()) {
//         if ($('#id').val() == 0) {
//             var productObj = {};
//             productObj.name = $('#productName').val();
//             productObj.inventory = $('#inventory').val();
//             productObj.price = $('#price').val();
//             productObj.image = $('#image').val();
//             //
//             var productLineObj = {};
//             productLineObj.id = $("#productLine").val();
//             productLineObj.name = $("#productLine option:selected").html();
//             productObj.productLine = productLineObj;
//
//             $.ajax({
//                 url: "http://localhost:8080/api/products/",
//                 method: "POST",
//                 dataType: "json",
//                 contentType: "application/json",
//                 data: JSON.stringify(productObj),
//                 done: function () {
//                     console.log("POST DONE");
//                     $('#modalAddEdit').modal('hide');
//
//                     $("#products-datatables").DataTable().ajax.reload();
//                 },
//                 success: function (data) {
//                     console.log("POST success");
//                     $('#modalAddEdit').modal('hide');
//                     $("#products-datatables").DataTable().ajax.reload();
//
//                 }
//             });
//         } else {
//             var productObj = {};
//             productObj.name = $('#productName').val();
//             productObj.inventory = $('#inventory').val();
//             productObj.price = $('#price').val();
//             productObj.image = $('#image').val();
//             productObj.id = $('#id').val();
//             var productLineObj = {};
//             productLineObj.id = $("#productLine").val();
//             productLineObj.name = $("#productLine option:selected").html();
//             productObj.productLine = productLineObj;
//
//             $.ajax({
//                 url: "http://localhost:8080/api/products/" + productObj.id,
//                 method: "PUT",
//                 dataType: "json",
//                 contentType: "application/json",
//                 data: JSON.stringify(productObj),
//                 success: function (data) {
//                     $('#modalAddEdit').modal('hide');
//                     $("#products-datatables").DataTable().ajax.reload();
//                 }
//             });
//         }
//     }
// };
=======
products.delete = function(id){
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
            if(result){
                $.ajax({
                    url : "http://localhost:8080/api/products/" + id,
                    method: "DELETE",
                    dataType : "json",
                    success : function(data){
                        products.initProductTable();
                    }
                });
            }
        }
    });
};

products.save = function () {
    if ($("#formAddEdit")) {
        if (!$('#id').val()) {
            var productObj = {};
            productObj.name = $('#productName').val();
            productObj.inventory = $('#inventory').val();
            productObj.price = $('#price').val();
            productObj.image = $('#image').val();


            //
            var productLineObj = {};
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
            var productObj = {};
            productObj.name = $('#productName').val();
            productObj.inventory = $('#inventory').val();
            productObj.price = $('#price').val();
            productObj.image = $('#image').val();
            productObj.id = $('#id').val();
            var productLineObj = {};
            productLineObj.id = $("#productLine").val();
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
                }
            });
        }
    }
};
>>>>>>> 799e6fc6d0916d25513c31ae756b4e64c7e0c3b0

products.init = function () {
    products.initProductTable();
    products.initProductLines();
    // products.initValidation();
};

$(document).ready(function () {
    products.init();

});