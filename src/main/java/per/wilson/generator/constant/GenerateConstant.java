package per.wilson.generator.constant;

import java.io.File;

/**
 * GenerateConstant
 *
 * @author Wilson
 * @since 18-7-12
 */
public interface GenerateConstant {

    String[] EXCLUDE_KEYWORDS = new String[]{"Detail", "Relation"};
    String EXCLUDE_DETAIL = "Detail";
    String EXCLUDE_RELATION = "Relation";

    interface BaseTemplate {
        String BASE_DAO = "/templates/default/BaseDAO.java";
        String BASE_SERVICE = "/templates/default/BaseService.java";
        String BASE_SERVICE_IMPL = "/templates/default/BaseServiceImpl.java";
    }

    String FREEMARKER_SUFFIX = ".ftl";
    String MYSQL_SUFFIX = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    String JAVA_PATH = "src" + File.separator + "main" + File.separator + "java";
    String RESOURCES_PATH = "src" + File.separator + "main" + File.separator + "resources";
}
