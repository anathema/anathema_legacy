package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.magic.IMagicStats;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.hero.magic.model.PrintMagicProvider;
import net.sf.anathema.hero.magic.sheet.content.MagicContentHelper;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.sheet.content.SpellStats;

import java.util.List;

public class PrintSpellsProvider implements PrintMagicProvider {

  private Hero hero;

  public PrintSpellsProvider(Hero hero) {
    this.hero = hero;
  }

  @Override
  public void addPrintMagic(List<IMagicStats> printMagic) {
    MagicContentHelper helper = new MagicContentHelper(hero);
    for (ISpell spell : helper.getAllLearnedSpells()) {
      printMagic.add(new SpellStats(spell));
    }
  }
}
