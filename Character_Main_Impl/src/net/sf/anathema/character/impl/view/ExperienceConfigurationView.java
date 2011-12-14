package net.sf.anathema.character.impl.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationViewListener;
import net.sf.anathema.character.view.advance.IExperienceConfigurationViewProperties;
import net.sf.anathema.framework.presenter.view.AbstractInitializableContentView;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.gui.table.SmartTable;
import net.sf.anathema.lib.gui.table.actions.ITableActionFactory;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledIntegerValueView;

public class ExperienceConfigurationView extends
    AbstractInitializableContentView<IExperienceConfigurationViewProperties> implements IExperienceConfigurationView {

  private final GenericControl<IExperienceConfigurationViewListener> listeners = new GenericControl<IExperienceConfigurationViewListener>();
  private SmartTable smartTable;
  private Action deleteAction;
  private LabelledIntegerValueView labelledIntValueView;

  @Override
  protected void createContent(JPanel panel, final IExperienceConfigurationViewProperties properties) {
    smartTable = new SmartTable(properties.getTableModel(), properties.getColumnSettings());
    smartTable.addActionFactory(new ITableActionFactory() {
      public Action createAction(SmartTable table) {
        return new SmartAction(properties.getAddIcon()) {
          private static final long serialVersionUID = -6922420757124813943L;

          @Override
          protected void execute(Component parentComponent) {
            fireAddRequested();
          }
        };
      }
    });
    deleteAction = new SmartAction(properties.getDeleteIcon()) {
      private static final long serialVersionUID = -9129787703604142621L;

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
    JPanel totalPanel = new JPanel(new GridDialogLayout(2, false));
    labelledIntValueView = new LabelledIntegerValueView(properties.getTotalString(), 0, false, 7);
    labelledIntValueView.addComponents(totalPanel);
    labelledIntValueView.getValueLabel().setHorizontalAlignment(SwingConstants.RIGHT);
    // todo vom (02.07.2005) (sieroux): Needs a better solution to work with SmartTable
    smartTablePanel.add(totalPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(smartTablePanel);
  }

  protected void fireSelectionChanged() {
    listeners.forAllDo(new IClosure<IExperienceConfigurationViewListener>() {
      public void execute(IExperienceConfigurationViewListener input) {
        input.selectionChanged(smartTable.getSelectedRowIndex());
      }
    });
  }

  private void fireRemoveRequested() {
    listeners.forAllDo(new IClosure<IExperienceConfigurationViewListener>() {
      public void execute(IExperienceConfigurationViewListener input) {
        input.removeRequested(smartTable.getSelectedRowIndex());
      }
    });
  }

  private void fireAddRequested() {
    listeners.forAllDo(new IClosure<IExperienceConfigurationViewListener>() {
      public void execute(IExperienceConfigurationViewListener input) {
        input.addRequested();
      }
    });
  }

  public void addExperienceConfigurationViewListener(IExperienceConfigurationViewListener listener) {
    listeners.addListener(listener);
  }

  public void setRemoveButtonEnabled(boolean enabled) {
    deleteAction.setEnabled(enabled);
  }

  public void setTotalValueLabel(int overallExperiencePoints) {
    labelledIntValueView.setValue(overallExperiencePoints);
  }
}