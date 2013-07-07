package net.sf.anathema.character.main.magic.parser.special;

import net.sf.anathema.character.main.magic.model.charm.special.SubeffectCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

@SpecialCharmParser
public class SubEffectCharmBuilder implements SpecialCharmBuilder {

  private static final String TAG_SUBEFFECTS = "subeffects";
  private static final String TAG_SUBEFFECT = "subeffect";
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
    return new SubeffectCharm(id, effects, cost);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element subeffectElement = charmElement.element(TAG_SUBEFFECTS);
    return subeffectElement != null;
  }
}