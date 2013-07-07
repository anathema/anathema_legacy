package net.sf.anathema.hero.magic.display;

import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.character.main.magic.display.view.charmtree.ICharmView;
import net.sf.anathema.framework.value.IntegerViewFactory;

@RegisteredCharacterView(ICharmView.class)
public class CharmViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    IntegerViewFactory factory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type);
    return (T) new CharmView(factory);
  }
}
