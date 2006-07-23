package net.sf.anathema.test.character.equipment.character;

import net.sf.anathema.character.equipment.character.EquipmentAdditionalPresenter;
import net.sf.anathema.character.equipment.character.EquipmentObjectCellRenderer;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentObjectCollection;
import net.sf.anathema.character.equipment.character.model.IEquipmentTemplate;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.test.character.generic.framework.magic.stringbuilder.DummyResources;

import org.easymock.EasyMock;

public class EquipmentAdditionalPresenterTest extends BasicTestCase {

  @SuppressWarnings("unchecked")
  public void testInitPresentation() throws Exception {
    DummyResources resources = new DummyResources();
    IEquipmentTemplate[] equipmentTemplates = new IEquipmentTemplate[0];
    IEquipmentObjectCollection model = EasyMock.createNiceMock(IEquipmentObjectCollection.class);
    IEquipmentAdditionalView view = EasyMock.createNiceMock(IEquipmentAdditionalView.class);
    EasyMock.expect(model.getAvailableTemplates()).andReturn(equipmentTemplates).once();
    IListObjectSelectionView<IEquipmentTemplate> equipmentPickList = EasyMock.createMock(IListObjectSelectionView.class);
    equipmentPickList.setObjects(equipmentTemplates);
    equipmentPickList.setCellRenderer(new EquipmentObjectCellRenderer());
    EasyMock.expect(view.getEquipmentTemplatePickList()).andReturn(equipmentPickList);
    EasyMock.replay(model, view, equipmentPickList);
    new EquipmentAdditionalPresenter(resources, model, view).initPresentation();
    EasyMock.verify(model, view, equipmentPickList);
  }
}