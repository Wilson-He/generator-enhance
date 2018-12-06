package ${package.ServiceImpl};

import ${package.Service}.base.BaseServiceImpl;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**<#if table.comment??>${"\n"} * <p>
 * ${table.comment}
 * </p>
 * </#if>
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} {
    @Resource
    private ${table.mapperName} ${table.mapperName?uncap_first};

}
</#if>
