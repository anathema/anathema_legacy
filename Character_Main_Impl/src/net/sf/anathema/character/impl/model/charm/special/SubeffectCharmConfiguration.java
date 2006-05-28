package net.sf.anathema.character.impl.model.charm.special;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.model.charm.ICharmLearnableArbitrator;
import net.sf.anathema.character.model.charm.special.ISubeffect;
import net.sf.anathema.character.model.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class SubeffectCharmConfiguration implements ISubeffectCharmConfiguration {

  private final ICharm charm;
  private final ISubeffect[] subeffects;
  private final GenericControl<ISpecialCharmLearnListener> control = new GenericControl<ISpecialCharmLearnListener>();
  private final double pointCost;

  public SubeffectCharmConfiguration(
      ICharacterModelContext context,
      final ICharm charm,
      ISubeffectCharm visited,
      final ICharmLearnableArbitrator arbitrator) {
    this.charm = charm;
    List<ISubeffect> effectList = new ArrayList<ISubeffect>();
    ICondition condition = new ICondition() {
      public boolean isFullfilled() {
        return arbitrator.isLearnable(charm);
      }
    };
    pointCost = visited.getPointCost();
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
    subeffects = effectList.toArray(new ISubeffect[effectList.size()]);
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

  public ISubeffect[] getSubeffects() {
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
    for (ISubeffect subeffect : subeffects) {
      if (subeffect.isCreationLearned()) {
        return 1;
      }
    }
    return 0;
  }

  public int getCurrentLearnCount() {
    for (ISubeffect subeffect : subeffects) {
      if (subeffect.isLearned()) {
        return 1;
      }
    }
    return 0;
  }

  public int getCreationLearnedSubeffectCount() {
    int count = 0;
    for (ISubeffect subeffect : subeffects) {
      if (subeffect.isCreationLearned()) {
        count++;
      }
    }
    return count;
  }

  public int getExperienceLearnedSubeffectCount() {
    int count = 0;
    for (ISubeffect subeffect : subeffects) {
      if (subeffect.isLearned() && !subeffect.isCreationLearned()) {
        count++;
      }
    }
    return count;
  }

  public double getPointCostPerEffect() {
    return pointCost;
  }
}