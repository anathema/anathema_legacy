package net.sf.anathema.hero.magic.advance.creation;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.hero.advance.CostAnalyzer;
import net.sf.anathema.character.main.xml.creation.template.MagicCreationCostGroupTto;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;

public class MagicCostStrategy {

  private MagicCreationCostGroupTto tto;
  private MartialArtsLevel standardMartialArtsLevel;

  public MagicCostStrategy(MagicCreationCostGroupTto tto, MartialArtsLevel standardMartialArtsLevel) {
    this.tto = tto;
    this.standardMartialArtsLevel = standardMartialArtsLevel;
  }

  public int getMagicCosts(Magic magic, CostAnalyzer analyzer) {
    MagicKeywordCosts set = new MagicKeywordCosts(tto.keywordCosts);
    if (set.hasCostFor(magic.getAttributes())) {
      return set.getCostFor(magic.getAttributes());
    }
    return getCharmCosts(analyzer.getMartialArtsLevel(magic));
  }

  private int getCharmCosts(MartialArtsLevel level) {
    return isHighLevelMartialArts(level) ? tto.highLevelMartialArtsCost : tto.charmCost;
  }

  private boolean isHighLevelMartialArts(MartialArtsLevel martialArtsLevel) {
    return martialArtsLevel != null && (standardMartialArtsLevel.compareTo(martialArtsLevel) < 0 || martialArtsLevel == MartialArtsLevel.Sidereal);
  }
}
