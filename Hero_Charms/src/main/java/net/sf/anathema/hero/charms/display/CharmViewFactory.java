package net.sf.anathema.hero.charms.display;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.hero.charms.display.view.BridgingCharmView;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.display.view.FxCharmView;

@RegisteredCharacterView(CharmView.class)
public class CharmViewFactory implements SubViewFactory {
  //The special types are registered here so cascades don't need a character type as well.
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(CharacterType type) {
    FxCharmView fxView = new FxCharmView();
    //TODO Special Types
    //IntegerViewFactory factory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type);
    //swingCharmView.registerSpecialType(IntValueView.class, new SpecialIntDisplayFactory(factory));
    //swingCharmView.registerSpecialType(IBooleanValueView.class, new SpecialBooleanDisplayFactory());
    return (T) new BridgingCharmView(fxView);
  }
}
