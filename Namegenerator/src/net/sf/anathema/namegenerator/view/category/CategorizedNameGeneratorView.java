package net.sf.anathema.namegenerator.view.category;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayout;

public class CategorizedNameGeneratorView implements ICategorizedNameGeneratorView {

  private JComboBox[] comboBoxes;
  private JPanel content;

  public void initGui(Object[] categories, int columnCount, ListCellRenderer renderer) {
    content = new JPanel(new GridDialogLayout(columnCount, true));
    comboBoxes = new JComboBox[columnCount];
    for (int index = 0; index < comboBoxes.length; index++) {
      comboBoxes[index] = new JComboBox(categories);
      comboBoxes[index].setRenderer(renderer);
      content.add(comboBoxes[index]);
    }
  }

  public JComponent getComponent() {
    return content;
  }

  public Object[] getSelectedCategories() {
    Object[] categories = new Object[comboBoxes.length];
    for (int index = 0; index < categories.length; index++) {
      categories[index] = comboBoxes[index].getSelectedItem();
    }
    return categories;
  }
}