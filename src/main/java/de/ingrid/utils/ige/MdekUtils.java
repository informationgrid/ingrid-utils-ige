package de.ingrid.utils.ige;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;

/**
 * Class encapsulating utility methods.
 * 
 * @author Martin
 */
public class MdekUtils {

	private static final Logger LOG = Logger.getLogger(MdekUtils.class);

	private final static SimpleDateFormat timestampFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private final static SimpleDateFormat displayDateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	private final static SimpleDateFormat displayDateTimeFormatter = new SimpleDateFormat("dd.MM.yyyy/HH:mm");

	/** Entry IDs in syslist OBJ_SERV_TYPE (5100) */
	public final static Integer OBJ_SERV_TYPE_CSW = 1;
	public final static Integer OBJ_SERV_TYPE_WMS = 2;
	public final static Integer OBJ_SERV_TYPE_WFS = 3;
	public final static Integer OBJ_SERV_TYPE_WCTS = 4;

	/** Entry ID of Ansprechpartner (former 'Auskunft') in syslist OBJ_ADR_TYPE (505) */
	public final static Integer OBJ_ADR_TYPE_POINT_OF_CONTACT_ID = 7;

	/** Entry ID of PHONE in syslist COMM_TYPE (4430) */
	public final static Integer COMM_TYPE_PHONE = 1;
	/** Entry ID of EMAIL in syslist COMM_TYPE (4430) */
	public final static Integer COMM_TYPE_EMAIL = 3;
	/** Free Entry VALUE of EMAIL for point of contact. Stored with key -1 in communication table ! */
	public final static String COMM_VALUE_EMAIL_POINT_OF_CONTACT = "emailPointOfContact";

	/** Entry ID "nicht evaluiert" in syslist OBJ_CONFORMITY (6000), is default */
	public final static Integer OBJ_CONFORMITY_NOT_EVALUATED = 3;
	/** "INSPIRE Richtline"-"Specification" String in ObjectConformity entity, is default */
	public final static Integer OBJ_CONFORMITY_SPECIFICATION_INSPIRE_KEY = 13;


	public final static String YES = "Y";
	public final static String NO = "N";
	public final static int YES_INTEGER = 1;
	public final static int NO_INTEGER = 0;

	public final static String LANGUAGE_SHORTCUT_DE = "de";
	public final static String LANGUAGE_SHORTCUT_EN = "en";
	public final static String[] LANGUAGES_SHORTCUTS = new String[] { LANGUAGE_SHORTCUT_DE, LANGUAGE_SHORTCUT_EN };

	/** Type of user operation */
	public enum UserOperation {
		NEW,
		EDITED,
		DELETED;
	}

	/** Syslists enum also encapsulating ID of syslist and metadata like bean class name and props of bean where syslist is applied ...) */
	public enum MdekSysList implements IMdekEnum {
		FREE_ENTRY(-1, "entry id if value is free entry"),
		/** specialRef='505' */
		OBJ_ADR_TYPE(505, "T012ObjAdr:type:specialName"),
		VERTICAL_EXTENT_VDATUM(101, "T01Object:verticalExtentVdatumKey:verticalExtentVdatumValue"),
//		VERTICAL_EXTENT_UNIT(102, "T01Object:verticalExtentUnit"),
//		CHARACTER_SET(510, "T01Object:datasetCharacterSet:metadataCharacterSet"),
//		HIERARCHY_LEVEL(525, "T011ObjGeo:hierarchyLevel"),
//		VECTOR_TOPOLOGY_LEVEL(528, "T011ObjGeo:vectorTopologyLevel"),
//		SPATIAL_REP(526, "T011ObjGeoSpatialRep:type"),
//		GEOMETRIC_OBJECT_TYPE(515, "T011ObjGeoVector:geometricObjectType"),
//		DATASET_REFERENCE(502, "T0113DatasetReference:type"),
		/** specialRef='2010' */
		OBJ_ADR_TYPE_SPECIAL(2010, "T012ObjAdr:type:specialName"),
		OBJ_REFERENCE(2000, "ObjectReference:specialRef:specialName"),
		OBJ_GEO_REFERENCESYSTEM(100, "SpatialSystem:referencesystemKey:referencesystemValue"),
		OBJ_TYPES_CATALOGUE(3535, "ObjectTypesCatalogue:titleKey:titleValue"),
		OBJ_GEO_SYMC(3555, "T011ObjGeoSymc:symbolCatKey:symbolCatValue"),
		OBJ_LITERATURE_TYPE(3385, "T011ObjLiterature:typeKey:typeValue"),
		/** T011ObjServ for class 3 ! */
		OBJ_SERV_TYPE(5100, "T011ObjServ:typeKey:typeValue"),
		/** T011ObjServ.type=1/CSW */
		OBJ_SERV_OPERATION_CSW(5105, "T011ObjServOperation:nameKey:nameValue"),
		/** T011ObjServ.type=2/WMS */
		OBJ_SERV_OPERATION_WMS(5110, "T011ObjServOperation:nameKey:nameValue"),
		/** T011ObjServ.type=3/WFS */
		OBJ_SERV_OPERATION_WFS(5120, "T011ObjServOperation:nameKey:nameValue"),
		/** T011ObjServ.type=4/WCTS */
		OBJ_SERV_OPERATION_WCTS(5130, "T011ObjServOperation:nameKey:nameValue"),
		OBJ_SERV_OPERATION_PLATFORM(5180, "T011ObjServOpPlatform:platformKey:platformValue"),
		OBJ_SERV_TYPE2(5200, "T011ObjServType:servTypeKey:servTypeValue"),
		/** T011ObjServ for class 6 ! */
		OBJ_SERV_TYPE_CLASS_6(5300, "T011ObjServ:typeKey:typeValue"),
		INFO_IMPART(1370, "T014InfoImpart:impartKey:impartValue"),
		LEGIST(1350, "T015Legist:legistKey:legistValue"),
		URL_REF_SPECIAL(2000, "T017UrlRef:specialRef:specialName"),
		MEDIA_OPTION_MEDIUM(520, "T0112MediaOption:mediumName"),
		SPATIAL_REF_VALUE(1100, "SpatialRefValue:nameKey:nameValue"),
		AVAIL_FORMAT(1320, "T0110AvailFormat:formatKey:formatValue"),
		ADDRESS_VALUE(4300, "T02Address:addressKey:addressValue"),
		ADDRESS_TITLE(4305, "T02Address:titleKey:titleValue"),
		COMM_TYPE(4430, "T021Communication:commtypeKey:commtypeValue"),
		OBJ_CONFORMITY_DEGREE(6000, "ObjectConformity:degreeKey:degreeValue"),
		OBJ_CONFORMITY_SPECIFICATION(6005, "ObjectConformity:specificationKey:specificationValue"),
		OBJ_ACCESS(6010, "ObjectAccess:restrictionKey:restrictionValue"),
		OBJ_USE(6020, "ObjectUse:termsOfUseKey:termsOfUseValue"),
		OBJ_TOPIC_CAT(527, "T011ObjTopicCat:topicCategory"),
		/** type='I'(INSPIRE) -> INSPIRE Themen zur Verschlagwortung */
		INSPIRE_SEARCHTERM(6100, "SearchtermValue:entryId:term"),
		COUNTRY(6200, "T03Catalogue, T02Address:countryKey:countryValue"),
		OBJ_FORMAT_INSPIRE(6300, "ObjectFormatInspire:formatKey:formatValue"),
		/** dqElementId=109 */
		DQ_109_CompletenessComission(7109, "ObjectDataQuality:nameOfMeasureKey:nameOfMeasureValue"),
		/** dqElementId=112 */
		DQ_112_ConceptualConsistency(7112, "ObjectDataQuality:nameOfMeasureKey:nameOfMeasureValue"),
		/** dqElementId=113 */
		DQ_113_DomainConsistency(7113, "ObjectDataQuality:nameOfMeasureKey:nameOfMeasureValue"),
		/** dqElementId=114 */
		DQ_114_FormatConsistency(7114, "ObjectDataQuality:nameOfMeasureKey:nameOfMeasureValue"),
		/** dqElementId=115 */
		DQ_115_TopologicalConsistency(7115, "ObjectDataQuality:nameOfMeasureKey:nameOfMeasureValue"),
		/** dqElementId=120 */
		DQ_120_TemporalConsistency(7120, "ObjectDataQuality:nameOfMeasureKey:nameOfMeasureValue"),
		/** dqElementId=125 */
		DQ_125_ThematicClassificationCorrectness(7125, "ObjectDataQuality:nameOfMeasureKey:nameOfMeasureValue"),
		/** dqElementId=126 */
		DQ_126_NonQuantitativeAttributeAccuracy(7126, "ObjectDataQuality:nameOfMeasureKey:nameOfMeasureValue"),
		/** dqElementId=127 */
		DQ_127_QuantitativeAttributeAccuracy(7127, "ObjectDataQuality:nameOfMeasureKey:nameOfMeasureValue"),
		LANGUAGE(99999999, "T03Catalogue, T01Object:languageKey:languageValue");

		MdekSysList(Integer dbValue, String description) {
			this.dbValue = dbValue;
			this.description = description;
		}
		/** returns syslist ID */
		public Integer getDbValue() {
			return dbValue;
		}
		/** Get bean class name and props of bean where syslist is applied ..
		 * NOTICE: Syslist metadata description in enum has to be set accordingly.
		 * @return String[]: 0=bean class name, 1=name key column, 2=name value column
		 */
		public String[] getMetadata() {
			return description.split(":");
		}
		/** returns ISO DQ_Element Id from Syslist Id (name of measure) */
		public int getDqElementId() {
			return dbValue - 7000;
		}
		/** returns Syslist Id (name of measure) of ISO DQ_Element Id */
		public static int getSyslistIdFromDqElementId(int dqElementId) {
			return dqElementId + 7000;
		}
		Integer dbValue;
		String description;
	}

	/** Type of entities. NOT STORED IN DATABASE !
	 * But implemented as IMdekEnum so we can use String (for arbitrary entities) and Enum for checking ! */
	public enum IdcEntityType implements IMdekEnum  {
		OBJECT("Object"),
		ADDRESS("Address");
		IdcEntityType(String entityName) {
			this.entityName = entityName;
		}
		public String getDbValue() {
			return entityName;
		}
		String entityName;
	}

	/** Different versions of IDC entities */
	public enum IdcEntityVersion {
		WORKING_VERSION,
		PUBLISHED_VERSION,
		ALL_VERSIONS;
	}

	/**
	 * Entities where user has write permission. Which entities to fetch ? Handles selection for the following pages !<br> 
	 * - WORK/RESPONSIBLE PAGE: "modified" Entities.<br> 
	 * - PORTAL QUICKLIST: quicklist to show in portal IGE entry page ! */
	public enum IdcWorkEntitiesSelectionType {
		/** WORK/RESPONSIBLE PAGE -> all expired entities where user is RESPONSIBLE. QUERIES PUBLISHED VERSION !!! */
		EXPIRED,
		/** WORK/RESPONSIBLE PAGE -> all modified entities where user is RESPONSIBLE or MOD-User */
		MODIFIED,
		/** WORK/RESPONSIBLE PAGE -> all entities in QA workflow where user is RESPONSIBLE or MOD-User or Assigner */
		IN_QA_WORKFLOW,
		/** WORK/RESPONSIBLE PAGE -> all entities where a spatial relation is expired and user is RESPONSIBLE. */
		SPATIAL_REF_EXPIRED,
		/** PORTAL QUICKLIST -> MODIFIED or REASSIGNED entities where user is MOD-User or Assigner */
		PORTAL_QUICKLIST,
	}

	/** QA PAGE:: Entities where user is QA. Which entities to fetch ? */
	public enum IdcQAEntitiesSelectionType {
		/** all expired entities where user is QA. QUERIES PUBLISHED VERSION !!! */
		EXPIRED,
		/** all entities where user is QA and a spatial relation is expired */
		SPATIAL_REF_EXPIRED,
	}

	/** STATISTICS PAGE: What kind of statistics ? */
	public enum IdcStatisticsSelectionType {
		/** analysis how many entities per class and work state */
		CLASSES_AND_STATES,
		/** analysis of free search terms */
		SEARCHTERMS_FREE,
		/** analysis of thesaurus search terms */
		SEARCHTERMS_THESAURUS;
	}

	/** Which sub entities to select ? */
	public enum IdcChildrenSelectionType {
		/** select only children where publish id doesn't fit to publish id of "root" node ! */
		PUBLICATION_CONDITION_PROBLEMATIC,
	}

	/** e.g. How to order fetched entities ? asc or desc is set with separate flag. */
	public enum IdcEntityOrderBy {
		/** order by class of entity */
		CLASS,
		/** order by "name", e.g. title or lastname ... */
		NAME,
		/** order by date, e.g. mod-time or assign-time ... */
		DATE,
		/** order by user, e.g. mod-user or responsible-user ... */
		USER,
		/** order by state, e.g. WorkState or ExpiryState ... */
		STATE,
	}

	/**
	 * Kind of csv data to fetch ? */
	public enum CsvRequestType {
		/** "Gesamtkatalog: Adresse loeschen" Tab 1 -> all Objects where address is referenced ! */
		OBJECTS_OF_ADDRESS,
		/** "Gesamtkatalog: Adresse loeschen" Tab 2 -> all Objects where address is responsible user ! */
		OBJECTS_OF_RESPONSIBLE_USER,
		/** "Gesamtkatalog: Adresse loeschen" Tab 3 -> all Addresses where address is responsible user ! */
		ADDRESSES_OF_RESPONSIBLE_USER,
	}

	/** expiry state (object/address_metadata.expiry_state) */
	public enum ExpiryState implements IMdekEnum {
		INITIAL(0),
		TO_BE_EXPIRED(10),
		EXPIRED(20);

		ExpiryState(Integer dbValue) {
			this.dbValue = dbValue;
		}
		public Integer getDbValue() {
			return dbValue;
		}
		/**
		 * Is this expiry state "more urgent" than the passed state ? e.g.<br>
		 * EXPIRED.isHigher(TO_BE_EXPIRED) -> true<br>
		 * EXPIRED.isHigher(INITIAL) -> true<br>
		 * EXPIRED.isHigher(EXPIRED) -> false<br>
		 */
		public boolean isHigher(ExpiryState inState) {
			if (this.getDbValue() > inState.getDbValue()) {
				return true;
			}
			return false;
		}
		Integer dbValue;
	}

	/** WorkState of entities (t01_object /t02_address.work_state) */
	public enum WorkState implements IMdekEnum {
		VEROEFFENTLICHT("V", "ver\u00f6ffentlicht"),
		IN_BEARBEITUNG("B", "in Bearbeitung"),
		QS_UEBERWIESEN("Q", "an Qualit\u00e4tssicherung zugewiesen"),
		QS_RUECKUEBERWIESEN("R", "von Qualit\u00e4tssicherung r\u00fcck\u00fcberwiesen");

		WorkState(String dbValue, String description) {
			this.dbValue = dbValue;
			this.description = description;
		}
		public String getDbValue() {
			return dbValue;
		}
		public String toString() {
			return description;
		}
		String dbValue;
		String description;
	}

	/** Type of spatial reference (spatial_ref_value.type) */
	public enum SpatialReferenceType implements IMdekEnum {
		FREI("F", "Freier Raumbezug"),
		GEO_THESAURUS("G", "Geo-Thesaurus");

		SpatialReferenceType(String dbValue, String description) {
			this.dbValue = dbValue;
			this.description = description;
		}
		public String getDbValue() {
			return dbValue;
		}
		public String toString() {
			return description;
		}
		public static boolean isThesaurusType(SpatialReferenceType inType) {
			if (inType == GEO_THESAURUS) {
				return true;
			}
			return false;
		}
		String dbValue;
		String description;
	}

	/** Type of searchterm (searchterm_value.type) */
	public enum SearchtermType implements IMdekEnum {
		FREI("F", "Freier Term"),
		UMTHES("T", "UMTHES Term"),
		GEMET("G", "GEMET Term"),
		INSPIRE("I", "INSPIRE Thema");

		SearchtermType(String dbValue, String description) {
			this.dbValue = dbValue;
			this.description = description;
		}
		public String getDbValue() {
			return dbValue;
		}
		public String toString() {
			return description;
		}
		public static boolean isThesaurusType(SearchtermType inType) {
			if (inType == UMTHES || inType == GEMET) {
				return true;
			}
			return false;
		}
		String dbValue;
		String description;
	}

	/** Publish condition (t01_object.publish_id) */
	public enum PublishType implements IMdekEnum {
		INTERNET(1, "Internet"),
		INTRANET(2, "Intranet"),
		AMTSINTERN(3, "amtsintern");

		PublishType(Integer dbValue, String description) {
			this.dbValue = dbValue;
			this.description = description;
		}
		public Integer getDbValue() {
			return dbValue;
		}
		public String toString() {
			return description;
		}
		/**
		 * Is this type "broader" or "equal" the passed type ? e.g.<br>
		 * INTERNET.includes(INTERNET) -> true<br>
		 * INTERNET.includes(INTRANET) -> true<br>
		 * INTRANET.includes(INTERNET) -> false<br>
		 */
		public boolean includes(PublishType inType) {
			if (this.getDbValue() <= inType.getDbValue()) {
				return true;
			}
			return false;
		}
		Integer dbValue;
		String description;
	}

	/** Type of object (object class)(t01_object.obj_class) */
	public enum ObjectType implements IMdekEnum {
		ORGANISATION(0, "Organisationseinheit/Fachaufgabe"),
		GEO_INFORMATION(1, "Geo-Information/Karte"),
		DOKUMENT(2, "Dokument/Bericht/Literatur"),
		GEO_DIENST(3, "Geodatendienst"),
		VORHABEN(4, "Vorhaben/Projekt/Programm"),
		DATENSAMMLUNG(5, "Datensammlung/Datenbank"),
		INFOSYSTEM_DIENST(6, "Informationssystem/Dienst/Anwendung"),
		OPEN_DATA(7, "Open Data");

		ObjectType(Integer dbValue, String description) {
			this.dbValue = dbValue;
			this.description = description;
		}
		public Integer getDbValue() {
			return dbValue;
		}
		public String toString() {
			return description;
		}
		Integer dbValue;
		String description;
	}

	/** Type of VISIBLE addresses (address class) (t02_address.adr_type) 
	 * ATTENTION: There are hidden address types, e.g. IGEUser with type 100, see AddressType.getHiddenAddressTypeIGEUser() */
	public enum AddressType implements IMdekEnum {
		INSTITUTION(0, "Institution"),
		EINHEIT(1, "Einheit"),
		PERSON(2, "Person"),
		FREI(3, "Freie Adresse");

		AddressType(Integer dbValue, String description) {
			this.dbValue = dbValue;
			this.description = description;
		}
		public Integer getDbValue() {
			return dbValue;
		}
		public String toString() {
			return description;
		}
		/** The address type stored for IGE users. These addresses will not be visible when editing, ... !
		 * NOTICE: DO NOT ADD AS ENUM VALUE ! All enumeration values are iterated in statistics !
		 */
		public static Integer getHiddenAddressTypeIGEUser() {
			return 100;
		}
		/** The virtual parent UUID of all IGE users stored in AddressNode.fk_addr_uuid !
		 * Thus we avoid interpretation as real top node (where parent is null) and mark user nodes ! */
		public static String getIGEUserParentUuid() {
			return "IGE_USER";
		}
		/** Return the HQL to add into where clause if the AddressNode should NOT query IGE USERS !
		 * Pass alias of AddressNode used in query.<br>
		 * NOTICE: contains space at front and end ! */
		public static String getHQLExcludeIGEUsersViaNode(String aliasAddressNode) {
			String columnToCheck = aliasAddressNode + ".fkAddrUuid";
			return " (" + columnToCheck + " IS NULL OR " +
				columnToCheck + " != '" + AddressType.getIGEUserParentUuid() + "') ";
		}
		/** Return the HQL to add into where clause if the T02Address should NOT query IGE USERS !
		 * Pass alias of T02Address used in query.<br>
		 * NOTICE: contains space at front and end ! */
		public static String getHQLExcludeIGEUsersViaAddress(String aliasT02Address) {
			String columnToCheck = aliasT02Address + ".adrType";
			return " " + columnToCheck + " != " + AddressType.getHiddenAddressTypeIGEUser() + " ";
		}
		Integer dbValue;
		String description;
	}

/*
	private static MdekUtils myInstance;

	public static synchronized MdekUtils getInstance() {
		if (myInstance == null) {
	        myInstance = new MdekUtils();
	      }
		return myInstance;
	}

	private MdekUtils() {}
*/

	/** Check whether the UUID is a "normal" object/address uuid.
	 * Returns false if uuid is null or a special placeholder 
	 * (e.g. parent uuid of IGE USER, see AddressType.getIGEUserParentUuid)
	 */
	public static boolean isValidUuid(String uuid) {
		if (uuid == null ||
			AddressType.getIGEUserParentUuid().equals(uuid))
		{
			return false;
		}
		
		return true;
	}

	/** Format database timestamp to display date. */
	public static String timestampToDisplayDate(String yyyyMMddHHmmssSSS) {
		try {
			Date in = timestampFormatter.parse(yyyyMMddHHmmssSSS);
			String out = displayDateFormatter.format(in);
			return out;
		} catch (Exception ex){
			if (yyyyMMddHHmmssSSS != null && yyyyMMddHHmmssSSS.length() > 0) {
				LOG.warn("Problems parsing timestamp from database: " + yyyyMMddHHmmssSSS, ex);				
			}
			return "";
		}
	}
	/** Format date to database timestamp. */
	public static String dateToTimestamp(Date date) {
		try {
			String out = timestampFormatter.format(date);
			return out;
		} catch (Exception ex){
			LOG.warn("Problems formating date to timestamp: " + date, ex);
			return "";
		}
	}
	/** Format milliseconds since January 1, 1970, 00:00:00 GMT to display date/time. */
	public static String millisecToDisplayDateTime(String millisec) {
		try {
			Date in = new Date(Long.valueOf(millisec));
			String out = displayDateTimeFormatter.format(in);
			return out;
		} catch (Exception ex){
			if (millisec != null && millisec.length() > 0) {
				LOG.warn("Problems parsing millisec: " + millisec, ex);
			}
			return "";
		}
	}

	/**
	 * Processes given string. Returns null if String is empty or only whitespaces etc.
	 * @param param the string to process
	 * @return the processed string
	 */
	public static String processStringParameter(String param) {
		if (param != null) {
			param = param.trim();

			// replace special characters inside string
			param = param.replaceAll ("\\n", " ");
			param = param.replaceAll ("\\t", " ");

			if (param.length() == 0) {
				param = null;
			}
		}
		
		return param;
	}

	/** Checks whether given String "has content", meaning is NOT null/empty and NOT whitespace.
	 * @param stringToCheck
	 * @return true=has content<br>false=no content (null or empty or only whitespaces ...)
	 */
	public static boolean hasContent(String stringToCheck) {
		if (stringToCheck == null || stringToCheck.trim().length() == 0) {
			return false;
		}
		return true;
	}
	/** Appends the given string. Separator is appended before, if length of base > 0. */
	public static void appendWithSeparator(StringBuilder appendHere, String separator, String stringToAppend) {
		if (appendHere.length() > 0) {
			appendHere.append(separator);
		}
		appendHere.append(stringToAppend);
	}


	/** Compares two strings and returns true if equal. Handles null (null equals null but not "") */
	public static boolean isEqual(String string1, String string2) {
		String tmp1 = (string1 == null) ? "" : string1;
		String tmp2 = (string2 == null) ? "" : string2;
		
		return tmp1.equals(tmp2);
	}
	/** Compares two Integers and returns true if equal. Handles null -> null.equals(null) is true */
	public static boolean isEqual(Integer int1, Integer int2) {
		Integer tmp1 = (int1 == null) ? -1 : int1;
		Integer tmp2 = (int2 == null) ? -1 : int2;
		
		return tmp1.equals(tmp2);
	}
	/** Compares two Doubles and returns true if equal. Handles null -> null.equals(null) is true */
	public static boolean isEqual(Double val1, Double val2) {
		Double tmp1 = (val1 == null) ? 0.0 : val1;
		Double tmp2 = (val2 == null) ? 0.0 : val2;
		
		return tmp1.equals(tmp2);
	}


	/** Deompress zipped byte array to String. */
	public static String decompressZippedByteArray(byte[] zippedData) throws IOException {
		ByteArrayOutputStream baos = decompress(new ByteArrayInputStream(zippedData));
		return baos.toString("UTF-8");
	}

	// Decompress (unzip) data on InputStream (has to contain zipped data) and write it to a ByteArrayOutputStream
	private static ByteArrayOutputStream decompress(InputStream is) throws IOException {
		GZIPInputStream gzin = new GZIPInputStream(new BufferedInputStream(is));
		ByteArrayOutputStream baout = new ByteArrayOutputStream();
		BufferedOutputStream out = new BufferedOutputStream(baout);

		final int BUFFER = 2048;
		int count;
		byte data[] = new byte[BUFFER];
		while((count = gzin.read(data, 0, BUFFER)) != -1) {
		   out.write(data, 0, count);
		}

		out.close();
		return baout;
	}

	/** Compress string to zipped byte array. */
	public static byte[] compressString(String stringToZip) throws IOException {
		ByteArrayOutputStream baos = compress(new ByteArrayInputStream(stringToZip.getBytes("UTF-8")));
		return baos.toByteArray();
	}

	// Decompress (unzip) data on InputStream (has to contain zipped data) and write it to a ByteArrayOutputStream
	private static ByteArrayOutputStream compress(InputStream is) throws IOException {
		BufferedInputStream in = new BufferedInputStream(is);
		ByteArrayOutputStream baout = new ByteArrayOutputStream();
		GZIPOutputStream gzout = new GZIPOutputStream(new BufferedOutputStream(baout));

		final int BUFFER = 2048;
		int count;
		byte data[] = new byte[BUFFER];
		while((count = in.read(data, 0, BUFFER)) != -1) {
			gzout.write(data, 0, count);
		}

		gzout.close();
		return baout;
	}
}
