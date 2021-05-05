let reportProducts ={};

reportProducts.showDatatable=function (){
    $('#report-product').empty().append(
        `<h1 class="h3 mb-2 text-gray-800">Báo cáo sản phẩm</h1>
         <p class="mb-4"></p>
         <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary text-uppercase">Báo cáo sản phẩm</h6>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <div class="form-group">
                                    <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                                        <label class="col-form-label mr-2">Từ ngày</label>
                                        <input type="text" id="test-time1" class="form-control datetimepicker-input"
                                               data-target="#datetimepicker1"/>
                                        <div class="input-group-append" data-target="#datetimepicker1"
                                             data-toggle="datetimepicker">
                                            <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="form-group">
                                    <div class="input-group date" id="datetimepicker2" data-target-input="nearest">
                                        <label class="col-form-label mr-2">Đến ngày</label>
                                        <input type="text" id="test-time2" class="form-control datetimepicker-input"
                                               data-target="#datetimepicker2"/>
                                        <div class="input-group-append" data-target="#datetimepicker2"
                                             data-toggle="datetimepicker">
                                            <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <a href="#" class="btn btn-light btn-icon-split border">
                                    <span class="icon text-gray-600 bg-light">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                             viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                             stroke-linecap="round" stroke-linejoin="round"><path
                                                d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path><polyline
                                                points="22 4 12 14.01 9 11.01"></polyline></svg>
                                    </span>
                                    <span class="text" onclick="reportProducts.showProductExport()">Áp dụng</span>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body bd-t">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dateTableBills" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th colspan="3">Sản phẩm</th>
                                    <th colspan="2" style="text-align: right">Số lượng</th>
                                </tr>
                                </thead>
                                <tbody id="list-productExport">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>`
    )
};

reportProducts.showProductExport=function (){
    let dateObj={};
    dateObj.dateIn=$('#test-time1').val();
    dateObj.dateOut=$('#test-time2').val();
    console.log(dateObj);
    $.ajax({
        url:"http://localhost:8080/api/productExports/dateExport",
        method:"POST",
        dataType:"JSON",
        contentType: "application/json",
        data: JSON.stringify(dateObj),
        success:function (data){
            console.log(data);
            $('#list-productExport').empty();
            $.each(data,function (i,v){
                $('#list-productExport').append(
                    `<tr>
                        <td colspan="3">${v.nameProduct}</td>
                        <td colspan="2" style="text-align: right">${v.quantity}</td>
                    </tr>`
                )
            })
        }
    })
}

$(document).ready(function () {
    reportProducts.showDatatable();
});

