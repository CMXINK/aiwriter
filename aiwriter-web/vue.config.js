
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // 配置代理服务器, 用于解决跨域问题

  //  这种方式仅能配置一台代理服务器, 且当可以在public找到文件时优先拿去public下的文件作为请求内容返回
  devServer: {
    proxy: 'http://114.55.56.15:8886'
  },
})
