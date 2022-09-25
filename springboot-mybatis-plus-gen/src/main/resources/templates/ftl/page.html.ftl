<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>${table.comment}</title>
    <link rel="stylesheet" th:href="@{/component/layui/css/layui.css}"/>
    <link rel="stylesheet" th:href="@{/css/public.css}"/>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body">

            <form class="layui-form" id="${lowerEntityName}Form">
                <div class="layui-form">
                    <div class="layui-form-item">
                    <#list allFieldInfo as fieldInfo>
                        <#if fieldInfo.queryField>
                        <div class="layui-inline">
                            <label class="layui-form-label">${fieldInfo.fieldComment}</label>
                            <div class="layui-input-inline">
                                <input id="${fieldInfo.fieldName}" name="${fieldInfo.fieldName}" class="layui-input" type="text" placeholder="请输入${fieldInfo.fieldComment}"/>
                            </div>
                        </div>
                        </#if>
                    </#list>
                        <div class="layui-inline">
                            <button type="button" id="searchBtn" class="layui-btn layui-btn-normal icon-btn layui-btn-sm">
                                <i class="layui-icon">&#xe615;</i> 搜索
                            </button>
                            <button type="button" id="resetBtn" class="layui-btn layui-btn-primary icon-btn layui-btn-sm">
                                <i class="layui-icon">&#xe669;</i> 重置
                            </button>
                        </div>
                    </div>
                </div>
            </form>

            <table class="layui-hide" id="${lowerEntityName}Table" lay-filter="${lowerEntityName}Table"></table>

            <!-- 表格操作工具栏 -->
            <script type="text/html" id="toolBar">
                <div class="layui-btn-container">
                    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">
                        <i class="layui-icon">&#xe654;</i> 新增数据
                    </button>
                    <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">
                        <i class="layui-icon">&#xe640;</i> 批量删除
                    </button>
                </div>
            </script>

            <!-- 数据行工具栏 -->
            <script type="text/html" id="lineBar">
                <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
            </script>

        </div>
    </div>
</div>

<script th:src="@{/component/layui/layui.js}"></script>
<script th:src="@{/assets/common/lay-config.js}"></script>

<script>
    layui.use(['table','layer','jquery','fast'], function() {
        var layer = layui.layer
            ,table = layui.table
            ,$ = layui.jquery
            ,fast = layui.fast;

        var tableIns = table.render({
            elem: '#${lowerEntityName}Table'
            ,method:'post'
            ,url: fast.ctxPath + '/${lowerEntityName}/list' //数据接口
            ,page: true //开启分页
            ,toolbar: '#toolBar' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,cols: [ [ //表头
                {type: 'checkbox', fixed: 'left'}
                <#list table.fields as field>
                <#if field.keyFlag>
                ,{field: '${field.propertyName}',hide:true,title:'${field.comment}'}
                <#else>
                ,{field: '${field.propertyName}', title: '${field.comment}'}
                </#if>
                </#list>
                ,{fixed: 'right',width: 150, align:'center', toolbar: '#lineBar',title:'操作'}
            ] ]
        });

        // 监听头工具栏事件
        table.on('toolbar(${lowerEntityName}Table)', function(obj) {
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据

            if (obj.event === 'add') {
                // 新增数据
                layer.open({
                    type: 2,
                    title:"新增数据",
                    shadeClose: false,
                    shade: 0.3,
                    area: ["80%","80%"],
                    content: fast.ctxPath + '/${lowerEntityName}/add'
                });
            } else if(obj.event === 'delete') {
                // 批量删除
                if (checkStatus.data.length === 0) {
                    layer.msg('请选择要删除的数据', {icon: 2});
                } else {
                    layer.confirm('确定要删除选中数据吗？', function(index) {
                        var ids = [];
                        $.each(data, function(index,element) {
                            ids.push(element.${keyPropertyName});
                        });
                        layer.close(index);
                        // 异步请求
                        $.ajax({
                            type: 'post',
                            url: fast.ctxPath + '/${lowerEntityName}/deleteBatch',
                            data: {ids: ids},
                            dataType: 'json',
                            success:function (res) {
                                layer.msg(res.message, {icon: 1});
                                table.reload('${lowerEntityName}Table',{
                                    page:{curr:1}
                                });
                            }
                        });
                    });
                }
            }
        });

        //监听行工具事件
        table.on('tool(${lowerEntityName}Table)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值

            if(layEvent === 'del') {
                // 单记录删除
                layer.confirm('确定要删除吗？', function() {
                    $.ajax({
                        type: 'post',
                        url: fast.ctxPath + '/${lowerEntityName}/delete',
                        data: {
                            ${keyPropertyName} : data.${keyPropertyName}
                        },
                        dataType: 'json',
                        success: function (res) {
                            layer.msg(res.message, {icon: 1});
                            table.reload('${lowerEntityName}Table',{page:{curr:1}});
                        }
                    });
                });
            } else if(layEvent === 'edit') {
                // 修改
                layer.open({
                    type: 2,
                    title: "修改数据",
                    shadeClose: false,
                    shade: 0.3,
                    area: ["80%","80%"],
                    content: fast.ctxPath + '/${lowerEntityName}/edit?${keyPropertyName}='+obj.data.${keyPropertyName}
                });
            }
        });

        // 搜索
        $('#searchBtn').on('click',function () {
            tableIns.reload({
                where: fast.getFormData('${lowerEntityName}Form'),
                page: {curr: 1}
            });
        });

        // 重置搜索条件
        $('#resetBtn').on('click',function () {
            document.getElementById('${lowerEntityName}Form').reset();
            tableIns.reload({
                where: fast.getFormData('${lowerEntityName}Form'),
                page: {curr: 1}
            });
        });

    });
</script>
</body>
