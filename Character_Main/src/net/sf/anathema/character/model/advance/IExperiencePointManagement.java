package net.sf.anathema.character.model.advance;

public interface IExperiencePointManagement {

  public int getAttributeCosts();

  public int getAbilityCosts();

  public int getCharmCosts();

  public int getComboCosts();

  public int getTotalCosts();

  public int getEssenceCosts();

  public int getSpecialtyCosts();

  public int getSpellCosts();

  public int getVirtueCosts();

  public int getWillpowerCosts();

  public int getMiscCosts();

  public int getMiscGain();
}