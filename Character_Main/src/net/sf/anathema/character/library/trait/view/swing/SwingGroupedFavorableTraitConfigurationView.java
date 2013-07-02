package net.sf.anathema.character.library.trait.view.swing;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.library.trait.view.GroupedTraitView;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.character.view.ColumnCount;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.value.IntegerViewFactory;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class SwingGroupedFavorableTraitConfigurationView implements GroupedFavorableTraitConfigurationView, IView {

  private GroupedTraitView groupedTraitView;
  private final IntegerViewFactory markerIntValueDisplayFactory;
  private final JComponent parent = new JPanel();

  public SwingGroupedFavorableTraitConfigurationView(IntegerViewFactory factoryWithMarker) {
    this.markerIntValueDisplayFactory = factoryWithMarker;
  }

  @Override
  public void initGui(ColumnCount columnCount) {
    this.groupedTraitView = new SwingGroupedTraitView(parent, columnCount, markerIntValueDisplayFactory);
  }

  @Override
  public ExtensibleTraitView addExtensibleTraitView(String labelText, int value, int maxValue, Trait trait) {
    return groupedTraitView.addExtensibleTraitView(labelText, value, maxValue, trait);
  }

  @Override
  public JComponent getComponent() {
    return parent;
  }

  @Override
  public void startNewTraitGroup(String groupLabel) {
    groupedTraitView.startNewGroup(groupLabel);
  }
}