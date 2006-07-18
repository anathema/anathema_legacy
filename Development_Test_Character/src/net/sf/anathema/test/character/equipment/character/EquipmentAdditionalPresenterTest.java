package net.sf.anathema.test.character.equipment.character;

import net.sf.anathema.character.equipment.character.EquipmentAdditionalPresenter;
import net.sf.anathema.character.equipment.character.EquipmentObjectCellRenderer;
import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.equipment.character.model.IEquipmentObjectCollection;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.test.character.generic.framework.magic.stringbuilder.DummyResources;

import org.easymock.EasyMock;

public class EquipmentAdditionalPresenterTest extends BasicTestCase {

  @SuppressWarnings("unchecked")
  public void testInitPresentation() throws Exception {
    DummyResources resources = new DummyResources();
    IEquipmentObject[] equipmentObjects = new IEquipmentObject[0];
    IEquipmentObjectCollection model = EasyMock.createNiceMock(IEquipmentObjectCollection.class);
    IEquipmentAdditionalView view = EasyMock.createNiceMock(IEquipmentAdditionalView.class);
    EasyMock.expect(model.getAvailableObjects()).andReturn(equipmentObjects).once();
    IListObjectSelectionView<IEquipmentObject> equipmentPickList = EasyMock.createMock(IListObjectSelectionView.class);
    equipmentPickList.setObjects(equipmentObjects);
    equipmentPickList.setCellRenderer(new EquipmentObjectCellRenderer());
    EasyMock.expect(view.getEquipmentObjectPickList()).andReturn(equipmentPickList);
    EasyMock.replay(model, view, equipmentPickList);
    new EquipmentAdditionalPresenter(resources, model, view).initPresentation();
    EasyMock.verify(model, view, equipmentPickList);
  }
}