package net.sf.anathema.hero.magic.sheet.content.stats;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.magic.IMagicStats;
import net.sf.anathema.character.main.magic.model.magic.IMagicVisitor;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.hero.magic.sheet.content.MagicContentHelper;
import net.sf.anathema.hero.model.Hero;

import java.util.List;

public class MagicStatsFactoryVisitor implements IMagicVisitor {
  private final MagicContentHelper content;
  private final List<IMagicStats> printStats;
  private Hero hero;

  public MagicStatsFactoryVisitor(Hero hero, List<IMagicStats> printStats) {
    this.hero = hero;
    this.content = new MagicContentHelper(hero);
    this.printStats = printStats;
  }

  @Override
  public void visitCharm(ICharm charm) {
    if (content.isGenericCharmFor(charm)) {
      return;
    }
    if (content.isMultipleEffectCharm(charm)) {
      String[] effects = content.getLearnedEffects(charm);
      for (String effect : effects) {
        printStats.add(new MultipleEffectCharmStats(charm, effect));
      }
    } else {
      printStats.add(new CharmStats(charm, new MagicContentHelper(hero)));
    }
  }

  @Override
  public void visitSpell(ISpell spell) {
    printStats.add(new SpellStats(spell));
  }
}
