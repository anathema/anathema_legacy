package net.sf.anathema.character.library.trait.view;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.character.view.ColumnCount;
import net.sf.anathema.framework.value.IntegerViewFactory;

import javax.swing.JComponent;

public class GroupedTraitView {

  private final GroupedColumnPanel panel;

  public GroupedTraitView(JComponent parent, ColumnCount columnCount) {
    panel = new GroupedColumnPanel(parent, columnCount);
  }

  public void startNewGroup(String groupLabel) {
    panel.startNewGroup(groupLabel);
  }

  public ExtensibleTraitView addExtensibleTraitView(String labelText, int value, int maxValue, Trait trait, IntegerViewFactory factory) {
    SimpleTraitView view = new SimpleTraitView(labelText, value, maxValue, trait, factory);
    SwingExtensibleTraitView extensibleTraitView = new SwingExtensibleTraitView(view);
    extensibleTraitView.addComponents(panel.getCurrentColumn());
    return extensibleTraitView;
  }
}