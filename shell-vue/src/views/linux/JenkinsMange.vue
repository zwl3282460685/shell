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
                <el-button style="margin-left: 20px" type="primary" icon="el-icon-plus" size="small" @click="showAddView">
                    添加信息
                </el-button>
            </div>
        </div>
        <div>
            <el-table
                    :data="jenkinsData"
                    v-loading="loading"
                    stripe
                    border
                    style="width: 100%; margin-top: 10px">
                <!--<el-table-column
                        prop="id"
                        label="id"
                        width="50">
                </el-table-column>-->
                <el-table-column
                        fixed
                        align="left"
                        prop="projectName"
                        label="项目名称"
                        width="170">
                </el-table-column>
                <el-table-column
                        prop="environmentType"
                        label="git分支"
                        width="100">
                </el-table-column>
                <el-table-column
                        width="350"
                        prop="classesPath"
                        label="classes路径">
                </el-table-column>
                <el-table-column
                        width="350"
                        prop="staticsPath"
                        label="statics路径">
                </el-table-column>
                <el-table-column
                        width="110"
                        prop="createTime"
                        label="创建时间">
                </el-table-column>
                <el-table-column
                        width="110"
                        prop="updateTime"
                        label="更新时间">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作">
                    <template slot-scope="scope">
                        <el-button @click="showEditView(scope.$index, scope.row)" style="padding: 3px" size="big">编辑</el-button>
                        <el-button @click="deleteInfo(scope.row)" style="padding: 3px" size="big" type="danger">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div style="display: flex;justify-content: flex-end; margin-top: 5px">
                <el-pagination
                        background
                        @size-change="sizeChange"
                        @current-change="currentChange"
                        layout="sizes, prev, pager, next, jumper, ->, total, slot"
                        :total="total">
                </el-pagination>
            </div>
            <el-dialog
                    :title="title"
                    :visible.sync="dialogVisible"
                    width="30%">
                <div>
                    <el-form :model="jenkinsInfo" :rules="rules" ref="dataFrom">
                        <el-row>
                            <el-form-item label="项目名称:" prop="projectName">
                                <el-input size="small" style="width: 250px" v-model="jenkinsInfo.projectName"
                                          placeholder="请输入项目名称"></el-input>
                            </el-form-item>
                        </el-row>
                        <el-row>
                            <el-form-item label="git分支:" prop="environmentType">
                                <el-select v-model="jenkinsInfo.environmentType" placeholder="请选择git分支" size="medium" style="width: 250px;">
                                    <el-option v-for="item in types" :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-row>
                        <el-row>
                            <el-form-item label="classes路径:" prop="classesPath">
                                <el-input size="small" style="width: 240px" v-model="jenkinsInfo.classesPath"
                                          placeholder="请输入classes文件夹路径"></el-input>
                            </el-form-item>
                        </el-row>
                        <el-row>
                            <el-form-item label="statics路径:" prop="staticsPath">
                                <el-input size="small" style="width: 240px" v-model="jenkinsInfo.staticsPath"
                                          placeholder="请输入statics文件夹路径"></el-input>
                            </el-form-item>
                        </el-row>
                    </el-form>
                </div>
                <span slot="footer" class="dialog-footer">
                    <el-button size="medium" @click="dialogVisible = false">取 消</el-button>
                    <el-button size="medium" type="primary" @click="doUpdateOrAdd">确 定</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'JenkinsMange',
        data() {
            return{
                keyword: '',
                jenkinsData: [],
                loading: false,
                dialogVisible: false,
                title: "",
                jenkinsInfo: {
                    id: '',
                    projectName: '',
                    environmentType: '',
                    classesPath: '',
                    staticsPath: ''
                },
                rules: {
                    projectName: [{required: true, message: '请输入项目名称', trigger: 'blur'}],
                    environmentType: [{required: true, message: '请输入git分支', trigger: 'blur'}],
                    classesPath: [{required: true, message: '请classes文件夹路径', trigger: 'blur'}],
                    staticsPath: [{required: true, message: '请static文件夹路径', trigger: 'blur'}]
                },
                total: 0,
                currentSize: 10,
                currentPage: 1,
                types: [{
                    value: 'dev',
                    label: '开发分支'
                },
                   {
                    value: 'tst',
                    label: '测试分支'
                },{
                    value: 'release',
                    label: 'uat环境'
                },{
                    value: 'master',
                    label: '生产分支'
                }]
            }
        },
        mounted() {
            this.initData();
            this.initPageDate();
        },
        methods: {
            sizeChange(size){
                this.currentSize = size;
                this.initPageDate();
            },
            currentChange(page){
                this.currentPage = page;
                this.initPageDate();
            },
            initPageDate(){
                this.loading = true;
                this.getRequest("/jenkins/?page=" + this.currentPage + '&size=' + this.currentSize).then(resp=>{

                    if(resp){
                        this.jenkinsData = resp.data.content;
                        this.total = resp.total;
                        this.loading = false;
                    }
                })
            },
            initData(){
                this.loading = true;
                this.getRequest("/jenkins/findAll").then(resp=>{
                    if(resp){
                        this.jenkinsData = resp;
                        this.loading = false;
                    }
                })
            },
            initSearchData(){
                this.loading = true;
                this.getRequest("/jenkins/findByName" + "?keywords=" + this.keyword).then(resp=>{
                    if(resp){
                        this.jenkinsData = resp;
                        this.loading = false;
                    }
                })
            },
            deleteInfo(data){
                this.$confirm('是否删除【' + data.projectName + '】?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/jenkins/" + data.id).then(resp => {
                        if (resp) {
                            this.initData();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            showEditView(index, data){
                this.title = '修改jenkins信息';
                Object.assign(this.jenkinsInfo, data);
                this.dialogVisible = true;
            },
            doUpdateOrAdd(){
                if(this.jenkinsInfo.id){
                    this.$refs['dataFrom'].validate(valid => {
                        if(valid){
                            this.putRequest("/jenkins/", this.jenkinsInfo).then(resp=>{
                                if(resp){
                                    this.dialogVisible = false;
                                    this.initData();
                                }
                            })
                        }
                    })
                }else {
                    this.$refs['dataFrom'].validate(valid=>{
                        if(valid){
                            this.postRequest("/jenkins/", this.jenkinsInfo).then(resp=>{
                                if(resp){
                                    this.dialogVisible = false;
                                    this.initData();
                                }
                            })
                        }
                    })
                }
            },
            showAddView(){
                this.jenkinsInfo = {
                    projectName: '',
                    environmentType: '',
                    classesPath: '',
                    staticsPath: ''
                }
                this.title = '添加jenkins信息';
                this.dialogVisible = true;
            }
        }
    }
</script>

<style>

</style>

