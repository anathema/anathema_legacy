package net.sf.anathema.character.lunar.beastform.model.gift;

import java.util.ArrayList;
import java.util.List;

public class Gifts {

	private final List<IGift> gifts = new ArrayList<IGift>();

	public void add(String id, int value) {
		gifts.add(new Gift(id, value));
	}

	public IGift[] asArray() {
		return gifts.toArray(new IGift[gifts.size()]);
	}
}