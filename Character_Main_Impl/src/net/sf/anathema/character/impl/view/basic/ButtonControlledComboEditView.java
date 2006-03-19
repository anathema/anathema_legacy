package net.sf.anathema.character.impl.view.basic;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.view.basic.IButtonControlledComboEditView;
import net.sf.anathema.lib.control.objectvalue.ITwoObjectsValueChangedListener;

public class ButtonControlledComboEditView implements IButtonControlledComboEditView {

  private final JComboBox comboBox;
  private final JButton addButton;
  private final JTextField text;
  private final boolean headerRow;

  public ButtonControlledComboEditView(Object[] objects, int textFieldWidth, Icon addIcon, boolean headerRow) {
    this.headerRow = headerRow;
    this.comboBox = new JComboBox(objects);
    this.comboBox.setEditable(false);
    this.text = new JTextField(textFieldWidth);
    addButton = new JButton(null, addIcon);
    addButton.setPreferredSize(new Dimension(addIcon.getIconWidth() + 4, addIcon.getIconHeight() + 4));
  }

  public void addTo(GridDialogPanel panel, final String labelText, final ListCellRenderer renderer) {
    panel.add(new IDialogComponent() {

      public int getColumnCount() {
        return headerRow ? 3 : 4;
      }

      public void fillInto(JPanel layoutedPanel, int columnCount) {
        comboBox.setRenderer(renderer);
        layoutedPanel.add(new JLabel(labelText));
        if (headerRow) {
          layoutedPanel.add(new EndOfLineMarkerComponent());
        }
        layoutedPanel.add(comboBox);
        layoutedPanel.add(text);
        layoutedPanel.add(addButton);
      }
    });
  }

  public void setSelectedObject(Object object) {
    comboBox.setSelectedItem(object);
  }

  public void addObjectSelectionChangedListener(final ITwoObjectsValueChangedListener listener) {
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.valueChanged(null, null, comboBox.getSelectedItem(), text.getText());
      }
    });
    text.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          listener.valueChanged(null, null, comboBox.getSelectedItem(), text.getText());
        }
      }
    });
  }

  public void setText(String text) {
    this.text.setText(text);
  }
}