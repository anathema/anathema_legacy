package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmEntryData;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.charmentry.model.data.ConfigurableCharmData;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.ICharmPrerequisitesEntryModel;
import net.sf.anathema.charmentry.presenter.model.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.presenter.model.ICostEntryModel;
import net.sf.anathema.charmentry.presenter.model.IDurationEntryModel;
import net.sf.anathema.charmentry.presenter.model.IKeywordEntryModel;
import net.sf.anathema.charmentry.presenter.model.IPrerequisitesModel;
import net.sf.anathema.lib.resources.IExtensibleDataSetProvider;

public class WizardCharmEntryModel implements ICharmEntryModel {
  private final IConfigurableCharmData charmData = new ConfigurableCharmData();
  private final IHeaderDataModel headerModel = new HeaderDataModel(charmData);
  private final ICharmTypeEntryModel typeModel = new CharmTypeEntryModel(charmData);
  private final IDurationEntryModel durationModel = new DurationEntryModel(typeModel, charmData);
  private final IPrerequisitesModel prerequisitesModel = new PrerequisiteEntryModel(headerModel, charmData);
  private final ICostEntryModel costEntryModel = new CostEntryModel(typeModel, charmData);
  private final IKeywordEntryModel keywordEntryModel = new KeywordEntryModel(charmData);
  private final ICharmPrerequisitesEntryModel charmPrerequisitesModel;
  
  public WizardCharmEntryModel(IExtensibleDataSetProvider dataSetProvider) {
	  charmPrerequisitesModel = new CharmPrerequisitesEntryModel(
		      headerModel,
		      prerequisitesModel,
		      charmData,
		      dataSetProvider.getDataSet(ICharmCache.class));
  }

  @Override
  public IHeaderDataModel getHeaderDataModel() {
    return headerModel;
  }

  @Override
  public ICharmTypeEntryModel getCharmTypeModel() {
    return typeModel;
  }

  @Override
  public IPrerequisitesModel getPrerequisitesModel() {
    return prerequisitesModel;
  }

  @Override
  public IDurationEntryModel getDurationModel() {
    return durationModel;
  }

  @Override
  public ICostEntryModel getCostEntryModel() {
    return costEntryModel;
  }

  @Override
  public ICharmPrerequisitesEntryModel getCharmPrerequisitesModel() {
    return charmPrerequisitesModel;
  }

  @Override
  public IKeywordEntryModel getKeywordEntryModel() {
    return keywordEntryModel;
  }

  @Override
  public ICharmEntryData getCharmData() {
    return new ICharmEntryData() {

      @Override
      public ICharmData getCoreData() {
        return charmData;
      }

      @Override
      public String getName() {
        return charmData.getName().getText();
      }

      @Override
      public int getPage() {
        return charmData.getPage();
      }
    };
  }
}