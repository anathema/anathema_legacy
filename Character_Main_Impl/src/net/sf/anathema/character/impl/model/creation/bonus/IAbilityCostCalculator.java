package net.sf.anathema.character.impl.model.creation.bonus;

import net.sf.anathema.character.library.trait.FavorableTraitCost;
import net.sf.anathema.character.library.trait.IFavorableModifiableTrait;

public interface IAbilityCostCalculator {

  public abstract void calculateAbilityCosts();

  public abstract int getAbilityPoints(boolean favored);

  public abstract int getBonusPoints();

  public abstract FavorableTraitCost getCosts(IFavorableModifiableTrait ability);

}