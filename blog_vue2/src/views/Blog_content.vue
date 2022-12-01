<template>
  <div  class="building1">
<!--    <Top></Top>&lt;!&ndash;侧边栏 登陆&ndash;&gt;-->
    <!--动漫图片-诗词-->
    <div class="poetry_text1">
      <div class="poetry_text_span1">
        <p >
          <span style="display:block;font-size: 100%; font-family: 华文行楷,cursive">{{typewriter3}}</span><!--标题-->
          <span style="font-size: 20px;">◕ {{this.data1.data.releaseTime}}&nbsp; ♒ {{this.data1.data.browse}} &nbsp; ❤ {{this.data1.data.like}} &nbsp;Т {{this.data1.data.content.length}} </span>
        </p>
        <div style="font-size: 25px;position: absolute; bottom: 10px;right: 10px">
            <Poetry></Poetry><!--诗词-->
        </div>
      </div>
    </div>
    <!--往下滑的页面-->
    <div style="top: 0;left: 0;width: 100%;overflow:hidden;min-height: 80%" class="bj_div1">
      <!--中间-->
      <div class="zj_div1" style="width: 65%;margin:auto; padding: 0 20px 30px 20px; box-shadow:  0 20px 10px rgb(220 220 220);">
        <!--        <div  v-for="i in 15">-->
        <!--          <h1 style="margin-top: 0;text-align: center">男儿何不带吴钩、收取关山十五州。{{i}}</h1>-->
        <!--        </div>-->
        <div v-if=this.data1.state style=""><!--判断有没有数据-->
            <markdownLook :information=this.data1.data.content style=""></markdownLook>
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
        <div></div>
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
import markdownLook from "../components/markdownLook";

export default {
  name: "Blog_content",
  components: {
    Poetry // 注册组件以使用
    , Top
    ,Tail
    ,markdownLook
  },
  data(){
    return{
      typewriter3: '',
      i3: 0,
      timer3: 0,
      str3: '标题',
      data1:{
        message:"",
        state:0,
        data:{
            id:0,
            title: "",
            summary:"",
            content:"",
            userId: 0,
            audit:0,
            like:0,
            releaseTime:"",
            mtime:"",
            browse: 0,
            top: 1,
            tag:"",
        }
      }
    }
  },
  mounted() {

    // 通过传递过来的 id和钩子函数 获取文章内容
    this.$axios.get(this.$URL.URL+`blog/selectIdBlog/${parseInt(this.$route.query.id)}`).then(
      rep=>{
        console.log(rep.data.state)
        if (rep.data.state===1){
          this.data1=rep.data // 将文章内容复制给准备好的模板
          this.str3=this.data1.data.title
          this.typeing3();
        }

      }
    );



  },
  methods:{
    typeing3 () {
      if (this.i3 <= this.str3.length) {
        this.typewriter3 = this.str3.slice(0, this.i3++) + '|'
        this.timer3 = setTimeout(() => {
          this.typeing3()
        }, 150)
      } else {
        clearTimeout(this.timer3)
      }
    },
  }
}
</script>

<style scoped>
.bj_div1{
  /*position: relative;*/

  background: linear-gradient(90deg,rgba(247,149,51,.1),rgba(243,112,85,.1) 15%,rgba(239,78,123,.1) 30%,rgba(161,102,171,.1) 44%,rgba(80,115,184,.1) 58%,rgba(16,152,173,.1) 72%,rgba(7,179,155,.1) 86%,rgba(109,186,130,.1));
}
.zj_div1{
  /*background-color: #333;*/
  opacity: 0.8;
  background: linear-gradient(aliceblue,aliceblue);

}

.building1 {
  background: url("https://imgapi.xl0408.top/index.php") ;  /* API接口大全：https://imgapi.xl0408.top/index.php http://prlrr.com/wallpaper-API/  https://img.r10086.com/  https://api.r10086.com/img-api.php?type=动漫综合1 "https://api.ixiaowai.cn/api/api.php" "../assets/test01.jpg"*/
  height: 45%;
  width: 100%;
  position: absolute; /*fixed：绝对定位，absolute：固定在那，滑动时位置改变*/
  background-size: 100% 100%;

  top: 0;  /* 这里是设置与顶部的距离*/
  left: 0;  /* 这里是设置与左边的距离*/
}

.poetry_text1{
  height: 100%;
  width: 100%;
  color: aliceblue;
  text-align: center;
  display: table;/*父元素设置表格属性*/

}
.poetry_text_span1{
  display: table-cell;/*img设置成表格元素属性*/
  vertical-align: middle;/*两个display设置后这个属性就起作用*/
  font-size: 50px;
  opacity:0.9;

}
</style>
