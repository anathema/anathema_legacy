package net.sf.anathema.test.character.generic.framework.xml;

import junit.framework.Assert;
import net.sf.anathema.character.generic.impl.traits.limitation.EssenceBasedLimitation;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitTemplate;

public class AnathemaCharacterAssert {

  public static void assertEssenceLimitedTraitTemplate(
      int startValue,
      int zeroLevelValue,
      int minimalValue,
      ITraitTemplate traitTemplate) {
    assertBasicValues(startValue, zeroLevelValue, minimalValue, traitTemplate);
    Assert.assertTrue(traitTemplate.getLimitation() instanceof EssenceBasedLimitation);
  }

  public static void assertStaticSimpleTraitTemplate(
      int startValue,
      int zeroLevelValue,
      int minimalValue,
      int maximalValue,
      ITraitTemplate traitTemplate) {
    assertBasicValues(startValue, zeroLevelValue, minimalValue, traitTemplate);
    Assert.assertEquals(maximalValue, ((StaticTraitLimitation) traitTemplate.getLimitation()).getStaticLimit());
  }

  private static void assertBasicValues(
      int startValue,
      int zeroLevelValue,
      int minimalValue,
      ITraitTemplate traitTemplate) {
    Assert.assertEquals(startValue, traitTemplate.getStartValue());
    Assert.assertEquals(zeroLevelValue, traitTemplate.getZeroLevelValue());
    Assert.assertEquals(minimalValue, traitTemplate.getMinimumValue(null));
  }
}