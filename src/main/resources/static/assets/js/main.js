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


    let areas = {} || areas;
    areas.initAreas = function () {
        $.ajax({
            url: "http://localhost:8080/api/areas",
            method: "GET",
            dataType: "json",
            success: function (data) {
                $('#area-sql').empty();
                $.each(data, function (i, v) {
                    $('#area-sql').append(
                        `<li class="nav-item w-100px">
                            <a class="nav-link" role="tab" data-toggle="pill" href="#tab-$href="#tab-1">${v.name}</a>
                        </li>`
                    );
                })
            }
        })
    }
    areas.initAreas();
});