<template>
  <div class="common-layout">
    <el-container style="height: 100%">
      <el-header>
        <el-button type="primary" :icon="ArrowLeft">Previous Page</el-button>
      </el-header>
      <el-main style="height: 100%">
        <el-row :gutter="20" style="height: 100%">
          <el-col :span="15" style="height: 100%"><div class="" />
            <Editor></Editor>
          </el-col>
          <el-col :span="9" style="height: 100%"><div class="" /></el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Editor from "@/components/content/Editor";
export default {
  name: "Dashboard",
  components:{
    Editor,
  }
}
</script>

<style>
  .common-layout{
    padding: 0px;
    margin: 0px;
    height: 100%;
  }
</style>
