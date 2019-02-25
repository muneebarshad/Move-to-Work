package cas.finalproject.wt;

public enum Province {
	CANADA,
	NEWFOUNDLAND_LABRADOR,
	PRINCE_EDWARD_ISLAND,
	NOVA_SCOTIA,
	NEW_BRUNSWICK,
	QUEBEC,
	ONTARIO,
	MANITOBA,
	SASKATCHEWAN,
	ALBERTA,
	BRITISH_COLUMBIA,
	YUKON,
	NORTHWEST_TERRITORIES,
	NUNAVUT,
	ERROR;
	
	public static Province getProvince(String name) {
		switch(name) {
		case "Canada":						return CANADA;
		case "Newfoundland and Labrador":	return NEWFOUNDLAND_LABRADOR;
		case "Prince Edward Island":		return PRINCE_EDWARD_ISLAND;
		case "Nova Scotia":					return NOVA_SCOTIA;
		case "New Brunswick":				return NEW_BRUNSWICK;
		case "Quebec":						return QUEBEC;
		case "Ontario":						return ONTARIO;
		case "Manitoba":					return MANITOBA;
		case "Saskatchewan":				return SASKATCHEWAN;
		case "Alberta":						return ALBERTA;
		case "British Columbia":			return BRITISH_COLUMBIA;
		case "Yukon":						return YUKON;
		case "Northwest Territories":		return NORTHWEST_TERRITORIES;
		case "Nunavut":						return NUNAVUT;
		default:							return ERROR;
		}
	}
}
