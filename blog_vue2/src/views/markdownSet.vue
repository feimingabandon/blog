<template>
  <div >
    <div style="width: 10%; top:20px;margin-left: 50%; right: 250px; position:fixed;z-index: 2;">
      <button @click="up" >上传</button>
      <button @click="fh" >返回</button>
    </div>
    <div id="fb" style="opacity:0;">
    <div  style="width: 40%; top:70px;margin-left: 50%;  position:fixed;z-index: 2; background-color: aliceblue; border: #212121;">
      <!--标题-->
      标题: <input v-model="title" placeholder="请输入内容">
      <br/>
      <!--摘要-->
      摘要: <textarea  v-model="summary" placeholder="请输入内容"></textarea>
      <br/>
      <button @click="qx" style="display: inline-block;">取消</button>
      <button @click="qrfb" style="display: inline-block;">确认发布</button>

    </div>
    </div>
    <mavon-editor
      v-model="value"
      :navigation=navigation
      :subfield=subfield
      :defaultOpen=defaultOpen
      :placeholder=placeholder
      :editable=editable
      :codeStyle=code_style
      :toolbarsFlag=toolbarsFlag
      :shortCut=shortCut
      :autofocus=autofocus
      :ishljs=ishljs
      :scrollStyle=scrollStyle
      style="z-index: 0; min-height: 90vh;width: 100%;bottom: 10px;height: 100%; top: 10px;border-radius: 10px 10px 10px 10px;"
    />

  </div>
</template>

<script>

export default {
  name: 'markdown',

  methods:{
    fh(){
      this.$router.push("/backstage")
    }
    ,
    up(){
      // 身份认证，如果是游客则不能添加
      if (this.$user.user.data.root===2) return alert("大哥，一个游客就不要搞这些了！")
          // 通过id获取最终输入的标签
         document.getElementById('fb').setAttribute('style','opacity:1;');

    },
    qx(){
      // 通过id获取最终输入的标签
      document.getElementById('fb').setAttribute('style','opacity:0;');
      this.title="";
      this.summary="";
    },
    qrfb(){
      // 插入一条文章
      if (this.title!==""&&this.summary!==""&&this.value!==""){
        // 获取指定格式的时间
        this.getNewDate()
        // 发送给后端
        this.$axios({
          url: this.$URL.URL+"blog/addBlog/"+this.$user.user.data.root,
          method:'post',
          data: JSON.stringify(
            {
              "id": "",
              "title": this.title,
              "summary": this.summary,
              "content": this.value,
              "userId": this.userId,
              "audit": this.audit,
              "like": this.like,
              "releaseTime": this.releasetime, // 获取当前时间
              "mtime": this.mtime,
              "browse": this.browse,
              "top": this.top,
              "tag": this.tag
            }
          ),
          headers: {
            'Content-Type': 'application/json',// 重要，设置传递的数据为json格式
          }
        }).then(rep=>{
            this.blackBlog = rep.data;
            alert(this.blackBlog.message)
          }
        )
        this.qx()
      }else {alert("要不咋还是写点东西吧，多少写点！");this.qx()}


    },
    //获取特定格式的日期时间  "yyyy-MM-dd HH:MMM:SS"
    getNewDate() {
      var date = new Date();
      console.log(date);
      var transverse = "-";
      var Verticalpoint = ":";
      var month = date.getMonth() + 1;//获取月份
      var strDate = date.getDate();//获取具体的日期
      var strHour = date.getHours();//获取...钟点
      var strMinute = date.getMinutes();//获取分钟数
      var strSeconde = date.getSeconds();//获取秒钟数
      //判断获取月份 、 具体的日期 、...钟点、分钟数、秒钟数 是否在1~9
      //如果是则在前面加“0”
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      console.log(month);
      if (strDate >= 1 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      if (strHour >= 1 && strHour <=9) {
        strHour = "0" + strHour
      }
      console.log(strHour);
      if (strMinute >= 1 && strMinute <= 9) {
        strMinute = "0" + strMinute;
      }

      if (strSeconde >= 1 && strSeconde <= 9) {
        strSeconde = "0" + strSeconde;
      }
      //时间日期字符串拼接
       this.releasetime = date.getFullYear() + transverse + month + transverse + strDate + " " +
        strHour + Verticalpoint + strMinute + Verticalpoint + strSeconde;
      console.log(this.releasetime)
}
  },
  data(){
    return{

      blackBlog:{
        message:"",
        state:"",
        data:{
          title:''
        }
      },

      value:'',             // 存放你想输出的博客
      title:"",
      summary:"",
      releasetime:"",           // 发布时间
      userId: this.$user.user.data.id,
      audit: 2,
      like: (Math.random()*10000).toFixed(),
      mtime: "0000-00-00",
      browse: (Math.random()*10000).toFixed(),
      top: 0,
      tag: "无",

      scrollStyle:true,         // 开启滚动条样式
      navigation:false,           // 要不要显示目录
      subfield:true,             // true： 双栏(编辑预览同屏)， false： 单栏(编辑预览分屏(通过小眼睛切换))
      defaultOpen:'',            // edit： 默认展示编辑区域 ， preview： 默认展示预览区域 , 其他 = edit
      placeholder:'开始编辑',     // 输入框为空时默认提示文本
      editable: true ,           // 是否允许编辑
      code_style:'isbl-editor-light' ,  //markdown样式： 默认github, 可选配色方案,这里不是默认
      toolbarsFlag:true,         //工具栏是否显示
      shortCut:true	,            //是否启用快捷键
      autofocus:true,            //自动聚焦到文本框
      ishljs:true,               // 代码高亮
      toolbars: {                // 工具栏
        bold: true,              // 粗体
        italic: true,            // 斜体
        header: true,            // 标题
        underline: true,         // 下划线
        strikethrough: true,     // 中划线
        mark: true,              // 标记
        superscript: true,       // 上角标
        subscript: true,         // 下角标
        quote: true,             // 引用
        ol: true,                // 有序列表
        ul: true,                // 无序列表
        link: true,              // 链接
        imagelink: true,         // 图片链接
        code: true,              // code
        table: true,             // 表格
        readmodel: true,         // 沉浸式阅读
        htmlcode: true,          // 展示html源码
        help: true,              // 帮助
        /* 1.3.5 */
        undo: true,              // 上一步
        redo: true,              // 下一步
        trash: true,             // 清空
        save: true,              // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: true,        // 导航目录
        /* 2.1.8 */
        alignleft: true,         // 左对齐
        aligncenter: true,       // 居中
        alignright: true,        // 右对齐
        /* 2.2.1 */
        subfield: true,          // 单双栏模式
        preview: true,           // 预览
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
