package net.sf.anathema.lib.veto;

import net.sf.anathema.lib.gui.list.VetoableListSelectionModel;
import net.sf.anathema.lib.gui.list.veto.Allower;
import net.sf.anathema.lib.gui.list.veto.Denier;
import net.sf.anathema.lib.gui.list.veto.Vetor;
import org.junit.Test;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import static net.sf.anathema.lib.gui.list.ListSelectionMode.SingleSelection;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

public class VetoableListSelectionModelTest {

  private VetoableListSelectionModel selectionModel = new VetoableListSelectionModel(SingleSelection);

  @Test
  public void usesGivenSelectionMode() throws Exception {
    assertEquals(SINGLE_SELECTION, selectionModel.getSelectionMode());
  }

  @Test
  public void doesNotChangeSelectionIfVetorDisagrees() throws Exception {
    selectionModel.addVetor(new Denier());
    selectionModel.addSelectionInterval(1, 2);
    assertEquals(-1, selectionModel.getMinSelectionIndex());
  }

  @Test
  public void removesVetorsForGood() throws Exception {
    Vetor vetor = mock(Vetor.class);
    selectionModel.addVetor(vetor);
    selectionModel.removeVetor(vetor);
    selectionModel.addSelectionInterval(1, 2);
    verifyZeroInteractions(vetor);
  }

  @Test
  public void addsSelectionIfVetorAgrees() throws Exception {
    selectionModel.addVetor(new Allower());
    selectionModel.addSelectionInterval(1, 2);
    assertEquals(2, selectionModel.getMinSelectionIndex());
  }

  @Test
  public void removesSelectionIfVetorAgrees() throws Exception {
    selectionModel.setSelectionInterval(1, 1);
    selectionModel.addVetor(new Allower());
    selectionModel.removeSelectionInterval(1, 1);
    assertEquals(-1, selectionModel.getMinSelectionIndex());
  }

  @Test
  public void doesNotRequestVetoIfNothingChangesOnRemoveInterval() throws Exception {
    Vetor vetor = mock(Vetor.class);
    selectionModel.clearSelection();
    selectionModel.addVetor(vetor);
    selectionModel.removeSelectionInterval(1, 1);
    verifyZeroInteractions(vetor);
  }

  @Test
  public void indexIndicatesNoSelectionAfterClearing() throws Exception {
    selectionModel.clearSelection();
    assertEquals(-1, selectionModel.getMinSelectionIndex());
  }

  @Test
  public void doesNotRequestVetoIfNothingChangesOnClearSelection() throws Exception {
    Vetor vetor = mock(Vetor.class);
    selectionModel.clearSelection();
    selectionModel.addVetor(vetor);
    selectionModel.clearSelection();
    verifyZeroInteractions(vetor);
  }
}