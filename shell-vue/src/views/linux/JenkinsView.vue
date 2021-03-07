<template>
    <div>
        <iframe src="http://192.168.1.33:13001/" width="1300" height="600" frameborder="0" ref="iframe2"/>
    </div>
</template>

<script>
    export default {
        /*data(){
            return {
                src: 'http://192.168.1.33:13001/'
            }
        },
        mounted() {
            window.addEventListener('message', this.handleMessage);
            this.iframeWin = this.$refs.iframe2.contentWindow;
        },
        methods: {
            handleMessage(event){
                const data = event.data.data
                if(data.info === 'success')
            }
        }*/
    }
</script>


<style>

</style>
