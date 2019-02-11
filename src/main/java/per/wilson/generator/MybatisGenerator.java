package per.wilson.generator;

import lombok.Getter;
import per.baomidou.mybatisplus.generator.AutoGenerator;
import per.baomidou.mybatisplus.generator.config.GlobalConfig;
import per.baomidou.mybatisplus.generator.config.PackageConfig;
import per.baomidou.mybatisplus.generator.config.StrategyConfig;
import per.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import per.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import per.wilson.generator.constant.GenerateConstant;
import per.wilson.generator.extend.config.DefaultInjectionConfigImpl;
import per.wilson.generator.extend.prop.GenerateProperties;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * MybatisGenerator
 *
 * @author Wilson
 * @since 18-7-12
 */
@Getter
public class MybatisGenerator {
    private GenerateProperties properties = new GenerateProperties();
    private PackageConfig packageGenerateConfig = new PackageConfig();

    private GlobalConfig globalConfig = new GlobalConfig()
            .setActiveRecord(false)
            .setAuthor("Wilson")
            .setConstantName("%sConstant")
            .setBaseResultMap(true)
            .setBaseColumnList(true)
            .setFileOverride(true)
            .setDtoGetName("Get%sDTO")
            .setDtoPageName("Page%sDTO");

    /**
     * 数据表转实体生成策略
     */
    private StrategyConfig strategyConfig = new StrategyConfig()
            .setCapitalMode(true)
            .setEntityLombokModel(false)
            .entityTableFieldAnnotationEnable(false)
            .setDbColumnUnderline(true)
            .setNaming(NamingStrategy.underline_to_camel);

    public static void main(String[] args) {
        MybatisGenerator generator = MybatisGenerator.build("root", "tiger", "jdbc:mysql://localhost:3306/wilson", "per.wilson.web");
        generator.packageGenerateConfig.setService(null);
    }

    public static MybatisGenerator build(String username, String password, String url, String basePackage) {
        return new MybatisGenerator(username, password, url, basePackage);
    }

    private MybatisGenerator(String username, String password, String url, String basePackage) {
        try {
            properties.setUsername(username)
                    .setPassword(password)
                    .setUrl(url)
                    .afterPropertiesSet();
            packageGenerateConfig.addPackagePrefix(basePackage);
        } catch (IOException e) {
            System.err.println("生成文件的文件目录不存在");
        }
    }

    /**
     * @param clazz clazz
     * @param tableNames 指定生成的表名，不传查全部
     */
    public void generate(Class clazz, String... tableNames) {
        packageGenerateConfig.setXml(GenerateConstant.RESOURCES_PATH + File.separator + packageGenerateConfig.getXml());
        AutoGenerator generator = new AutoGenerator()
                .setGlobalConfig(globalConfig.setOutputDir(properties.getOutput()))
                .setDataSource(properties.getDataSourceConfig())
                .setStrategy(strategyConfig.setInclude(tableNames))
                .setTemplate(properties.getTemplateConfig())
                .setTemplateEngine(new FreemarkerTemplateEngine(clazz))
                .setCfg(new DefaultInjectionConfigImpl(properties.getInjectCfgMap()))
                .setPackageInfo(packageGenerateConfig);
        generator.execute();
    }

    /**
     * 根据模板生成文件
     *
     * @param clazz        clazz
     * @param objectMap    模板文件变量map
     * @param templatePath 模板文件所在位置,classpath下文件路径为/...,如resources/templates/DAO.ftl则为/templates/DAO.ftl
     * @param outputFile   输出文件位置,可通过properties.getBasePath()获取basePackage路径再进行拼接
     * @throws Exception
     */
    public static void generateCustomTemplate(Class clazz, Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
        new FreemarkerTemplateEngine(clazz).writer(objectMap, templatePath, outputFile);
    }

}
