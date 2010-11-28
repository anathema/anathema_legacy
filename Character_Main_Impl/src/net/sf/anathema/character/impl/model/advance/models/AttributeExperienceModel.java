package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicTrait;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class AttributeExperienceModel extends AbstractIntegerValueModel {

  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;

  public AttributeExperienceModel(ICoreTraitConfiguration traitConfiguration, IPointCostCalculator calculator) {
    super("Experience", "Attributes"); //$NON-NLS-1$//$NON-NLS-2$
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
  }

  public Integer getValue() {
    return getAttributeCosts();
  }

  private int getAttributeCosts() {
    int experienceCosts = 0;
    for (IIdentifiedTraitTypeGroup group : traitConfiguration.getAttributeTypeGroups()) {
      for (ITrait attribute : traitConfiguration.getTraits(group.getAllGroupTypes())) {
        experienceCosts += calculator.getAttributeCosts((IBasicTrait) attribute);
      }
    }
    return experienceCosts;
  }
}