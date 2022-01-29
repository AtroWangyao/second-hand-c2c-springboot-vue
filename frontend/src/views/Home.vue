<template>
    <div>
      
        
        <Carousel> </Carousel>
        <div class="main">
            <div class="top">
                <h2>今 日 推 荐</h2>
                <p>那些你可能会需要的商品</p>
            </div>
            <el-divider></el-divider>
            <Recommend></Recommend>
            <div class="top">
                <h2>本 地 精 选</h2>
                <p>面对面交易,安心之选</p>
            </div>
            <el-divider></el-divider>
            <GoodList></GoodList>
        </div> 
        <!-- <router-view> </router-view> -->
    </div>
</template>

<script>
import Carousel from '../components/Carousel.vue'
import Recommend from '../components/TopRecommend.vue'
import GoodList from '../components/ListRecommend.vue'
export default {
    components: { Carousel, Recommend,GoodList},
}
</script>

<style scoped>

    .top h2{
        text-align: center;
        
        margin-bottom: 20px;
    }
    .top p{
        
        font-style: oblique;
        text-align: center;
        margin-bottom: 40px;
    }
    .main{
        /* padding-top: 20px; */
        /* background-color: red; */
        margin:0 auto;
        width: 1200px;
        
    }
</style>
    