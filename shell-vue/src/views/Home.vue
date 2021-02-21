<template>
    <div>
        <el-container>
            <el-header class="homeHeader">
                <div class="title">shell</div>
                <el-dropdown class="userInfo" @command="commandHandler">
                    <span class="el-dropdown-link">{{user}}
<!--                        <i><img src=""></i>-->
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="userInfo" disabled>个人中心</el-dropdown-item>
                        <el-dropdown-item command="setting" disabled>设置</el-dropdown-item>
                        <el-dropdown-item command="logout" disabled>注销</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu router>
                        <el-submenu :index="index" v-for="(item,index) in this.$router.options.routes" v-if="!item.hidden" :key="index">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>{{item.name}}</span>
                            </template>
                            <el-menu-item :index="child.path" v-for="(child, indexj) in item.children">{{child.name}}</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main>
                    <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path != '/home'">
                        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
                        <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
                    </el-breadcrumb>
                    <router-view class="homeRouterView"/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Home",
        data(){
            return {
                user: 'admin'
            }
        },
        methods: {
            commandHandler(cmd){
                if(cmd == 'logout'){
                    this.$confirm('是否注销登录?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.getRequest("/logout");
                        window.sessionStorage.removeItem("user");
                        //this.$store.commit('initRoutes', []); //清空routes中的数据，防止注销后另一个不同角色的用户看到上一个用户的菜单
                        this.$router.replace("/");
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消操作'
                        });
                    });
                }else if(cmd == 'userInfo'){
                    this.$router.push("/hrInfo");
                }else if(cmd == 'setting'){

                }
            },
        }
    }
</script>

<style>
    .homeHeader{
        background-color: #409eff;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0px 15px;
        box-sizing: border-box;
    }
    .homeHeader .title {
        font-size: 30px;
        font-family: 方正舒体;
        color: #ffffff;
    }
    .homeHeader .userInfo{
        cursor: pointer;
    }
    .homeRouterView {
        margin-top: 15px;
    }
</style>
