package net.sf.anathema.character.lunar.beastform.model;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.library.trait.IDefaultTrait;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;

public class BeastformTraitCollection extends AbstractTraitCollection implements IBeastformTraitCollection {

  private final Map<ITraitType, IBeastformAttribute> attributesByType = new HashMap<ITraitType, IBeastformAttribute>();

  public void addBeastFormAttribute(IBeastformAttribute attribute) {
    addTrait(attribute.getTrait());
    attributesByType.put(attribute.getTrait().getType(), attribute);
  }

  public IBeastformAttribute getDeadlyBeastmanAttribute(ITraitType traitType) {
    return attributesByType.get(traitType);
  }

  public int getTraitPointCost(IDefaultTrait trait) {
    Ensure.ensureArgumentTrue("Trait not in collection.", contains(trait.getType())); //$NON-NLS-1$
    return ((BeastformAttribute) trait).getPointCost();
  }
}