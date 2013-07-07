package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.model.view.SubViewFactory;
import net.sf.anathema.framework.value.IntegerViewFactory;

@RegisteredCharacterView(IntimaciesView.class)
public class IntimaciesSubViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    IntegerViewFactory factory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type);
    return (T) new IntimaciesViewImpl(factory);
  }
}
