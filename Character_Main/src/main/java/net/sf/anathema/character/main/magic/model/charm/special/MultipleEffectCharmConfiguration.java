package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmSpecialist;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.lib.control.ChangeListener;
import org.jmock.example.announcer.Announcer;

public class MultipleEffectCharmConfiguration implements MultipleEffectCharmSpecials {
  private final Charm charm;
  private final SubEffects subeffects;
  private final Announcer<ISpecialCharmLearnListener> control = Announcer.to(ISpecialCharmLearnListener.class);

  public MultipleEffectCharmConfiguration(CharmSpecialist specialist, Charm charm, IMultipleEffectCharm visited,
                                          ICharmLearnableArbitrator arbitrator) {
    this.charm = charm;
    this.subeffects = visited.buildSubEffects(specialist, arbitrator, charm);
    for (SubEffect subeffect : subeffects) {
      subeffect.addChangeListener(new ChangeListener() {
        @Override
        public void changeOccurred() {
          fireLearnCountChanged();
        }
      });
    }
  }

  @Override
  public void forget() {
    for (SubEffect effect : subeffects) {
      effect.setLearned(false);
    }
  }

  @Override
  public void learn(boolean experienced) {
    SubEffect firstEffect = subeffects.getEffects()[0];
    if (experienced && getCurrentLearnCount() == 0) {
      firstEffect.setExperienceLearned(true);
    } else if (!experienced && getCreationLearnCount() == 0) {
      firstEffect.setCreationLearned(true);
    }
  }

  private void fireLearnCountChanged() {
    control.announce().learnCountChanged(getCurrentLearnCount());
  }

  @Override
  public void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener) {
    control.addListener(listener);
  }

  @Override
  public Charm getCharm() {
    return charm;
  }

  @Override
  public SubEffect[] getEffects() {
    return subeffects.getEffects();
  }

  @Override
  public SubEffect getEffectById(final String id) {
    return subeffects.getById(id);
  }

  @Override
  public int getCreationLearnCount() {
    int sum = 0;
    for (SubEffect subeffect : subeffects) {
      if (subeffect.isCreationLearned()) {
        sum++;
      }
    }
    return sum;
  }

  @Override
  public int getCurrentLearnCount() {
    int sum = 0;
    for (SubEffect subeffect : subeffects) {
      if (subeffect.isLearned()) {
        sum++;
      }
    }
    return sum;
  }

  protected SubEffects getSubeffects() {
    return subeffects;
  }
}