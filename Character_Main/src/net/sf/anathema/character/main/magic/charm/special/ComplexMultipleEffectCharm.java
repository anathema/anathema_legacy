package net.sf.anathema.character.main.magic.charm.special;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.charms.special.SubEffects;
import net.sf.anathema.character.main.charm.CharmSpecialist;
import net.sf.anathema.lib.data.Condition;

import java.util.Map;

public class ComplexMultipleEffectCharm extends MultipleEffectCharm {
  Map<String, String> prereqEffectMap;

  public ComplexMultipleEffectCharm(String charmId, String[] effectIds, Map<String, String> prereqEffect) {
    super(charmId, effectIds);
    prereqEffectMap = prereqEffect;
  }

  @Override
  public SubEffects buildSubeffects(CharmSpecialist specialist, ICharmLearnableArbitrator arbitrator, ICharm charm) {
    CollectionSubEffects subEffects = new CollectionSubEffects();
    for (String id : effectIds) {
      String prerequisiteEffect = prereqEffectMap.get(id);
      Condition condition = buildLearnCondition(arbitrator, charm, prerequisiteEffect, subEffects);
      subEffects.add(new Subeffect(id, specialist.getExperience(), condition));
    }
    return subEffects;
  }

  private Condition buildLearnCondition(ICharmLearnableArbitrator arbitrator, ICharm charm, String prereqEffect, CollectionSubEffects subEffects) {
    return new PrerequisiteLearnCondition(subEffects, arbitrator, charm, prereqEffect);
  }
}