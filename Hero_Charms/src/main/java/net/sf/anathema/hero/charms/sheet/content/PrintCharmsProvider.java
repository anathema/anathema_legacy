package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.sheet.content.IMagicStats;
import net.sf.anathema.hero.charms.sheet.content.stats.CharmStats;
import net.sf.anathema.hero.charms.sheet.content.stats.MultipleEffectCharmStats;
import net.sf.anathema.hero.charms.model.PrintMagicProvider;
import net.sf.anathema.hero.model.Hero;

import java.util.List;

public class PrintCharmsProvider implements PrintMagicProvider {

  private Hero hero;

  public PrintCharmsProvider(Hero hero) {
    this.hero = hero;
  }

  @Override
  public void addPrintMagic(List<IMagicStats> printMagic) {
    addGenericCharms(printMagic);
    addNonGenericCharms(printMagic);
  }

  private void addGenericCharms(List<IMagicStats> printMagic) {
    for (IMagicStats stats : createContentHelper().getGenericCharmStats()) {
      if (createContentHelper().shouldShowCharm(stats)) {
        printMagic.add(stats);
      }
    }
  }

  private void addNonGenericCharms(List<IMagicStats> printMagic) {
    for (Charm charm: createContentHelper().getLearnedCharms()) {
      addStatsForNonGenericCharm(printMagic, charm);
    }
  }

  private void addStatsForNonGenericCharm(List<IMagicStats> printMagic, Charm charm) {
    if (createContentHelper().isGenericCharmFor(charm)) {
      return;
    }
    if (createContentHelper().isMultipleEffectCharm(charm)) {
      String[] effects = createContentHelper().getLearnedEffects(charm);
      for (String effect : effects) {
        printMagic.add(new MultipleEffectCharmStats(charm, effect));
      }
    } else {
      printMagic.add(new CharmStats(charm, createContentHelper()));
    }
  }

  private CharmContentHelper createContentHelper() {
    return new CharmContentHelper(hero);
  }
}
