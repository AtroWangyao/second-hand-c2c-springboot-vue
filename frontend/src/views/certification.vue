<template>
    <div>
        <div class="title">
          <h2>实名认证</h2> 
          <p>在这里你可以进行实名认证,只有实名认证的用户,才可以进行商品售卖</p>
        </div>
        <div class="message" v-if="certification.status != -1 ">
          <div v-if= "certification.status == 3" >
            <p>不好意思,您的审核请求已被退回</p>
            <p>退回理由:<span style="color: red; font-style: italic;">{{certification.message}}</span></p>
          </div>
          <div v-if= "certification.status == 2" >
            <p>恭喜您已经通过了实名认证</p>
            <p>尊敬的:<span style="color: red; font-style: italic;"> {{certification.realName}} </span>先生</p>
          </div>
          <div v-if= "certification.status == 1" >
            <p>您的审核信息已上交,请耐心等待</p>
            <p>尊敬的:<span style="color: red; font-style: italic;"> {{certification.realName}} </span>先生</p>
          </div>
        </div>
        <div class="form" v-if="(certification.status != 2 && certification.status != 1)">
            <el-form ref="certificationFormRef" :model="certificationForm" label-width="120px">
                <el-form-item label="真实姓名" >
                    <el-input v-model="certificationForm.realName" ></el-input>
                </el-form-item>
                <el-form-item label="身份证号">
                    <el-input v-model="certificationForm.idCardNumber" ></el-input>
                </el-form-item>
                <el-form-item label="身份证正面图">
                    <!--  :http-request 保留原有参数情况下 新增参数type:main -->
                    <el-upload
                    ref="upload1"
                    action="#"
                    list-type="picture-card"
                    :auto-upload="false"
                    :file-list="IDCard1"
                    class="Upload hideUpload"
                    :http-request="uploadFile"
                    :on-change="(file,fileList)=>onChange(file,fileList,1)" 
                    >
                        <i slot="default" class="el-icon-plus"></i>
                        <div slot="file" slot-scope="{file}" >
                            <img
                              class="el-upload-list__item-thumbnail"
                              :src="file.url" alt=""
                              
                            >
                            <span class="el-upload-list__item-actions">
                              <span
                                class="el-upload-list__item-preview"
                                @click="changeIDCard(file,1)"
                              >
                                <i class="el-icon-sort" style=" transform: rotate(90deg);"></i>
                              </span>
                            </span>
                          </div>
                    </el-upload>
                </el-form-item>
                <el-form-item label="身份证背面图">
                    <el-upload
                    ref="upload2"
                    action="#"
                    list-type="picture-card"
                    :auto-upload="false"
                    :file-list="IDCard2"
                    class="Upload hideUpload"
                    :http-request="uploadFile"  
                    :on-change="(file,fileList)=>onChange(file,fileList,2)"   
                    >
                        <i slot="default" class="el-icon-plus"></i>
                        <div slot="file" slot-scope="{file}" >
                            <img
                              class="el-upload-list__item-thumbnail"
                              :src="file.url" alt=""
                              
                            >
                            <span class="el-upload-list__item-actions">
                              <span
                                class="el-upload-list__item-preview"
                                @click="changeIDCard(file,2)"
                              >
                                <i class="el-icon-sort" style=" transform: rotate(90deg);"></i>
                              </span>
                            </span>
                          </div>
                    </el-upload>
                </el-form-item>
            
            
                <el-form-item>
                <el-button type="primary" @click="onSubmit">提交</el-button>
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
        fileList:[],
        certificationForm: {
            id: '',
            userId: getUser().id,
            status: '',
            picture1: '',
            picture2: '',
            message: '',
            idCardNumber: '',
            realName: '',
        },
        certification:{status:-1}, //默认状态是-1,代表没有获取到相关认证信息(从数据库中)
        IDCard1:[{      id:'', url:this.$imgPath + "身份证正面.jpg",type: 4,}],
        IDCard2:[{      id:'', url:this.$imgPath + "身份证背面.jpg",type: 4,}],
    }
  },
  created() {
    this.getCertification();
    this.checkUser();//为了审核信息及时响应,每次来到这个页面都重新获取一次user
  },
  methods: {

    async changeIDCard(param,type){
        this.$refs['upload'+type].$refs['upload-inner'].handleClick();//触发选择文件框.
        console.log(param);
        // if(type == 1)
        // this.IDCard1 =[param];
    },
    onChange(file,fileList,type){
        console.log(file)
        if(type == 1){
            this.IDCard1 =[file];//保证文件数组里,只有一张头像
        }
        else{
            this.IDCard2 =[file];//保证文件数组里,只有一张头像
        }
    },
    async uploadFile(param){
      this.fileList.append('file', param.file);
      console.log(this.fileList);
      
    },
    async onSubmit(){
        //console.log(this.certificationForm);
        this.fileList = new FormData()
        // good.saleDate = time.toLocaleString( );
        this.$refs.upload1.submit();
        this.$refs.upload2.submit();
        const {data:uploadRes} = await this.$axios.post("user/uploadIDCard",this.fileList);
        this.certificationForm.picture1 = uploadRes.pictureName0;
        this.certificationForm.picture2 = uploadRes.pictureName1;
        this.certificationForm.status = 1;
        console.log(this.certificationForm);
        if(this.certification.status != -1){//如果之前拿到过实名验证信息(默认的certification被覆盖掉,status不为-1时),就更新这个.
          const {data:res} = await this.$axios.post("user/updateCertification",this.certificationForm);
          if(res == 'suc'){
              this.$message.success("更新成功");
              location.reload();//刷新
            }else{
              
              this.$message.error("更新失败");
            }
          }else{//否则的话,就新增新的实名验证信息
            const {data:res} = await this.$axios.post("user/addCertification",this.certificationForm);
            if(res == 'suc'){
            location.reload();//刷新
              this.$message.success("提交成功");
          }else{
              
              this.$message.error("提交失败");
          }
        }

    },
    async getCertification(){
        
        const {data:res} = await this.$axios.get("user/getCertification?userId="+getUser().id);
        if(res.data){
          this.certification = res.data;
          this.certificationForm = JSON.parse(JSON.stringify(this.certification)); 
          console.log("sda");
        }
        console.log(this.certificationForm);
        // this.IDCard1 = [{      id:'', url:this.$imgPath +  res.data.picture1,type: 4,}];
        // this.IDCard2 = [{      id:'', url:this.$imgPath +  res.data.picture2,type: 4,}];
    },
    async checkUser(){
      const {data:res} = await this.$axios.get("user/getUserById?userId="+getUser().id);
      if(getUser().type != res.data.type){//如果角色身份变动的话
        setUser(res.data);
        location.reload();//刷新
      }
    }

  },      
}
</script>
<style lang="less" scoped>
  /* 主图的图片框 */
  .Upload .el-upload {
    width:400px;
    position: relative;
  }
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
    ::v-deep .el-upload-list--picture-card .el-upload-list__item{
        width: 400px;
        height: 250px;
        line-height: 100px;
    }
                /* ::v-deep .el-upload-list--picture-card .el-upload-list__item-thumbnail{
                    width: 400px;
                    height: 100px;
                    line-height: 100px;
                } */

    .message{
      border-radius: 45px;
      padding:50px;
      width:30%;
      background-color: rgba(0, 0, 0, 0.705);
      margin:100px auto;
      color:white
    }
    /* 取消上传动画 */
    ::v-deep .el-upload-list__item {  
      transition: none !important;
    }
</style>