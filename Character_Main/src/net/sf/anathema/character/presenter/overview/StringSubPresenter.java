package net.sf.anathema.character.presenter.overview;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class StringSubPresenter implements IOverviewSubPresenter {

  private final IValueModel<String> model;
  private final IValueView<String> view;
  private final IResources resources;

  public StringSubPresenter(IValueModel<String> model, IValueView<String> view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void update() {
    String value = model.getValue();
    boolean nullOrEmptyValue = StringUtilities.isNullOrEmpty(value);
    view.setValue(nullOrEmptyValue ? "" : resources.getString(value)); //$NON-NLS-1$
    view.setTextColor(nullOrEmptyValue ? LegalityColorProvider.COLOR_LOW : LegalityColorProvider.COLOR_OKAY);
  }
}