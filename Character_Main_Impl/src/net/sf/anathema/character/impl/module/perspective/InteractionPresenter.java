package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.model.CharacterSystemModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.swing.character.perspective.InteractionView;
import net.sf.anathema.swing.character.perspective.interaction.Command;

import java.io.IOException;

public class InteractionPresenter {

  private CharacterSystemModel model;
  private InteractionView view;

  public InteractionPresenter(CharacterSystemModel model, InteractionView view) {
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    view.getSaveInteraction().disable();
    view.getSaveInteraction().setTooltip("Save Current");
    view.getSaveInteraction().setIcon("TaskBarSave24.png");
    view.getSaveInteraction().setCommand(new Command() {
      @Override
      public void execute() {
        try {
          model.saveCurrent();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });
    model.whenCurrentSelectionBecomesDirty(new EnableSaveCurrent());
    model.whenCurrentSelectionBecomesClean(new DisableSaveCurrent());
  }

  private class DisableSaveCurrent implements IChangeListener {
    @Override

    public void changeOccurred() {
      view.getSaveInteraction().disable();
    }
  }

  private class EnableSaveCurrent implements IChangeListener {
    @Override
    public void changeOccurred() {
      view.getSaveInteraction().enable();
    }
  }
}
