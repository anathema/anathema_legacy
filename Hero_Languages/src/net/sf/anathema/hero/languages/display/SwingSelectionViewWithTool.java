package net.sf.anathema.hero.languages.display;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.gui.widgets.ColoredJComboBox;
import net.sf.anathema.swing.interaction.ActionInteraction;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class SwingSelectionViewWithTool<V> implements ObjectSelectionViewWithTool<V> {

  private final JComboBox comboBox;
  private final JPanel buttonPanel = new JPanel(new MigLayout(withoutInsets()));
  private final JLabel label;

  public SwingSelectionViewWithTool(
          ListCellRenderer renderer,
          String labelText) {
    this.comboBox = new ColoredJComboBox(new DefaultComboBoxModel(new Object[0]));
    comboBox.setEditor(new BasicComboBoxEditor());
    comboBox.setEditable(true);
    this.label = new JLabel(labelText);
    this.comboBox.setRenderer(renderer);
  }

  @Override
  public Tool addTool() {
    ActionInteraction actionInteraction = new ActionInteraction();
    actionInteraction.addTo(new AddToButtonPanel(buttonPanel));
    return actionInteraction;
  }

  public void addComponents(JPanel panel) {
    panel.add(label);
    panel.add(comboBox, new CC().growX().pushX());
    panel.add(buttonPanel);
  }

  @Override
  public void addObjectSelectionChangedListener(final ObjectValueListener<V> listener) {
    comboBox.addItemListener(new ItemListener() {
      @Override
      @SuppressWarnings("unchecked")
      public void itemStateChanged(ItemEvent e) {
        listener.valueChanged((V) comboBox.getSelectedItem());
      }
    });
  }

  @Override
  public void removeObjectSelectionChangedListener(ObjectValueListener<V> listener) {
    throw new NotYetImplementedException();
  }

  @Override
  @SuppressWarnings("unchecked")
  public V getSelectedObject() {
    return (V) comboBox.getSelectedItem();
  }

  @Override
  public boolean isObjectSelected() {
    return getSelectedObject() != null;
  }

  @Override
  public void setEnabled(boolean enabled) {
    throw new UnsupportedOperationException("Lack of Interface Segregation");
  }

  @Override
  public void setObjects(V[] objects) {
    Object selectedItem = comboBox.getSelectedItem();
    DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
    model.removeAllElements();
    for (Object object : objects) {
      model.addElement(object);
    }
    comboBox.setSelectedItem(selectedItem);
  }

  @Override
  public void setSelectedObject(Object object) {
    comboBox.setSelectedItem(object);
  }
}