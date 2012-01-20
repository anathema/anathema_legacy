package net.sf.anathema.character.generic.impl.magic.charm.special;

import com.google.common.base.Objects;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

import java.util.ArrayList;
import java.util.List;

public class MultipleEffectCharm implements IMultipleEffectCharm {

  private final String charmId;
  protected final String[] effectIds;

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
  public ISubeffect[] buildSubeffects(IBasicCharacterData data,
                                      IGenericTraitCollection traitCollection,
                                      ICharmLearnableArbitrator arbitrator,
                                      ICharm charm) {
    List<ISubeffect> effectList = new ArrayList<ISubeffect>();
    for (String id : effectIds) {
      effectList.add(new Subeffect(id, data, buildLearnCondition(arbitrator, charm)));
    }
    return effectList.toArray(new ISubeffect[effectList.size()]);
  }

  private ICondition buildLearnCondition(ICharmLearnableArbitrator arbitrator, ICharm charm) {
    return new ArbitratorLearnCondition(arbitrator, charm);
  }

  public String toString() {
    StringBuilder list = new StringBuilder();
    for (String effect : effectIds) {
      boolean isLastEffect = Objects.equal(effect, effectIds[effectIds.length - 1]);
      list.append(effect);
      list.append(isLastEffect ? "" : ",");
    }
    return "[" + getCharmId() + ";" + list + "]";
  }
}