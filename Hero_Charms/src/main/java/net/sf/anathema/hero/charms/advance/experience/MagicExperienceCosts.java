package net.sf.anathema.hero.charms.advance.experience;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.hero.advance.CostAnalyzer;
import net.sf.anathema.hero.charms.advance.MagicKeywordCosts;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.hero.charms.template.advance.KeywordMagicTemplate;
import net.sf.anathema.hero.charms.template.advance.MagicPointsCategoryTemplate;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;

import java.util.HashMap;
import java.util.Map;

public class MagicExperienceCosts {

  private MagicPointsTemplate template;
  private MartialArtsLevel standardMartialArtsLevel;

  public MagicExperienceCosts(MagicPointsTemplate template, MartialArtsLevel standardMartialArtsLevel) {
    this.template = template;
    this.standardMartialArtsLevel = standardMartialArtsLevel;
  }

  public int getMagicCosts(Magic magic, CostAnalyzer costAnalyzer) {
    boolean favored = costAnalyzer.isMagicFavored(magic);
    MagicKeywordCosts keywordCosts = createFavoredKeywordCosts(favored);
    if (keywordCosts.hasCostFor(magic.getAttributes())) {
      return keywordCosts.getCostFor(magic.getAttributes());
    }
    return getMagicCosts(favored, costAnalyzer.getMartialArtsLevel(magic));
  }

  private int getMagicCosts(boolean favored, MartialArtsLevel level) {
    MagicPointsCategoryTemplate categoryTemplate = getTemplate(favored);
    if (level != null && (standardMartialArtsLevel.compareTo(level) < 0 || level == MartialArtsLevel.Sidereal)) {
      return categoryTemplate.highMartialArtsExperience;
    }
    return categoryTemplate.experienceCosts;
  }

  private MagicKeywordCosts createFavoredKeywordCosts(boolean favored) {
    Map<String, Integer> keywordFavoredCosts = new HashMap<>();
    for (KeywordMagicTemplate keywordMagic : getTemplate(favored).keywordMagic) {
      keywordFavoredCosts.put(keywordMagic.keyword, keywordMagic.value);
    }
    return new MagicKeywordCosts(keywordFavoredCosts);
  }

  private MagicPointsCategoryTemplate getTemplate(boolean favored) {
    return favored ? template.favoredPoints : template.generalPoints;
  }
}
