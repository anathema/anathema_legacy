package net.sf.anathema.namegenerator.view.category;

import javax.swing.JComponent;
import javax.swing.ListCellRenderer;

public interface ICategorizedNameGeneratorView {

  public void initGui(Object[] categories, int columnCount, ListCellRenderer renderer);

  public JComponent getContent();

  public Object[] getSelectedCategories();

}