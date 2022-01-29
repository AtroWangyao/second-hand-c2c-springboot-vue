<template>
    <header>
      <!-- 如果navBarFixed是true的话,会新增class fix-nav -->
        <!-- <div class="logo">
          <img src="../assets/picture/logo2.png" />
        </div> -->
        <div id="nav" class="nav"  :class="{'fix-nav': navBarFixed, 'navBackgroundIsBlack': navBackgroundIsBlack} "> 
          <div class="logo">
            <img src="../assets/picture/logo2.png" />
          </div>
            <nav class="menu">
                <router-link to="/home"> 首页</router-link>
                <router-link to="/goodList"> 商品清单</router-link>
                <router-link to="/admin/orderList"> 订单</router-link>
                <router-link to="/admin/collectionList"> 收藏</router-link>
                <router-link to="/admin"> 后台入口</router-link>
            </nav>
        </div>
    </header>
</template>
<script>
export default {
        data() {
          return {
            //用于控制导航栏吸附
            navBarFixed: false,
            //只有Home页的导航栏会有顶部透明效果,用这个属性来判断是否是Home页,如果不是的话,会固定绑定fix-nav
            isHome:true,
            isNotDispay:true,
            navBackgroundIsBlack:false,
          };
        },
        watch: {
            '$route' (to, from) {
            console.log(this.$route.meta.isHome)
            if(this.$route.meta.isHome == true)
            {
              this.navBackgroundIsBlack = false;
            }
            else
            {
              this.navBackgroundIsBlack = true;
              
            }
          }
        },
        mounted() {
          // 事件监听滚动条
          if(this.$route.meta.isHome == true)
          {
            this.navBarFixed=false;
            this.navBackgroundIsBlack = false;
            window.addEventListener("scroll", this.watchScroll);
          }
          else
          {
            console.log("sadsad");
            this.navBarFixed=false;
            this.navBackgroundIsBlack = true;
            window.addEventListener("scroll", this.watchScroll);
          }
        },

        destroyed() {
          // 移除事件监听
          // if(this.$route.meta.isHome == true)
          window.removeEventListener("scroll", this.watchScroll);
          
        },

        methods: {
          watchScroll() {
            // 滚动的距离 兼容写法
            var scrollTop =
            window.pageYOffset ||
            document.documentElement.scrollTop ||
            document.body.scrollTop;
            if(this.$route.meta.isHome == true)
            {
              this.navBackgroundIsBlack = false;
                // 容器的高度
              //var offsetTop = document.querySelector("#nav").offsetHeight;//offsetHeight:可见区域高(class nav类里的top)
  
              // console.log("scrollTop=>", scrollTop, "  offsetTop=>", offsetTop);
  
              //  滚动的距离如果大于了元素到顶部的距离时，实现吸顶效果
              if (scrollTop > 800) {
                this.navBarFixed = true;
                // console.log("yes")
              } else {
                this.navBarFixed = false;
              }

            }
            else{
              
              
              if (scrollTop > 60) {
                this.navBarFixed = true;
                // console.log("yes")
              } else {
                this.navBarFixed = false;
              }
            }
          },
        },

}
</script>

<style lang="less" >

    .logo{
      position: absolute;
      z-index: 999;
    }
     /* 导航 */
    .nav {
      width: 100%;
      height: 70px;
      background-color:rgba(0, 0, 0, 0.0);
      /* background-color:rgb(49,53,57); */
      position: absolute;
      top: 40px;
      z-index: 999;
      
    }
    .notDisplay{
      display: none;
    }
    .navBackgroundIsBlack{
      background-color:rgb(49,53,57);
    }
    /* 固定导航 */
    .fix-nav {
      /* background-color:#1b1f2385; */
      background-color:rgb(49,53,57);
      position: fixed;
      top: 0;
      z-index: 999;
      
    }
    .menu{
      height: 100%;
      line-height: 70px;
    }
    .color{
        
        height: 80px;
        width: 100%;
        z-index: 999;
        /* padding: 20px;
        /* width: 100%; */
        /* background:rgb(221, 221, 221);
        position: sticky;
        position: -webkit-sticky;
        padding: 5px;
        top: 0;
        height: 100px;
        width: 100%; */ 
        /* z-index: 999; */
        
    }

    
    nav{
        text-align: right;
    }
    nav a{
        position: relative;
        margin: 4px 20px;
        outline: none;
        color: rgb(255, 255, 255);
        opacity: 0.9;
        text-align: center;
        text-transform: uppercase;
        text-decoration: none;
        font-size: 20px;
        font-weight: 500;
    }
    nav a:hover{
        color: rgb(255, 255, 255);
        font-weight: 1500;
    }
    nav a:hover, nav a:focus{outline: none;}
    .menu a:after{
        content: "";
        position: absolute;
        top: 100%;
        left: 0;
        width: 100%;
        height: 4px;
        background: rgb(255, 255, 255);
        transform: rotateX(-90deg) translateY(5px);
        transition: transform 0.3s, opacity 0.3s;
        opacity: 0.5;
    }
    .menu a:hover::after{
        opacity: 1;
        transform: translateY(10px) translateZ(0);
    }



</style>
