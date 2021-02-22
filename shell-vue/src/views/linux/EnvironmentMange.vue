<template>
    <div>
        <div>
            <div>
                <el-input placeholder="输入项目名称进行搜索" prefix-icon="el-icon-search"
                    style="width: 350px; margin-right: 10px" size="small"
                    v-model="keyword" @keydown.enter.native="initSearchData"
                    clearable
                    @clear="initSearchData">
                </el-input>
                <el-button type="primary" @click="" icon="el-icon-search" size="small" @click="initSearchData" >搜索</el-button>
            </div>
        </div>
        <div>
            <el-table
                    :data="environmentData"
                    v-loading="loading"
                    stripe
                    border
                    style="width: 100%; margin-top: 10px">
                <el-table-column
                        prop="id"
                        label="id"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="projectName"
                        label="项目名称"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="environmentType"
                        label="环境类型"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="classesPath"
                        label="classes路径">
                </el-table-column>
                <el-table-column
                        prop="jsPath"
                        label="statics路径">
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        label="创建时间">
                </el-table-column>
                <el-table-column
                        prop="updateTime"
                        label="更新时间">
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'EnvironmentMange',
        data() {
            return{
                keyword: '',
                environmentData: [],
                loading: false

            }
        },
        mounted() {
            this.initData();
        },
        methods: {
            initData(){
                this.getRequest("/info/findAll").then(resp=>{
                    if(resp){
                        this.environmentData = resp;
                    }
                })
            },
            initSearchData(){
                this.getRequest("/info/findByName" + "?keywords=" + this.keyword).then(resp=>{
                    if(resp){
                        this.environmentData = resp;
                    }
                })
            }
        }
    }
</script>

<style>

</style>
