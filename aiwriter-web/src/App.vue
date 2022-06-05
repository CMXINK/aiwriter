<template>
  <transition appear enter-active-class="animate__headShake" name="animate__animated animate__bounce">
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
