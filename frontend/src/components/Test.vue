<template>
  <div>
		<el-upload
    action="http://localhost:9000/user/upload/"
    list-type="picture-card"
    :auto-upload="true"
    class="MainUpload"
    limit="3"
    >
      <i slot="default" class="el-icon-plus"></i>
  </el-upload>
<el-dialog :visible.sync="dialogVisible">
  <img width="100%" :src="dialogImageUrl" alt="">
</el-dialog>
</div>
</template>

<script>
export default {
	name: 'HelloWorld',
	data() {
      return {
        
      };
    },
    methods: {
      
    }
  }
  
	
	
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .uploadDiv{
    width:700px;
    background-color: blueviolet;
    margin:0 auto;
  }
</style>

