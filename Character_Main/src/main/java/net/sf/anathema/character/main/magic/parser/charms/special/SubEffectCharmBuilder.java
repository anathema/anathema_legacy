package net.sf.anathema.character.main.magic.parser.charms.special;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charm.special.SubEffectCharm;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

@SpecialCharmParser
public class SubEffectCharmBuilder implements SpecialCharmBuilder {

  private static final String TAG_SUBEFFECTS = "Subeffects";
  private static final String TAG_SUBEFFECT = "Subeffect";
  private static final String TAG_BP_COST = "bpCost";

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    Element subeffectElement = charmElement.element(TAG_SUBEFFECTS);
    double cost = Double.parseDouble(subeffectElement.attributeValue(TAG_BP_COST));
    List<String> subeffects = new ArrayList<>();
    for (Object subeffectObj : subeffectElement.elements(TAG_SUBEFFECT)) {
      Element subeffect = (Element) subeffectObj;
      String name = subeffect.attributeValue(ATTRIB_NAME);
      subeffects.add(name);
    }
    String[] effects = new String[subeffects.size()];
    subeffects.toArray(effects);
    return new SubEffectCharm(id, effects, cost);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element subeffectElement = charmElement.element(TAG_SUBEFFECTS);
    return subeffectElement != null;
  }
}