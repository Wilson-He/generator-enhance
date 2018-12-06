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

import java.nio.charset.Charset;

/**
 * 定义常量
 *
 * @author YangHu, tangguo
 * @since 2016/8/31
 */
public interface ConstVal {

    String MODULENAME = "ModuleName";

    String ENTITY = "Entity";
    String CONSTANT = "Constant";
    String SERIVCE = "Service";
    String SERVICEIMPL = "ServiceImpl";
    String MAPPER = "Mapper";
    String XML = "Xml";
    String CONTROLLER = "Controller";
    String CONTROLLERIMPL = "ControllerImpl";
    String VO = "VO";
    String VO_ADD_FORMAT = "Add%sVO";
    String VO_UPDATE_FORMAT = "Update%sVO";
    String DTO = "DTO";
    String DTO_GET = "DTO_GET";
    String DTO_GET_FORMAT = "Get%sDTO";
    String DTO_PAGE_FORMAT = "Page%sDTO";

    String ENTITY_PATH = "entity_path";
    String CONSTANT_PATH = "constant_path";
    String SERIVCE_PATH = "serivce_path";
    String SERVICEIMPL_PATH = "serviceimpl_path";
    String MAPPER_PATH = "mapper_path";
    String XML_PATH = "xml_path";
    String CONTROLLER_PATH = "controller_path";
    String CONTROLLER_IMPL_PATH = "controller_impl_path";
    String VO_PATH = "vo_path";
    String DTO_PATH = "dto_path";

    String JAVA_TMPDIR = "java.io.tmpdir";
    String UTF8 = Charset.forName("UTF-8").name();
    String UNDERLINE = "_";

    String JAVA_SUFFIX = ".java";
    String KT_SUFFIX = ".kt";
    String XML_SUFFIX = ".xml";

    String TEMPLATE_ENTITY_JAVA = "/templates/default/entity.java";
    String TEMPLATE_ENTITY_KT = "/templates/entity.kt";
    String TEMPLATE_ENTITY_CONSTANT = "/templates/default/EntityConstant.java";
    String TEMPLATE_MAPPER = "/templates/default/Mapper.java";
    String TEMPLATE_XML = "/templates/default/Mapper.xml";
    String TEMPLATE_SERVICE = "/templates/default/Service.java";
    String TEMPLATE_SERVICE_IMPL = "/templates/default/ServiceImpl.java";
    String TEMPLATE_CONTROLLER = "/templates/default/Controller.java";
    String TEMPLATE_CONTROLLER_IMPL = "/templates/default/ControllerImpl.java";
    String TEMPLATE_VO_ADD = "/templates/default/voAdd.java";
    String TEMPLATE_VO_UPDATE = "/templates/default/voUpdate.java";
    String TEMPLATE_DTO_GET = "/templates/default/dtoGet.java";
    String TEMPLATE_DTO_PAGE = "/templates/default/dtoPage.java";

    String VM_LOADPATH_KEY = "file.resource.loader.class";
    String VM_LOADPATH_VALUE = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";

    String SUPERD_MAPPER_CLASS = "com.baomidou.mybatisplus.mapper.BaseMapper";
    String SUPERD_SERVICE_CLASS = "com.baomidou.mybatisplus.service.IService";
    String SUPERD_SERVICEIMPL_CLASS = "com.baomidou.mybatisplus.service.impl.ServiceImpl";

    /**
     * 输出相关常量
     */
    String OUT_CONFIG = "config";

}
