package net.sf.anathema.character.equipment.character.view;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public interface IMagicalMaterialView {

  void initView(String label, AgnosticUIConfiguration<MagicalMaterial> renderer, MagicalMaterial[] materials);

  void setSelectedMaterial(MagicalMaterial selection, boolean viewEnabled);

  MagicalMaterial getSelectedMaterial();
}