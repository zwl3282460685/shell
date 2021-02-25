import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home'
import EnvironmentMange from '../views/linux/EnvironmentMange'
import CreateShell from '../views/linux/CreateShell'
import JenkinsMange from '../views/linux/JenkinsMange'

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
        children: [
            {
               path: '/EnvironmentMange',
               name: '项目环境信息管理',
               component: EnvironmentMange
            },
            {
                path: '/JenkinsMange',
                name: 'Jenkins信息管理',
                component: JenkinsMange
            },
            {
               path: '/CreateShell',
               name: '脚本生成',
               component: CreateShell
            }
        ]
    },
    {
        path: '/windows',
        name: 'Windows',
        component: Home,
        hidden: false,
        children: [
            {
               path: '/EnvironmentMange',
               name: '环境信息管理',
               component: EnvironmentMange
            },
            {
               path: '/CreateShell',
               name: '脚本生成',
               component: CreateShell
            }
        ]
    }

]

const router = new VueRouter({
  routes
})

export default router
