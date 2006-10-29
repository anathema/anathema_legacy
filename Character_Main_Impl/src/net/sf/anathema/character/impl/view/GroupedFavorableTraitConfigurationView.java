package net.sf.anathema.character.impl.view;

import javax.swing.JPanel;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.trait.view.FrontToggleButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.framework.presenter.view.AbstractContentView;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.GroupedGridDialogPanel;

public class GroupedFavorableTraitConfigurationView extends AbstractContentView<Object> implements
    IGroupedFavorableTraitConfigurationView {

  private final GroupedGridDialogPanel groupedTraitView;
  private final IIntValueDisplayFactory markerIntValueDisplayFactory;
  private final IIntValueDisplayFactory markerLessIntValueDisplayFactory;

  public GroupedFavorableTraitConfigurationView(
      int columnCount,
      IIntValueDisplayFactory factoryWithMarker,
      IIntValueDisplayFactory factoryWithoutMarker) {
    this.groupedTraitView = new GroupedGridDialogPanel(columnCount);
    this.markerIntValueDisplayFactory = factoryWithMarker;
    this.markerLessIntValueDisplayFactory = factoryWithoutMarker;
  }

  public IToggleButtonTraitView<SimpleTraitView> addTraitView(
      String labelText,
      int value,
      int maxValue,
      boolean selected,
      IIconToggleButtonProperties properties) {
    return createTraitView(labelText, value, maxValue, selected, properties, markerIntValueDisplayFactory);
  }

  public IToggleButtonTraitView< ? > addMarkerLessTraitView(
      String labelText,
      int value,
      int maxValue,
      boolean selected,
      IIconToggleButtonProperties properties) {
    return createTraitView(labelText, value, maxValue, selected, properties, markerLessIntValueDisplayFactory);
  }

  private IToggleButtonTraitView<SimpleTraitView> createTraitView(
      String labelText,
      int value,
      int maxValue,
      boolean selected,
      IIconToggleButtonProperties properties,
      IIntValueDisplayFactory factory) {
    SimpleTraitView view = new SimpleTraitView(factory, labelText, value, maxValue);
    FrontToggleButtonTraitViewWrapper<SimpleTraitView> abilityView = new FrontToggleButtonTraitViewWrapper<SimpleTraitView>(
        view,
        properties,
        selected);
    groupedTraitView.addEntry(abilityView);
    return abilityView;
  }

  @Override
  protected void createContent(JPanel component, Object object) {
    groupedTraitView.addOverallView(component);
  }

  public void startNewTraitGroup(String groupLabel) {
    groupedTraitView.startNewGroup(groupLabel);
  }
}