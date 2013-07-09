package net.sf.anathema.character.main.xml.creation.magic;

import net.sf.anathema.character.main.magic.model.charm.ICharmAttribute;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

import java.io.Serializable;
import java.util.Map;

public class CharmKeywordCosts extends ReflectionEqualsObject implements Serializable {

  private Map<String, Integer> keywordCosts;

  public void setKeywordCosts(Map<String, Integer> keywordCosts) {
    this.keywordCosts = keywordCosts;
  }

  public boolean hasCostFor(ICharmAttribute[] attributes) {
    return getCostAttribute(attributes) != null;
  }

  public int getCostFor(ICharmAttribute[] attributes) {
    ICharmAttribute costAttribute = getCostAttribute(attributes);
    return keywordCosts.get(costAttribute.getId());
  }

  private ICharmAttribute getCostAttribute(ICharmAttribute[] attributes) {
    if (keywordCosts == null) {
      return null;
    }
    for (ICharmAttribute attribute : attributes) {
      if (keywordCosts.containsKey(attribute.getId())) {
        return attribute;
      }
    }
    return null;
  }
}
