package net.sf.anathema.character.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.library.trait.view.fx.BridgingTraitConfigurationView;
import net.sf.anathema.character.library.trait.view.fx.FxGroupedTraitConfigurationView;
import net.sf.anathema.character.platform.RegisteredCharacterView;

@RegisteredCharacterView(GroupedFavorableTraitConfigurationView.class)
public class GroupedFavorableTraitViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    FxGroupedTraitConfigurationView fxView = new FxGroupedTraitConfigurationView();
    BridgingTraitConfigurationView bridgingView = new BridgingTraitConfigurationView(fxView);
    return (T) bridgingView;
  }
}