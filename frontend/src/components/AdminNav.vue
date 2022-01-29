<template>
    <div> 
        
        <el-menu
        :uniqueOpened="true"
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#273238"
        text-color="white"   
        active-text-color="steelblue">
        
            <el-submenu index="1">
                <template #title>
                    <i class="el-icon-user-solid"></i>
                    <span>个人中心</span>
                </template>
                <el-menu-item-group>
                    <router-link to="/admin/personalInformation"><el-menu-item index="1-1"> 我的资料</el-menu-item></router-link>
                    <router-link to="/admin/certification"><el-menu-item index="1-2"> 实名验证</el-menu-item></router-link>
                    <router-link to="/admin/personalAddress"><el-menu-item index="1-3"> 我的地址</el-menu-item></router-link>
                </el-menu-item-group>

            </el-submenu>
            <el-submenu index="2">
                <template #title>
                    <i class="el-icon-s-shop"></i>
                    <span>店铺管理</span>
                </template>
                <el-menu-item-group>
                    <router-link to="/admin/addGood"><el-menu-item index="2-1"> 新货上架</el-menu-item></router-link>
                    <router-link to="/admin/myGoodList"><el-menu-item index="2-2"> 我的商品</el-menu-item></router-link>
                    <router-link to="/admin/orderProcessing"><el-menu-item index="2-3"> 订单处理</el-menu-item></router-link>
                </el-menu-item-group>

            </el-submenu>
            <el-submenu index="3">
                <template #title>
                    <i class="el-icon-star-on"></i>
                    <span>我的收藏</span>
                </template>
                <el-menu-item-group>
                    <router-link to="/admin/collectionList"><el-menu-item index="3-1"> 商品收藏</el-menu-item></router-link>
                </el-menu-item-group>

            </el-submenu>
            <el-submenu index="4">
                <template #title>
                    <i class="el-icon-s-goods"></i>
                    <span>我买到的</span>
                </template>
                <el-menu-item-group>
                    <router-link to="/admin/orderList"><el-menu-item index="4-1"> 我的订单</el-menu-item></router-link>
                </el-menu-item-group>
            </el-submenu>
            <el-submenu index="5" v-if="user.type == 3">
                <template #title>
                    <i class="el-icon-s-tools"></i>
                    <span>管理员选项</span>
                </template>
                <el-menu-item-group>
                    <router-link to="/admin/adminUserList"><el-menu-item index="5-1"> 用户列表</el-menu-item></router-link>
                    <router-link to="/admin/adminOrderList"><el-menu-item index="5-2"> 订单列表</el-menu-item></router-link>
                    <router-link to="/admin/addGood"><el-menu-item index="5-3"> 添加商品</el-menu-item></router-link>
                    <router-link to="/admin/adminTagList"><el-menu-item index="5-4"> 标签管理</el-menu-item></router-link>
                    <router-link to="/admin/adminCategoryList"><el-menu-item index="5-5"> 品类管理</el-menu-item></router-link>
                    <router-link to="/admin/adminCityList"><el-menu-item index="5-6"> 城市管理</el-menu-item></router-link>
                    <router-link to="/admin/adminGoodList"><el-menu-item index="5-7"> 商品列表</el-menu-item></router-link>
                    <router-link to="/admin/adminCommentManage"><el-menu-item index="5-8"> 评论管理</el-menu-item></router-link>
                    <router-link to="/admin/adminRecommendManage"><el-menu-item index="5-9"> 推荐管理</el-menu-item></router-link>
                </el-menu-item-group>
            </el-submenu>
                      <router-link to="/home"><el-menu-item index="6"> 退出</el-menu-item></router-link>
            

        </el-menu>
        
    </div>
    
</template>

<script>
import { getUser } from '../assets/js/comn.js'
export default {
    data() {
        return {
            user:getUser(),
            isCollapse: true
        }
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        }
    },
}
</script>

<style lang="less" scoped>
     a {
    text-decoration: none;
  }
 
  .router-link-active {
    text-decoration: none;
  }
  ::v-deep .el-menu-vertical-demo{
      border-right: none;
  }
</style>
    