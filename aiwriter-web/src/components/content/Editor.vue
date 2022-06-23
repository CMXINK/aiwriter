<template>
  <div style="border: 1px solid #ccc; height: 100%">
    <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef" :defaultConfig="toolbarConfig" :mode="mode" />
    <Editor style="height: 500px; overflow-y: hidden" v-model="editorContent" :defaultConfig="editorConfig" :mode="mode" @onCreated="handleCreated" />
  </div>
</template>

<script>
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import { useStore } from 'vuex'
import { onBeforeUnmount, ref, shallowRef, onMounted, computed, getCurrentInstance } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
export default {
  components: { Editor, Toolbar },
  setup() {
    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef()

    // 获取vuex store对象
    const store = useStore()
    const editorContent = computed({
      get: () => {
        return store.state.currentArticle.articleBody
      },
      set: newValue => {
        store.commit('CURRENT_ARTICLE_SETTER', { articleBody: newValue })
      }
    })

    // 模拟 ajax 异步获取内容
    onMounted(() => {
      // setTimeout(() => {
      //   editorContent.value = '<p>模拟 Ajax 异步设置内容</p>'
      // }, 1500)
    })
    const toolbarConfig = {}
    const editorConfig = { placeholder: '请输入内容...' }

    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
      const editor = editorRef.value
      if (editor == null) return
      editor.destroy()
    })

    const handleCreated = editor => {
      editorRef.value = editor // 记录 editor 实例，重要！
    }
    return {
      editorRef,
      editorContent,
      mode: 'default', // 或 'simple'
      toolbarConfig,
      editorConfig,
      handleCreated
    }
  }
}
</script>
