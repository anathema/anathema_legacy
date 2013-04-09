package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

public interface EquipmentNavigation {
  IListObjectSelectionView<String> getTemplateListView();

  Tool addEditTemplateTool();
}