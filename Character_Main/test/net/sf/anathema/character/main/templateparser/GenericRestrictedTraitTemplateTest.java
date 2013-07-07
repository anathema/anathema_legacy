package net.sf.anathema.character.main.templateparser;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.framework.xml.trait.GenericRestrictedTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.alternate.AlternateMinimumRestriction;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import net.sf.anathema.character.main.testing.dummy.trait.DummyTrait;

public class GenericRestrictedTraitTemplateTest extends TestCase {

  public void testMinimumValueIsLowWhenAlternativeFulfillsRequirement() throws Exception {
    AlternateMinimumRestriction restriction = new AlternateMinimumRestriction(1, 1);
    restriction.addTraitType(AbilityType.Resistance);
    restriction.addTraitType(AbilityType.Sail);
    DummyHero hero = DummyHero.createWithTraits(new Trait[]{new DummyTrait(AbilityType.Resistance, 3), new DummyTrait(AbilityType.Sail, 3)});
    GenericTraitTemplate delegateTemplate = new GenericTraitTemplate();
    delegateTemplate.setMinimumValue(0);
    GenericRestrictedTraitTemplate template = new GenericRestrictedTraitTemplate(delegateTemplate, restriction, AbilityType.Resistance);
    assertEquals(0, template.getMinimumValue(hero));
  }
}