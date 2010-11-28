package net.sf.anathema.character.equipment.character.view;

import javax.swing.ListCellRenderer;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.gui.IView;

public interface IMagicalMaterialView extends IView {

  public void initView(String label, ListCellRenderer renderer, MagicalMaterial[] materials);

  public void setSelectedMaterial(MagicalMaterial selection, boolean viewEnabled);

  public MagicalMaterial getSelectedMaterial();
}