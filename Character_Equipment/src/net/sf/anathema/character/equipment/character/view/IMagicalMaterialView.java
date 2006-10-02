package net.sf.anathema.character.equipment.character.view;

import javax.swing.ListCellRenderer;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.gui.IView;

public interface IMagicalMaterialView extends IView {

  public void initView(String label, ListCellRenderer renderer, MagicalMaterial[] materials, MagicalMaterial selection);

  public void setSelectedMaterial(MagicalMaterial selection);

  public MagicalMaterial getSelectedMaterial();
}