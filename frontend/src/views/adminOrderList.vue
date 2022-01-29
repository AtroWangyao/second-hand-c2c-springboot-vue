<template>
    <div>
      <div class="title">
        <h2>订单列表</h2> 
        <p>在这里可以查看到所有的订单</p>
      </div>
        <el-table
      :data="orderList.filter(data => !search || data.number)"
      style="width: 100%;">
        <el-table-column
          prop="id"
          label="订单ID"
          width="100">
        </el-table-column>
        <el-table-column
          prop="number"
          label="订单编号"
          width="300">
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
          width="300"
          :formatter="dateFormat1">
        </el-table-column>
        <el-table-column
          prop="status"
          label="订单状态"
          width="200">
        </el-table-column>
        <el-table-column
          prop="userAddress.detail"
          label="收货地址"
          width="500">
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
          prop="sellerId"
          label="卖家id"
          width="100">
        </el-table-column>
        <el-table-column
          prop="buyerId"
          label="买家id"
          width="100">
        </el-table-column>
        <el-table-column
          prop="offlineDealDate"
          label="线下交易时间"
          width="200"
          :formatter="dateFormat2">
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
            <el-button @click="deleteOrder(scope.row)" type="text" size="medium" >删除</el-button>
          </template>
        </el-table-column>
    </el-table>
    </div>
</template>
<script>
export default {
    data() {
        return {
            search:'',//搜索框
            orderList:[],
        }
    },
    created() {
        this.getOrderList();
    },
    methods: {    
        dateFormat1(row, column) {
            let datetime = row[column.property];
            console.log(datetime);
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
            console.log(datetime);
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
        async getOrderList(){
            const{data:result} = await this.$axios.get("order/allOrder"); 
            this.orderList = result.data;
            console.log(this.orderList);
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
        }
    },       
}
    
</script>

<style lang="less" scoped>
  .title{
      text-align: center;
  }

  
</style>
