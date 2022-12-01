<template >
  <!--style="overflow-y:scroll;overflow-x:hidden;height:100%;width:100%;"-->

<div  class="building2">
<!--  <Top></Top>&lt;!&ndash;侧边栏 登陆&ndash;&gt;-->
  <!--动漫图片-诗词-->
  <div class="poetry_text2">
    <div class="poetry_text_span2">
      <p>
        <Poetry></Poetry><!--诗词-->
      </p>
      <p style="position: absolute;bottom: -20px;margin-bottom:0;width:100%;font-size: 30px;font-family: 华文行楷,cursive">o(>﹏<)o不要向下滑动</p>
    </div>
  </div>
  <!--往下滑的页面-->
  <div style="top: 0;left: 0;width: 100%;overflow:hidden;min-height: 80%" class="bj_div2">
    <!--中间-->
    <div class="zj_div2" style="width: 65%;margin:auto; padding: 0 20px 30px 20px; box-shadow:  0 20px 10px rgb(220 220 220);">
<!--        <div  v-for="i in 15">-->
<!--          <h1 style="margin-top: 0;text-align: center">男儿何不带吴钩、收取关山十五州。{{i}}</h1>-->
<!--        </div>-->
      <div v-if="this.data1.state!==0"><!--判断有没有数据-->
      <div v-for="i in this.data1.data">
      <!--一篇博客-->
      <div style="margin: 0 0 52px 0;padding-top: 49px;">
        <!--存储标题和置顶-->
        <div>
          <!--标题，用 a 标签-->
          <router-link :to="{name:'blogContent',query:{id:i.id}}" style="text-decoration:none;font-size: 2.5vw;color: #212121;transition: color .3s ease;
          font-family: Merriweather,'Open Sans','Microsoft Jhenghei','Microsoft Yahei',sans-serif;
          font-weight:bold;
          ">{{i.title}}</router-link>
          <!--置顶:里面没有值除非传入,值为 0 时代表需要置顶-->
<!--          <span v-if='i.top===0' style="font-size: 1vw;color: #fff;background: #6fa3ef;border-radius: 3px;padding: 1px 3px;">置顶</span>-->
        </div>
        <!--存储时间，阅读，推荐（推荐值即点赞数）-->
        <span style="font-size: 0.9vw;color: #888;">&nbsp;&nbsp;
          <!--发表-->
          <i></i>◕ &nbsp;发表于 {{i.releaseTime}}&nbsp;&nbsp;&nbsp;
          <!--阅读-->
          <i></i>♒ &nbsp;阅读：{{i.browse}}&nbsp;&nbsp;&nbsp;
          <!--推荐-->
          <i></i>❤ &nbsp;推荐：{{i.like}}
        </span>
        <!--存储摘要和阅读全文的文字-->
        <div style="font-size:1.1vw;padding-top: 5px;color: #212121">
          <!--摘要-->
            摘要：
            {{i.summary}}
          <!--阅读全文 先跳转到详情页并附带id信息，然后详情页去获取文章-->
          <router-link :to="{name:'blogContent',query:{id:i.id}}"  style="display:block;padding-top: 25px;font-weight: bolder;opacity: 0.9;color: #888888;">阅读全文 »</router-link>
        </div>
      </div>
      <!--分割线-->
        <hr>
      </div>
      </div>
      <!--如果没有数据-->
      <div v-else style="
          padding-top: 10%;
          ">
        <div style="color: #212121;transition: color .3s ease;
          font:50px 华文行楷,cursive;
          text-align: center;
          font-weight:bold;">
          妈耶，知识怎么不见了!
        </div>
        <div style="margin-top: 225px;">
          <p style="bottom: 0;">∑(っ°Д°;)っ卧槽，不见了</p>
        </div>

      </div>

      <!--上一页，下一页按钮-->
      <div style="margin-left: 80%;color: #93999f;font-size: 15px;" >
        <button @click="previous" style="background:transparent;	border-width:1px;border-style:solid;border-color: #93999f;color: #93999f;font-size: 15px;border-radius:3px;">上一页</button>
        &nbsp;{{paging}}&nbsp;
        <button @click="next" style="background:transparent;	border-width:1px;border-style:solid;border-color: #93999f;color: #93999f;font-size: 15px;border-radius:3px;">下一页</button>
      </div>
    </div>

    <!--页脚-->
    <Tail></Tail>

  </div>


</div>
</template>


<script>
import Poetry from "../components/Poetry";
import Top from "./Top";
import Tail from "./Tail";

export default {
  name: "Main",
  components:{
    Poetry // 注册组件以使用
    ,Top
    ,Tail

  },
  data(){
      return{
        data1:{
          message:"",
          state:0,
          data:[
            {
              id:0,
              title: "",
              summary:"",
              content:"",
              userId: 0,
              audit:0,
              like:0,
              releaseTime:"", // 获取当前时间
              mtime:"",
              browse: 0,
              top: 1,
              tag:"",
            }
          ]
        },
        paging:1 // 分页参数
        ,heightFlag : false,//全局变量
        input_flag:false, // 查看当前是否是指定查询模式
      }
  },
  mounted() {

    // 判断是不是指定查询，如果是就执行指定查询的内容（判断是否存在localStorage）
    if (localStorage.length>0){
      var input = localStorage.getItem("input");
      if (input!==null&&input!==""){
        // 查询指定标题的数据
        // 钩子函数 、一次只返回10条数据，需要下一页再查
        this.$axios.get(this.$URL.URL+'blog/selectKeyBlog/'+input)
          .then(rep=>{
            console.log(rep.data)
            if (rep.data.state===0) {
              alert(rep.data.message)
              // 刷新当前页
              this.$router.go(0)
            }
            else {
              this.data1 = rep.data
              // 模式切换
              this.input_flag=true
              console.log(this.data1.message)
            }

          });
        // 删除localStorage
        //删除某个
        localStorage.removeItem('input');

      }else {
        // 钩子函数 、一次只返回10条数据，需要下一页再查
        this.$axios.get(this.$URL.URL+'blog/queryBlog/'+this.paging)
          .then(rep=>{
            this.data1 = rep.data
            console.log(this.data1.message)
          });
      }
    }else {
      // 钩子函数 、一次只返回10条数据，需要下一页再查
      this.$axios.get(this.$URL.URL+'blog/queryBlog/'+this.paging)
        .then(rep=>{
          this.data1 = rep.data
          console.log(this.data1.message)
        });
    }
  },

  methods:{
    previous(){
      // 判断模式,不是从首页的查询才能下一页，是首页搜索的就会显示全部
      if (!this.input_flag)
      // 上一页
      if (this.paging<=1){
        alert("大哥，后退不了了吧")
      }else {
        this.paging=this.paging-1
        // 钩子函数 、一次只返回10条数据，需要下一页再查
        this.$axios.get(this.$URL.URL+'blog/queryBlog/'+this.paging)
          .then(rep=>{
            this.data1 = rep.data
            this.clickQuotation()
            console.log(this.data1.message)

          });
      }
    },
    next(){
      // 下一页
      // 判断模式,不是从首页的查询才能下一页，是首页搜索的就会显示全部
      if (!this.input_flag)
      // 钩子函数 、一次只返回10条数据，需要下一页再查
      this.$axios.get(this.$URL.URL+'blog/queryBlog/'+(this.paging+1))
        .then(rep=>{
          if (rep.data.data.length!==0){
            this.paging=this.paging+1
            this.data1 = rep.data
            this.clickQuotation()
            console.log(this.data1.message)
            console.log(this.data1.data)
          }else {
           alert("真的没有的，求放过！")
          }
        });
    },

      clickQuotation() {
        if (this.heightFlag) return
        const orangeHeight = window.pageYOffset //点击事件后，页面移动至页面左上角的距离，现在为0（顶部）；const orangeHeight = window.pageYOffset-100（指距顶部下移100）
        //
        let i = 0
        this.heightFlag = true
        this.interval = setInterval(() => {
          const next = Math.floor(this.heightChange(10 * i, orangeHeight, -orangeHeight, 500))
          if (next <= 0) {
            window.scrollTo(0, 0 )//第二个参数表示滚动距离,window.screen.height获取屏幕高度
            clearInterval(this.interval)
            this.heightFlag = false
          } else {
            window.scrollTo(0, next)
          }
          i++
        }, 16.7)
      },
      heightChange(t, b, c, d) {
        if ((t /= d / 2) < 1) return c / 2 * t * t + b
        return -c / 2 * (--t * (t - 2) - 1) + b
      }

  }

}
</script>

<style scoped>

.bj_div2{
  /*position: relative;*/

  background: linear-gradient(90deg,rgba(247,149,51,.1),rgba(243,112,85,.1) 15%,rgba(239,78,123,.1) 30%,rgba(161,102,171,.1) 44%,rgba(80,115,184,.1) 58%,rgba(16,152,173,.1) 72%,rgba(7,179,155,.1) 86%,rgba(109,186,130,.1));
}
.zj_div2{
  /*background-color: #333;*/
  opacity: 0.8;
  background: linear-gradient(aliceblue,aliceblue);

}

.building2 {
  background: url("https://imgapi.xl0408.top/index.php") ;  /* API接口大全：https://imgapi.xl0408.top/index.php http://prlrr.com/wallpaper-API/  https://img.r10086.com/  https://api.r10086.com/img-api.php?type=动漫综合1 "https://api.ixiaowai.cn/api/api.php" "../assets/test01.jpg"*/
  height: 100%;
  width: 100%;
  position: absolute; /*fixed：绝对定位，absolute：固定在那，滑动时位置改变*/
  background-size: 100% 100%;

  top: 0;  /* 这里是设置与顶部的距离*/
  left: 0;  /* 这里是设置与左边的距离*/
}

.poetry_text2{
  height: 100%;
  width: 100%;
  color: aliceblue;
  text-align: center;
  display: table;/*父元素设置表格属性*/

}
.poetry_text_span2{
  display: table-cell;/*img设置成表格元素属性*/
  vertical-align: middle;/*两个display设置后这个属性就起作用*/
  font-size: 50px ;
  opacity:0.8;

}


</style>
