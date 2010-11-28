package net.sf.anathema.test.character.equipment.character;

import net.sf.anathema.character.equipment.character.EquipmentAdditionalPresenter;
import net.sf.anathema.character.equipment.character.EquipmentObjectCellRenderer;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.test.character.generic.framework.magic.stringbuilder.DummyResources;

import org.easymock.EasyMock;

public class EquipmentAdditionalPresenterTest extends BasicTestCase {

  @SuppressWarnings("unchecked")
  public void testInitPresentation() throws Exception {
    DummyResources resources = new DummyResources();
    String[] equipmentTemplateIds = new String[0];
    IEquipmentAdditionalModel model = EasyMock.createNiceMock(IEquipmentAdditionalModel.class);
    IEquipmentAdditionalView view = EasyMock.createNiceMock(IEquipmentAdditionalView.class);
    EasyMock.expect(model.getAvailableTemplateIds()).andReturn(equipmentTemplateIds).once();
    IListObjectSelectionView<String> equipmentPickList = EasyMock.createMock(IListObjectSelectionView.class);
    equipmentPickList.setObjects(equipmentTemplateIds);
    equipmentPickList.setCellRenderer(new EquipmentObjectCellRenderer());
    EasyMock.expect(view.getEquipmentTemplatePickList()).andReturn(equipmentPickList);
    EasyMock.replay(model, view, equipmentPickList);
    new EquipmentAdditionalPresenter(resources, model, view).initPresentation();
    EasyMock.verify(model, view, equipmentPickList);
  }
}