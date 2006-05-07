package net.sf.anathema.charmentry.demo.model;

import net.sf.anathema.charmentry.demo.ICharmEntryModel;
import net.sf.anathema.charmentry.demo.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.demo.IPrerequisitesModel;
import net.sf.anathema.charmentry.model.ConfigurableCharmData;
import net.sf.anathema.charmentry.model.IConfigurableCharmData;

public class WizardCharmEntryModel implements ICharmEntryModel {
  private final IConfigurableCharmData charmData = new ConfigurableCharmData();
  private final IHeaderDataModel headerModel = new HeaderDataModel(charmData);
  private final ICharmTypeEntryModel typeModel = new CharmTypeEntryModel(charmData);
  private final IPrerequisitesModel prerequisitesModel = new PrerequisiteEntryModel(charmData);

  public IHeaderDataModel getHeaderDataModel() {
    return headerModel;
  }

  public ICharmTypeEntryModel getCharmTypeModel() {
    return typeModel;
  }

  public IPrerequisitesModel getPrerequisitesModel() {
    return prerequisitesModel;
  }
}