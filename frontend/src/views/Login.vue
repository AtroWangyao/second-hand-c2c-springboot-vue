<template>
    
    <div class="loginContainer">
        <h1 style="text-align: center;">登录</h1>
        <el-divider></el-divider>
        <div class="imgDiv">
            <img src="../assets/picture/旧电视3.jpg" />
        </div>
        <div class="loginFormDiv">
            <el-form ref="loginFormRef" :model="loginForm" label-width="80px">
                <el-form-item label="登录账号">
                    <el-input v-model="loginForm.loginName"></el-input>
                </el-form-item>
                <el-form-item label="登录密码">
                    <el-input v-model="loginForm.loginPassword" type="password"></el-input>
                </el-form-item>
                <el-form-item>
                    <div>
                        <el-button class="floatRight" @click="goRegister()">注册</el-button>
                        <el-button type="primary" @click="login()" class="floatRight">登录</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
        
        
    </div>

</template>
<script>
export default {
    data() {
        return {
            loginForm:{
                loginName:'',
                loginPassword:'',

            }
        }
    },
    methods:{
       
        login(){
            this.$refs.loginFormRef.validate(async valid=>{
                const {data:res} = await this.$axios.post("login",this.loginForm);
                if(res.res == "suc")
                {
                    console.log(res.user);
                    window.sessionStorage.clear();
                    window.sessionStorage.setItem("user",JSON.stringify(res.user));//存储user对象
                    this.$message.success("suc");
                    this.$router.push({path:"/home"});
                    location.reload();//shuaxin
                    //var arr = sessionStorage.getItem('arr');

                }
                else
                {
                    this.$message.error("def");
                }
            })

        },
        goRegister(){
            this.$router.push({path:"/register"});
        }
    }
}
</script>
<!-- 其CSS将仅应用于当前组件的元素 -->
<style lang="less" scoped>
.loginContainer{
    margin: 70px auto 0  auto;
    /* background-color: deepskyblue; */
    height:500px;
    width:1300px;
}
.imgDiv{
    /* background-color: beige; */
    /* width:500px; */
    /* height:400px; */
    display: inline-block;
    vertical-align: top;
    margin-right: 100px;
    /* border-right:1px black solid; */
}
.imgDiv img{
    width:500px;
    /* height:400px; */
}
.loginFormDiv{
    padding-top: 100px;
    vertical-align: top;
    width:500px;
    display: inline-block;
}

</style>