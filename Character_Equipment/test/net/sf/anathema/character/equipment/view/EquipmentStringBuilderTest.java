package net.sf.anathema.character.equipment.view;

import net.sf.anathema.character.equipment.character.EquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.IEquipmentStringBuilder;
import net.sf.anathema.character.equipment.dummy.DemoMeleeWeapon;
import net.sf.anathema.character.equipment.dummy.DemoNaturalArmour;
import net.sf.anathema.character.equipment.dummy.DemoRangeWeapon;
import net.sf.anathema.character.main.health.HealthType;
import net.sf.anathema.lib.dummy.DummyResources;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EquipmentStringBuilderTest {

  private IEquipmentStringBuilder equipmentStringBuilder;

  @Before
  public void setUp() throws Exception {
    DummyResources resources = new DummyResources();
    resources.putString("Equipment.Stats.Short.Speed", "Speed");
    resources.putString("Equipment.Stats.Short.Accuracy", "Acc");
    resources.putString("Equipment.Stats.Short.Damage", "Dam");
    resources.putString("Equipment.Stats.Short.Range", "Range");
    resources.putString("Equipment.Stats.Short.Rate", "Rate");
    resources.putString("Equipment.Stats.Short.Defence", "Def");
    resources.putString("Equipment.Stats.Short.Soak", "Soak(B/L/A)");
    resources.putString("Equipment.Stats.Short.Hardness", "Hardness(B/L/A)");
    resources.putString("HealthType.Bashing.Short", "B");
    resources.putString("HealthType.Lethal.Short", "L");
    resources.putString("HealthType.Aggravated.Short", "A");
    resources.putString("Melee", "Melee");
    resources.putString("MartialArts", "Martial Arts");
    equipmentStringBuilder = new EquipmentStringBuilder(resources);
  }

  @Test
  public void testMeleeWeapon() {
    DemoMeleeWeapon weapon = new DemoMeleeWeapon(new SimpleIdentifier("Sword"), 5, 2, 7, 1, HealthType.Lethal, -1, 0, 2);
    assertEquals("Sword (Melee): Speed:5 Acc:+2 Dam:+7L Def:-1 Rate:2", equipmentStringBuilder.createString(null, weapon));
  }

  @Test
  public void testFixedDamageRangedWeapon() {
    DemoRangeWeapon weapon = new DemoRangeWeapon(new SimpleIdentifier("Bow"), 5, 2, 17, 1, HealthType.Bashing, 200, 4, false);
    assertEquals("Bow (Martial Arts): Speed:5 Acc:+2 Dam:17B Range:200 Rate:4", equipmentStringBuilder.createString(null, weapon));
  }

  @Test
  public void testNoDamageRangedWeapon() {
    DemoRangeWeapon weapon = new DemoRangeWeapon(new SimpleIdentifier("Bow"), 5, 2, 17, 1, HealthType.Bashing, 200, 4, true);
    assertEquals("Bow (Martial Arts): Speed:5 Acc:+2 Dam:- Range:200 Rate:4", equipmentStringBuilder.createString(null, weapon));
  }

  @Test
  public void testNaturalArmour() throws Exception {
    DemoNaturalArmour armour = new DemoNaturalArmour(new SimpleIdentifier("Natural"), 5, 2);
    String result = equipmentStringBuilder.createString(null, armour);
    assertEquals("Natural: Soak(B/L/A):+5/+2/- Hardness(B/L/A):-/-", result);
  }
}