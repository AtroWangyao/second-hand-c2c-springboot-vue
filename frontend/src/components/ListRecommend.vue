<template lang="">
    <div style="width:100%">
      <div class="topBar">
          <div class="searchDiv">
            <el-input
                placeholder="请输入内容"
                prefix-icon="el-icon-search"
                v-model="search">
            </el-input>
          </div>
          <div class="refreshDiv inlineBlock">
            <el-button  id="refreshButton" icon="el-icon-refresh" @click="refresh()"></el-button>
          </div>
          <div class="regionDiv">
            <template>
                <el-select v-model='nowCity' >
                  <el-option
                    v-for="item in cityList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                    <span style="float: left">{{ item.name }}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.desc }}</span>
                  </el-option>
                </el-select>
              </template>
          </div>
          
          <div class="moreDiv">
            <el-button type="info" @click="goGoodList()" style="height: 40px;"> 更多</el-button>
          </div>
        
      </div>
        <div class="list" id="list" >
          <div class="goodDiv"  v-for="(item, index) in filterlist(nowCity,search)" :key="index">
            <div class="goodImgDiv" style="cursor: pointer;" @click="goGoodDetail(item.id)">
              <img :src="item.mainImg" />
            </div>
            <div class="goodDetailDiv">
              <p class="fontType1" style="cursor: pointer;" @click="goGoodDetail(item.id)">{{item.name}} </p>
              <span class="fontType2">{{item.price}}¥</span>
              <collectBtn id="collectBtn" :goodId="item.id"></collectBtn>
              <i id="buyBtn"     class="el-icon-s-shop"></i>
            </div>
          </div>
        </div>
    </div>
  </template>
  <script>
import collectBtn from '../components/collectBtn.vue'
export default {
    components: {collectBtn},
    data() {
      return {
        search:'',
        cityList:[],
        nowCity:'',
        defaultRegion: '长春',
        goodList:[],
      }
    },
    created() {
      this.getCityList();
      this.getGoodList();

    },
    methods: {
        refresh(){
          // console.log(this.nowCity);
            document.getElementById("refreshButton").blur();
        },
        async getCityList(){
          const{data:result} = await this.$axios.get("address/allCity"); 
          this.cityList = result.data;
          this.nowCity = this.cityList[0].id;
        },
        async getGoodList(){
            const{data:result} = await this.$axios.get("good/allGoodWithUser"); 
            this.goodList = result.data;
            for(let i=0;i<this.goodList.length;i++){
              for(let j=0;j<this.goodList[i].pictureList.length;j++){
                if(this.goodList[i].pictureList[j].type == 1){
                  this.$set(this.goodList[i],'mainImg',this.$imgPath + this.goodList[i].pictureList[j].src);
                  break;
                }
              }
            }
            // console.log(this.goodList[0].user);
        },
        filterlist(nowCity,search) { 
          return this.goodList.filter(
            function (val) { 
              // console.log(val);
              return ((val.user.addressId == nowCity)&&(!search || val.name.toLowerCase().includes(search.toLowerCase())));
            }
          ) 
        },
        goGoodDetail(goodId) {
            this.$router.push({  
                path:'/goodDetail',
                query:{goodId:goodId},
            })
        },
        goGoodList() {
            this.$router.push({  
                path:'/goodList'
            })
        },
    },
    // computed:{
    //   ListDivWidth:function(){
    //     // console.log(window.screen.availWidth );
    //     return window.screen.availWidth  * 0.9;
    //   },
    //   goodDivWidth:function(){
    //       // console.log(this.ListDivWidth +'px ' + (this.ListDivWidth/4)+'px');
    //        return (this.ListDivWidth/4)+'px';
    //   },
      
    // }
  }
  </script>
  <style lang="less" scoped>
    @import "~@/assets/css/global.css";
    .topBar{
        /* margin-top:100px; */
        width: 100%;
        padding: 0 10px;
        padding-top: 10px;
        /* background-image: url(../assets/picture/方格.png); */
        box-sizing: border-box;
        background-color: rgba(0, 0, 0, 0);
        background-color: white;
        height:50px;
        border-top-left-radius: 25px;
        border-top-right-radius: 25px;
    }
    .refreshDiv{
        margin:0 20px;
    }
    .searchDiv{
        
        line-height: 50px;
        width:200px;
        display: inline-block;
        
    }
    .regionDiv{
        width:200px;
        line-height: 50px;
        display: inline-block;
    }
    .moreDiv{
        /* line-height: 50px; */
        height: 100%;
        /* line-height: normal; */
        float: right;
        
    }
    .list{
        /* background-color: rgba(0, 0, 0, 0.247); */
        background-color: white;
        /* margin-top:20px; */
        /* margin:10px 0 auto; */
        width:100%;
        min-height: 350px;
        /* border-radius: 25px; */
    }
    .goodDiv{
        /* background-color: rgb(245,245,245); */
        box-sizing:border-box;
        width:25%;
        height:100%;
        padding:20px;
        display: inline-block;
    }
    /* 图片自适应盒子 */
    .goodImgDiv{
      /* background-color: white; */
      background-color: rgb(245,245,245);

      /* margin:0 10px; */
      overflow: hidden;

      display: table-cell;
        display: inline-block;
        width:100%;
        height:300px;
        vertical-align: middle;
        align-items: center;
        /* display:inline-flex;
            flex-direction: row;
            justify-content: center;
            align-items: center; */
            /* 这是设置垂直居中的 */
    }
    .goodImgDiv img{
      max-width: 100%;
        /* min-height: 80%; */
        max-height: 100%;
        align-items: center;
        display: block;
        height: 100%;
        /* width:100%; */
        transition: all 0.8s;
        text-align: center;
        margin:0px auto;
        cursor: pointer;  
    }
    img:hover{
        transform: scale(1.2); 
    }
    .goodDetailDiv{
      position: relative;
      box-sizing:border-box;
      padding:20px 0 20px 20px;
      background-color:white;
      width:100%;
      height:100px;
    }
    .goodTitle{
        text-align: center;
        margin-bottom: 20px;
    }
    #collectBtn{
      font-size: 30px;
      position: absolute;
      right:10px;
      top:40px;
    }
    #buyBtn{
      font-size: 30px;
      position: absolute;
      right:50px;
      top:40px;
    }
  </style>