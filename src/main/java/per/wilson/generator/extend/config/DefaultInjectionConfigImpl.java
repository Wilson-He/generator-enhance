package per.wilson.generator.extend.config;

import java.util.Map;

import per.baomidou.mybatisplus.generator.AbstractInjectionConfig;

/**
 * DefaultInjectionConfigImpl
 *
 * @author Wilson
 * @since 18-4-16
 */
public class DefaultInjectionConfigImpl extends AbstractInjectionConfig {
    private Map<String, Object> cfgMap;

    @Override
    public void initMap() {
        setMap(cfgMap);
    }

    public DefaultInjectionConfigImpl(Map<String, Object> cfgMap) {
        this.cfgMap = cfgMap;
    }
}
