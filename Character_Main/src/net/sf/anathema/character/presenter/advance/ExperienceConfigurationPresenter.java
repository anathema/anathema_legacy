package net.sf.anathema.character.presenter.advance;

import net.sf.anathema.character.impl.view.ExperienceTableView;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.character.view.advance.ExperienceView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationViewListener;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;

public class ExperienceConfigurationPresenter {

  private final IExperiencePointConfiguration experiencePoints;
  private final ExperienceView experienceView;
  private DefaultTableModel tableModel;
  private final Map<Integer, IExperiencePointEntry> entriesByIndex = new HashMap<>();
  private final Map<IExperiencePointEntry, Integer> indexByEntry = new HashMap<>();
  private final Resources resources;

  public ExperienceConfigurationPresenter(Resources resources, IExperiencePointConfiguration experiencePoints,
                                          ExperienceView experienceView) {
    this.resources = resources;
    this.experiencePoints = experiencePoints;
    this.experienceView = experienceView;
  }

  public void initPresentation() {
    experienceView.addExperienceConfigurationViewListener(new IExperienceConfigurationViewListener() {
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
    experiencePoints.addExperiencePointConfigurationListener(new IExperiencePointConfigurationListener() {
      @Override
      public void entryRemoved(IExperiencePointEntry entry) {
        removeFromView(entry);
      }

      @Override
      public void entryAdded(IExperiencePointEntry entry) {
        addToView(entry);
      }

      @Override
      public void entryChanged(IExperiencePointEntry entry) {
        updateView(entry);
      }
    });
    experienceView.initGui(new ExperienceViewProperties(resources));
    this.tableModel = experienceView.getTableModel();
    showEntriesInView();
    tableModel.addTableModelListener(new TableModelListener() {
      @Override
      public void tableChanged(TableModelEvent e) {
        if (e.getType() != TableModelEvent.UPDATE) {
          return;
        }
        int tableRowIndex = e.getFirstRow();
        IExperiencePointEntry entry = entriesByIndex.get(tableRowIndex);
        entry.setExperiencePoints((Integer) tableModel.getValueAt(tableRowIndex, ExperienceTableView.VALUE_INDEX));
        entry.getTextualDescription().setText((String) tableModel.getValueAt(tableRowIndex, ExperienceTableView.DESCRIPTION_INDEX));
      }
    });
    updateTotal();
  }

  private void showEntriesInView() {
    for (IExperiencePointEntry entry : experiencePoints.getAllEntries()) {
      addToView(entry);
    }
  }

  private void addToView(IExperiencePointEntry entry) {
    entriesByIndex.put(tableModel.getRowCount(), entry);
    indexByEntry.put(entry, tableModel.getRowCount());
    Object[] values = new Object[2];
    values[ExperienceTableView.VALUE_INDEX] = entry.getExperiencePoints();
    values[ExperienceTableView.DESCRIPTION_INDEX] = entry.getTextualDescription().getText();
    tableModel.addRow(values);
  }

  private void removeFromView(IExperiencePointEntry entry) {
    int rowIndex = indexByEntry.get(entry);
    tableModel.removeRow(rowIndex);
    updateTotal();
  }

  protected void updateView(IExperiencePointEntry entry) {
    int rowIndex = indexByEntry.get(entry);
    tableModel.setValueAt(entry.getExperiencePoints(), rowIndex, ExperienceTableView.VALUE_INDEX);
    tableModel.setValueAt(entry.getTextualDescription().getText(), rowIndex, ExperienceTableView.DESCRIPTION_INDEX);
    updateTotal();
  }

  private void updateTotal() {
    experienceView.setTotalValueLabel(experiencePoints.getTotalExperiencePoints());
  }
}
