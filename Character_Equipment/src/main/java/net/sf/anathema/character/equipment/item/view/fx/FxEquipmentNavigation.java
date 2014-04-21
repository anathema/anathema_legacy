package net.sf.anathema.character.equipment.item.view.fx;

import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.framework.repository.tree.FxVetor;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.list.veto.Vetor;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.platform.fx.ListSelectionView;
import net.sf.anathema.platform.fx.Navigation;

public class FxEquipmentNavigation extends Navigation implements EquipmentNavigation {

  private ListSelectionView<String> listView = new ListSelectionView<>();

  public FxEquipmentNavigation() {
    addContainerToNavigation(listView.getNode());
  }

  @Override
  public VetoableObjectSelectionView<String> getTemplateListView() {
    return listView;
  }

  @Override
  public Tool addEditTemplateTool() {
    return addTool();
  }

  @Override
  public Vetor createVetor(String title, String message) {
    return new FxVetor(title, message);
  }
}