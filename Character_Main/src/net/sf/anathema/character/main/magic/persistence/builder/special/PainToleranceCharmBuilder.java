package net.sf.anathema.character.main.magic.persistence.builder.special;

import net.sf.anathema.character.main.magic.charms.special.StaticPainToleranceCharm;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharm;
import org.dom4j.Element;

import java.util.List;

@SpecialCharmParser
public class PainToleranceCharmBuilder implements SpecialCharmBuilder {

  private static final String ATTRIB_VALUE = "value";
  private static final String TAG_PAIN_TOLERANCE = "painTolerance";
  private static final String TAG_LEVEL = "level";

  public ISpecialCharm readCharm(Element charmElement, String id) {
    Element painToleranceElement = charmElement.element(TAG_PAIN_TOLERANCE);
    @SuppressWarnings("unchecked") List<Element> elements = painToleranceElement.elements(TAG_LEVEL);
    int[] levelArray = new int[elements.size()];
    for (int i = 0; i != elements.size(); i++) {
      levelArray[i] = Integer.parseInt(elements.get(i).attributeValue(ATTRIB_VALUE));
    }
    return new StaticPainToleranceCharm(id, levelArray.length, levelArray);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element painToleranceElement = charmElement.element(TAG_PAIN_TOLERANCE);
    return painToleranceElement != null;
  }
}