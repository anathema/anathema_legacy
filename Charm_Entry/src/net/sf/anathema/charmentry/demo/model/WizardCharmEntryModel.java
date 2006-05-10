package net.sf.anathema.charmentry.demo.model;

import net.sf.anathema.charmentry.demo.ICharmEntryModel;
import net.sf.anathema.charmentry.demo.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.demo.ICostEntryModel;
import net.sf.anathema.charmentry.demo.IDurationEntryModel;
import net.sf.anathema.charmentry.demo.IPrerequisitesModel;
import net.sf.anathema.charmentry.model.ConfigurableCharmData;
import net.sf.anathema.charmentry.model.IConfigurableCharmData;

public class WizardCharmEntryModel implements ICharmEntryModel {
  private final IConfigurableCharmData charmData = new ConfigurableCharmData();
  private final IHeaderDataModel headerModel = new HeaderDataModel(charmData);
  private final ICharmTypeEntryModel typeModel = new CharmTypeEntryModel(charmData);
  private final IDurationEntryModel durationModel = new DurationModel(typeModel, charmData);
  private final IPrerequisitesModel prerequisitesModel = new PrerequisiteEntryModel(headerModel, charmData);
  private final ICostEntryModel costEntryModel = new CostEntryModel(typeModel, charmData);

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
}