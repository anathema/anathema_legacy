package net.sf.anathema.character.db.magic;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.db.aspect.DBAspect;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class ElementalMultipleEffectCharm implements IMultipleEffectCharm {

  private final String charmId;
  private final List<IElementalSubeffect> effectList = new ArrayList<IElementalSubeffect>();

  public ElementalMultipleEffectCharm(String charmId) {
    this.charmId = charmId;
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
      for (DBAspect aspect : DBAspect.values()) {
        effectList.add(new ElementalSubeffect(aspect, data, buildLearnCondition(aspect, data, arbitrator, charm)));
      }
    }
    return effectList.toArray(new ISubeffect[effectList.size()]);
  }

  private ICondition buildLearnCondition(
      final DBAspect aspect,
      final IBasicCharacterData data,
      final ICharmLearnableArbitrator arbitrator,
      final ICharm charm) {
    return new ICondition() {
      public boolean isFullfilled() {
        boolean learnable = arbitrator.isLearnable(charm);
        if (!data.getCharacterType().equals(CharacterType.DB)) {
          return learnable;
        }
        if (aspect.equals(data.getCasteType())) {
          return learnable;
        }
        for (IElementalSubeffect effect : effectList) {
          if (effect.isLearned() && effect.matches(data.getCasteType())) {
            return learnable;
          }
        }
        return false;
      }
    };
  }
}