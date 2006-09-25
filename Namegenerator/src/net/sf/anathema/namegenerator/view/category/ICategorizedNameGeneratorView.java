package net.sf.anathema.namegenerator.view.category;

import javax.swing.ListCellRenderer;

import net.sf.anathema.lib.gui.IView;

public interface ICategorizedNameGeneratorView extends IView {

  public void initGui(Object[] categories, int columnCount, ListCellRenderer renderer);

  public Object[] getSelectedCategories();
}