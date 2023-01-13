/*
 * **************************************************-
 * InGrid Utils IGE
 * ==================================================
 * Copyright (C) 2014 - 2023 wemove digital solutions GmbH
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
package de.ingrid.utils.ige.profile;

import java.util.List;

import org.apache.log4j.Logger;

import de.ingrid.utils.ige.profile.beans.ProfileBean;
import de.ingrid.utils.ige.profile.beans.Rubric;
import de.ingrid.utils.ige.profile.beans.controls.Controls;

/**
 * Helper class for manipulating profile. USED IN IMPORTER !!!!!!!
 * @author martin
 */
public class MdekProfileUtils {

	private final static Logger log = Logger.getLogger(MdekProfileUtils.class);
	
	/** Returns null if rubric not found */
	public static Rubric findRubric(ProfileBean profileBean, String rubricId) {
		Rubric retValue = null;

        for (Rubric rubric : profileBean.getRubrics()) {
			if (rubricId.equals(rubric.getId())) {
				retValue = rubric;
				break;
			}
        }

		return retValue;
	}

	/** Returns null if rubric not found */
	public static Integer findRubricIndex(ProfileBean profileBean, String rubricId) {
		Integer retValue = null;

		List<Rubric> rubrics = profileBean.getRubrics();
		for (int i=0; i < rubrics.size(); i++) {
			if (rubricId.equals(rubrics.get(i).getId())) {
				retValue = i;
				break;
			}
		}
		
		return retValue;
	}

	/** Returns null if control not found */
	public static Rubric findRubricOfControl(ProfileBean profileBean, String controlId) {
		Rubric retValue = null;

        for (Rubric rubric : profileBean.getRubrics()) {
            for (Controls control : rubric.getControls()) {
    			if (controlId.equals(control.getId())) {
    				retValue = rubric;
    				break;
    			}
            }
        }

		return retValue;
	}

	/** Returns null if rubric not found */
	public static Rubric removeRubric(ProfileBean profileBean, String rubricId) {
		Rubric retValue = null;

		int index = findRubricIndex(profileBean, rubricId);
		if (index > -1) {
			retValue = profileBean.getRubrics().remove(index);
		}
		
		return retValue;
	}

	/** Add given rubric add given position (index, starts at 0)  */
	public static void addRubric(ProfileBean profileBean, Rubric rubric, int index) {
		profileBean.getRubrics().add(index, rubric);
	}

	/** Returns null if control not found. */
	public static Controls findControl(ProfileBean profileBean, String controlId) {
		Controls retValue = null;

        for (Rubric rubric : profileBean.getRubrics()) {
            for (Controls control : rubric.getControls()) {
    			if (controlId.equals(control.getId())) {
    				retValue = control;
    				break;
    			}
            }
        }

		return retValue;
	}

	/** Determine index of control in given rubric of control. Returns null if control not found */
	public static Integer findControlIndex(ProfileBean profileBean, Rubric rubric, String controlId) {
		Integer retValue = null;

		List<Controls> controls = rubric.getControls();
		for (int i=0; i < controls.size(); i++) {
			if (controlId.equals(controls.get(i).getId())) {
				retValue = i;
				break;
			}
		}
	
		return retValue;
	}

	/** Returns null if control not found */
	public static Controls removeControl(ProfileBean profileBean, String controlId) {
		Controls retValue = null;

		Rubric rubric = findRubricOfControl(profileBean, controlId);
		if (rubric != null) {
			int index = findControlIndex(profileBean, rubric, controlId);
			if (index > -1) {
				retValue = rubric.getControls().remove(index);
			}
		} else {
			log.info("Rubric or control was not found for control id: " + controlId);
		}

		return retValue;
	}

	/** Add given control in given rubric at given position (index, starts at 0) in given rubric */
	public static void addControl(ProfileBean profileBean, Controls control, Rubric rubric, int index) {
		rubric.getControls().add(index, control);
	}

	public static void addToScriptedProperties(Controls control, String jsToAdd) {
		String js = control.getScriptedProperties();
		if (js == null) {
			js = "";
		}
		control.setScriptedProperties(js + jsToAdd);
	}
	public static void removeAllScriptedProperties(Controls control) {
		control.setScriptedProperties("");
	}
	/** Replace a section of the scripted properties.
	 * @param control control containing js
	 * @param startTag part of existing js marking START of section to replace (will be removed), e.g. "// START 3.2.0 update"
	 * @param endTag part of existing js marking END of section to replace (will be removed), e.g. "// 3.2.0 END"
	 * @param jsNew new js to replace old js between startTag <-> endTag (old tags will be replaced)
	 * @return true=old part replaced, false=start or end tag not found, keep old js
	 */
	public static boolean replaceInScriptedProperties(Controls control, String startTag, String endTag, String jsNew) {
    	boolean propsReplaced = false;
    	String myScriptedProps = control.getScriptedProperties();
		if (myScriptedProps != null) {
			int startIndex = myScriptedProps.indexOf(startTag);
			if (startIndex != -1) {
				int endIndex = myScriptedProps.indexOf(endTag);
				if (endIndex != -1) {
					endIndex = endIndex + endTag.length();
					// replace old part with new part
					myScriptedProps = myScriptedProps.substring(0, startIndex)
							+ jsNew
		                    + myScriptedProps.substring(endIndex);
					control.setScriptedProperties(myScriptedProps);
					propsReplaced = true;
				}
			}			
		}
		return propsReplaced;
	}
}
