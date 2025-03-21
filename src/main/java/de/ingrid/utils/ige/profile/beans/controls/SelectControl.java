/*
 * **************************************************-
 * InGrid Utils IGE
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.utils.ige.profile.beans.controls;

import java.util.List;
import java.util.Map;

public class SelectControl extends ExtendedControls {
    private Map<String,List<OptionEntry>> options;
    private boolean allowFreeEntries; 
    private String useSyslist; 
    
    public SelectControl() {
        this.setType(SELECT_CONTROL);
    }

    public Map<String,List<OptionEntry>> getOptions() { return options; }
    
    public void setOptions(Map<String,List<OptionEntry>> options) { this.options = options; }
    
    public String getOptionsAsString(String lang) {
        String optionsString = "";
        List<OptionEntry> optionList = this.options.get(lang);
        if (optionList == null || optionList.isEmpty())
            return "";
        
        for (OptionEntry option : optionList) {
            optionsString += "'"+option.getValue() + "',";
        }
        
        return optionsString.substring(0, optionsString.length() - 1);
    }
    
    public String getOptionsAsStringWithId(String lang) {
        String optionsString = "";
        List<OptionEntry> optionList = this.options.get(lang);
        if (optionList == null || optionList.isEmpty())
            return "";
        
        for (OptionEntry option : optionList) {
            optionsString += "{id:'"+option.getId()+"', value:'"+option.getValue() + "'},";
        }
        
        return optionsString.substring(0, optionsString.length() - 1);
    }
    
    public String getIdsAsString(String lang) {
        String optionsString = "";
        List<OptionEntry> optionList = this.options.get(lang);
        if (optionList == null || optionList.isEmpty())
            return "";
        
        for (OptionEntry option : optionList) {
            optionsString += "'"+option.getId()+"',";
        }
        
        return optionsString.substring(0, optionsString.length() - 1);
    }

    public void setAllowFreeEntries(boolean allowFreeEntries) {
        this.allowFreeEntries = allowFreeEntries;
    }

    public boolean getAllowFreeEntries() {
        return allowFreeEntries;
    }

    public String getUseSyslist() {
        return useSyslist;
    }

    public void setUseSyslist(String useSyslist) {
        this.useSyslist = useSyslist;
    }
}
