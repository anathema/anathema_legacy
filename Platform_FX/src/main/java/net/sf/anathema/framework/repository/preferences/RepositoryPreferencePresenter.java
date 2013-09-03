package net.sf.anathema.framework.repository.preferences;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.preferences.elements.PreferenceModel;
import net.sf.anathema.framework.preferences.elements.PreferencePresenter;
import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.framework.preferences.elements.RegisteredPreferencePresenter;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

@RegisteredPreferencePresenter
public class RepositoryPreferencePresenter implements PreferencePresenter {
  private RepositoryPreferenceModel model;
  private Resources resources;
  private RepositoryPreferenceView view;

  @Override
  public void initialize() {
    final ITextView textView = view.addRepositoryDisplay("Repository folder");
    textView.setEnabled(false);
    model.whenLocationChanges(new ChangeListener() {
      @Override
      public void changeOccurred() {
        showRepoInView(textView);
      }
    });
    showRepoInView(textView);
  }

  private void showRepoInView(ITextView textView) {
    textView.setText(model.getRepositoryPath().toAbsolutePath().toString());
  }

  @Override
  public Class getViewClass() {
    return RepositoryPreferenceView.class;
  }

  @Override
  public Class getModelClass() {
    return RepositoryPreferenceModel.class;
  }

  @Override
  public String getTitle() {
    return resources.getString("Preferences.Repository");
  }

  @Override
  public void useModel(PreferenceModel preferenceModel) {
    this.model = (RepositoryPreferenceModel) preferenceModel;
  }

  @Override
  public void useView(PreferenceView view) {
    this.view = (RepositoryPreferenceView) view;
  }

  @Override
  public void useResources(Resources resources) {
    this.resources = resources;
  }
}
