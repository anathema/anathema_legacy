package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class AttributeExperienceModel implements IValueModel<Integer> {

  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;

  public AttributeExperienceModel(ICoreTraitConfiguration traitConfiguration, IPointCostCalculator calculator) {
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
  }

  public Integer getValue() {
    return getAttributeCosts();
  }

  public String getId() {
    return "Attributes"; //$NON-NLS-1$
  }

  private int getAttributeCosts() {
    int experienceCosts = 0;
    for (IIdentifiedTraitTypeGroup group : traitConfiguration.getAttributeTypeGroups()) {
      for (ITrait attribute : traitConfiguration.getTraits(group.getAllGroupTypes())) {
        experienceCosts += calculator.getAttributeCosts(attribute);
      }
    }
    return experienceCosts;
  }
}