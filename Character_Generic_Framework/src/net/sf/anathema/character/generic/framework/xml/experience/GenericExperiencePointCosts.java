package net.sf.anathema.character.generic.framework.xml.experience;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.template.points.FixedValueRatingCosts;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

import java.util.HashMap;
import java.util.Map;

public class GenericExperiencePointCosts extends ReflectionCloneableObject<GenericExperiencePointCosts> implements IExperiencePointCosts {

  private CurrentRatingCosts generalAttributeCost = new FixedValueRatingCosts(0);
  private CurrentRatingCosts favoredAttributeCost = new FixedValueRatingCosts(0);
  private CurrentRatingCosts generalAbilityCost = new FixedValueRatingCosts(0);
  private CurrentRatingCosts favoredAbilityCost = new FixedValueRatingCosts(0);
  private int specialtyCost = 0;
  private CurrentRatingCosts virtueCosts = new FixedValueRatingCosts(0);
  private CurrentRatingCosts willpowerCosts = new FixedValueRatingCosts(0);
  private CurrentRatingCosts essenceCosts = new FixedValueRatingCosts(0);
  private int generalCharmCost = 0;
  private int favoredCharmCost = 0;
  private int generalHighLevelCharmCost = 0;
  private int favoredHighLevelCharmCost = 0;
  private int spellCost = 0;
  private MartialArtsLevel standardMartialArtsLevel = MartialArtsLevel.Terrestrial;
  private int backgroundCosts = 0;
  private Map<String, Integer> keywordGeneralCosts = new HashMap<>();
  private Map<String, Integer> keywordFavoredCosts = new HashMap<>();

  @Override
  public CurrentRatingCosts getAbilityCosts(boolean favored) {
    return favored ? favoredAbilityCost : generalAbilityCost;
  }

  @Override
  public CurrentRatingCosts getAttributeCosts(boolean favored) {
    return favored ? favoredAttributeCost : generalAttributeCost;
  }

  @Override
  public int getSpellCosts(ISpell spell, IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection) {
    return spellCost != 0 ? spellCost : getCharmCosts(spell.isFavored(basicCharacter, traitCollection), null);
  }

  @Override
  public int getCharmCosts(ICharm charm, ICostAnalyzer costMapping) {
    boolean favored = costMapping.isMagicFavored(charm);
    for (ICharmAttribute attribute : charm.getAttributes()) {
      Map<String, Integer> set = favored ? keywordFavoredCosts : keywordGeneralCosts;
      if (set.containsKey(attribute.getId())) {
        return set.get(attribute.getId());
      }
    }
    return getCharmCosts(favored, costMapping.getMartialArtsLevel(charm));
  }

  private int getCharmCosts(boolean favored, MartialArtsLevel level) {
    if (level != null && (standardMartialArtsLevel.compareTo(level) < 0 || level == MartialArtsLevel.Sidereal)) {
      return favored ? favoredHighLevelCharmCost : generalHighLevelCharmCost;
    }
    return favored ? favoredCharmCost : generalCharmCost;
  }

  @Override
  public CurrentRatingCosts getEssenceCosts() {
    return essenceCosts;
  }

  @Override
  public int getSpecialtyCosts(boolean favored) {
    return specialtyCost;
  }

  @Override
  public CurrentRatingCosts getVirtueCosts() {
    return virtueCosts;
  }

  @Override
  public CurrentRatingCosts getWillpowerCosts() {
    return willpowerCosts;
  }

  public void setGeneralAttributeCosts(CurrentRatingCosts generalAttributeCost) {
    this.generalAttributeCost = generalAttributeCost;
  }

  public void setFavoredAttributeCosts(CurrentRatingCosts favoredAttributeCost) {
    this.favoredAttributeCost = favoredAttributeCost;
  }

  public void setGeneralAbilityCosts(CurrentRatingCosts generalAbilityCost) {
    this.generalAbilityCost = generalAbilityCost;
  }

  public void setFavoredAbilityCosts(CurrentRatingCosts favoredAbilityCost) {
    this.favoredAbilityCost = favoredAbilityCost;
  }

  public void setSpecialtyCosts(int specialtyCost) {
    this.specialtyCost = specialtyCost;
  }

  public void setWillpowerCosts(CurrentRatingCosts willpowerCosts) {
    this.willpowerCosts = willpowerCosts;
  }

  public void setVirtueCosts(CurrentRatingCosts virtueCosts) {
    this.virtueCosts = virtueCosts;
  }

  public void setEssenceCosts(CurrentRatingCosts essenceCosts) {
    this.essenceCosts = essenceCosts;
  }

  public void setCharmCosts(int favoredCharmCost, int generalCharmCost, Map<String, Integer> keywordGeneralCost,
                            Map<String, Integer> keywordFavoredCost) {
    this.favoredCharmCost = favoredCharmCost;
    this.generalCharmCost = generalCharmCost;
    this.keywordFavoredCosts = keywordFavoredCost;
    this.keywordGeneralCosts = keywordGeneralCost;
  }

  public void setSpellCost(int spellCost) {
    this.spellCost = spellCost;
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