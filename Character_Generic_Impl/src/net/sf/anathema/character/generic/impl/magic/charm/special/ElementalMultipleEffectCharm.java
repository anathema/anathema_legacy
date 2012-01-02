package net.sf.anathema.character.generic.impl.magic.charm.special;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class ElementalMultipleEffectCharm implements IMultipleEffectCharm {

  private final String charmId;
  private final List<ElementalSubeffect> effectList = new ArrayList<ElementalSubeffect>();

  public ElementalMultipleEffectCharm(String charmId) {
    this.charmId = charmId;
  }

  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitMultipleEffectCharm(this);
  }

  public String getCharmId() {
    return charmId;
  }

  public ISubeffect[] buildSubeffects(IBasicCharacterData data,
                                      IGenericTraitCollection traitCollection,
                                      ICharmLearnableArbitrator arbitrator,
                                      ICharm charm) {
    if (effectList.isEmpty()) {
      for (Element element : Element.values()) {
        effectList.add(new ElementalSubeffect(element, data, buildLearnCondition(element, data, arbitrator, charm)));
      }
    }
    return effectList.toArray(new ISubeffect[effectList.size()]);
  }

  private ICondition buildLearnCondition(Element element, IBasicCharacterData data, ICharmLearnableArbitrator arbitrator, ICharm charm) {
    return new ElementalCharmLearnCondition(effectList, arbitrator, charm, data, element);
  }
}