package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.character.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;
import net.sf.anathema.hero.charms.model.special.subeffects.MultipleEffectCharmSpecials;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffect;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffectCharmSpecials;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharmContentHelper {

  private final Hero hero;

  public CharmContentHelper(Hero hero) {
    this.hero = hero;
  }

  public static List<IMagicStats> collectPrintCharms(ReportSession session) {
    List<IMagicStats> printStats = new ArrayList<>();
    new PrintCharmsProvider(session.getHero()).addPrintMagic(printStats);
    return printStats;
  }

  public boolean isMultipleEffectCharm(Charm charm) {
    CharmSpecialsModel charmConfiguration = CharmsModelFetcher.fetch(hero).getCharmSpecialsModel(charm);
    return charmConfiguration instanceof MultipleEffectCharmSpecials && !(charmConfiguration instanceof SubEffectCharmSpecials);
  }

  public String[] getLearnedEffects(Charm charm) {
    CharmSpecialsModel charmConfiguration = CharmsModelFetcher.fetch(hero).getCharmSpecialsModel(charm);
    if (!(charmConfiguration instanceof MultipleEffectCharmSpecials)) {
      return new String[0];
    }
    MultipleEffectCharmSpecials configuration = (MultipleEffectCharmSpecials) charmConfiguration;
    List<String> learnedEffectIds = new ArrayList<>();
    for (SubEffect effect : configuration.getEffects()) {
      if (effect.isLearned()) {
        learnedEffectIds.add(effect.getId());
      }
    }
    return learnedEffectIds.toArray(new String[learnedEffectIds.size()]);
  }

  public List<Charm> getLearnedCharms() {
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    return Arrays.asList(CharmsModelFetcher.fetch(hero).getLearnedCharms(experienced));
  }

  public boolean isSubEffectCharm(Charm charm) {
    CharmSpecialsModel charmConfiguration = CharmsModelFetcher.fetch(hero).getCharmSpecialsModel(charm);
    return charmConfiguration instanceof SubEffectCharmSpecials;
  }

  public int getLearnCount(Charm charm) {
    return getLearnCount(charm, CharmsModelFetcher.fetch(hero));
  }

  private int getLearnCount(Charm charm, CharmsModel model) {
    CharmSpecialsModel specialCharmConfiguration = model.getSpecialCharmConfiguration(charm.getId());
    if (specialCharmConfiguration != null) {
      return specialCharmConfiguration.getCurrentLearnCount();
    }
    return model.isLearned(charm) ? 1 : 0;
  }

  public boolean shouldShowCharm(IMagicStats stats) {
    if (AnathemaCharacterPreferences.getDefaultPreferences().printAllGenerics()) {
      return true;
    }
    for (Charm magic : getLearnedCharms()) {
      if (magic.getId().startsWith(stats.getName().getId())) {
        return true;
      }
    }
    return false;
  }
}
