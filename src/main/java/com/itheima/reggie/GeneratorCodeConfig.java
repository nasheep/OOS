package com.itheima.reggie;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

import java.util.Collections;
import java.util.Scanner;

public class GeneratorCodeConfig {

    private static final String url = "jdbc:mysql://localhost:3306/reggie?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
    private static final String username = "root";
    private static final String password = "meng";
    // 生成的代码存放路径
    private static final String parentName = "com.itheima";
    // 过滤表名前缀如t_test，生成的类名只需要test
    private static final String tablePrefix = "t_";
    // 作者
    private static final String author = "t-rex";

    @Test
    public void generatorCoder() {
        String moduleName = scanner("模块名");

        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig(builder -> builder
                        // 作者
                        .author(author)
                        // 输出路径(写到java目录)
                        .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")
                        // 开启swagger
                        .enableSwagger()
                        // 时间格式
                        .commentDate("yyyy-MM-dd")
                        // 开启覆盖之前生成的文件
                        .fileOverride()
                        // 禁用生成后打开文件所在目录
                        .disableOpenDir())

                // 包配置（包名称和存放的位置）
                .packageConfig(builder -> builder
                        .parent(parentName)
                        .moduleName(moduleName)
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .mapper("mapper")
                        .xml("mapper")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper")))

                // 策略配置
                .strategyConfig(builder -> builder
                        // 配置要生成的表名
                        .addInclude(scanner("表名，多张表使用英文逗号分割").split(","))
                        // 过滤表名前缀如t_test，生成的类名只需要test
                        .addTablePrefix(tablePrefix)
                        // service配置策略
                        .serviceBuilder()
                        // service类名，%s根据表名进行替换
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        // 实体类配置策略
                        .entityBuilder()
                        // 开启lombok
                        .enableLombok()
                        // 说明逻辑删除的字段
                        .logicDeleteColumnName("deleted")
                        // 属性添加说明注解
                        .enableTableFieldAnnotation()
                        // controller配置策略
                        .controllerBuilder()
                        // 映射路径使用连字符格式，而不是驼峰
                        .enableHyphenStyle()
                        .formatFileName("%sController")
                        // 开启RestController
                        .enableRestStyle()
                        // mapper配置策略
                        .mapperBuilder()
                        // 生成通用的resultMap
                        .enableBaseResultMap()
                        // 继承的父类
                        .superClass(BaseMapper.class)
                        .formatMapperFileName("%sMapper")
                        // 开启@mapper
                        .enableMapperAnnotation()
                        .formatXmlFileName("%sMapper"))

                // 模板配置
                .templateConfig(builder -> builder
                        // 实体类模板
                        .entity("templates/myentity.java")
                )
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                // 执行代码生成
                .execute();
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help);
        if (scanner.hasNext()) {
            String ipt = scanner.nextLine();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}