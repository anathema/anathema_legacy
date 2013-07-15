package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.equipment.core.MagicalMaterial;

public interface MagicalMaterialView {

  void setMaterials(MagicalMaterial[] materials);

  void setSelectedMaterial(MagicalMaterial selection, boolean viewEnabled);

  MagicalMaterial getSelectedMaterial();
}