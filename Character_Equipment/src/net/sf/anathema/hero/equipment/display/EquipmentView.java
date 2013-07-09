package net.sf.anathema.hero.equipment.display;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

public interface EquipmentView {

  IListObjectSelectionView<String> getEquipmentTemplatePickList();

  IEquipmentObjectView addEquipmentObjectView();

  void removeEquipmentObjectView(IEquipmentObjectView objectView);

  Tool addToolButton();

  IMagicalMaterialView getMagicMaterialView();

  void revalidateEquipmentViews();

  PersonalizationEditView startEditingPersonalization(EquipmentPersonalizationProperties properties);
}