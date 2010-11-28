package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.presenter.model.IReflexiveSpecialsEntryModel;
import net.sf.anathema.charmentry.presenter.model.ISimpleSpecialsEntryModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class CharmTypeEntryModel implements ICharmTypeEntryModel {

  private final ChangeControl control = new ChangeControl();
  private final IConfigurableCharmData charmData;
  private final SimpleSpecialsEntryModel simpleCharmSpecials = new SimpleSpecialsEntryModel();
  private final ReflexiveSpecialsEntryModel reflexiveCharmSpecials = new ReflexiveSpecialsEntryModel();
  private boolean enabled;

  public CharmTypeEntryModel(IConfigurableCharmData charmData) {
    this.charmData = charmData;
  }

  public CharmType[] getCharmTypes() {
    return CharmType.values();
  }

  public ISimpleSpecialsEntryModel getSimpleSpecialsModel() {
    return simpleCharmSpecials;
  }

  public IReflexiveSpecialsEntryModel getReflexiveSpecialsModel() {
    return reflexiveCharmSpecials;
  }

  public void setCharmType(CharmType type) {
    final CharmTypeModel charmTypeModel = charmData.getCharmTypeModel();
    charmTypeModel.setCharmType(type);
    setSpecialModel();
    control.fireChangedEvent();
  }

  private void setSpecialModel() {
    final CharmTypeModel charmTypeModel = charmData.getCharmTypeModel();
    if (isSimpleSpecialsAvailable() && enabled) {
      charmTypeModel.setSpecialModel(simpleCharmSpecials);
    }
    else if (isReflexiveSpecialsAvailable() && enabled) {
      charmTypeModel.setSpecialModel(reflexiveCharmSpecials);
    }
    else {
      charmTypeModel.setSpecialModel(null);
    }
  }

  public void addModelListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public void setSpecialModelEnabled(boolean enabled) {
    if (this.enabled != enabled) {
      this.enabled = enabled;
      setSpecialModel();
      control.fireChangedEvent();
    }
  }

  public boolean isSpecialModelEnabled() {
    return enabled;
  }

  public boolean isSpecialModelAvailable() {
    return isSimpleSpecialsAvailable() || isReflexiveSpecialsAvailable();
  }

  private boolean isSimpleSpecialsAvailable() {
    return charmData.getCharmTypeModel().getCharmType() == CharmType.Simple
        && charmData.getEdition() == ExaltedEdition.SecondEdition;
  }

  private boolean isReflexiveSpecialsAvailable() {
    return charmData.getCharmTypeModel().getCharmType() == CharmType.Reflexive
        && charmData.getEdition() == ExaltedEdition.SecondEdition;
  }

  public CharmType getCharmType() {
    return charmData.getCharmTypeModel().getCharmType();
  }
}