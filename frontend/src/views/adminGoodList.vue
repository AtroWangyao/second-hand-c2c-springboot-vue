<template>
    <div>
      <div class="title">
        <h2>商品列表</h2> 
        <p>这里包括了所有的商品,您可以更改它们,或者添加新的商品</p>
      </div>
        <el-table
      :data="goodList.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100% ">
        <el-table-column
          prop="id"
          label="商品ID"
          width="100">
        </el-table-column>
        <el-table-column
          prop="name"
          label="商品名称"
          width="200">
        </el-table-column>
        <el-table-column
          label="商品图片"
          width="180">
          <template slot-scope="scope">
            <img  :src="getGoodMainPicture(scope.row.pictureList)" alt="" style="width: 50px;height: 50px;text-align: center;">
          </template>
        </el-table-column>
        <el-table-column
          prop="price"
          label="商品价格"
          width="100">
        </el-table-column>
        <el-table-column
          prop="desc"
          label="商品描述"
          width="300">
        </el-table-column>
        <el-table-column
          prop="manufacturer"
          label="商品产商"
          width="200">
        </el-table-column>
        <el-table-column
          prop="appearanceValue"
          label="外观评估值"
          width="200">
        </el-table-column>
        <el-table-column
          prop="functionValue"
          label="功能评估值"
          width="300">
        </el-table-column>
        <el-table-column
          prop="packageValue"
          label="包装评估值"
          width="300">
        </el-table-column>
        <el-table-column
          prop="dealType"
          label="交易方式"
          width="300"
          :formatter="typeFormat"
          >
        </el-table-column>
        <el-table-column
          prop="purchaseDate"
          label="购买时间"
          width="300"
          :formatter="dateFormat">
        </el-table-column>
        <el-table-column
          prop="saleDate"
          label="出售时间"
          width="300"
          :formatter="dateFormat">
        </el-table-column>
        <el-table-column
          header-cell-class-name="conmandStyle"
          fixed="right"
          label="操作"
          width="200">
          <template slot="header" slot-scope="scope">
            <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
            </template>
          <template #default="scope"  >
            <el-button @click="editGood(scope.row)" type="text" size="medium">编辑</el-button>
            <el-button @click="deleteGood(scope.row)" type="text" size="medium" >删除</el-button>
          </template>
        </el-table-column>
    </el-table>

    
    <!-- <el-button type="text" @click="AddDialogFormVisible = true">打开嵌套表单的 Dialog</el-button> -->
    <!-- 浮动窗口,新增和编辑 -->
    <el-dialog title="商品信息" :visible.sync="AddDialogFormVisible" width="50%" @close="addFormClear()">
       <el-form ref="goodForm" :model="goodForm" label-width="120px">
            <el-form-item label="商品ID" >
              <el-input v-model="goodForm.id" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="商品名称">
              <el-input v-model="goodForm.name"></el-input>
            </el-form-item>
            <el-form-item label="商品价格">
              <el-input v-model="goodForm.price"></el-input>
            </el-form-item>
            <el-form-item label="商品折扣">
              <el-input v-model="goodForm.sprice"></el-input>
            </el-form-item>
            
            <el-form-item label="商品厂商">
              <el-input v-model="goodForm.manufacturer"></el-input>
            </el-form-item>
            <el-form-item label="商品标签">
              <el-select v-model="goodForm.tagList" value-key="id" multiple placeholder="请选择标签">
                <el-option
                  v-for="item in tagList"
                  :key="item.id"

                  :label="item.name"
                  :value="item">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="商品类别">
              <el-select v-model="goodForm.categoryId" placeholder="请选择商品所属的类别">
                <el-option
                v-for="item in categoryList"
                :key="item.id"
                :label="item.name"
                :value=item.id>
                </el-option>
              </el-select>
            </el-form-item>
            
            <!-- <el-form-item label="购买日期">
              <el-col :span="11">
                <el-date-picker type="time" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="goodForm.purchaseDate"  style="width: 100%;"></el-date-picker>
              </el-col>
            </el-form-item> -->
            
            <el-form-item label="交易方式">
              <el-checkbox-group v-model="dealTypeCheckGroup">
                <el-checkbox @change="chooseItem()" label="线上交易"  ></el-checkbox>
                <el-checkbox @change="chooseItem()" label="线下面交" ></el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="外表磨损评级">
              <div class="bar">
                <el-slider v-model="goodForm.appearanceValue"></el-slider>
              </div>
            </el-form-item>
            <el-form-item label="功能磨损评级">
              <div class="bar">
                <el-slider v-model="goodForm.functionValue"></el-slider>
              </div>
            </el-form-item>
            <el-form-item label="包装磨损评级">
              <div class="bar">
                <el-slider v-model="goodForm.packageValue"></el-slider>
              </div>
            </el-form-item>
            <el-form-item label="商品主图">
              <!--  :http-request 保留原有参数情况下 新增参数type:main -->
              <el-upload
              ref="uploadMain"
              action="#"
              list-type="picture-card"
              :auto-upload="false"
              class="mainUpload"
              :http-request="(params)=>uploadFile(params,'main')"  
              :file-list="mainPhotoList"
              :class="{hideUpload:this.mainPhotoNum >= 1}"
              :on-remove="(file, fileList)=>handleRemove(file, fileList,1)"  
              :on-change="(file, fileList)=>handleProgress(file, fileList,1)"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
              >
              <i slot="default" class="el-icon-plus"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="商品副图(0-3张)">
              <el-upload
              ref="uploadAssistant"
              action="#"
              list-type="picture-card"
              :auto-upload="false"
              class="assistantUpload"
              :http-request="(params)=>uploadFile(params,'assistant')"
              :file-list="assistantPhotoList"
              :class="{hideUpload:this.assistantPhotoNum >= 3}"
              :on-remove="(file, fileList)=>handleRemove(file, fileList,2)"
              :on-change="(file, fileList)=>handleProgress(file, fileList,2)"
              >
                <i slot="default" class="el-icon-plus"></i>
              </el-upload>
            </el-form-item>
           
            <el-form-item label="商品描述">
              <el-input type="textarea" v-model="goodForm.desc"></el-input>
            </el-form-item>
            <!-- <el-form-item>
              <el-button type="primary" @click="onSubmit">立即创建</el-button>
              <el-button  @click="Test">取消</el-button>
            </el-form-item> -->
          </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="AddDialogFormVisible = false">取 消</el-button>
          <el-button v-if="(DialogFormIsAdd == true)" type="primary" @click="addGood()">确 定</el-button>
          <el-button v-else type="primary" @click="updateGood()">更改</el-button>
        </span>
      </template>
    </el-dialog>
    
    
    </div>
</template>
<script>
import { getUser } from '../assets/js/comn.js'
  export default {
    data() {
      return {
        assistantPhotoNum:0,  //当前展示在编辑商品图片上传墙上的辅图数量,用于控制上传图片框是否显示
        mainPhotoNum:0,//当前展示在编辑商品图片上传墙上的主图数量,用于控制上传图片框是否显示
        search:'',//搜索框
        DialogFormIsAdd:true,    //当为true的时候,代表弹出框应是添加框,为false时代表弹出框是更新框
        AddDialogFormVisible: false,//添加品类框是否显示
        goodList:[],
        tagList:[],
        categoryList:[],
        mainPhotoList:[], //主图列表(用于商品编辑中 获取已经上传的主图列表)
        assistantPhotoList:[],//辅图列表(用于商品编辑中 获取已经上传的辅图列表)
        dealTypeCheckGroup:[],//商品交易形式复选框数组
        fileList:[],//商品图文件数组
        deletePictureList:[],//需要删除的图片列表,内存储id
        goodForm:{
          
        },
        formLabelWidth: '120px',
      }
    },
    watch: {
      '$route': {
        handler: 'getGoodList',
        immediate: true
      }
    },
    created() {
      this.getGoodList();
      this.getCategoryList();
      this.getTagList();
    },
    methods: {
      async getCategoryList(){
        const{data:result} = await this.$axios.get("good/allCategory"); 
        this.categoryList = result.data;
      },
      async getTagList(){
          const{data:result} = await this.$axios.get("good/allTag"); 
          this.tagList = result.data;
      },
      async uploadFile(param,type){//这个方法在点击上传后才会执行(一次或以上)
        this.fileList.append('file', param.file);
        if(type == 'main'){
          this.fileList.append('type', 1);
        }
        else{
          this.fileList.append('type', 2);
        }
        console.log(param.file + "up");
    },
      getGoodMainPicture(PictureList){
        for(let i=0;i<PictureList.length;i++)
        {
          if(PictureList[i].type == 1){
            return this.$imgPath + PictureList[i].src;
          }
        }

      },
      chooseItem(){
        // console.log("?" + this.assistantPhotoNum);
        // console.log("!" + this.mainPhotoNum);
        let onlineIndex = this.dealTypeCheckGroup.findIndex((value)=>{ return value == '线上交易'})//dealTypeCheckGroup中存在线上交易的位置
        let offlineIndex = this.dealTypeCheckGroup.findIndex((value)=>{ return value == '线下面交'})//dealTypeCheckGroup中存在线上交易的位置
        if(onlineIndex == -1 && offlineIndex == -1){
          this.goodForm.dealType = 0;
        }else if(onlineIndex != -1 && offlineIndex !=-1){
          this.goodForm.dealType = 3;
        }else if(onlineIndex != -1){
          this.goodForm.dealType = 1;
        }else{
          this.goodForm.dealType = 2;
        }
      },    
        dateFormat(row, column) {
          let datetime = row[column.property];
          // console.log(datetime);
          if(datetime){
              datetime = new Date(datetime);
              let y = datetime.getFullYear() + '-';
              let m = datetime.getMonth()+1 + '-';
              let d = datetime.getDate();
              return y + m + d;
          }
          return ''
        },
        typeFormat(row, column) {
          let typeNum = row[column.property];
          if(typeNum == 1){
            return'线下面交';
          }
          if(typeNum == 2){
            return'线上交易';
          }
          if(typeNum == 3){
            return'均可';
          }
          if(typeNum == 0){
            return'均不可';
          }
          return ''
        },
        addFormClear(){
          this.mainPhotoList = [];
          this.assistantPhotoList = [];
          this.fileList = [];
          this.dealTypeCheckGroup = [];
        },
        // handleRemove(file, fileList) { //当文件墙的文件被移除时
        //   console.log(file, fileList);
        //   if(file.type == 1){
        //     this.mainPhotoNum--;
        //   }else{//==2
        //     this.assistantPhotoNum--;
        //   }
        //   if(file.status == 'success'){//如果是已经上传的图片被删除,需要加入删除列表中,便于稍后上传时进行删除 
        //     this.deletePictureList.push({id:file.id});
        //     console.log(this.deletePictureList);
        //   } 
        // },
        handleRemove(file, fileList,type) { //当文件墙的文件被移除时
          console.log(file, fileList);
          if(type == 1){
            this.mainPhotoNum--;
          }else{//==2
            this.assistantPhotoNum--;
          }
          if(file.status == 'success'){//如果是已经上传的图片被删除,需要加入删除列表中,便于稍后上传时进行删除 
            this.deletePictureList.push({id:file.id});
            console.log(this.deletePictureList);
          } 
        },
        handleProgress(file, fileList,type) { //当文件墙的文件被添加时
          if(type == 1){
            console.log(fileList.length);
            this.mainPhotoNum = fileList.length;
          }else{//==2
            this.assistantPhotoNum = fileList.length;
          }
        },
        //res是那行绑定的goodList[那行]
        editGood(row){
            this.mainPhotoNum = 0;
            this.assistantPhotoNum = 0;
            this.mainPhotoList = [];
            this.assistantPhotoList = [];
            this.goodForm = row;
            for(let i=0;i<this.goodForm.pictureList.length;i++){
              if(this.goodForm.pictureList[i].type == 1){
                this.mainPhotoList.push({
                    id:this.goodForm.pictureList[i].id,
                    url:this.$imgPath + this.goodForm.pictureList[i].src,
                    type: this.goodForm.pictureList[i].type
                  })
                this.mainPhotoNum++;
              }
              else{ //如果等于2
                this.assistantPhotoList.push({
                    id:this.goodForm.pictureList[i].id,
                    url:this.$imgPath + this.goodForm.pictureList[i].src,
                    type: this.goodForm.pictureList[i].type
                  })
                this.assistantPhotoNum++;
                
              }
            }
            if(this.goodForm.dealType == 0){
              //无事
            }else if(this.goodForm.dealType == 3){
              this.dealTypeCheckGroup.push('线上交易')
              this.dealTypeCheckGroup.push('线下面交')
            }else if(this.goodForm.dealType == 1){
              this.dealTypeCheckGroup.push('线上交易')
            }else{
              this.dealTypeCheckGroup.push('线下面交')
            }
            this.OpenDialogForm('update');
        },
        OpenDialogForm(DialogType){
            if(DialogType=="add")
            {
            this.DialogFormIsAdd = true;
            }
            else if(DialogType=="update")
            {

              this.DialogFormIsAdd = false;
            }
            this.AddDialogFormVisible = true;
        },
        async getGoodList(){
          if(this.$route.meta.isAdmin){//如果当前是管理员选项下的商品管理
            const{data:result} = await this.$axios.get("good/allGood"); 
            this.goodList = result.data;
          }
          else{//如果是我的商品(通过用户id来调取商品列表)
            const{data:result} = await this.$axios.get("good/allGoodByUserId?userId=" + getUser().id); 
            this.goodList = result.data;
          }
        },
        updateGood(){
            console.log(this.goodForm);
            if(this.goodForm.sprice > this.goodForm.price){
              this.$message.error("折扣不能大于商品原价");
              return;
            }
            this.fileList = new FormData()
            // good.saleDate = time.toLocaleString( );
            this.$refs.uploadMain.submit();
            this.$refs.uploadAssistant.submit();
            this.fileList.append('goodId', this.goodForm.id);//在上传图集里绑一个商品id,为了能够在后面构造图片和商品的关系表
            this.$refs.goodForm.validate(async valid=>{
              for(let i=0;i<this.deletePictureList.length;i++){
                const {data:deletePictureRes} = await this.$axios.post("good/deletePicture?id="+this.deletePictureList[i].id);//删除图片
              }
              if(this.fileList.get('file')){
               // console.log(this.fileList.get('file'));
                const {data:uploadRes} = await this.$axios.post("good/upload",this.fileList);//上传图片,fileList中含有(file[]图片文件,type[]图片种类,goodId商品Id)
              }
              const {data:res} = await this.$axios.post("good/updateGood",this.goodForm); 
                console.log(res);
                if(res == "suc")
                {
                    this.AddDialogFormVisible=false;
                    this.$message.success("更改成功");
                    this.getGoodList();
                }
                else
                {
                  this.$message.error("更改失败");
                }
            })
        },
        //res是那行绑定的goodList[那行]
        async deleteGood(res){
                console.log(res);
                const {data:result} = await this.$axios.post("good/deleteGood",res); 
                console.log(result);
                if(result == "suc")
                {
                  this.$message.success("删除成功");
                  this.getGoodList();
                }
                else
                {
                  this.$message.error("删除失败");
                }
            }
        },
      
    async onSubmit(){
      console.log(this.goodForm);
      this.fileList = new FormData()
      // good.saleDate = time.toLocaleString( );
      this.$refs.uploadMain.submit();
      this.$refs.uploadAssistant.submit();
      const {data:res} = await this.$axios.post("good/updateGood",this.goodForm); //添加商品,回调商品ID
      this.fileList.append('goodId', this.goodForm.id);//在上传图集里绑一个商品id,为了能够在后面构造图片和商品的关系表
      const {data:uploadRes} = await this.$axios.post("good/upload",this.fileList);//上传图片,fileList中含有(file[]图片文件,type[]图片种类,goodId商品Id)

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
