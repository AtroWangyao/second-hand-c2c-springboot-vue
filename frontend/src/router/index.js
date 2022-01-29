import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/register.vue'
import Home from '../views/Home.vue'
import Test from '../components/Test.vue'
import Index from '../Index.vue'
import Admin from "../admin.vue"
import adminUserList from "../views/adminUserList.vue"
import adminTagList from "../views/adminTagList.vue"
import adminCityList from "../views/adminCityList.vue"
import adminCategoryList from "../views/adminCategoryList.vue"
// import adminAddUser from"../views/adminAddUser.vue"
import test2 from"../views/test2.vue"
import GoodDetail from '../views/goodDetail.vue'
import ConfirmOrder from '../views/confirmOrder.vue'
import GoodList from '../views/goodList.vue'
import addGood from '../components/AddGood.vue'
import adminGoodList from '../views/adminGoodList.vue'
import personalInformation from '../views/personalInformation.vue'
import certification from '../views/certification.vue'
import personalAddress from '../views/personalAddress.vue'
import orderList from '../views/orderList.vue'
import orderProcessing from '../views/orderProcessing.vue'
import collectionList from '../views/collectionList.vue'
import adminOrderList from '../views/adminOrderList.vue'
import adminCommentManage from '../views/adminCommentManage.vue'
import adminCommentList from '../views/adminCommentList.vue'
import adminRecommendManage from '../views/adminRecommendManage.vue'
import adminCertificationManage from '../views/adminCertificationManage.vue'
import about from '../views/about.vue'

Vue.use(VueRouter)

const routes = [
  {//这个括号内,是需要导航栏的页面 组件index内包括一个导航栏组件与一个路由出口
    path:"/",
    redirect:"/home",
    component:Index,
    children:[
      {
        path:"test",
        component:Test
      },
      {
        path:"home",
        component:Home,
        meta: { isHome: true }
      },
      {
        path:"goodDetail",
        component:GoodDetail,
        meta: { isHome: false }
      },
      {
        path:"confirmOrder",
        component:ConfirmOrder,
        meta: {
          isHome: false ,
          needLogin: true,    
        }
      },
      {
        path:"goodList",
        component:GoodList,
        meta: { isHome: false }
      },
      {
        path:"/login",
        component:Login,
        meta: { isHome: false }
      },
      {
        path:"/register",
        component:Register,
        meta: { isHome: false }
      },
      {
        path:"/about",
        component:about,
        meta: { isHome: false }
      },
    ]
  },
  //以下则是不需要导航的页面
  {
    path:"/test2",
    component:test2,
  },
  {
    path:"/admin",
    component:Admin,
    meta:{
      needLogin: true,    
    },
    children:[
      {
        path:"test",
        component:Test,
        
      },
      {
        path:"adminUserList",
        component:adminUserList,
      },
      {
        path:"adminCityList",
        component:adminCityList,
      },
      {
        path:"adminCategoryList",
        component:adminCategoryList,
      },
      {
        path:"adminTagList",
        component:adminTagList,
      },
      {
        path:"adminOrderList",
        component:adminOrderList,
      },
      // {
      //   path:"adminAddUser",
      //   component:adminAddUser,
      // },
      {
        path:"adminGoodList",
        component:adminGoodList,
        meta: { isAdmin: true },
      },
      {
        path:"myGoodList",
        component:adminGoodList,
        meta:{  isAdmin: false, 
          needCertificate:true
        }
      },
      {
        path:"addGood",
        component:addGood,
        meta: { needCertificate:true },
      },
      {
        path:"personalInformation",
        component:personalInformation,
      },
      {
        path:"certification",
        component:certification,
      },
      {
        path:"personalAddress",
        component:personalAddress,
      },
      {
        path:"orderList",
        component:orderList,
        meta:{
          needLogin: true,    
        },
      },
      {
        path:"orderProcessing",
        component:orderProcessing,
        meta: { needCertificate:true },
      },
      {
        path:"collectionList",
        component:collectionList,
        meta:{
          needLogin: true,    
        },
      },
      {
        path:"adminCommentManage",
        component:adminCommentManage,
      },
      {
        path:"adminCommentList",
        component:adminCommentList,
      },
      {
        path:"adminCertificationManage",
        component:adminCertificationManage,
      },
      {
        path:"adminRecommendManage",
        component:adminRecommendManage,
      },
      
    ]
  }
  
]

const router = new VueRouter({
  mode: 'history',
  routes
})
//跳转页面时,定位在顶部
router.afterEach((to,from,next)=>{ 
  　　　　window,scrollTo(0,0)
})
export default router
