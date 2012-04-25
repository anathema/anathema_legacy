package net.sf.anathema.charmentry.view;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.charmentry.presenter.view.ICharmTypeEntryView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;

public class CharmTypeEntryView implements ICharmTypeEntryView {

  private final StandardPanelBuilder builder = new StandardPanelBuilder();
  private JComponent content;

  @Override
  public IObjectSelectionView<CharmType> addComboBoxRow(String typeLabel, ListCellRenderer renderer, CharmType[] objects) {
    return builder.addObjectSelectionView(typeLabel, renderer, objects);
  }

  @Override
  public JComponent getContent() {
    if (content == null) {
      content = builder.getUntitledContent();
    }
    return content;
  }

  @Override
  public void requestFocus() {
    // Nothing to do
  }

  @Override
  public void dispose() {
    // Nothing to do
  }

  @Override
  public JToggleButton addCheckBoxRow(String label) {
    final JCheckBox box = new JCheckBox(label);
    builder.addDialogComponent(new IDialogComponent() {
      @Override
      public void fillInto(JPanel panel, int columnCount) {
        panel.add(box);
      }

      @Override
      public int getColumnCount() {
        return 1;
      }

    });
    return box;
  }
}