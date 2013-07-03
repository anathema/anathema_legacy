package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import net.sf.anathema.character.generic.GenericCharacter;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.character.main.model.charms.CharmsModelFetcher;
import net.sf.anathema.character.model.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharmStats extends AbstractCharmStats {

  private Hero hero;
  protected final IGenericCharacter character;

  public CharmStats(ICharm charm, Hero hero) {
    super(charm);
    this.hero = hero;
    this.character = new GenericCharacter(hero);
  }

  @Override
  protected String[] getDetailKeys() {
    String[] detailKeys = super.getDetailKeys();
    List<String> details = new ArrayList<>();
    Collections.addAll(details, detailKeys);
    if (isSubeffectCharm(getMagic())) {
      for (String subeffectId : character.getLearnedEffects(getMagic())) {
        details.add(getMagic().getId() + ".Subeffects." + subeffectId);
      }
    }
    return details.toArray(new String[details.size()]);
  }

  @Override
  public String getNameString(Resources resources) {
    StringBuilder nameString = new StringBuilder();
    nameString.append(resources.getString(getMagic().getId()));
    int learnCount = getLearnCount(getMagic());
    if (learnCount > 1) {
      nameString.append(" (");
      nameString.append(learnCount);
      nameString.append("x)");
    }
    return nameString.toString();
  }

  private boolean isSubeffectCharm(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = CharmsModelFetcher.fetch(hero).getSpecialCharmConfiguration(charm);
    return charmConfiguration instanceof ISubeffectCharmConfiguration;
  }

  private int getLearnCount(ICharm charm) {
    return getLearnCount(charm, CharmsModelFetcher.fetch(hero));
  }

  private int getLearnCount(ICharm charm, CharmsModel configuration) {
    ISpecialCharmConfiguration specialCharmConfiguration = configuration.getSpecialCharmConfiguration(charm.getId());
    if (specialCharmConfiguration != null) {
      return specialCharmConfiguration.getCurrentLearnCount();
    }
    return configuration.isLearned(charm) ? 1 : 0;
  }
}