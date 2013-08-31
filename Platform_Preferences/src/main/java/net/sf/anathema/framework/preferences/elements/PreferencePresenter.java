package net.sf.anathema.framework.preferences.elements;

public interface PreferencePresenter {
  Class getViewClass();

  Class getModelClass();

  String getTitle();

  void useModel(PreferenceModel preferenceModel);

  void useView(PreferenceView view);

  void initialize();
}
