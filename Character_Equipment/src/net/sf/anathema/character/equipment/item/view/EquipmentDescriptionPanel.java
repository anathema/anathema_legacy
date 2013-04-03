package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface EquipmentDescriptionPanel {

  ITextView addNameView(String label);

  ITextView addDescriptionView(String label);

  IObjectSelectionView<MaterialComposition> addCompositionView(String label, TechnologyAgnosticUIConfiguration<MaterialComposition> ui);

  IObjectSelectionView<MagicalMaterial> addMaterialView(String label, TechnologyAgnosticUIConfiguration<MagicalMaterial> ui);

  CostSelectionView addCostView(String label);
}