layui.use(['table','jquery','layer','fast'], function(){
    var table = layui.table,
        layer = layui.layer,
        $ = layui.jquery,
        fast = layui.fast;

    var tableIns = table.render({
        elem: '#genTable'
        ,url: fast.ctxPath + '/gen/list'
        ,page: true
        ,cellMinWidth: 100
        ,cols: [ [
            {type: 'checkbox'}
            ,{field:'tableName', title: '表名'}
            ,{field:'dataRows', title: '数据行', sort: true}
            ,{field:'tableDescribe', title: '表描述'}
            ,{field:'createTime', title: '创建时间', sort: true}
            ,{field:'updateTime', title: '修改时间', sort: true}
            ,{fixed: 'right', align: 'center', toolbar: '#tableBar', minWidth: 300, title: '操作'}
        ] ]
    });

    // 搜索
    $('#searchBtn').on('click',function () {
        tableIns.reload({
            where: fast.getFormData('genForm'),
            page: {curr: 1}
        });
    });

    // 重置搜索条件
    $('#resetBtn').on('click',function () {
        document.getElementById('genForm').reset();
        tableIns.reload({
            where: fast.getFormData('genForm'),
            page: {curr: 1}
        });
    });

    // 生成代码
    function execute(data) {
        layer.open({
            type: 2,
            title:'生成代码',
            content: fast.ctxPath + '/gen/add?tableName=' + data.tableName,
            area: ['80%', '80%'],
            max: true
        });
    }

    // 表格行工具栏事件监听
    table.on('tool(genTable)', function (obj) {
        var data = obj.data
            ,event = obj.event;

        if(event === 'generate') {
            execute(data);
        }
    });

});