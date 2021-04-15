var areas = {};

$(document).ready(function () {
    // let orderStack = false;
    // let tableStack = false;
    // $(".table-item").click(function () {
    //     if (!orderStack && !tableStack) {
    //         $(".order-list").addClass("d-block");
    //         orderStack = true;
    //     }
    //     if (!orderStack && tableStack) {
    //         $(".table-add-form").removeClass("d-block");
    //         tableStack = false;
    //         $(".order-list").addClass("d-block");
    //         orderStack = true;
    //     }
    // });
    // $(".order-list-cancel-btn").click(function () {
    //     $(".order-list").removeClass("d-block");
    //     orderStack = false;
    // });
    // $(".table-add-btn").click(function () {
    //     if (!tableStack && !orderStack) {
    //         $(".table-add-form").addClass("d-block");
    //         tableStack = true;
    //     }
    //     if (!tableStack && orderStack) {
    //         $(".order-list").removeClass("d-block");
    //         orderStack = false;
    //         $(".table-add-form").addClass("d-block");
    //         tableStack = true;
    //     }
    // });
    // $(".table-add-cancel-btn").click(function () {
    //     $(".table-add-form").removeClass("d-block");
    //     tableStack = false;
    // });


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
                            <a class="nav-link" role="tab" data-toggle="pill" href="#tab" onclick="areas.initTables(${v.id})">${v.name}</a>
                        </li>`
                    );
                })
            }
        })
    }

    areas.initTables = function (id) {
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
                            <i class="fl flaticon-table line-height-1" style="font-size: 110px" onclick="areas.createOrder()">
                            </i>
                            <h5 class="d-lfex justify-content-center">${v.name}</h5>
                        </div>`
                    )
                })
                $('#tables-sql').append(
                        `<a href="#" class="d-flex flex-column justify-content-center align-items-center w-200px" onclick="areas.createTables()">
                    <div class="d-flex justify-content-center align-items-center table-add-btn">
                           <i class="fa fa-plus" style="font-size: 30px;" ></i>
                    </div>
                    </a>`
                )
            }
        })
    }

    areas.createOrder = function () {
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
                                <button class="btn btn-danger col-6 pt-2 pb-2 order-list-cancel-btn" type="button">Hủy
                                    bỏ
                                </button>
                            </div>
                        </div>
                        </div>
            </div>`
        )
    }

    areas.createTables=function (){
        $('#showOrder').remove();
        $('#showTables').remove();
        $('#showOrdersTables').append(
            `<div class="col table-add-form" id="showTables">

                <div>
                    <form method="post"><h2 class="text-center mt-3">Thêm bàn mới</h2>
                        <div class="form-group"><label>Tên bàn</label><input class="form-control" type="text" name="name"
                                                                             placeholder="Mời nhập tên bàn"></div>
                        <div class="form-group"><label>Trạng thái</label><select class="form-control">
                            <optgroup label="This is a group">
                                <option value="12" selected="">This is item 1</option>
                                <option value="13">This is item 2</option>
                                <option value="14">This is item 3</option>
                            </optgroup>
                        </select></div>
                        <div class="form-group"><label>Mô tả/Ghi chú</label><textarea class="form-control" name="message"
                                                                                      placeholder="Nhập ghi chú"
                                                                                      rows="5"></textarea></div>
                       <div class="form-group">
                            <div class="form-row row">
                                
                                    <button class="btn btn-success col-6" type="button">Thực hiện</button>
                                    <button class="btn btn-danger col-6 table-add-cancel-btn" type="button">Hủy bỏ</button>
                                </div>
                         
                        </div>
                    </form>
                </div>
            </div>`
        )
    }

    areas.initAreas();
});