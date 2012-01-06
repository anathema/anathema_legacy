package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class WillpowerExperienceModel extends AbstractIntegerValueModel {

  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;

  public WillpowerExperienceModel(ICoreTraitConfiguration traitConfiguration, IPointCostCalculator calculator) {
    super("Experience", "Willpower"); //$NON-NLS-1$ //$NON-NLS-2$
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
  }

  public Integer getValue() {
    return getWillpowerCosts();
  }

  private int getWillpowerCosts() {
    return calculator.getWillpowerCosts(TraitCollectionUtilities.getWillpower(traitConfiguration));
  }
}