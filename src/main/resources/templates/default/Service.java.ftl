package ${package.Service};

import ${package.Service}.base.BaseService;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**<#if table.comment??>${"\n"} * <p>
 * ${table.comment}
 * </p>
 * </#if>
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} {

}
</#if>
