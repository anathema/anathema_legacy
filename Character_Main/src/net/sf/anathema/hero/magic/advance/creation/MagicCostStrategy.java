package net.sf.anathema.hero.magic.advance.creation;

import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.xml.creation.template.MagicCreationCostGroupTto;

public class MagicCostStrategy {

  private MagicCreationCostGroupTto tto;
  private MartialArtsLevel standardMartialArtsLevel;

  public MagicCostStrategy(MagicCreationCostGroupTto tto, MartialArtsLevel standardMartialArtsLevel) {
    this.tto = tto;
    this.standardMartialArtsLevel = standardMartialArtsLevel;
  }

  public int getMagicCosts(Magic magic) {
    MagicKeywordCosts set = new MagicKeywordCosts(tto.keywordCosts);
    if (set.hasCostFor(magic.getAttributes())) {
      return set.getCostFor(magic.getAttributes());
    }
    return getCharmCosts(MartialArtsUtilities.getLevel(magic));
  }

  private int getCharmCosts(MartialArtsLevel martialArtsLevel) {
    return isHighLevelMartialArts(martialArtsLevel) ? tto.highLevelMartialArtsCost : tto.charmCost;
  }

  private boolean isHighLevelMartialArts(MartialArtsLevel martialArtsLevel) {
    return martialArtsLevel != null && (standardMartialArtsLevel.compareTo(martialArtsLevel) < 0 || martialArtsLevel == MartialArtsLevel.Sidereal);
  }
}
