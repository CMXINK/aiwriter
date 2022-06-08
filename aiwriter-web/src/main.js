import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import store from './store'
import http from './utils/http'
import 'animate.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'



const app = createApp(App).use(store).use(router).use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.config.globalProperties.$http = http
app.mount('#app')

