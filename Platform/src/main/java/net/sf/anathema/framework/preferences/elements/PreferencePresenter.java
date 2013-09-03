package net.sf.anathema.framework.preferences.elements;

import net.sf.anathema.framework.environment.Resources;

public interface PreferencePresenter {
  Class getViewClass();

  Class getModelClass();

  String getTitle();

  void useModel(PreferenceModel preferenceModel);

  void useView(PreferenceView view);

  void initialize();

  void useResources(Resources resources);
}
