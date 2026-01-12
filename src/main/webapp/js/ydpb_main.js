$(document).ready(function(){

    $('.main3_dong').on('click', function() {
        $(this).toggleClass('checked');
        $('.main3_contents').removeClass('show');
        $('.m3dong').addClass('show');
    });
    $('.main3_gu').on('click', function() {
        $(this).toggleClass('checked');
        $('.main3_contents').removeClass('show');
        $('.m3gu').addClass('show');
    });

});