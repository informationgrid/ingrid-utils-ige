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
package de.ingrid.utils.ige.profile.beans;

import java.util.ArrayList;
import java.util.List;
public class ProfileBean {
    private String name; 
    private List<String> languages = new ArrayList<String>();
    private List<Rubric> rubrics = new ArrayList<Rubric>();
    private String namespace;
    private String version;
    
    public ProfileBean() {
        languages.add("en");
        languages.add("de");
    }
    
    public void setRubrics(List<Rubric> rubrics) {this.rubrics = rubrics;}
    public List<Rubric> getRubrics() {return this.rubrics;}
    
    public void setLanguages(List<String> languages) {this.languages = languages;}
    public List<String> getLanguages() {return this.languages;}

    public void setName(String name) {this.name = name;}
    public String getName() {return name;}

    public void setNamespace(String namespace) {this.namespace = namespace;}
    public String getNamespace() {return namespace;}

    public void setVersion(String version) {this.version = version;}
    public String getVersion() {return version;}
}
