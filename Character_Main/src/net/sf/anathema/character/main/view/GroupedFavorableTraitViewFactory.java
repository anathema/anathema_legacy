package net.sf.anathema.character.main.view;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.main.library.trait.view.fx.BridgingTraitConfigurationView;
import net.sf.anathema.character.main.library.trait.view.fx.FxGroupedTraitConfigurationView;
import net.sf.anathema.character.main.type.ICharacterType;

@RegisteredCharacterView(GroupedFavorableTraitConfigurationView.class)
public class GroupedFavorableTraitViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    FxGroupedTraitConfigurationView fxView = new FxGroupedTraitConfigurationView();
    BridgingTraitConfigurationView bridgingView = new BridgingTraitConfigurationView(fxView, type);
    return (T) bridgingView;
  }
}