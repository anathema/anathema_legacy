package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.IGroupedFavorableTraitViewFactory;
import net.sf.anathema.framework.value.IntegerViewFactory;

import javax.swing.JPanel;

public class GroupedFavorableTraitViewFactory implements IGroupedFavorableTraitViewFactory {

  private final IntegerViewFactory intValueDisplayFactory;
  private final IntegerViewFactory intValueDisplayFactoryWithoutMarker;

  public GroupedFavorableTraitViewFactory(
      IntegerViewFactory intValueDisplayFactory,
      IntegerViewFactory intValueDisplayFactoryWithoutMarker) {
    this.intValueDisplayFactory = intValueDisplayFactory;
    this.intValueDisplayFactoryWithoutMarker = intValueDisplayFactoryWithoutMarker;
  }

  @Override
  public IGroupedFavorableTraitConfigurationView createView(int columnCount) {
    return new GroupedFavorableTraitConfigurationView(
        new JPanel(),
        columnCount,
        intValueDisplayFactory,
        intValueDisplayFactoryWithoutMarker);
  }
}