package net.sf.anathema.character.equipment.character.view;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;

public interface IMagicalMaterialView {

  void initView(String label, TechnologyAgnosticUIConfiguration<MagicalMaterial> renderer, MagicalMaterial[] materials);

  void setSelectedMaterial(MagicalMaterial selection, boolean viewEnabled);

  MagicalMaterial getSelectedMaterial();
}