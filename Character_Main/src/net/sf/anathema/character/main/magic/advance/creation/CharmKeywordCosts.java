package net.sf.anathema.character.main.magic.advance.creation;

import net.sf.anathema.character.main.magic.model.charm.ICharmAttribute;

import java.util.HashMap;
import java.util.Map;

public class CharmKeywordCosts {

  private Map<String, Integer> keywordCosts;

  public CharmKeywordCosts(Map<String, Integer> keywordCosts) {
    this.keywordCosts = keywordCosts == null ? new HashMap<String, Integer>() : keywordCosts;
  }

  public boolean hasCostFor(ICharmAttribute[] attributes) {
    return getCostAttribute(attributes) != null;
  }

  public int getCostFor(ICharmAttribute[] attributes) {
    ICharmAttribute costAttribute = getCostAttribute(attributes);
    return keywordCosts.get(costAttribute.getId());
  }

  private ICharmAttribute getCostAttribute(ICharmAttribute[] attributes) {
    for (ICharmAttribute attribute : attributes) {
      if (keywordCosts.containsKey(attribute.getId())) {
        return attribute;
      }
    }
    return null;
  }
}
