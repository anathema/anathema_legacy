package net.sf.anathema.character.generic.framework.xml.experience;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.template.experience.ComboCostCalculator;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericExperiencePointCosts extends ReflectionCloneableObject<GenericExperiencePointCosts> implements
    IExperiencePointCosts {

  private ICurrentRatingCosts generalAttributeCost;
  private ICurrentRatingCosts generalAbilityCost;
  private ICurrentRatingCosts favoredAbilityCost;
  private int specialtyCost;
  private ICurrentRatingCosts virtueCosts;
  private ICurrentRatingCosts willpowerCosts;
  private ICurrentRatingCosts essenceCosts;
  private int generalCharmCost;
  private int favoredCharmCost;
  private int generalHighLevelCharmCost;
  private int favoredHighLevelCharmCost;
  private MartialArtsLevel standardMartialArtsLevel;
  private int backgroundCosts;

  public ICurrentRatingCosts getAbilityCosts(boolean favored) {
    return favored ? favoredAbilityCost : generalAbilityCost;
  }

  public ICurrentRatingCosts getAttributeCosts() {
    return generalAttributeCost;
  }

  public int getComboCosts(ICharm[] comboCharms) {
    return new ComboCostCalculator().getComboCosts(comboCharms);
  }

  public int getSpellCosts(ISpell spell, IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection) {
    return getCharmCosts(spell.isFavored(basicCharacter, traitCollection), null);
  }

  public int getCharmCosts(ICharm charm, ICostAnalyzer costMapping) {
    return getCharmCosts(costMapping.isMagicFavored(charm), costMapping.getMartialArtsLevel(charm));
  }

  private int getCharmCosts(boolean favored, MartialArtsLevel level) {
    if (level != null && standardMartialArtsLevel.compareTo(level) < 0) {
      return favored ? favoredHighLevelCharmCost : generalHighLevelCharmCost;
    }
    return favored ? favoredCharmCost : generalCharmCost;
  }

  public ICurrentRatingCosts getEssenceCosts() {
    return essenceCosts;
  }

  public int getSpecialtyCosts(boolean favored) {
    return specialtyCost;
  }

  public ICurrentRatingCosts getVirtueCosts() {
    return virtueCosts;
  }

  public ICurrentRatingCosts getWillpowerCosts() {
    return willpowerCosts;
  }

  public void setGeneralAttributeCosts(ICurrentRatingCosts generalAttributeCost) {
    this.generalAttributeCost = generalAttributeCost;
  }

  public void setGeneralAbilityCosts(ICurrentRatingCosts generalAbilityCost) {
    this.generalAbilityCost = generalAbilityCost;
  }

  public void setFavoredAbilityCosts(ICurrentRatingCosts favoredAbilityCost) {
    this.favoredAbilityCost = favoredAbilityCost;
  }

  public void setSpecialtyCosts(int specialtyCost) {
    this.specialtyCost = specialtyCost;
  }

  public void setWillpowerCosts(ICurrentRatingCosts willpowerCosts) {
    this.willpowerCosts = willpowerCosts;
  }

  public void setVirtueCosts(ICurrentRatingCosts virtueCosts) {
    this.virtueCosts = virtueCosts;
  }

  public void setEssenceCosts(ICurrentRatingCosts essenceCosts) {
    this.essenceCosts = essenceCosts;
  }

  public void setCharmCosts(int favoredCharmCost, int generalCharmCost) {
    this.favoredCharmCost = favoredCharmCost;
    this.generalCharmCost = generalCharmCost;
  }

  @Override
  public int getBackgroundCost() {
    return backgroundCosts;
  }
  
  public void setBackgroundCosts(int backgroundCosts) {
    this.backgroundCosts = backgroundCosts;
  }
  
  
  public void setMartialArtsCosts(int favoredMartialArtsCost, int generalMartialArtsCost) {
    this.favoredHighLevelCharmCost = favoredMartialArtsCost;
    this.generalHighLevelCharmCost = generalMartialArtsCost;
  }

  public void setStandardMartialArtsLevel(MartialArtsLevel standardLevel) {
    this.standardMartialArtsLevel = standardLevel;
  }
}