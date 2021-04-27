let areas = {};

let tables = {};

let orders = {};

let arrOrderDetailsTest = [];

function clearArr() {
    while (arrOrderDetailsTest.length > 0) {
        arrOrderDetailsTest.pop();
    }
}

function setStatusTable() {
    if (arrOrderDetailsTest.length > 0) {
        return "USING";
    } else {
        return "EMPTY";
    }
}

// #1cc88a


areas.initAreas = function () {
    $.ajax({
        url: "http://localhost:8080/api/areas/",
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#area-sql').empty();
            $('#showOrdersTables').empty();
            $('#showOrdersTables').append(
                `<div class="col mt-5">
                            <div class="d-xl-flex align-items-xl-start">
                                <ul class="nav nav-pills text-capitalize border rounded-0 d-xl-flex flex-column" id="area-sql"></ul>
                                <div class="tab-content">
                                    <div class="tab-pane fade show active" role="tabpanel" id="tab">
                                        <div class="col d-flex flex-wrap" id="tables-sql">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>`
            );
            $.each(data, function (i, v) {
                $('#area-sql').append(
                    `<li class="nav-item w-100px">
                            <a class="nav-link" id="area-${v.id}" role="tab" data-toggle="pill" href="#tab" onclick="areas.showTables(${v.id})">${v.name}</a>
                        </li>`
                );
            });
            $("#area-1").click();
        }
    })

}

areas.showMenu = function () {
    $.ajax({
        url: "http://localhost:8080/api/menu",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $.each(data, function (i, v) {
                $('#menuOrder').append(
                    `<hr>
                        <p>${v.nameProductLine}</p>
                        <div class="coffee-search-items d-flex flex-row row">
                        ${v.productList.map(p =>
                        `<div class="coffee-search-item p-2 col-3 d-flex flex-column justify-content-center align-items-center">
                                <div
                                    class="mx-width-100 mx-height-100 w-100px h-100px bg-size-contain bg-pos-center d-flex flex-column justify-content-between"
                                    style="background-image: url(https://static.wikia.nocookie.net/leagueoflegends/images/6/66/Tear_of_the_Goddess_item_HD.png/revision/latest?cb=20201111004755);" onclick="orders.addArrOrderDetails(${p.id})">
                                    <span class="text-white price">Giá: ${p.price}</span>
                                </div>
                                <p class="product-name">${p.name}</p>
                              </div>`
                    ).join("")}
                        </div>
                    `
                )
            })
        }
    })
}

areas.showTables = function (id) {
    $('#showOrder').empty().hide();
    $('#showTables').empty().hide();
    $.ajax({
        url: "http://localhost:8080/api/areas/" + id + "/tables",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tables-sql').empty();
            $.each(data, function (i, v) {
                $('#tables-sql').append(
                    `<div
                            class="d-flex flex-column justify-content-between align-items-center p-3 table-item">
                            <i class="fl flaticon-table line-height-1" style="font-size: 110px" onclick="tables.showFormAddOrder(${v.id})">
                            </i>
                            <h5 class="d-lfex justify-content-center">${v.name}</h5>
                        </div>`
                )
            })
            $('#tables-sql').append(
                `<a href="javascript:;" class="d-flex flex-column justify-content-center align-items-center w-200px" onclick="tables.showFormAddTable(${id})">
                    <div class="d-flex justify-content-center align-items-center table-add-btn">
                           <i class="fa fa-plus" style="font-size: 30px;" ></i>
                    </div>
                </a>`
            )
        }
    })
}

tables.showFormAddTable = function (id) {
    $('#showOrder').remove();
    $('#showTables').remove();
    $('#showOrdersTables').append(
        `<div class="col table-add-form" id="showTables">
                <div>
                    <form method="post" id="formAddEdit" name="formAddEdit"><h2 class="text-center mt-3">Thêm bàn mới</h2>
                    <input type="hidden" name="id" id="id">
                        <div class="form-group"><label>Tên bàn</label>
                        <input class="form-control" type="text" name="name" id="name"
                                                                             placeholder="Mời nhập tên bàn" required>
                        <small id="err-add-table-name" class="text-danger"></small>

                        </div>
                        <div class="form-group"><label>Mô tả/Ghi chú</label><textarea class="form-control" name="comment" id="comment"
                                                                                      placeholder="Nhập ghi chú"
                                                                                      rows="5" required></textarea></div>                                                                             
                       <div class="form-group">
                            <div class="form-row row">
                                    <button class="btn btn-success col-6" type="button" onclick="tables.addTable(${id})">Thực hiện</button>
                                    <button class="btn btn-danger col-6 table-add-cancel-btn" type="button" onclick="tables.closeTable(${id})">Hủy bỏ</button>
                                </div>
                        </div>
                    </form>
                </div>
            </div>`
    )
}

tables.addTable = function (idArea) {
    let tableObj = {};
    tableObj.name = $('#name').val();
    tableObj.comment = $('#comment').val();
    let areaObj = {};
    areaObj.id = idArea;
    tableObj.area = areaObj;
    $.ajax({
        url: "http://localhost:8080/api/tables/",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(tableObj),
        success: function (data) {
            toastr.success("Thêm bàn mới thành công");
            tables.closeTable();
            $("#showTables").empty();
            areas.showTables(idArea);
        },
        error: function (err) {
            $("#err-add-table-name").html(err.responseJSON.name);
        }
    })
}

tables.showFormAddOrder = function (idTable) {
    console.log(idTable);
    clearArr();
    $.ajax({
        url: "http://localhost:8080/api/tables/" + idTable,
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            console.log(data)
            $('#showTables').remove();
            $('#showOrder').remove();
            $('#showOrdersTables').append(
                `<div class="col order-list" id="showOrder">
                <div class="card">
                 <div class="card-header">
                    <i class="fas fa-cog"></i>
                    
                </div>
                <div class="form-row">
                    <div class="col">
                        <div>
                            <h4>
                                <b>Bàn ${data.name}</b>
                            </h4>
                        </div>
                    </div>
                    <div class="col">
                        <div>
                            <h4>
                                <b>Trạng thái:</b>
                                <span>${data.tableStatus}</span>
                            </h4>
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col">
                        <div class="table">
                            <table class="table table-hover">
                                <thead>
                                    <tr class="row order-table-title">
                                        <th class="col-1">&nbsp;</th>
                                        <th class="col-3">Sản phẩm</th>
                                        <th class="col-2">Đơn giá</th>
                                        <th class="col-3 justify-content-center">Số lượng</th>
                                        <th class="col-2">Tổng</th>
                                        <th class="col-1">Trạng thái</th>
                                    </tr>
                                </thead>
                                <tbody class="" id="list-orderdetail">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <hr>
                    <div class="col d-xl-flex justify-content-xl-center align-items-xl-center">
                        <a href="#"
                           data-toggle="modal"
                           data-target="#exampleModalCenter"
                           class="d-xl-flex justify-content-xl-center align-items-xl-center"
                           style="  border-radius: 50%;  border: 2px solid;  width: 50px;  height: 50px; " onclick="areas.showMenu()">
                            <i
                                class="fa fa-plus" style="font-size: 30px;"></i>
                        </a>
                    </div>
                    <hr>
                        <div class="form-group">
                            <div class="row">
                                <button class="btn btn-success col-6 pt-2 pb-2" type="button" onclick="orders.addOrder(${idTable})">Cập nhật</button>
                                <button class="btn btn-danger col-6 pt-2 pb-2 order-list-cancel-btn" type="button" onclick="tables.closeOrder(${data.area.id})">
                                Hủy bỏ
                                </button>
                            </div>
                        </div>
                    </div>
            </div>`
            )
            orders.showOrderAndOrderDetails(idTable);
        }
    })
}

orders.addArrOrderDetails = function (idProduct) {
    let hasProduct = false;
    $.ajax({
        url: "http://localhost:8080/api/products/" + idProduct,
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $.each(data, function (i, vCurrent) {
                $.each(arrOrderDetailsTest, function (i, v) {
                    if (v.id === vCurrent.id) {
                        v.quantity = v.quantity + 1;
                        hasProduct = true;
                    }
                })
                if (!hasProduct) {
                    let detailObj = {};
                    detailObj.id = vCurrent.id;
                    detailObj.name = vCurrent.name;
                    detailObj.price = vCurrent.price;
                    detailObj.quantity = 1;
                    arrOrderDetailsTest.push(detailObj);
                }
            })
            console.log(arrOrderDetailsTest);
            orders.showOrderDetails(arrOrderDetailsTest);
        }
    })
}

orders.showOrderDetails = function (arrOrderDetailsTest) {
    $('#list-orderdetail').empty();
    $.each(arrOrderDetailsTest, function (i, v) {
        $('#list-orderdetail').append(
            `<tr class="row">
                <td class="d-xl-flex justify-content-xl-center align-items-xl-center order-item-trash col-1">
                    <i class="far fa-trash-alt d-xl-flex justify-content-xl-center align-items-xl-center"></i>
                </td>
                <td class="d-xl-flex justify-content-xl-left align-items-xl-center col-3">
                    <div>
                        <div>
                            <h5>${v.name}</h5>
                        </div>
                    </div>
                </td>
                <td class="d-xl-flex justify-content-xl-left align-items-xl-center col-2">${v.price}
                    đ
                </td>
                <td class="d-xl-flex justify-content-xl-center align-items-xl-center col-3">
                    <div class="quantity clearfix d-flex justify-content-center">
                        <input
                            id="quantity-left-minus"
                            onClick="this.parentNode.querySelector('input[type=number]').stepDown()"
                            type="button" value="-" class="minus btn">
                            <input id="quantity"
                                   type="number" step="1"
                                   min="1" max="99"
                                   name="quantity"
                                   title="Qty"
                                   class="qty form-control d-inline-block"
                                   size="4" value="${v.quantity}" disabled>
                                <input
                                    id="quantity-right-plus"
                                    onClick="this.parentNode.querySelector('input[type=number]').stepUp()"
                                    type="button" value="+" class="plus btn">
                    </div>
                </td>
                <td class="d-xl-flex justify-content-xl-left align-items-xl-center col-2">
                    <strong>${v.price * v.quantity}
                        đ
                    </strong>
                </td>
                <td class="d-xl-flex justify-content-xl-center align-items-xl-center col-1">
                    <i class="fas fa-check text-success" style="width: 50%"></i>
                    <i class="fas fa-redo text-danger" style="width: 50%"></i>
                </td>
            </tr>`
        )
    })
}

orders.addOrder = function (idTable) {
    $.ajax({
        url: "http://localhost:8080/api/tables/" + idTable + "/order",
        method: "GET",
        dataType: "JSON",
        error: function (data) {
            let tableObj = {};
            tableObj.id = idTable;
            let accountObj = {};
            accountObj.id = 1;
            let orderObj = {};
            orderObj.table = tableObj;
            orderObj.totalAllPrice = 0;
            orderObj.account = accountObj;
            $.ajax({
                url: "http://localhost:8080/api/orders/",
                method: "POST",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(orderObj),
                success: function (data) {
                    console.log("thanh cong");
                    orders.addOrderDetails(idTable);
                }
            })
        },
        success: function (data) {
            orders.addOrderDetails(idTable);
        }
    })
}

orders.addOrderDetails = function (idTable) {
    $.ajax({
        url: "http://localhost:8080/api/tables/" + idTable + "/order",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $.each(arrOrderDetailsTest, function (i, v) {
                let orderObj = {};
                orderObj.id = data.id;
                let productObj = {};
                productObj.id = v.id;
                let orderDetailObj = {};
                orderDetailObj.order = orderObj;
                orderDetailObj.product = productObj;
                orderDetailObj.quantity = v.quantity;
                orderDetailObj.priceEach = v.price;
                orderDetailObj.totalPrice = v.price * v.quantity;
                $.ajax({
                    url: "http://localhost:8080/api/orderDetails/",
                    method: "POST",
                    dataType: "JSON",
                    contentType: "application/json",
                    data: JSON.stringify(orderDetailObj),
                    success: function (data) {
                        console.log("thanh cong details");
                    }
                })
            })
        }
    })
}

orders.showOrderAndOrderDetails = function (idTable) {
    $.ajax({
        url: "http://localhost:8080/api/tables/" + idTable + "/order",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $.ajax({
                url: "http://localhost:8080/api/orders/" + data.id + "/orderDetails",
                method: "GET",
                dataType: "JSON",
                success: function (data) {
                    $('#list-orderdetail').empty();
                    $.each(data, function (i, v) {
                        let detailObj = {};
                        detailObj.id = v.product.id;
                        detailObj.name = v.product.name;
                        detailObj.price = v.priceEach;
                        detailObj.quantity = v.quantity;
                        arrOrderDetailsTest.push(detailObj);
                        $('#list-orderdetail').append(
                            `<tr class="row">
                                <td class="d-xl-flex justify-content-xl-center align-items-xl-center order-item-trash col-1">
                                    <i class="far fa-trash-alt d-xl-flex justify-content-xl-center align-items-xl-center"></i>
                                </td>
                                <td class="d-xl-flex justify-content-xl-left align-items-xl-center col-3">
                                     <div>
                                          <div>
                                               <h5>${v.product.name}</h5>
                                          </div>
                                     </div>
                                </td>
                                <td class="d-xl-flex justify-content-xl-left align-items-xl-center col-2">${v.priceEach} đ</td>
                                <td class="d-xl-flex justify-content-xl-center align-items-xl-center col-3">
                                     <div class="quantity clearfix d-flex justify-content-center">
                                          <input id="quantity-left-minus" onClick="this.parentNode.querySelector('input[type=number]').stepDown()" type="button" value="-" class="minus btn">
                                          <input id="quantity" type="number" step="1" min="1" max="99" name="quantity" title="Qty" class="qty form-control d-inline-block" size="4" value="${v.quantity}" disabled>
                                          <input id="quantity-right-plus" onClick="this.parentNode.querySelector('input[type=number]').stepUp()" type="button" value="+" class="plus btn">
                                     </div>
                                </td>
                                <td class="d-xl-flex justify-content-xl-left align-items-xl-center col-2">
                                     <strong>${v.priceEach * v.quantity} đ</strong>
                                </td>
                                <td class="d-xl-flex justify-content-xl-center align-items-xl-center col-1">
                                     <i class="fas fa-check text-success" style="width: 50%"></i>
                                     <i class="fas fa-redo text-danger" style="width: 50%"></i>
                                </td>
                            </tr>`
                        )
                    })
                }
            })
        }
    })
}

tables.closeTable = function (idTable) {
    $('#showTables').empty().hide();
    $('#tables-sql').empty();
    areas.showTables(idTable);
}

tables.closeOrder = function (idTable) {
    $('#showOrder').empty().hide();
    $('#tables-sql').empty();
    areas.showTables(idTable);
}

$(document).ready(function () {
        areas.initAreas();
    }
);