package net.sf.anathema.character.main.view;

public interface OverviewContainer {
  CategorizedOverview addCreationOverviewView();

  CategorizedOverview addExperienceOverviewView();

  void toggleOverviewView(boolean experienced);
}