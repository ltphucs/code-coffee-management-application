$(document).ready(function () {
  $(".table-item").click(function () {
    $(".order-list").addClass("d-block");
  });
  $(".order-list-cancel-btn").click(function () {
    $(".order-list").removeClass("d-block");
  });
  $(".table-add-cancel-btn").click(function () {
    $(".table-add-form").removeClass("d-block");
  });
  $(".table-add-btn").click(function () {
    $(".table-add-form").addClass("d-block");
  });
});