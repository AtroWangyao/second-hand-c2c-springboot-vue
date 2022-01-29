<template>
    <div>
      <div class="title">
        <h2>用户列表</h2> 
        <p>此处包含了所有的非管理员用户,您可以对他们进行管理</p>
      </div>
        <el-table
      :data="userList.filter(data => !search || data.userName.toLowerCase().includes(search.toLowerCase())|| data.loginName.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%;">
        <el-table-column
          prop="id"
          label="用户ID"
          width="100">
        </el-table-column>
        <el-table-column
          prop="type"
          label="用户类型"
          width="100"
          :formatter="typeFormat">
        </el-table-column>
        <el-table-column
          prop="userName"
          label="姓名"
          width="100">
        </el-table-column>
        <el-table-column
          prop="loginName"
          label="用户名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="addressId"
          label="当前所属区域"
          width="120"
          :formatter="addressFormat">
        </el-table-column>
        <el-table-column
          prop="phone"
          label="电话"
          width="180">
        </el-table-column>
        <el-table-column
          prop="email"
          label="邮箱"
          width="180">
        </el-table-column>
          <el-table-column
          prop="photo"
          label="头像"
          width="80">
          <template slot-scope="scope">
            <img  :src="getAvatarInTable(scope.row.photo)" alt="" style="width: 50px;height: 50px;text-align: center;">
          </template>
        </el-table-column>
        <el-table-column
          prop="certification.status"
          label="状态"
          width="180">
          <template slot="header" slot-scope="scope">
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入关键字搜索"/>
            </template>
            <template #default="scope"  >
              <el-button v-if="scope.row.certification.status == 1" @click="handleCertification(scope.row)" type="text" size="medium" >审核实名认证</el-button>
            </template>
        </el-table-column>
        <el-table-column
          header-cell-class-name="conmandStyle"
          fixed="right"
          label="操作"
          width="200">
          <template slot="header" slot-scope="scope">
            <el-button type="primary" @click="OpenDialogForm('add')">新增用户</el-button>
            </template>
          <template #default="scope"  >
            <el-button @click="editUser(scope.row)" type="text" size="medium">编辑</el-button>
            <el-button @click="deleteUser(scope.row)" type="text" size="medium" >删除</el-button>
          </template>
        </el-table-column>
    </el-table>

    
    <!-- 浮动窗口,新增和编辑 -->
    <el-dialog title="添加用户" :visible.sync="AddDialogFormVisible" width="50%" >
      <el-form :model="userForm" ref="userFormRef" label-width="180px">
        <el-form-item label="用户ID" prop="id" v-if="DialogType=='update'">
          <el-input v-model="userForm.id" :disabled=true></el-input>
        </el-form-item>
        <el-form-item label="登录账号" prop="loginName">
          <el-input v-model="userForm.loginName"></el-input>
        </el-form-item>
        <el-form-item label="登录密码" prop="loginPassword">
          <el-input v-model="userForm.loginPassword"></el-input>
        </el-form-item>
        <el-form-item label="用户名称" prop="userName">
          <el-input v-model="userForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="userForm.phone"></el-input>
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
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="userForm.email"></el-input>
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
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="AddDialogFormVisible = false">取 消</el-button>
          <el-button v-if="(DialogType == 'add')" type="primary" @click="addUser()">确 定</el-button>
          <el-button v-else type="primary" @click="updateUser()">更改</el-button>
        </span>
      </template>
    </el-dialog>
    <!-- 实名认证悬浮窗 -->
    <!-- 浮动窗口,新增和编辑 -->
    <el-dialog title="实名审核" :visible.sync="CertificationDialogFormVisible" width="50%" >
      <el-form :model="certificationForm" ref="certificationFormRef" label-width="180px">
        <el-form-item label="真实姓名" prop="realName">
          <el-input style="width: 50%;" v-model="certificationForm.realName"  :disabled=true></el-input>
        </el-form-item>
        <el-form-item label="身份证号码" prop="idCardNumber">
          <el-input style="width: 50%;" v-model="certificationForm.idCardNumber"  :disabled=true></el-input>
        </el-form-item>
        <el-form-item label="身份证正面照" prop="picture1" >
          <img :src="this.$imgPath + certificationForm.picture1" style="margin-top:20px;width: 400px;height: 250px;">
        </el-form-item>
        </el-form-item>
        <el-form-item label="身份证背面照" prop="picture2" >
          <img :src="this.$imgPath + certificationForm.picture2" style="margin-top:20px; width: 400px;height: 250px;">
        </el-form-item>
        <el-form-item label="备注" prop="message" >
          <el-input style="width: 80%;" type="textarea"  :autosize="{ minRows: 4, maxRows: 4}" v-model="certificationForm.message"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="CertificationDialogFormVisible = false">取 消</el-button>
          <el-button type="danger" @click="updateCertification(3)">不通过</el-button>
          <el-button type="primary" @click="updateCertification(2)">通过</el-button>
        </span>
      </template>
    </el-dialog>
    
    </div>
</template>
<script>
    export default {
      data() {
        return {
          search:'', //搜索框  
          DialogType:'',
          AddDialogFormVisible: false,//添加用户框是否显示
          CertificationDialogFormVisible:false,
          userList:[],//用户列表
          userForm: {//增加用户的表单数据
            id: '',
            loginName: '',
            loginPassword: '',
            userName: '',
            phone: '',
            addressId: '',
            email: '',
            photo: '',
            type:'1',
          },
          cityList:[],//城市列表
          avatarFileList:[],//头像图文件数组
          avatarList:[],//头像图数组(照片墙)
          avatarIsChanged:false, //头像是否被改变,用于判断提交后是否上传并更改头像
          certificationForm:{
            id: '',
            idCardNumber: '',
            message: '',
            picture1: '',
            picture2: '',
            realName: '',
            status: '',
            userId: '',
          }
        }
      },
      created() {
          this.getUserList();
          this.getCityList();
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
        //res是那行绑定的userList[那行]
        editUser(row){
          this.userForm = JSON.parse(JSON.stringify(row));
          console.log(this.DialogType);
          this.avatarList=[({
            id:row.id,
            url:this.$imgPath + row.photo,
            type: 3,
          })]
          this.OpenDialogForm('update');
        },
        OpenDialogForm(DialogType){
          if(DialogType=="add")
          {
            this.avatarList=[({ //放个占位头像
              id:'',
              url:this.$imgPath + 'avatar01.jpg',
              type: 3,
            })]
            this.DialogType = 'add';
          }
          else if(DialogType=="update")
          {
            this.DialogType = 'update';
          }
          this.AddDialogFormVisible = true;
        },
        async getUserList(){
          const{data:result} = await this.$axios.get("user/allUser"); 
          for(let i=0;i<result.data.length;i++){
            const{data:certificationRes} = await this.$axios.get("user/getCertification?userId=" + result.data[i].id); 
            if(certificationRes.data != null){
              this.$set(result.data[i],'certification',certificationRes.data);
            }
            else{
              this.$set(result.data[i],'certification',{status:0});
            }
          }
          this.userList = result.data;

        },
        //function(response, file, fileList)	 response 是后端返回的数据
        getAvatarInTable(imgPath){
          return this.$imgPath + imgPath;
        },
        handleCertification(row){
          console.log(row);
          this.certificationForm = JSON.parse(JSON.stringify(row.certification));
          this.CertificationDialogFormVisible = true;
          
        },
        addUser(){
          this.$refs.userFormRef.validate(async valid=>{//这里一定要异步处理,才能获取到后端的信息
                this.avatarFileList = new FormData();//这个必须要,否则没办法append
                this.$refs.uploadRef.submit();
                this.avatarFileList.append('type', 3); //标记一下上传的图片类型,是3 头像
                const {data:avatarSrc} = await this.$axios.post("user/upload",this.avatarFileList); //添加头像,回调头像名称
                this.userForm.photo = avatarSrc;
                const {data:res} = await this.$axios.post("user/add",this.userForm); //这里不是很懂data:res是什么语法,回看(好像是解构赋值) 嗯..是解构赋值..起了个data的别名res
                console.log(res);
                if(res == "suc")
                {
                  this.AddDialogFormVisible=false;
                  this.$message.success("添加成功");
                  this.getUserList();
                }
                else
                {
                  this.$message.error("添加失败");
                }
            })
        },
        updateUser(){
          this.$refs.userFormRef.validate(async valid=>{
                this.avatarFileList = new FormData();//这个必须要,否则没办法append
                this.$refs.uploadRef.submit();
                if(this.avatarIsChanged){
                  this.avatarFileList.append('type', 3); //标记一下上传的图片类型,是3 头像
                  const {data:avatarSrc} = await this.$axios.post("user/upload",this.avatarFileList); //添加头像,回调头像名称
                  this.userForm.photo = avatarSrc;
                }
                const {data:res} = await this.$axios.post("user/update",this.userForm); //这里不是很懂data:res是什么语法,回看(好像是解构赋值) 嗯..是解构赋值..起了个data的别名res
                console.log(res);
                if(res == "suc")
                {
                  
                  this.AddDialogFormVisible=false;
                  this.$message.success("更新成功");
                  this.getUserList();
                }
                else
                {
                  this.$message.error("更新失败");
                }
            })
        },
        updateCertification(status){//status是2是通过,status是3 是不通过
          this.$refs.certificationFormRef.validate(async valid=>{
              this.certificationForm.status = status;
              const {data:res} = await this.$axios.post("user/updateCertification",this.certificationForm); 
              console.log(res);
              if(res == "suc")
              {
                this.CertificationDialogFormVisible=false;
                this.$message.success("审核成功");
                if(status == 2){ //如果通过了审核的话,要把用户设为商家用户
                  const {data:UserRes} = await this.$axios.get("user/getUserById?userId=" + this.certificationForm.userId); 
                  // let user = UserRes;
                  // console.log("userRes", UserRes.data.type);
                  UserRes.data.type = 2;
                  const {data:UserUpdateRes} = await this.$axios.post("user/update",UserRes.data); 
                  if(UserUpdateRes == 'suc'){
                    this.$message.success("用户 " + UserRes.data.userName + " 已经设置为商家用户");
                  }else{
                    this.$message.error("用户 " + UserRes.data.userName + " 设置商家用户失败");
                  }
                }
                this.getUserList();
              }
              else
              {
                this.$message.error("审核失败");
              }
          })
        },
        //res是那行绑定的userList[那行]
        async deleteUser(res){
          console.log(res);
          const {data:result} = await this.$axios.post("user/delete",res); 
          console.log(result);
          if(result == "suc")
          {
            this.$message.success("删除成功");
            this.getUserList();
          }
          else
          {
            this.$message.error("删除失败");
          }
        },
        async getCityList(){
          const{data:result} = await this.$axios.get("address/allCity"); 
          this.cityList = result.data;
        },
        addressFormat(row, column) {
            let addressId = row[column.property];
            let index = this.cityList.findIndex((value)=>{return value.id == addressId})
            // console.log(this.cityList[index]);
            if(index != -1){
              return this.cityList[index].name;
            }
            return '';
        },
        typeFormat(row, column) {
            let type = row[column.property];
            switch(type){
              case 1:return "普通用户";
              case 2:return "商家用户";
              case 3:return "管理员用户";
            }
        },
      },     
    }
    
</script>

<style lang="less" scoped>
  .el-upload {
    display: block;
    margin:0 auto;
  }
  .avatar-uploader{
    margin:0 auto;
    width:180px;
  }
  .avatar-uploader .el-upload {
    margin:0 auto;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 180px;
    height: 180px;
    line-height: 180px;
    text-align: center;
  }
  .avatarPreview{
    margin-top:20px;
    margin-right:80px;
    width:200px;
  }
  .title{
      text-align: center;
  }
  .hideUpload /deep/ .el-upload{
        display:none;   /* 上传按钮隐藏 */
  }
  /* 取消上传动画 */
  ::v-deep .el-upload-list__item { 
    transition: none !important;
  }
  
</style>
