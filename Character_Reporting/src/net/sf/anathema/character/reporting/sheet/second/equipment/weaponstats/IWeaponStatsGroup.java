package net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats;

public interface IWeaponStatsGroup {

  public int getColumnCount();

  public String getTitle();
  
  public Float[] getColumnWeights();
}