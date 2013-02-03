package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.model.CharacterSystemModel;
import net.sf.anathema.lib.control.IChangeListener;

public class InteractionPresenter {

  private CharacterSystemModel model;
  private InteractionView view;

  public InteractionPresenter(CharacterSystemModel model, InteractionView view) {
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    model.whenCurrentSelectionBecomesDirty(new EnableSaveCurrent());
    model.whenCurrentSelectionBecomesClean(new DisableSaveCurrent());
  }

  private class DisableSaveCurrent implements IChangeListener {
    @Override
    public void changeOccurred() {
      view.disableSaveCurrent();
    }
  }

  private class EnableSaveCurrent implements IChangeListener {
    @Override
    public void changeOccurred() {
      view.enableSaveCurrent();
    }
  }
}
