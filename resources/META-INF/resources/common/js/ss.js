var el_top = document.querySelector("#top"),
    el_search = document.querySelector("#search");
var height = el_search.offsetTop,
    flag = true;

document.addEventListener("scroll", function() {
    if (document.body.scrollTop >= height) {
        if (flag) {
            flag = false;
            el_top.classList.add("active");
            el_search.classList.add("fixed");
        } 
    } else {
        if (!flag) {
            flag = true;
            el_top.classList.remove("active");
            el_search.classList.remove("fixed");
        }
    }
}, false);