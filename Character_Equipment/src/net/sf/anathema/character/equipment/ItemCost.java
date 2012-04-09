package net.sf.anathema.character.equipment;

public class ItemCost {
	private String costType;
	private int costValue;
	
	public ItemCost(String type, int value) {
		this.costType = type;
		this.costValue = value;
	}

	public String getType() {
		return costType;
	}
	
	public int getValue() {
		return costValue;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof ItemCost) {
			ItemCost otherCost = (ItemCost) obj;
			return costType.equals(otherCost.costType) && costValue == otherCost.costValue;
		}
		return false;
	}
	
	public String toString() {
		if (costType == null || costValue == 0) {
			return "";
		}
		String base = costType + " ";
		if (costValue == 6) {
			base += "N/A";
		} else {
			for (int i = 0; i != costValue; i++) {
				base += '*';
			}
		}
		return base;
	}
}
