<template>
  <!-- 若需加抖动动画请取消注释本行 -->
  <!-- <transition appear enter-active-class="animate__headShake" name="animate__animated animate__bounce"> -->
  <transition>
    <router-view />
  </transition>
</template>

<style>
/*@import "assets/css/base.css";*/
html,
body,
#app {
  height: 100%;
  margin: 0px;
  padding: 0px;
}
</style>

<script>
export default {
  name: 'App'
}
</script>
