package per.wilson.generator.extend.config;

import com.mysql.jdbc.Driver;
import oracle.jdbc.driver.OracleDriver;
import org.springframework.beans.BeanUtils;
import per.baomidou.mybatisplus.generator.config.DataSourceConfig;
import per.baomidou.mybatisplus.generator.config.ITypeConvert;
import per.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import per.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import per.wilson.generator.extend.converter.MySqlConverter;
import per.wilson.generator.extend.converter.PostgresSqlConverter;
import per.wilson.generator.extend.prop.GenerateProperties;

/**
 * CustomDataSourceConfig
 *
 * @author Wilson
 * @since 18-5-28
 */
public class CustomDataSourceConfig extends DataSourceConfig {

    private ITypeConvert typeConvert;

    public CustomDataSourceConfig() {
    }

    public CustomDataSourceConfig(GenerateProperties generateProperties) {
        BeanUtils.copyProperties(generateProperties, this);
        switch (getDbType()) {
            case ORACLE:
                setDriverName(OracleDriver.class.getName());
                break;
            case POSTGRE_SQL:
                setDriverName(org.postgresql.Driver.class.getName());
                break;
            default:
                setDriverName(Driver.class.getName());
                break;
        }
    }

    @Override
    public ITypeConvert getTypeConvert() {
        if (null == typeConvert) {
            switch (getDbType()) {
                case ORACLE:
                    typeConvert = new OracleTypeConvert();
                    break;
                case SQL_SERVER:
                    typeConvert = new SqlServerTypeConvert();
                    break;
                case POSTGRE_SQL:
                    typeConvert = new PostgresSqlConverter();
                    break;
                default:
                    // 默认 MYSQL
                    typeConvert = new MySqlConverter();
                    break;
            }
        }
        return typeConvert;
    }
}
