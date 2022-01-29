<template>
    <div class="top">
        <div class ="topRight">
            <!-- <div id="fullScreenButtonDiv"  >
                <i class="el-icon-rank"></i>
            </div> -->
            
            <div id="userNameDropDownDiv">
                <div id="avatarDiv"></div>
                <el-avatar class="avatar" :size="60" shape="square" :src=avatarUrl fit="contain"></el-avatar>
            </div>
            <div style="float:right;margin-right:20px">
                <el-button @click="back" type="danger">返回前台<i class="el-icon-s-home icon"></i></el-button>
            </div>
            <div style="float:right;">
                <el-button @click="fullScreen" type="primary">一键全屏<i class="el-icon-rank icon" style="    transform: rotate(45deg);"></i></el-button>
            </div>
            <div>
                <h3 class="userName">{{userName}}</h3>
            </div>
            <div>
                <h5 class="userName" style="color: skyblue;">{{userType}}</h5>
            </div>
        </div>
    </div>
</template>

<script>
import { getUser } from '../assets/js/comn.js'
export default {
    data() {
        return {
            isFullScreen : false,
            
        }
    },
    computed: {
        userName(){
           console.log(getUser());
           if(getUser())
               return getUser().userName;
           else
               return "用户没登录咋就进来后台了"
       },
       userType(){
        if(getUser()){
            let type = getUser().type;
            if(type==1) return "普通用户"
            if(type==2) return "商家用户"
            if(type==3) return "管理员"
        }
       },
       avatarUrl(){
            if(getUser())
                return this.$imgPath + getUser().photo;
            else
                return this.$imgPath + "avatar01.png";
       }
   },
    methods: {
        fullScreen()
        {
            //浏览器兼容用
            if(this.isFullScreen){
                if(document.exitFullscreen){
                    document.exitFullscreen();
                }
                else if(document.webkitCancelFullScreen){
                    document.webkitCancelFullScreen();
                }
                else if(document.mozCancelFullScreen)
                {
                    document.mozCancelFullScreen();
                }
                else if(document.msExitFullScreen)
                {
                    document.msExitFullScreen();
                }
            }
            else{
                let element = document.documentElement;
                if(element.requestFullscreen)
                {
                    element.requestFullscreen();
                }
                else if(element.webkitRequestFullScreen)
                {
                    element.webkitRequestFullScreen();
                }
                else if(element.mozRequestFullScreen)
                {
                    element.webkitRequestFullScreen();
                }
                else if(element.msRequestFullScreen)
                {
                    element.msRequestFullScreen();
                }
            }
            this.isFullScreen = !this.isFullScreen;
        },
        back(){
            this.$router.push({path:"/home"});
        }
    },
}
</script>

<style lang="less" scoped>
body{
    margin:0px;
    padding:0px;
}
.top{
    padding: 0px;
    /* text-align: center; */
    line-height: 60px;
    width: 100%;
    height: 100%;
    background-color:rgb(39,43,52);

    /* background-color: #39424f; */
}
.topRight div{
    display: inline-block;
    margin-left: 15px;
    /* color:black; */
    /* 没有文字的div位置才是初始的位置，将有内容的div的vertical-align设置为top（元素的顶端与行中最高元素的顶端对齐）,就可以实现这几个div在一行上。 */
    vertical-align:top;
}
#fullScreenButtonDiv{
    cursor: pointer;
    font-size: 30px;
    /* float:right; */
    color:white;
}
.el-dropdown-link{
    float:right;
    font-size: 21px;
    cursor: pointer;
    color:white;
}

.el-icon-arrow-a {
    font-size: 20px;
}
/* .topRight { */
    /* float:right; */
    /* height:100%; */
    /* width:300px; */
    
    /* background-color:white; */
/* } */
.icon{
    margin-left:7px;

    /* font-size:22px;
    vertical-align: middle; */
}
.userName{
    margin:0;
    color: white;
    vertical-align: middle;
    line-height: 60px;
}
</style>
    