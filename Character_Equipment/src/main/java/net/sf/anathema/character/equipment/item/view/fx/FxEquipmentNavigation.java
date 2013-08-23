package net.sf.anathema.character.equipment.item.view.fx;

import javafx.application.Platform;
import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.platform.fx.ListSelectionView;
import net.sf.anathema.platform.fx.Navigation;

public class FxEquipmentNavigation extends Navigation implements EquipmentNavigation {

  private ListSelectionView<String> listView = new ListSelectionView<>();

  public FxEquipmentNavigation() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        addContainerToNavigation(listView.getNode());
      }
    });
  }

  @Override
  public VetoableObjectSelectionView<String> getTemplateListView() {
    return listView;
  }

  @Override
  public Tool addEditTemplateTool() {
    return addTool();
  }
}