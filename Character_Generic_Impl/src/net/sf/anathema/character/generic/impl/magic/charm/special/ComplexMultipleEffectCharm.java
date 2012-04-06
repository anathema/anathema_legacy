package net.sf.anathema.character.generic.impl.magic.charm.special;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class ComplexMultipleEffectCharm extends MultipleEffectCharm {
  Map<String, String> prereqEffectMap;
  Map<IBasicCharacterData, ISubeffect[]> sessionsSubeffects =
		  new HashMap<IBasicCharacterData, ISubeffect[]>();

  public ComplexMultipleEffectCharm(String charmId, String[] effectIds, Map<String, String> prereqEffect) {
    super(charmId, effectIds);
    prereqEffectMap = prereqEffect;
  }

  public ISubeffect[] buildSubeffects(IBasicCharacterData data,
                                      IGenericTraitCollection traitCollection,
                                      ICharmLearnableArbitrator arbitrator,
                                      ICharm charm) {
	List<ISubeffect> effectList = new ArrayList<ISubeffect>();
    for (String id : effectIds) {
      String prerequisiteEffect = prereqEffectMap.get(id);
      effectList.add(new Subeffect(id, data, buildLearnCondition(data, arbitrator, charm, prerequisiteEffect)));
    }
    ISubeffect[] list = new ISubeffect[0];
    sessionsSubeffects.put(data, list);
    return effectList.toArray(list);
  }
  
  public ISubeffect[] getSubeffectsForSession(IBasicCharacterData data) {
	  return sessionsSubeffects.get(data);
  }

  private ICondition buildLearnCondition(IBasicCharacterData data, ICharmLearnableArbitrator arbitrator, ICharm charm, String prereqEffect) {
    return new PrerequisiteLearnCondition(data, this, arbitrator, charm, prereqEffect);
  }
}