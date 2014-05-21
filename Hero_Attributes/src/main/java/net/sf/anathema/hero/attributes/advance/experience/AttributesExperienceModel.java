package net.sf.anathema.hero.attributes.advance.experience;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.advance.overview.model.AbstractIntegerValueModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;

public class AttributesExperienceModel extends AbstractIntegerValueModel {

  private final AttributeModel attributes;
  private final AttributesExperienceCalculator calculator;

  public AttributesExperienceModel(AttributeModel attributes, AttributesExperienceCalculator calculator) {
    super("Experience", "Attributes");
    this.attributes = attributes;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return getAttributeCosts();
  }

  private int getAttributeCosts() {
    int experienceCosts = 0;
    for (Trait attribute : attributes.getAll()) {
      boolean casteOrFavored = attribute.getFavorization().isCasteOrFavored();
      experienceCosts += calculator.getAttributeCosts(attribute, casteOrFavored);
    }
    return experienceCosts;
  }

}