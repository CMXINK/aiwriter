<template>
  <div class="common-layout">
    <el-container style="height: 100%">
      <el-header style="height: 8%">
        <el-row style="height: 100%" align="middle" justify="center">
          <el-col :span="4"><el-page-header content="文章详情" @back="goBack" /></el-col>
          <el-col :span="4" :offset="11" class="left-bar" style="text-align: center">
            <router-link
              @click.prevent="queryData"
              :to="{
                path: 'title'
              }"
              active-class="tab-active"
              replace
              >获取标题</router-link
            >
          </el-col>
          <el-col :span="5" class="left-bar">
            <router-link
              :to="{
                path: 'introduction'
              }"
              @click.prevent="queryData"
              replace
              active-class="tab-active"
              >获取简介</router-link
            >
          </el-col>
          <!-- 如需要保存按键，则打卡该注释稍调整样式即可三个left-bar比列3:3:2,且都应该文字居中 -->
          <!-- <el-col :span="2" class="left-bar save" @click="save">保存</el-col> -->
        </el-row>
      </el-header>
      <el-main style="height: 100%">
        <el-row style="height: 100%" :gutter="20">
          <el-col :span="16" style="height: 100%"
            ><div class="" />
            <Editor></Editor>
          </el-col>
          <el-col :span="8" style="height: 100%">
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
import ElLoading, { dayjs } from 'element-plus'
import { mapState, mapMutations, mapGetters } from 'vuex'
export default {
  name: 'Dashboard',
  components: {
    Editor
  },
  computed: {
    ...mapState(['currentArticle']),
    ...mapGetters(['currentTitle'])
  },
  // 销毁组件触发保存当前数据, vue3 不再使用beforeDestroy, 使用beforeUnmount代替
  beforeUnmount() {
    this.save()
    this.reset_state()
  },
  created() {
    // 监听浏览器刷新行为,刷新前保存当前数据以防止刷新导致数据丢失
    window.addEventListener('beforeunload', function (e) {
      this.save()
    })
  },
  methods: {
    ...mapMutations({ switch_ifShowingTitle: 'SWITCH_IS_SHOWING_TITLE', switch_ifListALL: 'SWITCH_IS_LIST_All', reset_state: 'RESER_DATA' }),
    save() {
      console.log('====>title:', { ...this.currentArticle, updateTime: dayjs().format('YYYY-MM-DD HH:mm:ss'), titles: { status: '1', delFlag: '0', title: this.currentTitle(this.currentArticle) } })
      this.$http
        .post('/article/update', { ...this.currentArticle, updateTime: dayjs().format('YYYY-MM-DD HH:mm:ss'), titles: { status: '1', delFlag: '0', title: this.currentTitle(this.currentArticle) } })
        .then()
        .catch(error => {
          if (error.response) {
            console.log('res-res-d:', error.response.data)
            console.log('res-res-s:', error.response.status)
            console.log('res-res-h:', error.response.headers)
          } else if (error.request) {
            console.log('res-req:', error.request)
          } else {
            console.log('Error', error.message)
          }
        })
    },
    queryData(e) {
      const loading = this.$loading({
        lock: true,
        text: '请求中,请稍后...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      this.switch_ifListALL(false)
      this.$http
        .get(e.target.innerText === '获取标题' ? '/title/list' : '/summary/list', {
          params: {
            articleId: this.currentArticle.articleId,
            articleBody: this.currentArticle.articleBody,
            wordNum: this.currentArticle.wordNum,
            userId: this.currentArticle.userId
            // createTime: this.currentArticle.createTime,
            // updateTime: this.currentArticle.createTime ? this.currentArticle.createTime : dayjs().format('YYYY-MM-DD HH:mm:ss')
          }
        })
        .then(res => {
          loading.close()
          e.target.innerText === '获取标题' ? this.switch_ifShowingTitle(true) : this.switch_ifShowingTitle(false)
          this.$store.commit('UPDATE_SHOWLIST', res.data)
          this.switch_ifListALL(true)
        })

        .catch(error => {
          loading.close()
          e.target.innerText === '获取标题' ? this.switch_ifShowingTitle(true) : this.switch_ifShowingTitle(false)
          // 打印错误信息
          this.switch_ifListALL(true)
          if (error.response) {
            console.log('res-res-d:', error.response.data)
            console.log('res-res-s:', error.response.status)
            console.log('res-res-h:', error.response.headers)
          } else if (error.request) {
            console.log('res-req:', error.request)
          } else {
            console.log('Error', error.message)
          }
        })
    },
    // 返回到上一页
    goBack() {
      this.$router.back()
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
/* .el-header {
  --el-header-padding: 0px;
} */
.left-bar {
  /* text-align: center; */
  cursor: pointer;
}
.save:hover {
  color: #fe0627;
  font-weight: 600;
}
</style>
