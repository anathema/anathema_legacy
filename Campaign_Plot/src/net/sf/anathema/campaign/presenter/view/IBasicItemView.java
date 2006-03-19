package net.sf.anathema.campaign.presenter.view;

import net.sf.anathema.framework.view.IItemView;

public interface IBasicItemView extends IItemView {
  public IBasicItemDescriptionView addDescriptionView(String title);
}