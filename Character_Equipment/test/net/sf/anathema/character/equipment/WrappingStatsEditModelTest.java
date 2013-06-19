package net.sf.anathema.character.equipment;

import net.sf.anathema.character.equipment.item.WrappingStatsEditModel;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.IChangeListener;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class WrappingStatsEditModelTest {

  private final IEquipmentDatabaseManagement model = mock(IEquipmentDatabaseManagement.class);
  private final WrappingStatsEditModel editModel = new WrappingStatsEditModel(model);
  private final IEquipmentTemplateEditModel templateEditModel = mock(IEquipmentTemplateEditModel.class);
  private final IEquipmentStats stats = createStats();

  @Before
  public void setUp() throws Exception {
    when(model.getTemplateEditModel()).thenReturn(templateEditModel);
  }

  @Test
  public void returnsSelectedStats() throws Exception {
    selectOriginalStats();
    assertThat(editModel.getSelectedStats(), is(stats));
  }

  @Test
  public void reportsSelectedStats() throws Exception {
    selectOriginalStats();
    assertThat(editModel.hasSelectedStats(), is(true));
  }

  @Test
  public void hasNoSelectionInitially() throws Exception {
    assertThat(editModel.hasSelectedStats(), is(false));
  }

  @Test
  public void notifiesClientsOfSelection() throws Exception {
    IChangeListener listener = addAListener();
    selectOriginalStats();
    verify(listener).changeOccurred();
  }

  @Test
  public void doesNotNotifyWhenNoActualChangeOccurs() throws Exception {
    selectOriginalStats();
    IChangeListener listener = addAListener();
    selectOriginalStats();
    verifyZeroInteractions(listener);
  }

  @Test
  public void replacesSelectedStats() throws Exception {
    IEquipmentStats newStats = createStats();
    selectOriginalStats();
    editModel.replaceSelectedStatistics(newStats);
    verify(templateEditModel).replaceStatistics(stats, newStats);
  }

  @Test
  public void removesSelectedStats() throws Exception {
    selectOriginalStats();
    editModel.removeSelectedStatistics();
    verify(templateEditModel).removeStatistics(stats);
  }

  @Test
  public void resetSelectionAfterRemoval() throws Exception {
    selectOriginalStats();
    editModel.removeSelectedStatistics();
    assertThat(editModel.hasSelectedStats(), is(false));
  }

  @Test
  public void doesNotRemoveWithoutSelection() throws Exception {
    editModel.removeSelectedStatistics();
    verifyZeroInteractions(templateEditModel);
  }

  @Test
  public void notifiesClientsOfRemoval() throws Exception {
    selectOriginalStats();
    IChangeListener listener = addAListener();
    editModel.removeSelectedStatistics();
    verify(listener).changeOccurred();
  }

  private IChangeListener addAListener() {
    IChangeListener listener = mock(IChangeListener.class);
    editModel.whenSelectedStatsChanges(listener);
    return listener;
  }

  private IEquipmentStats createStats() {
    return mock(IEquipmentStats.class);
  }

  private void selectOriginalStats() {
    editModel.selectStats(stats);
  }
}
