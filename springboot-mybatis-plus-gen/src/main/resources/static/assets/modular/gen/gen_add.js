layui.use(['table','jquery','form','fast'], function(){
    var table = layui.table,
        form = layui.form,
        $ = layui.jquery,
        fast = layui.fast;

    table.render({
        elem: '#fieldTable'
        ,url: fast.ctxPath + '/gen/getFields'
        ,page: false
        ,where: {
            tableName: fast.getUrlParam('tableName')
        }
        ,cellMinWidth: 100
        ,cols: [ [
            {field: 'fieldName', title: '字段名称'}
            ,{field: 'fieldComment', title: '字段描述'}
            ,{field: 'dataType', title: 'SQL类型'}
            ,{title:'查询', event: 'queryField', width: 60, align: 'center', toolbar: '#toolBar'}
        ] ]
    });


    // 表单事件监听
    form.on('submit(execute)', function(data) {
        // 获取字段信息
        data.field.fields = layui.table.cache['fieldTable'];
        // 处理开关的选项
        data.field.createPage = data.field.createPage === "on";
        data.field.createSql = data.field.createSql === "on";
        data.field.createSwagger = data.field.createSwagger === "on";
        // 获取表名
        data.field.tableName = fast.getUrlParam('tableName');
        // 发送请求
        fast.download(fast.ctxPath + '/gen/execute','code.zip',data.field);
        return false;
    });

    // 监听行工具事件
    table.on('tool(fieldTable)', function(obj) {
        var data = obj.data;
        // 页面查询字段
        if (obj.event === 'queryField') {
            var flag = $(obj.tr).find('.queryField').is(":checked");
            data.queryField = flag === true;
            var rowIndex = obj.tr[0].dataset.index;
            fast.updateLayTableRow('fieldTable',rowIndex,data);
        }
    });

    // 取消 关闭弹窗
    $('#closeDialog').on('click',function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

});