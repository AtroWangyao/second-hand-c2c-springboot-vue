<template>
    <div class="main">
        <el-page-header @back="goHome" :content="good.name">
        </el-page-header>
        <el-divider></el-divider>
        <div class="goodDiv">
            <div class="mainImgDiv">
                <img class="mainImg" :src ="mainImg"></img>
            </div>
            <div class="goodDetailDiv">
                <h3>{{good.name}}</h3>
                <span>购买日期:{{good.purchaseDate | dateFormat}}<el-divider direction="vertical"></el-divider></span>
                <span>出售日期:{{good.saleDate | dateFormat}}</span>
                
                <p class="fontType1">原价: <span class="fontType2">¥{{good.price}}</span></p>
                <p class="fontType1">折扣价: <span class="fontType2 fontColorRed">¥{{good.sprice}}</span></p>
                <p class="fontType1">品牌/厂商: <span class="fontType2">{{good.manufacturer}}</span></p>
                <p class="fontType1">交易方式:<span class="fontType2"> {{good.dealType | dealTypeFormat}}</span></p>
                <p class="fontType1">商品种类:<span class="fontType2"> {{good.categoryName}}</span></p>
                <p class="fontType1">标签:
                    <el-tag v-for="(item, index) in good.tagList" :key="index" style="margin-left: 3px;">{{item.name}}</el-tag>
                </p>
                
                <div class="propertyBarDiv">
                    <el-progress :percentage="good.appearanceValue"  color="red" :format="rateAppearance"></el-progress>
                    <el-progress :percentage="good.functionValue"  color="blue" :format="rateFunction"></el-progress>
                    <el-progress :percentage="good.packageValue"  color="green" :format="ratePackage"></el-progress>
                </div> 
                <router-link :to="{path:'/confirmOrder', query:{goodId:this.good.id}}"><el-button type="danger" class="buyBtn">立即购买<i class="el-icon-arrow-right el-icon--right"></i></el-button></router-link>
                <div id="collectBtn">
                    <!-- <i v-if="good.isCollected"  class="el-icon-star-on"  style="color:rgb(255, 153, 0)"@click="handleCollection"   ></i>
                    <i v-else   class="el-icon-star-on"  @click="handleCollection"    ></i>< -->
                    <collectBtn :goodId="this.$route.query.goodId"></collectBtn>
                </div>
            </div>
            <div class="imgListDiv">
                <div class="listImgdiv" v-for="(item, index) in imgList" :key="index">
                    <img  :src="item.src"  @click="changeMainImg(item.src)" />
                </div>
            </div>
            <div class="functionDiv">
                <template>
                    <el-tabs v-model="activeName" @tab-click="handleClick">
                        <el-tab-pane label="商品介绍" name="first">
                            <p>{{good.desc}}</p>
                        </el-tab-pane>
                        <el-tab-pane label="用户点评" name="second">
                            <div class="commentDiv">
                                <div class="comment"  v-for="(item, index) in commentList" :key="index">
                                    <div class="avatarDiv">
                                        <el-avatar :size="50" :src="getSrc(item.user.photo)"></el-avatar>
                                    </div>
                                    <strong class="commentUserName">{{item.user.userName}} <div class="sellerSign" v-if="item.type == 2||item.type == 3">商家</div></strong>
                                       
                                    
                                    <div class="commentDate"><span  >{{item.date | dateFormat}}</span></div>
                                    <p v-if="(item.type!=1)&&(item.type!=3)">{{item.text}}</p>
                                    <p v-else style="color:red">该评论违规,暂不可见</p>
                                    <div class="commentRate" v-if="item.type != 1 && item.type != 3">
                                        <el-rate
                                            v-model="item.score"
                                            disabled
                                            show-score
                                            text-color="#ff9900"
                                            score-template="{value}">
                                        </el-rate>
                                    </div>
                                </div>
                                <el-divider></el-divider>
                                <h2>你的评论</h2>
                                <div class="commentSubmitDiv">
                                    <div class="commentSubmitTextDiv">
                                        <el-input
                                        type="textarea"
                                        placeholder="请输入内容"
                                        v-model="commentForm.text"
                                        maxlength="130"
                                        show-word-limit
                                        resize="none"
                                        :rows="5"
                                        >
                                        </el-input>
                                    </div>
                                    <div class="commentSubmitRateDiv">
                                        <el-rate
                                        v-model="commentForm.score"
                                        >
                                        </el-rate>
                                    </div>
                                    <el-button class="commentSubmitRateBtn" type="primary" @click="submitComment">提交</el-button>
                                </div>
                            </div>
                            
                        </el-tab-pane>
                       
                    </el-tabs>
                </template>
            </div>
        </div>
    </div>
</template>
<script>
    import { getUser,rateFunction,ratePackage,rateAppearance,getSrc} from '../assets/js/comn.js'
import collectBtn from '../components/collectBtn.vue'
export default {
    components: {collectBtn},
    data() {
        return {
            imgList:[],
            mainImg:'',
            commentList:[],
            value:3.7,
            activeName: 'second',
            commentText:'',
            good:'',
            commentForm:{
                id: '',
                goodId: '',
                userId: '',
                text:'',
                score:3,
                data:'',
            },
            collection:{
                id:'',
                userId:getUser()?getUser().id:'',
                goodId:this.$route.query.goodId,
            }
        }
    },
    created() {
        this.getGood().then(res=>{
                this.commentForm.goodId = this.good.id;
                if(getUser())
                this.commentForm.userId = getUser().id;
            } 
        )
    },
    methods: {
        async getGood(){
            const{data:result} = await this.$axios.get("good/getGoodDetail?goodId="+this.$route.query.goodId); 
            this.good = result.good;
            this.commentList = result.commentList;
            this.$set(this.good, 'categoryName', result.categoryName)
            if(getUser()){
                const{data:data} = await this.$axios.get("collection/get?userId="+getUser().id + "&goodId="+this.$route.query.goodId);
                if(data.collection){//如果存在代表已收藏
                    this.$set(this.good, 'isCollected',true)
                    this.$set(this.good, 'collection',data.collection)
                }
                else{//如果不存在代表没有收藏
                    this.$set(this.good, 'isCollected',false)
                }
            }
            for(let i=0;i<this.good.pictureList.length;i++){//图片处理
                this.imgList.push({
                    src:this.$imgPath + this.good.pictureList[i].src,
                })
                if(this.good.pictureList[i].type == 1){//如果是主图的话,设为主图
                    this.mainImg = this.$imgPath + this.good.pictureList[i].src;
                }  
            }
            return result;
        },
        async getCommentList(){
            const{data:result} = await this.$axios.get("comment/getCommentListByGoodId?goodId="+this.$route.query.goodId); 
            this.commentList = result.commentList;
        },
        async submitComment(){
            if(!getUser()){
                this.$message.error("请先登录");
                return;
            }
            console.log("asd");
            this.commentForm.date = new Date()
            const {data:res} = await this.$axios.post("comment/add",this.commentForm); 
            console.log(res);
            if(res == "suc")
            {
                this.$message.success("评论成功");
                this.commentForm.text ='';
                this.getCommentList();
            }
            else
            {
                this.$message.error("评论失败");
            }
        },
        rateAppearance(percentage){
            return rateAppearance(percentage);
        },
        rateFunction(percentage){
            return rateFunction(percentage);
        },
        ratePackage(percentage){
            return ratePackage(percentage);
        },
        changeMainImg(src){
            this.mainImg = src;
        },
        getSrc(src){
            return this.$imgPath + src;
        },
        goHome(){
            this.$router.push({path:'/home'});
        },
        handleClick(){
            
        },
        async handleCollection(){
            if(this.good.isCollected == true){//如果是已收藏,那就是解除收藏
                // console.log(this.good.collection);
                const {data:res} = await this.$axios.post("collection/delete",this.good.collection); 
                if(res == "suc")
                {
                    this.$message.success("该商品已移出收藏列表");
                    this.good.collection = ''; //清空该商品的收藏
                    this.good.isCollected = false;
                }
                else
                {
                    this.$message.error("移出收藏失败");
                }
            }else{//如果是未收藏,那就是添加
                const {data:BackCollection} = await this.$axios.post("collection/add",this.collection); //这里成功会回调collection
                if(BackCollection != "def")
                {
                    this.$message.success("已收藏该商品");
                    this.good.collection = BackCollection;
                    this.good.isCollected = true;
                }
                else
                {
                    this.$message.error("添加收藏失败");
                }
            }
            

        }
    },
    filters: {
        dealTypeFormat: function(val){
            switch(val){
                case 0: return'均不可';
                case 1: return'线下面交';
                case 2: return'线上交易';
                case 3: return'均可';
            }
        },
        dateFormat: function(val){
            // console.log(val);
            val = new Date(val);
            let y = val.getFullYear() + '-';
            let m = val.getMonth()+1 + '-';
            let d = val.getDate();
            return y + m + d;
        },

    }
}
</script>
<style lang="less" scoped>
    @import "~@/assets/css/global.css";
    /* .goodDiv{ */
        /* background-color: blue; */
        /* height:900px; */
    /* } */
    .main
    {
        margin: 70px auto 0  auto;
        box-sizing: border-box;
        min-height:2000px;
        /* background-color: wheat; */
        width:1300px;
        padding:50px 0px ;
    }
    .goodDetailDiv{
        vertical-align:top;
        display: inline-block;
        position:relative;
        width:550px;
        height:700px;
        padding-left: 100px;
        /* background-color:white; */
    }
    .goodTitle{
        text-align: center;
        margin-bottom: 20px;
    }
    #collectBtn{
      font-size: 30px;
      position: absolute;
      right:0px;
      top:30px;
      cursor:pointer;
    }
    .imgListDiv{
        
        margin-top:10px;
        /* width:620px; */
        height:200px;
        /* background-color: cyan; */
    }
    /* 图片自适应盒子 */
    .mainImgDiv{
        display: table-cell;
        display: inline-block;
        margin-right:30px;
        width:620px;
        height:700px;
        vertical-align: middle;
        align-items: center;
        /* display:inline-flex;
            flex-direction: row;
            justify-content: center;
            align-items: center; */
            /* 这是设置垂直居中的 */
    }
    .mainImg{
        max-width: 100%;
        min-height: 80%;
        max-height: 100%;
        vertical-align: middle;
        align-items: center;
    }
    .listImgdiv{
        cursor: pointer;
        margin-right:5px;
        display: inline-block;
        width:170px;
        height:130px;
        background-color: rgb(214, 214, 214);
        border:solid black 1px;
        display: table-cell;
        display: inline-block;
        vertical-align: top;
    }
    .listImgdiv img{
        min-height: 100%;
        min-width:100%;
        max-width: 100%;
        max-height: 100%;
        vertical-align: middle;
    }
    .functionDiv{
        margin-top:40px;
        width:100%;
        /* height:500px; */
        /* background-color: darkmagenta; */
    }
    .propertyBarDiv{
        margin:0;
        
        width:500px;
    }
    .propertyBarDiv * {
        margin: 30px 0;
    }
    /* 评论区 */
    .comment{
        /* padding-top:10px; */
        margin-bottom:10px;
        position: relative;
        width:100%;
        height:100px;
        
    }
    .comment p{
        display:inline-block;
        position:absolute;
        top:30px;
        left:60px;
        font-style: italic;
    }
    .avatarDiv{
        box-sizing: border-box;
        height:100%;
        padding-top:15px;
        display: inline-block;
    }
    .commentUserName{
        display:inline-block;
        vertical-align:top;
        margin-left:10px;
        margin-top:15px;
    }
    .commentDate{
        margin-top:15px;
        margin-left:10px;
        font-style:normal;
        vertical-align:top;
        display: inline-block;
        /* line-height: normal; */
        /* font-size: 8px; */
    }
    .commentRate{
        position:absolute;
        right:10px;
        top:10px;
    }
    .commentSubmitDiv{
        height:500px;
        
        width: 100%;
    }
    .commentSubmitTextDiv{
        width:80%;
        margin-bottom: 10px;
    } 
    .commentSubmitRateDiv{
        margin: 0 auto;
        display: inline-block;
    } 
    .commentSubmitRateBtn{
        display: inline-block;
    } 
    .buyBtn{
        float:right;
        width:200px;
        margin-top:50px;
    }
    .blue{
        color:blue;
    }
    .sellerSign{
        display: inline-block;
        border-left:3px red solid;
        font-size: 12px;
        padding-left: 5px;
        font-weight:normal;
        font-style: italic;
    }
</style>