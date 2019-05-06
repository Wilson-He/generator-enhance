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
        <version>LATEST</version>
     </dependency>
     
 # 快速开始
  - Simple Start
  
        public class CustomGeneratorApplication {
            public static void main(String[] args) throws IOException {
                // 支持yml、properties配置
                DefaultGeneratorConfigFactory.defaultAutoGenerator("application.yml", "io.github.test")
                        .execute();
                // DefaultGeneratorConfigFactory.defaultAutoGenerator("url","username","password", Driver.class,"io.github.wilson-he");
            }
        }
   
  - 扩展配置
   
        DefaultGeneratorConfigFactory.defaultAutoGenerator("application.yml", "io.github.test")
            .getStrategy()
            // 设置输出目录,不设置则默认生成到项目root模块的output目录下
            // .setOutputDir("E:\\project\\payment\\output")
            .setLogicDeleteFieldName("is_delete")
            // 不生成表名含detail、relation的Service、Controller
            .excludeKeywords("detail", "relation")
            // 只生成名表名pay、base的Service、Controller
            .includeKeywords("pay", "base")
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
      
  - 常量模板类生成范例(暂只有freemarker模板constant.java.ftl)
  
        package io.github.test.domain.constant;
        
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        
        /**
         * UserBaseConstant
         *
         * @author 
         * @since 2019-05-05
         */
        public interface UserBaseConstant {
            /**
             * 删除(0-未删除NO,1-已删除YES)
             */
            @AllArgsConstructor
            @Getter
            enum IsDelete {
                /**
                 * 未删除
                 */
                NO(0),
                /**
                 * 已删除
                 */
                YES(1);
                private Integer value;
            }
        
            /**
             * 状态(ENABLE-启用,DISABLE-禁用)
             */
            @AllArgsConstructor
            @Getter
            enum Status {
                /**
                 * 启用
                 */
                ENABLE("ENABLE"),
                /**
                 * 禁用
                 */
                DISABLE("DISABLE");
                private String value;
            }
        
        }


