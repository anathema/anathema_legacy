package net.sf.anathema.character.view.overview;

import net.sf.anathema.character.library.overview.IOverviewCategory;

public interface CategorizedOverview {

  IOverviewCategory addOverviewCategory(String borderLabel);

  void showIn(OverviewDisplay characterPane);
}