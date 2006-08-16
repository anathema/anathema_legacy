package net.sf.anathema.character.impl.view.basic;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.view.basic.IButtonControlledComboEditView;
import net.sf.anathema.lib.control.objectvalue.ITwoObjectsValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;

public class ButtonControlledComboEditView<V> implements IButtonControlledComboEditView<V> {

  private final ChangeableJComboBox<V> comboBox;
  private final JButton addButton;
  private final JTextField text;

  public ButtonControlledComboEditView(V[] objects, Icon addIcon) {
    this.comboBox = new ChangeableJComboBox<V>(objects, false);
    this.text = new JTextField();
    this.addButton = new JButton(null, addIcon);
    addButton.setPreferredSize(new Dimension(addIcon.getIconWidth() + 4, addIcon.getIconHeight() + 4));
  }

  public void addTo(GridDialogPanel panel, final String labelText, final ListCellRenderer renderer) {
    panel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 3;
      }

      public void fillInto(JPanel layoutedPanel, int columnCount) {
        comboBox.setRenderer(renderer);
        layoutedPanel.add(new JLabel(labelText));
        layoutedPanel.add(new EndOfLineMarkerComponent());
        layoutedPanel.add(comboBox.getComponent());
        layoutedPanel.add(text, GridDialogLayoutData.FILL_HORIZONTAL);
        layoutedPanel.add(addButton, GridDialogLayoutData.RIGHT);
      }
    });
  }

  public void setSelectedObject(V object) {
    comboBox.setSelectedObject(object);
  }

  public void addObjectSelectionChangedListener(final ITwoObjectsValueChangedListener<V, String> listener) {
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.valueChanged(null, null, comboBox.getSelectedObject(), text.getText());
      }
    });
    text.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          listener.valueChanged(null, null, comboBox.getSelectedObject(), text.getText());
        }
      }
    });
  }

  public void setText(String text) {
    this.text.setText(text);
  }
}