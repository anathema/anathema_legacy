package net.sf.anathema.character.impl.view;

import javax.swing.JPanel;

import net.sf.anathema.framework.value.IIntValueDisplayFactory;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.IGroupedFavorableTraitViewFactory;

public class GroupedFavorableTraitViewFactory implements IGroupedFavorableTraitViewFactory {

  private final IIntValueDisplayFactory intValueDisplayFactory;
  private final IIntValueDisplayFactory intValueDisplayFactoryWithoutMarker;

  public GroupedFavorableTraitViewFactory(
      IIntValueDisplayFactory intValueDisplayFactory,
      IIntValueDisplayFactory intValueDisplayFactoryWithoutMarker) {
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