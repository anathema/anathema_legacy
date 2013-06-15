package net.sf.anathema.character.generic.impl.magic.persistence.builder.special;

import net.sf.anathema.character.generic.impl.magic.charm.special.ComplexMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpecialCharmParser
public class MultiEffectCharmBuilder implements SpecialCharmBuilder {
  private static final String TAG_MULTI_EFFECT = "multiEffects";
  private static final String TAG_EFFECT = "effect";
  private static final String ATTRIB_PREREQ_EFFECT = "prereqEffect";

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    Element multiEffectElement = charmElement.element(TAG_MULTI_EFFECT);
    List<String> effects = new ArrayList<>();
    Map<String, String> prereqEffectMap = new HashMap<>();
    for (Object effectObj : multiEffectElement.elements(TAG_EFFECT)) {
      Element effect = (Element) effectObj;
      String name = effect.attributeValue(ATTRIB_NAME);
      effects.add(name);

      String prereqEffect = effect.attributeValue(ATTRIB_PREREQ_EFFECT);
      prereqEffectMap.put(name, prereqEffect);
    }
    String[] effectArray = new String[effects.size()];
    effects.toArray(effectArray);
    return new ComplexMultipleEffectCharm(id, effectArray, prereqEffectMap);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element multiEffectElement = charmElement.element(TAG_MULTI_EFFECT);
    return multiEffectElement != null;
  }
}