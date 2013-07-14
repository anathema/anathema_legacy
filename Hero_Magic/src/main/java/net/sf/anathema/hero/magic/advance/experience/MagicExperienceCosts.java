package net.sf.anathema.hero.magic.advance.experience;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.magic.attribute.MagicAttribute;
import net.sf.anathema.hero.advance.CostAnalyzer;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.hero.magic.template.advance.KeywordMagicTemplate;
import net.sf.anathema.hero.magic.template.advance.MagicPointsTemplate;

import java.util.HashMap;
import java.util.Map;

public class MagicExperienceCosts {

  private MagicPointsTemplate template;
  private MartialArtsLevel standardMartialArtsLevel;
  private Map<String, Integer> keywordGeneralCosts = new HashMap<>();
  private Map<String, Integer> keywordFavoredCosts = new HashMap<>();

  public MagicExperienceCosts(MagicPointsTemplate template, MartialArtsLevel standardMartialArtsLevel) {
    this.template = template;
    this.standardMartialArtsLevel = standardMartialArtsLevel;
    for (KeywordMagicTemplate keywordMagic : template.keywordMagic) {
      keywordGeneralCosts.put(keywordMagic.keyword, keywordMagic.general);
      keywordFavoredCosts.put(keywordMagic.keyword, keywordMagic.favored);
    }
  }

  public int getMagicCosts(Charm charm, CostAnalyzer costAnalyzer) {
    boolean favored = costAnalyzer.isMagicFavored(charm);
    for (MagicAttribute attribute : charm.getAttributes()) {
      Map<String, Integer> set = favored ? keywordFavoredCosts : keywordGeneralCosts;
      if (set.containsKey(attribute.getId())) {
        return set.get(attribute.getId());
      }
    }
    return getCharmCosts(favored, costAnalyzer.getMartialArtsLevel(charm));
  }

  private int getCharmCosts(boolean favored, MartialArtsLevel level) {
    if (level != null && (standardMartialArtsLevel.compareTo(level) < 0 || level == MartialArtsLevel.Sidereal)) {
      return favored ? template.highMartialArtsExperience.favored : template.highMartialArtsExperience.general;
    }
    return favored ? template.experienceCosts.favored : template.experienceCosts.general;
  }
}
