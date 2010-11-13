package net.sf.anathema.character.meritsflaws.view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.meritsflaws.presenter.view.IPerkDetailsView;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class MultiValuePerkDetailsView implements IPerkDetailsView {

  private final Integer[] values;
  private JPanel content;
  private final String pointValueLabel;
  private final ChangeControl changeControl = new ChangeControl();
  private JComboBox pointBox;

  public MultiValuePerkDetailsView(Integer[] values, String label) {
    this.values = values;
    this.pointValueLabel = label;
  }

  public JComponent getComponent() {
    if (content == null) {
      content = createContent();
    }
    return content;
  }

  private JPanel createContent() {
    JPanel panel = new JPanel(new GridDialogLayout(3, false));
    panel.add(new JLabel(pointValueLabel));
    pointBox = new JComboBox(values);
    pointBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        changeControl.fireChangedEvent();
      }
    });
    panel.add(pointBox);
    return panel;
  }

  public boolean isComplete() {
    return pointBox.getSelectedItem() != null;
  }

  public void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  public int getSelectedValue() {
    if (!isComplete()) {
      return 0;
    }
    return (Integer) pointBox.getSelectedItem();
  }
}