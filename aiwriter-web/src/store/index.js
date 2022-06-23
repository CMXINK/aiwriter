import { createStore } from 'vuex'
import http from '@/utils/http'

export default createStore({
  actions: {
    //  新建文章
    addArticle(context) {
      http.post('/article/add', {
        articleId: 4,
        articleBody: '这是三体正文',
        userId: null,
        createTime: new Date(),
        updateTime: new Date()
      })
        .then(res => context.commit('CURRENT_ARTICLE_SETTER',
          {
            articleId: res.data,
            titles: [],
            summarys: [],
            articleBody: '<p>...请输入文章内容</p>',
            wordNum: '',
            userId: null,
            createTime: new Date(),
            updateTime: new Date()
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
    }
  },
  state: {
    articleList: [
    ],
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
    currentTitleIndex: ''
  },
  getters: {
    firVersion(state) {
      let data = state.articleList.filter(item =>
        item.articleId === state.currentArticle.articleId)
      return (data.length > 0 && state.currentTitleIndex) ? data : { titles: [{ title: "未选择标题" }] }
    },
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
