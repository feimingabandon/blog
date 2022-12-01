<template>

  <div style="width: 100%;height:100%;
  top:0;left:0;right:0;border:0;
  background: url('https://imgapi.xl0408.top/index.php');
  position: absolute; /*fixed：绝对定位，absolute：固定在那，滑动时位置改变*/
  background-size: 100% 100%;">
    <!--导航栏-->
    <div style="margin-left: 15%; margin-top: 25px;">
      <vs-button danger border @click="zx" style="display: inline-block;">注销登陆</vs-button>
      <vs-button success border @click="addBlog" style="display: inline-block;">添加文章</vs-button>
      <vs-button border @click="cx" style="display: inline-block;">审核通过文章</vs-button>
      <vs-button border @click="sh" style="display: inline-block;">未审核文章</vs-button>
      <vs-button warn border @click="no" style="display: inline-block;">审核不通过文章</vs-button>
      <vs-button  border @click="rzjk"  style="display: inline-block;">日志监控</vs-button>
      <vs-button  border @click="apiwd"  style="display: inline-block;">API文档</vs-button>
    </div>
    <!--显示查询结果-->
    <div style="width: 80%;margin-left: 15%;margin-top: 25px;
    border-width:1px;border-color: #2575e5;border-style: solid;
    min-height: 82vh;">
      <ul>
        <li v-for="i in this.blog1">
          <span >{{i.title}}
            <span v-if='i.userId===$user.user.data.id' style="font-size: 1vw;color: #fff;background: #6fa3ef;border-radius: 3px;padding: 1px 3px;">我的</span>
          </span>
          <vs-button danger @click="sc(i.id,i.userId)" style="display: inline-block;position:fixed;right: 5vw;">删除</vs-button>
          <vs-button warn @click="xg(i.id)"  style="display: inline-block;position:fixed;right: 9.5vw;">审核文章</vs-button>
          <vs-button border @click="xx(i.id)"  style="display: inline-block;position:fixed;right: 16.5vw;">详细内容</vs-button>
          <br/>
          <hr/>
        </li>
      </ul>

      <!--上一页，下一页按钮-->
      <div style="color: #d80b0b;font-size: 15px;
      bottom: 5vh; position:fixed;right: 6vw" >
        <button @click="previous" style="background:transparent;	border-width:1px;border-style:solid;border-color: #d80b0b;color: #d80b0b;font-size: 15px;border-radius:3px;">上一页</button>
        &nbsp;{{paging}}&nbsp;
        <button @click="next" style="background:transparent;	border-width:1px;border-style:solid;border-color: #d80b0b;color: #d80b0b;font-size: 15px;border-radius:3px;">下一页</button>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "backstage",
  data(){
    return{
      paging:1, // 分页参数
      url:"", // 查询全部的url
      blog1: [
        {
          id: 0,
          title: "",
          audit:0,
          userId: 0,
        },

      ]
    }
  },
  mounted() {
    alert("跳转前提醒：\n 1.普通用户可以添加文章(但最多有三条未审核文章)，可以删除自己的文章。\n 2.管理员才能无限制。\n 3.未登陆时默认为游客，游客无修改，删除，添加的权限。 \n 4.查询随便查")
  },
  methods:{
    rzjk(){
      // 日志监控

      var str= this.$URL.URL+"swagger-ui.html"
      window.open(str)
    },
    apiwd(){
      // API接口
      alert("用户名：root,密码：123456")
      var str= this.$URL.URL+"druid/sql.html"
      window.open(str)
    },
    sc(id,userId){
      // 删除 deleteIdBlog ,root:2是游客，1是用户
      // 判断删除不为空
      if (id!==0)
      // 先判断是不是游客
      if (this.$user.user.data.root!==2){
        // 再判断想删除的文章是不是自己的或者是不是管理员
        if (this.$user.user.data.id===userId || this.$user.user.data.id===1){
          this.$axios.get(this.$URL.URL+'blog/deleteIdBlog/'+id+'/'+this.$user.user.data.root+'/'+userId)
            .then(rep=>{

              alert(rep.data.message)
              // 如果成功，就刷新页面
              if (rep.data.state===1){
                // 钩子函数 、一次只返回10条数据，需要下一页再查
                this.$axios.get(this.url+'/'+this.paging)
                  .then(rep=>{
                    this.blog1 = rep.data.data

                  });
              }
            })
        }else {alert("越界了，越界了！不是自己的瞎搞啥！")}
      }else {alert("游客大哥，只可远观，不可亵玩啊")}

    },
    xx(id){
      // 详细
      this.$router.push({name:'blogContent',query: {id:id}})
}
    ,
    xg(id){
      // 修改
      if (id!==0)
      if (this.$user.user.data.root===0){
        // 有权限才能修改
        var name=prompt("请输入数字结果(1:不通过，0:通过)","0");
        this.$axios.get(this.$URL.URL+'blog/deleteIdBlog/'+id+'/'+name)
          .then(rep=>{
            alert(rep.data.message)
            // 如果成功，就刷新页面
            if (rep.data.state===1){
              // 钩子函数 、一次只返回10条数据，需要下一页再查
              this.$axios.get(this.url+'/'+this.paging)
                .then(rep=>{
                  this.blog1 = rep.data.data

                });
            }
          });
      }else {
        alert("有没有一种可能，你没有权限删除它！")
      }
    }
    ,
    zx(){
      localStorage.clear();
      if (localStorage.length===0){
        alert("注销成功辣！")
        this.$router.go(0)
      }
    },
    addBlog(){
      // 跳转到添加页
      this.$router.push('/markdownSet');
    },
    cx(){
      // 查询全部文章
      // 查询未审核文章queryNoPassBlog
      this.paging=1;
      // this.$URL.URL+'blog/queryBlog/'
      this.url=this.$URL.URL+'blog/queryBlog'
      // 钩子函数 、一次只返回10条数据，需要下一页再查
      this.$axios.get(this.$URL.URL+'blog/queryBlog/1')
        .then(rep=>{
          this.blog1 = rep.data.data
        });
    },
    sh(){
      // 查询未审核文章queryNoPassBlog
      this.paging=1;
      this.url=this.$URL.URL+'blog/queryNoPassBlog/2'
      // 钩子函数 、一次只返回10条数据，需要下一页再查
      this.$axios.get(this.$URL.URL+'blog/queryNoPassBlog/2/1')
        .then(rep=>{
          this.blog1 = rep.data.data
        });

    },
    no(){
      // 查询审核不通过文章
      this.paging=1;
      this.url=this.$URL.URL+'blog/queryNoPassBlog/1'
      // 钩子函数 、一次只返回10条数据，需要下一页再查
      this.$axios.get(this.$URL.URL+'blog/queryNoPassBlog/1/1')
        .then(rep=>{
          this.blog1 = rep.data.data
        });
    },
    previous(){
      // 上一页
      if (this.paging<=1){
        alert("大哥，后退不了了吧")
      }else {
        this.paging=this.paging-1

        // 钩子函数 、一次只返回10条数据，需要下一页再查
        this.$axios.get(this.url+'/'+this.paging)
          .then(rep=>{
            this.blog1 = rep.data.data

          });
      }
    },
    next(){
      // 下一页
      // 钩子函数 、一次只返回10条数据，需要下一页再查

      this.$axios.get(this.url+'/'+(this.paging+1))
        .then(rep=>{
          if (rep.data.data.length!==0){
            this.paging=this.paging+1
            this.blog1 = rep.data.data

          }else {
            alert("真的没有的，求放过！")
          }
        });
    },

  }
}
</script>

<style scoped>

</style>
