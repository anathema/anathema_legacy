package net.sf.anathema.character.equipment.view;

import junit.framework.TestCase;
import net.sf.anathema.character.equipment.character.EquipmentAdditionalPresenter;
import net.sf.anathema.character.equipment.character.EquipmentObjectCellRenderer;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.character.equipment.impl.character.view.MagicMaterialView;
import net.sf.anathema.lib.dummy.DummyResources;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EquipmentAdditionalPresenterTest extends TestCase {

  @SuppressWarnings("unchecked")
  public void testInitPresentation() throws Exception {
    DummyResources resources = new DummyResources();
    String[] equipmentTemplateIds = new String[0];
    IEquipmentAdditionalModel model = mock(IEquipmentAdditionalModel.class);
    when(model.getNaturalWeapons()).thenReturn(new IEquipmentItem[0]);
    when(model.getEquipmentItems()).thenReturn(new IEquipmentItem[0]);
    IEquipmentAdditionalView view = mock(IEquipmentAdditionalView.class);
    when(model.getAvailableTemplateIds()).thenReturn(equipmentTemplateIds);
    IListObjectSelectionView<String> equipmentPickList = mock(IListObjectSelectionView.class);
    equipmentPickList.setObjects(equipmentTemplateIds);
    equipmentPickList.setCellRenderer(new EquipmentObjectCellRenderer());
    when(view.getEquipmentTemplatePickList()).thenReturn(equipmentPickList);
    when(view.getMagicMaterialView()).thenReturn(new MagicMaterialView());
    new EquipmentAdditionalPresenter(resources, model, view).initPresentation();
  }
}