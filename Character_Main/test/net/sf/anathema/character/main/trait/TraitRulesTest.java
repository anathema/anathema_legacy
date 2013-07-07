package net.sf.anathema.character.main.trait;

import net.sf.anathema.character.main.template.ITraitLimitation;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.library.trait.rules.TraitRules;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TraitRulesTest {

  private DummyHero dummyHero = new DummyHero();
  private ITraitTemplate template = mock(ITraitTemplate.class);
  private TraitRules traitRules = new TraitRules(AbilityType.Archery, template, dummyHero);

  @Test
  public void testAbsoluteMaximumValue() throws Exception {
    int absoluteMaxValue = 9;
    ITraitLimitation limitation = mock(ITraitLimitation.class);
    when(limitation.getAbsoluteLimit(dummyHero)).thenReturn(absoluteMaxValue);
    when(template.getLimitation()).thenReturn(limitation);
    int actualMaximalValue = traitRules.getAbsoluteMaximumValue();
    assertEquals(absoluteMaxValue, actualMaximalValue);
  }
}