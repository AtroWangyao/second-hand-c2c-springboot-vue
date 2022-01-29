<template>
    
    <div class="container">
       
        <div class="display"  v-for="(item, index) in goodList" :key="index">
            <div class="imgDiv" style="cursor: pointer;" @click="goGoodDetail(item.id)" >
                <div class="hidden">
                    <img :src="item.mainImg">
                </div>
            </div>
            <div class="detailDiv" >
                <p class="goodTitle" style="cursor: pointer;" @click="goGoodDetail(item.id)">{{item.name}}</p>
                
                
                <p class="fontType1">原价: <span class="fontType2">¥{{item.price}}</span></p>
                <p class="fontType1">折扣: <span class="fontType2 fontColorRed">¥{{item.sprice}}</span></p>
                <p class="fontType1">厂商: <span class="fontType2">{{item.manufacturer}}</span></p>
                <p class="fontType1">交易方式: <span class="fontType2">{{item.dealType|dealTypeFormat}}</span></p>
                <el-progress :percentage="item.appearanceValue"  color="red" :format="rateAppearance"></el-progress>
                <el-progress :percentage="item.functionValue"  color="blue" :format="rateFunction"></el-progress>
                <el-progress :percentage="item.packageValue"  color="green" :format="ratePackage"></el-progress>
                <collectBtn id="collectBtn" :goodId="item.id"></collectBtn>
                <!-- <i  class="el-icon-star-off"></i> -->
            </div>     
        </div>
        
    </div>
</template>
<script>
import collectBtn from '../components/collectBtn.vue'
import { rateFunction,ratePackage,rateAppearance } from '../assets/js/comn.js'
export default {
    components: {collectBtn},
    data() {
        return {
            goodList:[],
        }
    },

    created() {
        this.getGoodList(1);
    },
    methods: {
        async getGoodList(type){
            const{data:result} = await this.$axios.get("recommend/allByType?type=" + type); 
            for(let i=0;i<result.data.length;i++){
                const{data:goodRes} = await this.$axios.get("good/getGood?goodId="+result.data[i].goodId)
                for(let j=0;j<goodRes.good.pictureList.length;j++){//找主图,插主图
                    if(goodRes.good.pictureList[j].type == 1){
                        this.$set(goodRes.good,'mainImg',this.$imgPath + goodRes.good.pictureList[j].src);
                        break;
                    }
                }
                this.$set(goodRes.good,'categoryName',goodRes.categoryName);
                this.goodList.push(goodRes.good);
            }
            // console.log(this.goodList);
        },
        rateAppearance(percentage){
            return rateAppearance(percentage);
        },
        rateFunction(percentage){
            return rateFunction(percentage);
        },
        ratePackage(percentage){
            return ratePackage(percentage);
        },
        goGoodDetail(goodId) {
            this.$router.push({  
                path:'/goodDetail',
                query:{goodId:goodId},
            })

        },
    },
    filters: {
        dealTypeFormat: function(val){
            switch(val){
                case 0: return'均不可';
                case 1: return'线下面交';
                case 2: return'线上交易';
                case 3: return'均可';
            }
        },
        dateFormat: function(val){
            // console.log(val);
            val = new Date(val);
            let y = val.getFullYear() + '-';
            let m = val.getMonth()+1 + '-';
            let d = val.getDate();
            return y + m + d;
        },

    }
}
</script>
<style lang="less" scoped>
    @import "~@/assets/css/global.css";
    
    .container{
        margin-top: 20px;
        
        /* height:500px; */
        /* background-color: blue; */
        padding-bottom: 20px;
    }
    
    .display{
        border:solid 30px;
        border-image: url(../assets/picture/border.png) 30;
        width: 900px;
        margin:auto;
        height: 400px;
        background-color: white;
        margin-bottom: 100px;
        
    }
   
    .hidden{
        display: inline-block;
        height: 100%;
        width: 100%;
        /* overflow: hidden; */
    }
    .imgDiv{
        
        top:-40px;
        position: relative;
        height:500px;
        width:40%;
        /* background-color: black; */
        margin-left:5%;
        float:left;
        /* display: inline-block; */
    }
    .imgDiv .hidden img{
        
        height: 100%;
        width:100%;
        transition: all 0.8s;
        /* cursor: pointer;   */
    }
    img:hover{
        transform: scale(1.2); 
    }
    
    .detailDiv{
        padding-top: 10px;
        padding-left:80px;
        width:40%;
        float:left;
        /* display: inline-block; */
        height:100%;
        position: relative;
    }
    .goodTitle{
        text-align: center;
        margin-bottom: 20px;
    }
    #collectBtn{
      font-size: 30px;
      position: absolute;
      right:0px;
      top:30px;
    }
    
</style>