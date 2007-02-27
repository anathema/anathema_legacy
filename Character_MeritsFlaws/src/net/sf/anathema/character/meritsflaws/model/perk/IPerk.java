package net.sf.anathema.character.meritsflaws.model.perk;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.meritsflaws.model.perk.cost.IFixedPerkCost;
import net.sf.anathema.character.meritsflaws.presenter.IPerkVisitor;

public interface IPerk extends IQuality {

  public void accept(IPerkVisitor visitor);

  public Integer[] getPointValues(IBasicCharacterData characterData);

  public boolean isLegalFor(ICharacterType characterType);

  public void setSpecialFixedCost(IFixedPerkCost cost);

  public PerkCategory getCategory();
}