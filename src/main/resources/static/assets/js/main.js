let areas = {};

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
                            <a class="nav-link" role="tab" data-toggle="pill" href="#tab" onclick="areas.showTables(${v.id})">${v.name}</a>
                        </li>`
                );
            })
        }
    })
}

areas.showTables = function (id) {
    $.ajax({
        url: "http://localhost:8080/api/tables/area/" + id,
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tables-sql').empty();
            $.each(data, function (i, v) {
                $('#tables-sql').append(
                    `<div
                            class="d-flex flex-column justify-content-between align-items-center p-3 table-item">
                            <i class="fl flaticon-table line-height-1" style="font-size: 110px" onclick="areas.showFormAddOrder()">
                            </i>
                            <h5 class="d-lfex justify-content-center">${v.name}</h5>
                        </div>`
                )
            })
            $('#tables-sql').append(
                `<a href="javascript:;" class="d-flex flex-column justify-content-center align-items-center w-200px" onclick="areas.showFormAddTable()">
                    <div class="d-flex justify-content-center align-items-center table-add-btn">
                           <i class="fa fa-plus" style="font-size: 30px;" ></i>
                    </div>
                    </a>`
            )
        }
    })
}

areas.showFormAddOrder = function () {
    $('#showTables').remove();
    $('#showOrder').remove();
    $('#showOrdersTables').append(
        `<div class="col order-list" id="showOrder">
                <div>
                <div class="form-row">
                    <div class="col">
                        <div>
                            <h4>
                                <b>Bàn số 1</b>
                            </h4>
                        </div>
                    </div>
                    <div class="col">
                        <div>
                            <h4>
                                <b>Trạng thái:</b>
                                <span>đang chờ</span>
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
                                <tbody class="">
                                    <tr class="row">
                                        <td class="d-xl-flex justify-content-xl-center align-items-xl-center order-item-trash col-1">
                                            <i class="far fa-trash-alt d-xl-flex justify-content-xl-center align-items-xl-center"></i>
                                        </td>
                                        <td class="d-xl-flex justify-content-xl-left align-items-xl-center col-3">
                                            <div>
                                                <div>
                                                    <h5>Nước mắt nữ thần</h5>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="d-xl-flex justify-content-xl-left align-items-xl-center col-2">20000
                                            đ
                                        </td>
                                        <td class="d-xl-flex justify-content-xl-center align-items-xl-center col-3">
                                            <div class="quantity clearfix d-flex justify-content-center">
                                                <input
                                                    id="quantity-left-minus"
                                                    onclick="this.parentNode.querySelector('input[type=number]').stepDown()"
                                                    type="button" value="-" class="minus btn">
                                                    <input id="quantity"
                                                           type="number" step="1"
                                                           min="1" max="99"
                                                           name="quantity"
                                                           title="Qty"
                                                           class="qty form-control d-inline-block"
                                                           size="4">
                                                        <input
                                                            id="quantity-right-plus"
                                                            onclick="this.parentNode.querySelector('input[type=number]').stepUp()"
                                                            type="button" value="+" class="plus btn">
                                            </div>
                                        </td>
                                        <td class="d-xl-flex justify-content-xl-left align-items-xl-center col-2">
                                            <strong>20000
                                                đ
                                            </strong>
                                        </td>
                                        <td class="d-xl-flex justify-content-xl-center align-items-xl-center col-1">
                                            <input
                                                class="form-check" type="checkbox">
                                        </td>
                                    </tr>
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
                           style="  border-radius: 50%;  border: 2px solid;  width: 50px;  height: 50px; ">
                            <i
                                class="fa fa-plus" style="font-size: 30px;"></i>
                        </a>
                    </div>
                    <hr>
                        <div class="form-group">
                            <div class="row">
                                <button class="btn btn-success col-6 pt-2 pb-2" type="button">Thực hiện</button>
                                <button class="btn btn-danger col-6 pt-2 pb-2 order-list-cancel-btn" type="button" onclick="areas.closeOrder()">Hủy
                                    bỏ
                                </button>
                            </div>
                        </div>
                        </div>
            </div>`
    )
}

areas.formAddTable = function () {
    $('#showOrder').remove();
    $('#showTables').remove();
    $('#showOrdersTables').append(
        `<div class="col table-add-form" id="showTables">
                <div>
                    <form method="post" id="formAdd"><h2 class="text-center mt-3">Thêm bàn mới</h2>
                    <input type="hidden" name="id" id="id">
                        <div class="form-group"><label>Tên bàn</label><input class="form-control" type="text" name="name" id="name"
                                                                             placeholder="Mời nhập tên bàn"></div>
                        <div class="form-group"><label>Khu vực</label><select class="form-control" id="area" required>
                        </select></div>
                        <div class="form-group"><label>Mô tả/Ghi chú</label><textarea class="form-control" name="comment" id="comment"
                                                                                      placeholder="Nhập ghi chú"
                                                                                      rows="5"></textarea></div>
                       <div class="form-group">
                            <div class="form-row row">
                                    <button class="btn btn-success col-6" type="button" onclick="areas.addTable()">Thực hiện</button>
                                    <button class="btn btn-danger col-6 table-add-cancel-btn" type="button" onclick="areas.closeTable()">Hủy bỏ</button>
                                </div>
                        </div>
                    </form>
                </div>
            </div>`
    )
}

areas.OptionArea = function () {
    $.ajax({
        url: "http://localhost:8080/api/areas/",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#area').append(
                `<option value="" selected disabled>---Choose Area---</option>`
            )
            $.each(data, function (i, v) {
                $('#area').append(
                    `<option value="${v.id}">${v.name}</option>`
                )
            })
        }
    })
}

areas.showListByProductline = function (productline) {
    $.ajax({
        url: "http://localhost:8080/api/products" + productline,
        method: "GET",
        dataType: "JSON",
        success: function (data) {
                $.each(data, function (i, v) {
                    $('#menuOrder').append(
                    `<div className="coffee-search-items d-flex flex-row row">
                        <div
                            className="coffee-search-item p-2 col-3 d-flex flex-column justify-content-center align-items-center">
                            <div
                                className="mx-width-100 mx-height-100 w-100px h-100px bg-size-contain bg-pos-center d-flex flex-column justify-content-between"
                                style="background-image: url(assets/img/Tear_of_the_Goddess_Large.jpg);"><span
                                className="text-white price">Giá: ${v.price}</span></div>
                            <p className="product-name">${v.name}</p></div>
                        <div>`
                    )
                })
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
                        <p>${v.productLineList.name}</p>`
                )
            })
        }
    })
}

areas.addTable = function () {
}

areas.showFormAddTable = function () {
    areas.formAddTable();
    areas.OptionArea();
}

areas.closeTable = function () {
    $('#showTables').empty();
}

areas.closeOrder = function () {
    $('#showOrder').empty();
}
$(document).ready(function () {
        areas.showMenu();
        areas.initAreas();
    }
);