package net.sf.anathema.character.view.overview;

import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;

public class NullOverviewContainer implements CategorizedOverview {
  @Override
  public IOverviewCategory addOverviewCategory(String borderLabel) {
    return new OverviewCategory(null, null, false);
  }

  @Override
  public void showIn(OverviewDisplay characterPane) {
    //nothing to do
  }
}
