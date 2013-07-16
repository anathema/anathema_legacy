package net.sf.anathema.character.main.magic.parser.charms.special;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charm.special.SubEffectCharm;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

@RegisteredSpecialCharmBuilder
public class SubEffectCharmBuilder implements SpecialCharmBuilder {

  private static final String TAG_SUB_EFFECTS = "Subeffects";
  private static final String TAG_SUB_EFFECT = "Subeffect";
  private static final String TAG_BP_COST = "bpCost";

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    Element subEffectElement = charmElement.element(TAG_SUB_EFFECTS);
    double cost = Double.parseDouble(subEffectElement.attributeValue(TAG_BP_COST));
    List<String> subEffects = new ArrayList<>();
    for (Object subEffectObj : subEffectElement.elements(TAG_SUB_EFFECT)) {
      Element subEffect = (Element) subEffectObj;
      String name = subEffect.attributeValue(ATTRIB_NAME);
      subEffects.add(name);
    }
    String[] effects = new String[subEffects.size()];
    subEffects.toArray(effects);
    return new SubEffectCharm(id, effects, cost);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    return charmElement.element(TAG_SUB_EFFECTS) != null;
  }
}