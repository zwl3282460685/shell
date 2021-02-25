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
            <el-card shadow="hover" align="center">
                <div slot="header">
                    <span>生成迁移脚本</span>
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
    </div>
</template>

<script>
    export default {
        name: 'CreateShell'
    }
</script>

<style>
    .shell-container {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
    }
</style>
