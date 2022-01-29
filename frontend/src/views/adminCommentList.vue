<template>
    <div class="main">
        <el-page-header @back="goBack" :content="this.$route.query.goodName">
        </el-page-header>
        <el-table
      :data="commentList.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100% ">
        <el-table-column
          prop="id"
          label="评论ID"
          width="100">
        </el-table-column>
        <el-table-column
          prop="user.userName"
          label="用户昵称"
          width="150">
        </el-table-column>
        <el-table-column
          prop="text"
          label="内容"
          width="300">
        </el-table-column>
        <el-table-column
          prop="score"
          label="评分"
          width="100">
        </el-table-column>
        <el-table-column
          prop="date"
          label="发表时间"
          width="300"
          :formatter="dateFormat">
        </el-table-column>
        <el-table-column
          prop="type"
          label="评论类型"
          width="300"
          :formatter="typeFormat" >
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
            <el-button @click="handleComment(scope.row)" type="text" size="medium">{{scope.row.type|handleFormat}}</el-button>
          </template>
        </el-table-column>
    </el-table>

    
    
    
    </div>
</template>
<script>
    export default {
      data() {
        return {
        
          search:'',//搜索框
          commentList:[],

        }
      },
      created() {
        this.getCommentList();
      },
    methods: {
        async handleComment(row){
            if(row.type == 1){//如果是1(用户违规)则是解除违规=>0(普通用户评论)
                row.type = 0;
            }else if(row.type == 3){//如果是3(商家违规)则是解除违规=>2(普通商家评论)
                row.type = 2;
            }else if(row.type == 2){//如果是2(普通商家评论)则是进行违规化=>3(商家违规)
                row.type = 3;
            }else if(row.type == 0){//如果是0(普通用户评论)则是进行违规化=>)1(用户违规)
                row.type = 1;
            }
            const{data:result} = await this.$axios.post("comment/update",row); 
            this.getCommentList();
        },
      getGoodMainPicture(PictureList){
        for(let i=0;i<PictureList.length;i++)
        {
          if(PictureList[i].type == 1){
            return this.$imgPath + PictureList[i].src;
          }
        }

      },
      typeFormat(row, column) {
            let type = row[column.property];
            switch(type){
                case 0: return'正常评论';
                case 1: return'违禁评论';
                case 2: return'商家评论';
                case 3: return'违禁评论';
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
        async getCommentList(){
            const{data:result} = await this.$axios.get("comment/getCommentListByGoodId?goodId="+this.$route.query.goodId); 
            this.commentList = result.commentList;
            console.log(this.commentList)
            // for(let i=0;i<this.commentList.length;i++){//绑定一下评论的用户名
            //     const{data:UserRes} = await this.$axios.get("user/getUserById?userId="+this.commentList[i].userId); 
            //     this.$set(this.commentList[i], 'userName', UserRes.data.userName)
            // }
        },
        goBack() {
            this.$router.go(-1); 
        },
  },
  filters: {
        handleFormat: function(val){
            switch(val){
                case 0: return'设为违规';
                case 1: return'解除违规';
                case 2: return'设为违规';
                case 3: return'解除违规';
            }
        },
    }       
}
</script>

<style lang="less" scoped>
 
  .main{
      width:100%;
      margin:20px auto;
  }

</style>
