<template>
    <div>
      <div class="title">
        <h2>我的订单</h2> 
        <p>您需要对购买到的商品的订单进行处理</p>
      </div>
        <el-table
      :data="orderList.filter(data => !search || data.number)"
      style="width: 100%;">
        <el-table-column
          prop="id"
          label="订单ID"
          width="70">
        </el-table-column>
        <el-table-column
          prop="number"
          label="订单编号"
          width="210">
        </el-table-column>
        <el-table-column
          prop="dealType"
          label="交易类型"
          width="100"
          :formatter="dealTypeFormat">
        </el-table-column>>
        <el-table-column
          prop="date"
          label="订单生成日期"
          width="130"
          :formatter="dateFormat1">
        </el-table-column>
        
        
        <el-table-column
          prop="good.name"
          label="购买商品"
          width="200">
        </el-table-column>
        <el-table-column
          prop="money"
          label="总金额"
          width="100">
        </el-table-column>
        <el-table-column
          prop="sellerName"
          label="卖家"
          width="100">
        </el-table-column>
        
        <el-table-column
          prop="offlineDealDate"
          label="线下交易时间"
          width="200"
          :formatter="dateFormat2">
        </el-table-column>
        <el-table-column
          prop="userAddress.detail"
          label="收货地址/面交地址"
          width="500">
        </el-table-column>
        <el-table-column
          prop="status"
          label="订单状态"
          width="200"
          fixed="right"
          :formatter="statusFormat">
        </el-table-column>
        <el-table-column
          header-cell-class-name="conmandStyle"
          fixed="right"
          label="操作"
          width="200">
          <template slot="header" slot-scope="scope">
            <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
            </template>
            <template #default="scope"  >
                <el-button @click="handleOrder(scope.row)" type="text" size="medium" >{{scope.row.status|handleFormat}}</el-button>
                <el-button @click="OpenDialog(scope.row)" type="text" size="medium" v-if="scope.row.status == 8">调整</el-button>
            </template>
        </el-table-column>
    </el-table>



     <!-- 面交信息悬浮窗 -->
     <el-dialog title="面交信息" :visible.sync="DialogFormVisible" width="50%" >
         <div style="text-align: center; margin-bottom: 50px;">
             <p>用户的约定地址:{{chosenRow.userAddress.detail}}</p>
             <p>用户的约定时间:{{chosenRow.offlineDealDate|dateFormat}}</p>

         </div>

        <el-form :model="offLineMessageForm" ref="offLineMessageFormRef" label-width="150px">
          <el-form-item label="新的约定地址">
            <el-select v-model="newAddressId" placeholder="请选择新的地址">
              <el-option
              v-for="item in addressList"
              :key="item.id"
              :label="item.name"
              :value=item.id>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="新的约定时间">
                <el-date-picker
                v-model="newTime"
                type="datetime"
                placeholder="选择日期时间">
                </el-date-picker>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="DialogFormVisible = false">取 消</el-button>
            <el-button  type="primary" @click="handleOffLineMessage">发起约定</el-button>
          </span>
        </template>
    </el-dialog>
    </div>
</template>
<script>
import {getUser} from '../assets/js/comn.js'
export default {
    data() {
        return {
            search:'',//搜索框
            orderList:[],
            addressList:[],
            DialogFormVisible:false,//悬浮窗是否可见
            offLineMessageForm:{ //实际在调整时会被覆盖为整个订单信息
                userAddress:{detail:''},
                offLineMessageForm:'',
            },
            chosenRow:{ //实际在调整时会被覆盖为整个订单信息
                userAddress:{detail:''},
                offLineMessageForm:'',
            },
            newTime:'',//调整面交时设置的新时间
            newAddressId:'',
        }
    },
    created() {
        this.getOrderList();
        this.getAddressList();
    },
    methods: {    
        async handleOffLineMessage(){
            
            this.offLineMessageForm.status = 7;//将状态更改为商家面交信息未处理
            this.offLineMessageForm.offlineDealDate = this.newTime;//将约定时间改为新时间
            this.offLineMessageForm.userAddressId = this.newAddressId;//将地址id更改为新地址id
            // this.offLineMessageForm.userAddress='';//清空userAddress,便于下次查询时获取新的userAddress
            console.log(this.offLineMessageForm);
            const{data:result} = await this.$axios.post("order/updateOrder",this.offLineMessageForm); 
            this.DialogFormVisible = false;
            this.getOrderList();
        },
        OpenDialog(row){
            this.chosenRow = row;
            this.DialogFormVisible = true;
            this.offLineMessageForm = JSON.parse(JSON.stringify(row))//让row处订单信息,覆盖当前表单的信息(不能直接用等号赋值,因为赋值过去的是指针,两个数组会是同一个数组);
            console.log(this.offLineMessageForm)
        },
        async getAddressList(){
            const{data:result} = await this.$axios.get("address/allUserAddressByUserId?userId=" + getUser().id); 
            this.addressList = result.data;
        },
        dateFormat1(row, column) {
            let datetime = row[column.property];

            if(datetime){
                datetime = new Date(datetime);
                let y = datetime.getFullYear() + '年';
                let m = datetime.getMonth()+1 + '月';
                let d = datetime.getDate()+'日';
                return y + m + d;
            }
            return ''
        },
        dateFormat2(row, column) {
            let datetime = row[column.property];

            if(datetime){
                datetime = new Date(datetime);
                let y = datetime.getFullYear() + '年';
                let m = datetime.getMonth()+1 + '月';
                let d = datetime.getDate()+'日';
                let hh = datetime.getHours()+'时';
                let mm = datetime.getMinutes()+'分';
                return y + m + d + hh + mm;
            }
            return ''
        },
        dealTypeFormat(row, column) {
            let dealType = row[column.property];
            // console.log(dealType);
            if(dealType == 1)   return '线上交易';
            if(dealType == 2)   return '线下交易';
            else   return '其它?';
        },
        statusFormat(row, column) {
            let status = row[column.property];
            switch(status){
                case 1: return'未发货';
                case 2: return'等待买家确认收货';
                case 3: return'买家已收货,等待卖家结算订单';
                case 4: return'订单已关闭';
                case 7: return'商家面交信息未处理';
                case 8: return'用户面交信息未处理';
                case 9: return'等待用户确认面交完成';
            }
        },
        async getOrderList(){
            const{data:result} = await this.$axios.get("order/allOrderByBuyerId?buyerId="+getUser().id); 
            this.orderList = result.data;
            for(let i=0;i<this.orderList.length;i++){
                const{data:UserRes} = await this.$axios.get("user/getUserById?userId="+this.orderList[i].sellerId); 
                // console.log(UserRes);
                this.$set(this.orderList[i], 'sellerName', UserRes.data.userName)
            }
        },

        async deleteOrder(row){
            // console.log(row);
            const {data:result} = await this.$axios.post("order/deleteOrder",row); 
            // console.log(result);
            if(result == "suc")
            {
                this.$message.success("删除成功");
                this.getOrderList();
            }
            else
            {
                this.$message.error("删除失败");
            }
        },
        async handleOrder(row){
            console.log(row.id)
            const {data:result} = await this.$axios.post("order/processingOrder?orderId="+row.id); 
            // console.log(result);
            if(result == "suc")
            {
                this.$message.success("处理成功");
                this.getOrderList();
            }
            else
            {
                this.$message.error("处理失败");
            }
            
        }
    },
    filters: {
        handleFormat: function(val){
            switch(val){
                case 1: return'';
                case 2: return'确认收货';
                case 3: return'';
                case 7: return'';
                case 8: return'接受面交';
                case 9: return'面交已完成';
            }
        },
        dateFormat(val) {
            if(val){
                let datetime = new Date(val);
                let y = datetime.getFullYear() + '年';
                let m = datetime.getMonth()+1 + '月';
                let d = datetime.getDate()+'日';
                let hh = datetime.getHours()+'时';
                let mm = datetime.getMinutes()+'分';
                return y + m + d + hh + mm;
            }
            return ''
        },
    }       
}
    
</script>

<style lang="less" scoped>
  .title{
      text-align: center;
  }

  
</style>
