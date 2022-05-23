<template>
  <el-row class="desk" :gutter="20">
    <el-col :span="7" style="margin-top:20px;margin-left: 10px">
      <el-card shadow="hover">
        <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
        >
          <router-link to="/home">
            <img src="../../assets/img/upload.png" alt="">
            <div style="height: 35px; font-size: 16px;">
              <a style="color: rgb(36, 112, 255); font-weight: 600;">新建文档</a>
            </div>
          </router-link>
          <div style="height: 64px; font-size: 14px; border-top: 1px solid rgb(237, 237, 237);">
            <a data-tag-id="uploadDoc" style="color: rgb(136, 136, 136); line-height: 65px;">上传文档</a>
          </div>
        </el-upload>
      </el-card>
    </el-col>
    <el-col :span="4" style="margin-top:20px;">
      <el-card shadow="hover">
        <div class="successShow" style="">
          <div title="范文——赵州桥" class="textDescriptionTitle">
            范文——赵州桥
          </div>
          <div class="textDescription">
            <img src="../../assets/img/number.png" alt=""
                 style="vertical-align: middle;height:16px;width:16px; margin-right: 8px;"/>
            <span>字数：393</span>
          </div>
          <div class="textDescription">
            <img src="../../assets/img/time.png" alt=""
                 style="vertical-align: middle; height:16px;width:16px;margin-right: 5px;">
            <span>日期：<span class="uploadTime">2019-11-12</span>
          </span>
          </div>
          <!--           下面的打印，下载，删除等等图标-->
          <div class="bottomButtons">
<!--            下载-->
            <div class="download">
              <img src="../../assets/img/download.svg" alt="">
            </div>
<!--            删除-->
            <div class="delect">
              <img src="../../assets/img/delect.svg" alt="">
            </div>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Desk"
}
</script>

<style>
  .el-upload-dragger{
    padding: 0;
    border: 0;
  }
  .el-card__body{
    padding: 0;
  }
  .textDescriptionTitle {
    font-size: 16px;
    height: 40px;
    color: #111;
    font-weight: 700;
    padding-top: 18px;
    margin-bottom: 40px;
    padding-left: 20px;
}
  .textDescription{
    text-align: left;
    margin-left: 18px;
    margin-right: 18px;
    font-size: 14px;
    line-height: 30px;
    color: #888;
  }
  .bottomButtons{
    margin-top: 62px;
    margin-bottom: 20px;
  }
  .download img{
    height: 30px;
    width: 30px;
  }
  .delect img{
    height: 30px;
    width: 30px;
  }
  .download{
    display:inline;
    margin-left: 20%;
    margin-right: 30%;
  }
  .delect{
    display:inline;
  }
</style>
