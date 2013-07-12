package net.sf.anathema.hero.spiritual.advance.experience;

import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.hero.advance.overview.model.AbstractIntegerValueModel;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;

public class WillpowerExperienceModel extends AbstractIntegerValueModel {

  private final SpiritualTraitModel spiritualTraits;
  private final SpiritualExperienceCalculator calculator;

  public WillpowerExperienceModel(SpiritualTraitModel spiritualTraits, SpiritualExperienceCalculator calculator) {
    super("Experience", "Willpower");
    this.spiritualTraits = spiritualTraits;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return getWillpowerCosts();
  }

  private int getWillpowerCosts() {
    return calculator.getWillpowerCosts(spiritualTraits.getTrait(OtherTraitType.Willpower));
  }
}