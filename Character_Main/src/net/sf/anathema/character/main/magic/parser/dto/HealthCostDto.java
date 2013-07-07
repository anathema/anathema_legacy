package net.sf.anathema.character.main.magic.parser.dto;

import net.sf.anathema.hero.health.HealthType;

public class HealthCostDto {

  public int cost = 0;
  public String text = "";
  public boolean permanent = false;
  public HealthType type = HealthType.Lethal;
}