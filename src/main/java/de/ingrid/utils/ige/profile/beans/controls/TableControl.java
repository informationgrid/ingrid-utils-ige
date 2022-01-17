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
package de.ingrid.utils.ige.profile.beans.controls;

import java.util.ArrayList;
import java.util.List;

public class TableControl extends ExtendedControls {
    
    private List<TableColumn> columns = new ArrayList<TableColumn>();
    private int numTableRows;
    
    public TableControl() {
        this.setType(GRID_CONTROL);
    }

    public void setColumns(List<TableColumn> columns) {this.columns = columns;}
    public List<TableColumn> getColumns() {return columns;}

    public void setNumTableRows(int numRows) {
        this.numTableRows = numRows;
    }

    public int getNumTableRows() {
        return numTableRows;
    }
    
    /**
     * A table has no index name since only its columns can have one!
     */
    @Override
    public String getIndexName() {
        return null;
    }
}
