package net.sf.anathema.hero.charms.display;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.hero.charms.display.special.SpecialBooleanDisplayFactory;
import net.sf.anathema.hero.charms.display.special.SpecialIntDisplayFactory;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.display.view.SwingCharmView;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;

@RegisteredCharacterView(CharmView.class)
public class CharmViewFactory implements SubViewFactory {
  //TODO (Swing->FX) Needs character type
  //The special types are registered here so cascades don't need a character type as well.
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(CharacterType type) {
    IntegerViewFactory factory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type);
    SwingCharmView swingCharmView = new SwingCharmView();
    swingCharmView.registerSpecialType(IntValueView.class, new SpecialIntDisplayFactory(factory));
    swingCharmView.registerSpecialType(IBooleanValueView.class, new SpecialBooleanDisplayFactory());
    return (T) swingCharmView;
  }
}
