package net.sf.anathema.charmdatabase.tools;

import net.sf.anathema.charmdatabase.management.ICharmDatabaseManagement;
import net.sf.anathema.charmdatabase.presenter.DirtyCharmCondition;
import net.sf.anathema.charmdatabase.presenter.DiscardChangesVetor;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.interaction.Command;

public class NewCharmItem implements Command {
  private final ICharmDatabaseManagement model;
  private final Resources resources;

  public NewCharmItem(ICharmDatabaseManagement model, Resources resources) {
    this.model = model;
    this.resources = resources;
  }

  @Override
  public void execute() {
    DiscardChangesVetor vetor = new DiscardChangesVetor(resources, new DirtyCharmCondition(model));
    vetor.requestPermissionFor(new Command() {
      @Override
      public void execute() {
        model.getCharmEditModel().setNewTemplate();
      }
    });
  }
}