package net.sf.anathema.character.meritsflaws.model.perk.cost;

import net.sf.anathema.character.generic.IBasicCharacterData;

public interface IFixedPerkCost {

  public int[] getModifiedCost(IBasicCharacterData characterData);
}