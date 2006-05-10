package net.sf.anathema.charmentry.demo.view;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.charmentry.demo.ICharmTypeEntryView;
import net.sf.anathema.charmentry.demo.IReflexiveCharmSpecialsView;
import net.sf.anathema.charmentry.demo.ISimpleCharmSpecialsView;
import net.sf.anathema.charmentry.view.ReflexiveCharmSpecialsView;
import net.sf.anathema.charmentry.view.SimpleCharmSpecialsView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;

public class CharmTypeEntryView implements ICharmTypeEntryView {

  private final StandardPanelBuilder builder = new StandardPanelBuilder();
  private JComponent content;

  public IObjectSelectionView addComboBoxRow(String typeLabel, ListCellRenderer renderer, Object[] objects) {
    return builder.addObjectSelectionView(typeLabel, renderer, objects);
  }

  public JComponent getContent() {
    if (content == null) {
      content = builder.getUntitledContent();
    }
    return content;
  }

  public void requestFocus() {
    // Nothing to do
  }

  public void dispose() {
    // Nothing to do
  }

  public JToggleButton addCheckBoxRow(String label) {
    final JCheckBox box = new JCheckBox(label);
    builder.addDialogComponent(new IDialogComponent() {
      public void fillInto(JPanel panel, int columnCount) {
        panel.add(box);
      }

      public int getColumnCount() {
        return 1;
      }

    });
    return box;
  }

  public ISimpleCharmSpecialsView addSimpleCharmSpecialsView(
      String modifiersLabel,
      String speedLabel,
      String defenseLabel) {
    SimpleCharmSpecialsView view = new SimpleCharmSpecialsView(modifiersLabel, speedLabel, defenseLabel);
    builder.addDialogComponent(view);
    return view;
  }

  public IReflexiveCharmSpecialsView addReflexiveCharmSpecialsView(
      String stepLabel,
      String defaultLabel,
      String defensiveLabel,
      String splitLabel) {
    ReflexiveCharmSpecialsView view = new ReflexiveCharmSpecialsView(
        stepLabel,
        defaultLabel,
        defensiveLabel,
        splitLabel);
    builder.addDialogComponent(view);
    return view;
  }
}