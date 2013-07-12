package net.sf.anathema.hero.experience.display;

import net.sf.anathema.hero.advance.experience.ExperiencePointConfiguration;
import net.sf.anathema.hero.advance.experience.ExperiencePointConfigurationListener;
import net.sf.anathema.hero.advance.experience.ExperiencePointEntry;
import net.sf.anathema.hero.advance.experience.ExperienceSelectionListener;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.resources.Resources;

public class ExperienceConfigurationPresenter {

  private final ExperiencePointConfiguration experiencePoints;
  private final ExperienceView experienceView;
  private final Resources resources;

  public ExperienceConfigurationPresenter(Resources resources, ExperiencePointConfiguration experiencePoints,
                                          ExperienceView experienceView) {
    this.resources = resources;
    this.experiencePoints = experiencePoints;
    this.experienceView = experienceView;
  }

  public void initPresentation() {
    experienceView.addSelectionListener(new ExperienceSelectionListener() {
      @Override
      public void selectionChanged(ExperiencePointEntry entry) {
        experiencePoints.selectForChange(entry);
      }
    });
    configureAddTool();
    configureRemoveTool();
    experiencePoints.addExperiencePointConfigurationListener(new ExperiencePointConfigurationListener() {
      @Override
      public void entriesAddedRemovedOrChanged() {
        refreshEntriesInView();
      }
    });
    experiencePoints.addEntrySelectionListener(new ExperienceSelectionListener() {
      @Override
      public void selectionChanged(ExperiencePointEntry entry) {
        updateSelectionInView(entry);
      }
    });
    experienceView.initGui(new DefaultExperienceProperties(resources));
    refreshEntriesInView();
    experienceView.addUpdateListener(new ExperienceUpdateListener() {
      public void update(int points, String description) {
        experiencePoints.updateCurrentSelection(description, points);
      }
    });
    updateTotal();
  }

  private void configureAddTool() {
    Tool tool = experienceView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        experiencePoints.addEntry();
      }
    });
  }


  private void configureRemoveTool() {
    final Tool tool = experienceView.addTool();
    tool.setIcon(new BasicUi().getRemoveIconPath());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        experiencePoints.removeEntry();
      }
    });
    experiencePoints.addEntrySelectionListener(new ExperienceSelectionListener() {
      @Override
      public void selectionChanged(ExperiencePointEntry entry) {
        if (entry != null) {
          tool.enable();
        } else {
          tool.disable();
        }
      }
    });
  }

  private void updateSelectionInView(ExperiencePointEntry entry) {
    experienceView.setSelection(entry);
  }

  private void refreshEntriesInView() {
    experienceView.setEntries(experiencePoints.getAllEntries());
    updateSelectionInView(experiencePoints.getCurrentSelection());
    updateTotal();
  }

  private void updateTotal() {
    experienceView.setTotalValueLabel(experiencePoints.getTotalExperiencePoints());
  }
}