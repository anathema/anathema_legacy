package net.sf.anathema.hero.advance.overview.view;

public interface OverviewContainer {
  CategorizedOverview addCreationOverviewView();

  CategorizedOverview addExperienceOverviewView();

  void toggleOverviewView(boolean experienced);
}