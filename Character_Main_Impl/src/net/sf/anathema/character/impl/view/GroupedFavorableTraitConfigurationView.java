package net.sf.anathema.character.impl.view;

import javax.swing.JPanel;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.trait.view.GroupedTraitView;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.framework.presenter.view.AbstractInitializableContentView;

public class GroupedFavorableTraitConfigurationView extends AbstractInitializableContentView<Object> implements
    IGroupedFavorableTraitConfigurationView {

  private final GroupedTraitView groupedTraitView;
  private final IIntValueDisplayFactory markerIntValueDisplayFactory;
  private final IIntValueDisplayFactory markerLessIntValueDisplayFactory;

  public GroupedFavorableTraitConfigurationView(
      int columnCount,
      IIntValueDisplayFactory factoryWithMarker,
      IIntValueDisplayFactory factoryWithoutMarker) {
    this.groupedTraitView = new GroupedTraitView(columnCount);
    this.markerIntValueDisplayFactory = factoryWithMarker;
    this.markerLessIntValueDisplayFactory = factoryWithoutMarker;
  }

  public IToggleButtonTraitView<SimpleTraitView> addTraitView(
      String labelText,
      int value,
      int maxValue,
      boolean selected,
      IIconToggleButtonProperties properties) {
    return groupedTraitView.addTraitView(labelText, value, maxValue, selected, properties, markerIntValueDisplayFactory);
  }

  public IToggleButtonTraitView< ? > addMarkerLessTraitView(
      String labelText,
      int value,
      int maxValue,
      boolean selected,
      IIconToggleButtonProperties properties) {
    return groupedTraitView.addTraitView(
        labelText,
        value,
        maxValue,
        selected,
        properties,
        markerLessIntValueDisplayFactory);
  }

  @Override
  protected void createContent(JPanel component, Object object) {
    groupedTraitView.addOverallView(component);
  }

  public void startNewTraitGroup(String groupLabel) {
    groupedTraitView.startNewGroup(groupLabel);
  }
}