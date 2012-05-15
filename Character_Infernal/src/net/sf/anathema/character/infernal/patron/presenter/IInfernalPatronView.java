package net.sf.anathema.character.infernal.patron.presenter;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.framework.value.IntegerViewFactory;

public interface IInfernalPatronView {

  void startGroup(String groupLabel);

  IToggleButtonTraitView< ? > addIntValueView(String label, IntegerViewFactory factory, IIconToggleButtonProperties properties, boolean selected);

  void setOverview(IOverviewCategory overview);

  IOverviewCategory createOverview(String borderLabel);
}