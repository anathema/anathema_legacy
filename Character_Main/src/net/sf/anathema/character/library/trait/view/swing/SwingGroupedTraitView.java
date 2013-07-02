package net.sf.anathema.character.library.trait.view.swing;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.view.GroupedTraitView;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.character.view.ColumnCount;
import net.sf.anathema.framework.value.IntegerViewFactory;

import javax.swing.JComponent;

public class SwingGroupedTraitView implements GroupedTraitView {

  private final GroupedColumnPanel panel;
  private final IntegerViewFactory factory;

  public SwingGroupedTraitView(JComponent parent, ColumnCount columnCount, IntegerViewFactory factory) {
    this.factory = factory;
    this.panel = new GroupedColumnPanel(parent, columnCount);
  }

  @Override
  public void startNewGroup(String groupLabel) {
    panel.startNewGroup(groupLabel);
  }

  @Override
  public ExtensibleTraitView addExtensibleTraitView(String labelText, int value, int maxValue, Trait trait) {
    SimpleTraitView view = SimpleTraitView.RightAlignedWithUpperBoundsForTrait(factory, labelText, value, maxValue,
            trait);
    SwingExtensibleTraitView extensibleTraitView = new SwingExtensibleTraitView(view);
    extensibleTraitView.addComponents(panel.getCurrentColumn());
    return extensibleTraitView;
  }
}