/**
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package per.baomidou.mybatisplus.generator.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import per.wilson.generator.constant.GenerateConstant;
import per.wilson.generator.constant.TemplateType;

/**
 * <p>
 * 模板路径配置项
 * </p>
 *
 * @author tzg hubin
 * @since 2017-06-17
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
public class TemplateConfig {

    private String entity = ConstVal.TEMPLATE_ENTITY_JAVA;

    private String constant = ConstVal.TEMPLATE_ENTITY_CONSTANT;

    private String service = ConstVal.TEMPLATE_SERVICE;

    private String serviceImpl = ConstVal.TEMPLATE_SERVICE_IMPL;

    private String mapper = ConstVal.TEMPLATE_MAPPER;

    private String xml = ConstVal.TEMPLATE_XML;

    private String controller = ConstVal.TEMPLATE_CONTROLLER;

    private String controllerImpl = ConstVal.TEMPLATE_CONTROLLER_IMPL;
    private String voGet = ConstVal.TEMPLATE_VO_ADD;
    private String voUpdate = ConstVal.TEMPLATE_VO_UPDATE;
    private String dtoGet = ConstVal.TEMPLATE_DTO_GET;
    private String dtoPage = ConstVal.TEMPLATE_DTO_PAGE;

    private String excludeRelationKeywords = GenerateConstant.EXCLUDE_RELATION;
    private String excludeDetailKeywords = GenerateConstant.EXCLUDE_DETAIL;
    private String[] excludeKeywords = GenerateConstant.EXCLUDE_KEYWORDS;
    private TemplateType templateType;

    /**
     * 模板配置
     *
     * @param templateType      模板类型 {@link TemplateType}
     * @param hasControllerImpl controller是否生成接口
     */
    public TemplateConfig(TemplateType templateType, boolean hasControllerImpl) {
        String path = templateType.path;
        if (hasControllerImpl) {
            this.controller = String.format("%sIController.java", path);
            this.controllerImpl = String.format("%sControllerImpl.java", path);
        } else {
            this.controller = String.format("%sController.java", path);
        }
    }

    public String getEntity(boolean kotlin) {
        return kotlin ? ConstVal.TEMPLATE_ENTITY_KT : entity;
    }

    public TemplateConfig setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public String getExcludeRelationKeywords() {
        return excludeRelationKeywords;
    }

    public String getControllerImpl() {
        return controllerImpl;
    }

    public TemplateConfig setControllerImpl(String controllerImpl) {
        this.controllerImpl = controllerImpl;
        return this;
    }

    public TemplateConfig setExcludeRelationKeywords(String excludeRelationKeywords) {
        this.excludeRelationKeywords = excludeRelationKeywords;
        return this;
    }

    public String getExcludeDetailKeywords() {
        return excludeDetailKeywords;
    }

    public TemplateConfig setExcludeDetailKeywords(String excludeDetailKeywords) {
        this.excludeDetailKeywords = excludeDetailKeywords;
        return this;
    }

    public String getService() {
        return service;
    }

    public TemplateConfig setService(String service) {
        this.service = service;
        return this;
    }

    public TemplateConfig setExcludeKeywords(String[] excludeKeywords) {
        this.excludeKeywords = excludeKeywords;
        return this;
    }

    public String getEntity() {
        return entity;
    }

    public String[] getExcludeKeywords() {
        return excludeKeywords;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public TemplateConfig setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
        return this;
    }

    public String getMapper() {
        return mapper;
    }

    public TemplateConfig setMapper(String mapper) {
        this.mapper = mapper;
        return this;
    }

    public String getXml() {
        return xml;
    }

    public TemplateConfig setXml(String xml) {
        this.xml = xml;
        return this;
    }

    public String getController() {
        return controller;
    }

    public TemplateConfig setController(String controller) {
        this.controller = controller;
        return this;
    }

}
