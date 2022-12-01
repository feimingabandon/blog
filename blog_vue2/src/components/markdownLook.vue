<template>
  <div style="left: 0;right: 0;width: 100%;">
    <mavon-editor
    :value=information
    :navigation=navigation
    :subfield=subfield
    :defaultOpen=defaultOpen
    :placeholder=placeholder
    :editable=editable
    :codeStyle=codeStyle
    :toolbarsFlag=toolbarsFlag
    :shortCut=shortCut
    :autofocus=autofocus
    :ishljs=ishljs
    :boxShadow=boxShadow
    :toolbarsBackground=toolbarsBackground
    :previewBackground=previewBackground
    :boxShadowStyle="boxShadowStyle"
    style="z-index: 0; min-height: 50vh; border: none;background: linear-gradient(aliceblue,aliceblue);padding-top: 30px;"
  />

  </div>
</template>

<script>

export default {
  name: "markdownLook",
  props: ['information'],
  data(){
    return{
      value: '',             // 存放你想输出的博客
      toolbarsBackground:""	,//工具栏背景颜色
      previewBackground:""	, //预览框背景颜色
      boxShadow:false ,           // 去除背景阴影
      boxShadowStyle:"",           // 边框阴影样式
      navigation:false,           // 要不要显示目录
      subfield:false,             // true： 双栏(编辑预览同屏)， false： 单栏(编辑预览分屏(通过小眼睛切换))
      defaultOpen:'preview',            // edit： 默认展示编辑区域 ， preview： 默认展示预览区域 , 其他 = edit
      placeholder:'',     // 输入框为空时默认提示文本
      editable: false ,           // 是否允许编辑
      codeStyle:'xt256' ,  //markdown样式： 默认github, 可选配色方案vs2015,xt256
      toolbarsFlag:false,         //工具栏是否显示
      shortCut:false	,            //是否启用快捷键
      autofocus:false,            //自动聚焦到文本框
      ishljs:true,               // 代码高亮

    }
  },
}
</script>

<style scoped>

</style>
