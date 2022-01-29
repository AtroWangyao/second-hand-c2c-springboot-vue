<template>
<div>
    <div class="title">
        <h2>我的地址</h2> 
        <p>在这里您可以随时编辑您的收货地址列表</p>
    </div>
    <AddressCardList></AddressCardList>
   
</div>
</template>
<script>
import { getUser,setUser } from '../assets/js/comn.js'
import AddressCardList from '../components/AddressCardList.vue'
export default {
    components: {AddressCardList},
  
}
</script>
<style lang="less" scoped>
    
</style>