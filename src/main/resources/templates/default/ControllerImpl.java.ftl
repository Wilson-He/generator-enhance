package ${package.ControllerImpl};


import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RestController
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
public class ${table.controllerImplName} implements ${table.controllerName} {
    @Override
    @PostMapping("/")
    public <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> add(<#if cfg.vo??>@Validated @RequestBody Add${table.entityName}VO vo</#if>){
      return null;
    }

    @Override
    @GetMapping("/")
    public <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> get(@ApiParam(name = "id", value = "<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>id") @RequestParam String id){
      return null;
    }

    @Override
    @GetMapping("/page")
    public <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> page(@ApiParam(name = "page", value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer page,
                @ApiParam(name = "size", value = "每页返回数", defaultValue = "15") @RequestParam(defaultValue = "15") Integer size){
      return null;
    }

    @Override
    @PutMapping("/")
    public <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> update(<#if cfg.vo??>@Validated @RequestBody Update${table.entityName}VO vo</#if>){
      return null;
    }

    @Override
    @DeleteMapping("/")
    public <#if cfg?? && cfg.responseClass??>${cfg.responseClass}<#else >Object</#if> delete(@ApiParam(name = "id", value = "<#if table.comment??><#if table.comment?ends_with("表")>${table.comment?replace("表","")}<#else>${table.comment}</#if></#if>id") @RequestParam String id){
      return null;
    }
 }
