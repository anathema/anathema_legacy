package net.sf.anathema.charmentry.demo.model;

import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.charmentry.demo.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.model.IConfigurableCharmData;
import net.sf.anathema.charmentry.model.IReflexiveSpecialsArbitrator;
import net.sf.anathema.charmentry.model.IReflexiveSpecialsEntryModel;
import net.sf.anathema.charmentry.model.ISimpleSpecialsArbitrator;
import net.sf.anathema.charmentry.model.ReflexiveSpecialsEntryModel;
import net.sf.anathema.charmentry.model.SimpleSpecialsEntryModel;
import net.sf.anathema.charmentry.presenter.ISimpleSpecialsEntryModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class CharmTypeEntryModel implements
    ICharmTypeEntryModel,
    ISimpleSpecialsArbitrator,
    IReflexiveSpecialsArbitrator {

  private final ChangeControl control = new ChangeControl();
  private final IConfigurableCharmData charmData;
  private final SimpleSpecialsEntryModel simpleCharmSpecials = new SimpleSpecialsEntryModel(this);
  private final ReflexiveSpecialsEntryModel reflexiveCharmSpecials = new ReflexiveSpecialsEntryModel(this);
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
    if (isSimpleSpecialsAvailable()) {
      charmTypeModel.setSpecialModel(simpleCharmSpecials);
    }
    else if (isReflexiveSpecialsAvailable()) {
      charmTypeModel.setSpecialModel(reflexiveCharmSpecials);
    }
    else {
      charmTypeModel.setSpecialModel(null);
    }
    control.fireChangedEvent();
  }

  public void addModelListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public void setSpecialModelEnabled(boolean enabled) {
    if (this.enabled != enabled) {
      this.enabled = enabled;
      control.fireChangedEvent();
    }
  }

  public boolean isSpecialModelEnabled() {
    return enabled;
  }

  public boolean isSpecialModelAvailable() {
    final CharmType type = charmData.getCharmTypeModel().getCharmType();
    return type == CharmType.Simple || type == CharmType.Reflexive;
  }

  public boolean isSimpleSpecialsAvailable() {
    return charmData.getCharmTypeModel().getCharmType() == CharmType.Simple
        && charmData.getEdition() == ExaltedEdition.SecondEdition
        && enabled;
  }

  public boolean isReflexiveSpecialsAvailable() {
    return charmData.getCharmTypeModel().getCharmType() == CharmType.Reflexive
        && charmData.getEdition() == ExaltedEdition.SecondEdition
        && enabled;
  }

  public CharmType getCharmType() {
    return charmData.getCharmTypeModel().getCharmType();
  }
}