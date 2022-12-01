<template>

<div>
  <!--<span class='typewriter'>{{typewriter}}</span>-->
  <span style="display:block;font-size: 100% ;font-family: 华文行楷,cursive">{{typewriter1}}</span>
  <span style="display:block;font-size: 70% ;font-family: 华文行楷,cursive">{{typewriter2}}</span>
</div>

</template>
<script >
export default {
  name: "Poetry",
  data () {
    return {
      typewriter1: '',
      typewriter2: '',
      i: 0,
      timer: 0,
      i1: 0,
      timer1: 0,
      str: '男儿何不带吴钩，收取关山十五州。',
      str_from: '《 南园十三首·其五 》 · 李贺',
    }
  },

  mounted () {
    fetch('https://v1.hitokoto.cn?c=i')
      .then(response => response.json())
      .then(data => {
        this.str = data.hitokoto;
        this.str_from="《"+data.from+"》· "+data.from_who;

        console.log(this.str+this.str_from)
      })
      .catch(console.error)
    this.typeing();

    this.typeing2();


  },
  methods: {
    typeing () {
      if (this.i <= this.str.length) {
        this.typewriter1 = this.str.slice(0, this.i++) + '|'
        this.timer = setTimeout(() => {
          this.typeing()
        }, 150)
      } else {
        clearTimeout(this.timer)
      }
    },
    typeing2 () {
      if (this.i1 <= this.str_from.length) {
        this.typewriter2 = this.str_from.slice(0, this.i1++) + '|'
        this.timer1 = setTimeout(() => {
          this.typeing2()
        }, 150)
      } else {
        clearTimeout(this.timer1)
      }
    }
  }

}

</script>

<style scoped>

</style>
