package ${package.DTO};

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * ${table.dtoPageName}
 *
 * @author ${author}
 * @since ${date}
 */
@Builder
@Setter
@Getter
@ApiModel("<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if><#else>${entity}</#if>分页dto")
public class ${table.dtoPageName}{

}
