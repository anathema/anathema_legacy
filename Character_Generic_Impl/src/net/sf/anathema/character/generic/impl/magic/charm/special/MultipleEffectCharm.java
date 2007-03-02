package net.sf.anathema.character.generic.impl.magic.charm.special;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class MultipleEffectCharm implements IMultipleEffectCharm {

  private final String charmId;
  private final String[] effectIds;

  public MultipleEffectCharm(String charmId, String[] effectIds) {
    this.charmId = charmId;
    this.effectIds = effectIds;
  }

  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitMultipleEffectCharm(this);
  }

  public String getCharmId() {
    return charmId;
  }

  @Override
  public ISubeffect[] buildSubeffects(IBasicCharacterData data, ICharmLearnableArbitrator arbitrator, ICharm charm) {
    List<ISubeffect> effectList = new ArrayList<ISubeffect>();
    for (String id : effectIds) {
      effectList.add(new Subeffect(id, data, buildLearnCondition(arbitrator, charm)));
    }
    return effectList.toArray(new ISubeffect[effectList.size()]);
  }

  private ICondition buildLearnCondition(final ICharmLearnableArbitrator arbitrator, final ICharm charm) {
    return new ICondition() {
      public boolean isFullfilled() {
        return arbitrator.isLearnable(charm);
      }
    };
  }
}