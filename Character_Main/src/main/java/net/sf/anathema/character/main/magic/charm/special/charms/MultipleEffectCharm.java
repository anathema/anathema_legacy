package net.sf.anathema.character.main.magic.charm.special.charms;

import com.google.common.base.Objects;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmSpecialist;
import net.sf.anathema.character.main.magic.charm.special.ArbitratorLearnCondition;
import net.sf.anathema.character.main.magic.charm.special.ArraySubEffects;
import net.sf.anathema.character.main.magic.charm.special.SubEffect;
import net.sf.anathema.character.main.magic.charm.special.SubEffectImpl;
import net.sf.anathema.character.main.magic.charm.special.SubEffects;
import net.sf.anathema.character.main.magic.charm.special.charms.IMultipleEffectCharm;
import net.sf.anathema.character.main.magic.charm.special.charms.ISpecialCharmVisitor;
import net.sf.anathema.character.main.magic.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.lib.data.Condition;

import java.util.ArrayList;
import java.util.List;

public class MultipleEffectCharm implements IMultipleEffectCharm {

  private final String charmId;
  protected final String[] effectIds;

  public MultipleEffectCharm(String charmId, String[] effectIds) {
    this.charmId = charmId;
    this.effectIds = effectIds;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitMultipleEffectCharm(this);
  }

  @Override
  public String getCharmId() {
    return charmId;
  }

  @Override
  public SubEffects buildSubEffects(CharmSpecialist specialist, ICharmLearnableArbitrator arbitrator, Charm charm) {
    List<SubEffect> effectList = new ArrayList<>();
    for (String id : effectIds) {
      effectList.add(new SubEffectImpl(id, specialist.getExperience(), buildLearnCondition(arbitrator, charm)));
    }
    return new ArraySubEffects(effectList.toArray(new SubEffect[effectList.size()]));
  }

  private Condition buildLearnCondition(ICharmLearnableArbitrator arbitrator, Charm charm) {
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