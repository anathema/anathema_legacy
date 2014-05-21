package net.sf.anathema.hero.spiritual.advance.experience;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.VirtueType;
import net.sf.anathema.hero.advance.overview.model.AbstractIntegerValueModel;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;

public class VirtueExperienceModel extends AbstractIntegerValueModel {

  private final SpiritualTraitModel spiritualTraits;
  private final SpiritualExperienceCalculator calculator;

  public VirtueExperienceModel(SpiritualTraitModel spiritualTraits, SpiritualExperienceCalculator calculator) {
    super("Experience", "Virtues");
    this.spiritualTraits = spiritualTraits;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return getVirtueCosts();
  }

  private int getVirtueCosts() {
    int experienceCosts = 0;
    for (TraitType virtueType : VirtueType.values()) {
      experienceCosts += calculator.getVirtueCosts(spiritualTraits.getTrait(virtueType));
    }
    return experienceCosts;
  }
}