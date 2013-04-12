package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface EquipmentDescriptionPanel {

  ITextView addNameView(String label);

  ITextView addDescriptionView(String label);

  IObjectSelectionView<MaterialComposition> addCompositionView(String label, AgnosticUIConfiguration<MaterialComposition> ui);

  IObjectSelectionView<MagicalMaterial> addMaterialView(String label, AgnosticUIConfiguration<MagicalMaterial> ui);

  CostSelectionView addCostView(String label);
}