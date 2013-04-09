package net.sf.anathema.character.equipment.item.view.fx;

import javafx.scene.control.ListView;
import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.platform.fx.Navigation;

public class FxEquipmentNavigation extends Navigation implements EquipmentNavigation {

  private final ListView<String> listView = new ListView<>();

  public FxEquipmentNavigation() {
    addElementToNavigation(listView);
  }

  @Override
  public IListObjectSelectionView<String> getTemplateListView() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Tool addEditTemplateTool() {
    return addTool();
  }
}
