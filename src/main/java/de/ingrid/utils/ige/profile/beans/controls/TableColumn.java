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

public class TableColumn extends SelectControl {
    //private String             type;
    //private String             field;
    //private Map<String,String> label; // localized
    //private String             width;
    //private String             widthUnit;
    //private String             indexName;
    //private Map<String,List<String>> options; // localized
    
    // empty constructor for DWR!
    public TableColumn() {}
    
    public TableColumn(String type, String field, Map<String,String> label, String width, String indexName, Map<String,List<OptionEntry>> options) {
    //    this.type = type;
    //    this.field = field;
    //    this.label = label;
        setId(field);
        setType(type);
        setLabel(label);
        //this.width = width;
        setWidth(width);
        //this.setWidthUnit("px"); // use pixel for column width
        setWidthUnit("px");
        //this.indexName = indexName;
        setIndexName(indexName);
        //this.options = options;
        setOptions(options);
    }

    //public void setType(String type) {this.type = type;}
    //public String getType() {return type;}
    //public void setLabel(Map<String,String> label) {this.label = label;}
    //public Map<String,String> getLabel() {return label;}
    //public void setWidth(String width) {this.width = width;}
    //public String getWidth() {return width;}
    //public void setId(String id) {this.field = id;}
    //public String getId() {return field;}
    //public void setIndexName(String indexName) {this.indexName = indexName;}
    //public String getIndexName() {return indexName;}

    /*public void setWidthUnit(String widthUnit) {
        this.widthUnit = widthUnit;
    }

    public String getWidthUnit() {
        return widthUnit;
    }*/

    /*public void setOptions(Map<String,List<String>> options) {
        this.options = options;
    }

    public Map<String,List<String>> getOptions() {
        return options;
    }
    
    public String getOptionsAsString(String lang) {
        String optionsString = "";
        for (String option : this.options.get(lang)) {
            optionsString += "'"+option + "',";
        }
        
        return optionsString.substring(0, optionsString.length() - 1);
    }*/
}
