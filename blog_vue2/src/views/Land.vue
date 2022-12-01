<template>
  <div class="building3">

    <div style="margin-top: 20%;margin-left: 37%;">
      <!--文本输入框：input type=“text”-->
      <p>名字：<input id="username" type="text" name="username" value=""></p>

      <!--密码输入框 type="password"-->
      <p>密码：<input id="password" type="password" name="password" value=""></p>

      <p>
        <!-- 提交按钮-->
        <button @click="landuser">登陆</button>
        <!--注册按钮-->
        <button @click="zc">注册</button>
        <button @click="sy">首页</button>
      </p>
    </div>

  </div>

</template>

<script>
export default {
  name: "Land",
  data() {
    return {
      username: '',
      password: '',


      // 接收异步请求判断用户名是否重复
      flag:false,

    }

  },
  methods: {

    landuser() {
      // 登陆
      // 获取用户名和密码
      var username =  document.getElementById('username').value;
      var password =  document.getElementById('password').value;
      if (username!==""&&password!==""){

        // 使用get提交表单
        this.$axios.get(this.$URL.URL+'user/selectUserPwd/'+username.toString()+'/'+password.toString())
          .then(rep=>{
            this.$user.user.data = rep.data.data // 获取返回数据且将用户数据赋值给模板
            alert(rep.data.message) // 显示是否登陆成功

            if (rep.data.state===1){
              // 保存用户数据到本地化
              localStorage.setItem('user', JSON.stringify(this.$user.user.data))
              // 跳转到上一页
              this.$router.go(0) // 刷新页面
              history.go(-1)

            }
          })
      }else {
        alert("大哥，好歹填一点啊。")
      }

    },

    sy(){
      this.$router.push('/')
    },
    async zc(){

      // 注册
         var name=prompt("快输入用户名与密码，两者用英文 `,` 逗号分隔","root,1");
      if (name!=null){
        // 使用axios验证用户名是否被注册了
        var str = name.split(',');
        await this.yz(str[0]);

        if (this.flag===1) return alert("哎哟，英雄所见略同，用户名已经存在辣！");
        else {
          // 执行注册程序
          // 发送请求
          this.$axios.get(this.$URL.URL+'user/addUser/'+str[0]+'/'+str[1])
          .then(rep=>{
            alert(rep.data.message)
          })
        }
      }
    },
    async  yz(name){
      // 验证用户名是否被注册this.$URL.URL+
      await  this.$axios.get(this.$URL.URL+`user/selectUserName/${name.toString()}`)
      .then(rep=>{
        this.flag = rep.data // 如果存在则赋值为true，反之false
      })
    }
  }

}
</script>

<style  scoped>

.building3 {
  background: url("https://imgapi.xl0408.top/index.php") ;  /* API接口大全：https://imgapi.xl0408.top/index.php http://prlrr.com/wallpaper-API/  https://img.r10086.com/  https://api.r10086.com/img-api.php?type=动漫综合1 "https://api.ixiaowai.cn/api/api.php" "../assets/test01.jpg"*/
  height: 100%;
  width: 100%;
  position: absolute; /*fixed：绝对定位，absolute：固定在那，滑动时位置改变*/
  background-size: 100% 100%;

  top: 0;  /* 这里是设置与顶部的距离*/
  left: 0;  /* 这里是设置与左边的距离*/
}
</style>


