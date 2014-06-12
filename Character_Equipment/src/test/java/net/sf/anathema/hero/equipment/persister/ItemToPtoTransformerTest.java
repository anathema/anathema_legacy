package net.sf.anathema.hero.equipment.persister;

import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.equipment.dummy.DemoMeleeWeapon;
import net.sf.anathema.character.equipment.dummy.DummyEquipmentItem;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemToPtoTransformerTest {

  @Test
  public void savesPrintStats() throws Exception {
    DummyEquipmentItem item = new DummyEquipmentItem("Title", "Description");
    IEquipmentStats stats = createStats("Stat");
    EquipmentModel model = createEquipmentModel(item, stats);
    ItemToPtoTransformer transformer = new ItemToPtoTransformer(model);
    item.addEquipment(stats);
    item.setPrintEnabled(stats, true);
    EquipmentPto pto = transformer.createPto(item);
    EquipmentStatsPto expected = new EquipmentStatsPto();
    expected.id = "Stat";
    assertThat(pto.printStats, contains(expected));
  }

  private DemoMeleeWeapon createStats(String id) {
    return new DemoMeleeWeapon(new SimpleIdentifier(id), 0, 0, 0, 0, HealthType.Aggravated, 0, 0, 0);
  }

  private EquipmentModel createEquipmentModel(DummyEquipmentItem item, IEquipmentStats stats) {
    EquipmentModel model = mock(EquipmentModel.class);
    EquipmentOptionsProvider provider = mock(EquipmentOptionsProvider.class);
    when(model.getOptionProvider()).thenReturn(provider);
    when(provider.getEnabledStatOptions(item, stats)).thenReturn(
            new IEquipmentStatsOption[0]);
    return model;
  }
}