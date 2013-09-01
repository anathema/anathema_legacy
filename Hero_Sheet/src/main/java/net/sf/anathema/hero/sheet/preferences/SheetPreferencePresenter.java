package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.preferences.elements.PreferenceModel;
import net.sf.anathema.framework.preferences.elements.PreferencePresenter;
import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.framework.preferences.elements.RegisteredPreferencePresenter;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.framework.environment.Resources;

@RegisteredPreferencePresenter
public class SheetPreferencePresenter implements PreferencePresenter {
  private SheetPreferenceModel model;
  private SheetPreferenceView view;
  private Resources resources;

  @Override
  public void initialize() {
    final ObjectSelectionView<PageSize> pageSizeView = view.addObjectSelectionView("Page format", new PageSizeUi(resources));
    pageSizeView.setObjects(model.getAvailableChoices());
    pageSizeView.addObjectSelectionChangedListener(new ObjectValueListener<PageSize>() {
      @Override
      public void valueChanged(PageSize newValue) {
        model.requestChangeTo(newValue);
      }
    });
    model.onChange(new ChangeListener() {
      @Override
      public void changeOccurred() {
        showCurrentChoiceInView(pageSizeView);
      }
    });
    showCurrentChoiceInView(pageSizeView);
  }

  @Override
  public void useResources(Resources resources) {
    this.resources = resources;
  }

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
    this.model = (SheetPreferenceModel) preferenceModel;
  }

  @Override
  public void useView(PreferenceView view) {
    this.view = (SheetPreferenceView) view;
  }

  private void showCurrentChoiceInView(ObjectSelectionView<PageSize> pageSizeView) {
    pageSizeView.setSelectedObject(model.getSelectedPageSize());
  }
}
