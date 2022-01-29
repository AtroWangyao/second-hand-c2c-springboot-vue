<template >
    <div class="main">
        <el-page-header @back="back" content="订单确定">
        </el-page-header>
        <el-divider></el-divider>
        <div class="dealDiV">
            <el-tabs>
                <el-tab-pane label="线上交易">
                    <h3>收货地址</h3>
                    <el-divider></el-divider>
                    <!-- 地址列表 -->
                    <div class="addressCardListDiv">
                        <div class="btnDiv">
                            <el-button type="primary" @click="OpenDialogForm('add')">新增地址</el-button>
                        </div>
                        <div class="addressCardDiv" v-for="(item, index) in addressList" :key="index">
                            <el-card class="box-card">
                                <div slot="header" class="clearfix">
                                  <span>{{item.name}}</span>
                                  <div class="fontType2" style="display: inline-block; margin-left:10px">{{item.cityName}}</div>
                                  <div class="chosenSign" v-if="item.id==order.userAddressId">已选择</div>
                                  <el-button style="float: right; padding: 3px 6px" type="text" @click="deleteAddress(index)">删除 </el-button>
                                  <el-button style="float: right; padding: 3px 6px" type="text" @click="OpenDialogForm('update',index)">编辑</el-button>
                                  <el-button style="float: right; padding: 3px 6px" type="text" v-if="item.id!=order.userAddressId" @click="setChosen(item.id)">选中</el-button>
                                </div>
                                <div style="height:100px;">
                                   
                                    {{item.detail}}
                                </div>
                            </el-card>
                        </div>
                    </div>
                    <!-- 新增地址悬浮窗 -->
                    <el-dialog :title="titleName" :visible.sync="DialogFormVisible" width="50%" >
                        <el-form :model="addressForm" ref="addressFormRef" label-width="80px">
                          <el-form-item label="地址ID" prop="id" v-if="(DialogType != 'add')">
                            <el-input v-model="addressForm.id" :disabled=true></el-input>
                          </el-form-item>
                          <el-form-item label="地址名称" prop="name">
                            <el-input v-model="addressForm.name"></el-input>
                          </el-form-item>
                          <el-form-item label="城市/地区" prop="cityId">
                            <el-select v-model="addressForm.cityId" placeholder="请选择所属的城市(区域)">
                              <el-option
                              v-for="item in cityList"
                              :key="item.id"
                              :label="item.name"
                              :value=item.id>
                              </el-option>
                            </el-select>
                          </el-form-item>
                          <el-form-item label="详细地址" prop="detail">
                            <el-input type="textarea" v-model="addressForm.detail"></el-input>
                          </el-form-item>
                        </el-form>
                        <template #footer>
                          <span class="dialog-footer">
                            <el-button @click="DialogFormVisible = false">取 消</el-button>
                            <el-button v-if="(DialogType == 'add')" type="primary" @click="handleAddress()">确 定</el-button>
                            <el-button v-else type="primary" @click="handleAddress()">更改</el-button>
                          </span>
                        </template>
                    </el-dialog>
                    <!-- 支付方式 -->
                    <div class="payDiv">
                        <span>支付方式:</span>
                        <el-select v-model="payOptionShow" placeholder="请选择">
                            <el-option
                            v-for="item in payOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                            :disabled="item.disabled">
                            </el-option>
                        </el-select>
                        <el-button type="danger" @click="addOrder(1)">支付</el-button>
                        <!-- 参数1,线上交易 -->
                    </div>
                </el-tab-pane>
                <el-tab-pane label="线下交易" style="height:max-content;">
                    <h3>面交地址</h3>
                    <el-divider></el-divider>
                    <!-- 地址列表 -->
                    <div class="addressCardListDiv">
                        <div class="btnDiv">
                            <el-button type="primary" @click="OpenDialogForm('add')">新增地址</el-button>
                        </div>
                        <div class="addressCardDiv" v-for="(item, index) in addressList" :key="index">
                            <el-card class="box-card">
                                <div slot="header" class="clearfix">
                                  <span>{{item.name}}</span>
                                  <div class="fontType2" style="display: inline-block; margin-left:10px">{{item.cityName}}</div>
                                  <div class="chosenSign" v-if="item.id==order.userAddressId">已选择</div>
                                  <el-button style="float: right; padding: 3px 6px" type="text" @click="deleteAddress(index)">删除 </el-button>
                                  <el-button style="float: right; padding: 3px 6px" type="text" @click="OpenDialogForm('update',index)">编辑</el-button>
                                  <el-button style="float: right; padding: 3px 6px" type="text" v-if="item.id!=order.userAddressId" @click="setChosen(item.id)">选中</el-button>
                                </div>
                                <div style="height:100px;">
                                   
                                    {{item.detail}}
                                </div>
                            </el-card>
                        </div>
                    </div>
                    <h3>面交时间</h3>
                    <el-divider></el-divider>
                    <div class="block">
                        <el-date-picker
                          v-model="order.offlineDealDate"
                          type="datetime"
                          placeholder="选择日期时间">
                        </el-date-picker>
                      </div>
                      <div class="payDiv">
                        <span>支付方式:</span>
                        <el-select v-model="payOptionShow" placeholder="请选择">
                            <el-option
                            v-for="item in payOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                            :disabled="item.disabled">
                            </el-option>
                        </el-select>
                        <el-button type="danger" @click="addOrder(2)">支付</el-button>
                        <!-- 参数2,线下交易 -->
                    </div>
  
                    <!-- 地址列表 -->
                </el-tab-pane>
            </el-tabs>
        </div>
        <div class="goodDetailDiv">
            <h3>订单内容</h3>
            <el-divider></el-divider>
            <div class="detailDiv">
                <div class="detailImgDiv">
                    <img class="detailImg" :src="goodImg">   
                </div>
                <div class="detailText">
                    <h4 class="goodTitle" >{{good.name}}</h4>
                    <p></p>
                    <!-- <span>浏览: 12次<el-divider direction="vertical"></el-divider></span>
                    <span>被收藏: 12次</span> -->
                    <p class="fontType1">原价: <span class="fontType2">¥{{good.price}}</span></p>
                    <p class="fontType1">品牌: <span class="fontType2">{{good.manufacturer}}</span></p>
                    <p class="fontType1">交易方式: <span class="fontType2">{{good.dealType | dealTypeFormat}}</span></p> 
                    <p class="fontType1">商品种类:<span class="fontType2"> {{good.categoryName}}</span></p>
                </div>
                <div class="propertyBarDiv">
                    <el-progress :percentage="70"  color="red" :format="rateAppearance"></el-progress>
                    <el-progress :percentage="90"  color="blue" :format="rateFunction"></el-progress>
                    <el-progress :percentage="80"  color="green" :format="ratePackage"></el-progress>
                </div>
            </div>
            <div class="moneyDiv">
                <p><span class="floatLeft">商品金额：</span><span class="floatRight">¥{{good.price}}</span></p>
                <p><span class="floatLeft">折扣:</span><span class="floatRight">¥{{good.sprice}}</span></p>
                <p><span class="floatLeft">运费:</span><span class="floatRight">无</span></p>
                <el-divider></el-divider>
                <div class="total">
                    <p><span class="floatLeft">合计：</span><span class="floatRight fontColorRed">¥{{good.sumMoney}}</span></p>
                </div>
            </div>
        </div>    

    </div>
</template>
<script>
import { rateFunction,ratePackage,rateAppearance,getUser} from '../assets/js/comn.js'
import AddressCardList from '../components/AddressCardList.vue'

export default {
    components: {AddressCardList},
    data() {
        return {
            addressData:[
                {
                    id:1,
                    ischosenCard:false,
                },
                {
                    id:2,
                    ischosenCard:false,
                },
            ],
            payOptions: [{
                value: '选项1',
                label: '微信支付',
            },{
                value: '选项2',
                label: '支付宝支付',
                disabled: true
            }, {
                value: '选项3',
                label: '银行卡',
                disabled: true
            }],
            payOptionShow:"请选择",
            currentRow: null,
            good:'',
            goodImg:'',
            DialogFormVisible:false,
            DialogType:'add',
            titleName:'',//弹出地址悬浮框的名称
            addressForm:{
                id:'',   
                userId:'',
                cityId:'',
                name:'',
                detail:'', 
                type:'',
            },
            order:{
                id:'',
                number:'',
                sellerId:'',
                buyerId:'',
                dealType:'',
                date:'',
                status:'',
                userAddressId:'',
                goodId:'',
                offlineDealDate:'',
            },
            addressList:[],//(用户的)地址列表
            cityList:[],//城市列表
            
        }
    },
    created() {
        console.log(this.$route.query.goodId);
        this.getGood();
        var user = getUser()
        if(user){
            this.addressForm.userId = user.id;//这里仅能够更改自身的地址(就是登陆用户的地址),所以地址表单中的userId固定为user.userId
            console.log(user);
            //console.log(this.addressForm);
        }
        this.getCityList().then(res=>{  
            this.getAddressList();//不要调换位置,因为此处需要使用到上面获取的userId
        });
    },
    methods: {
        back(){
            this.$router.back(-1);
        },
        async addOrder(dealType){
            console.log(this.good);
            if(this.good.userId == this.addressForm.userId){
                this.$message.error("不能购买自己出售的商品哦");
                return;
            }
            this.order.sellerId = this.good.userId;//卖家id就是商品的拥有者id
            // console.log("aaa" + this.user);
            this.order.buyerId = this.addressForm.userId;//买家id是当前登录用户的id,也就是当前地址表所有者的id
            this.order.goodId = this.good.id;//商品id就是商品的id
            this.order.dealType = dealType;//交易类型是传递进来的交易类型,线上交易或者线下交易
            this.order.date = new Date();//时间是当前时间
            this.order.status = 7;//订单状态是7,代表是线下订单.(后面会把线上订单的状态改掉)
            this.order.money = this.good.sumMoney;//订单金钱数是商品原价-商品折扣
            if(dealType != 2){//如果交易类型不是线下交易的话,要把线下交易日期设为空,并且将订单状态也改为线上订单状态1
                this.order.offlineDealDate = ''
                this.order.status = 1;//订单状态是1,订单已生成
            }
            //userAddressId 是选中的地址id,number去后端生成.

            const {data:res} = await this.$axios.post("order/addOrder",this.order);
            if(res == "suc")
            {
                this.$message.success("购买成功");
                this.$router.push({path:"/home"});
            }
            else
            {
                this.$message.error("购买失败");
            }
            this.getAddressList();
        },
        async getGood(){
            const{data:result} = await this.$axios.get("good/getGood?goodId="+this.$route.query.goodId); 
            this.good = result.good;
            this.$set(this.good, 'categoryName', result.categoryName)
            this.$set(this.good, 'sumMoney', result.good.price-result.good.sprice)//计算总额
            for(let i=0;i<this.good.pictureList.length;i++){
                if(this.good.pictureList[i].type == 1){
                    this.goodImg = this.$imgPath + this.good.pictureList[i].src;
                }  
            }
            return result;
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
        
        OpenDialogForm(DialogType,index){ //dialogType是这个悬浮窗的类型(添加,修改),index是当前打开的地址下标(addressList的下标,如果是添加类的话,则不会使用这个参数)
                if(DialogType=="add")
                {
                    this.addressForm.id = '';
                    this.DialogType = 'add';
                    this.titleName="添加地址";
                }
                else if(DialogType=="update")
                {
                    this.DialogType = 'update';
                    // this.addressForm = this.addressList[index];//让index下标处的地址信息,覆盖当前表单的信息
                    this.addressForm = JSON.parse(JSON.stringify(this.addressList[index]));//让index下标处的地址信息,覆盖当前表单的信息(不能直接用等号赋值,因为赋值过去的是指针,两个数组会是同一个数组)
                    this.titleName="修改地址";
                }
                this.DialogFormVisible = true;
            },
            async getAddressList(){
                const{data:result} = await this.$axios.get("address/allUserAddressByUserId?userId="+this.addressForm.userId); 
                this.addressList = result.data;
                for(let i=0;i<this.addressList.length;i++){
                    
                    // console.log(this.cityList);
                    let index = this.cityList.findIndex((value)=>{return value.id == this.addressList[i].cityId})//在城市列表中找到城市id与用户住址表中城市id相同的城市
                     this.$set(this.addressList[i], 'cityName', this.cityList[index].name) //给这个用户住址表中装一个城市名称,用于住址卡片上显示.
                    if(this.addressList[i].type == 1){//把其中的默认地址找出来
                        this.order.userAddressId = this.addressList[i].id;
                    }
                }
            },
            async getCityList(){
                const{data:result} = await this.$axios.get("address/allCity"); 
                this.cityList = result.data;
                console.log( this.cityList)
            },
            handleAddress(){
                this.$refs.addressFormRef.validate(async valid=>{//这里一定要异步处理,才能获取到后端的信息
                    if(this.DialogType=="add"){//如果是添加就添加
                        const {data:res} = await this.$axios.post("address/addUserAddress",this.addressForm);
                        console.log(res);
                        if(res == "suc")
                        {
                            this.DialogFormVisible=false;
                            this.getAddressList();
                            this.$message.success("添加成功");
                        }
                        else
                        {
                            this.$message.error("添加失败");
                        }
                    }
                    else{ //if(this.DialogType=="update")如果是修改就修改
                    const {data:res} = await this.$axios.post("address/updateUserAddress",this.addressForm);
                    console.log(res);
                    if(res == "suc")
                    {
                        this.DialogFormVisible=false;
                        this.getAddressList();
                        this.$message.success("修改成功");
                    }
                    else
                    {
                        this.$message.error("修改失败");
                    }
                }
                
            })
        }, 
        setChosen(addressId){
            this.order.userAddressId = addressId;
        },
        async deleteAddress(index){
            this.addressForm = JSON.parse(JSON.stringify(this.addressList[this.index]))//让index下标处的地址信息,覆盖当前表单的信息(不能直接用等号赋值,因为赋值过去的是指针,两个数组会是同一个数组)
            const {data:res} = await this.$axios.post("address/deleteUserAddress",this.addressForm);
            if(res == "suc")
            {
                if(order.userAddressId == this.addressList[this.index].id){//如果删除的正好是选中的地址的话,要重置选中地址的下标
                    order.userAddressId = '';
                }
                this.$message.success("删除成功");
            }
            else
            {
                this.$message.error("删除失败");
            }
            this.getAddressList();
        },
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
        cityFormat: function(val){
            // console.log(this.cityList);
            // let index = this.cityList.find(val);
            // return index;
        },

    }
}
</script>      
<style scoped>
    @import "~@/assets/css/global.css";
    .payDiv{
        margin-top:70px;
    }
    .payDiv span{
        margin-right:350px;
    }
    .ischosenCard{
        border:red solid 2px;
    }
    
    .dealDiV{
        width:55%;
        /* background-color: chartreuse; */
        float:left;
    }
    .moneyDiv
    {
        
        box-sizing: border-box;
        width:100%;
        padding:0 50px;
    }
    .moneyDiv p{
        height:20px;
        font-size: 12px;
    }
    .total p{
        font-size: 16px;
    }
    /* 图片自适应盒子 */
    .detailImgDiv{
        width: 200px;
        height: 230px;
        margin-right:20px;
        display: table-cell;
        /* display: inline-block; */
        vertical-align: top;
        display:inline-flex;
        flex-direction: row;
        justify-content: center;
        align-items: center; 
        /* 这是设置垂直居中的*/
        
    }
    .detailImg{ 
        max-width: 100%;
        max-height: 100%;
        vertical-align: middle;
        
    }
    /* 图片自适应盒子 结尾*/
    .detailText{
        display: inline-block;
        
    }
    .detailDiv{
        background-color:rgba(255, 255, 255, 0.4);  
        box-sizing: border-box;
        padding:0 50px;
        /* background-color: darkgrey; */
        width:100%;
        /* display: inline-block; */
        
    }
    .goodDetailDiv{
        width:40%;
        
        /* background-color: chocolate; */
        float:right;
    }
    .propertyBarDiv{
        width:400px;
        padding-bottom: 30px;
        /* margin:0 auto; */
    }
    .main
    {
        /* overflow: hidden;
        zoom:1; */
        margin: 70px auto 0  auto;
        box-sizing: border-box;
        /* min-height:1200px; */
        /* background-color: wheat; */
        width:1300px;
        padding:50px 0px ;
    }
    .main:after{
        content: "";
        display: block;
        clear: both;
    }
    .addressCardListDiv{
            width:auto;
            margin:0 auto;
        }
    .addressCardDiv{
        width:600px;
        margin-right: 50px;
        margin-top: 30px;
        display: block;
    }
    .btnDiv{
        float:none;
    }
    .chosenSign{
        display: inline-block;
        border-left:rgb(255, 0, 0) solid 3px;
        margin-left:10px;
        padding-left:10px;
        color:rgb(20, 20, 20);
        font-size:7px;
    }
</style>
