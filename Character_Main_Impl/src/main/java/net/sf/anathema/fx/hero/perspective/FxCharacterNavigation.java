package net.sf.anathema.fx.hero.perspective;

import net.sf.anathema.hero.framework.perspective.CharacterButtonDto;
import net.sf.anathema.hero.framework.perspective.CharacterGridView;
import net.sf.anathema.hero.framework.perspective.Selector;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.InteractionView;
import net.sf.anathema.platform.fx.MenuTool;
import net.sf.anathema.platform.fx.Navigation;

public class FxCharacterNavigation extends Navigation implements InteractionView, CharacterGridView {
  private CharacterGridFxView gridView;

  public FxCharacterNavigation() {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        gridView = new CharacterGridFxView();
        addContainerToNavigation(gridView.getNode());
      }
    });
  }


  @Override
  public void addButton(final CharacterButtonDto dto, final Selector<CharacterIdentifier> characterSelector) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        gridView.addButton(dto, characterSelector);
      }
    });
  }

  @Override
  public void selectButton(final CharacterIdentifier identifier) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        gridView.selectButton(identifier);
      }
    });
  }

  @Override
  public void updateButton(final CharacterButtonDto dto) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        gridView.updateButton(dto);
      }
    });
  }

  @Override
  public MenuTool addMenuTool() {
    final FxMenuButtonTool tool = FxMenuButtonTool.ForToolbar();
    addTool(tool);
    return tool;
  }
}
