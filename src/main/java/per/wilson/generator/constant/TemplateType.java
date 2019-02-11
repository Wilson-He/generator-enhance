package per.wilson.generator.constant;

/**
 * @author: Wilson
 * @since: 2018/10/23
 */
public enum TemplateType {
    /**
     * 默认模板路径
     */
    DEFAULT("/templates/default/");

    public String path;

    TemplateType(String path) {
        this.path = path;
    }
}
