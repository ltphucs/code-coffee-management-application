let ingredients = {}
let units = {};

ingredients.initIngredients = function () {
    $("#ingredients-datatables").DataTable({
        ajax: {
            url: "http://localhost:8080/api/ingredients",
            method: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {data: "name", name: "name", title: "Tên nguyên liệu", orderable: true},
            {data: "unit.name",name:"unit.name",title: "Tên đơn vị"},
            {data: "comment" , name: "comment", title: "Ghi chú"},
            {
                data: "id", name: "action", title: "Chức năng", orderable: false,
                "render": function (data, type, row, meta) {
                    return "<a href='javascript:;' title='Chỉnh sửa nguyên liệu' onclick='ingredients.get("+ data +")'><i class='fa fa-edit'></i></a> " +
                        "<a href='javascript:;' title='remove product' onclick='ingredients.delete("+ data +")' ><i class='fa fa-trash'></i></a>"
                }
            },
        ],
        // moment("dateJoin").fomat;
        order: [[1, "desc"]],
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

// thêm nguyên liệu
ingredients.addNew = function(){
    $('#formSaveIngredients')[0].reset();
    $('#modalTitleIngredient').html("Thêm mới nguyên liệu")
    $('#modalSaveIngredient').modal('show');
};

// list unit
ingredients.initListUnit = function (){
    $.ajax({
        url : "http://localhost:8080/api/units",
        method : "GET",
        dataType : "json",
        success : function(data){
            $('#nameUnit').empty();
            $.each(data, function(i, v){
                $('#nameUnit').append(
                    "<option value='"+ v.id +"'>"+ v.name +"</option>"
                );
            });
        }
    });
}

// lấy id update

ingredients.get = function (id) {
    $.ajax({
        url: "http://localhost:8080/api/ingredients/" + id,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#formSaveIngredients')[0].reset();
            $('#modalTitleIngredient').html("Chỉnh sửa nguyên liệu");
            $('#nameIngredient').val(data.name);
            $('#price').val(data.unit.name);
            $('#comment').val(data.comment);
            $('#idIngredient').val(data.id);
            $('#modalSaveIngredient').modal('show');
        }
    });
};


// and and save ingredient
ingredients.save = function (){
    if ($('#modalSaveIngredient')){
        if (!$('#idIngredient').val()){
            let ingredient = {};
            ingredient.name = $('#nameIngredient').val();
            ingredient.comment = $('#comment'). val();

            let unitObj = {};
            unitObj.id = Number($('#nameUnit').val());
            unitObj.name = $("#nameUnit option:selected").html();
            ingredient.unit = unitObj;

            $.ajax({
                url : "http://localhost:8080/api/ingredients",
                method : "POST",
                dataType : "json",
                contentType : "application/json",
                data : JSON.stringify(ingredient),

                success : function(data){
                    toastr.success("Thêm mới thành công");
                    $('#modalSaveIngredient').modal('hide');
                    $("#ingredients-datatables").DataTable().ajax.reload();

                },
                error: function (data){
                    $('#err-nameIngredient').html(data.responseJSON.name);
                }
            });
        }
        else {
            let ingredient = {};
            ingredient.name = $('#nameIngredient').val();
            ingredient.comment = $('#comment'). val();
            ingredient.id = Number($('#idIngredient').val());

            let unitObj = {};
            unitObj.id = Number($('#nameUnit').val());
            unitObj.name = $("#nameUnit option:selected").html();
            ingredient.unit = unitObj;

            $.ajax({
                url : "http://localhost:8080/api/ingredients/" + ingredient.id,
                method : "PUT",
                dataType : "json",
                contentType : "application/json",
                data : JSON.stringify(ingredient),

                success : function(data){
                    toastr.success("Cập nhật thành công");
                    $('#modalSaveIngredient').modal('hide');
                    $("#ingredients-datatables").DataTable().ajax.reload();

                },
                error: function (data){
                    $('#err-nameIngredient').html(data.responseJSON.name);
                }
            });
        }
    }
}

// delete

ingredients.delete = function (id){
    bootbox.confirm({
        title: "Xóa nguyên liệu",
        message: "Bạn có chắc chắn muốn xóa nguyên liệu này không ???",
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
                    url : "http://localhost:8080/api/ingredients/" + id,
                    method: "DELETE",
                    dataType : "json",
                    success : function(){
                        toastr.success("Xóa thành công");
                        $('#modalSaveIngredient').modal('hide');
                        $("#ingredients-datatables").DataTable().ajax.reload();
                    },
                    error: function (){
                        alert('Bạn phải xóa tất cả các thông tin nhập hàng liên quan đến nguyên liệu này trước khi xóa nó');
                    }
                });
            }
        }
    });
}

// list datatable
ingredients.listTable = function (){
    $('#modalTableUnit').modal('show');
    ingredients.innitProductLineTable();
    $("#modalTableUnit").on("shown.bs.modal", function(e) {
        $("#unit-datatables")
            .DataTable()
            .columns.adjust()
            .responsive.recalc();
    });
}

// table list unit
ingredients.innitProductLineTable = function (){
    $("#unit-datatables").DataTable({
        ajax: {
            url: "http://localhost:8080/api/units",
            method: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {data: "name", name: "name", title: "Tên đơn vị", orderable: true},
            {data: "comment", name: "comment", title: "Ghi chú"},
            {
                data: "id", name: "id", title: "Chức năng", orderable: false,
                "render": function (data, type, row, meta) {
                    return "<a href='javascript:;' title='edit product' onclick='units.get("+ data +")'><i class='fa fa-edit'></i></a> "+
                        "<a href='javascript:;' title='remove product' onclick='units.delete("+ data +")' ><i class='fa fa-trash'></i></a>";
                }
            },
        ],
    });
}

// modal add
ingredients.addNewunit = function (){
    $('#formAddEditUnit')[0].reset();
    $('#modalTitleUnit').html('Thêm mới đơn vị')
    $('#modalSaveUnit').modal('show');
}

// lấy id
units.get = function (idUnit){
    $.ajax({
        url: "http://localhost:8080/api/units/" + idUnit,
        method: "GET",
        dataType: "json",
        success: function (data){
            $('#modalTitleUnit').html('Chỉnh sửa đơn vị');
            $('#nameUnitt').val(data.name);
            $('#commentUnit').val(data.comment);
            $('#idUnit').val(data.id);
            $('#modalSaveUnit').modal('show');
        }
    });
}
// save unit
ingredients.saveUnit = function (){
    if ($('#formAddEditUnit')){
        if (!$('#idUnit').val()){
            let unit = {};
            unit.name = $('#nameUnitt').val();
            unit.comment = $('#commentUnit').val();

            $.ajax({
               url: "http://localhost:8080/api/units",
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(unit),

                success: function (){
                    toastr.success("Thêm mới thành công");
                    $('#modalSaveUnit').modal('hide');
                    $("#unit-datatables").DataTable().ajax.reload();
                }
            });
        }
        else {
            let unit = {};
            unit.name = $('#nameUnitt').val();
            unit.comment = $('#commentUnit').val();
            unit.id = Number($('#idUnit').val());

            $.ajax({
                url: "http://localhost:8080/api/units/" + unit.id,
                method: "PUT",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(unit),

                success: function (){
                    toastr.success("Cập nhật thành công");
                    $('#modalSaveUnit').modal('hide');
                    $("#unit-datatables").DataTable().ajax.reload();
                }
            });
        }
    }
}

// delete unit
units.delete = function (id){
    bootbox.confirm({
        title: "Xóa sản phẩm",
        message: "Bạn có muốn xóa đơn vị này?",
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
                    url: "http://localhost:8080/api/units/" + id,
                    method: "DELETE",
                    dataType: "json",

                    success: function (data) {
                        $('#formAddEditUnit').modal('hide');
                        $("#unit-datatables").DataTable().ajax.reload();
                        // importProducts.initImportProductTable();
                    },
                    error: function (){
                        alert('Bạn phải xóa tất cả nguyên liệu có đơn vị này trước khi xóa nó')
                    }
                });
            }
        }
    });
}

$(document).ready(function () {
    ingredients.initIngredients();
    ingredients.initListUnit();
});