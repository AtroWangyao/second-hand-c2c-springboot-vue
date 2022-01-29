<template>
    <div>
      <div class="title">
        <h2>收藏列表</h2> 
        <p>这里是您曾收藏过的商品,您可以在这里直接前往商品页面</p>
      </div>
        <el-table
      :data="collectionList.filter(data => !search || data.good.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 50%; margin:0 auto">
        <el-table-column
          prop="id"
          label="收藏ID"
          width="70">
        </el-table-column>
        <el-table-column
          prop="good.name"
          label="收藏商品"
          width="170">
        </el-table-column>
        <el-table-column
          label="商品图片"
          width="180">
          <template slot-scope="scope"  >
            <img  v-if="flag==true" :src="scope.row.good.mainPicture" alt="" style="width: 50px;height: 50px;text-align: center;">
          </template>
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
                <el-button @click="goGoodDetail(scope.row)" type="text" size="medium" >查看</el-button>
                <el-button @click="deleteCollection(scope.row)" type="text" size="medium" >删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    </div>
</template>
<script>
import {getUser} from '../assets/js/comn.js'
export default {
    data() {
        return {
            search:'',//搜索框
            collectionList:[],
            flag : false,
        }
    },
    created() {
        this.getCollectionList();
    },
    methods: {    
        async getCollectionList(){
            const{data:result} = await this.$axios.get("collection/getCollectionListByUserId?userId="+getUser().id); 
            this.collectionList = result.collectionList;
            for(let i=0;i<this.collectionList.length;i++){//逐个绑定商品给收藏列表
                const{data:GoodRes} = await this.$axios.get("good/getGood?goodId="+this.collectionList[i].goodId); 
                this.$set(this.collectionList[i], 'good', GoodRes.good)
                for(let j=0;j<this.collectionList[i].good.pictureList.length;j++){//逐个绑定主图给商品
                    if(this.collectionList[i].good.pictureList[j].type == 1){
                        this.$set(this.collectionList[i].good, 'mainPicture', this.$imgPath + this.collectionList[i].good.pictureList[j].src);
                        break;
                    }
                }
            }
            this.flag = true; //防报错用,如果在没有绑定完mainPicture表格就显示mainPicture 会报错...虽然不影响使用
        },
        async deleteCollection(row){
            const {data:res} = await this.$axios.post("collection/delete",row); 
            if(res == "suc")
            {
                this.$message.success("该商品已移出收藏列表");
                this.getCollectionList();
            }
            else
            {
                this.$message.error("移出收藏失败");
            }
        },
        goGoodDetail(row) {
            this.$router.push({  
                path:'/goodDetail',
                query:{goodId:row.goodId},
            })

        },
    },       
}
    
</script>

<style lang="less" scoped>
  .title{
      text-align: center;
  }

  
</style>
