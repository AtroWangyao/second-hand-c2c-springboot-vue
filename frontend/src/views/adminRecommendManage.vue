<template>
    <div>
      <div class="title">
        <h2>推荐管理</h2> 
        <p>在这里您可以管理首页推荐窗口所展示的商品列表</p>
      </div>
      <div class="main">
          <div class="transfer">
              <el-transfer
                filterable
                style="text-align: left; display: inline-block"
                filter-placeholder="请输入商品名"
                v-model="TodayRecommendGoodIdList"
                :props="{
                    key: 'id',
                    label: 'name'
                }"
                @change="handleChange"
                :titles="['商品列表', '今日推荐']"
                :data="goodList">
            </el-transfer>
          </div>
          <div style="float: right;">
              <el-button type="primary" @click="updateCommend(1)">保存</el-button>
          </div>
      </div>
    </div>
</template>
<script>
import {getUser} from '../assets/js/comn.js'
export default {
    data() {
        return {
            goodList:[],
            TodayRecommendGoodIdList:[],
            TodayRecommendList:[],
        }
    },
    created() {
        this.getGoodList();
        this.getRecommendList(1);
    },
    methods: {    
        async getGoodList(){
          const{data:result} = await this.$axios.get("good/allGood"); 
          this.goodList = result.data;
        },
        async getRecommendList(type){
            const{data:result} = await this.$axios.get("recommend/allByType?type=" + type); 
            for(let i=0;i<result.data.length;i++){
                this.TodayRecommendGoodIdList.push(result.data[i].goodId);
            }
        },
         handleChange(){
            console.log(this.TodayRecommendGoodIdList);
           
        },
        async updateCommend(type){
            this.TodayRecommendList=[];
            if(this.TodayRecommendGoodIdList.length == 0){ //如果全部都清空了的话
                this.$message.error("请至少保留一个推荐商品");
            }
            else{
                for(let i=0;i<this.TodayRecommendGoodIdList.length;i++){
                    this.TodayRecommendList.push({
                        id:'',
                        goodId:this.TodayRecommendGoodIdList[i],
                        type:type,
                    })
                }
                console.log(this.TodayRecommendList);
                const{data:result} = await this.$axios.post("recommend/recreate",this.TodayRecommendList); 
                if(result == 'suc'){
                    this.$message.success("修改成功")
                }
                else{
                    this.$message.error("修改失败");
                }
                
            }
        }
    },  
    
}
    
</script>

<style lang="less" scoped>
  .title{
      text-align: center;
  }
  .transfer{
    text-align: center
  }
  .main{
      margin:50px auto;
      width:1000px;
  }
</style>
