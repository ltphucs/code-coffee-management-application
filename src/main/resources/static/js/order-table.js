let areas = {};

let tables = {};

let orders = {};

let bills = {};

areas.initAreas = function () {
    $.ajax({
        url: "http://localhost:8080/api/areas/",
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#area-sql').empty();
            $('#showOrdersTables').empty().append(
                `<div class="col mt-5">
                            <div class="d-xl-flex align-items-xl-start">
                                <ul class="nav nav-pills text-capitalize border rounded-0 d-xl-flex flex-column shadow" id="area-sql"></ul>
                                <div class="tab-content shadow">
                                    <div class="tab-pane fade show active card" role="tabpanel" id="tab">
                                        <div class="col d-flex flex-wrap pt-4 pb-4" id="tables-sql">
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

areas.showMenu = function (idTable) {
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
                        `<div class="coffee-search-item p-2 col-3 d-flex flex-column align-items-center">
                                <div
                                    class="mx-width-100 mx-height-100 w-100px h-100px bg-size-contain bg-pos-center d-flex flex-column justify-content-between"
                                    style="background-image: url(https://static.wikia.nocookie.net/leagueoflegends/images/6/66/Tear_of_the_Goddess_item_HD.png/revision/latest?cb=20201111004755);" onclick="orders.addOrder(${idTable},${p.id})">
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

areas.showTables = function (idArea) {
    $('#showOrder').empty().hide();
    $('#showTables').empty().hide();
    $.ajax({
        url: "http://localhost:8080/api/areas/" + idArea + "/tables",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tables-sql').empty();
            $.each(data, function (i, v) {
                if (v.tableStatus === "USING") {
                    $('#tables-sql').append(
                        `<div
                            class="d-flex flex-column justify-content-between align-items-center p-3 table-item">
                            <i class="fl flaticon-table line-height-1 text-success" style="font-size: 110px" onclick="tables.showFormAddOrder(${v.id})">
                            </i>
                            <h5 class="d-lfex justify-content-center">${v.name}</h5>
                        </div>`
                    )
                } else {
                    $('#tables-sql').append(
                        `<div
                            class="d-flex flex-column justify-content-between align-items-center p-3 table-item">
                            <i class="fl flaticon-table line-height-1" style="font-size: 110px" onclick="tables.showFormAddOrder(${v.id})">
                            </i>
                            <h5 class="d-lfex justify-content-center">${v.name}</h5>
                        </div>`
                    )
                }

            })
            $('#tables-sql').append(
                `<a href="javascript:;" class="d-flex flex-column justify-content-center align-items-center w-200px" onclick="tables.showFormAddTable(${idArea})">
                    <div class="d-flex justify-content-center align-items-center table-add-btn">
                           <i class="fa fa-plus" style="font-size: 30px;" ></i>
                    </div>
                </a>`
            )
        }
    })
}

tables.showFormAddTable = function (idArea) {
    $('#showOrder').remove();
    $('#showTables').remove();
    $('#showOrdersTables').append(
        `<div class="col table-add-form mt-5" id="showTables">
                <div class="card shadow">
                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Thêm bàn mới</h6>
                        <div>
                             <a href="javascript:;" class="btn btn-danger btn-circle" onclick="tables.closeTable(${idArea})">
                                 <i class="fas fa-times"></i>
                             </a>
                        </div>
                    </div>
                    <div class="card-body">
                        <input type="hidden" name="id" id="id">
                            <div class="form-group"><label>Tên bàn</label>
                            <input class="form-control" type="text" name="name" id="name"
                                                                                 placeholder="Mời nhập tên bàn" required>
                            <small id="err-add-table-name" class="text-danger"></small>
    
                            </div>
                            <div class="form-group"><label>Mô tả/Ghi chú</label><textarea class="form-control" name="comment" id="comment"
                                                                                          placeholder="Nhập ghi chú"
                                                                                          rows="5" required></textarea>
                            </div>      
                    </div>
                    <div class="card-footer">
                       <div class="form-group">
                            <div class="form-row row">

                                    <button class="btn btn-success col" type="button" onclick="tables.addTable(${idArea})">Thực hiện</button>
                            </div>

                        </div>
                 
                    </div>
                    
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

tables.usingTable = function (idTable) {
    let tableObj = {};
    tableObj.id = idTable;
    tableObj.tableStatus = "USING";
    $.ajax({
        url: "http://localhost:8080/api/tables/" + idTable + "/tableStatus",
        method: "PUT",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(tableObj),
        success: function (data) {
            tables.showFormAddOrder(idTable);
        }
    })
}

tables.emptyTable = function (idTable) {
    let tableObj = {};
    tableObj.id = idTable;
    tableObj.tableStatus = "EMPTY";
    $.ajax({
        url: "http://localhost:8080/api/tables/" + idTable + "/tableStatus",
        method: "PUT",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(tableObj),
        success: function (data) {
            tables.showFormAddOrder(idTable);
        }
    })
}

tables.showFormAddOrder = function (idTable) {
    $.ajax({
        url: "http://localhost:8080/api/tables/" + idTable,
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#showTables').remove();
            $('#showOrder').remove();
            $('#showOrdersTables').append(
                `<div class="col order-list mt-5" id="showOrder">
                <div class="card shadow">
                     <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Đặt món</h6>
                        <div class="d-flex">
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle btn btn-warning btn-circle mr-2" href="javascript:;" id="userDropdown" role="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-cog"></i>
                                </a>
                               
                            <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow "
                                    aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="javascript:;">
                                        <i class="fas fa-exclamation-triangle fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Cập nhật bàn
                                    </a>
                                    <a class="dropdown-item" href="javascript:;">
                                        <i class="fas fa-trash fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Xóa bàn
                                    </a>
                                </div>
                            </li>     
                             <a href="javascript:;" class="btn btn-danger btn-circle" onclick="tables.closeOrder(${data.area.id})">
                                 <i class="fas fa-times"></i>
                             </a>
                        </div>
                         
                    </div>
                    <div class="card-body">
                        <div class="form-row flex-column">
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
                        <div class="col d-xl-flex justify-content-xl-center align-items-xl-center mb-3">
                            <a href="javascript:;"
                               data-toggle="modal"
                               data-target="#exampleModalCenter"
                               class="d-xl-flex justify-content-xl-center align-items-xl-center"
                               style="  border-radius: 50%;  border: 2px solid;  width: 50px;  height: 50px; " onclick="areas.showMenu(${idTable})">
                                <i
                                    class="fa fa-plus" style="font-size: 30px;"></i>
                            </a>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="form-group">
                                <div class="row" id="btnSuccess">
                                   
                                </div>
                            </div>
                        </div>
                    </div>
            </div>`
            )
            orders.getButtonSuccess(data);
        }
    })
    orders.showOrderAndOrderDetails(idTable);
}

orders.getButtonSuccess = function (table) {
    if (table.tableStatus === "USING") {
        $('#btnSuccess').empty().append(
            `<button class="btn btn-success col pt-2 pb-2" type="button" onclick="bills.addBill(${table.id})">Thanh toán</button>`
        )
    } else {
        $('#btnSuccess').empty().append(
            `<button class="btn btn-success col pt-2 pb-2" type="button" disabled>Thanh toán</button>`
        )
    }
}

orders.addOrder = function (idTable, idProduct) {
    let tableObj = {};
    tableObj.id = idTable;
    let accountObj = {};
    accountObj.id = 1;
    let orderObj = {};
    orderObj.table = tableObj;
    orderObj.account = accountObj;
    $.ajax({
        url: "http://localhost:8080/api/orders/",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(orderObj),
        success: function (data) {
            orders.getProduct(data.id, idProduct, idTable);
        },
        error: function (data) {
            orders.getOrder(idTable, idProduct);
        }
    })
}

orders.getOrder = function (idTable, idProduct) {
    $.ajax({
        url: "http://localhost:8080/api/tables/" + idTable + "/order",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            orders.getProduct(data.id, idProduct, idTable);
        }
    })
}

orders.getProduct = function (idOrder, idProduct, idTable) {
    $.ajax({
        url: "http://localhost:8080/api/products/" + idProduct,
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            let productObj = {};
            productObj.id = data.id;
            productObj.priceEach = data.price;
            orders.findOrderDetailByIdOrderAndIdProduct(idOrder, productObj, idTable);
        }
    })
}

orders.findOrderDetailByIdOrderAndIdProduct = function (idOrder, productObj, idTable) {
    console.log(productObj);
    $.ajax({
        url: "http://localhost:8080/api/orders/" + idOrder + "/product/" + productObj.id + "/orderDetail",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            orders.plusQuantity(data, idTable);
        },
        error: function () {
            orders.addOrderDetail(idOrder, productObj, idTable);
        }
    })
}

orders.plusQuantity = function (orderDetail, idTable) {
    console.log(orderDetail);
    let orderObj = {};
    orderObj.id = orderDetail.order.id;
    let productObj = {};
    productObj.id = orderDetail.product.id;
    let orderDetailObj = {};
    orderDetailObj.order = orderObj;
    orderDetailObj.product = productObj;
    orderDetailObj.quantity = orderDetail.quantity + 1;
    orderDetailObj.totalPrice = orderDetailObj.quantity * orderDetail.priceEach;
    console.log(orderDetailObj);
    $.ajax({
        url: "http://localhost:8080/api/orders/" + orderObj.id + "/product/" + productObj.id,
        method: "PUT",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(orderDetailObj),
        success: function (data) {
            orders.updateTotalPriceOrder(idTable, orderObj.id, orderDetail.priceEach);
            toastr.success("Đã cập nhật số lượng");
        }
    })
}

orders.addOrderDetail = function (idOrder, productCurrent, idTable) {
    let orderObj = {};
    orderObj.id = idOrder;
    let productObj = {};
    productObj.id = productCurrent.id;
    let orderDetailObj = {};
    orderDetailObj.order = orderObj;
    orderDetailObj.product = productObj;
    orderDetailObj.quantity = 1;
    orderDetailObj.priceEach = productCurrent.priceEach;
    orderDetailObj.totalPrice = productCurrent.priceEach * orderDetailObj.quantity;
    $.ajax({
        url: "http://localhost:8080/api/orderDetails/",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(orderDetailObj),
        success: function (data) {
            toastr.success("Đã thêm sản phẩm mới");
            orders.updateTotalPriceOrder(idTable, idOrder, orderDetailObj.priceEach);
        }
    })
}

orders.updateTotalPriceOrder = function (idTable, idOrder, totalPrice) {
    $.ajax({
        url: "http://localhost:8080/api/orders/" + idOrder,
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            let orderObj = {};
            orderObj.id = idOrder;
            orderObj.totalAllPrice = totalPrice + data.totalAllPrice;
            $.ajax({
                url: "http://localhost:8080/api/orders/" + idOrder,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(orderObj),
                success: function (data) {
                    tables.usingTable(idTable);
                }
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
                        $('#list-orderdetail').append(
                            `<tr class="row">
                                <td class="d-xl-flex justify-content-xl-center align-items-xl-center order-item-trash col-1">
                                    <i class="far fa-trash-alt d-xl-flex justify-content-xl-center align-items-xl-center" onclick="orders.removeOrderDetail(${v.order.id},${v.product.id},${idTable})"></i>
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
                                          <input id="quantity-left-minus" onclick="this.parentNode.querySelector('input[type=number]').stepDown();orders.showBtnQuantity(${v.product.id},${v.quantity},${v.order.id},${v.priceEach},${idTable})" type="button" value="-" class="minus btn">
                                          <input id="quantity${v.product.id}" type="number" step="1" min="1" max="99" name="quantity${v.product.id}" title="Qty" class="qty form-control d-inline-block quantity-orderdetail" size="4" value="${v.quantity}" readonly>
                                          <input id="quantity-right-plus" onclick="this.parentNode.querySelector('input[type=number]').stepUp();orders.showBtnQuantity(${v.product.id},${v.quantity},${v.order.id},${v.priceEach},${idTable})" type="button" value="+" class="plus btn">
                                     </div>
                                </td>
                                <td class="d-xl-flex justify-content-xl-left align-items-xl-center col-2">
                                     <strong>${v.priceEach * v.quantity} đ</strong>
                                </td>
                                <td class="d-xl-flex justify-content-xl-center align-items-xl-center col-1" id="btnCheck${v.product.id}">
                                </td>
                            </tr>`
                        )
                    })
                }
            })
        }
    })
}

orders.showBtnQuantity = function (idProduct, quantity, idOrder, priceEach, idTable) {
    let idBtn = '#btnCheck' + idProduct;
    let idQuantity = 'quantity' + idProduct;
    let quantityCurrent = Number(document.getElementById(idQuantity).value);
    if (quantityCurrent === quantity) {
        $(idBtn).empty();
    } else {
        $(idBtn).empty().append(
            `<i class="fas fa-check text-success" style="width: 50%" onclick="orders.updateQuantity(${idProduct},${quantityCurrent},${idOrder},${priceEach},${idTable},${quantity})"></i>
             <i class="fas fa-redo text-danger" style="width: 50%" onclick="orders.resetQuantity(${idProduct},${quantity})"></i>`
        )
    }
}

orders.updateQuantity = function (idProduct, quantityUpdate, idOrder, priceEach, idTable, quantityBefore) {
    let orderObj = {};
    orderObj.id = idOrder;
    let productObj = {};
    productObj.id = idProduct;
    let orderDetailObj = {};
    orderDetailObj.order = orderObj;
    orderDetailObj.product = productObj;
    orderDetailObj.quantity = quantityUpdate;
    orderDetailObj.totalPrice = quantityUpdate * priceEach;
    $.ajax({
        url: "http://localhost:8080/api/orders/" + orderObj.id + "/product/" + productObj.id,
        method: "PUT",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(orderDetailObj),
        success: function (data) {
            $.ajax({
                url: "http://localhost:8080/api/orderDetails/" + data.id + "/product/" + idProduct,
                method: "DELETE",
                dataType: "json",
                success: function (data) {
                    console.log("truoc khi xoa");
                    console.log(arrOrderDetailsTest);
                    $.each(arrOrderDetailsTest, function (i, v) {
                        if (v.id === idProduct) {
                            console.log("xoa arr roi");
                            arrOrderDetailsTest.splice(i, 1);
                        }
                    })
                    console.log("sau khi xoa");
                    console.log(arrOrderDetailsTest);
                    tables.updateTableStatus(idTable);
                    toastr.success("Xóa thành công");
                    orders.showOrderAndOrderDetails(idTable);
                    console.log("sau khi load function");
                    console.log(arrOrderDetailsTest);
                }
            });
            toastr.success("them quantity thanh cong");
            orders.updateTotalPriceOrder(idTable, idOrder, priceEach * (quantityUpdate - quantityBefore))
        }
    })
}

orders.resetQuantity = function (idProduct, quantity) {
    let idBtn = '#btnCheck' + idProduct;
    let idQuantity = 'quantity' + idProduct;
    document.getElementById(idQuantity).value = quantity;
    $(idBtn).empty();
}

orders.removeOrderDetail = function (idOrder, idProduct, idTable) {
    $.ajax({
        url: "http://localhost:8080/api/orders/" + idOrder + "/product/" + idProduct,
        method: "DELETE",
        dataType: "JSON",
        success: function (data) {
            toastr.success("xoa thanh cong");
            orders.updateTotalPriceOrder(idTable, idOrder, -data.totalPrice)
            orders.checkOrderDetails(idOrder, idTable);
        }
    })
}

orders.checkOrderDetails = function (idOrder, idTable) {
    $.ajax({
        url: "http://localhost:8080/api/orders/" + idOrder + "/orderDetails",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            orders.showOrderAndOrderDetails(idTable)
        },
        error: function () {
            orders.removeOrder(idOrder, idTable);
        }
    })
}

orders.removeOrder = function (idOrder, idTable) {
    $.ajax({
        url: "http://localhost:8080/api/orders/" + idOrder,
        method: "DELETE",
        dataType: "JSON",
        success: function (data) {
            tables.emptyTable(idTable);
        },
        error: function () {
            tables.emptyTable(idTable);
        }
    })
}

bills.addBill = function (idTable) {
    $.ajax({
        url: "http://localhost:8080/api/tables/" + idTable + "/order",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            let billObj = {};
            billObj.idOrder = data.id;
            billObj.dateJoin = data.dateJoin;
            billObj.nameTable = data.table.name;
            billObj.totalPrice = data.totalAllPrice;
            console.log(billObj);
            $.ajax({
                url: "http://localhost:8080/api/bills/",
                method: "POST",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(billObj),
                success: function (dataBill) {
                    bills.addBillDetail(data.id, idTable);
                },
                error: function () {
                    bills.addBillDetail(billObj.idOrder, idTable);
                }
            })
        }
    })
}

bills.addBillDetail = function (idOrder, idTable) {
    $.ajax({
        url: "http://localhost:8080/api/orders/" + idOrder + "/orderDetails",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $.each(data, function (i, v) {
                let billDetailObj = {};
                billDetailObj.idOrder = v.order.id;
                billDetailObj.nameProduct = v.product.name;
                billDetailObj.quantity = v.quantity;
                billDetailObj.priceEach = v.priceEach;
                $.ajax({
                    url: "http://localhost:8080/api/billDetails/",
                    method: "POST",
                    dataType: "JSON",
                    contentType: "application/json",
                    data: JSON.stringify(billDetailObj),
                    success: function (dataBillDetails) {
                    }
                })
            })
            bills.removeAllOrderDetails(idOrder, idTable);
        }
    })
}

bills.removeAllOrderDetails = function (idOrder, idTable) {
    $.ajax({
        url: "http://localhost:8080/api/orders/" + idOrder + "/orderDetails",
        method: "DELETE",
        dataType: "JSON",
        success: function (data) {
            toastr.success("thanh toan thanh cong");
            orders.removeOrder(idOrder, idTable);
        },
        error: function () {
            toastr.success("thanh toan thanh cong");
            orders.removeOrder(idOrder, idTable);
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