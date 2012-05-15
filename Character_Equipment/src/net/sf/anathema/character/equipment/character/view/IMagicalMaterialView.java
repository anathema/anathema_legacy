package net.sf.anathema.character.equipment.character.view;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.gui.IView;

import javax.swing.ListCellRenderer;

public interface IMagicalMaterialView extends IView {

  void initView(String label, ListCellRenderer renderer, MagicalMaterial[] materials);

  void setSelectedMaterial(MagicalMaterial selection, boolean viewEnabled);

  MagicalMaterial getSelectedMaterial();
}