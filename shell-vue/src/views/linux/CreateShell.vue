<template>
    <div class="shell-container">
        <div style="width: 380px;margin-top: 20px">
            <el-card shadow="hover">
                <div slot="header"  align="center">
                    <span>从jenkins中生成获取迁移文件的脚本</span>
                </div>
                <div>
                    <el-form :model="environmentInfo" :rules="rules" ref="dataFrom">
                        <el-row>
                            <el-form-item label="项目名称:" prop="environmentType">
                                <el-select placeholder="请选择项目" size="medium" style="width: 200px;">
                                    <el-option value="test">test</el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="环境类型:" prop="environmentType">
                                <el-select placeholder="请选择环境类型" size="medium" style="width: 200px;">
                                    <el-option value="test">test</el-option>
                                </el-select>
                            </el-form-item>
                        </el-row>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                            <el-upload
                                    align="center"
                                    class="upload-demo"
                                    action="https://jsonplaceholder.typicode.com/posts/"
                                    :on-preview="handlePreview"
                                    :on-remove="handleRemove"
                                    :before-remove="beforeRemove"
                                    multiple
                                    :limit="3"
                                    :on-exceed="handleExceed"
                                    :file-list="false">
                            <el-button size="primary" type="primary" style="width: 220px;" >生成脚本</el-button>
                        </el-upload>
                    </span>
                </div>
            </el-card>
        </div>
        <div style="width: 380px;margin-top: 20px">
            <el-card shadow="hover">
                <div slot="header"  align="center">
                    <span>从项目中生成获取迁移文件的脚本</span>
                </div>
                <div>
                    <el-form :model="environmentInfo" :rules="rules" ref="dataFrom">
                        <el-row>
                            <el-form-item label="项目名称:" prop="projectName">
                                <el-select placeholder="请选择项目" size="medium" style="width: 200px;">
                                    <el-option value="test">test</el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="环境类型:" prop="environmentType">
                                <el-select placeholder="请选择环境类型" size="medium" style="width: 200px;">
                                    <el-option value="test">test</el-option>
                                </el-select>
                            </el-form-item>
                        </el-row>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                            <el-upload
                                    align="center"
                                    class="upload-demo"
                                    action="https://jsonplaceholder.typicode.com/posts/"
                                    :on-preview="handlePreview"
                                    :on-remove="handleRemove"
                                    :before-remove="beforeRemove"
                                    multiple
                                    :limit="3"
                                    :on-exceed="handleExceed"
                                    :file-list="false">
                            <el-button size="primary" type="primary" style="width: 220px;" >生成脚本</el-button>
                        </el-upload>
                    </span>
                </div>
            </el-card>
        </div>

        <div style="width: 380px; margin-top: 20px">
            <el-card shadow="hover">
                <div slot="header" align="center">
                    <span>生成备份脚本</span>
                </div>
                <div>
                    <el-form :model="environmentInfo" :rules="rules" ref="dataFrom">
                        <el-row>
                            <el-form-item label="项目名称:" prop="environmentType">
                                <el-select placeholder="请选择项目" size="medium" style="width: 200px;">
                                    <el-option value="test">test</el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="环境类型:" prop="environmentType">
                                <el-select placeholder="请选择环境类型" size="medium" style="width: 200px;">
                                    <el-option value="test">test</el-option>
                                </el-select>
                            </el-form-item>
                        </el-row>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                            <el-upload
                                    align="center"
                                    :show-file-list="false"
                                    class="upload-demo"
                                    action="/shell/createTransFerShell"
                                    :http-request="httpRequest">
                            <el-button size="primary" type="primary" style="width: 220px;">生成脚本</el-button>
                        </el-upload>
                    </span>
                </div>
            </el-card>
        </div>

        <div style="width: 380px; margin-top: 20px">
            <el-card shadow="hover" align="center">
                <div slot="header">
                    <span>生成迁移脚本</span>
                </div>
                <div>
                    <el-form :model="environmentInfo" :rules="rules" ref="dataForm">
                        <el-row>
                            <el-form-item label="项目名称:" prop="projectName">
                                <el-select v-model="environmentInfo.projectName" placeholder="请选择项目" size="medium" style="width: 200px;">
                                    <el-option v-for="item in environmentInfo.projectName"  :value="item.value"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="环境类型:" prop="type">
                                <el-select v-model="environmentInfo.type" placeholder="请选择环境类型" size="medium" style="width: 200px;">
                                    <el-option v-for="item in environmentInfo.type"  :value="item.value"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-row>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                            <el-upload
                                    align="center"
                                    :show-file-list="false"
                                    class="upload-demo"
                                    action="/shell/createTransFerShell"
                                    :http-request="httpRequest">
                            <el-button size="primary" type="primary" style="width: 220px;">生成脚本</el-button>
                        </el-upload>
                    </span>
                </div>
            </el-card>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    export default {
        name: 'CreateShell',
        data(){
            return{
                environmentInfo: {
                    projectName: '厦大',
                    type: [{
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
                },
                rules: {
                    projectName: [{required: true, message: '请输入项目名称', trigger: 'blur'}],
                    type: [{required: true, message: '请输入环境类型', trigger: 'blur'}]
                },
            }
        },
        mounted() {

        },
        methods: {
            httpRequest(file){
                this.$refs['dataForm'].validate(valid =>{
                    if(valid){
                        let formData = new FormData();
                        formData.append("file", file.file)
                        formData.append("projectName", this.projectName);
                        formData.append("type", this.type);
                        axios({
                            url: '/shell/createDB',
                            method: "post",
                            data: formData,
                            responseType: 'blob',
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        }).then(resp=>{
                            const data = resp
                            console.log(data);
                            let url = window.URL.createObjectURL(data)   // 将二进制文件转化为可访问的url
                            var a = document.createElement('a')
                            document.body.appendChild(a)
                            a.href = url
                            a.download = 'backup.sh'
                            a.click()   // 模拟点击下载
                            window.URL.revokeObjectURL(url)
                        })
                    }else{
                        alert("enter")
                        this.$message.error("请填写项目名称或项目类型");
                    }
                })
            }
        }
    }
</script>

<style>
    .shell-container {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
    }
</style>
