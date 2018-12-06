package per.wilson.generator.constant;

/**
 * @author: Wilson
 * @date: 2018/10/23
 * @since:
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
