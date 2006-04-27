package net.sf.anathema.character.generic.magic.charms.type;

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
}