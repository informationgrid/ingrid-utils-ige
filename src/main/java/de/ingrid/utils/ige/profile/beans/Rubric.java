/*
 * **************************************************-
 * InGrid Utils IGE
 * ==================================================
 * Copyright (C) 2014 - 2022 wemove digital solutions GmbH
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
import java.util.Map;

import de.ingrid.utils.ige.profile.beans.controls.Controls;

public class Rubric {
    private String              id;
    private Map<String, String> label;
    private Map<String, String> helpMessage;
    private boolean             isLegacy;
    private List<Controls>      controls = new ArrayList<Controls>();
    
    public void setId(String id) {this.id = id;}

    public String getId() {return id;}

    public void setLabel(Map<String, String> label) {this.label = label;}

    public Map<String, String> getLabel() {return label;}

    public void setHelpMessage(Map<String, String> helpMessage) {this.helpMessage = helpMessage;}

    public Map<String, String> getHelpMessage() {return helpMessage;}

    public void setControls(List<Controls> controls) {this.controls = controls;}

    public List<Controls> getControls() {return controls;}

    public void setIsLegacy(boolean isLegacy) {this.isLegacy = isLegacy;}

    public boolean getIsLegacy() {return isLegacy;}
}
