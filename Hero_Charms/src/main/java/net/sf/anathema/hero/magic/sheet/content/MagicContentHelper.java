package net.sf.anathema.hero.magic.sheet.content;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import net.sf.anathema.character.main.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.ISubeffect;
import net.sf.anathema.character.main.magic.model.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.model.magic.IMagicStats;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.charms.CharmsModelFetcher;
import net.sf.anathema.hero.charms.sheet.content.stats.CharmStats;
import net.sf.anathema.hero.charms.sheet.content.stats.GenericCharmStats;
import net.sf.anathema.hero.charms.sheet.content.stats.MultipleEffectCharmStats;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.spells.SpellsModelFetcher;
import net.sf.anathema.hero.spells.sheet.content.SpellStats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MagicContentHelper {

  private final Hero hero;

  public MagicContentHelper(Hero hero) {
    this.hero = hero;
  }

  public static List<IMagicStats> collectPrintCharms(ReportSession session) {
    final MagicContentHelper helper = new MagicContentHelper(session.getHero());
    final List<IMagicStats> printStats = new ArrayList<>();
    for (IMagicStats stats : helper.getGenericCharmStats()) {
      if (helper.shouldShowCharm(stats)) {
        printStats.add(stats);
      }
    }
    for (Charm charm: helper.getLearnedCharms()) {
      if (helper.isGenericCharmFor(charm)) {
        continue;
      }
      if (helper.isMultipleEffectCharm(charm)) {
        String[] effects = helper.getLearnedEffects(charm);
        for (String effect : effects) {
          printStats.add(new MultipleEffectCharmStats(charm, effect));
        }
      } else {
        printStats.add(new CharmStats(charm, helper));
      }
    }
    return printStats;
  }

  public static List<IMagicStats> collectPrintSpells(ReportSession session) {
    final List<IMagicStats> printStats = new ArrayList<>();
    final MagicContentHelper helper = new MagicContentHelper(session.getHero());
    for (ISpell spell : helper.getAllLearnedSpells()) {
      printStats.add(new SpellStats(spell));
    }
    return printStats;
  }

  public List<Magic> getAllLearnedMagic() {
    List<Magic> magicList = new ArrayList<>();
    magicList.addAll(Arrays.asList(getLearnedCharms()));
    magicList.addAll(getAllLearnedSpells());
    return magicList;
  }

  private List<ISpell> getAllLearnedSpells() {
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    return Arrays.asList(SpellsModelFetcher.fetch(hero).getLearnedSpells(experienced));
  }

  public boolean isMultipleEffectCharm(Charm charm) {
    ISpecialCharmConfiguration charmConfiguration = CharmsModelFetcher.fetch(hero).getSpecialCharmConfiguration(charm);
    return charmConfiguration instanceof IMultipleEffectCharmConfiguration && !(charmConfiguration instanceof ISubeffectCharmConfiguration);
  }

  public String[] getLearnedEffects(Charm charm) {
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

  public Charm[] getGenericCharms() {
    List<Charm> genericCharms = new ArrayList<>();
    for (ILearningCharmGroup group : CharmsModelFetcher.fetch(hero).getAllGroups()) {
      for (Charm charm : group.getAllCharms()) {
        if (charm.isInstanceOfGenericCharm() && charm.getCharacterType().equals(hero.getTemplate().getTemplateType().getCharacterType())) {
          genericCharms.add(charm);
        }
      }
    }
    return genericCharms.toArray(new Charm[genericCharms.size()]);
  }

  private Charm[] getLearnedCharms() {
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    return CharmsModelFetcher.fetch(hero).getLearnedCharms(experienced);
  }

  public boolean isSubeffectCharm(Charm charm) {
    ISpecialCharmConfiguration charmConfiguration = CharmsModelFetcher.fetch(hero).getSpecialCharmConfiguration(charm);
    return charmConfiguration instanceof ISubeffectCharmConfiguration;
  }

  public int getLearnCount(Charm charm) {
    return getLearnCount(charm, CharmsModelFetcher.fetch(hero));
  }

  private int getLearnCount(Charm charm, CharmsModel configuration) {
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
    for (Magic magic : getAllLearnedMagic()) {
      if (magic.getId().startsWith(stats.getName().getId())) {
        return true;
      }
    }
    return false;
  }

  public boolean isGenericCharmFor(Charm charm) {
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
    for (Charm charm : getGenericCharms()) {
      IMagicStats stats = new GenericCharmStats(charm, hero);
      if (!genericCharmStats.contains(stats)) {
        genericCharmStats.add(stats);
      }
    }
    return genericCharmStats.toArray(new IMagicStats[genericCharmStats.size()]);
  }

  public boolean isCharmLearned(List<Magic> allLearnedMagic, final String charmId) {
    Optional<? extends Magic> optional = Iterables.tryFind(allLearnedMagic, new Predicate<Magic>() {
      @Override
      public boolean apply(Magic value) {
        return charmId.equals(value.getId());
      }
    });
    return optional.isPresent();
  }
}
