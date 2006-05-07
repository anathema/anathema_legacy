package net.sf.anathema.charmentry.demo;

import net.sf.anathema.charmentry.demo.model.IHeaderDataModel;

public interface ICharmEntryModel {

  public IHeaderDataModel getHeaderDataModel();

  public ICharmTypeEntryModel getCharmTypeModel();

  public IPrerequisitesModel getPrerequisitesModel();

}