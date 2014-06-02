package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;

public interface EquipmentView {

  VetoableObjectSelectionView<String> getEquipmentTemplatePickList();

  Tool addToolButton();

  MagicalMaterialView addMagicMaterialView(String label, AgnosticUIConfiguration<MagicalMaterial> renderer);

  PersonalizationEditView startEditingPersonalization(EquipmentPersonalizationProperties properties);

  ObjectSelectionView<IEquipmentItem> addOwnedEquipmentList(EquipmentItemRenderer renderer);

  EquipmentObjectView addItemEditView();
}