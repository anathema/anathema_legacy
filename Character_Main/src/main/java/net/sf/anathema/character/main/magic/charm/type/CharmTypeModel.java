package net.sf.anathema.character.main.magic.charm.type;

public class CharmTypeModel implements ICharmTypeModel {

  private CharmType type;
  private ITypeSpecialsModel model;
  
  public CharmTypeModel() {
  }
  
  public CharmTypeModel(CharmType type, ITypeSpecialsModel model) {
	  this.type = type;
	  this.model = model;
  }

  @Override
  public CharmType getCharmType() {
    return type;
  }

  @Override
  public ITypeSpecialsModel getSpecialsModel() {
    return model;
  }

  public void setCharmType(CharmType type) {
    this.type = type;
  }

  public void setSpecialModel(ITypeSpecialsModel model) {
    this.model = model;
  }

  @Override
  public boolean hasSpecialsModel() {
    return model != null;
  }
}