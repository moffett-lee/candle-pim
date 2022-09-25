package com.fast.coding.generator.toolkit;

import com.fast.coding.generator.core.entity.FieldInfo;

import java.util.List;

/**
 * 处理模板变量常用方法工具类
 *
 * @author Bamboo
 * @since 2020-03-19
 */
public class TemplateUtil {

    /**
     * 第一个首字母小写
     *
     * @param value str
     * @return ${String}
     */
    public static String lowerFirst(String value) {
        if(Character.isLowerCase(value.charAt(0))) {
            return value;
        } else {
            return Character.toLowerCase(value.charAt(0)) + value.substring(1);
        }
    }

    /**
     * 下划线转驼峰
     * @param underlineName 字符串
     * @return 驼峰名称的字符串
     */
    public static String underlineToCamel(String underlineName) {
        //截取下划线分成数组
        char[] charArray = underlineName.toCharArray();
        //判断上次循环的字符是否是"_"
        boolean underlineBefore = false;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0,l = charArray.length; i < l; i++) {
            //判断当前字符是否是"_",如果跳出本次循环
            if (charArray[i] == 95) {
                underlineBefore = true;
            } else if (underlineBefore) {
                //如果为true，代表上次的字符是"_",当前字符需要转成大写
                buffer.append(charArray[i] -= 32);
                underlineBefore = false;
            } else {
                //不是"_"后的字符就直接追加
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }

    /**
     * 下划线转驼峰
     * @param fieldInfos 字段集合
     * @return 转换完成的集合
     */
    public static List<FieldInfo> underlineToCamel(List<FieldInfo> fieldInfos) {
        for (FieldInfo fieldInfo : fieldInfos) {
            String str = TemplateUtil.underlineToCamel(fieldInfo.getFieldName());
            fieldInfo.setFieldName(str);
        }
        return fieldInfos;
    }
}
