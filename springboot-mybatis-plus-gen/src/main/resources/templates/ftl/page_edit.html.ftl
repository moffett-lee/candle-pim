<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>修改数据</title>
    <link rel="stylesheet" th:href="@{/component/layui/css/layui.css}"/>
</head>
<body class="layui-bg-gray">
<#if table.fields?? && (table.fields?size > 0) >
<div class="layui-row">
    <div class="layui-col-md12">
        <div class="layui-card" style="margin: 10px;">
            <div class="layui-card-body">
                <form class="layui-form" action="" lay-filter="${lowerEntityName}Form">
                    <#list table.fields as field>
                    <#if field.keyFlag>
                    <input name="${field.propertyName}" type="text" style="display: none">
                    <#else>
                    <div class="layui-form-item">
                        <label class="layui-form-label">${field.comment}<span style="color: red;">*</span></label>
                        <div class="layui-input-block">
                            <input type="text" name="${field.propertyName}" id="${field.propertyName}" lay-verify="required" placeholder="请输入${field.comment}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    </#if>
                    </#list>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="formBtn">立即提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</#if>
<script th:src="@{/component/layui/layui.js}"></script>
<script th:src="@{/assets/common/lay-config.js}"></script>
<script>
    layui.use(['layer','jquery','form','fast'], function() {
        var $ = layui.jquery
            ,form = layui.form
            ,layer = layui.layer
            ,fast = layui.fast;

        //获取详情信息，填充表单
        $.ajax({
            type: 'post',
            url: fast.ctxPath + '/${lowerEntityName}/detail',
            data: {
                ${keyPropertyName}: fast.getUrlParam('${keyPropertyName}')
            },
            dataType: 'json',
            success: function (res) {
                form.val('${lowerEntityName}Form', res.data);
            },
            error: function (XMLHttpRequest,textStatus) {
                layer.msg('获取数据失败', {icon: 2});
            }
        });

        //监听提交
        form.on('submit(formBtn)', function(data) {
            $.ajax({
                type: 'post',
                url: fast.ctxPath + '/${lowerEntityName}/edit${entity}',
                data: data.field,
                dataType: 'json',
                success: function (data) {
                    // 提示信息
                    top.layer.msg(data.message, {icon: 1});
                    // 获取当前iframe层的索引
                    var index = parent.layer.getFrameIndex(window.name);
                    // 关闭弹窗
                    parent.layer.close(index);
                    // 重载表格
                    parent.layui.table.reload('${lowerEntityName}Table',{page:{curr:1}});
                },
                error: function (XMLHttpRequest,textStatus) {
                    layer.msg('请求失败，系统发生异常', {icon: 2});
                    console.log(textStatus);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>
