package ${package.Controller};


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
<#if cfg?? && cfg.responseClassPackage??>import ${cfg.responseClassPackage};</#if>
<#if cfg?? && cfg.vo??>import ${cfg.vo}.Add${table.entityName}VO;</#if>
<#if cfg?? && cfg.vo??>import ${cfg.vo}.Update${table.entityName}VO;</#if>
<#if cfg?? && cfg.dto??>import ${cfg.dto}.Get${table.entityName}DTO;</#if>
<#if cfg?? && cfg.dto??>import ${cfg.dto}.Page${table.entityName}DTO;</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * ${table.controllerName}<#if table.comment??>-<#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if>控制器</#if>
 *
 * @author ${author}
 * @since ${date}
 */
@Validated
@Api(tags = {"<#if table.comment??>${table.comment}<#else>${table.controllerName}</#if>"})
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public interface ${table.controllerName} extends ${superControllerClass} {
    <#else>
public interface ${table.controllerName} {
    @ApiOperation(value = "[Doc]添加<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>", notes = "添加<#if table.comment??>${table.comment}</#if>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if>.class)})
    @PostMapping("/")
    <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> add(<#if cfg.vo??>Add${table.entityName}VO vo</#if>);

    @ApiOperation(value = "[Doc]查看<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>详情", notes = "查看<#if table.comment??>${table.comment}</#if>详情")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if>.class),
            @ApiResponse(code = 201, message = "data", response = <#if cfg?? && cfg.dto??>Get${table.entityName}DTO<#else >Object</#if>.class)})
    @GetMapping("/")
    <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> get(@NotBlank @ApiParam(name = "id", value = "<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>id", required = true) @RequestParam String id);

    @ApiOperation(value = "[Doc]分页查询<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>", notes = "分页查询<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if>.class),
            @ApiResponse(code = 201, message = "data", response = <#if cfg?? && cfg.dto??>Page${table.entityName}DTO<#else >Object</#if>.class)})
    @GetMapping("/page")
    <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> page(@Min(1) @ApiParam(name = "page", value = "页码", defaultValue = "1", required = true) @RequestParam(defaultValue = "1") Integer page,
                @Min(1) @ApiParam(name = "size", value = "每页返回数", defaultValue = "15", required = true) @RequestParam(defaultValue = "15") Integer size);

    @ApiOperation(value = "[Doc]更新<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>", notes = "更新<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if>.class)})
    @PutMapping("/")
    <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> update(@Validated @RequestBody <#if cfg.vo??>Update${table.entityName}VO vo</#if>);

    @ApiOperation(value = "[Doc]删除<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if></#if>", notes = "删除<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if>.class)})
    @DeleteMapping("/")
    <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> delete(@ApiParam(name = "id", value = "<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>id") @RequestParam String id);
 }
</#if>
