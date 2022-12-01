// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from "./router";
import Vuesax from 'vuesax'
import 'vuesax/dist/vuesax.css' //Vuesax styles
import VueParticles from 'vue-particles'
import axios from 'axios'
import VueAxios from 'vue-axios'




// 全局注册
// import with ES6 markdown插件
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import url from "./structure/url";
import blog from "./structure/blog";
import user from "./structure/user";


Vue.prototype.$URL=url // 定义全局变量url
Vue.prototype.$blog=blog // 定义全局变量一篇博客模板
Vue.prototype.$user=user // 定义全局变量一个用户模板

Vue.use(mavonEditor)
Vue.use(VueParticles)
Vue.use(Vuesax)
Vue.use(VueAxios, axios)
Vue.config.productionTip = false

Vue.prototype.$axios = axios

// 跳转后返回顶部
router.afterEach((to,from,next) => {
  window.scrollTo(0,0);
})



/* eslint-disable no-new */
new Vue({
  el: '#app',
  // 配置路由
  router,
  components: { App },
  template: '<App/>',


});
