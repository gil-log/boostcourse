function moreDisplayInfo(){

    var start = $("#startCount").val() * 1;
    var categoryId = $("#categoryId").val();

    $.ajax({
        url: "/api/displayinfos?categoryId=" + categoryId+"&start="+start,
        type:"GET",
        cache: false
    }).done(function (fragment) {

        var next = start + 1;

        $("#startCount").val(next);

        var products = fragment.products;
        var infos = "";


        if(products.length != 0){

            for(var i = 0; i <products.length; i++){
                infos += "<tr>\n" +
                    "                <td>"+products[i].name+"</td>\n" +
                    "                <td>"+products[i].placeName+"</td>\n" +
                    "                <td>"+products[i].content+"</td>\n" + "</tr>\n"
            }

            var html =
                "<div class=\"jumbotron\" id=\"productListSpot"+next+"\">"+
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

            $("#productListSpotInit .jumbotron:last").after(html);
        }


    });
}