# 基于Mybatis-plus生成器基础上进行扩展，主要扩展功能如下：
 - 添加根据数据库注释生成常量类
 - 添加spring数据库配置读取
 - 添加默认配置
 - 排除含指定关键字的表Service、Controller类的生成
 
 # 依赖
    <dependency>
        <groupId>io.github.wilson-he</groupId>
        <artifactId>generator-enhance</artifactId>
        <version>0.3.1.RELEASE</version>
     </dependency>
     
 # 快速开始
  - Simple Start
  
        public class CustomGeneratorApplication {
            public static void main(String[] args) throws IOException {
                DefaultGeneratorConfigFactory.defaultAutoGenerator("application.yml", "io.github.test")
                        .execute();
            }
        }
   
  - 扩展配置
   
        DefaultGeneratorConfigFactory.defaultAutoGenerator("application.yml", "io.github.test")
            .getStrategy()
            .setLogicDeleteFieldName("is_delete")
            // 不生成名称含Detail、Relation的Service、Controller
            .excludeKeywords("Detail", "Relation")
            .backGenerator()
            .getTemplateConfig()
            // 不生成常量类
            .excludeConstant()
            // 不生成Controller类
            .excludeController()
            .backGenerator()
            .execute();
        }
   
  - SQL常量字段注释范式(user-test.sql含DDL范式demo)
    - 删除标志量(1-已删除YES, 0-未删除NO)|删除标志量(1:已删除YES, 0:未删除NO)
       
       生成枚举值:YES(1),NO(0);
   
    - 删除标志量(YES:已删除, NO:未删除, D:未删除),删除标志量(YES-已删除, NO-未删除)
      
       生成枚举值:YES("YES"),NO("NO");
      