package net.sf.anathema.namegenerator.view.category;

import net.sf.anathema.framework.swing.IView;

import javax.swing.ListCellRenderer;

public interface ICategorizedNameGeneratorView extends IView {

  void initGui(Object[] categories, int columnCount, ListCellRenderer renderer);

  Object[] getSelectedCategories();
}