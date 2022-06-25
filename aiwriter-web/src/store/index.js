import { createStore } from 'vuex'
import http from '@/utils/http'
import dayjs from 'dayjs'

export default createStore({
  actions: {
    //  新建文章
    addArticle(context, val) {
      http.post('/article/add', {
        articleId: 4,
        articleBody: '<p>...请输入文章内容</p>',
        userId: null,
        createTime: dayjs().format('YYYY-MM-DD HH:mm:ss'),
        updateTime: dayjs().format('YYYY-MM-DD HH:mm:ss')
      })
        .then(res => context.commit('CURRENT_ARTICLE_SETTER',
          {
            ...{
              articleId: res.data,
              titles: [],
              summarys: [],
              articleBody: '<p>...请输入文章内容</p>',
              wordNum: '',
              userId: null,
              createTime: dayjs().format('YYYY-MM-DD HH:mm:ss'),
              updateTime: dayjs().format('YYYY-MM-DD HH:mm:ss')
            }, val
          }
        ))
    },
    // 删除文章
    delArticle(context, id) {
      http.delete(`/article/${id}`).then(res => {
        if (res.data === 1) {
          context.commit('DEL_ARTICLE', id)
        }
      })
    },
  },
  mutations: {
    DEL_ARTICLE(state, value) {
      state.articleList = state.articleList.filter(item => item.articleId != value)
    },
    CURRENT_ARTICLE_SETTER(state, value) {
      // 根据value的数据替换currentArticle 的数据
      state.currentArticle = { ...state.currentArticle, ...value }
    },
    UPDATE_SHOWLIST(state, data) {
      state.showList = data
    },
    // 隐藏某一列表项
    HIDE_LISTITEM(state, index) {
      state.showList[index].delFlag = false
    },
    SWITCH_IS_SHOWING_TITLE(state, val) {
      state.isShowingTitle = val
    },
    //  整体控制list 项的显示与隐藏, 以便加载动画
    SWITCH_IS_LIST_All(state, val) {
      state.isListAll = val
    },
    RESER_DATA(state) {
      //  组件销毁清空数据
      state.currentArticle = {
        articleId: '',
        titles: [],
        summarys: [],
        articleBody: '',
        wordNum: '',
        userId: null,
        createTime: '',
        updateTime: ''
      },
        state.isShowingTitle = true,
        state.currentTitleIndex = '',
        this.showList = [],
        // 列表展示项的隐藏与显示
        this.isListAll = true
    }
  },
  state: {
    // 文章列表
    articleList: [],
    // 当前文章所有状态, 实时更新
    currentArticle: {
      articleId: '',
      titles: [],
      summarys: [],
      articleBody: '',
      wordNum: '',
      userId: null,
      createTime: '',
      updateTime: ''
    },
    // 当前展示的是否是标题
    isShowingTitle: true,
    // 标题中当前选中的列表的index
    currentTitleIndex: '',
    // 展示的数据列表
    showList: [],
    // 列表展示项的隐藏与显示
    isListAll: true
  },
  getters: {
    // 第一次选择文章后编辑前的文章状态
    firVersion(state) {
      let data = state.articleList.filter(item =>
        item.articleId === state.currentArticle.articleId)
      return (data.length > 0 && state.currentTitleIndex) ? data : { titles: [{ title: "未选择标题" }] }
    },
    // 当前标题
    currentTitle(state) {
      return function (article) {
        let data = article.titles.filter((item, index) => {
          if (item.status === '1') {
            state.currentTitleIndex = index
            return item
          }
          else {
            state.currentTitleIndex = null
          }
        })
        return data.length !== 0 ? data[0].title : '未选择标题'
      }
    }
  },
  modules: {
  }
})
