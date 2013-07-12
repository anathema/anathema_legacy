package net.sf.anathema.hero.magic.advance.creation;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;
import net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities;
import net.sf.anathema.character.main.xml.creation.template.MagicCreationCostGroupTto;

public class MagicCostStrategy {

  private MagicCreationCostGroupTto tto;
  private MartialArtsLevel standardMartialArtsLevel;

  public MagicCostStrategy(MagicCreationCostGroupTto tto, MartialArtsLevel standardMartialArtsLevel) {
    this.tto = tto;
    this.standardMartialArtsLevel = standardMartialArtsLevel;
  }

  public int getCharmCosts(Charm charm) {
    CharmKeywordCosts set = new CharmKeywordCosts(tto.keywordCosts);
    if (set.hasCostFor(charm.getAttributes())) {
      return set.getCostFor(charm.getAttributes());
    }
    return getCharmCosts(MartialArtsUtilities.getLevel(charm));
  }

  public int getSpellCosts() {
    return tto.charmCost;
  }

  private int getCharmCosts(MartialArtsLevel martialArtsLevel) {
    return isHighLevelMartialArts(martialArtsLevel) ? tto.highLevelMartialArtsCost : tto.charmCost;
  }

  private boolean isHighLevelMartialArts(MartialArtsLevel martialArtsLevel) {
    return martialArtsLevel != null && (standardMartialArtsLevel.compareTo(martialArtsLevel) < 0 || martialArtsLevel == MartialArtsLevel.Sidereal);
  }
}
