<template>
    <div>
        <TopMessage></TopMessage>
        <!-- 导航栏 -->
        <header-nav></header-nav>
        <!-- 路由出口 -->
        <router-view> </router-view>
        <!-- 尾巴 -->
        <Footer></Footer>
    </div>
</template>

<script>
import TopMessage from './components/TopMessage.vue'
import HeaderNav from './components/HeaderNav.vue'
import Footer from './components/Footer.vue'
export default {
  components: { HeaderNav,TopMessage,Footer},
    
}
</script>

<style lang="less" scoped>
div{
  /* background-color: silver; */
  background-image: url(./assets/picture/祥云底纹素材.png);
}
</style>
    