package net.sf.anathema.character.presenter.advance;

import java.util.HashMap;
import java.util.Map;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationViewListener;
import net.sf.anathema.lib.resources.IResources;

public class ExperienceConfigurationPresenter {

  private static final int VALUE_INDEX = 1;
  private static final int DESCRIPTION_INDEX = 0;
  private final IExperiencePointConfiguration experiencePoints;
  private final IExperienceConfigurationView experienceView;
  private DefaultTableModel tableModel;
  private final Map<Integer, IExperiencePointEntry> entriesByIndex = new HashMap<Integer, IExperiencePointEntry>();
  private final Map<IExperiencePointEntry, Integer> indexByEntry = new HashMap<IExperiencePointEntry, Integer>();
  private final IResources resources;

  public ExperienceConfigurationPresenter(
      IResources resources,
      IExperiencePointConfiguration experiencePoints,
      IExperienceConfigurationView experienceView) {
    this.resources = resources;
    this.experiencePoints = experiencePoints;
    this.experienceView = experienceView;
  }

  public void initPresentation() {
    initTableModel();
    experienceView.addExperienceConfigurationViewListener(new IExperienceConfigurationViewListener() {
      public void removeRequested(int index) {
        experiencePoints.removeEntry(entriesByIndex.get(index));
      }

      public void addRequested() {
        experiencePoints.addEntry();
      }

      public void selectionChanged(int index) {
        experienceView.setRemoveButtonEnabled(index != -1);
      }

    });
    experiencePoints.addExperiencePointConfigurationListener(new IExperiencePointConfigurationListener() {
      public void entryRemoved(IExperiencePointEntry entry) {
        removeFromView(entry);        
      }

      public void entryAdded(IExperiencePointEntry entry) {
        addToView(entry);
      }

      public void entryChanged(IExperiencePointEntry entry) {
        updateView(entry);
      }
    });
    experienceView.initGui(new ExperienceConfigurationViewProperties(resources, tableModel));
    tableModel.addTableModelListener(new TableModelListener() {
      public void tableChanged(TableModelEvent e) {
        if (e.getType() != TableModelEvent.UPDATE) {
          return;
        }
        int tableRowIndex = e.getFirstRow();
        IExperiencePointEntry entry = entriesByIndex.get(tableRowIndex);
        entry.setExperiencePoints(((Integer) tableModel.getValueAt(tableRowIndex, VALUE_INDEX)).intValue());
        entry.getTextualDescription().setText((String) tableModel.getValueAt(tableRowIndex, DESCRIPTION_INDEX));
      }
    });
    updateTotal();
  }

  private void initTableModel() {
    String[] headers = new String[2];
    headers[DESCRIPTION_INDEX] = resources.getString("CardView.Experience.Description"); //$NON-NLS-1$
    headers[VALUE_INDEX] = resources.getString("CardView.Experience.ExperiencePoints"); //$NON-NLS-1$
    this.tableModel = new DefaultTableModel(headers, 0);
    for (IExperiencePointEntry entry : experiencePoints.getAllEntries()) {
      addToView(entry);
    }
  }

  private void addToView(IExperiencePointEntry entry) {
    entriesByIndex.put(tableModel.getRowCount(), entry);
    indexByEntry.put(entry, tableModel.getRowCount());
    Object[] values = new Object[2];
    values[VALUE_INDEX] = entry.getExperiencePoints();
    values[DESCRIPTION_INDEX] = entry.getTextualDescription().getText();
    tableModel.addRow(values);
  }

  private void removeFromView(IExperiencePointEntry entry) {
    int rowIndex = indexByEntry.get(entry);
    tableModel.removeRow(rowIndex);
    updateTotal();
  }

  protected void updateView(IExperiencePointEntry entry) {
    int rowIndex = indexByEntry.get(entry);
    tableModel.setValueAt(entry.getExperiencePoints(), rowIndex, VALUE_INDEX);
    tableModel.setValueAt(entry.getTextualDescription().getText(), rowIndex, DESCRIPTION_INDEX);
    updateTotal();
  }

  private void updateTotal() {
    experienceView.setTotalValueLabel(experiencePoints.getTotalExperiencePoints());
  }
}