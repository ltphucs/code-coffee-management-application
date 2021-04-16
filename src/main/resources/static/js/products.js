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
                    var str = "<a href='javascript:;' title='edit product'><i class='fa fa-edit'></i></a> " +
                        "<a href='javascript:;' title='remove product' ><i class='fa fa-trash'></i></a>"
                    return str;
                }
            },
        ]
    });
}

$(document).ready(function () {
    products.initProductTable();

});