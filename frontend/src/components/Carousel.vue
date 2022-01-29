<template>
  <div id="carousel">
      <div class="block">
        <!-- <span class="demonstration">默认 Hover 指示器触发</span> -->
        <el-carousel height="750px">
          <el-carousel-item v-for="item in dataPicture" :index="item">
            <img v-bind:src="item.src">
          </el-carousel-item>
        </el-carousel>
      </div>
  </div>
</template>

<script>
export default {
    data() {
      return {
         //幻灯片图
         dataPicture: [
          {
						src: require('../assets/picture/走马灯1.jpg'),
						
					},
          {
            src: require('../assets/picture/走马灯2.jpg'),
          },

          
          ]
      }
    },
}
</script>


<style lang="less" scoped>
  #carousel img{
    /* height: 900px; */
    background-color: black;
    width:100%;
  }
  .block{
    /* width:100%; */
    background-color: blue;
  }
     .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
  }
</style>