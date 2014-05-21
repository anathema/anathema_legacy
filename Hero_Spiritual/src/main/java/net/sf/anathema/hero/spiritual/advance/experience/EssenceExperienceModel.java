package net.sf.anathema.hero.spiritual.advance.experience;

import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import net.sf.anathema.hero.advance.overview.model.AbstractIntegerValueModel;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;

public class EssenceExperienceModel extends AbstractIntegerValueModel {
  private final SpiritualTraitModel spiritualTraits;
  private final SpiritualExperienceCalculator calculator;

  public EssenceExperienceModel(SpiritualTraitModel spiritualTraits, SpiritualExperienceCalculator calculator) {
    super("Experience", "Essence");
    this.spiritualTraits = spiritualTraits;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return calculator.getEssenceCosts(spiritualTraits.getTrait(OtherTraitType.Essence));
  }
}