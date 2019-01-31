/**
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package per.baomidou.mybatisplus.generator.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.Objects;

/**
 * <p>
 * 跟包相关的配置项
 *
 * @author YangHu, tangguo, hubin
 * @since 2016-08-30
 */
@Setter
@Getter
@Accessors(chain = true)
public class PackageConfig {

    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    private String parent = "src" + File.separator + "main" + File.separator + "java";

    /**
     * 父包模块名。
     */
    private String moduleName = null;

    /**
     * Entity包名
     */
    private String entity = ".entity.model";
    /**
     * EntityConstant包名
     */
    private String constant = ".entity.constant";

    /**
     * Service包名
     */
    private String service = ".service";

    /**
     * Service Impl包名
     */
    private String serviceImpl = ".service.impl";
    /**
     * Mapper包名
     */
    private String mapper = ".dao";

    /**
     * Mapper XML包名
     */
    private String xml = "mappers";

    /**
     * Controller包名
     */
    private String controller = ".controller";

    /**
     * ControllerImpl 包名
     */
    private String controllerImpl = ".controller.impl";

    /**
     * vo 包名
     */
    private String vo = ".vo";

    /**
     * dto 包名
     */
    private String dto = ".dto";

    public String getParent() {
        if (moduleName != null && moduleName.length() != 0) {
            return parent + "." + moduleName;
        }
        return parent;
    }

    public PackageConfig setParent(String parent) {
        this.parent = parent;
        return this;
    }

    public String getModuleName() {
        return moduleName;
    }

    public PackageConfig setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public String getEntity() {
        return entity;
    }

    public PackageConfig setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public String getService() {
        return service;
    }

    public PackageConfig setService(String service) {
        this.service = service;
        return this;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public PackageConfig setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
        return this;
    }

    public String getMapper() {
        return mapper;
    }

    public PackageConfig setMapper(String mapper) {
        this.mapper = mapper;
        return this;
    }

    public String getXml() {
        return xml;
    }

    public PackageConfig setXml(String xml) {
        this.xml = xml;
        return this;
    }

    public String getController() {
        if (controller == null || controller.length() == 0) {
            return "web";
        }
        return controller;
    }

    public String getControllerImpl() {
        return controllerImpl;
    }

    public PackageConfig setController(String controller) {
        this.controller = controller;
        return this;
    }

    public PackageConfig setControllerImpl(String controllerImpl) {
        this.controllerImpl = controllerImpl;
        return this;
    }

    public void addPackagePrefix(String basePackage) {
        Objects.requireNonNull(basePackage, "basePackage must not be null");
        entity = entity == null ? null : basePackage + entity;
        constant = constant == null ? null : basePackage + constant;
        mapper = mapper == null ? null : basePackage + mapper;
        service = service == null ? null : basePackage + service;
        serviceImpl = service == null ||  serviceImpl == null ? null : basePackage + serviceImpl;
        controller = controller == null ? null : basePackage + controller;
        controllerImpl = controllerImpl == null ? null : basePackage + controllerImpl;
        dto = dto == null ? null : basePackage + dto;
        vo = vo == null ? null : basePackage + vo;
    }
}
