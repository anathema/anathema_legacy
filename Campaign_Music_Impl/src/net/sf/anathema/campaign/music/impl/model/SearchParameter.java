package net.sf.anathema.campaign.music.impl.model;

import net.sf.anathema.campaign.music.presenter.ISearchParameter;

final class SearchParameter implements ISearchParameter {

  private final String fieldName;
  private final String displayKey;

  public SearchParameter(String displayKey, String fieldName) {
    this.displayKey = displayKey;
    this.fieldName = fieldName;

  }

  public String getDisplayKey() {
    return displayKey;
  }

  public String getFieldName() {
    return fieldName;
  }
}