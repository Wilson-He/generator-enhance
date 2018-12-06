package ${package.ControllerImpl};


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
import org.springframework.web.bind.annotation.RestController;
import ${package.Controller}.${table.controllerName};

/**
 * ${table.controllerImplName}<#if table.comment??>-<#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if>控制器</#if>
 *
 * @author ${author}
 * @since ${date}
 */
@Validated
@RestController
@Api(tags = {"<#if table.comment??>${table.comment}<#else>${table.controllerName}</#if>"})
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
public class ${table.controllerImplName} extends ${table.controllerName} {
    @ApiOperation(value = "[Doc]添加<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>", notes = "添加<#if table.comment??>${table.comment}</#if>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if>.class)})
    @PostMapping("/")
    public <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> add(<#if cfg.vo??>@Validated @RequestBody Add${table.entityName}VO vo</#if>){
      return null;
    }

    @ApiOperation(value = "[Doc]查看<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>详情", notes = "查看<#if table.comment??>${table.comment}</#if>详情")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if>.class),
            @ApiResponse(code = 201, message = "data", response = <#if cfg?? && cfg.dto??>Get${table.entityName}DTO<#else >Object</#if>.class)})
    @GetMapping("/")
    public <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> get(@NotBlank @ApiParam(name = "id", value = "<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>id") @RequestParam String id){
      return null;
    }

    @ApiOperation(value = "[Doc]分页查询<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>", notes = "分页查询<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if>.class),
            @ApiResponse(code = 201, message = "data", response = <#if cfg?? && cfg.dto??>Page${table.entityName}DTO<#else >Object</#if>.class)})
    @GetMapping("/page")
    public <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> page(@Min(1) @ApiParam(name = "page", value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer page,
               @Min(1) @ApiParam(name = "size", value = "每页返回数", defaultValue = "15") @RequestParam(defaultValue = "15") Integer size){
      return null;
    }

    @ApiOperation(value = "[Doc]更新<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>", notes = "更新<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if>.class)})
    @PutMapping("/")
    public <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> update(<#if cfg.vo??>@Validated @RequestBody Update${table.entityName}VO vo</#if>){
      return null;
    }

    @ApiOperation(value = "[Doc]删除<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>", notes = "删除<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if>.class)})
    @DeleteMapping("/")
    public <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> delete(@ApiParam(name = "id", value = "<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>id") @RequestParam String id){
      return null;
    }
 }
