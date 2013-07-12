package net.sf.anathema.hero.spells.sheet.content;

import net.sf.anathema.character.main.magic.model.magic.IMagicStats;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.magic.model.PrintMagicProvider;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.hero.spells.sheet.content.SpellStats;

import java.util.Arrays;
import java.util.List;

public class PrintSpellsProvider implements PrintMagicProvider {

  private Hero hero;

  public PrintSpellsProvider(Hero hero) {
    this.hero = hero;
  }

  @Override
  public void addPrintMagic(List<IMagicStats> printMagic) {
    for (ISpell spell : getAllLearnedSpells()) {
      printMagic.add(new SpellStats(spell));
    }
  }

  private List<ISpell> getAllLearnedSpells() {
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    return Arrays.asList(SpellsModelFetcher.fetch(hero).getLearnedSpells(experienced));
  }
}
