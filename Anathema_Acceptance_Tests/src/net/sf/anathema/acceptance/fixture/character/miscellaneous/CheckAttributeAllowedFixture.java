package net.sf.anathema.acceptance.fixture.character.miscellaneous;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.library.trait.IDefaultTrait;

public class CheckAttributeAllowedFixture extends AbstractCharacterColumnFixture {

  public int appearanceValue;
  public int essenceValue;

  public boolean isAppearanceValueAllowed() {
    getAppearance().setCurrentValue(0);
    getEssence().setCurrentValue(essenceValue);
    ValuedTraitType appearance = new ValuedTraitType(AttributeType.Appearance, appearanceValue);
    ILimitationContext limitationContext = getCharacterStatistics().getCharacterContext()
        .getTraitContext()
        .getLimitationContext();
    return getCharacterStatistics().getCharacterTemplate()
        .getAdditionalRules()
        .getAdditionalTraitRules()
        .isAllowedTraitValue(appearance, limitationContext);

  }

  private IDefaultTrait getEssence() {
    return (IDefaultTrait) getCharacterStatistics().getTraitConfiguration().getTrait(OtherTraitType.Essence);
  }

  public boolean isEssenceValueAllowed() {
    getAppearance().setCurrentValue(appearanceValue);
    ValuedTraitType essence = new ValuedTraitType(OtherTraitType.Essence, essenceValue);
    ILimitationContext limitationContext = getCharacterStatistics().getCharacterContext()
        .getTraitContext()
        .getLimitationContext();
    return getCharacterStatistics().getCharacterTemplate()
        .getAdditionalRules()
        .getAdditionalTraitRules()
        .isAllowedTraitValue(essence, limitationContext);
  }

  private IDefaultTrait getAppearance() {
    return (IDefaultTrait) getCharacterStatistics().getTraitConfiguration().getTrait(AttributeType.Appearance);
  }
}