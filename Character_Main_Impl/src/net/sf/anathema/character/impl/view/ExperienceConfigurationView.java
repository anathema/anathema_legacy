package net.sf.anathema.character.impl.view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationViewListener;
import net.sf.anathema.character.view.advance.IExperienceConfigurationViewProperties;
import net.sf.anathema.framework.presenter.view.AbstractInitializableContentView;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.table.SmartTable;
import net.sf.anathema.lib.gui.table.actions.ITableActionFactory;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledIntegerValueView;

public class ExperienceConfigurationView extends
    AbstractInitializableContentView<IExperienceConfigurationViewProperties> implements IExperienceConfigurationView {

  private final List<IExperienceConfigurationViewListener> listeners = new ArrayList<IExperienceConfigurationViewListener>();
  private SmartTable smartTable;
  private Action deleteAction;
  private LabelledIntegerValueView labelledIntValueView;

  @Override
  protected void createContent(JPanel panel, final IExperienceConfigurationViewProperties properties) {
    smartTable = new SmartTable(properties.getTableModel(), properties.getColumnSettings());
    smartTable.addActionFactory(new ITableActionFactory() {
      public Action createAction(SmartTable table) {
        return new SmartAction(properties.getAddIcon()) {
          @Override
          protected void execute(Component parentComponent) {
            fireAddRequested();
          }
        };
      }
    });
    deleteAction = new SmartAction(properties.getDeleteIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        smartTable.stopCellEditing();
        fireRemoveRequested();
      }
    };
    smartTable.addActionFactory(new ITableActionFactory() {
      public Action createAction(SmartTable table) {
        return deleteAction;
      }
    });

    smartTable.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        fireSelectionChanged();
      }
    });
    JPanel smartTablePanel = smartTable.getComponent();
    smartTablePanel.setPreferredSize(new Dimension(700, 400));
    final TableColumn descriptionColumn = smartTable.getTable().getTableHeader().getColumnModel().getColumn(0);
    descriptionColumn.setPreferredWidth(500);
    descriptionColumn.setWidth(descriptionColumn.getPreferredWidth());
    setRemoveButtonEnabled(false);
    IGridDialogPanel totalPanel = new DefaultGridDialogPanel();
    labelledIntValueView = new LabelledIntegerValueView(properties.getTotalString(), 0, false, 7);
    labelledIntValueView.addComponents(totalPanel);
    labelledIntValueView.getValueLabel().setHorizontalAlignment(SwingConstants.RIGHT);
    // todo vom (02.07.2005) (sieroux): Hier muss eine besser Lösung her im Zusammenspiel mit der SmartTable
    smartTablePanel.add(totalPanel.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(smartTablePanel);
  }

  protected void fireSelectionChanged() {
    for (IExperienceConfigurationViewListener listener : cloneListeners()) {
      listener.selectionChanged(smartTable.getSelectedRowIndex());
    }
  }

  private void fireRemoveRequested() {
    for (IExperienceConfigurationViewListener listener : cloneListeners()) {
      listener.removeRequested(smartTable.getSelectedRowIndex());
    }
  }

  private void fireAddRequested() {
    for (IExperienceConfigurationViewListener listener : cloneListeners()) {
      listener.addRequested();
    }
  }

  private synchronized List<IExperienceConfigurationViewListener> cloneListeners() {
    return new ArrayList<IExperienceConfigurationViewListener>(listeners);
  }

  public synchronized void addExperienceConfigurationViewListener(IExperienceConfigurationViewListener listener) {
    listeners.add(listener);
  }

  public void setRemoveButtonEnabled(boolean enabled) {
    deleteAction.setEnabled(enabled);
  }

  public void setTotalValueLabel(int overallExperiencePoints) {
    labelledIntValueView.setValue(overallExperiencePoints);
  }
}