// 一般存放路由信息，router文件夹下也是存放路由的相关文件
import Vue from 'vue';
//import * as VueRouter from 'vue-router'//,这是版本4的写法，下面的是3的写法
import VueRouter from 'vue-router';


import Main from "../views/Main";
import Poetry from "../components/Poetry";
import Top from "../views/Top";
import Blog_content from "../views/Blog_content";
import Land from "../views/Land";
import markdownSet from "../views/markdownSet";
import backstage from "../views/backstage";

// 安装路由
Vue.use(VueRouter);

// 配置导出路由
export default new VueRouter({
  mode: 'history',


  routes: [
    {
      // 路由路径
      path: '/',
      // 跳转的组件
      component: Main
    },
    { path: '/poetry', component: Poetry },
    { path: '/Top', component: Top },
    { path: '/blogContent', name: 'blogContent', component: Blog_content},
    { path: '/Land', component: Land},
    { path: '/markdownSet', component: markdownSet},
    { path: '/backstage', component: backstage},

  ]
});
