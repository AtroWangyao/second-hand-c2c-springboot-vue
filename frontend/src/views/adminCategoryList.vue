<template>
    <div>
      <div class="title">
        <h2>标签列表</h2> 
        <p>此处包含了所有的用于标记商品的标签,您可以对他们进行管理</p>
      </div>
        <el-table
      :data="categoryList.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100% ">
        <el-table-column
          prop="id"
          label="标签ID"
          width="100">
        </el-table-column>
        <el-table-column
          prop="name"
          label="名称"
          width="200">
        </el-table-column>
        <el-table-column
          prop="desc"
          label="描述"
          width="280">
        </el-table-column>
        <el-table-column>
          <template slot="header" slot-scope="scope">
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入关键字搜索"/>
            </template>
        </el-table-column>
        <el-table-column
          header-cell-class-name="conmandStyle"
          fixed="right"
          label="操作"
          width="200">
          <template slot="header" slot-scope="scope">
            <el-button type="primary" @click="OpenDialogForm('add')">新增标签</el-button>
            </template>
          <template #default="scope"  >
            <el-button @click="editCategory(scope.row)" type="text" size="medium">编辑</el-button>
            <el-button @click="deleteCategory(scope.row)" type="text" size="medium" >删除</el-button>
          </template>
        </el-table-column>
    </el-table>

    
    <!-- <el-button type="text" @click="AddDialogFormVisible = true">打开嵌套表单的 Dialog</el-button> -->
    <!-- 浮动窗口,新增和编辑 -->
    <el-dialog title="添加标签" :visible.sync="AddDialogFormVisible" width="50%" @close="addFormClear()">
      <el-form :model="addForm" ref="addFormRef" label-width="80px">
        <el-form-item label="标签ID" prop="addForm.id" v-if="(DialogFormIsAdd == false)">
          <el-input v-model="addForm.id" :disabled=true></el-input>
        </el-form-item>
        <el-form-item label="标签名" prop="addForm.name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="标签介绍" prop="addForm.desc">
          <el-input v-model="addForm.desc"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="AddDialogFormVisible = false">取 消</el-button>
          <el-button v-if="(DialogFormIsAdd == true)" type="primary" @click="addCategory()">确 定</el-button>
          <el-button v-else type="primary" @click="updateCategory()">更改</el-button>
        </span>
      </template>
    </el-dialog>
    
    
    </div>
</template>
<script>
    export default {
      data() {
        return {
          //搜索框
          search:'',
          //当为true的时候,代表弹出框应是添加框,为false时代表弹出框是更新框
          DialogFormIsAdd:true,
          //添加品类框是否显示
          AddDialogFormVisible: false,
          //品类列表
          categoryList:[],
          //增加品类的表单数据
          addForm:{
            id:'',
            name:'',
            desc:'',
          },
          formLabelWidth: '120px'
        }
      },
      created() {
          this.getCategoryList();
      },
    methods: {
        addFormClear(){
            this.addForm = {
                id:'',
                name:'',
                desc:'',
            };
        },
        //res是那行绑定的categoryList[那行]
        async editCategory(row){
            console.log(row.id);
            this.addForm.id = row.id;
            this.addForm.name = row.name;
            this.addForm.desc = row.desc;
            this.OpenDialogForm('update');
            console.log(this.DialogFormIsAdd);
        },
        OpenDialogForm(DialogType){
            if(DialogType=="add")
            {
            this.DialogFormIsAdd = true;
            }
            else if(DialogType=="update")
            {
            this.DialogFormIsAdd = false;
            }
            this.AddDialogFormVisible = true;
        },
        async getCategoryList(){
          const{data:result} = await this.$axios.get("good/allCategory"); 
          this.categoryList = result.data;
        },
        addCategory(){
            console.log(this.addForm.desc);
            this.$refs.addFormRef.validate(async valid=>{//这里一定要异步处理,才能获取到后端的信息
                const {data:res} = await this.$axios.post("good/addCategory",this.addForm); //这里不是很懂data:res是什么语法,回看(好像是解构赋值) 嗯..是解构赋值..起了个data的别名res
                if(res == "suc")
                {
                    this.AddDialogFormVisible=false;
                    this.$message.success("添加成功");
                    this.getCategoryList();
                }
                else
                {
                  this.$message.error("添加失败");
                }
            })
        },
        updateCategory(){
            this.$refs.addFormRef.validate(async valid=>{//这里一定要异步处理,才能获取到后端的信息
                const {data:res} = await this.$axios.post("good/updateCategory",this.addForm); //这里不是很懂data:res是什么语法,回看(好像是解构赋值) 嗯..是解构赋值..起了个data的别名res
                console.log(res);
                if(res == "suc")
                {
                    this.AddDialogFormVisible=false;
                    this.$message.success("更改成功");
                    this.getCategoryList();
                }
                else
                {
                  this.$message.error("更改失败");
                }
            })
        },
        //res是那行绑定的categoryList[那行]
        async deleteCategory(res){
                console.log(res);
                const {data:result} = await this.$axios.post("good/deleteCategory",res); 
                console.log(result);
                if(result == "suc")
                {
                  this.$message.success("删除成功");
                  this.getCategoryList();
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
  .el-upload {
    display: block;
    margin:0 auto;
  }
  .avatar-uploader{
    margin:0 auto;
    width:180px;
  }
  .avatar-uploader .el-upload {
    margin:0 auto;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 180px;
    height: 180px;
    line-height: 180px;
    text-align: center;
  }
  .avatarPreview{
    margin-top:20px;
    margin-right:80px;
    width:200px;
  }
  .title{
      text-align: center;
  }


  
</style>
