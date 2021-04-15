var areas = {};

$(document).ready(function () {
    let orderStack = false;
    let tableStack = false;
    $(".table-item").click(function () {
        if (!orderStack && !tableStack) {
            $(".order-list").addClass("d-block");
            orderStack = true;
        }
        if (!orderStack && tableStack) {
            $(".table-add-form").removeClass("d-block");
            tableStack = false;
            $(".order-list").addClass("d-block");
            orderStack = true;
        }
    });
    $(".order-list-cancel-btn").click(function () {
        $(".order-list").removeClass("d-block");
        orderStack = false;
    });
    $(".table-add-btn").click(function () {
        if (!tableStack && !orderStack) {
            $(".table-add-form").addClass("d-block");
            tableStack = true;
        }
        if (!tableStack && orderStack) {
            $(".order-list").removeClass("d-block");
            orderStack = false;
            $(".table-add-form").addClass("d-block");
            tableStack = true;
        }
    });
    $(".table-add-cancel-btn").click(function () {
        $(".table-add-form").removeClass("d-block");
        tableStack = false;
    });


    areas.initTables=function (id){
        $.ajax({
            url:"http://localhost:8080/api/tables/area/" +id,
            method:"GET",
            dataType: "JSON",
            success:function (data) {
                $('#tables-tab').empty();
                $.each(data,function (i,v){
                    $('#tables-tab').append(
                            `<div
                                class="d-flex flex-column justify-content-between align-items-center p-3 table-item">
                                <i class="fl flaticon-table line-height-1" style="font-size: 110px">
                                </i>
                                <h5 class="d-lfex justify-content-center">${v.name}</h5>
                            </div>`
                    )
                })
                $('#tables-tab').append(
                    `<div class="d-flex flex-column justify-content-center align-items-center w-200px">
                        <a href="#" class="d-flex justify-content-center align-items-center table-add-btn"><i
                            class="fa fa-plus" style="font-size: 30px;"></i></a>
                    </div>`
                )
            }
        })
    }

    areas.initAreas = function () {
        $.ajax({
            url: "http://localhost:8080/api/areas/",
            method: "GET",
            dataType: "json",
            success: function (data) {
                $('#area-sql').empty();
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

    areas.initAreas();
});