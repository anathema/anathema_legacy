package net.sf.anathema.character.sidereal.colleges.presenter;

import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.overview.IOverviewCategory;

public interface ISiderealCollegeView {

  public void startGroup(String groupLabel);

  public IToggleButtonTraitView< ? > addIntValueView(
      String label,
      IIntValueDisplayFactory factory,
      IIconToggleButtonProperties properties,
      int value,
      int maxValue,
      boolean selected);

  public void setOverview(IOverviewCategory overview);

  public IOverviewCategory createOverview(String borderLabel);
}