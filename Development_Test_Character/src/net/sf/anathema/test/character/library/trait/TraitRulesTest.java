package net.sf.anathema.test.character.library.trait;

import net.sf.anathema.character.generic.dummy.DummyLimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.easymock.EasyMock;

public class TraitRulesTest extends BasicTestCase {

  private DummyLimitationContext limitationContext;
  private ITraitTemplate template;
  private TraitRules traitRules;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.limitationContext = new DummyLimitationContext();
    this.template = EasyMock.createMock(ITraitTemplate.class);
    this.traitRules = new TraitRules(AbilityType.Archery, template, limitationContext);
  }

  public void testAbsoluteMaximumValue() throws Exception {
    int absoluteMaxValue = 9;
    ITraitLimitation limitation = EasyMock.createMock(ITraitLimitation.class);
    EasyMock.expect(limitation.getAbsoluteLimit(limitationContext)).andReturn(absoluteMaxValue);
    EasyMock.expect(template.getLimitation()).andReturn(limitation);
    EasyMock.replay(template, limitation);
    int actualMaximalValue = traitRules.getAbsoluteMaximumValue();
    EasyMock.verify(template, limitation);
    assertEquals(absoluteMaxValue, actualMaximalValue);
  }
}