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

import java.util.Map;


/**
 * This control generally defines a table with a button where new 
 * thesaurus entries can be added.
 * @author Andre
 *
 */
public class ThesaurusControl extends ExtendedControls {
    
    private int    numTableRows;
    private String thesaurusUrl;
    private Map<String, String> linkLabel;
    
    public ThesaurusControl() {
        this.setType(THESAURUS_CONTROL);
    }

    public void setNumTableRows(int numRows) {
        this.numTableRows = numRows;
    }

    public int getNumTableRows() {
        return numTableRows;
    }
    
    public String getThesaurusUrl() {
        return thesaurusUrl;
    }

    public void setThesaurusUrl(String thesaurusUrl) {
        this.thesaurusUrl = thesaurusUrl;
    }
    
    public Map<String, String> getLinkLabel() {
        return linkLabel;
    }

    public void setLinkLabel(Map<String, String> linkLabel) {
        this.linkLabel = linkLabel;
    }
    
    /**
     * A table has no index name since only its columns can have one!
     */
    @Override
    public String getIndexName() {
        return null;
    }
}
