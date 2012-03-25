package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.reporting.pdf.content.magic.GenericCharmUtilities;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.CharmStats;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MultipleEffectCharmStats;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.SpellStats;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.List;

public class MagicStatsFactoryVisitor implements IMagicVisitor {
  private final static IIdentificate KNACK = new Identificate("Knack");
  private final IGenericCharacter character;
  private final List<IMagicStats> printStats;

  public MagicStatsFactoryVisitor(IGenericCharacter character, List<IMagicStats> printStats) {
    this.character = character;
    this.printStats = printStats;
  }

  @Override
  public void visitCharm(ICharm charm) {
    if (GenericCharmUtilities.isGenericCharmFor(charm, character)) {
      return;
    }
    if (charm.hasAttribute(KNACK)) {
      return;
    }
    if (character.isMultipleEffectCharm(charm)) {
      String[] effects = character.getLearnedEffects(charm);
      for (String effect : effects) {
        printStats.add(new MultipleEffectCharmStats(charm, effect));
      }
    } else {
      printStats.add(new CharmStats(charm, character));
    }
  }

  @Override
  public void visitSpell(ISpell spell) {
    printStats.add(new SpellStats(spell));
  }
}
