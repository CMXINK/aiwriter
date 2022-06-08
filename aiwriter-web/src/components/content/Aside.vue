<template>
    <div class="aside">
      <div class="icon">
        <img src="../../assets/img/logo.svg" alt="">
      </div>
      <div class="desk" @click="deskClick">
        <img v-if ="isActive" src="../../assets/img/desk.png" alt="">
        <img v-else src="../../assets/img/desk_active.png" alt="">
        <div>写作台</div>
      </div>
      <div class="logon" @click="logonClick">
        <img src="../../assets/img/user.png" alt="">
        <div>登录</div>
      </div>
    </div>
</template>

<script>

export default {
  name: "Aside",
  props:{
    path:String
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    deskClick(){
      this.$router.replace('/desk')
    },
    logonClick(){
      this.$router.replace('/logon');
    }
  },
  data(){
    return{
      // isActive:false
    }
  },
  computed:{
    isActive(){
      return this.$router.currentRoute._rawValue.path.indexOf(this.path) !== -1
    }
  }
}
</script>

<style scoped>
  .aside {
    width: 80px;
    background-color: rgb(255, 255, 255);
    /*box-shadow: rgba(0, 0, 0, 0.08) 4px 0px 10px 0px;*/
    /*border-style: solid;*/
  }
  .icon {
    text-align: center;
    /*width: 50px;*/
    height: 80px;
    margin-top: 18px;
    margin-bottom: 50px;
    /*border-style: solid;*/
  }
  .icon img{
    /*width: 50px;*/
    height: 60px;
  }
  .desk{
    /*width: 50px;*/
    height: 50px;
    text-align: center;
    margin-bottom: 50px;
    /*border-style: solid;*/
  }
  .logon{
    /*width: 50px;*/
    height: 50px;
    text-align: center;
    /*border-style: solid;*/
  }
</style>
