package net.sf.anathema.acceptance.fixture.character.miscellaneous;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;

public class CheckAttributeAllowedFixture extends AbstractCharacterColumnFixture {

  public int appearanceValue;
  public int essenceValue;

  public boolean isAppearanceValueAllowed() {
    getCharacterStatistics().getTraitConfiguration().getTrait(AttributeType.Appearance).setCurrentValue(0);
    getCharacterStatistics().getTraitConfiguration().getTrait(OtherTraitType.Essence).setCurrentValue(essenceValue);
    ValuedTraitType appearance = new ValuedTraitType(AttributeType.Appearance, appearanceValue);
    ILimitationContext limitationContext = getCharacterStatistics().getCharacterContext()
        .getTraitContext()
        .getLimitationContext();
    return getCharacterStatistics().getCharacterTemplate()
        .getAdditionalRules()
        .getAdditionalTraitRules()
        .isAllowedTraitValue(appearance, limitationContext);

  }

  public boolean isEssenceValueAllowed() {
    getCharacterStatistics().getTraitConfiguration()
        .getTrait(AttributeType.Appearance)
        .setCurrentValue(appearanceValue);
    ValuedTraitType essence = new ValuedTraitType(OtherTraitType.Essence, essenceValue);
    ILimitationContext limitationContext = getCharacterStatistics().getCharacterContext()
        .getTraitContext()
        .getLimitationContext();
    return getCharacterStatistics().getCharacterTemplate()
        .getAdditionalRules()
        .getAdditionalTraitRules()
        .isAllowedTraitValue(essence, limitationContext);
  }
}