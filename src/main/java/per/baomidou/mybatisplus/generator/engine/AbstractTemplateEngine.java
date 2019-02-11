/**
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package per.baomidou.mybatisplus.generator.engine;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import per.baomidou.mybatisplus.generator.AbstractInjectionConfig;
import per.baomidou.mybatisplus.generator.config.AbstractFileOutConfig;
import per.baomidou.mybatisplus.generator.config.ConstVal;
import per.baomidou.mybatisplus.generator.config.GlobalConfig;
import per.baomidou.mybatisplus.generator.config.TemplateConfig;
import per.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import per.baomidou.mybatisplus.generator.config.po.TableInfo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 模板引擎抽象类
 * </p>
 *
 * @author hubin
 * @since 2018-01-10
 */
public abstract class AbstractTemplateEngine {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractTemplateEngine.class);
    /**
     * 配置信息
     */
    private ConfigBuilder configBuilder;

    /**
     * <p>
     * 模板引擎初始化
     * </p>
     */
    public AbstractTemplateEngine init(ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
        return this;
    }


    /**
     * <p>
     * 输出 java xml 文件
     * </p>
     */
    public AbstractTemplateEngine batchOutput() {
        try {
            Map<String, String> packageInfo = configBuilder.getPackageInfo();
            final boolean generateEntity = packageInfo.get(ConstVal.ENTITY) != null;
            final boolean generateConstant = packageInfo.get(ConstVal.CONSTANT) != null;
            final boolean generateDAO = packageInfo.get(ConstVal.MAPPER) != null;
            final boolean generateMapper = packageInfo.get(ConstVal.XML) != null;
            final boolean generateService = packageInfo.get(ConstVal.SERIVCE) != null;
            final boolean generateServiceImpl = packageInfo.get(ConstVal.SERVICEIMPL) != null;
            final boolean generateController = packageInfo.get(ConstVal.CONTROLLER) != null;
            final boolean generateControllerImpl = packageInfo.get(ConstVal.CONTROLLERIMPL) != null;
            final boolean generateDTO = packageInfo.get(ConstVal.DTO) != null;
            final boolean generateVO = packageInfo.get(ConstVal.VO) != null;
            List<TableInfo> tableInfoList = this.getConfigBuilder().getTableInfoList();
            for (TableInfo tableInfo : tableInfoList) {
                Map<String, Object> objectMap = this.getObjectMap(tableInfo);
                Map<String, String> pathInfo = this.getConfigBuilder().getPathInfo();
                TemplateConfig template = this.getConfigBuilder().getTemplate();
                String[] excludeKeywords = template.getExcludeKeywords();
                // 自定义内容
                AbstractInjectionConfig abstractInjectionConfig = this.getConfigBuilder().getAbstractInjectionConfig();
                if (null != abstractInjectionConfig) {
                    abstractInjectionConfig.initMap();
                    objectMap.put("cfg", abstractInjectionConfig.getMap());
                    List<AbstractFileOutConfig> focList = abstractInjectionConfig.getFileOutConfigList();
                    if (CollectionUtils.isNotEmpty(focList)) {
                        for (AbstractFileOutConfig foc : focList) {
                            if (!containsAny(tableInfo.getEntityName(), excludeKeywords) && this
                                    .isCreate(foc.outputFile(tableInfo))) {
                                this.writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                            }
                        }
                    }
                }
                final boolean excludeFlag = !containsAny(tableInfo.getEntityName(), excludeKeywords);
                boolean generateCondition;
                // Entity.java
                String entityName = tableInfo.getEntityName();
                generateCondition = generateEntity && null != tableInfo.getMapperName() && null != pathInfo.get(ConstVal.ENTITY_PATH);
                generateFile(generateCondition, entityName, objectMap, pathInfo.get(ConstVal.ENTITY_PATH),
                        "%s", template.getEntity(this.getConfigBuilder().getGlobalConfig().isKotlin()));
                // EntityConstant.java
                generateCondition = generateConstant && tableInfo.hasConstantField() && null != pathInfo.get(ConstVal.CONSTANT_PATH);
                generateFile(generateCondition, entityName, objectMap, pathInfo.get(ConstVal.CONSTANT_PATH),
                        tableInfo.getConstantName(), template.getConstant());
                // EntityMapper.java
                generateCondition = generateDAO && null != tableInfo.getMapperName() && null != pathInfo.get(ConstVal.MAPPER_PATH);
                generateFile(generateCondition, entityName, objectMap, pathInfo.get(ConstVal.MAPPER_PATH),
                        tableInfo.getMapperName(), template.getMapper());
                // EntityMapper.xml
                generateCondition = generateMapper && null != tableInfo.getXmlName() && null != pathInfo.get(ConstVal.XML_PATH);
                generateFile(generateCondition, entityName, objectMap, pathInfo.get(ConstVal.XML_PATH),
                        tableInfo.getXmlName(), template.getXml());
                // EntityService.java
                generateCondition = generateService && excludeFlag && null != tableInfo.getServiceName()
                        && null != pathInfo.get(ConstVal.SERIVCE_PATH);
                generateFile(generateCondition, entityName, objectMap, pathInfo.get(ConstVal.SERIVCE_PATH),
                        tableInfo.getServiceName(), template.getService());
                // EntityServiceImpl.java
                generateCondition = generateService && generateServiceImpl && excludeFlag && null != tableInfo.getServiceImplName()
                        && null != pathInfo.get(ConstVal.SERVICEIMPL_PATH);
                generateFile(generateCondition, entityName, objectMap, pathInfo.get(ConstVal.SERVICEIMPL_PATH),
                        tableInfo.getServiceImplName(), template.getServiceImpl());
                // EntityController.java
                generateCondition = generateController && excludeFlag && null != tableInfo.getControllerName()
                        && null != pathInfo.get(ConstVal.CONTROLLER_PATH);
                generateFile(generateCondition, entityName, objectMap, pathInfo.get(ConstVal.CONTROLLER_PATH),
                        tableInfo.getControllerName(), template.getController());
                // EntityControllerImpl.java
                generateCondition = generateControllerImpl && excludeFlag && null != tableInfo.getControllerImplName()
                        && null != pathInfo.get(ConstVal.CONTROLLER_IMPL_PATH);
                generateFile(generateCondition, entityName, objectMap, pathInfo.get(ConstVal.CONTROLLER_IMPL_PATH),
                        tableInfo.getControllerImplName(), template.getControllerImpl());
                // GetDTO.java
                generateCondition = generateDTO && excludeFlag && null != tableInfo.getDtoGetName()
                        && null != pathInfo.get(ConstVal.DTO_PATH);
                generateFile(generateCondition, entityName, objectMap, pathInfo.get(ConstVal.DTO_PATH),
                        tableInfo.getDtoGetName(), template.getDtoGet());
                // PageDTO.java
                generateCondition = generateDTO && excludeFlag && null != tableInfo.getDtoPageName()
                        && null != pathInfo.get(ConstVal.DTO_PATH);
                generateFile(generateCondition, entityName, objectMap, pathInfo.get(ConstVal.DTO_PATH),
                        tableInfo.getDtoPageName(), template.getDtoPage());
                // AddVO.java
                generateCondition = generateVO && excludeFlag && null != tableInfo.getVoAddName()
                        && null != pathInfo.get(ConstVal.VO_PATH);
                generateFile(generateCondition, entityName, objectMap, pathInfo.get(ConstVal.VO_PATH),
                        tableInfo.getVoAddName(), template.getVoGet());
                // UpdateVO.java
                generateCondition = generateVO && excludeFlag && null != tableInfo.getVoUpdateName()
                        && null != pathInfo.get(ConstVal.VO_PATH);
                generateFile(generateCondition, entityName, objectMap, pathInfo.get(ConstVal.VO_PATH),
                        tableInfo.getVoUpdateName(), template.getVoUpdate());
            }
        } catch (Exception e) {
            logger.error("无法创建文件，请检查配置信息！", e);
        }
        return this;
    }

    private void generateFile(boolean condition, String entityName, Map<String, Object> objectMap, String path,
                              String nameTemplate, String templatePath) throws Exception {
        if (!condition) {
            return;
        }
        String file = String.format(path + File.separator + nameTemplate + this.suffixJavaOrKt(), entityName);
        if (this.isCreate(file)) {
            this.writer(objectMap, this.templateFilePath(templatePath), file);
        }
    }

    /**
     * <p>
     * 将模板转化成为文件
     * </p>
     *
     * @param objectMap    渲染对象 MAP 信息
     * @param templatePath 模板文件
     * @param outputFile   文件生成的目录
     */
    public abstract void writer(Map<String, Object> objectMap, String templatePath, String outputFile)
            throws Exception;

    /**
     * <p>
     * 处理输出目录
     * </p>
     */
    public AbstractTemplateEngine mkdirs() {
        Map<String, String> pathInfo = this.getConfigBuilder().getPathInfo();
        for (Map.Entry<String, String> entry : pathInfo.entrySet()) {
            File dir = new File(entry.getValue());
            if (!dir.exists() && !dir.getName().endsWith("null")) {
                boolean result = dir.mkdirs();
                if (result) {
                    logger.debug("创建目录： [" + entry.getValue() + "]");
                }
            }
        }
        return this;
    }


    /**
     * <p>
     * 打开输出目录
     * </p>
     */
    public void open() {
        if (this.getConfigBuilder().getGlobalConfig().isOpen()) {
            try {
                String osName = System.getProperty("os.name");
                if (osName != null) {
                    if (osName.contains("Mac")) {
                        Runtime.getRuntime()
                                .exec("open " + this.getConfigBuilder().getGlobalConfig().getOutputDir());
                    } else if (osName.contains("Windows")) {
                        Runtime.getRuntime()
                                .exec("cmd /c start " + this.getConfigBuilder().getGlobalConfig().getOutputDir());
                    } else {
                        logger.debug("文件输出目录:" + this.getConfigBuilder().getGlobalConfig().getOutputDir());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * <p>
     * 渲染对象 MAP 信息
     * </p>
     *
     * @param tableInfo 表信息对象
     */
    public Map<String, Object> getObjectMap(TableInfo tableInfo) {
        Map<String, Object> objectMap = new HashMap<>();
        ConfigBuilder config = this.getConfigBuilder();
        if (config.getStrategyConfig().isControllerMappingHyphenStyle()) {
            objectMap.put("controllerMappingHyphenStyle",
                    config.getStrategyConfig().isControllerMappingHyphenStyle());
            objectMap
                    .put("controllerMappingHyphen", StringUtils.camelToHyphen(tableInfo.getEntityPath()));
        }
        objectMap.put("restControllerStyle", config.getStrategyConfig().isRestControllerStyle());
        objectMap.put("package", config.getPackageInfo());
        GlobalConfig globalConfig = config.getGlobalConfig();
        objectMap.put("author", globalConfig.getAuthor());
        objectMap.put("idType",
                globalConfig.getIdType() == null ? null : globalConfig.getIdType().toString());
        objectMap.put("logicDeleteFieldName", config.getStrategyConfig().getLogicDeleteFieldName());
        objectMap.put("versionFieldName", config.getStrategyConfig().getVersionFieldName());
        objectMap.put("activeRecord", globalConfig.isActiveRecord());
        objectMap.put("kotlin", globalConfig.isKotlin());
        objectMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        objectMap.put("table", tableInfo);
        objectMap.put("enableCache", globalConfig.isEnableCache());
        objectMap.put("baseResultMap", globalConfig.isBaseResultMap());
        objectMap.put("baseColumnList", globalConfig.isBaseColumnList());
        objectMap.put("entity", tableInfo.getEntityName());
        objectMap.put("entityColumnConstant", config.getStrategyConfig().isEntityColumnConstant());
        objectMap.put("entityBuilderModel", config.getStrategyConfig().isEntityBuilderModel());
        objectMap.put("entityLombokModel", config.getStrategyConfig().isEntityLombokModel());
        objectMap.put("entityBooleanColumnRemoveIsPrefix",
                config.getStrategyConfig().isEntityBooleanColumnRemoveIsPrefix());
        objectMap.put("superEntityClass", this.getSuperClassName(config.getSuperEntityClass()));
        objectMap.put("superMapperClassPackage", config.getSuperMapperClass());
        objectMap.put("superMapperClass", this.getSuperClassName(config.getSuperMapperClass()));
        objectMap.put("superServiceClassPackage", config.getSuperServiceClass());
        objectMap.put("superServiceClass", this.getSuperClassName(config.getSuperServiceClass()));
        objectMap.put("superServiceImplClassPackage", config.getSuperServiceImplClass());
        objectMap
                .put("superServiceImplClass", this.getSuperClassName(config.getSuperServiceImplClass()));
        objectMap.put("superControllerClassPackage", config.getSuperControllerClass());
        objectMap.put("superControllerClass", this.getSuperClassName(config.getSuperControllerClass()));
        return objectMap;
    }


    /**
     * 获取类名
     */
    private String getSuperClassName(String classPath) {
        if (StringUtils.isEmpty(classPath)) {
            return null;
        }
        return classPath.substring(classPath.lastIndexOf(".") + 1);
    }


    /**
     * <p>
     * 模板真实文件路径
     * </p>
     *
     * @param filePath 文件路径
     */
    public abstract String templateFilePath(String filePath);


    /**
     * 检测文件是否存在
     *
     * @return 是否
     */
    protected boolean isCreate(String filePath) {
        File file = new File(filePath);
        boolean exist = file.exists();
        if (!exist) {
            this.mkDir(file.getParentFile());
        }
        return !exist || this.getConfigBuilder().getGlobalConfig().isFileOverride();
    }

    protected void mkDir(File file) {
        if (null != file) {
            if (file.getParent() == null) {
                file.mkdirs();
            } else {
                file.getParentFile().mkdirs();
            }
        }
    }


    /**
     * 文件后缀
     */
    protected String suffixJavaOrKt() {
        return this.getConfigBuilder().getGlobalConfig().isKotlin() ? ConstVal.KT_SUFFIX
                : ConstVal.JAVA_SUFFIX;
    }


    public ConfigBuilder getConfigBuilder() {
        return configBuilder;
    }

    public AbstractTemplateEngine setConfigBuilder(ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
        return this;
    }

    public boolean containsAny(String source, String... targets) {
        return org.apache.commons.lang3.StringUtils.containsAny(source, targets);
    }

}
