package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.IWeaponModifiers;
import net.sf.anathema.character.equipment.impl.character.model.ModifierFactory;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.util.IIdentificate;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProxyWeaponStats_EquipmentModifierTest {
  IWeaponStats original = mock(IWeaponStats.class);
  IWeaponModifiers modifiers = mock(IWeaponModifiers.class);
  ModifierFactory factory = mock(ModifierFactory.class);
  BaseMaterial material = mock(BaseMaterial.class);
  ProxyWeaponStats stats = new ProxyWeaponStats(original, material, factory);

  @Before
  public void createModifiers() throws Exception {
    when(factory.createModifiers()).thenReturn(modifiers);
  }

  @Before
  public void setUpWeapon() throws Exception {
    when(original.getTags()).thenReturn(new IIdentificate[]{});
    when(original.getSpeed()).thenReturn(5);
  }

  @Test
  public void respectsSpeedFromEquipment() throws Exception {
    when(modifiers.getMeleeSpeedMod()).thenReturn(-2);
    int speed = stats.getSpeed();
    assertThat(speed, is(3));
  }

  @Test
  public void usesBestSpeedFromEquipmentAndMaterial() throws Exception {
    when(material.isJadeBased()).thenReturn(true);
    int speed = stats.getSpeed();
    assertThat(speed, is(4));
  }

  @Test
  public void respectsAccuracyFromEquipment() throws Exception {
    when(modifiers.getMeleeAccuracyMod()).thenReturn(5);
    int accuracy = stats.getAccuracy();
    assertThat(accuracy, is(5));
  }

  @Test
  public void usesBestAccuracyFromEquipmentAndMaterial() throws Exception {
    when(material.isOrichalcumBased()).thenReturn(true);
    int accuracy = stats.getAccuracy();
    assertThat(accuracy, is(2));
  }

  @Test
  public void respectsDamageFromEquipment() throws Exception {
    when(modifiers.getMeleeDamageMod()).thenReturn(2);
    int damage = stats.getDamage();
    assertThat(damage, is(2));
  }

  @Test
  public void usesBestDamageFromEquipmentAndMaterial() throws Exception {
    when(material.isStarmetalBased()).thenReturn(true);
    int damage = stats.getDamage();
    assertThat(damage, is(3));
  }

  @Test
  public void usesDefenceFromMaterial() throws Exception {
    when(material.isMoonsilverBased()).thenReturn(true);
    int defence = stats.getDefence();
    assertThat(defence, is(2));
  }

  @Test
  public void respectsPDVPoolFromEquipment() throws Exception {
    when(modifiers.getPDVPoolMod()).thenReturn(2);
    int defence = stats.getDefence();
    assertThat(defence, is(2));
  }

  @Test
  public void usesBestDefenceFromEquipmentAndMaterial() throws Exception {
    when(material.isMoonsilverBased()).thenReturn(true);
    when(modifiers.getPDVPoolMod()).thenReturn(3);
    int defence = stats.getDefence();
    assertThat(defence, is(3));
  }

  @Test
  public void respectsRateFromEquipment() throws Exception {
    when(modifiers.getMeleeRateMod()).thenReturn(2);
    int rate = stats.getRate();
    assertThat(rate, is(2));
  }

  @Test
  public void usesBestRateFromEquipmentAndMaterial() throws Exception {
    when(material.isOrichalcumBased()).thenReturn(true);
    int rate = stats.getRate();
    assertThat(rate, is(1));
  }
}