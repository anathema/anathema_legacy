package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.IVetoableObjectSelectionView;

public interface EquipmentNavigation {
  IVetoableObjectSelectionView<String> getTemplateListView();

  Tool addEditTemplateTool();
}