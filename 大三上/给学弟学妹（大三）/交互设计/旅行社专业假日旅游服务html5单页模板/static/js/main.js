//body preloader
$(window).on('load', function () {
    setTimeout(function () { // allowing 3 secs to fade out loader
        $('.page-loader').fadeOut('slow');
    }, 3500);
});

//On Scroll Header fixed to top
$(window).scroll(function () {
    if ($(window).scrollTop() >= 50) {
        $('header').addClass('fixed-top smooth');
    }
    else {
        $('header').removeClass('fixed-top smooth');
    }
});

//menu button toggel
$('.menu-toggle').on('click', function () {
    $('.btn-wrapper').toggleClass('menu--is-revealed');
});

// menu link click close collapse
$('.navbar-collapse a').click(function () {
    $(".navbar-collapse").collapse('hide');
    $(".btn-wrapper").toggleClass('menu--is-revealed');
});

//Menu Droup Down on hover
$('.dropdown').hover(function () {
    $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
}, function () {
    $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
});

// Add active class to the current button (highlight it)
$(document).ready(function () {
    $('#nav li a').click(function (e) {

        $('#nav li.active').removeClass('active');

        var $parent = $(this).parent();
        $parent.addClass('active');
        e.preventDefault();
    });
});

//promo Slider
$('#carouselPromo').owlCarousel({
    loop: true,
    margin: 15,
    autoplay: true,
    autoplayTimeout: 5000,
    autoplayHoverPause: true,
    nav: true,
    navText: ["<i class='bi bi-arrow-left-short'></i>", "<i class='bi bi-arrow-right-short'></i>"],
    responsiveClass: true,
    responsive: {
        0: {
            items: 1
        },
        600: {
            items: 2
        },
        1000: {
            items: 4
        }
    }
});

//special offer Slider
$('#carouselOffer').owlCarousel({
    loop: true,
    margin: 15,
    autoplay: true,
    autoplayTimeout: 5000,
    autoplayHoverPause: true,
    dots: false,
    nav: true,
    navText: ["<i class='bi bi-arrow-left-short'></i>", "<i class='bi bi-arrow-right-short'></i>"],
    responsiveClass: true,
    responsive: {
        0: {
            items: 1
        },
        600: {
            items: 2
        },
        1000: {
            items: 3
        }
    }
});

//Travel Data calender
$(function () {
    $("#datepicker").datepicker();
});

//testimonial slider
$('#carouselReview').owlCarousel({
    loop: true,
    margin: 15,
    autoplay: true,
    autoplayTimeout: 5000,
    autoplayHoverPause: true,
    nav: false,
    responsiveClass: true,
    responsive: {
        0: {
            items: 1
        },
        600: {
            items: 2
        },
        1000: {
            items: 3
        }
    }
});

//stats
(function ($) {
    $.fn.countTo = function (options) {
        options = options || {};

        return $(this).each(function () {
            // set options for current element
            var settings = $.extend({}, $.fn.countTo.defaults, {
                from: $(this).data('from'),
                to: $(this).data('to'),
                speed: $(this).data('speed'),
                refreshInterval: $(this).data('refresh-interval'),
                decimals: $(this).data('decimals')
            }, options);

            // how many times to update the value, and how much to increment the value on each update
            var loops = Math.ceil(settings.speed / settings.refreshInterval),
                increment = (settings.to - settings.from) / loops;

            // references & variables that will change with each update
            var self = this,
                $self = $(this),
                loopCount = 0,
                value = settings.from,
                data = $self.data('countTo') || {};

            $self.data('countTo', data);

            // if an existing interval can be found, clear it first
            if (data.interval) {
                clearInterval(data.interval);
            }
            data.interval = setInterval(updateTimer, settings.refreshInterval);

            // initialize the element with the starting value
            render(value);

            function updateTimer() {
                value += increment;
                loopCount++;

                render(value);

                if (typeof (settings.onUpdate) == 'function') {
                    settings.onUpdate.call(self, value);
                }

                if (loopCount >= loops) {
                    // remove the interval
                    $self.removeData('countTo');
                    clearInterval(data.interval);
                    value = settings.to;

                    if (typeof (settings.onComplete) == 'function') {
                        settings.onComplete.call(self, value);
                    }
                }
            }

            function render(value) {
                var formattedValue = settings.formatter.call(self, value, settings);
                $self.text(formattedValue);
            }
        });
    };

    $.fn.countTo.defaults = {
        from: 0,               // the number the element should start at
        to: 0,                 // the number the element should end at
        speed: 1000,           // how long it should take to count between the target numbers
        refreshInterval: 100,  // how often the element should be updated
        decimals: 0,           // the number of decimal places to show
        formatter: formatter,  // handler for formatting the value before rendering
        onUpdate: null,        // callback method for every time the element is updated
        onComplete: null       // callback method for when the element finishes updating
    };

    function formatter(value, settings) {
        return value.toFixed(settings.decimals);
    }
}(jQuery));

jQuery(function ($) {

    // start all the timers
    $('.timer').each(count);

    // restart a timer when a button is clicked
    $(window).scroll(function () {
        console.log($(window).scrollTop());
        if ($(window).scrollTop() > 700 && $(window).scrollTop() < 850) {
            $('.timer').each(count);
        }
    });

    function count(options) {
        var $this = $(this);
        options = $.extend({}, options || {}, $this.data('countToOptions') || {});
        $this.countTo(options);
    }
});

// video frame open popup
jQuery(document).ready(function ($) {
    // Define App Namespace
    var popup = {
        // Initializer
        init: function () {
            popup.popupVideo();
        },
        popupVideo: function () {

            $('.video_model').magnificPopup({
                type: 'iframe',
                mainClass: 'mfp-fade',
                removalDelay: 160,
                preloader: false,
                fixedContentPos: false,
                gallery: {
                    enabled: true
                }
            });

            // Image Gallery Popup
            $('.gallery_container').magnificPopup({
                delegate: 'a',
                type: 'image',
                mainClass: 'mfp-fade',
                removalDelay: 160,
                preloader: false,
                fixedContentPos: false,
                gallery: {
                    enabled: true
                }
            });

        }
    };
    popup.init($);
});

//On Scroll back to top
$(window).on('scroll', function () {

    // Back Top Button
    if ($(window).scrollTop() > 500) {
        $('.scrollup').addClass('back-top');
    } else {
        $('.scrollup').removeClass('back-top');
    }
});
// On Click Section Switch
// used for back-top
$('[data-type="section-switch"]').on('click', function () {
    if (location.pathname.replace(/^\//, '') === this.pathname.replace(/^\//, '') && location.hostname === this.hostname) {
        var target = $(this.hash);
        if (target.length > 0) {

            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            $('html,body').animate({
                scrollTop: target.offset().top
            }, 1000);
            return false;
        }
    }
});







