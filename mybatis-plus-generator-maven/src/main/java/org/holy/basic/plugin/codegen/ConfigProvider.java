package org.holy.basic.plugin.codegen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有配置实例.
 * @author holy
 * @version 1.0.0
 * @date 2021/3/24 15:07
 */
public class ConfigProvider {
    /**
     * 项目目录名
     */
    private static final String PROJECT_DIRECTORY_NAME = "mybatis-plus-generator-maven";
    /**
     * 基础包名
     */
    private static final String BASIC_PACKAGE_NAME = "org.holy.basic";
    private static final String BASIC_PACKAGE_DIRECTORY = "org/holy/basic";

    /**
     * 私有构造器.
     */
    private ConfigProvider() { }

    /**
     * 模板引擎
     * @return AbstractTemplateEngine
     */
    @SuppressWarnings("java:S125")
    static AbstractTemplateEngine engine() {
        // 模板引擎，默认使用 velocity engine
        return new VelocityTemplateEngine();
        // set freemarker engine
        // return new FreemarkerTemplateEngine();
        // set beetl engine
        // return new BeetlTemplateEngine();
        // set custom engine (extends com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine, see official docs)
        // return new CustomTemplateEngine();

    }

    /**
     * 全局策略.
     * @return GlobalConfig
     */
    static GlobalConfig global() {
        GlobalConfig gc = new GlobalConfig();
        // 输出目录
        gc.setOutputDir(System.getProperty("user.dir") + "/" + PROJECT_DIRECTORY_NAME + "/src/main/java");
        // 作者
        gc.setAuthor(System.getProperty("user.name"));
        // 打开文件
        gc.setOpen(false);
        // 主键
        gc.setIdType(IdType.AUTO);
        // 覆盖文件
        gc.setFileOverride(false);

        gc.setEntityName("%sDO");
        return gc;
    }

    /**
     * 数据源配置.
     * @return DataSourceConfig
     */
    static DataSourceConfig dataSource() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://"
                .concat("127.0.0.1:3306")
                .concat("/")
                .concat("xx")
                .concat("?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&useSSL=false")
        );
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        return dsc;
    }

    /**
     * 数据库表
     * @param tables 需要生成代码的表
     * @return StrategyConfig
     */
    static StrategyConfig strategy(String... tables) {
        StrategyConfig sc = new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setEntityLombokModel(true);
        sc.setChainModel(true);
        sc.setRestControllerStyle(true);
        sc.setEntityBooleanColumnRemoveIsPrefix(true);
        sc.setEntityTableFieldAnnotationEnable(true);
        sc.setInclude(tables);
        sc.setControllerMappingHyphenStyle(true);
        return sc;
    }

    /**
     * 包名.
     * @return PackageConfig
     */
    static PackageConfig packageInfo() {
        PackageConfig pc = new PackageConfig();
        pc.setParent(BASIC_PACKAGE_NAME);
        pc.setEntity("bean.domain");
        pc.setController("controller.v1");
        return pc;
    }

    /**
     * 模板.
     * @return TemplateConfig
     */
    static TemplateConfig template() {
        return new TemplateConfig()
                .setXml(null);
    }

    /**
     * 注入
     * @return InjectionConfig
     */
    @SuppressWarnings({"java:S1186", "java:S125"})
    static InjectionConfig injection() {
        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() { }
        };

        // 自定义输出配置。优先输出
        List<FileOutConfig> focList = new ArrayList<>();

        // 自定义 Mapper
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return System.getProperty("user.dir")
                        + PROJECT_DIRECTORY_NAME
                        + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        // DTO实体
        /*focList.add(new FileOutConfig("/templates/dto.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return System.getProperty("user.dir")
                        + "/" + PROJECT_DIRECTORY_NAME + "/src/main/java/" + BASIC_PACKAGE_DIRECTORY + "/bean/transfer/"
                        + tableInfo.getEntityName()+ "DTO" + StringPool.DOT_JAVA;
            }
        });*/

        // Manager 实体
        /*focList.add(new FileOutConfig("/templates/dto.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return System.getProperty("user.dir")
                        + "/" + PROJECT_DIRECTORY_NAME + "/src/main/java/" + BASIC_PACKAGE_DIRECTORY + "/bean/transfer/"
                        + tableInfo.getEntityName()+ "DTO" + StringPool.DOT_JAVA;
            }
        });*/
        ic.setFileOutConfigList(focList);

        return ic;
    }
}
