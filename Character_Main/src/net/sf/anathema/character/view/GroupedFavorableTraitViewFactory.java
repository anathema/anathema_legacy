package net.sf.anathema.character.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.library.trait.view.swing.SwingGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.framework.value.IntegerViewFactory;

@RegisteredCharacterView(GroupedFavorableTraitConfigurationView.class)
public class GroupedFavorableTraitViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    IntegerViewFactory withMarker = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type);
    return (T) new SwingGroupedFavorableTraitConfigurationView(withMarker);
  }
}