$(document).ready(function () {
  let menuStack = false;
  $(".table-item").click(function () {
    if (!menuStack) {
      $(".order-list").addClass("d-block");
      menuStack = true;
    }
  });
  $(".order-list-cancel-btn").click(function () {
    $(".order-list").removeClass("d-block");
    menuStack = false;
  });
  $(".table-add-cancel-btn").click(function () {
    $(".table-add-form").removeClass("d-block");
    menuStack = false;
  });
  $(".table-add-btn").click(function () {
    if (!menuStack) {
      $(".table-add-form").addClass("d-block");
      menuStack = true;
    }
  });
});