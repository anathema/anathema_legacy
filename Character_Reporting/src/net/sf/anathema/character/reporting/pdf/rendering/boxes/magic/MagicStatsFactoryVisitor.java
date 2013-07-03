package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.reporting.pdf.content.magic.MagicContentHelper;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.CharmStats;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MultipleEffectCharmStats;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.SpellStats;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.List;

public class MagicStatsFactoryVisitor implements IMagicVisitor {
  private final static Identifier KNACK = new SimpleIdentifier("Knack");
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
    if (charm.hasAttribute(KNACK)) {
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
