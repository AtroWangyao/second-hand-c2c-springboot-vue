<template>
    <div>
        <div id="black">
            <div class="floatLeft">
                <span class="tel"><i class="el-icon-phone"></i>155-2686-1676 </span>
            </div>
            <div v-if="isLogin">
                <div class="avatarDiv">
                    <el-avatar class="avatar" :size="40" shape="circle" :src=avatarUrl></el-avatar>
                </div>
                <div class="nameDiv">
                    <el-dropdown>
                        <span class="el-dropdown-link">
                            <span class="white">{{userName}}</span>
                            <i class="userNameDown el-icon-arrow-down white" ></i>
                        </span>
                        <template #dropdown>
                            <el-dropdown-menu>
                               

   
                            <router-link to="/admin/personalInformation"><el-dropdown-item> 我的资料</el-dropdown-item></router-link>
                            <router-link to="/admin/personalAddress"><el-dropdown-item> 我的地址</el-dropdown-item></router-link>
                            <router-link to="/admin/collectionList"><el-dropdown-item> 我的收藏</el-dropdown-item></router-link>
                            <router-link to="/admin/orderList"><el-dropdown-item> 我的订单</el-dropdown-item></router-link>
                            <el-dropdown-item @click.native="logout" divided>
                                退出登录
                            </el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>
            </div>
            <div v-else>
                <div class="loginDiv navStyle">
                    <router-link to="/login">登录</router-link>
                </div>
                <div class="registerDiv navStyle">
                    <router-link to="/register">注册</router-link>
                </div>
            </div>
            

        </div>
    </div>
</template>
<script>
import { getUser } from '../assets/js/comn.js'
export default {
    data() {
        return {
            isLogin:false,
        }
    },
    watch: {
        '$route' (to, from) {
            console.log(this.$route.params)
            if(getUser()){
                this.isLogin = true;
            }
        },
    },
    mounted() {
        
        if(getUser()){
            this.isLogin = true;
        }
    },
    computed: {
       
        userName(){
            return getUser().userName;
        },
        avatarUrl(){
            return this.$imgPath + getUser().photo;
        }
    },
    methods: {
        logout(){
            console.log("out");
            window.sessionStorage.clear();
            // this.$router.push({path:"/home"});
            // location. reload();
            this.isLogin = false;
        },
    },
}
</script>
<style lang="less" scoped>
    @import "~@/assets/css/global.css";
    #black{
        background-color:#1b1f23;
        height:40px;
        position: relative;
    }
    .avatarDiv{
        position: absolute;
        right:20px;
        /* float: right; */
        display: inline-block; 
        /* float:right;  */
        vertical-align: top;
    }
    .nameDiv{
        cursor: pointer;
        position: absolute;
        right:70px;
        /* color:white; */
        /* display: inline-block;  */
        height:40px;
        line-height: 40px;
        /* background-color: beige; */
    }
    .tel{
        padding-left: 30px;
        color: rgb(224, 224, 224);
        font-size: 15px;
        font-weight: 500;
        line-height: 40px;
    }
    .white{
        color:white;
    }
    .registerDiv{
        position: absolute;
        right:0;
        display: inline-block; 
        vertical-align: top;
        line-height: 40px;
        height:40px;
    }
    .loginDiv{
        position: absolute;
        right:40px;
        height:40px;
        line-height: 40px;
    }
    a{
        /* outline: none; */
        text-decoration: none;
    }


    nav{
        text-align: right;
    }
    .navStyle a{
        position: relative;
        margin: 2px 20px;
        outline: none;
        color: rgb(224, 224, 224);
        opacity: 0.9;
        text-align: center;
        text-transform: uppercase;
        text-decoration: none;
        font-size: 15px;
        font-weight: 500;
    }
    .navStyle a:hover{
        color: rgb(255, 255, 255);
    }
    
</style>