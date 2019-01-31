package per.wilson.generator.extend.prop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import per.baomidou.mybatisplus.generator.config.DataSourceConfig;
import per.baomidou.mybatisplus.generator.config.TemplateConfig;
import per.baomidou.mybatisplus.generator.config.rules.DbType;
import per.wilson.generator.constant.GenerateConstant;
import per.wilson.generator.constant.TemplateType;
import per.wilson.generator.extend.config.CustomDataSourceConfig;
import per.wilson.generator.utils.IfUtils;
import per.wilson.generator.utils.MapUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Wilson
 * @since: 2018/10/8
 * @since:
 * @description: 文件生成配置类
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class GenerateProperties {

    /**
     * 输出路径(可为绝对路径),默认为当前生成项目的output文件夹下
     */
    private String output = "output";
    private String url;
    private String username;
    private String password;
    private String basePackage;
    private DbType dbType = DbType.MYSQL;
    /**
     * 逻辑删除字段名
     */
    private String deleteField = "is_delete";
    private String tablePrefix = "";
    /**
     * 包含的单词表不生成Service、Controller
     */
    private String[] excludeWords = GenerateConstant.EXCLUDE_KEYWORDS;
    /**
     * controller返回响应类,可不设置, 或responseClass，responseClassCanonicalName二选一
     */
    private Class responseClass;
    /**
     * 完整类名字符串,packageName.className
     */
    private String responseClassCanonicalName;
    /**
     * 文件生成前是否清空生成目录
     */
    private Boolean cleanBeforeGenerate = true;
    private Map<String, Object> injectCfgMap;
    /**
     * 生成文件模板配置,相应分成设置null则不生成
     */
    private TemplateConfig templateConfig = new TemplateConfig(TemplateType.DEFAULT, true);

    public static GenerateProperties build() {
        return new GenerateProperties();
    }

    public static GenerateProperties build(String url, String username, String password,
                                           String basePackage) {
        return new GenerateProperties(url, username, password, basePackage);
    }

    private GenerateProperties(String url, String username, String password,
                               String basePackage) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.basePackage = basePackage;
    }

    public GenerateProperties setUrl(String url) {
        this.url = url.contains("mysql") && !url.contains("?") ? url + GenerateConstant.MYSQL_SUFFIX : url;
        return this;
    }

    public GenerateProperties afterPropertiesSet() throws IOException {
        if (StringUtils.isAnyEmpty(url, username, password)) {
            throw new RuntimeException("url,name,password皆不能为空");
        }
        initOutput();
        injectCfgMap = new HashMap<>(8);
        IfUtils.withIf(responseClass != null, injectCfgMap, e -> MapUtils.build(e)
                .put("responseClass", responseClass.getSimpleName())
                .put("responseClassPackage", responseClass.getCanonicalName()));
        IfUtils.withIf(responseClassCanonicalName != null, injectCfgMap, e -> MapUtils.build(e)
                .put("responseClass", StringUtils.substringAfterLast(responseClassCanonicalName, "."))
                .put("responseClassPackage", responseClassCanonicalName));
        return this;
    }

    /**
     * 若目录存在则清空生成目录下的文件
     * @throws IOException
     */
    private void initOutput() throws IOException {
        if (cleanBeforeGenerate) {
            File file = new File(output);
            if (file.exists()) {
                FileUtils.cleanDirectory(new File(output));
            }
        }
    }

    public String getOutput() {
        return output;
    }

    public GenerateProperties setOutput(String output) {
        this.output = output;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public GenerateProperties setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public GenerateProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public GenerateProperties setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }

    public DbType getDbType() {
        return dbType;
    }

    public GenerateProperties setDbType(DbType dbType) {
        this.dbType = dbType;
        return this;
    }

    public String getDeleteField() {
        return deleteField;
    }

    public GenerateProperties setDeleteField(String deleteField) {
        this.deleteField = deleteField;
        return this;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public GenerateProperties setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
        return this;
    }

    public String[] getExcludeWords() {
        return excludeWords;
    }

    public GenerateProperties setExcludeWords(String[] excludeWords) {
        this.excludeWords = excludeWords;
        return this;
    }

    public Class getResponseClass() {
        return responseClass;
    }

    public GenerateProperties setResponseClass(Class responseClass) {
        this.responseClass = responseClass;
        return this;
    }

    public String getResponseClassCanonicalName() {
        return responseClassCanonicalName;
    }

    public GenerateProperties setResponseClassCanonicalName(
            String responseClassCanonicalName) {
        this.responseClassCanonicalName = responseClassCanonicalName;
        return this;
    }

    public Boolean getCleanBeforeGenerate() {
        return cleanBeforeGenerate;
    }

    public GenerateProperties setCleanBeforeGenerate(Boolean cleanBeforeGenerate) {
        this.cleanBeforeGenerate = cleanBeforeGenerate;
        return this;
    }

    public Map<String, Object> getInjectCfgMap() {
        return injectCfgMap;
    }

    public GenerateProperties setInjectCfgMap(
            Map<String, Object> injectCfgMap) {
        this.injectCfgMap = injectCfgMap;
        return this;
    }

    public TemplateConfig getTemplateConfig() {
        return templateConfig;
    }

    public GenerateProperties setTemplateConfig(TemplateType templateType, boolean hasControllerImpl) {
        this.templateConfig = new TemplateConfig(templateType, hasControllerImpl);
        return this;
    }

    /**
     * 数据源获取
     *
     * @return
     */
    public DataSourceConfig getDataSourceConfig() {
        return new CustomDataSourceConfig(this);
    }

    public String getBasePath() {
        return StringUtils.replaceChars(output + File.separator + GenerateConstant.JAVA_PATH
                + File.separator + basePackage, ".", File.separator);
    }
}
