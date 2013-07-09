package net.sf.anathema.hero.charms.display;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmView;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.framework.value.IntegerViewFactory;

@RegisteredCharacterView(CharmView.class)
public class CharmViewFactory implements SubViewFactory {
  //TODO (Swing->FX) Needs character type
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    IntegerViewFactory factory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type);
    return (T) new SwingCharmView(factory);
  }
}
