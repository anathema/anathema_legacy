package net.sf.anathema.character.generic.impl.magic.charm.special;

import java.util.ArrayList;
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
  List<ISubeffect> effectList = new ArrayList<ISubeffect>();

  public ComplexMultipleEffectCharm(String charmId, String[] effectIds, Map<String, String> prereqEffect) {
    super(charmId, effectIds);
    prereqEffectMap = prereqEffect;
  }

  public ISubeffect[] buildSubeffects(IBasicCharacterData data,
                                      IGenericTraitCollection traitCollection,
                                      ICharmLearnableArbitrator arbitrator,
                                      ICharm charm) {
    for (String id : effectIds) {
      String prereqEffect = prereqEffectMap.get(id);
      effectList.add(new Subeffect(id, data, buildLearnCondition(arbitrator, charm, prereqEffect)));
    }
    return effectList.toArray(new ISubeffect[effectList.size()]);
  }

  private ICondition buildLearnCondition(final ICharmLearnableArbitrator arbitrator,
                                         final ICharm charm,
                                         final String prereqEffect) {
    return new PrerequisiteLearnCondition(this, arbitrator, charm, prereqEffect);
  }
}