let proxyObj= {};

proxyObj['/'] = {
    ws: false,
    target: 'http://localhost:8082',
    changeOrigin: true,
    pathRewrite: {
        '^/': ''
    }
}
module.exports={
    devServer: {
        host: '0.0.0.0',
        port: 8080,
        proxy: proxyObj,
        disableHostCheck: true
    }
}
