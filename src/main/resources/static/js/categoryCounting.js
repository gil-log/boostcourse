function countingCategory(categoryId){
    $.ajax({
        url: "/api/displayinfos?categoryId=" + categoryId,
        data: categoryId,
        type:"GET",
        cache: false
    }).done(function (fragment) {
        $("#categoryCounting").text(fragment.totalCount);


        var init = "<div id = \"productListSpotInit\"><div class=\"jumbotron\" id = \"productListSpot\">"+"</div></div>";
        $("#productListSpotInit").replaceWith(init);

        $("#startCount").val(2);


        var products = fragment.products;
        var infos = "";

        for(var i = 0; i <products.length; i++){
            infos += "<tr>\n" +
                "                <td>"+products[i].name+"</td>\n" +
                "                <td>"+products[i].placeName+"</td>\n" +
                "                <td onclick=\"location.href=\'/api/displayinfos?displayId="+products[i].displayinfoid+"'\">"+products[i].content+"</td>\n" + "</tr>\n"
        }

        var html =
            "<div class=\"jumbotron\" id = \"productListSpot\">"+
            "        <table class=\"table table-bordered table-striped\">\n" +
            "            <thead>\n" +
            "            <tr>\n" +
            "                <td>행사 제목</td>\n" +
            "                <td>행사 장소</td>\n" +
            "                <td>행사 설명</td>\n" +
            "            </tr>\n" +
            "            </thead>\n" +
            "\n" +
            "            <tbody>\n" +

            infos +


            "            </tbody>\n" +
            "\n" +
            "        </table>"+"</div>";

        $("#productListSpot").replaceWith(html);
        $("#categoryId").val(categoryId);
    });
}