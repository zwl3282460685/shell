<template>
    <div>
        <div>
            <div>
                <el-input placeholder="输入项目名称进行搜索" prefix-icon="el-icon-search"
                    style="width: 350px; margin-right: 10px" size="small"
                    v-model="keyword" @keydown.enter.native="initData"
                    clearable
                    @clear="initData">
                </el-input>
                <el-button type="primary" @click="" icon="el-icon-search" size="small" @click="initData" >搜索</el-button>
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
                        prop="date"
                        label="项目名称"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="地址">
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
        methods: {
            initData(){


            }
        }
    }
</script>

<style>

</style>
