


    $(document).ready(function(){
        $(window).on("scroll", function(){
            if ($(this).scrollTop() > 300) {
                $('.back-to-top').fadeIn();
            } else {
                $('.back-to-top').fadeOut();
            }
        });

        $('.back-to-top').on("click", function(){
            $("html, body").animate({ scrollTop: 0 }, 600);
            return false;
        });
    });

