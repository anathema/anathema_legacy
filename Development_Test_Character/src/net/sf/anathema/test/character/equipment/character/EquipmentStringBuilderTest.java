package net.sf.anathema.test.character.equipment.character;

import net.sf.anathema.character.equipment.character.EquipmentStringBuilder;
import net.sf.anathema.character.equipment.character.IEquipmentStringBuilder;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.dummy.character.equipment.DemoMeleeWeapon;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.test.character.generic.framework.magic.stringbuilder.DummyResources;

public class EquipmentStringBuilderTest extends BasicTestCase {

  private DummyResources resources;
  private IEquipmentStringBuilder equipmentStringBuilder;

  @Override
  protected void setUp() throws Exception {
    resources = new DummyResources();
    resources.putString("Equipment.Stats.Short.Speed", "Speed"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Equipment.Stats.Short.Accuracy", "Acc"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Equipment.Stats.Short.Damage", "Dam"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Equipment.Stats.Short.Rate", "Rate"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Equipment.Stats.Short.Defence", "Def"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Weapons.Damage.Bashing.Short", "B"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Weapons.Damage.Lethal.Short", "L"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Weapons.Damage.Aggravated.Short", "A"); //$NON-NLS-1$ //$NON-NLS-2$
    equipmentStringBuilder = new EquipmentStringBuilder(resources);
  }

  public void testWeapon() {
    DemoMeleeWeapon weapon = new DemoMeleeWeapon(new Identificate("Sword"), 5, 2, 7, HealthType.Lethal, -1, 2); //$NON-NLS-1$
    assertEquals("Sword: Speed:5 Acc:+2 Dam:+7L Def:-1 Rate:2", equipmentStringBuilder.createString(weapon)); //$NON-NLS-1$
  }
}
