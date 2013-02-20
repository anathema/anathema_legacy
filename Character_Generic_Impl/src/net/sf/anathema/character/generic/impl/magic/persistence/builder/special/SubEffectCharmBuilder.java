package net.sf.anathema.character.generic.impl.magic.persistence.builder.special;

import net.sf.anathema.character.generic.impl.magic.charm.special.SubeffectCharm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.SpecialCharmBuilder;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class SubEffectCharmBuilder {

  private static final String TAG_SUBEFFECTS = "subeffects";
  private static final String TAG_SUBEFFECT = "subeffect";
  private static final String TAG_BP_COST = "bpCost";

  public ISpecialCharm readSubEffectCharm(Element charmElement, String id) {
    Element subeffectElement = charmElement.element(TAG_SUBEFFECTS);
    if (subeffectElement == null) {
      return null;
    }
    double cost = Double.parseDouble(subeffectElement.attributeValue(TAG_BP_COST));
    List<String> subeffects = new ArrayList<>();
    for (Object subeffectObj : subeffectElement.elements(TAG_SUBEFFECT)) {
      Element subeffect = (Element) subeffectObj;
      String name = subeffect.attributeValue(SpecialCharmBuilder.ATTRIB_NAME);
      subeffects.add(name);
    }
    String[] effects = new String[subeffects.size()];
    subeffects.toArray(effects);
    return new SubeffectCharm(id, effects, cost);
  }
}
