package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.alternate.AlternateMinimumRestriction;
import net.sf.anathema.character.generic.framework.xml.trait.alternate.GenericRestrictedTraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.test.character.dummy.DummyFavorableGenericTrait;
import net.sf.anathema.test.character.dummy.DummyLimitationContext;

public class GenericRestrictedTraitTemplateTest extends BasicTestCase {

  public void testMinimumValueIsLowWhenAlternativeFulfillsRequirement() throws Exception {
    AlternateMinimumRestriction restriction = new AlternateMinimumRestriction(1, 1);
    restriction.addTraitType(AbilityType.Endurance);
    restriction.addTraitType(AbilityType.Sail);
    DummyLimitationContext context = new DummyLimitationContext();
    context.addTrait(new DummyFavorableGenericTrait(AbilityType.Endurance, 3));
    context.addTrait(new DummyFavorableGenericTrait(AbilityType.Sail, 3));
    GenericTraitTemplate delegateTemplate = new GenericTraitTemplate();
    delegateTemplate.setMinimumValue(0);
    GenericRestrictedTraitTemplate template = new GenericRestrictedTraitTemplate(
        delegateTemplate,
        restriction,
        AbilityType.Endurance);
    assertEquals(0, template.getMinimumValue(context));
  }
}