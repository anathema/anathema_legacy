package net.sf.anathema.character.db.magic;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.impl.magic.charm.special.Subeffect;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class ElementalMultipleEffectCharm implements IMultipleEffectCharm {

  private final String[] effectIds;
  private final String charmId;
  private final List<ISubeffect> effectList = new ArrayList<ISubeffect>();

  public ElementalMultipleEffectCharm(String charmId, String[] effectIds) {
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
    if (effectList.isEmpty()) {
      for (String id : effectIds) {
        effectList.add(new Subeffect(id, data, buildLearnCondition(id, data, arbitrator, charm)));
      }
    }
    return effectList.toArray(new ISubeffect[effectList.size()]);
  }

  private ICondition buildLearnCondition(
      final String effectId,
      final IBasicCharacterData data,
      final ICharmLearnableArbitrator arbitrator,
      final ICharm charm) {
    return new ICondition() {
      public boolean isFullfilled() {
        boolean learnable = arbitrator.isLearnable(charm);
        if (!data.getCharacterType().equals(CharacterType.DB)) {
          return learnable;
        }
        if (isMatchingElement(data, effectId)) {
          return learnable;
        }
        for (ISubeffect effect : effectList) {
          if (effect.isLearned() && isMatchingElement(data, effect.getId())) {
            return learnable;
          }
        }
        return false;
      }
    };
  }

  private boolean isMatchingElement(final IBasicCharacterData data, String effectId) {
    return effectId.equals(data.getCasteType().getId());
  }
}