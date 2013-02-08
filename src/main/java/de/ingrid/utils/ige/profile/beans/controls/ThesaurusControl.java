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
