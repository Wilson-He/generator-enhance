package per.wilson.generator.extend.config;

import java.util.function.Function;

import lombok.ToString;
import per.baomidou.mybatisplus.generator.config.AbstractFileOutConfig;
import per.baomidou.mybatisplus.generator.config.po.TableInfo;

/**
 * FIleOutConfigImpl
 *
 * @author Wilson
 * @since 18-4-16
 */
@ToString
public class DefaultFileOutConfig extends AbstractFileOutConfig {

    private String outputPath;

    private Function<TableInfo, String> function;

    public DefaultFileOutConfig(String templatePath, String outputPath) {
        super(templatePath);
        this.outputPath = outputPath;
    }

    public DefaultFileOutConfig(String templatePath, Function<TableInfo, String> function) {
        super(templatePath);
        this.function = function;
    }


    @Override
    public String outputFile(TableInfo tableInfo) {
        return function == null ? this.outputPath : function.apply(tableInfo);
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public Function<TableInfo, String> getFunction() {
        return function;
    }

    public DefaultFileOutConfig setFunction(
            Function<TableInfo, String> function) {
        this.function = function;
        return this;
    }
}
