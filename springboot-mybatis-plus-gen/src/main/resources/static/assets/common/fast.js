layui.define(['jquery','layer','table'], function(exports) {

    var $ = layui.jquery,
        table = layui.table,
        layer = layui.layer;

    var fast = {};

    /**
     * ajax文件下载
     *
     * @param url 请求地址
     * @param fileName 文件名称
     * @param param 参数对象，会转换为JSON
     */
    fast.download = function(url,fileName,param) {
        // 创建XMLHttpRequest对象
        var xhr = new XMLHttpRequest();
        xhr.open('POST', url, true);
        // 设置发送数据的数据格式
        xhr.setRequestHeader('content-type', 'application/json');
        // 设置响应数据格式
        xhr.responseType = "blob";
        // 定义请求完成的处理参数
        xhr.onload = function () {
            if (this.status === 200) {
                // 返回200
                var blob = this.response;
                var reader = new FileReader();
                // 转换为base64，可以直接放入a --> href
                reader.readAsDataURL(blob);
                reader.onload = function (e) {
                    // 转换完成，创建一个a标签用于下载
                    var a = document.createElement('a');
                    a.style.display = 'none';
                    a.download = fileName;
                    a.href = e.target.result;
                    $("body").append(a);
                    a.click();
                    $(a).remove();
                }
            } else {
                layer.msg('有点忧伤,代码生成失败', {icon: 5});
            }
        };
        // 发送请求
        xhr.send(JSON.stringify(param));
    };

    /**
     * 项目根目录
     * @description 如果项目配置了[server.context-path]上下文，请务必将参数改为true，否则会出现错误的值
     * @type {string}
     */
    fast.ctxPath = getRootPath(false);

    /**
     * 截取请求参数
     * @param name
     * @returns {string|null}
     */
    fast.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        } else {
            return null;
        }
    };

    /**
     * 修改行数据
     * @param layId
     * @param row
     * @param data
     */
    fast.updateLayTableRow = function (layId, row, data) {
        layui.$.extend(table.cache[layId][row],data);
    };

    /**
     * 根据id获取表单数据
     * @param formId
     * @returns {Object}
     */
    fast.getFormData = function (formId) {
        var formData = {};
        var data = $('#' + formId).serializeArray();
        $.each(data, function() {
            formData[this.name] = this.value;
        });
        return formData;
    };

    /**
     * 获取根目录地址(存在Bug,嫌麻烦建议使用thymeleaf等模板引擎)
     *
     * @param flag 项目是否配置了上下文
     * @description 如果项目配置了[server.context-path]上下文，请务必将参数改为true，否则会出现错误的值
     * @returns {string}
     */
    function getRootPath(flag) {
        //获取当前网址
        var curWwwPath = window.document.location.href;
        //获取主机地址之后的目录
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        //获取主机地址
        var localhostPath = curWwwPath.substring(0,pos);
        //获取带"/"的项目名
        var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
        // 判断项目是否配置了server.context-path
        if(flag === true) {
            return localhostPath + projectName;
        }
        return localhostPath;
    }

    // 全局 lay-tips 处理
    $(document).on('mouseenter', '*[lay-tips]', function () {
        var tipText = $(this).attr('lay-tips');
        layer.tips(tipText, this, {
            tips: 1, //在上边
            time: -1 //-1不做时间限制
        });
    }).on('mouseleave', '*[lay-tips]', function () {
        layer.closeAll('tips');
    });

    exports('fast', fast);
});