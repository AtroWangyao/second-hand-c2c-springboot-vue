<template>
        <i v-if="isCollected"  class="el-icon-star-on"  style="color:rgb(255, 153, 0);cursor: pointer;"@click="handleCollection" id="collectBtn"  ></i>
        <i v-else   class="el-icon-star-on"  style="cursor:pointer"   @click="handleCollection" id="collectBtn"   ></i>
</template>
<script>
    
import { getUser,rateFunction,ratePackage,rateAppearance,getSrc} from '../assets/js/comn.js'
export default {
    props:['goodId'],
    data() {
        return {
            collection:{
                id:'',
                userId:getUser()?getUser().id:'',
                goodId:this.goodId,
            },
            isCollected:false,
        }
    },
    created() {
        this.getCollection();
    },
    methods: {
        async getCollection(){
            if(getUser()){
                //console.log(this.goodId);
                const{data:data} = await this.$axios.get("collection/get?userId="+getUser().id + "&goodId="+this.goodId);
                if(data.collection){//如果存在代表已收藏
                    this.isCollected = true;
                    this.collection = data.collection;
                }
                else{//如果不存在代表没有收藏
                    this.isCollected = false;
                    this.collection.id = '';
                }
            }
        },
        async handleCollection(){
            if(!getUser()){
                this.$message.error('请先登录');
            }
            else if(this.isCollected == true){//如果是已收藏,那就是解除收藏
                // console.log(this.good.collection);
                const {data:res} = await this.$axios.post("collection/delete",this.collection); 
                if(res == "suc")
                {
                    this.$message.success("该商品已移出收藏列表");
                    this.getCollection();
                }
                else
                {
                    this.$message.error("移出收藏失败,请刷新页面");
                }
            }else{//如果是未收藏,那就是添加
                const {data:BackCollection} = await this.$axios.post("collection/add",this.collection); //这里成功会回调collection
                if(BackCollection != "def")
                {
                    this.$message.success("已收藏该商品");
                    this.getCollection();
                }
                else
                {
                    this.$message.error("添加收藏失败,请刷新页面");
                }
            }
            

        }
    },
}
</script>
<style lang="less" scoped>
    
</style>