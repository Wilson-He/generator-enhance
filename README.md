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
    - comment({db_val}:{val_comment}-{map.key}), 例：删除标志量(1:已删除-YES, 0:未删除-NO)
    - comment({db_val}:{val_comment}), 例：删除标志量(YES:已删除, NO:未删除)
      
  - 常量模板类生成范例

        public interface UserBaseConstant {
        
            /**
             * 删除(0:未删除-YES,1:已删除-NO)
             */
            interface IsDelete {
                /**
                 * 未删除
                 */
                Integer NO = 0;
                /**
                 * 已删除
                 */
                Integer YES = 1;
                Map<Integer, String> MAP = ImmutableMap.of(
                        NO, "未删除",
                        YES, "已删除");
            }
        
            /**
             * 状态(ENABLE:启用,DISABLE:禁用,AAA:ASEDSA用,BBB:FDADSA用,CCC:BV用AS,DDD:E用WQS)
             */
            interface Status {
                /**
                 * 启用
                 */
                String ENABLE = "ENABLE";
                /**
                 * 禁用
                 */
                String DISABLE = "DISABLE";
                /**
                 * ASEDSA用
                 */
                String AAA = "AAA";
                /**
                 * FDADSA用
                 */
                String BBB = "BBB";
                /**
                 * BV用AS
                 */
                String CCC = "CCC";
                /**
                 * E用WQS
                 */
                String DDD = "DDD";
                Map<Object, Object> MAP = ImmutableMap.builder()
                        .put(ENABLE, "启用")
                        .put(DISABLE, "禁用")
                        .put(AAA, "ASEDSA用")
                        .put(BBB, "FDADSA用")
                        .put(CCC, "BV用AS")
                        .build();
            }
        
        }


