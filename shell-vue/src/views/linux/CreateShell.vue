<template>
    <div class="shell-container">
        <div style="width: 380px;margin-top: 20px">
            <el-card shadow="hover">
                <div slot="header" align="center">
                    <span>从jenkins中生成获取迁移文件的脚本</span>
                </div>
                <div>
                    <el-form :model="jenkinsInfo" :rules="rules" ref="dataFrom1">
                        <el-row>
                            <el-form-item label="项目名称:" prop="projectName">
                                <el-select v-model='jenkinsInfo.projectName' placeholder="请选择项目" size="medium" style="width: 200px;">
                                    <el-option
                                            v-for="item in jenkinsProjectName"
                                            :key="item"
                                            :label="item"
                                            @click.native="initJenkinsType"
                                            :value="item"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="环境类型:" prop="type">
                                <el-select v-model="jenkinsInfo.type" placeholder="请选择git分支" size="medium" style="width: 200px;">
                                    <el-option
                                            v-for="item in jenkinsTypes"
                                            :label="item"
                                            :key="item"
                                            :value="item"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-row>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                            <el-upload
                                    align="center"
                                    :show-file-list="false"
                                    class="upload-demo"
                                    action="/shell/getFilesFromJenkins"
                                    :http-request="createJenkinsShell">
                            <el-button size="primary" type="primary" style="width: 220px;" >生成脚本</el-button>
                        </el-upload>
                    </span>
                </div>
            </el-card>
        </div>
        <div style="width: 380px;margin-top: 20px">
            <el-card shadow="hover">
                <div slot="header" align="center">
                    <span>从项目中生成获取迁移文件的脚本</span>
                </div>
                <div>
                    <el-form :model="environmentInfo1" :rules="rules" ref="dataFrom2">
                        <el-row>
                            <el-form-item label="项目名称:" prop="projectName">
                                <el-select v-model="environmentInfo1.projectName" placeholder="请选择项目" size="medium" style="width: 200px;">
                                    <el-option
                                            @click.native="initEnvType1"
                                            v-for="item in envProjectName"
                                            :key="item"
                                            :label="item"
                                            :value="item"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="环境类型:" prop="type">
                                <el-select v-model="environmentInfo1.type" placeholder="请选择环境类型" size="medium" style="width: 200px;">
                                    <el-option
                                            v-for="item in envTypes1"
                                            :label="item"
                                            :key="item"
                                            :value="item"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-row>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                            <el-upload
                                    align="center"
                                    :show-file-list="false"
                                    class="upload-demo"
                                    action="/shell/getFilesFromProject"
                                    :http-request="getFilesFromProject">
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
                    <el-form :model="environmentInfo2" :rules="rules" ref="dataFrom3">
                        <el-row>
                            <el-form-item label="项目名称:" prop="projectName">
                                <el-select v-model="environmentInfo2.projectName" placeholder="请选择项目" size="medium" style="width: 200px;">
                                    <el-option
                                            @click.native="initEnvType2"
                                            v-for="item in envProjectName"
                                               :label="item"
                                               :key="item"
                                               :value="item"
                                               ></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item  label="环境类型:" prop="type">
                                <el-select v-model="environmentInfo2.type" placeholder="请选择环境类型" size="medium" style="width: 200px;">
                                    <el-option
                                            v-for="item in envTypes2"
                                            :key="item"
                                            :label="item"
                                            :value="item"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-row>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                            <el-upload
                                    align="center"
                                    :show-file-list="false"
                                    class="upload-demo"
                                    action="/shell/createDB"
                                    :http-request="createDBShell">
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
                    <el-form :model="environmentInfo3" :rules="rules" ref="dataForm">
                        <el-row>
                            <el-form-item label="项目名称:" prop="projectName">
                                <el-select v-model="environmentInfo3.projectName" placeholder="请选择项目" size="medium" style="width: 200px;">
                                    <el-option
                                            @click.native="initEnvType3"
                                            v-for="item in envProjectName"
                                               :label="item"
                                               :key="item"
                                               :value="item"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="环境类型:" prop="type">
                                <el-select v-model="environmentInfo3.type" placeholder="请选择环境类型" size="medium" style="width: 200px;">
                                    <el-option
                                            v-for="item in envTypes3"
                                            :label="item"
                                            :key="item"
                                            :value="item"></el-option>
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
                                    :http-request="createTransFerShell">
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
                environmentInfo1: {
                    projectName: '',
                    type: ''
                },
                environmentInfo2: {
                    projectName: '',
                    type: ''
                },
                environmentInfo3: {
                    projectName: '',
                    type: ''
                },
                jenkinsInfo: {
                  projectName: '',
                  type:''
                },
                rules: {
                    projectName: [{required: true, message: '请输入项目名称', trigger: 'blur'}],
                    type: [{required: true, message: '请输入环境类型', trigger: 'blur'}]
                },
                envProjectName:[],
                envTypes1: [],
                envTypes2: [],
                envTypes3: [],
                jenkinsProjectName:[],
                jenkinsTypes: [],
                updateTime: ''
            }
        },
        mounted() {
            this.initEnvProjectName();
            this.initJenkinsProjectName();
            this.initTime();
        },
        methods: {
            createJenkinsShell(file) {
                this.$refs['dataFrom1'].validate(valid =>{
                    if(valid){
                        let formData = new FormData();
                        formData.append("file", file.file)
                        formData.append("projectName", this.jenkinsInfo.projectName);
                        formData.append("type", this.jenkinsInfo.type);
                        axios({
                            url: '/shell/getFilesFromJenkins',
                            method: "post",
                            data: formData,
                            responseType: 'blob',
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        }).then(resp => {
                            const data = resp
                            console.log(data);
                            let url = window.URL.createObjectURL(data)   // 将二进制文件转化为可访问的url
                            var a = document.createElement('a')
                            document.body.appendChild(a)
                            a.href = url
                            a.download = 'fromJenkins' + this.updateTime + '.sh'
                            a.click()   // 模拟点击下载
                            window.URL.revokeObjectURL(url)
                        })
                    }else{
                        this.$message.error("请填写项目名称或项目类型");
                    }
                })

            },
            getFilesFromProject(file) {
                this.$refs['dataFrom2'].validate(valid =>{
                    if(valid){
                        let formData = new FormData();
                        formData.append("file", file.file)
                        formData.append("projectName", this.environmentInfo1.projectName);
                        formData.append("type", this.environmentInfo1.type);
                        axios({
                            url: '/shell/getFilesFromProject',
                            method: "post",
                            data: formData,
                            responseType: 'blob',
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        }).then(resp => {
                            const data = resp
                            console.log(data);
                            let url = window.URL.createObjectURL(data)   // 将二进制文件转化为可访问的url
                            var a = document.createElement('a')
                            document.body.appendChild(a)
                            a.href = url
                            a.download = 'fromProject' + this.updateTime + '.sh'
                            a.click()   // 模拟点击下载
                            window.URL.revokeObjectURL(url)
                        })
                    }else{
                        this.$message.error("请填写项目名称或项目类型");
                    }
                })

            },
            createDBShell(file) {
                this.$refs['dataFrom3'].validate(valid =>{
                    if(valid){
                        let formData = new FormData();
                        formData.append("file", file.file)
                        formData.append("projectName", this.environmentInfo2.projectName);
                        formData.append("type", this.environmentInfo2.type);
                        axios({
                            url: '/shell/createDB',
                            method: "post",
                            data: formData,
                            responseType: 'blob',
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        }).then(resp => {
                            const data = resp
                            console.log(data);
                            let url = window.URL.createObjectURL(data)   // 将二进制文件转化为可访问的url
                            var a = document.createElement('a')
                            document.body.appendChild(a)
                            a.href = url
                            a.download = 'backup' + this.updateTime + '.sh'
                            a.click()   // 模拟点击下载
                            window.URL.revokeObjectURL(url)
                        })
                    }else{
                        this.$message.error("请填写项目名称或项目类型");
                    }
                })

            },
            createTransFerShell(file){
                this.$refs['dataForm'].validate(valid =>{
                    if(valid){
                        let formData = new FormData();
                        formData.append("file", file.file)
                        formData.append("projectName", this.environmentInfo3.projectName);
                        formData.append("type", this.environmentInfo3.type);
                        axios({
                            url: '/shell/createTransFerShell',
                            method: "post",
                            data: formData,
                            responseType: 'blob',
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        }).then(resp=>{
                            const data = resp
                            //console.log(data);
                            let url = window.URL.createObjectURL(data)   // 将二进制文件转化为可访问的url
                            var a = document.createElement('a')
                            document.body.appendChild(a)
                            a.href = url
                            a.download = 'transfer' + this.updateTime + '.sh'
                            a.click()   // 模拟点击下载
                            window.URL.revokeObjectURL(url)
                        })
                    }else{
                        //alert("enter")
                        this.$message.error("请填写项目名称或项目类型");
                    }
                })
            },
            initEnvProjectName(){
                this.getRequest("/shellInfo/env").then(resp=>{
                    if(resp){
                        this.envProjectName = resp;
                    }
                })
            },
            initJenkinsProjectName(){
                this.getRequest("/shellInfo/jenkins").then(resp=>{
                    if(resp){
                        this.jenkinsProjectName = resp;
                    }
                })
            },
            initJenkinsType(){
                this.getRequest("/shellInfo/JenkinsType?projectName=" + this.jenkinsInfo.projectName).then(resp=>{
                    if(resp){
                        this.jenkinsTypes = resp;
                    }
                })
            },
            initEnvType1(){
                this.getRequest("/shellInfo/envType?projectName=" + this.environmentInfo1.projectName).then(resp=>{
                    if(resp){
                        this.envTypes1 = resp;
                    }
                })
            },
            initEnvType2(){
                this.getRequest("/shellInfo/envType?projectName=" + this.environmentInfo2.projectName).then(resp=>{
                    if(resp){
                        this.envTypes2 = resp;
                    }
                })
            },
            initEnvType3(){
                this.getRequest("/shellInfo/envType?projectName=" + this.environmentInfo3.projectName).then(resp=>{
                    if(resp){
                        this.envTypes3 = resp;
                    }
                })
            },
            initTime(){
                const nowDate = new Date();
                const date = {
                    year: nowDate.getFullYear(),
                    month: nowDate.getMonth() + 1,
                    date: nowDate.getDate(),
                }
                const month = date.month>10?date.month:'0'+date.month
                const day = date.date>10?date.date:'0'+date.date
                this.updateTime = date.year + '_' + month + '_' + day;
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
