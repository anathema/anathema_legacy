package net.sf.anathema.hero.combos;

import net.sf.anathema.hero.dummy.DummyCharmUtilities;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charmtree.duration.SimpleDuration;
import net.sf.anathema.hero.charmtree.type.CharmType;
import net.sf.anathema.character.main.magic.combos.ComboRestrictions;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.character.main.traits.types.ValuedTraitType;
import net.sf.anathema.hero.combos.model.ComboRules;
import net.sf.anathema.hero.combos.model.rules.AbstractComboArbitrator;

public abstract class AbstractComboRulesTestCase {

  private ComboRules rules = new AbstractComboArbitrator() {

    @Override
    protected boolean isCharmLegalByRules(Charm charm) {
      return charm.getDuration() == SimpleDuration.INSTANT_DURATION;
    }
  };

  protected ComboRules getRules() {
    return rules;
  }

  protected boolean comboSameAbilityCharms(CharmType type1, CharmType type2) {
    Charm charm1 = DummyCharmUtilities.createCharm(type1, new ValuedTraitType(AbilityType.Archery, 3));
    Charm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AbilityType.Archery, 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboDifferentAbilityCharms(CharmType type1, CharmType type2) {
    Charm charm1 = DummyCharmUtilities.createCharm(type1, new ValuedTraitType(AbilityType.Bureaucracy, 3));
    Charm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AbilityType.Archery, 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboDifferentAttributeCharms(CharmType type1, CharmType type2) {
    Charm charm1 = DummyCharmUtilities.createCharm(type1, new ValuedTraitType(AttributeType.Intelligence, 3));
    Charm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AttributeType.Charisma, 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboAbilityAttributeCharms(CharmType type1, CharmType type2) {
    Charm charm1 = DummyCharmUtilities.createCharm(type1, new ValuedTraitType(AbilityType.Performance, 3));
    Charm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AttributeType.Charisma, 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboAllAbilitiesCharmWithAbility(CharmType type1, CharmType type2) {
    Charm charm1 = DummyCharmUtilities.createCharm(type1, new ComboRestrictions(true, ""), new ValuedTraitType(AbilityType.Performance, 3));
    Charm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AbilityType.Archery, 3));
    return rules.isComboLegal(charm1, charm2);
  }
}