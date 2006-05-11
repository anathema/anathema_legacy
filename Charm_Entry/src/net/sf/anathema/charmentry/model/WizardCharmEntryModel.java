package net.sf.anathema.charmentry.model;

import net.sf.anathema.charmentry.model.data.ConfigurableCharmData;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.ICharmPrerequisitesEntryModel;
import net.sf.anathema.charmentry.presenter.model.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.presenter.model.ICostEntryModel;
import net.sf.anathema.charmentry.presenter.model.IDurationEntryModel;
import net.sf.anathema.charmentry.presenter.model.IKeywordEntryModel;
import net.sf.anathema.charmentry.presenter.model.IPrerequisitesModel;

public class WizardCharmEntryModel implements ICharmEntryModel {
  private final IConfigurableCharmData charmData = new ConfigurableCharmData();
  private final IHeaderDataModel headerModel = new HeaderDataModel(charmData);
  private final ICharmTypeEntryModel typeModel = new CharmTypeEntryModel(charmData);
  private final IDurationEntryModel durationModel = new DurationModel(typeModel, charmData);
  private final IPrerequisitesModel prerequisitesModel = new PrerequisiteEntryModel(headerModel, charmData);
  private final ICostEntryModel costEntryModel = new CostEntryModel(typeModel, charmData);
  private final IKeywordEntryModel keywordEntryModel = new KeywordEntryModel();
  private final ICharmPrerequisitesEntryModel charmPrerequisitesModel = new CharmPrerequisitesEntryModel(
      headerModel,
      charmData);

  public IHeaderDataModel getHeaderDataModel() {
    return headerModel;
  }

  public ICharmTypeEntryModel getCharmTypeModel() {
    return typeModel;
  }

  public IPrerequisitesModel getPrerequisitesModel() {
    return prerequisitesModel;
  }

  public IDurationEntryModel getDurationModel() {
    return durationModel;
  }

  public ICostEntryModel getCostEntryModel() {
    return costEntryModel;
  }

  public ICharmPrerequisitesEntryModel getCharmPrerequisitesModel() {
    return charmPrerequisitesModel;
  }

  public IKeywordEntryModel getKeywordEntryModel() {
    return keywordEntryModel;
  }
}