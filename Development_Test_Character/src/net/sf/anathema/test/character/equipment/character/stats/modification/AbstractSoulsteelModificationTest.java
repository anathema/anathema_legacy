package net.sf.anathema.test.character.equipment.character.stats.modification;

import net.sf.anathema.character.equipment.MagicalMaterial;

import org.junit.Test;

public abstract class AbstractSoulsteelModificationTest extends AbstractEquipmentModificationTest {

  @Test
  public final void defenseUnmodified() throws Exception {
    assertDefenseUnmodified();
  }

  @Test
  public final void speedUnmodified() throws Exception {
    assertSpeedUnmodified();
  }

  @Test
  public final void rangeUnmodified() throws Exception {
    assertRangeUnmodified();
  }

  @Test
  public final void rateUnmodified() throws Exception {
    assertRateUnmodified();
  }

  @Test
  public final void unmodifiedDamage() throws Exception {
    assertDefenseUnmodified();
  }

  @Override
  protected final MagicalMaterial getMagicMaterial() {
    return MagicalMaterial.Soulsteel;
  }
}