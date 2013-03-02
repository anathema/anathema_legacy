package net.sf.anathema.character.view;

import net.sf.anathema.character.view.overview.CategorizedOverview;

public interface OverviewContainer {
  CategorizedOverview addCreationOverviewView();

  CategorizedOverview addExperienceOverviewView();

  void toggleOverviewView(boolean experienced);
}
