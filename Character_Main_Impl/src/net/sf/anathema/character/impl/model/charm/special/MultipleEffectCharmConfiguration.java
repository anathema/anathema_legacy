package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.lib.collection.ArrayUtilities;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.IPredicate;
import org.jmock.example.announcer.Announcer;

public class MultipleEffectCharmConfiguration implements IMultipleEffectCharmConfiguration {
  private final ICharm charm;
  private final ISubeffect[] subeffects;
  private final Announcer<ISpecialCharmLearnListener> control = Announcer.to(ISpecialCharmLearnListener.class);

  public MultipleEffectCharmConfiguration(
      ICharacterModelContext context,
      ICharm charm,
      IMultipleEffectCharm visited,
      ICharmLearnableArbitrator arbitrator) {
    this.charm = charm;
    this.subeffects = visited.buildSubeffects(context.getBasicCharacterContext(),
    		context.getTraitCollection(), arbitrator, charm).getEffects();
    for (ISubeffect subeffect : subeffects) {
      subeffect.addChangeListener(new IChangeListener() {
        @Override
        public void changeOccurred() {
          fireLearnCountChanged();
        }
      });
    }
  }

  @Override
  public void forget() {
    for (ISubeffect effect : subeffects) {
      effect.setLearned(false);
    }
  }

  @Override
  public void learn(boolean experienced) {
    if (experienced && getCurrentLearnCount() == 0) {
      subeffects[0].setExperienceLearned(true);
    }
    else if (!experienced && getCreationLearnCount() == 0) {
      subeffects[0].setCreationLearned(true);
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
  public ICharm getCharm() {
    return charm;
  }

  @Override
  public ISubeffect[] getEffects() {
    return subeffects;
  }

  @Override
  public ISubeffect getEffectById(final String id) {
    return ArrayUtilities.getFirst(subeffects, new IPredicate<ISubeffect>() {
      @Override
      public boolean evaluate(ISubeffect t) {
        return t.getId().equals(id);
      }
    });
  }

  @Override
  public int getCreationLearnCount() {
    int sum = 0;
    for (ISubeffect subeffect : subeffects) {
      if (subeffect.isCreationLearned()) {
        sum++;
      }
    }
    return sum;
  }

  @Override
  public int getCurrentLearnCount() {
    int sum = 0;
    for (ISubeffect subeffect : subeffects) {
      if (subeffect.isLearned()) {
        sum++;
      }
    }
    return sum;
  }
}