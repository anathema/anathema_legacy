package net.sf.anathema.hero.languages.display.view;

import net.miginfocom.layout.CC;
import net.sf.anathema.hero.languages.display.presenter.ObjectSelectionViewWithTool;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxSelectionViewWithTool<V> implements ObjectSelectionViewWithTool<V> {

  private final ComboBoxSelectionView<V> comboBox;
  private final MigPane buttonPanel = new MigPane(withoutInsets());

  public FxSelectionViewWithTool(AgnosticUIConfiguration<V> configuration, String labelText) {
    comboBox = new ComboBoxSelectionView<>(labelText, configuration);
    comboBox.makeEditable();
  }

  @Override
  public void setSelectedObject(V object) {
    comboBox.setSelectedObject(object);
  }

  @Override
  public void addObjectSelectionChangedListener(ObjectValueListener<V> listener) {
    comboBox.addObjectSelectionChangedListener(listener);
  }

  @Override
  public void removeObjectSelectionChangedListener(ObjectValueListener<V> listener) {
    comboBox.removeObjectSelectionChangedListener(listener);
  }

  @Override
  public void setObjects(V[] objects) {
    comboBox.setObjects(objects);
  }

  @Override
  public void setObjects(Collection<V> objects) {
    comboBox.setObjects(objects);
  }

  @Override
  public V getSelectedObject() {
    return comboBox.getSelectedObject();
  }

  @Override
  public void setEnabled(boolean enabled) {
    throw new UnsupportedOperationException("Lack of Interface Segregation detected.");
  }

  @Override
  public Tool addTool() {
    final FxButtonTool tool = FxButtonTool.ForToolbar();
    buttonPanel.add(tool.getNode());
    return tool;
  }

  public void addTo(final MigPane selectionPanel) {
    selectionPanel.add(comboBox.getNode());
    selectionPanel.add(buttonPanel, new CC().alignY("center"));
  }
}