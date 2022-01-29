<template>
    <footer class="footer">
      <div class="main">
        <div class="">
            <h4 class="title">赞助伙伴</h4>
            <ul>
              <li>
                My Parent
              </li>
              <li>
                Friend
              </li>
              <li>
                Classmate
              </li>
              <li>
                Internet
              </li>
              <li>
                teacher
              </li>
              <li>
                school
              </li>
            </ul>
          </div>
          <div class="" >
            <h4 class="title">信息与许可证</h4>
            <ul>
              <li>
                互联网ICP备案：
              </li>
              <li>
                营业执照
              </li>
              <li>
                出版物经营许可证
              </li>
              <li>
                市场名称登记证
              </li>
              <li>
                出版物网络交易平台服务经营备案号
              </li>
              <li>
                互联网药品信息服务资格证书
              </li>
            </ul>
          </div>
          <div class="" >
            <h4 class="title">传送门</h4>
            <ul>
              <li>
                <router-link to="/about"> About</router-link>
              </li>
              <li>
                <router-link to="/home"> Home</router-link>
              </li>
              <li>
                <router-link to="/admin"> admin</router-link>
              </li>
              <li>
                <router-link to="/admin/orderList"> Orders</router-link>
              </li>
              <li>
                <router-link to="/goodList"> Goods</router-link>
              </li>
              <li>
                <router-link to="/test"> Test</router-link>
              </li>
            </ul>
          </div>
          <div class="" >
            <h4 class="title">友情链接</h4>
            <ul>
              <li>
                <router-link to="/home"> ego</router-link>
              </li>
              <li>
                <router-link to="/home"> myself</router-link>
              </li>
              <li>
                <router-link to="/home"> I</router-link>
              </li>
              <li>
                <router-link to="/home"> Me</router-link>
              </li>
              <li>
                <router-link to="/home"> there</router-link>
              </li>
              <li>
                <router-link to="/home"> emmm</router-link>
              </li>
            </ul>
          </div>
          
          
          
        </div>
    </footer>
</template>
<script>
export default {
    data() {
      return {
        items:[1,2]
      }
    },
}
</script>
<style lang="less" scoped>
    .footer{
      box-sizing: border-box;
        height:400px;
        background-color: rgb(49,53,57);
        padding-top:100px;
    }
    .main{
      
      width:1500px;
      margin:0 auto;
    }
    .main div{
      display: inline-block;
      margin: 0 100px;
    }
    .title{
      color: #fff;
      padding: 0;
      margin: 0;
      -webkit-text-size-adjust: 100%;
      box-sizing: border-box;
      line-height: 2.4;
      margin: 0;
      font-size: 16px;
      margin-bottom: 20px;
      padding-left: 35px;
      border-left: 2px solid #79b6c8;
      font-weight: bold;
    }
    ul li{
      line-height: 1.5;
      font-size: 16px;
      font-family: 'Lato', sans-serif;
      color: rgb(146, 146, 146) ;
      box-sizing: border-box;
      padding: 0;
      margin: 0;
      list-style: none;
    }
    a{
      color:rgb(146, 146, 146) ;
      text-decoration: none;
    }
</style>