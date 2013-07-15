package net.sf.anathema.hero.template.parser;

import junit.framework.TestCase;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.xml.trait.GenericRestrictedTraitTemplate;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.main.xml.trait.alternate.AlternateMinimumRestriction;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.dummy.trait.DummyTrait;

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