<template>
    
    <div class="registerContainer">
        <h1 style="text-align: center;">注册</h1>
        <el-divider></el-divider>
        <div class="imgDiv">
            <img src="../assets/picture/旧电视2.jpg" />
        </div>
        <div class="registerFormDiv">
            <el-form ref="registerFormRef" :rules="rules":model="registerForm" label-width="80px">
                <el-form-item label="登录账号" prop="loginName">
                    <el-input v-model="registerForm.loginName"></el-input>
                </el-form-item>
                <el-form-item label="登录密码" prop="loginPassword">
                    <el-input v-model="registerForm.loginPassword" type="password"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="confirmPassword">
                    <el-input v-model="registerForm.confirmPassword" type="password"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" prop="phone">
                    <el-input v-model="registerForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="registerForm.email"></el-input>
                </el-form-item>
                <el-form-item>
                    <div>
                        <el-button class="floatRight">取消</el-button>
                        <el-button type="primary" @click="register()" class="floatRight">注册</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
        
        
    </div>

</template>
<script>
export default {
    data() {
        var phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/
        var validatePhone = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('号码不能为空!!'))
            }
            setTimeout(() => {
                if (!phoneReg.test(value)) {
                callback(new Error('格式有误'))
                } else {
                callback()
                }
            }, 1000)
        }
        return {
            registerForm:{
                userName:"新用户",
                sellerId:'',
                loginName:'',
                loginPassword:'',
                phone:'',
                addressId:6,//默认其它
                email:'',
                photo:'defaultAvatar.png',
                type:1,
                confirmPassword:'',
            },

            

            rules: {
                loginName: [
                { required: true, message: "用户名不能为空", trigger: "change" },
                { min: 3, max: 15, message: "长度在 3 到 15 个字符", trigger: "blur" }
                ],
                email: [
                {
                    type: "email",
                    required: true,
                    message: "邮箱格式不正确",
                    trigger: "blur"
                }
                ],
                loginPassword: [
                    { required: true, message: "密码不能为空", trigger: "blur" },
                    { min: 6, max: 15, message: "长度在 6 到 15 个字符", trigger: "blur" }
                ],
                confirmPassword: [
                    { required: true, message: "确认密码不能为空", trigger: "blur" },
                    {
                        min: 6,
                        max: 15,
                        message: "长度在 6 到 15 个字符",
                        trigger: "blur"
                    },
                    {
                        validator:(rule,value,callback)=>{
                            if(value===''){
                                callback(new Error('请再次输入密码'))
                            }else if(value!==this.registerForm.loginPassword){
                                callback(new Error('两次输入密码不一致'))
                            }else{
                                callback( )
                            }
                        },
                    }
                ],
                phone: [
                    { required: true, message: "电话不能为空", trigger: "blur" },
                    
                    { validator: validatePhone, trigger: "blur" }
                ]
            }
        }
    },
    methods:{
        
        register(){
            this.$refs.registerFormRef.validate(async valid=>{
                if(!valid){
                    return;
                }
                const {data:res} = await this.$axios.post("register",this.registerForm); //这里不是很懂data:res是什么语法,回看(好像是解构赋值) 嗯..是解构赋值..起了个data的别名res
                if(res == "suc")
                {
                    // console.log(res.user);
                    // window.sessionStorage.setItem("user",JSON.stringify(res.user));//存储user对象
                    this.$message.success("suc");
                    this.$router.push({path:"/home"});
                    //var arr = sessionStorage.getItem('arr');

                }
                else
                {
                    this.$message.error("def");
                }
            })

        },
    }
}
</script>
<!-- 其CSS将仅应用于当前组件的元素 -->
<style lang="less" scoped>
.registerContainer{
    margin: 70px auto 0  auto;
    /* background-color: deepskyblue; */
    height:500px;
    width:1300px;
}
.imgDiv{
    /* background-color: beige; */
    width:500px;
    height:400px;
    display: inline-block;
    vertical-align: top;
    margin-right: 100px;
    /* border-right:1px black solid; */
}
.imgDiv img{
    width:500px;
    /* height:400px; */
}
.registerFormDiv{
    padding-top: 20px;
    vertical-align: top;
    width:500px;
    display: inline-block;
}

</style>