/*
 * **************************************************-
 * InGrid Utils IGE
 * ==================================================
 * Copyright (C) 2014 - 2017 wemove digital solutions GmbH
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

public class Controls {
    public static String LEGACY_CONTROL     = "legacyControl";
    public static String TEXT_CONTROL       = "textControl";
    public static String NUMBER_CONTROL     = "numberControl";
    public static String DATE_CONTROL       = "dateControl";
    //public static String TEXTAREA_CONTROL   = "TEXTAREA_CONTROL";
    public static String SELECT_CONTROL     = "selectControl";
    public static String GRID_CONTROL       = "tableControl";
    public static String THESAURUS_CONTROL  = "thesaurusControl";
    public static String CHECKBOX_CONTROL  = "checkboxControl";
    
    private String              type;
    private String              id;
    private Map<String, String> label;       // localized "de" -> "German label"
    private boolean             isMandatory;
    private String              isVisible;   // "alwaysHidden", "show" or "optional"
    private boolean             isLegacy;
    private String              scriptedProperties;
    
    public Controls() {
        this.type = LEGACY_CONTROL;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setIsMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsVisible(String isVisible) {
        this.isVisible = isVisible;
    }
    public String getIsVisible() {
        return isVisible;
    }
    public void setScriptedProperties(String scriptedProperties) {
        this.scriptedProperties = scriptedProperties;
    }
    public String getScriptedProperties() {
        return scriptedProperties;
    }
    public void setLabel(Map<String, String> label) {
        this.label = label;
    }
    public Map<String, String> getLabel() {
        return label;
    }

    public void setIsLegacy(boolean isLegacy) {
        this.isLegacy = isLegacy;
    }

    public boolean getIsLegacy() {
        return isLegacy;
    }
}
