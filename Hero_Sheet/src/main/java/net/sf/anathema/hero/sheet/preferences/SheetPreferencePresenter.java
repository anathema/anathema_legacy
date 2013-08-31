package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.preferences.elements.PreferenceModel;
import net.sf.anathema.framework.preferences.elements.PreferencePresenter;
import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.framework.preferences.elements.RegisteredPreferencePresenter;

@RegisteredPreferencePresenter
public class SheetPreferencePresenter implements PreferencePresenter {
  private PreferenceModel model;
  private PreferenceView view;

  @Override
  public Class getViewClass() {
    return SheetPreferenceView.class;
  }

  @Override
  public Class getModelClass() {
    return SheetPreferenceModel.class;
  }

  @Override
  public String getTitle() {
    return "Sheet";
  }

  @Override
  public void useModel(PreferenceModel preferenceModel) {
    this.model = preferenceModel;
  }

  @Override
  public void useView(PreferenceView view) {
    this.view = view;
  }

  @Override
  public void initialize() {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
