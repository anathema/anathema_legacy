package net.sf.anathema.character.generic.impl.magic.charm.type;

import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.magic.charms.type.ITypeSpecialsModel;

public class CharmTypeModel implements ICharmTypeModel {

  private CharmType type;
  private ITypeSpecialsModel model;

  public CharmType getCharmType() {
    return type;
  }

  public ITypeSpecialsModel getSpecialsModel() {
    return model;
  }

  public void setCharmType(CharmType type) {
    this.type = type;
  }

  public void setSpecialModel(ITypeSpecialsModel model) {
    this.model = model;
  }

  public boolean hasSpecialsModel() {
    return model != null;
  }
}