package net.sf.anathema.character.main.presenter.overview;

import com.google.common.base.Strings;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.hero.points.overview.IValueModel;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.resources.Resources;

public class StringSubPresenter implements IOverviewSubPresenter {

  private final IValueModel<String> model;
  private final IValueView<String> view;
  private final Resources resources;

  public StringSubPresenter(IValueModel<String> model, IValueView<String> view, Resources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  @Override
  public void update() {
    String value = model.getValue();
    boolean nullOrEmptyValue = Strings.isNullOrEmpty(value);
    view.setValue(nullOrEmptyValue ? "" : resources.getString(value));
    view.setTextColor(nullOrEmptyValue ? LegalityColorProvider.COLOR_LOW : LegalityColorProvider.COLOR_OKAY);
  }
}