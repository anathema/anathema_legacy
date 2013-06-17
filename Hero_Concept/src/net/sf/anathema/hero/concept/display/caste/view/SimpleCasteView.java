package net.sf.anathema.hero.concept.display.caste.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.swing.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import java.awt.Color;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class SimpleCasteView implements CasteView, IView {
  private final JPanel conceptPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(2).debug(1)));
  private final JPanel content = new JPanel(new MigLayout(withoutInsets().wrapAfter(1)));

  public SimpleCasteView() {
    content.add(conceptPanel, new CC().growX());
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public <V> IObjectSelectionView<V> addObjectSelectionView(String labelText, V[] objects, ListCellRenderer renderer, boolean editable) {
    ObjectSelectionView<V> selectionView = new ObjectSelectionView<>(labelText, renderer, editable, objects);
    selectionView.getComboBox().getEditor().getEditorComponent().setEnabled(true);
    selectionView.setDisabledLabelColor(Color.DARK_GRAY);
    selectionView.addTo(conceptPanel, new CC().growX().pushX());
    return selectionView;
  }
}