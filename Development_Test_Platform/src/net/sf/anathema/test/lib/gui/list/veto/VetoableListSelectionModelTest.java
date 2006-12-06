// Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.test.lib.gui.list.veto;

import javax.swing.ListSelectionModel;

import net.disy.commons.core.util.ISimpleBlock;
import net.sf.anathema.lib.gui.list.veto.IVetor;
import net.sf.anathema.lib.gui.list.veto.VetoableListSelectionModel;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.easymock.EasyMock;

// NOT_PUBLISHED
public class VetoableListSelectionModelTest extends BasicTestCase {

  private VetoableListSelectionModel selectionModel;
  private IVetor vetor;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    selectionModel = new VetoableListSelectionModel();
    vetor = EasyMock.createMock(IVetor.class);
  }

  public void testIsSingleSelectionMode() throws Exception {
    assertEquals(ListSelectionModel.SINGLE_SELECTION, new VetoableListSelectionModel().getSelectionMode());
  }

  public void testDontAllowNonSingleSelectionMode() throws Exception {
    assertThrowsException(IllegalArgumentException.class, new ISimpleBlock() {
      public void execute() {
        selectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      }
    });
  }

  public void testVeto() throws Exception {
    EasyMock.expect(vetor.vetos()).andReturn(true);
    EasyMock.replay(vetor);
    selectionModel.addVetor(vetor);
    selectionModel.addSelectionInterval(1, 2);
    EasyMock.verify(vetor);
    assertEquals(-1, selectionModel.getMinSelectionIndex());
  }

  public void testRemoveVetor() throws Exception {
    EasyMock.replay(vetor);
    selectionModel.addVetor(vetor);
    selectionModel.removeVetor(vetor);
    selectionModel.addSelectionInterval(1, 2);
    EasyMock.verify(vetor);
  }

  public void testAddSelectionIntervall() throws Exception {
    EasyMock.expect(vetor.vetos()).andReturn(false);
    EasyMock.replay(vetor);
    selectionModel.addVetor(vetor);
    selectionModel.addSelectionInterval(1, 2);
    EasyMock.verify(vetor);
    assertEquals(2, selectionModel.getMinSelectionIndex());
  }

  public void testRemoveSelectionIntervall() throws Exception {
    EasyMock.expect(vetor.vetos()).andReturn(false);
    EasyMock.replay(vetor);
    selectionModel.setSelectionInterval(1, 1);
    selectionModel.addVetor(vetor);
    selectionModel.removeSelectionInterval(1, 1);
    EasyMock.verify(vetor);
    assertEquals(-1, selectionModel.getMinSelectionIndex());
  }

  public void testRemoveSelectionIntervallOnNoSelection() throws Exception {
    EasyMock.replay(vetor);
    selectionModel.clearSelection();
    selectionModel.addVetor(vetor);
    selectionModel.removeSelectionInterval(1, 1);
    EasyMock.verify(vetor);
    assertEquals(-1, selectionModel.getMinSelectionIndex());
  }

  public void testClearSelectionOnNoSelection() throws Exception {
    EasyMock.replay(vetor);
    selectionModel.clearSelection();
    selectionModel.addVetor(vetor);
    selectionModel.clearSelection();
    EasyMock.verify(vetor);
  }
}