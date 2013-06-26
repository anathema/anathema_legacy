package net.sf.anathema.character.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.framework.value.IntegerViewFactory;

@RegisteredCharacterView(AdvantageView.class)
public class AdvantageViewFactory implements SubViewFactory {

  @Override
  public <T> T create(ICharacterType type) {
    IntegerViewFactory factory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type);
    return (T) new BasicAdvantageView(factory);
  }
}