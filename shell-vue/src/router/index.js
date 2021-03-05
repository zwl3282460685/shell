import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home'
import EnvironmentMange from '../views/linux/EnvironmentMange'
import CreateShell from '../views/linux/CreateShell'
import JenkinsMange from '../views/linux/JenkinsMange'
import CreateBat from "../views/windows/CreateBat";
import WindowsEnvMange from "../views/windows/WindowsEnvMange";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        hidden: true
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
        hidden: true
    },
    {
        path: '/linux',
        name: 'Linux',
        component: Home,
        hidden: false,
        iconCls: 'el-icon-help',
        children: [
            {
               path: '/EnvironmentMange',
               name: '项目环境信息管理',
               component: EnvironmentMange,
            },
            {
                path: '/JenkinsMange',
                name: 'Jenkins信息管理',
                component: JenkinsMange
            },
            {
               path: '/CreateShell',
               name: 'shell脚本生成',
               component: CreateShell
            }
        ]
    },
    {
        path: '/windows',
        name: 'Windows',
        component: Home,
        hidden: false,
        iconCls: 'el-icon-s-tools',
        children: [
            {
               path: '/WindowsEnvMange',
               name: '项目环境信息管理',
               component: WindowsEnvMange
            },
            {
               path: '/CreateBat',
               name: 'bat脚本生成',
               component: CreateBat
            }
        ]
    }

]

const router = new VueRouter({
  routes
})

export default router
