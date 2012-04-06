package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.IWeaponModifiers;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.impl.character.model.stats.TraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EquipmentCollectionTest {
  private EquipmentCollection collection = new EquipmentCollection();

  @Test
  public void createsModifiers() throws Exception {
    TraitModifyingStats traitModifyingStats = createStats();
    IEquipmentItem item = createItemWithStats(traitModifyingStats);
    collection.add(item);
    IWeaponModifiers modifiers = collection.createModifiers();
    assertThat(modifiers.getMeleeAccuracyMod(), is(5));
  }

  private IEquipmentItem createItemWithStats(TraitModifyingStats traitModifyingStats) {
    IEquipmentItem item = mock(IEquipmentItem.class);
    when(item.getStats()).thenReturn(new IEquipmentStats[]{traitModifyingStats});
    when(item.isPrintEnabled(traitModifyingStats)).thenReturn(true);
    return item;
  }

  private TraitModifyingStats createStats() {
    TraitModifyingStats traitModifyingStats = new TraitModifyingStats();
    traitModifyingStats.setMeleeAccuracyMod(5);
    return traitModifyingStats;
  }
}