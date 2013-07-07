package net.sf.anathema.hero.charms.sheet.content;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import net.sf.anathema.character.main.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.IMagic;
import net.sf.anathema.character.main.magic.IMagicStats;
import net.sf.anathema.character.main.magic.IMagicVisitor;
import net.sf.anathema.character.main.magic.ISpell;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.magic.charms.special.ISubeffect;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.charms.CharmsModelFetcher;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.spells.SpellsModelFetcher;
import net.sf.anathema.character.main.charm.ILearningCharmGroup;
import net.sf.anathema.character.main.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.main.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.charms.sheet.content.stats.CharmStats;
import net.sf.anathema.hero.charms.sheet.content.stats.GenericCharmStats;
import net.sf.anathema.hero.charms.sheet.content.stats.MultipleEffectCharmStats;
import net.sf.anathema.hero.charms.sheet.content.stats.SpellStats;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MagicContentHelper {

  private final Hero hero;

  public MagicContentHelper(Hero hero) {
    this.hero = hero;
  }

  public static List<IMagicStats> collectPrintMagic(final Hero hero, final boolean includeSpells, final boolean includeCharms) {
    final List<IMagicStats> printStats = new ArrayList<>();
    final MagicContentHelper helper = new MagicContentHelper(hero);
    if (includeCharms) {
      for (IMagicStats stats : helper.getGenericCharmStats()) {
        if (helper.shouldShowCharm(stats)) {
          printStats.add(stats);
        }
      }
    }

    IMagicVisitor statsCollector = new IMagicVisitor() {
      @Override
      public void visitCharm(ICharm charm) {
        if (!includeCharms) {
          return;
        }
        if (helper.isGenericCharmFor(charm)) {
          return;
        }
         if (helper.isMultipleEffectCharm(charm)) {
          String[] effects = helper.getLearnedEffects(charm);
          for (String effect : effects) {
            printStats.add(new MultipleEffectCharmStats(charm, effect));
          }
        } else {
          printStats.add(new CharmStats(charm, new MagicContentHelper(hero)));
        }
      }

      @Override
      public void visitSpell(ISpell spell) {
        if (includeSpells) {
          printStats.add(new SpellStats(spell));
        }
      }
    };
    for (IMagic magic : helper.getAllLearnedMagic()) {
      magic.accept(statsCollector);
    }
    return printStats;
  }

  public static List<IMagicStats> collectPrintCharms(ReportSession session) {
    return collectPrintMagic(session.getHero(), false, true);
  }

  public static List<IMagicStats> collectPrintSpells(ReportSession session) {
    return collectPrintMagic(session.getHero(), true, false);
  }

  public List<IMagic> getAllLearnedMagic() {
    List<IMagic> magicList = new ArrayList<>();
    magicList.addAll(Arrays.asList(getLearnedCharms()));
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    magicList.addAll(Arrays.asList(SpellsModelFetcher.fetch(hero).getLearnedSpells(experienced)));
    return magicList;
  }

  public boolean isMultipleEffectCharm(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = CharmsModelFetcher.fetch(hero).getSpecialCharmConfiguration(charm);
    return charmConfiguration instanceof IMultipleEffectCharmConfiguration && !(charmConfiguration instanceof ISubeffectCharmConfiguration);
  }

  public String[] getLearnedEffects(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = CharmsModelFetcher.fetch(hero).getSpecialCharmConfiguration(charm);
    if (!(charmConfiguration instanceof IMultipleEffectCharmConfiguration)) {
      return new String[0];
    }
    IMultipleEffectCharmConfiguration configuration = (IMultipleEffectCharmConfiguration) charmConfiguration;
    List<String> learnedEffectIds = new ArrayList<>();
    for (ISubeffect effect : configuration.getEffects()) {
      if (effect.isLearned()) {
        learnedEffectIds.add(effect.getId());
      }
    }
    return learnedEffectIds.toArray(new String[learnedEffectIds.size()]);
  }

  public ICharm[] getGenericCharms() {
    List<ICharm> genericCharms = new ArrayList<>();
    for (ILearningCharmGroup group : CharmsModelFetcher.fetch(hero).getAllGroups()) {
      for (ICharm charm : group.getAllCharms()) {
        if (charm.isInstanceOfGenericCharm() &&
            charm.getCharacterType().equals(hero.getTemplate().getTemplateType().getCharacterType())) {
          genericCharms.add(charm);
        }
      }
    }
    return genericCharms.toArray(new ICharm[genericCharms.size()]);
  }

  private ICharm[] getLearnedCharms() {
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    return CharmsModelFetcher.fetch(hero).getLearnedCharms(experienced);
  }

  public boolean isSubeffectCharm(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = CharmsModelFetcher.fetch(hero).getSpecialCharmConfiguration(charm);
    return charmConfiguration instanceof ISubeffectCharmConfiguration;
  }

  public int getLearnCount(ICharm charm) {
    return getLearnCount(charm, CharmsModelFetcher.fetch(hero));
  }

  private int getLearnCount(ICharm charm, CharmsModel configuration) {
    ISpecialCharmConfiguration specialCharmConfiguration = configuration.getSpecialCharmConfiguration(charm.getId());
    if (specialCharmConfiguration != null) {
      return specialCharmConfiguration.getCurrentLearnCount();
    }
    return configuration.isLearned(charm) ? 1 : 0;
  }

  public boolean shouldShowCharm(IMagicStats stats) {
    if (AnathemaCharacterPreferences.getDefaultPreferences().printAllGenerics()) {
      return true;
    }
    for (IMagic magic : getAllLearnedMagic()) {
      if (magic.getId().startsWith(stats.getName().getId())) return true;
    }
    return false;
  }

  public boolean isGenericCharmFor(ICharm charm) {
    IMagicStats[] genericCharmStats = getGenericCharmStats();
    String charmId = charm.getId();
    for (IMagicStats stat : genericCharmStats) {
      if (charmId.startsWith(stat.getName().getId())) {
        return true;
      }
    }
    return false;
  }

  public IMagicStats[] getGenericCharmStats() {
    List<IMagicStats> genericCharmStats = new ArrayList<>();
    for (ICharm charm : getGenericCharms()) {
      IMagicStats stats = new GenericCharmStats(charm, hero);
      if (!genericCharmStats.contains(stats)) genericCharmStats.add(stats);
    }
    return genericCharmStats.toArray(new IMagicStats[genericCharmStats.size()]);
  }

  public boolean isCharmLearned(List<IMagic> allLearnedMagic, final String charmId) {
    Optional<? extends IMagic> optional = Iterables.tryFind(allLearnedMagic, new Predicate<IMagic>() {
      @Override
      public boolean apply(IMagic value) {
        return charmId.equals(value.getId());
      }
    });
    return optional.isPresent();
  }
}
