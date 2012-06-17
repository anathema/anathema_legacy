package net.sf.anathema.character.generic.impl.magic.charm.special;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.SubEffects;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class ElementalMultipleEffectCharm implements IMultipleEffectCharm {

  private final Map<IBasicCharacterData, ElementalSubeffect[]> sessionSubeffects =
		  new HashMap<IBasicCharacterData, ElementalSubeffect[]>();
  private final String charmId;

  public ElementalMultipleEffectCharm(String charmId) {
    this.charmId = charmId;
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
  public SubEffects buildSubeffects(IBasicCharacterData data, IGenericTraitCollection traitCollection,
                                    ICharmLearnableArbitrator arbitrator, ICharm charm) {
	List<ElementalSubeffect> effectList = new ArrayList<ElementalSubeffect>();
    for (Element element : Element.values()) {
      effectList.add(new ElementalSubeffect(element, data, buildLearnCondition(element, data, arbitrator, charm)));
    }
    ElementalSubeffect[] list = effectList.toArray(new ElementalSubeffect[effectList.size()]);
    sessionSubeffects.put(data, list);
    return new DefaultSubEffects(list);
  }
  
  public ElementalSubeffect[] getSessionSubeffects(IBasicCharacterData data) {
	  return sessionSubeffects.get(data);
  }

  private ICondition buildLearnCondition(Element element, IBasicCharacterData data, ICharmLearnableArbitrator arbitrator, ICharm charm) {
    return new ElementalCharmLearnCondition(this, arbitrator, charm, data, element);
  }
}