package net.sf.anathema.fx.hero.traitview;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;

@RegisteredCharacterView(GroupedFavorableTraitConfigurationView.class)
public class GroupedFavorableTraitViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(CharacterType type) {
    FxGroupedTraitConfigurationView fxView = new FxGroupedTraitConfigurationView();
    BridgingTraitConfigurationView bridgingView = new BridgingTraitConfigurationView(fxView, type);
    return (T) bridgingView;
  }
}