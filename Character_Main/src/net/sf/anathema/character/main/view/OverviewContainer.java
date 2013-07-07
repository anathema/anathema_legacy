package net.sf.anathema.character.main.view;

import net.sf.anathema.character.main.view.overview.CategorizedOverview;

public interface OverviewContainer {
  CategorizedOverview addCreationOverviewView();

  CategorizedOverview addExperienceOverviewView();

  void toggleOverviewView(boolean experienced);
}
