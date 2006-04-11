package net.sf.anathema.framework.presenter.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.widgets.ColoredJComboBox;

public class ButtonControlledObjectSelectionView implements IObjectSelectionView {

  private final JComboBox comboBox;
  private final JButton addButton;

  public ButtonControlledObjectSelectionView(Object[] objects, ComboBoxEditor editor, Icon addIcon) {
    this.comboBox = new ColoredJComboBox(new DefaultComboBoxModel(objects));
    this.comboBox.setEditable(true);
    this.comboBox.setEditor(editor);
    addButton = new JButton(null, addIcon);
    addButton.setPreferredSize(new Dimension(addIcon.getIconWidth() + 4, addIcon.getIconHeight() + 4));
  }

  public void addTo(final String labelText, final ListCellRenderer renderer, IGridDialogPanel panel) {
    panel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 3;
      }

      public void fillInto(JPanel layoutPanel, int columnCount) {
        layoutPanel.add(new JLabel(labelText));
        comboBox.setRenderer(renderer);
        layoutPanel.add(comboBox, GridDialogLayoutData.FILL_HORIZONTAL);
        layoutPanel.add(addButton);
      }
    });
  }

  public void setSelectedObject(Object object) {
    comboBox.setSelectedItem(object);
  }

  public void addObjectSelectionChangedListener(final IObjectValueChangedListener listener) {
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.valueChanged(comboBox.getSelectedItem());
      }
    });
    comboBox.getEditor().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.valueChanged(comboBox.getEditor().getItem());
      }
    });
  }

  public void setObjects(Object[] objects) {
    Object selectedItem = comboBox.getSelectedItem();
    DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
    model.removeAllElements();
    for (Object object : objects) {
      model.addElement(object);
    }
    comboBox.setSelectedItem(selectedItem);
  }
}