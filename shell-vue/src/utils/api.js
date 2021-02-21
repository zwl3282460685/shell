import axios from "axios";
import { Message } from 'element-ui';
import router from "../router/index";

axios.interceptors.response.use(success=>{//请求成功
    if(success.status && success.status == 200 && success.data == 500){
        Message.error({message: success.msg})
        return
    }
    if(success.data.msg) {
        Message.success({message: success.data.msg})
    }
    return success.data;
}, error => {//请求失败
    if (error.response.status == 504 || error.response.status==404){
        Message.error({message: '服务器错误！( ╯□╰ )'})
    }else{
        if(error.response.data.msg){
            Message.error({message: error.response.data.msg})
        }else{
            Message.error({message: "未知错误！"})
        }
    }
    return;
})

//封装get、post请求
let base = '';
export const postKeyValueRequest=(url, params)=>{
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        transformRequest: [function (data) {
            let ret = '';
            for(let i in data){
                ret+=encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&'
            }
            return ret;
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
}
export const postRequest=(url, params)=>{
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params
    })
}

export const putRequest=(url, params)=>{
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params
    })
}

export const getRequest=(url, params)=>{
    return axios({
        method: 'get',
        url: `${base}${url}`,
        data: params
    })
}

export const deleteRequest=(url, params)=>{
    return axios({
        method: 'delete',
        url: `${base}${url}`,
        data: params
    })
}
