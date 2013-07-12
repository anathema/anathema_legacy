package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public interface MagicalMaterialView {

  void initView(String label, AgnosticUIConfiguration<MagicalMaterial> renderer, MagicalMaterial[] materials);

  void setSelectedMaterial(MagicalMaterial selection, boolean viewEnabled);

  MagicalMaterial getSelectedMaterial();
}