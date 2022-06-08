<template>
  <div class="common-layout">
    <el-container style="height: 100%">
      <el-aside width=auto>
        <Aside></Aside>
      </el-aside>
      <el-main style="background: #f8f8f8">
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Aside from "@/components/content/Aside";
export default {
  name: "Main",
  components: {
    Aside,
  }
}
</script>

<style scoped>
  .common-layout{
    padding: 0px;
    margin: 0px;
    height: 100%;  }
</style>
