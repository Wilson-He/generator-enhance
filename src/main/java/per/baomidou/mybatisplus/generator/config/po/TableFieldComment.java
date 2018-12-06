package per.baomidou.mybatisplus.generator.config.po;

import lombok.Getter;
import lombok.Setter;

/**
 * TableFieldComment
 *
 * @author Wilson
 * @since 18-7-12
 */
@Setter
@Getter
public class TableFieldComment {
    private String key;
    private String value;
    private String comment;
    private String clazz;

    public TableFieldComment(String key, String comment, String clazz) {
        this.key = key;
        this.value = "\"" + key + "\"";
        this.comment = comment;
        this.clazz = clazz;
    }

    public TableFieldComment(String key, String value, String comment, String clazz) {
        this.key = key;
        this.value = value;
        this.comment = comment;
        this.clazz = clazz;
    }
}
