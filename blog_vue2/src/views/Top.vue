<template>
  <div class="hidden" >

    <vs-button class="top1" @click="activeSidebar = !activeSidebar" color="#fff" style="background: rgba(0,0,0,0.1);  font-size:25px; "  transparent >
      ☯ 菜单
    </vs-button>
    <vs-button v-model="dl_text" class="top1"  v-on:click="landl"  color="#fff"  transparent style="font-size:25px;top:80px;background: rgba(0,0,0,0.1);">
      ☯&nbsp;{{dl_text}}
      <vs-avatar v-if="dl_text!=='登陆'" v-model="dl_text"  circle>
      <img src="/static/tx_user1.png" alt="">
      </vs-avatar>
    </vs-button>

    <!--导航栏-->



    <!--侧边栏-->
    <vs-sidebar
      fixed
      v-model="active"
      :open.sync="activeSidebar"
      style="opacity: 0.7;height: 80%;top: 20%;"
    >
      <template #logo>
        <!-- ...img logo -->
      </template>
      <vs-sidebar-item to="/" id="home">
        <template #icon>
          <i class='bx bx-home'></i>
        </template>
        ☯ 首页
      </vs-sidebar-item>
      <vs-sidebar-item to="/backstage" id="ht">
        <template #icon>
          <i class='bx bx-home'></i>
        </template>
        ☯ 后台管理
      </vs-sidebar-item>

      <input type="search" name="search" id="input"  placeholder="搜索文章" style="width: 70%;height:30px;margin-left: 20%;font-size: 15px;">
      <vs-button border style="opacity: 0.5;left: 45px;"  @click="ss" id="ss">
        <template  #icon>
          <i class='bx bx-home'></i>
        </template>
        ☯ 搜索一下
      </vs-button>

      <vs-sidebar-item >
        <template #icon>
          <i class='bx bx-home'></i>
        </template>
        ☯ 友链 ----
      </vs-sidebar-item>

      <vs-sidebar-item href="https://blog.csdn.net/qq_43483251" id="csdn">
        <template #icon>
          <i class='bx bx-home'></i>
        </template>
        ☯ CSDN
      </vs-sidebar-item>
      <vs-sidebar-item id="github">
        <template #icon>
          <i class='bx bx-home'></i>
        </template>
        ☯ GitHub
      </vs-sidebar-item>
      <vs-button transparent style="color: #888888;left: 33px;font-size: 0.9rem;font-weight:bold;" @click="lx1"  id="lx">
        <template #icon>
          <i class='bx bx-home'></i>
        </template>
        ☯ 联系我
      </vs-button>

    </vs-sidebar>
  </div>

</template>

<script>


import user from "../structure/user";

export default {
  name: "Top",
  data(){
    return{
      active: 'home',
      activeSidebar: false,
      landl2: false,
      dl_text:"登陆"
    }
  },
  components:{

  },

  mounted: function () {

    // 第一次访问时查看是否登陆过
    if (localStorage.length > 0) {
      var mydata = JSON.parse(localStorage.getItem('user'));
      console.log("查看是否登陆过")
      console.log(mydata)
      if (mydata !== null) {
        if (mydata.id!==0){
          console.log("登陆过,id,name为:"+mydata.id)
          // 说明登陆过、我们就将值显示
          this.$user.user.data=mydata
          this.dl_text=""
          console.log(this.$user.user.data.name)
        }
      }
    }


  },
  methods:{
    landl:function (){

      this.$router.push('/Land')
    },

    ss(){
      // 搜索
      var input =  document.getElementById('input').value;
      // 存储在 localStorage.setItem('zheng', str); 里
      if (input!==""&&input!==null){
        localStorage.setItem('input', input);
        // 然后刷新整个页面
        // 刷新当前页
        this.$router.push("/")
        this.$router.go(0)
      }
    },
    lx1(){
      alert("微信：15629871172")

    }
  },




}
</script>

<style scoped>

.top1{
  position:fixed;
  bottom:0;
  top: 20px;
  left: 10px;
  height: 50px;
  opacity:0.8;
}
</style>
