package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.IVetoableObjectSelectionView;

public interface EquipmentView {

  IVetoableObjectSelectionView<String> getEquipmentTemplatePickList();

  EquipmentObjectView addEquipmentObjectView();

  void removeEquipmentObjectView(EquipmentObjectView objectView);

  Tool addToolButton();

  MagicalMaterialView getMagicMaterialView();

  void revalidateEquipmentViews();

  PersonalizationEditView startEditingPersonalization(EquipmentPersonalizationProperties properties);
}