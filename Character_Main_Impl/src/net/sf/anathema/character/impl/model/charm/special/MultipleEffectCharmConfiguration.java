package net.sf.anathema.character.impl.model.charm.special;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.model.charm.ICharmLearnableArbitrator;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.model.charm.special.ISubeffect;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

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
    List<ISubeffect> effectList = new ArrayList<ISubeffect>();
    ICondition condition = new ICondition() {
      public boolean isFullfilled() {
        return arbitrator.isLearnable(charm);
      }
    };
    for (String subeffectId : visited.getSubeffectIds()) {
      effectList.add(new Subeffect(subeffectId, context.getBasicCharacterContext(), condition));
    }
    for (ISubeffect subeffect : effectList) {
      subeffect.addChangeListener(new IChangeListener() {
        public void changeOccured() {
          fireLearnCountChanged();
        }
      });
    }
    this.subeffects = effectList.toArray(new ISubeffect[effectList.size()]);
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