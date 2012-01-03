package net.sf.anathema.test.character.library.trait;

import net.sf.anathema.character.generic.dummy.DummyLimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TraitRulesTest {

  private DummyLimitationContext limitationContext = new DummyLimitationContext();
  private ITraitTemplate template = mock(ITraitTemplate.class);
  private TraitRules traitRules = new TraitRules(AbilityType.Archery, template, limitationContext);

  @Test
  public void testAbsoluteMaximumValue() throws Exception {
    int absoluteMaxValue = 9;
    ITraitLimitation limitation = mock(ITraitLimitation.class);
    when(limitation.getAbsoluteLimit(limitationContext)).thenReturn(absoluteMaxValue);
    when(template.getLimitation()).thenReturn(limitation);
    int actualMaximalValue = traitRules.getAbsoluteMaximumValue();
    assertEquals(absoluteMaxValue, actualMaximalValue);
  }
}