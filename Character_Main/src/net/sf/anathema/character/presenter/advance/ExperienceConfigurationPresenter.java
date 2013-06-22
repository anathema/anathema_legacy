package net.sf.anathema.character.presenter.advance;

import net.sf.anathema.character.model.advance.ExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.character.view.advance.ExperienceConfigurationViewListener;
import net.sf.anathema.character.view.advance.ExperienceView;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;

public class ExperienceConfigurationPresenter {

  private final IExperiencePointConfiguration experiencePoints;
  private final ExperienceView experienceView;
  private DefaultTableModel tableModel;
  private final Map<Integer, IExperiencePointEntry> entriesByIndex = new HashMap<>();
  private final Resources resources;

  public ExperienceConfigurationPresenter(Resources resources, IExperiencePointConfiguration experiencePoints,
                                          ExperienceView experienceView) {
    this.resources = resources;
    this.experiencePoints = experiencePoints;
    this.experienceView = experienceView;
  }

  public void initPresentation() {
    experienceView.addExperienceConfigurationViewListener(new ExperienceConfigurationViewListener() {
      @Override
      public void removeRequested(int index) {
        experiencePoints.removeEntry(entriesByIndex.get(index));
      }

      @Override
      public void addRequested() {
        experiencePoints.addEntry();
      }

      @Override
      public void selectionChanged(int index) {
        experienceView.setRemoveButtonEnabled(index != -1);
      }

    });
    experiencePoints.addExperiencePointConfigurationListener(new ExperiencePointConfigurationListener() {
      @Override
      public void entryRemoved(IExperiencePointEntry entry) {
        refreshEntriesInView();
      }

      @Override
      public void entryAdded(IExperiencePointEntry entry) {
        refreshEntriesInView();
      }

      @Override
      public void entryChanged(IExperiencePointEntry entry) {
        refreshEntriesInView();
      }
    });
    experienceView.initGui(new ExperienceViewProperties(resources));
    this.tableModel = experienceView.getTableModel();
    refreshEntriesInView();
    experienceView.addUpdateListener(new ExperienceUpdateListener() {
      public void update(int index, int points, String description) {
        IExperiencePointEntry entry = entriesByIndex.get(index);
        entry.setExperiencePoints(points);
        entry.getTextualDescription().setText(description);
      }
    });
    updateTotal();
  }

  private void refreshEntriesInView() {
    experienceView.clearEntries();
    for (IExperiencePointEntry entry : experiencePoints.getAllEntries()) {
      addToView(entry);
    }
    updateTotal();
  }

  private void addToView(IExperiencePointEntry entry) {
    entriesByIndex.put(tableModel.getRowCount(), entry);
    experienceView.addEntry(entry.getExperiencePoints(), entry.getTextualDescription().getText());
  }

  private void updateTotal() {
    experienceView.setTotalValueLabel(experiencePoints.getTotalExperiencePoints());
  }
}