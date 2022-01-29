
export function rateAppearance(percentage){
    var result = "外表:";
    switch (true) {
        case (percentage < 1) :
            return result+"无评判标准";
        case (percentage < 20) :
            return result+"破烂不堪";
        case (percentage < 40) :
            return result+"磨损严重";
        case (percentage < 60) :
            return result+"稍有磨损";
        case (percentage <= 99) :
            return result+"几乎全新";
        case (percentage == 100) :
            return result+"崭新出厂";
    }
}
export function rateFunction(percentage){
    var result = "功能:";
    switch (true) {
        case (percentage < 1) :
            return result+"完全丧失功能";
        case (percentage < 20) :
            return result+"个别功能存在";
        case (percentage < 40) :
            return result+"功能基本丧失";
        case (percentage < 70) :
            return result+"部分功能丧失";
        case (percentage <= 99) :
            return result+"几乎正常";
        case (percentage == 100) :
            return result+"完全正常";
    }
}
export function ratePackage(percentage){
    // console.log("percentage");
    var result = "包装:";
    switch (true) {
        case (percentage < 1) :
            return result+"无包装";
        case (percentage < 20) :
            return result+"破烂不堪";
        case (percentage < 40) :
            return result+"磨损严重";
        case (percentage < 60) :
            return result+"中规中矩";
        case (percentage < 90) :
            return result+"稍有磨损";
        case (percentage <= 99) :
            return result+"几乎全新";
        case (percentage == 100) :
            return result+"崭新出厂";
    }
}
 function test()
{
    console.log("asdasd");
    return "sadasd";
}
export function getSrc(src){//
    return this.$imgPath + src;
}
export function getUser(){
    return JSON.parse(sessionStorage.getItem('user'));
}
export function setUser(data){
    window.sessionStorage.setItem("user",JSON.stringify(data));//存储user对象
}




