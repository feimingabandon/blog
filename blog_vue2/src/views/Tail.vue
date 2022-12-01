<template>
  <div style="margin-top: 123.520px;margin-bottom:52px;text-align: center;">
    <!--背景图片-->
    <footer>
      <!--        <footer style="background: url('/src/assets/Tail_xsq.jpg') repeat-x;height: 240px;"></footer>-->
      <!--        <footer style="background: url('/src/assets/s_tall.png') repeat-x;height: 240px;"></footer>-->
    </footer>
    <div style="font: 16px 华文行楷;">[  男儿何不带吴钩、收取关山十五州  ]</div>
    <!--存放博客运行时间-->
    <div style="font-size: 12px; color: #888;">This blog has running : <span id="box1"></span> &nbsp;ღゝ◡╹)ノ♡</div>
    <!--友链-->
    <div style='font-size: 12px; color: #888;'>友情链接：
      <!--一言链接-->
      <a href="https://v1.hitokoto.cn" style="text-decoration:none;color: #212121;">一言</a>
      <!--看板娘链接-->
      <a href="https://github.com/fghrsh/live2d_demo" style="text-decoration:none;color: #212121;">/&nbsp;看板娘&nbsp;&nbsp;  </a>
      特别鸣谢：
      <a href="https://www.cnblogs.com/bndong/p/9947729.html" style="text-decoration:none;color: #212121;"> BNDong</a>

    </div>

  </div>
</template>

<script type="text/javascript" >
export default {
  name: "Tail",
  methods:{

  }
}
function timingTime(){
  let start = '2022-11-29 21:31:00'
  let startTime = new Date(start).getTime()
  let currentTime = new Date().getTime()
  let difference = currentTime - startTime
  let m =  Math.floor(difference / (1000))
  let mm = m % 60  // 秒
  let f = Math.floor(m / 60)
  let ff = f % 60 // 分钟
  let s = Math.floor(f/ 60) // 小时
  let ss = s % 24
  let day = Math.floor(s  / 24 ) // 天数
  return day + "d  " + ss + "h  " + ff + "m  " + mm +'s  ' //2197 d 9 h 28 m 37 s
}
setInterval(()=>{
  if (document.getElementById('box1'))
  document.getElementById('box1').innerHTML = timingTime()

},1000)
</script>

<style scoped>

</style>
