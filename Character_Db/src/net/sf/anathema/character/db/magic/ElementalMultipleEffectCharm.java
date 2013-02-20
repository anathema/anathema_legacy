package net.sf.anathema.character.db.magic;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.magic.charm.special.CollectionSubEffects;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.SubEffects;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class ElementalMultipleEffectCharm implements IMultipleEffectCharm {

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
    CollectionSubEffects subEffects = new CollectionSubEffects();
    for (Element element : Element.values()) {
      ICondition condition = buildLearnCondition(element, data, arbitrator, charm, subEffects);
      subEffects.add(new ElementalSubeffect(element, data, condition));
    }
    return subEffects;
  }

  private ICondition buildLearnCondition(Element element, IBasicCharacterData data,
                                         ICharmLearnableArbitrator arbitrator, ICharm charm,
                                         CollectionSubEffects subEffects) {
    return new ElementalCharmLearnCondition(subEffects, arbitrator, charm, data, element);
  }
}