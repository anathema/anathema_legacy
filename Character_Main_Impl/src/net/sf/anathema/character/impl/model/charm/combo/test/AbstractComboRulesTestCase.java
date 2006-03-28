package net.sf.anathema.character.impl.model.charm.combo.test;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.impl.model.charm.combo.ComboArbitrator;
import net.sf.anathema.character.impl.model.charm.combo.IComboRules;
import net.sf.anathema.character.impl.model.charm.test.DummyCharmUtilities;
import net.sf.anathema.lib.testing.BasicTestCase;

public abstract class AbstractComboRulesTestCase extends BasicTestCase {

  private IComboRules rules;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.rules = new ComboArbitrator();
  }

  protected IComboRules getRules() {
    return rules;
  }

  protected boolean comboSameAbilityCharms(CharmType type1, CharmType type2) {
    ICharm charm1 = DummyCharmUtilities.createCharm(type1, new ValuedTraitType(AbilityType.Archery, 3));
    ICharm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AbilityType.Archery, 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboDifferentAbilityCharms(CharmType type1, CharmType type2) {
    ICharm charm1 = DummyCharmUtilities.createCharm(type1, new ValuedTraitType(AbilityType.Bureaucracy, 3));
    ICharm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AbilityType.Archery, 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboDifferentAttributeCharms(CharmType type1, CharmType type2) {
    ICharm charm1 = DummyCharmUtilities.createCharm(type1, new ValuedTraitType(AttributeType.Intelligence, 3));
    ICharm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AttributeType.Charisma, 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboAbilityAttributeCharms(CharmType type1, CharmType type2) {
    ICharm charm1 = DummyCharmUtilities.createCharm(type1, new ValuedTraitType(AbilityType.Performance, 3));
    ICharm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AttributeType.Charisma, 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboAllAbilitiesCharmWithAbility(CharmType type1, CharmType type2) {
    ICharm charm1 = DummyCharmUtilities.createCharm(type1, new ComboRestrictions(true, null), new ValuedTraitType(
        AbilityType.Performance,
        3));
    ICharm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AbilityType.Archery, 3));
    return rules.isComboLegal(charm1, charm2);
  }
}