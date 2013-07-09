package net.sf.anathema.hero.magic;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.magic.model.magic.IMagicVisitor;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharm;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.charms.CharmsModelFetcher;
import net.sf.anathema.hero.spells.SpellsModelFetcher;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.hero.model.Hero;

public class MagicCollectionImpl implements MagicCollection {

  private Hero hero;

  public MagicCollectionImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public int getLearnCount(IMultiLearnableCharm charm) {
    return getLearnCount(charm.getCharmId());
  }

  @Override
  public int getLearnCount(String charmName) {
    CharmsModel charms = CharmsModelFetcher.fetch(hero);
    try {
      IMultiLearnableCharmConfiguration configuration = (IMultiLearnableCharmConfiguration) charms.getSpecialCharmConfiguration(charmName);
      return configuration.getCurrentLearnCount();
    } catch (IllegalArgumentException e) {
      return 0;
    }
  }

  @Override
  public void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue) {
    setLearnCount(multiLearnableCharm.getCharmId(), newValue);
  }

  @Override
  public void setLearnCount(String charmName, int newValue) {
    CharmsModel charms = CharmsModelFetcher.fetch(hero);
    IMultiLearnableCharmConfiguration configuration = (IMultiLearnableCharmConfiguration) charms.getSpecialCharmConfiguration(charmName);
    configuration.setCurrentLearnCount(newValue);
  }

  @Override
  public boolean isLearned(Magic magic) {
    final boolean[] isLearned = new boolean[1];
    magic.accept(new IMagicVisitor() {
      @Override
      public void visitSpell(ISpell spell) {
        isLearned[0] = SpellsModelFetcher.fetch(hero).isLearned(spell);
      }

      @Override
      public void visitCharm(Charm charm) {
        isLearned[0] = CharmsModelFetcher.fetch(hero).isLearned(charm);
      }
    });
    return isLearned[0];
  }
}
