package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface EquipmentDescriptionPanel {

  ITextView addNameView(String label);

  ITextView addDescriptionView(String label);

  ObjectSelectionView<MaterialComposition> addCompositionView(String label, AgnosticUIConfiguration<MaterialComposition> ui);

  ObjectSelectionView<MagicalMaterial> addMaterialView(String label, AgnosticUIConfiguration<MagicalMaterial> ui);

  CostSelectionView addCostView(String label);
}