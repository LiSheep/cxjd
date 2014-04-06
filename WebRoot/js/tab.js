
function tab(){
    $(".tabUl li:first-child").addClass("current");
    $(".tabCon").find("div.layout:not(:first-child)").hide();
    $(".tabCon div.layout").attr("id", function(){return idNumber("No")+ $(".tabCon div.layout").index(this)});
   $(".tabUl li").click(function(){
        var c = $(".tabUl li");
        var index = c.index(this);
        var p = idNumber("No");
        show(c,index,p);
   });
    
    function show(controlMenu,num,prefix){
        var content= prefix + num;
       $('#'+content).siblings().hide();
        $('#'+content).show();
        controlMenu.eq(num).addClass("current").siblings().removeClass("current");
    };
 
    function idNumber(prefix){
        var idNum = prefix;
        return idNum;
    };
}
