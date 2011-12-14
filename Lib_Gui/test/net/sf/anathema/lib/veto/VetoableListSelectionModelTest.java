// Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.veto;

import javax.swing.ListSelectionModel;

import net.sf.anathema.lib.gui.list.veto.IVetor;
import net.sf.anathema.lib.gui.list.veto.VetoableListSelectionModel;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// NOT_PUBLISHED
public class VetoableListSelectionModelTest {

  private VetoableListSelectionModel selectionModel;
  private IVetor vetor;

  @Before
  public void setUp() throws Exception {
    selectionModel = new VetoableListSelectionModel();
    vetor = EasyMock.createMock(IVetor.class);
  }

  @Test
  public void testIsSingleSelectionMode() throws Exception {
    Assert.assertEquals(ListSelectionModel.SINGLE_SELECTION, new VetoableListSelectionModel().getSelectionMode());
  }

  @Test
  public void testVeto() throws Exception {
    EasyMock.expect(vetor.vetos()).andReturn(true);
    EasyMock.replay(vetor);
    selectionModel.addVetor(vetor);
    selectionModel.addSelectionInterval(1, 2);
    EasyMock.verify(vetor);
    Assert.assertEquals(-1, selectionModel.getMinSelectionIndex());
  }

  @Test
  public void testRemoveVetor() throws Exception {
    EasyMock.replay(vetor);
    selectionModel.addVetor(vetor);
    selectionModel.removeVetor(vetor);
    selectionModel.addSelectionInterval(1, 2);
    EasyMock.verify(vetor);
  }

  @Test
  public void testAddSelectionIntervall() throws Exception {
    EasyMock.expect(vetor.vetos()).andReturn(false);
    EasyMock.replay(vetor);
    selectionModel.addVetor(vetor);
    selectionModel.addSelectionInterval(1, 2);
    EasyMock.verify(vetor);
    Assert.assertEquals(2, selectionModel.getMinSelectionIndex());
  }

  @Test
  public void testRemoveSelectionIntervall() throws Exception {
    EasyMock.expect(vetor.vetos()).andReturn(false);
    EasyMock.replay(vetor);
    selectionModel.setSelectionInterval(1, 1);
    selectionModel.addVetor(vetor);
    selectionModel.removeSelectionInterval(1, 1);
    EasyMock.verify(vetor);
    Assert.assertEquals(-1, selectionModel.getMinSelectionIndex());
  }

  @Test
  public void testRemoveSelectionIntervallOnNoSelection() throws Exception {
    EasyMock.replay(vetor);
    selectionModel.clearSelection();
    selectionModel.addVetor(vetor);
    selectionModel.removeSelectionInterval(1, 1);
    EasyMock.verify(vetor);
    Assert.assertEquals(-1, selectionModel.getMinSelectionIndex());
  }

  @Test
  public void testClearSelectionOnNoSelection() throws Exception {
    EasyMock.replay(vetor);
    selectionModel.clearSelection();
    selectionModel.addVetor(vetor);
    selectionModel.clearSelection();
    EasyMock.verify(vetor);
  }
}