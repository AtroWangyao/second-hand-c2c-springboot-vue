import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import axios from 'axios'
import comnJs from './assets/js/comn.js';
Vue.config.productionTip = false
Vue.prototype.$axios = axios;
Vue.prototype.$imgPath = "http://localhost:9000/img/";
Vue.prototype.comnJs = comnJs;
axios.defaults.baseURL="http://localhost:9000"
new Vue({
  router,
  render: h => h(App),
  created(){
    router.beforeEach((to, from, next) => {
        let flag = false;
        if(to.meta.needCertificate){//如果访问页面需要实名认证
          if (JSON.parse(sessionStorage.getItem('user')).type != 1) { 
            next();  
          }
          else{
            this.$message.error("请先进行实名认证");  
            // next({
            //   path: '/admin/certification',  //path到登录页面
            // })
            flag = true;
          }
        }
        if (to.meta.needLogin) {     // 如果访问的页面必须要登录
            if (sessionStorage.user!=null || sessionStorage.user!=undefined) { 
              next();  
            }
            else{
              this.$message.error("请先登录");  
              // next({
              //   path: '/login',  //path到登录页面
              // })
              flag = true;
            }
        }
        if(!flag){ 
          next();
        }
      })
    }
    
}).$mount('#app')


