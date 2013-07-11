package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.special.ISubEffectCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.MultipleEffectCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.ISubeffect;
import net.sf.anathema.character.main.magic.model.charm.CharmSpecialist;
import net.sf.anathema.character.main.magic.model.charms.IExtendedCharmLearnableArbitrator;

public class SubeffectCharmConfiguration extends MultipleEffectCharmConfiguration implements ISubeffectCharmConfiguration {

  private final double pointCost;

  public SubeffectCharmConfiguration(CharmSpecialist specialist, Charm charm, ISubEffectCharm visited,
                                     IExtendedCharmLearnableArbitrator arbitrator) {
    super(specialist, charm, visited, arbitrator);
    this.pointCost = visited.getPointCost();
  }

  @Override
  public int getCreationLearnCount() {
    return super.getCreationLearnCount() > 0 ? 1 : 0;
  }

  @Override
  public int getCurrentLearnCount() {
    return super.getCurrentLearnCount() > 0 ? 1 : 0;
  }

  @Override
  public int getCreationLearnedSubeffectCount() {
    int count = 0;
    for (ISubeffect subeffect : getEffects()) {
      if (subeffect.isCreationLearned()) {
        count++;
      }
    }
    return count;
  }

  @Override
  public int getExperienceLearnedSubeffectCount() {
    int count = 0;
    for (ISubeffect subeffect : getEffects()) {
      if (subeffect.isLearned() && !subeffect.isCreationLearned()) {
        count++;
      }
    }
    return count;
  }

  @Override
  public double getPointCostPerEffect() {
    return pointCost;
  }
}