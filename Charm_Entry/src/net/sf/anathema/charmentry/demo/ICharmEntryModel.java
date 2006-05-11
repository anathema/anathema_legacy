package net.sf.anathema.charmentry.demo;

import net.sf.anathema.charmentry.demo.model.IHeaderDataModel;
import net.sf.anathema.charmentry.model.IKeywordEntryModel;

public interface ICharmEntryModel {

  public IHeaderDataModel getHeaderDataModel();

  public ICharmTypeEntryModel getCharmTypeModel();

  public IPrerequisitesModel getPrerequisitesModel();

  public IDurationEntryModel getDurationModel();

  public ICostEntryModel getCostEntryModel();

  public ICharmPrerequisitesEntryModel getCharmPrerequisitesModel();

  public IKeywordEntryModel getKeywordEntryModel();
}