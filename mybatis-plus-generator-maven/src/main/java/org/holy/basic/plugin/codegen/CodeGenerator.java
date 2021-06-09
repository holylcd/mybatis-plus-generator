package org.holy.basic.plugin.codegen;

import com.baomidou.mybatisplus.generator.AutoGenerator;

/**
 * Mybatis Plus Plugin 代码生成器
 * @author holy
 * @version 1.0.0
 * @date 2019/9/29 16:11
 */
public class CodeGenerator {
    /**
     * 待自动生成的目标表
     */
    private static final String[] TABLES = {
//            "user",
    };

    @SuppressWarnings({"java:S125", "java:S1075"})
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator generator = new AutoGenerator();

        // 模板引擎
        generator.setTemplateEngine(ConfigProvider.engine());

        // 全局策略
        generator.setGlobalConfig(ConfigProvider.global());

        // 数据源
        generator.setDataSource(ConfigProvider.dataSource());

        // 包名
        generator.setPackageInfo(ConfigProvider.packageInfo());

        // 注入
        generator.setCfg(ConfigProvider.injection());

        // 模板
        generator.setTemplate(ConfigProvider.template());

        // 数据库表
        generator.setStrategy(ConfigProvider.strategy(TABLES));

        generator.execute();
    }
}
