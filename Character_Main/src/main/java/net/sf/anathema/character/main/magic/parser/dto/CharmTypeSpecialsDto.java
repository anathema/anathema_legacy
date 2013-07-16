package net.sf.anathema.character.main.magic.parser.dto;

import net.sf.anathema.hero.charmtree.type.ISimpleSpecialsModel;
import net.sf.anathema.hero.charmtree.type.TurnType;

public class CharmTypeSpecialsDto {

  public Integer primaryStep;
  public Integer secondaryStep;
  public TurnType turntype = TurnType.Tick;
  public int speed = ISimpleSpecialsModel.DEFAULT_SPEED;
  public int defense = ISimpleSpecialsModel.DEFAULT_DEFENSE_MODIFIER;
}
