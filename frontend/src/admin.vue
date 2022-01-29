<template>
    <el-container class="container">
        <el-header height="60px"><admin-top></admin-top></el-header>
        <el-container>
          <el-aside width="200px"><admin-nav></admin-nav></el-aside>
          <el-main><router-view> </router-view></el-main>
        </el-container>
      </el-container>
    
</template>

<script>
import AdminNav from './components/AdminNav.vue'
import AdminTop from './components/AdminTop.vue'
export default {
     components: { AdminTop,AdminNav },
     
}
</script>

<style lang="less" scoped>
  .container{
    height:100%;
  }
    .el-header, .el-footer {
    /* background-color: #B3C0D1; */
    color: #333;
    padding:0px;
  }

  .el-aside {
    background-color:#273238;

    /* height:900px; */
    min-height: 820px;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: rgb(252,252,254);
    color: #333;
    /* text-align: center; */
    /* line-height: 160px; */
    padding:0px;
  }
</style>
    