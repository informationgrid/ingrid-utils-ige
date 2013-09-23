package de.ingrid.utils.ige.profile.beans.controls;

public class CheckboxControl extends ExtendedControls {
    private boolean checked;
    
    public CheckboxControl() {
        this.setType(CHECKBOX_CONTROL);
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
