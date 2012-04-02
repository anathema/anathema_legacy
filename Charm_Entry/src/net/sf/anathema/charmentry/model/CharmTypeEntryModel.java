package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
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

  @Override
  public CharmType[] getCharmTypes() {
    return CharmType.values();
  }

  @Override
  public ISimpleSpecialsEntryModel getSimpleSpecialsModel() {
    return simpleCharmSpecials;
  }

  @Override
  public IReflexiveSpecialsEntryModel getReflexiveSpecialsModel() {
    return reflexiveCharmSpecials;
  }

  @Override
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

  @Override
  public void addModelListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  @Override
  public void setSpecialModelEnabled(boolean enabled) {
    if (this.enabled != enabled) {
      this.enabled = enabled;
      setSpecialModel();
      control.fireChangedEvent();
    }
  }

  @Override
  public boolean isSpecialModelEnabled() {
    return enabled;
  }

  @Override
  public boolean isSpecialModelAvailable() {
    return isSimpleSpecialsAvailable() || isReflexiveSpecialsAvailable();
  }

  private boolean isSimpleSpecialsAvailable() {
    return charmData.getCharmTypeModel().getCharmType() == CharmType.Simple;
  }

  private boolean isReflexiveSpecialsAvailable() {
    return charmData.getCharmTypeModel().getCharmType() == CharmType.Reflexive;
  }

  @Override
  public CharmType getCharmType() {
    return charmData.getCharmTypeModel().getCharmType();
  }
}