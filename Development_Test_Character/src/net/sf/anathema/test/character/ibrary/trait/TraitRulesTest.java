package net.sf.anathema.test.character.ibrary.trait;

import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.test.character.dummy.DummyLimitationContext;

import org.easymock.MockControl;

public class TraitRulesTest extends BasicTestCase {

  private DummyLimitationContext limitationContext;
  private MockControl templateControl;
  private ITraitTemplate template;
  private TraitRules traitRules;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.limitationContext = new DummyLimitationContext();
    this.templateControl = MockControl.createControl(ITraitTemplate.class);
    this.template = (ITraitTemplate) templateControl.getMock();
    this.traitRules = new TraitRules(AbilityType.Archery, template, limitationContext);
  }

  public void testAbsoluteMaximumValue() throws Exception {
    int absoluteMaxValue = 9;
    MockControl limitationControl = MockControl.createControl(ITraitLimitation.class);
    ITraitLimitation limitation = (ITraitLimitation) limitationControl.getMock();
    limitationControl.expectAndReturn(limitation.getAbsoluteLimit(limitationContext), absoluteMaxValue);
    templateControl.expectAndReturn(template.getLimitation(), limitation);
    templateControl.replay();
    limitationControl.replay();
    int actualMaximalValue = traitRules.getAbsoluteMaximumValue();
    limitationControl.verify();
    templateControl.verify();
    assertEquals(absoluteMaxValue, actualMaximalValue);
  }
}