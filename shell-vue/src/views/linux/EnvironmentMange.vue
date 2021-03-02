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
                    :data="environmentData"
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
                        prop="projectName"
                        label="项目名称"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="environmentType"
                        label="环境类型"
                        width="100">
                </el-table-column>
                <el-table-column
                        width="350"
                        prop="classesPath"
                        label="classes路径">
                </el-table-column>
                <el-table-column
                        width="350"
                        prop="jsPath"
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
                        label="操作">
                    <template slot-scope="scope">
                        <el-button @click="showEditView(scope.$index, scope.row)" style="padding: 3px" size="big">编辑</el-button>
                        <el-button @click="deleteInfo(scope.row)" style="padding: 3px" size="big" type="danger">删除</el-button>
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
                    <el-form :model="environmentInfo" :rules="rules" ref="dataFrom">
                        <el-row>
                            <el-form-item label="项目名称:" prop="projectName">
                                <el-input size="small" style="width: 250px" v-model="environmentInfo.projectName"
                                          placeholder="请输入项目名称"></el-input>
                            </el-form-item>
                        </el-row>
                        <el-row>
                            <el-form-item label="环境类型:" prop="environmentType">
                                <el-select v-model="environmentInfo.environmentType" placeholder="请选择环境类型" size="medium" style="width: 250px;">
                                    <el-option v-for="item in types" :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-row>
                        <el-row>
                            <el-form-item label="classes路径:" prop="classesPath">
                                <el-input size="small" style="width: 240px" v-model="environmentInfo.classesPath"
                                          placeholder="请输入classes文件夹路径"></el-input>
                            </el-form-item>
                        </el-row>
                        <el-row>
                            <el-form-item label="statics路径:" prop="jsPath">
                                <el-input size="small" style="width: 240px" v-model="environmentInfo.jsPath"
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
        name: 'EnvironmentMange',
        data() {
            return{
                keyword: '',
                environmentData: [],
                loading: false,
                dialogVisible: false,
                title: "",
                environmentInfo: {
                    id: '',
                    projectName: '',
                    environmentType: '',
                    classesPath: '',
                    jsPath: ''
                },
                rules: {
                    projectName: [{required: true, message: '请输入项目名称', trigger: 'blur'}],
                    environmentType: [{required: true, message: '请输入环境类型', trigger: 'blur'}],
                    classesPath: [{required: true, message: '请classes文件夹路径', trigger: 'blur'}],
                    jsPath: [{required: true, message: '请static文件夹路径', trigger: 'blur'}]
                },
                total: 0,
                currentSize: 10,
                currentPage: 1,
                types: [{
                    value: 'dev',
                    label: '开发环境'
                },{
                    value: 'tst',
                    label: '测试环境'
                },{
                    value: 'uat',
                    label: 'uat环境'
                },{
                    value: 'prd',
                    label: '生产环境'
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
                this.getRequest("/info/?page=" + this.currentPage + '&size=' + this.currentSize).then(resp=>{
                    if(resp){
                        this.environmentData = resp.data.content;
                        this.total = resp.total;
                        this.loading = false;
                    }
                })
            },
            initData(){
                this.loading = true;
                this.getRequest("/info/findAll").then(resp=>{
                    if(resp){
                        this.environmentData = resp;
                        this.loading = false;
                    }
                })
            },
            initSearchData(){
                this.loading = true;
                this.getRequest("/info/findByName" + "?keywords=" + this.keyword).then(resp=>{
                    if(resp){
                        this.environmentData = resp;
                        this.loading=false;
                    }
                })
            },
            deleteInfo(data){
                this.$confirm('是否删除【' + data.projectName + '】?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/info/" + data.id).then(resp => {
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
                this.title = '修改环境信息';
                Object.assign(this.environmentInfo, data);
                this.dialogVisible = true;
            },
            doUpdateOrAdd(){
                if(this.environmentInfo.id){
                    this.$refs['dataFrom'].validate(valid => {
                        if(valid){
                            this.putRequest("/info/", this.environmentInfo).then(resp=>{
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
                            this.postRequest("/info/", this.environmentInfo).then(resp=>{
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
                this.environmentInfo = {
                    projectName: '',
                    environmentType: '',
                    classesPath: '',
                    jsPath: ''
                }
                this.title = '添加环境信息';
                this.dialogVisible = true;
            }
        }
    }
</script>

<style>

</style>
