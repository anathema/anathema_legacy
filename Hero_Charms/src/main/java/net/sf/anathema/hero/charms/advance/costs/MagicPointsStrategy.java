package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.hero.advance.CostAnalyzer;
import net.sf.anathema.hero.charms.advance.costs.MagicKeywordCosts;
import net.sf.anathema.hero.charms.template.advance.KeywordMagicTemplate;
import net.sf.anathema.hero.charms.template.advance.MagicPointsCategoryTemplate;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;

import java.util.HashMap;
import java.util.Map;

public class MagicPointsStrategy {


  private MartialArtsLevel standardMartialArtsLevel;
  private MagicPointsCategoryTemplate template;
  private final MagicKeywordCosts keywordCosts;

  public MagicPointsStrategy(MagicPointsCategoryTemplate template, MartialArtsLevel standardMartialArtsLevel) {
    this.template = template;
    this.keywordCosts = createKeywordCosts(template);
    this.standardMartialArtsLevel = standardMartialArtsLevel;
  }

  private MagicKeywordCosts createKeywordCosts(MagicPointsCategoryTemplate template) {
    Map<String, Integer> keywordFavoredCosts = new HashMap<>();
    for (KeywordMagicTemplate keywordMagic : template.keywordMagic) {
      keywordFavoredCosts.put(keywordMagic.keyword, keywordMagic.costs);
    }
    return new MagicKeywordCosts(keywordFavoredCosts);
  }

  public int getMagicCosts(Magic magic, CostAnalyzer analyzer) {
    if (keywordCosts.hasCostFor(magic.getAttributes())) {
      return keywordCosts.getCostFor(magic.getAttributes());
    }
    return getCharmCosts(analyzer.getMartialArtsLevel(magic));
  }

  private int getCharmCosts(MartialArtsLevel level) {
    return isHighLevelMartialArts(level) ? template.highMartialArtsCosts : template.costs;
  }

  private boolean isHighLevelMartialArts(MartialArtsLevel martialArtsLevel) {
    return martialArtsLevel != null && (standardMartialArtsLevel.compareTo(martialArtsLevel) < 0 || martialArtsLevel == MartialArtsLevel.Sidereal);
  }
}
