<template>
    <div>
        <div class="title">
          <h2>添加商品</h2> 
          <p>在这里可以您可以添加新的商品,并决定它们的属性</p>
        </div>
        <div class="form">
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
            <el-form-item label="商品特价">
              <el-input v-model="goodForm.sprice" :disabled="true"></el-input>
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
            
            <el-form-item label="购买日期">
              <el-col :span="11">
                <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="goodForm.purchaseDate"  style="width: 100%;"></el-date-picker>
              </el-col>
            </el-form-item>
            
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
              :http-request="(params)=>uploadFile(params,'assistant')"
              :limit=3
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
            <el-form-item>
              <el-button type="primary" @click="onSubmit">立即创建</el-button>
              <el-button  @click="Test">取消</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
    </template>
<script>
import { getUser } from '../assets/js/comn.js'
export default {
  data() {
    return {
      goodForm: {
        id: '',
        categoryId: '',
        name: '',
        price: '',
        sprice: '',
        desc: '',
        manufacturer: '',
        purchaseDate: '',
        saleDate: '',
        appearanceValue: 0,
        functionValue: 0,
        packageValue: 0,
        dealType:0,
        tagList:[],
        pictureList:[],
        userId:getUser().id,
      },
      fileNum:0,
      fileList:[],//商品图文件数组
      categoryList:[],//种类列表
      tagList:[],//标签列表,区别与goodForm中的tagList,这个是创建时获取的所有列表,用于选择
      dealTypeCheckGroup:[],//商品交易形式复选框数组
      assistantPhotoNum:0,  //当前展示在编辑商品图片上传墙上的辅图数量,用于控制上传图片框是否显示
      mainPhotoNum:0,//当前展示在编辑商品图片上传墙上的主图数量,用于控制上传图片框是否显示
    }
  },
  created() {
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
    handleRemove(file, fileList,type) { //当文件墙的文件被移除时
      console.log(file, fileList);
      if(type == 1){
        this.mainPhotoNum--;
        console.log(this.mainPhotoNum);
      }else{//==2
        this.assistantPhotoNum--;
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
    chooseItem(){
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
      console.log(this.goodForm.dealType);
    },
    async uploadFile(param,type){
      this.fileList.append('file', param.file);
      if(type == 'main'){
        this.fileList.append('type', 1);
      }
      else{
        this.fileList.append('type', 2);
      }

      this.fileNum++;
      
    },
    async onSubmit(){
      this.fileList = new FormData()
      this.$refs.uploadMain.submit();
      this.$refs.uploadAssistant.submit();
      if(this.fileNum == 0){
        this.$message.error("请上传图片");
        return;
      }
      console.log(this.goodForm);
      this.goodForm.saleDate = new Date() //出售时间设为当前时间
      const {data:res} = await this.$axios.post("good/addGood",this.goodForm); //添加商品,回调商品ID
      let goodId = res.goodId;
      // let goodId = 2;

      // console.log("goodId = " + goodId);
      this.fileList.append('goodId', goodId);//在上传图集里绑一个商品id,为了能够在后面构造图片和商品的关系表
      const {data:uploadRes} = await this.$axios.post("good/upload",this.fileList);//上传图片,fileList中含有(file[]图片文件,type[]图片种类,goodId商品Id)
      if(uploadRes == 'suc'){
        this.$message.success("上传成功");
        this.goMyGoodList();
      }else{
        this.$message.error("上传失败");
      }
      // location.reload();//刷新
    },
    goMyGoodList(){
      this.$router.push({path:"/admin/myGoodList"});
    },
    Test(){
      console.log(this.dealTypeCheckGroup);
    }
  },      
}
</script>
<style lang="less">
  /* 主图的图片框 */
  
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
  
</style>
<style lang="less" scoped>
  .hideUpload /deep/ .el-upload{
    display:none;   /* 上传按钮隐藏 */
  }
  /* 取消上传动画 */
  ::v-deep .el-upload-list__item {  
    transition: none !important;
  }
</style>