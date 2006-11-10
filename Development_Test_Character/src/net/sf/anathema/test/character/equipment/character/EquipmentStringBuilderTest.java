package net.sf.anathema.test.character.equipment.character;

import junit.framework.Assert;
import net.sf.anathema.character.equipment.character.EquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.IEquipmentStringBuilder;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.dummy.character.equipment.DemoMeleeWeapon;
import net.sf.anathema.dummy.character.equipment.DemoNaturalArmour;
import net.sf.anathema.dummy.character.equipment.DemoRangeWeapon;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.test.character.generic.framework.magic.stringbuilder.DummyResources;

import org.junit.Before;
import org.junit.Test;

public class EquipmentStringBuilderTest {

  private IEquipmentStringBuilder equipmentStringBuilder;

  @Before
  public void setUp() throws Exception {
    DummyResources resources = new DummyResources();
    resources.putString("Equipment.Stats.Short.Speed", "Speed"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Equipment.Stats.Short.Accuracy", "Acc"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Equipment.Stats.Short.Damage", "Dam"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Equipment.Stats.Short.Range", "Range"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Equipment.Stats.Short.Rate", "Rate"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Equipment.Stats.Short.Defence", "Def"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Equipment.Stats.Short.Soak", "Soak(B/L/A)"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Equipment.Stats.Short.Hardness", "Hardness(B/L/A)"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("HealthType.Bashing.Short", "B"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("HealthType.Lethal.Short", "L"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("HealthType.Aggravated.Short", "A"); //$NON-NLS-1$ //$NON-NLS-2$
    equipmentStringBuilder = new EquipmentStringBuilder(resources);
  }

  @Test
  public void testMeleeWeapon() {
    DemoMeleeWeapon weapon = new DemoMeleeWeapon(new Identificate("Sword"), 5, 2, 7, HealthType.Lethal, -1, 2); //$NON-NLS-1$
    Assert.assertEquals("Sword: Speed:5 Acc:+2 Dam:+7L Def:-1 Rate:2", equipmentStringBuilder.createString(weapon)); //$NON-NLS-1$
  }

  @Test
  public void testFixedDamageRangedWeapon() {
    DemoRangeWeapon weapon = new DemoRangeWeapon(new Identificate("Bow"), 5, 2, 17, HealthType.Bashing, 200, 4, false); //$NON-NLS-1$
    Assert.assertEquals("Bow: Speed:5 Acc:+2 Dam:17B Range:200 Rate:4", equipmentStringBuilder.createString(weapon)); //$NON-NLS-1$
  }

  @Test
  public void testNoDamageRangedWeapon() {
    DemoRangeWeapon weapon = new DemoRangeWeapon(new Identificate("Bow"), 5, 2, 17, HealthType.Bashing, 200, 4, true); //$NON-NLS-1$
    Assert.assertEquals("Bow: Speed:5 Acc:+2 Dam:- Range:200 Rate:4", equipmentStringBuilder.createString(weapon)); //$NON-NLS-1$
  }

  @Test
  public void testNaturalArmour() throws Exception {
    DemoNaturalArmour armour = new DemoNaturalArmour(new Identificate("Natural"), 5, 2); //$NON-NLS-1$
    Assert.assertEquals(
        "Natural: Soak(B/L/A):+5/+2/- Hardness(B/L/A):-/-/-", equipmentStringBuilder.createString(armour)); //$NON-NLS-1$
  }
}