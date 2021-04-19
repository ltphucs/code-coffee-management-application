let importIngredient = {};

importIngredient.initIngredients = function () {
    $("#import-ingredients-datatables").DataTable({
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
                    return "<a href='javascript:;' title='edit product'><i class='fa fa-edit'></i></a> " +
                        "<a href='javascript:;' title='remove product' ><i class='fa fa-trash'></i></a>"
                }
            },
        ]
    });
}

$(document).ready(function () {
    importIngredient.initIngredients();
});
