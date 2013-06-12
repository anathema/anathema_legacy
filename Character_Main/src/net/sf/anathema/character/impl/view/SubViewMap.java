package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;

import java.util.HashMap;
import java.util.Map;

public class SubViewMap {

  private final Map<Class, SubViewFactory> map = new HashMap<>();

  public SubViewMap() {
    map.put(ICharacterDescriptionView.class, new DescriptionViewFactory());
    map.put(ICharacterConceptAndRulesView.class, new ConceptViewFactory());
  }

  public <T> T get(Class<T> viewClass){
    return map.get(viewClass).create();
  }
}
