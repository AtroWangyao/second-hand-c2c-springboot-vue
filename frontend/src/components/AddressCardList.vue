<template>
    <div>
        <div class="addressCardListDiv">
            <div class="btnDiv">
                <el-button type="primary" class="floatRight" @click="OpenDialogForm('add')">新增地址</el-button>
            </div>
            <div class="addressCardDiv" v-for="(item, index) in addressList" :key="index">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                      <span>{{item.name}}</span>
                      <div class="fontType2" style="display: inline-block; margin-left:10px">{{item.cityName}}</div>
                      <div class="defaultSign" v-if="item.type==1">默认地址</div>
                      <el-button style="float: right; padding: 3px 6px" type="text" @click="deleteAddress(index)">删除 </el-button>
                      <el-button style="float: right; padding: 3px 6px" type="text" @click="OpenDialogForm('update',index)">编辑</el-button>
                      <el-button style="float: right; padding: 3px 6px" type="text" v-if="item.type!=1" @click="setDefault(index)">设为默认</el-button>
                    </div>
                    <div style="height:100px;">
                        <span></span>
                        {{item.detail}}
                    </div>
                </el-card>
            </div>
        </div>
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
              <!-- <el-form-item label="地址类型" prop="type">
                <el-input v-model="addressForm.type"></el-input>
              </el-form-item> -->
            </el-form>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="DialogFormVisible = false">取 消</el-button>
                <el-button v-if="(DialogType == 'add')" type="primary" @click="handleAddress()">确 定</el-button>
                <el-button v-else type="primary" @click="handleAddress()">更改</el-button>
              </span>
            </template>
        </el-dialog>
    </div>
    </template>
    <script>
    import { getUser,setUser } from '../assets/js/comn.js'
    export default {
      data() {
        return {
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
            defaultAddressIndex:-1,//目前默认地址所在addressList中的下标
            addressList:[],//(用户的)地址列表
            cityList:[],//城市列表
        }
      },
        created() {
            this.getCityList();
            var user = getUser()
            if(user){
                this.addressForm.userId = user.id;//这里仅能够更改自身的地址(就是登陆用户的地址),所以地址表单中的userId固定为user.userId
                //console.log(this.addressForm);
            }
            this.getAddressList();//不要调换位置,因为此处需要使用到上面获取的userId
        },
        methods: {
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
                    let index = this.cityList.findIndex((value)=>{return value.id == this.addressList[i].cityId})//在城市列表中找到城市id与用户住址表中城市id相同的城市
                     this.$set(this.addressList[i], 'cityName', this.cityList[index].name) //给这个用户住址表中装一个城市名称,用于住址卡片上显示.
                    if(this.addressList[i].type == 1){//把其中的默认地址找出来
                        this.defaultAddressIndex = i;
                    }
                }
            },
            async getCityList(){
                const{data:result} = await this.$axios.get("address/allCity"); 
                this.cityList = result.data;
                console.log()
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
        async setDefault(index){
            if(this.defaultAddressIndex != -1)//将之前的默认地址更新为非默认地址,如果有默认地址
            { 
                this.addressForm = JSON.parse(JSON.stringify(this.addressList[this.defaultAddressIndex]));//让index下标处的地址信息,覆盖当前表单的信息(不能直接用等号赋值,因为赋值过去的是指针,两个数组会是同一个数组)
                this.addressForm.type = 0;
                const {data:res} = await this.$axios.post("address/updateUserAddress",this.addressForm);
            }{//将选中地址更新为默认地址
                this.addressForm = JSON.parse(JSON.stringify(this.addressList[index]));//让index下标处的地址信息,覆盖当前表单的信息(不能直接用等号赋值,因为赋值过去的是指针,两个数组会是同一个数组)
                this.addressForm.type = 1;
                const {data:res} = await this.$axios.post("address/updateUserAddress",this.addressForm);
                this.defaultAddressIndex = index; //现在默认地址是index了
            }
            this.getAddressList();
        },
        async deleteAddress(index){
            this.addressForm = JSON.parse(JSON.stringify(this.addressList[index]))//让index下标处的地址信息,覆盖当前表单的信息(不能直接用等号赋值,因为赋值过去的是指针,两个数组会是同一个数组)
            const {data:res} = await this.$axios.post("address/deleteUserAddress",this.addressForm);
            if(res == "suc")
            {
                if(this.defaultAddressIndex == index){//如果删除的正好是默认地址的话,要重置默认地址的下标
                    this.defaultAddressIndex = -1;
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
    }
    </script>
    <style lang="less" scoped>
    @import "~@/assets/css/global.css";
    .title{
        text-align: center;
    }
    .addressCardListDiv{
        clear: both;
        width:1000px;
        margin:0 auto;
    }
    .addressCardDiv{
        width:450px;
        margin-right: 50px;
        margin-top: 50px;
        display: inline-block;
        vertical-align: top;
    }
    .defaultSign{
        display: inline-block;
        border-left:rgb(0, 60, 255) solid 3px;
        margin-left:10px;
        padding-left:10px;
        color:rgb(20, 20, 20);
        font-size:7px;
    }
    .btnDiv{
        width:1000px;
        height:40px
    }
    
    </style>