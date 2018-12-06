package ${package.Mapper};

import ${package.Mapper}.base.BaseDAO;
import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**<#if table.comment??>${"\n"} * <p>
    * ${table.comment}
    * </p>
 * </#if>
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
@Repository
public interface ${table.mapperName}{
}
</#if>
