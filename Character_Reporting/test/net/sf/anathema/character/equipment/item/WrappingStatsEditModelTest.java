package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WrappingStatsEditModelTest {

  private final IEquipmentDatabaseManagement model = mock(IEquipmentDatabaseManagement.class);
  private final WrappingStatsEditModel editModel = new WrappingStatsEditModel(model);

  @Test
  public void returnsSelectedStats() throws Exception {
    IEquipmentStats stats = createStats();
    editModel.selectStats(stats);
    assertThat(editModel.getSelectedStats(), is(stats));
  }

  @Test
  public void replacesSelectedStats() throws Exception {
    IEquipmentTemplateEditModel templateEditModel = makeInnerModelReturnTemplateEditModel();
    IEquipmentStats newStats = createStats();
    IEquipmentStats stats = createStats();
    editModel.selectStats(stats);
    editModel.replaceSelectedStatistics(newStats);
    verify(templateEditModel).replaceStatistics(stats, newStats);
  }

  private IEquipmentTemplateEditModel makeInnerModelReturnTemplateEditModel() {
    IEquipmentTemplateEditModel templateEditModel = mock(IEquipmentTemplateEditModel.class);
    when(model.getTemplateEditModel()).thenReturn(templateEditModel);
    return templateEditModel;
  }

  private IEquipmentStats createStats() {
    return mock(IEquipmentStats.class);
  }
}
