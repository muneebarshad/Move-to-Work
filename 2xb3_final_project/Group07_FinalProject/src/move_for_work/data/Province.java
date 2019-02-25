package move_for_work.data;

public enum Province {
	ALBERTA,
	BRITISH_COLUMBIA,
	CANADA,
	MANITOBA,
	NEW_BRUNSWICK,
	NEWFOUNDLAND_LABRADOR,
	NORTHWEST_TERRITORIES,
	NOVA_SCOTIA,
	NUNAVUT,
	ONTARIO,
	PRINCE_EDWARD_ISLAND,
	QUEBEC,
	SASKATCHEWAN,
	YUKON,
	ERROR;
	
	public static Province getProvince(String name) {
		switch(name) {
		case "Alberta":						return ALBERTA;
		case "British Columbia":			return BRITISH_COLUMBIA;
		case "Canada":						return CANADA;
		case "Manitoba":					return MANITOBA;
		case "New Brunswick":				return NEW_BRUNSWICK;
		case "Newfoundland and Labrador":	return NEWFOUNDLAND_LABRADOR;
		case "Northwest Territories":		return NORTHWEST_TERRITORIES;
		case "Nova Scotia":					return NOVA_SCOTIA;
		case "Nunavut":						return NUNAVUT;
		case "Ontario":						return ONTARIO;
		case "Prince Edward Island":		return PRINCE_EDWARD_ISLAND;
		case "Quebec":						return QUEBEC;
		case "Saskatchewan":				return SASKATCHEWAN;
		case "Yukon":						return YUKON;
		default:							return ERROR;
		}
	}
}

