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
            <el-button @click="goCommentList(scope.row)" type="text" size="medium">查看评论</el-button>
          </template>
        </el-table-column>
    </el-table>

    
    
    
    </div>
</template>
<script>
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
        console.log("?" + this.assistantPhotoNum);
        console.log("!" + this.mainPhotoNum);
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
        //   console.log(datetime);
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
       
        async getGoodList(){
          const{data:result} = await this.$axios.get("good/allGood"); 
          this.goodList = result.data;
        },
        
        goCommentList(row) {
            this.$router.push({  
                path:'/admin/adminCommentList',
                query:{goodId:row.id,goodName:row.name},
            })

        },

  }
}
</script>

<style lang="less" scoped>
 
  .title{
      text-align: center;
  }

</style>
