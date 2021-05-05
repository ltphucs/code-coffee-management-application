let reportBills = {};

function setDatetime() {
    $('#datetimepicker1').datetimepicker();
    $('#datetimepicker1').data("DateTimePicker").format("YYYY-MM-DD HH:mm:ss");
    $('#datetimepicker1').data("DateTimePicker").defaultDate(`${moment().year()}-${moment().month() + 1}-${moment().date()} 00:00:00`);

    $('#datetimepicker2').datetimepicker();
    $('#datetimepicker2').data("DateTimePicker").format("YYYY-MM-DD HH:mm:ss");
    $('#datetimepicker2').data("DateTimePicker").defaultDate(`${moment().year()}-${moment().month() + 1}-${moment().date()} 23:59:59`);
}

reportBills.showDatatable = function () {
    setDatetime();
    reportBills.showBills();
};

reportBills.showBills = function () {
    let total = 0;
    let dateObj = {};
    dateObj.dateIn = $('#test-time1').val();
    dateObj.dateOut = $('#test-time2').val();
    console.log(dateObj);
    $.ajax({
        url: "http://localhost:8080/api/bills/dateExport",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(dateObj),
        success: function (data) {
            console.log(data);
            $('#list-bill').empty();
            $.each(data, function (i, v) {
                total += v.totalPrice;
                $('#list-bill').append(
                    `<tr>
                        <td>${v.idOrder}</td>
                        <td>${v.dateExport}</td>
                        <td>Bàn ${v.nameTable}</td>
                        <td>${v.totalPrice}</td>
                        <td>
                           <a class='mr-2' href='javascript:;' title='Thông tin chi tiết' onclick='reportBills.showBillDetails(${v.idOrder},"${v.dateJoinView}","${v.dateExport}",${v.totalPrice})' ><i class='fas fa-eye'></i></a>
                        </td>
                    </tr>`
                )
            })
            $('#total-revenue').html(`${total} ₫`);
        }
    })
};

reportBills.showBillDetails = function (idOrder, dateJoinView, dateExport, totalPrice) {
    $.ajax({
        url: "http://localhost:8080/api/billDetails/" + idOrder,
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            console.log(data);
            $('#report-bill-id').html(idOrder);
            $('#modalReportBillViewTile').html("Chi tiết hóa đơn");
            $('#report-bill-dateJoin').html(dateJoinView);
            $('#report-bill-dateExport').html(dateExport);
            $('#modalReportBillView').modal("show");
            $.each(data, function (i, v) {
                $('#report-bill-table').append(
                    `
                <tr>
                    <td>
                        ${v.nameProduct}
                    </td>
                    <td>
                        ${v.quantity}
                    </td>
                    <td>
                        ${v.priceEach}
                    </td>
                    <td>
                        ${v.quantity * v.priceEach}
                    </td>
                </tr>
                `
                )
            });
            $('#report-bill-totalPrice').html(`${totalPrice} đ`);
        }
    })
};

$(document).ready(function () {
        reportBills.showDatatable();
    }
);
