package net.sf.anathema.character.impl.model.charm.special;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.change.IChangeListener;

public class MultipleEffectCharmConfiguration implements IMultipleEffectCharmConfiguration {
  private final ICharm charm;
  private final ISubeffect[] subeffects;
  private final GenericControl<ISpecialCharmLearnListener> control = new GenericControl<ISpecialCharmLearnListener>();

  public MultipleEffectCharmConfiguration(
      ICharacterModelContext context,
      final ICharm charm,
      IMultipleEffectCharm visited,
      final ICharmLearnableArbitrator arbitrator) {
    this.charm = charm;
    this.subeffects = visited.buildSubeffects(context.getBasicCharacterContext(), arbitrator, charm);
    for (ISubeffect subeffect : subeffects) {
      subeffect.addChangeListener(new IChangeListener() {
        public void changeOccured() {
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
    else if (getCreationLearnCount() == 0) {
      subeffects[0].setCreationLearned(true);
    }
  }

  private void fireLearnCountChanged() {
    control.forAllDo(new IClosure<ISpecialCharmLearnListener>() {
      public void execute(ISpecialCharmLearnListener input) {
        input.learnCountChanged(getCurrentLearnCount());
      }
    });
  }

  public void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener) {
    control.addListener(listener);
  }

  public ICharm getCharm() {
    return charm;
  }

  public ISubeffect[] getEffects() {
    return subeffects;
  }

  public ISubeffect getEffectById(final String id) {
    return ArrayUtilities.getFirst(subeffects, new IPredicate<ISubeffect>() {
      public boolean evaluate(ISubeffect t) {
        return t.getId().equals(id);
      }
    });
  }

  public int getCreationLearnCount() {
    int sum = 0;
    for (ISubeffect subeffect : subeffects) {
      if (subeffect.isCreationLearned()) {
        sum++;
      }
    }
    return sum;
  }

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