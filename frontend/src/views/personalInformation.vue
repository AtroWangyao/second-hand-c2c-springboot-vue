<template>
    <div>
        <div class="title">
          <h2>我的资料</h2> 
          <p>在这里可以您可以更改您的个人信息,包括名称,头像,电话,邮箱等</p>
        </div>
        <div class="form">
          <el-form ref="userForm" :model="userForm" label-width="150px">
            <el-form-item label="用户ID" >
              <el-input v-model="userForm.id" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="用户名">
              <el-input v-model="userForm.loginName" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="用户昵称">
              <el-input v-model="userForm.userName"></el-input>
            </el-form-item>
            <el-form-item label="用户电话">
              <el-input v-model="userForm.phone" ></el-input>
            </el-form-item>
            <el-form-item label="用户邮箱">
              <el-input v-model="userForm.email"></el-input>
            </el-form-item>
            <el-form-item label="用户所属城市(区域)">
              <el-select v-model="userForm.addressId" placeholder="请选择所属的城市(区域)">
                <el-option
                v-for="item in cityList"
                :key="item.id"
                :label="item.name"
                :value=item.id>
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="头像">
              <el-upload
              ref="uploadRef"
              action="#"
              list-type="picture-card"
              :auto-upload="false"
              class="hideUpload"
              :file-list="avatarList"
              :http-request="uploadFile"
              :on-change="onAvatarChange" 
              >
                <i slot="default" class="el-icon-plus"></i>
                <div slot="file" slot-scope="{file}">
                    <img
                      class="el-upload-list__item-thumbnail"
                      :src="file.url" alt=""
                    >
                    <span class="el-upload-list__item-actions">
                      <span
                        class="el-upload-list__item-preview"
                        @click="changeAvatar(file)"
                      >
                        <i class="el-icon-sort" style=" transform: rotate(90deg);"></i>
                      </span>
                    </span>
                  </div>
              </el-upload>
            </el-form-item>     
            <el-form-item>
              <el-button type="primary" @click="updateUser">修改</el-button>
              <el-button  @click="">取消</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
    </template>
<script>
import { getUser,setUser } from '../assets/js/comn.js'
export default {
  data() {
    return {
      userForm: {
        id: '',
        loginName: '',
        loginPassword: '',
        userName: '',
        phone: '',
        addressId: '',
        email: '',
        photo: '',
      },
      cityList:[],//城市列表
      avatarFileList:[],//头像图文件数组
      avatarList:[],//头像图数组(照片墙)
      mainPhotoNum:2,
      avatarIsChanged:false, //头像是否被改变,用于判断提交后是否上传并更改头像
      user:'',
    }
  },
  created() {
    this.getCityList();
    this.user = getUser()
    if(this.user){
        this.userForm = this.user;
    }
    this.avatarList.push({
                    id:this.user.id,
                    url:this.$imgPath + this.user.photo,
                    type: 3,
    })

  },
  methods: {
    async uploadFile(params){//这个方法在点击上传后才会执行(一次或以上)   params可能是每次上传的东西(包括文件和一些别的东西)
        this.avatarFileList.append('file', params.file);
        console.log(params);
    },
    async changeAvatar(param){
        this.$refs['uploadRef'].$refs['upload-inner'].handleClick();//触发选择文件框.
    },
    onAvatarChange(file,fileList){
        this.avatarList =[file];//保证文件数组里,只有一张头像
        this.avatarIsChanged = true;//标记一下头像已经改变
        // console.log( this.avatarList );
    },
    async getCityList(){
        const{data:result} = await this.$axios.get("address/allCity"); 
        this.cityList = result.data;
    },
    async updateUser(){
        console.log(this.userForm);
        this.avatarFileList = new FormData();//这个必须要,否则没办法append
        this.$refs.uploadRef.submit();
        if(this.avatarIsChanged){
            this.avatarFileList.append('type', 3); //标记一下上传的图片类型,是3 头像
            const {data:avatarSrc} = await this.$axios.post("user/upload",this.avatarFileList); //添加头像,回调头像名称
            this.userForm.photo = avatarSrc;
        }
        const {data:res} = await this.$axios.post("user/update",this.userForm);
        if(res == 'suc'){
            this.$message.success("suc");
            // console.log(this.userForm);
            const {data:userData} = await this.$axios.post("user/getUserById?userId="+this.userForm.id);
            
            setUser(userData.data);
            this.user = getUser();
            console.log(this.user);
            this.$router.push({path:"/admin/personalInformation"});
            location.reload();//shuaxin
        }else{
            this.$message.console.error("def");
        }
    },
    getAvatar(){
        return this.$imgPath + this.userForm.photo;
    },
    
  },      
}
</script>
<style lang="less" scoped>
  /* 主图的图片框 */
  /* .avatarUpload .el-upload {
    width:170px;
    position: relative;
  } */
  .bar{
    width:50%;
  }
  .title{
    text-align: center;
  }
  .form{
    /* background-color:blue; */
    /* margin-left:100px; */
    width:1200px;
    margin:0 auto;
  }
  .hideUpload /deep/ .el-upload{
        display:none;   /* 上传按钮隐藏 */
  }
  /* 取消上传动画 */
  ::v-deep .el-upload-list__item {  
      transition: none !important;
  }

</style>