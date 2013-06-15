package net.sf.anathema.character.impl.view.magic;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.view.SubViewFactory;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.framework.value.IntegerViewFactory;

@RegisteredCharacterView(ICharmView.class)
public class CharmViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    IntegerViewFactory factory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type);
    return (T) new CharmView(factory);
  }
}
