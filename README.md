# 基于Mybatis-plus生成器基础上进行扩展，主要扩展功能如下：
 - 添加根据数据库注释生成常量类
 - 添加spring数据库配置(spring.datasource.url,username,password,driver-class-name)读取
 - 添加默认配置
 - 排除含指定关键字的表Service、Controller类的生成
 - 将默认生成目录更改为当前root模块的output目录下
 # 依赖
    <dependency>
        <groupId>io.github.wilson-he</groupId>
        <artifactId>generator-enhance</artifactId>
        <version>0.3.1</version>
     </dependency>
     
 # 快速开始
  - Simple Start
  
        public class CustomGeneratorApplication {
            public static void main(String[] args) throws IOException {
                // 支持yml、properties配置
                DefaultGeneratorConfigFactory.defaultAutoGenerator("application.yml", "io.github.test")
                        .execute();
            }
        }
   
  - 扩展配置
   
        DefaultGeneratorConfigFactory.defaultAutoGenerator("application.yml", "io.github.test")
            .getStrategy()
            // 设置输出目录,不设置则默认生成到项目root模块的output目录下
            // .setOutputDir("E:\\project\\payment\\output")
            .setLogicDeleteFieldName("is_delete")
            // 不生成名称含Detail、Relation的Service、Controller
            .excludeKeywords("Detail", "Relation")
            .backGenerator()
            // 设置生成哪些模板
            .getTemplate()
            // 不生成常量类
            .excludeConstant()
            // 不生成Controller模板类
            .excludeController()
            .backGenerator()
            .execute();
        }
   
  - SQL常量字段注释范式(user-test.sql含DDL范式demo)
    - 删除标志量(1-已删除YES, 0-未删除NO)|删除标志量(1:已删除YES, 0:未删除NO)
       
       生成枚举值:YES(1),NO(0);
   
    - 删除标志量(YES:已删除, NO:未删除, D:未删除),删除标志量(YES-已删除, NO-未删除)
      
       生成枚举值:YES("YES"),NO("NO");
      
  - 默认常量模板例子(constant.java.ftl)
  
        package ${package.Constant};
        
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        
        /**
         * ${entity}Constant
         *
         * @author ${author}
         * @since ${date}
         */
        public interface ${entity}Constant {
        <#list table.fields as field>
            <#if field.constantField>
            /**
             * ${field.comment}
             */
            @AllArgsConstructor
            @Getter
            enum ${field.propertyName?capFirst} {
                <#list field.fieldEnums as fieldEnum>
                /**
                 * ${fieldEnum.comment}
                 */
                    <#if fieldEnum_has_next>
                ${fieldEnum.key}(${fieldEnum.value}),
                    <#else >
                ${fieldEnum.key}(${fieldEnum.value});
                    </#if>
                </#list>
                private ${field.columnType.type} value;
            }
        
            </#if>
        </#list>
        }