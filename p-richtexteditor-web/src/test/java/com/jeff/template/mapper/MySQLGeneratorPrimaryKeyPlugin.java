package com.jeff.template.mapper;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

public final class MySQLGeneratorPrimaryKeyPlugin extends PluginAdapter {

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        if (introspectedTable.getPrimaryKeyColumns().size() == 1) {
            FullyQualifiedJavaType fullyQualifiedJavaType = introspectedTable.getPrimaryKeyColumns().get(0)
                    .getFullyQualifiedJavaType();
            String primaryKeyClassTypeName = fullyQualifiedJavaType.getFullyQualifiedName();
            if ("java.lang.Long".equals(primaryKeyClassTypeName)) {
                element.addAttribute(new Attribute("useGeneratedKeys", "true"));
                element.addAttribute(new Attribute("keyProperty",
                        introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty()));
            }
        } else {
            System.out.println("主键不止一个，不生成useGeneratedKeys和keyProperty属性!");
        }
        return super.sqlMapInsertElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        if (introspectedTable.getPrimaryKeyColumns().size() == 1) {
            FullyQualifiedJavaType fullyQualifiedJavaType = introspectedTable.getPrimaryKeyColumns().get(0)
                    .getFullyQualifiedJavaType();
            String primaryKeyClassTypeName = fullyQualifiedJavaType.getFullyQualifiedName();
            if ("java.lang.Long".equals(primaryKeyClassTypeName)) {
                element.addAttribute(new Attribute("useGeneratedKeys", "true"));
                element.addAttribute(new Attribute("keyProperty",
                        introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty()));
            }
        } else {
            System.out.println("主键不止一个，不生成useGeneratedKeys和keyProperty属性!");
        }
        return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }
}