package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.charmentry.model.IHeaderDataModel;

public interface ICharmEntryModel {

  public IHeaderDataModel getHeaderDataModel();

  public ICharmTypeEntryModel getCharmTypeModel();

  public IPrerequisitesModel getPrerequisitesModel();

  public IDurationEntryModel getDurationModel();

  public ICostEntryModel getCostEntryModel();

  public ICharmPrerequisitesEntryModel getCharmPrerequisitesModel();

  public IKeywordEntryModel getKeywordEntryModel();
}