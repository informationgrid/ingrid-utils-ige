/*
 * **************************************************-
 * InGrid Utils IGE
 * ==================================================
 * Copyright (C) 2014 - 2020 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.utils.ige.profile.beans.controls;

import java.util.Map;

public class ExtendedControls extends Controls{
    private String              width;
    private String              widthUnit = "%";
    private Map<String, String> helpMessage; // localized
    private String              scriptedCswMapping;
    private String              scriptedCswMappingImport;
    private String              indexName;
    
    public void setWidth(String width) {
        this.width = width;
    }
    public String getWidth() {
        return width;
    }
    
    public void setScriptedCswMapping(String scriptedCswMapping) {
        this.scriptedCswMapping = scriptedCswMapping;
    }
    public String getScriptedCswMapping() {
        return scriptedCswMapping;
    }
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
    public String getIndexName() {
        return indexName;
    }
    public void setHelpMessage(Map<String, String> helpMessage) {
        this.helpMessage = helpMessage;
    }
    public Map<String, String> getHelpMessage() {
        return helpMessage;
    }
    public void setWidthUnit(String widthUnit) {
        this.widthUnit = widthUnit;
    }
    public String getWidthUnit() {
        return widthUnit;
    }
    public String getScriptedCswMappingImport() {
        return scriptedCswMappingImport;
    }
    public void setScriptedCswMappingImport(String scriptedCswMappingImport) {
        this.scriptedCswMappingImport = scriptedCswMappingImport;
    }
}
