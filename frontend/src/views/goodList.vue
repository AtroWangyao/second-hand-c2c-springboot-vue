<template>
    <div class="main">
        
        <el-header style="height:100px;padding-top: 50px;">
            <el-page-header @back="goHome" content="商品清单">
            </el-page-header>
            <el-divider></el-divider>
        </el-header>
        <el-container class="container">
            <el-aside width="400px" class="aside">
                <div class="categoryDiv">
                    <h4>种类</h4>
                    <div class="checkboxDiv" v-for="(item, index) in categoryList" :key="item.id">
                        <el-checkbox @change="onCheckboxChange('category',item.id)" class="checkbox" :v-model="item.isChecked" :label="item.name" border ></el-checkbox>
                    </div>
                </div>
                <div class="tagDiv">
                    <h4>标签</h4>
                    <div class="checkboxDiv" v-for="(item, index) in tagList" :key="item.id">
                        <el-checkbox @change="onCheckboxChange('tag',item.id)" class="checkbox" :v-model="item.isChecked" :label="item.name" border ></el-checkbox>
                    </div>
                </div>
            </el-aside>
            <el-main width="900px" class="list">
                <div class="topBar"> 
                    <div class="searchDiv">
                      <el-input
                          placeholder="请输入内容"
                          prefix-icon="el-icon-search"
                          v-model="search">
                      </el-input>
                    </div>
                    <!-- <div class="refreshDiv inlineBlock">
                      <el-button  id="refreshButton" icon="el-icon-refresh" @click="refresh()"></el-button>
                    </div>
                    <div class="regionDiv">
                      <template>
                          <el-select v-model="defaultRegion" >
                            <el-option
                              v-for="item in region"
                              :key="item.value"
                              :label="item.label"
                              :value="item.value">
                              <span style="float: left">{{ item.label }}</span>
                              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                            </el-option>
                          </el-select>
                        </template>
                    </div> -->
                    
                   
                </div> 
                <div class="list" id="list" >
                    <div class="goodDiv"  v-for="(item, index) in filterlist(tagArr,categoryArr,search)" :key="index">
                      <div class="goodImgDiv" @click="goGoodDetail(item.id)">
                        <img :src=getGoodMainPicture(item.pictureList) />
                      </div>
                      <div class="goodDetailDiv">
                        <p class="fontType1" style="cursor: pointer;" @click="goGoodDetail(item.id)">{{item.name}}</p>
                        <span class="fontType2">¥{{item.price}}</span>
                           <collectBtn id="collectBtn" :goodId="item.id"></collectBtn>
                            <i   id="buyBtn"   style="cursor: pointer;" @click="goGoodDetail(item.id)" class="el-icon-s-shop"></i>
                      </div>
                    </div>
                  </div>
            </el-main>
        </el-container>
    </div>
</template>
<script>
import collectBtn from '../components/collectBtn.vue'
export default {
    components: {collectBtn},
    created() {
        this.getGoodList();
        this.getCategoryList();
        this.getTagList();
    },
    data() {
        return {
            goodList:[],
            categoryArr: [],
            tagArr: [],//选中的标签数组
            categoryList: [],
            tagList: [],//所有的标签
            props: { multiple: true },
            search:'',
            category: [
                {
                    id: 1,
                    name: '裤子',
                    isChecked:false,
                },
                {
                    id: 2,
                    name: '衣服',
                    isChecked:false,
                },
                {
                    id: 3,
                    name: '衣服',
                    isChecked:false,
                },
                {
                    id: 4,
                    name: '衣服',
                    isChecked:false,
                },
            ],
            tag: [
                {
                    id: 1,
                    name: '手工',
                    isChecked:false,
                },
                {
                    id: 2,
                    name: '五金',
                    isChecked:false,
                },
                {
                    id: 3,
                    name: '新货转手',
                    isChecked:false,
                },
                {
                    id: 4,
                    name: '又长又短',
                    isChecked:false,
                },
            ]
        }
    },
    methods: {
        goHome(){
            this.$router.push({path:'/home'});
        },
        onCheckboxChange(type,id){
            if(type == 'category'){
                let idIndex = this.categoryArr.findIndex((value)=>{ return value == id})//categoryArr中存在id的位置
                if(idIndex == -1)
                {
                    this.categoryArr.push(id);
                }
                else{
                    this.categoryArr.splice(idIndex,1); //删除1个 categoryArr idIndex 位置的元素
                }
            }
            else{// == tag
                let idIndex = this.tagArr.findIndex((value)=>{ return value == id})//tagArr
                if(idIndex == -1)
                {
                    this.tagArr.push(id);
                }
                else{
                    this.tagArr.splice(idIndex,1); //删除1个 tagArr idIndex 位置的元素
                }
            }
            // console.log(this.categoryArr);
        },
        async getCategoryList(){
            const{data:result} = await this.$axios.get("good/allCategory"); 
            this.categoryList = result.data;
        },
        async getTagList(){
            const{data:result} = await this.$axios.get("good/allTag"); 
            this.tagList = result.data;
        },
        async getGoodList(){
          const{data:result} = await this.$axios.get("good/allGood"); 
          this.goodList = result.data;
        },
        getGoodMainPicture(PictureList){
            for(let i=0;i<PictureList.length;i++)
            {
            if(PictureList[i].type == 1){
                return this.$imgPath + PictureList[i].src;
            }
            }
        },
        filterlist(tagArr,categoryArr,search) {  //多选时过滤器范围增大 而不是缩小
          return this.goodList.filter(
            function (val) { 
                //console.log(val);
                //console.log(tagArr);
                //console.log(categoryArr);
                if(tagArr.length != 0)
                for(let i=0;i<tagArr.length;i++){
                    if(val.tagList.findIndex((value)=>{ return value.id == tagArr[i]}) != -1)
                        break;
                    else if(i == tagArr.length - 1)//如果全找过还是找不到就false
                        return false;
                }   
                if(categoryArr.length != 0)
                for(let i=0;i<categoryArr.length;i++){
                    
                    if(categoryArr.findIndex((value)=>{;return value == val.categoryId}) != -1){//在种类数组里找到(该商品)的类别(如果能找到就break到true),找不到就false
                        

                        break;
                    }
                    else    
                        return false;
                }   
                if(!search || val.name.toLowerCase().includes(search.toLowerCase()))
                return true;
            }
          ) 
        },
        goGoodDetail(goodId) {
            this.$router.push({  
                path:'/goodDetail',
                query:{goodId:goodId},
            })

        },
    },
    
}
</script>
<style lang="less" scoped>
    .list{
        margin-top:20px;
        margin:10px 0 auto;
        width:100%;
        /* background-color: white; */
    }
    .goodDiv{
        /* background-color: coral; */
        box-sizing:border-box;
        width:280px;
        height:100%;
        padding:20px;
        margin-left:20px;
        margin-top:20px;
        background-color: rgb(245,245,245);
        display: inline-block;
    }
    .goodImgDiv{
      /* background-color: white; */
      /* margin:0 10px; */
      box-sizing: border-box;
      width:100%;
      height:300px;
      overflow: hidden;
      display: table-cell;
        display: inline-block;
        vertical-align: top;
        /* border:1px black solid; */
    }
    .goodImgDiv img{
        /* display: block; */
        height: 100%;
        width:100%;
        
        transition: all 0.8s;
        text-align: center;
        max-width: 100%;
        max-height: 100%;
        vertical-align: middle;
        cursor: pointer;  
        
    }
    img:hover{
        transform: scale(1.2); 
    }
    .goodDetailDiv{
      position: relative;
      box-sizing:border-box;
      padding:20px 0 20px 20px;
      /* background-color:darkgrey; */
      /* border:1px black solid; */
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
      cursor:pointer;
    }
    #buyBtn{
        /* height:50px;
      width:50px; */
      font-size: 30px;
      position: absolute;
      right:50px;
      top:40px;
    }
    .topBar{
        
        margin-top:0;
        width: 100%;
        /* background-color:black; */
        height:50px;
    }
    .refreshDiv{
        margin:0 20px;
    }
    .searchDiv{
        float: right;
        line-height: 50px;
        width:200px;
        /* display: inline-block; */
        
    }
    .regionDiv{
        width:200px;
        line-height: 50px;
        display: inline-block;
    }
    .moreDiv{
        /* display: inline-block; */
        float: right;
        
    }
    .tagDiv .checkboxDiv{
        width:150px;
        display: inline-block;
        margin:10px auto;
        margin-left:20px;
        /* margin-right: 70px; */
    }
    .tagDiv .checkboxDiv .checkbox{
        width:150px;
    }
    .categoryDiv .checkboxDiv{
        width:350px;
        /* display:block; */
        margin:10px auto;
        /* margin-right: 70px; */
    }
    .categoryDiv .checkboxDiv .checkbox{
        width:350px;
    }
    .categoryDiv{
        margin:0 auto;
    }
    .categoryDiv h4{
        border-bottom:border black 2px;
        display: block;
    }
    h4::after{
        content: '';
        height: 2px;
        width: 30px;
        display: block;
        margin-top: 15px;
        background: #303030;
    }
    .main
    {
        margin: 0 auto;
        box-sizing: border-box;
        /* height:2000px; */
        /* background-color: wheat; */
        width:1400px;
        padding:50px 0px ;
        border-radius: 25px;
    }
    .container{
        padding: 15px;
        background-color:rgba(255, 255, 255, 0.7);  
        border-radius: 25px;
    }
    /* .aside{
        
    }
    .list{
        
    } */
</style>