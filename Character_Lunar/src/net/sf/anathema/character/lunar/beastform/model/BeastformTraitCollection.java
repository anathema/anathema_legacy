package net.sf.anathema.character.lunar.beastform.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;

import java.util.HashMap;
import java.util.Map;

public class BeastformTraitCollection extends AbstractTraitCollection implements IBeastformTraitCollection {

  private final Map<ITraitType, IBeastformAttribute> attributesByType = new HashMap<ITraitType, IBeastformAttribute>();

  public void addBeastFormAttribute(IBeastformAttribute attribute) {
    addTrait(attribute.getTrait());
    attributesByType.put(attribute.getTrait().getType(), attribute);
  }

  @Override
  public IBeastformAttribute getDeadlyBeastmanAttribute(ITraitType traitType) {
    return attributesByType.get(traitType);
  }
}