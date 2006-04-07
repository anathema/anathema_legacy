package net.sf.anathema.character.sidereal.colleges.presenter;

import net.sf.anathema.character.library.intvalue.IFavorableIntValueView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;

public interface ISiderealCollegeView {

  public void startGroup(String groupLabel);

  public IFavorableIntValueView addIntValueView(
      String label,
      IIntValueDisplayFactory factory,
      IIconToggleButtonProperties properties,
      int value,
      int maxValue,
      boolean selected);

  public void setOverview(ISiderealCollegeOverview overview);
}