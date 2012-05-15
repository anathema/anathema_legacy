package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.impl.magic.persistence.ICharmEntryData;
import net.sf.anathema.charmentry.model.IHeaderDataModel;

public interface ICharmEntryModel {

  IHeaderDataModel getHeaderDataModel();

  ICharmTypeEntryModel getCharmTypeModel();

  IPrerequisitesModel getPrerequisitesModel();

  IDurationEntryModel getDurationModel();

  ICostEntryModel getCostEntryModel();

  ICharmPrerequisitesEntryModel getCharmPrerequisitesModel();

  IKeywordEntryModel getKeywordEntryModel();

  ICharmEntryData getCharmData();
}