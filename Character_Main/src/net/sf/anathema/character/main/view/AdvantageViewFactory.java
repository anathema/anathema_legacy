package net.sf.anathema.character.main.view;

import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.framework.value.IntegerViewFactory;

@RegisteredCharacterView(AdvantageView.class)
public class AdvantageViewFactory implements SubViewFactory {

  @Override
  public <T> T create(ICharacterType type) {
    IntegerViewFactory factory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type);
    return (T) new BasicAdvantageView(factory);
  }
}