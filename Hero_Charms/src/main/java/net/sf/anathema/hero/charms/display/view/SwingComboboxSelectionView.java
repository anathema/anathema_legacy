package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.ui.ConfigurableListCellRenderer;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.util.Identifier;

import javax.swing.JComponent;

public class SwingComboboxSelectionView implements ObjectSelectionView<Identifier> {
  private final ConfigurableListCellRenderer renderer;
  private IChangeableJComboBox<Identifier> comboBox = new ChangeableJComboBox<>();

  public SwingComboboxSelectionView(AgnosticUIConfiguration<Identifier> configuration) {
    this.renderer = new ConfigurableListCellRenderer(configuration);
    comboBox.setRenderer(renderer);
  }

  @Override
  public void setSelectedObject(Identifier object) {
    comboBox.setSelectedObject(object);
  }

  @Override
  public void addObjectSelectionChangedListener(ObjectValueListener<Identifier> listener) {
    comboBox.addObjectSelectionChangedListener(listener);
  }

  @Override
  public void removeObjectSelectionChangedListener(ObjectValueListener<Identifier> listener) {
    comboBox.removeObjectSelectionChangeListener(listener);
  }

  @Override
  public void setObjects(Identifier[] objects) {
    comboBox.setObjects(objects);
  }

  @Override
  public Identifier getSelectedObject() {
    return comboBox.getSelectedObject();
  }

  @Override
  public boolean isObjectSelected() {
    return comboBox.getSelectedObject() != null;
  }

  @Override
  public void setEnabled(boolean enabled) {
    comboBox.getComponent().setEnabled(enabled);
  }

  public JComponent getComponent() {
    return comboBox.getComponent();
  }

  public void sizeComboboxFor(Identifier[] objects) {
    ChangeableJComboBox<Identifier> box = new ChangeableJComboBox<>(objects);
    box.setRenderer(renderer);
    comboBox.setPreferredSize(box.getPreferredSize());
  }
}