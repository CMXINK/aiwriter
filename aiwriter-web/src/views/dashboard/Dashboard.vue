<template>
  <div class="common-layout">
    <el-container style="height: 100%">
      <el-header style="height: 8%">
        <el-row style="height: 100%" align="middle">
          <el-col :span="4"><el-page-header content="文章详情" @back="goBack" /></el-col>
          <el-col :span="5" :offset="10" style="text-align: center">
            <router-link
              @click.prevent="queryData"
              :to="{
                path: 'title',
                params: {
                  data: resData
                }
              }"
              active-class="tab-active"
              >获取标题</router-link
            >
          </el-col>
          <el-col :span="5">
            <router-link
              :to="{
                path: 'introduction',
                params: {
                  data: resData
                }
              }"
              @click.prevent="queryData"
              active-class="tab-active"
              >获取简介</router-link
            >
          </el-col>
        </el-row>
      </el-header>
      <el-main style="height: 100%">
        <el-row style="height: 100%" :gutter="20">
          <el-col :span="15" style="height: 100%"
            ><div class="" />
            <Editor></Editor>
          </el-col>
          <el-col :span="9" style="height: 100%">
            <transition name="animate__animated animate__bounce" enter-active-class="animate__bounceInDown" appear>
              <router-view></router-view>
            </transition>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Editor from '@/components/content/Editor'
import ElLoading from 'element-plus'
export default {
  name: 'Dashboard',
  components: {
    Editor
  },
  data() {
    return {
      resData: ''
    }
  },
  methods: {
    queryData(e) {
      //  这里的APIa, APIb 暂时用来占位, 又真正API后替换掉只写Baseurl 之外的AIP
      const loading = this.$loading({
        lock: true,
        text: '请求中,请稍后...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      this.$http.get(e.target.innerText === '获取标题' ? '/menus/initmenus' : '').then(res => {
        loading.close()
        this.resData = res.data
      })
    }
  }
}
</script>

<style scoped>
.common-layout {
  padding: 0px;
  margin: 0px;
  height: 100%;
}
a {
  text-decoration: none;
  color: rgb(3, 3, 3);
}
.tab-active {
  font-weight: 700;
  border-bottom: 2px solid rgb(207, 4, 4);
}
.el-main {
  padding-top: 10px;
}
</style>
