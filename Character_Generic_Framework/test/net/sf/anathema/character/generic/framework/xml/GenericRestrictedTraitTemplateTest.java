package net.sf.anathema.character.generic.framework.xml;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.dummy.DummyFavorableGenericTrait;
import net.sf.anathema.character.generic.dummy.DummyLimitationContext;
import net.sf.anathema.character.generic.framework.xml.trait.GenericRestrictedTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.alternate.AlternateMinimumRestriction;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class GenericRestrictedTraitTemplateTest extends TestCase {

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