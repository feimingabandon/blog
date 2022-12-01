<template>
  <div id="app"><router-view :key="key1" />
  <Top></Top>
  </div>

</template>

<script>
import Top from "./views/Top";

export default {

  name: 'App',
  components:{
    Top,
  },
  data(){

    return{

    }
  },
  computed: { // 计算属性
    key1(){
      return this.$route.path + Math.random();
    },

  }
}
</script>

<style>

</style>
