package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.INatureType;

public class NatureModel implements ICharacterMotivationModel {
  private final ITypedDescription<INatureType> nature = new TypedDescription<INatureType>(null);

  public String getMainEntry() {
    return nature.getType().getName();
  }

  public boolean hasSeparateWillpowerCondition() {
    return true;
  }

  public String getWillpowerCondition() {
    return nature.getType().getWillpowerCondition();
  }
}