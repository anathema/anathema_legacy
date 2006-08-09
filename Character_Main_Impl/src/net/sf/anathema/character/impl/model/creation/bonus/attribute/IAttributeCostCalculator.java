package net.sf.anathema.character.impl.model.creation.bonus.attribute;

import net.sf.anathema.character.impl.model.creation.bonus.basic.ElementCreationCost;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;

public interface IAttributeCostCalculator {

  public abstract void calculateAttributeCosts();

  public abstract int getBonusPoints();

  public abstract ElementCreationCost getCosts(IDefaultTrait attribute);
}