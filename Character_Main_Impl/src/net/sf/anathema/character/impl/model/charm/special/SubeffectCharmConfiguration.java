package net.sf.anathema.character.impl.model.charm.special;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.model.charm.special.ISubeffect;
import net.sf.anathema.character.model.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.change.IChangeListener;

public class SubeffectCharmConfiguration implements ISubeffectCharmConfiguration {

  private final ICharm charm;
  private final ISubeffect[] subeffects;
  private final GenericControl<ISpecialCharmLearnListener> control = new GenericControl<ISpecialCharmLearnListener>();

  public SubeffectCharmConfiguration(ICharacterModelContext context, ICharm charm, ISubeffectCharm visited) {
    this.charm = charm;
    List<ISubeffect> effectList = new ArrayList<ISubeffect>();
    for (String subeffectId : visited.getSubeffectIds()) {
      effectList.add(new Subeffect(subeffectId, context.getBasicCharacterContext()));
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
}