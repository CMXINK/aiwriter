<template>
  <div>
    <!-- 展示当前选择内容 -->
    <el-collapse accordion>
      <el-collapse-item class="currentItem">
        <template #title>
          <div class="title">{{ currentTitle(currentArticle) }}</div>
          <div style="position: absolute; right: 50px; color: #ff7a7a">使用中</div>
        </template>
        <div class="itemContent" v-for="(item, index) in currentArticle.summerys" :key="index">{{ item }}</div>

        <!-- 触摸事的标签的提示 -->
        <div class="icons">
          <el-popover placement="top-start" trigger="hover" popper-class="my-popover" :append-to-body="false">
            <template #reference>
              <el-icon :size="25" color="gray" @click="dialogEditVisible = true"><EditPen /></el-icon>
            </template>
            <template #default>
              <div class="content">编辑内容</div>
            </template>
          </el-popover>
          <el-popover placement="top-start" trigger="hover" popper-class="my-popover" :append-to-body="false">
            <template #reference>
              <el-icon :size="25" color="gray" @click="resetTitle"><Refresh /></el-icon>
            </template>
            <template #default>
              <div class="content">初始版本</div>
            </template>
          </el-popover>
        </div>
      </el-collapse-item>
      <!-- 遮盖层内容dialog -->
      <el-dialog v-model="dialogEditVisible" draggable title="编辑内容" center>
        <el-input v-model="tempCurrentTitle" autosize type="textarea" placeholder="Please input" resize="none" />
        <div class="Editdata" v-for="(item, index) in tempCurrentItem.summerys" :key="index" style="margin: 30px 0">
          <el-input v-model="tempCurrentItem.summerys[index]" :autosize="{ minRows: 3, maxRows: 20 }" type="textarea" placeholder="Please input" resize="none" />
        </div>

        <template #footer>
          <el-row class="dialog-footer">
            <el-button @click="dialogEditVisible = false">取消</el-button>
            <el-button type="primary" @click="updateCurrentItem">确认</el-button>
          </el-row>
        </template>
      </el-dialog>
    </el-collapse>
    <!--  -->
    <el-collapse accordion>
      <el-collapse-item :name="index" v-for="(item, index) in titleList" :key="item.titleId" v-show="item.delFlag" :class="item.class" class="item">
        <template #title>
          <div class="title">{{ item.title }}</div>
          <div class="icons item-icon">
            <el-popover placement="top-start" trigger="hover" popper-class="my-popover" :append-to-body="false">
              <template #reference>
                <el-icon :size="25" color="gray" @click="setCurrentItem(item)"><DocumentChecked /></el-icon>
              </template>
              <template #default>
                <div class="content">应用内容</div>
              </template>
            </el-popover>

            <el-popover placement="top-start" trigger="hover" popper-class="my-popover" :append-to-body="false">
              <template #reference>
                <el-icon :size="25" color="gray" @click="removeItem(item, index)"><DocumentDelete /></el-icon>
              </template>
              <template #default>
                <div class="content">忽略内容</div>
              </template>
            </el-popover>
          </div>
        </template>
        <!-- 当品题目和简介一起时的简介内容 -->
        <!-- <div class="itemContent" v-for="(item_data, index) in item.data" :key="index">{{ item_data }}</div> -->

        <!-- 当题目和简介在一起时的触摸事件的标签的提示 -->
        <!-- <div class="icons">
          <el-popover placement="top-start" trigger="hover" popper-class="my-popover" :append-to-body="false">
            <template #reference>
              <el-icon :size="25" color="gray" @click="setCurrentItem(item)"><DocumentChecked /></el-icon>
            </template>
            <template #default>
              <div class="content">应用内容</div>
            </template>
          </el-popover>

          <el-popover placement="top-start" trigger="hover" popper-class="my-popover" :append-to-body="false">
            <template #reference>
              <el-icon :size="25" color="gray" @click="removeItem(item)"><DocumentDelete /></el-icon>
            </template>
            <template #default>
              <div class="content">忽略内容</div>
            </template>
          </el-popover>
        </div> -->
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
import { mapGetters, mapMutations, mapState } from 'vuex'
export default {
  name: 'Query',
  data() {
    return {
      //  currentItem和Items里的data需要一个段落标识来分段或data本身就是数组, 每一段为一个数组
      titleList: [],
      tempCurrentItem: {},
      tempCurrentTitle: '',
      dialogEditVisible: false
    }
  },
  computed: {
    ...mapState(['currentArticle', 'currentTitleIndex', 'articleList']),
    ...mapGetters(['currentTitle', 'firVersion'])
  },
  watch: {
    currentArticle: {
      deep: true, // 开启深度监视
      immediate: true, // 在一开始时调用一下handler
      handler() {
        console.log('这是articleList', this.articleList, 'ddd')
        this.tempCurrentItem = JSON.parse(JSON.stringify(this.currentArticle))
        if (this.currentTitleIndex) {
          this.tempCurrentTitle = this.tempCurrentItem.titles[this.currentTitleIndex].title
        } else {
          this.tempCurrentTitle = ''
        }
      }
    }
  },
  methods: {
    ...mapMutations({ editCurentArticle: 'CURRENT_ARTICLE_SETTER' }),
    updateCurrentItem() {
      this.dialogEditVisible = false
      if (this.currentTitleIndex) {
        this.tempCurrentItem.titles[this.currentTitleIndex].title = this.tempCurrentTitle
      } else {
        this.tempCurrentItem.titles = this.tempCurrentTitle
      }
      this.editCurentArticle(this.tempCurrentItem)
    },
    removeItem(item, index) {
      item.class = 'animate__animated animate__bounce animate__backOutUp'
      setTimeout(() => {
        this.titleList[index].delFlag = false
      }, 650)
    },

    resetTitle() {
      if (this.currentTitleIndex) {
        this.$store.state.currentArticle.titles[this.currentTitleIndex].title = window.localStorage.getItem('firVersion_title')
      } else {
        this.$store.state.currentArticle.titles = [{ title: '未选则标题' }]
      }
    },
    collapseChange(val) {
      //  当前激活的item
      console.log('currentCollapseItem==>:', val)
    },
    setCurrentItem(item) {
      if (this.currentTitleIndex) {
        this.$store.state.currentArticle.titles[this.currentTitleIndex].title = item.title
      } else {
        item.status = '1'
        this.currentItem.titles = [item]
      }
    }
  },
  beforeCreate() {
    this.$store.state.currentTitleIndex = null
  },
  beforeMount() {},
  mounted() {
    this.titleList = this.currentTitleIndex ? JSON.parse(JSON.stringify(this.currentArticle)).titles : []
    if (this.firVersion.length > 0 && this.currentTitleIndex) {
      window.localStorage.setItem('firVersion_title', this.currentTitle(this.currentArticle))
    } else {
      window.localStorage.setItem('firVersion_title', '未选则标题')
    }
  }
}
</script>
<style>
.el-collapse {
  border: none;
  --el-collapse-header-height: 54px;
  margin-bottom: 20px;
}
.el-collapse-item {
  border-radius: 4px;
  border-left: 4px solid #ff7a7a;
  margin-bottom: 20px;
  box-shadow: 4px 4px 4px 1px rgba(187, 187, 187, 0.2);
  padding-left: 10px;
}
.itemContent {
  padding-left: 7px;
  padding-right: 12px;
  text-indent: 16px;
}
.currentItem {
  border-left: 4px solid #2693ec;
  position: relative;
}
.el-collapse-item:hover .title {
  color: #2693ec;
  text-decoration: underline;
  text-underline-offset: 4px;
}
.el-collapse-item .title {
  font-weight: 700;
  font-size: 17px;
}
.el-collapse-item .icons {
  float: right;
}
.el-collapse-item .icons .el-icon {
  margin-right: 25px;
}
.el-collapse-item .icons .el-icon:hover {
  color: #0477d4;
}
.currentItem .is-active .title {
  color: #2693ec;
}
.el-popover.my-popover {
  box-shadow: none;
  position: relative;
  border: none;
  background-color: transparent;
}
.el-popover.my-popover .content {
  width: 80px;
  height: 22px;
  line-height: 22px;
  font-size: 12px !important;
  position: absolute;
  text-align: center;
  top: 0px;
  left: 50%;
  letter-spacing: 1.5px;
  color: #fafafa;
  border-radius: 3px;
  transform: translateX(-40%);
  background-color: #888;
  border: 1px solid #888;
  border-color: #888;
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
  z-index: 5 !important;
}
.el-popover.my-popover .el-popper__arrow::before {
  content: '';
  top: -3.5px;
  display: block;
  height: 15px;
  background-color: #999;
}
.el-popover.my-popover .el-popper__arrow {
  /* z-index: 1 !important; */
  /* z-index: 2 !important; */
  margin: 0;
  padding: 0;
}

.dialog-footer {
  position: relative;
  height: 30px;
}
.dialog-footer button:first-child {
  position: absolute;
  left: 35%;
}
.dialog-footer button:last-child {
  position: absolute;
  left: 55%;
}

.item .el-collapse-item__arrow {
  font-size: 0px !important;
}
.item {
  position: relative;
}
.item .item-icon {
  position: absolute;
  right: 0px;
}
.item .el-collapse-item__content {
  padding: 0px !important;
}
</style>
