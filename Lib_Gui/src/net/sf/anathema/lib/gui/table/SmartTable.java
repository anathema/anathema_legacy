package net.sf.anathema.lib.gui.table;

import com.google.common.base.Preconditions;
import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.table.actions.ITableActionFactory;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SmartTable implements IView {

  private boolean enabled = true;
  private final JTable table;
  private JPanel content;
  private final List<ActionListener> selectionActionListeners = new ArrayList<ActionListener>();
  private final List<ITableActionFactory> actionFactories = new ArrayList<ITableActionFactory>();

  private Action[] actions = new Action[0];

  public SmartTable(TableModel tableModel, ITableColumnViewSettings[] settings) {
    Preconditions.checkNotNull(tableModel);
    Preconditions.checkNotNull(settings);

    table = new JTable(tableModel);

    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() != 2 || e.isMetaDown()) {
          return;
        }
        if (table.getSelectedRowCount() == 0) {
          return;
        }
        fireSelectionActionEvent();
      }
    });

    table.setRowHeight(Math.max(table.getRowHeight(), 21));
    TableColumnConfigurator.configureTableColumns(table, settings);
    setSelectionMode(ListSelectionMode.SINGLE_SELECTION);
  }

  public void addActionFactory(ITableActionFactory actionFactory) {
    Preconditions.checkNotNull(actionFactory);
    if (content != null) {
      throw new IllegalStateException("Adding actions after creating content."); //$NON-NLS-1$
    }
    actionFactories.add(actionFactory);
  }

  public void setSelectionMode(ListSelectionMode selectionMode) {
    table.setSelectionMode(selectionMode.getListSelectionMode());
  }

  public JTable getTable() {
    return table;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
    updateEnabled();
  }

  protected void updateEnabled() {
    table.setEnabled(enabled);
    if (!enabled) {
      table.getSelectionModel().clearSelection();
    }
    for (Action action : actions) {
      action.setEnabled(enabled);
    }
  }

  @Override
  public final JPanel getComponent() {
    if (content == null) {
      content = createContent();
      updateEnabled();
    }
    return content;
  }

  private JPanel createContent() {
    JPanel tablePanel = new JPanel(new BorderLayout());
    tablePanel.add(table);
    JScrollPane scrollPane = new JScrollPane(tablePanel) {

      @Override
      public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        SmartTable.this.setEnabled(enabled);
      }
    };
    scrollPane.setColumnHeaderView(table.getTableHeader());
    int preferredWidth = table.getPreferredSize().width + scrollPane.getInsets().left + scrollPane.getInsets().right;
    scrollPane.setPreferredSize(new Dimension(preferredWidth, 150));
    this.actions = createTableActions();
    JPanel panel = new JPanel(new GridDialogLayout(2, false)) {

      @Override
      public void setEnabled(boolean enabled) {
        SmartTable.this.setEnabled(enabled);
        if (!enabled) {
          TableCellEditor cellEditor = getTable().getCellEditor();
          if (cellEditor != null) {
            cellEditor.stopCellEditing();
          }
        }
      }
    };
    panel.add(scrollPane, GridDialogLayoutData.FILL_BOTH);
    if (actions.length <= 0) {
      panel.add(new EndOfLineMarkerComponent());
    } else {
      panel.add(createButtonPanel(actions), GridDialogLayoutDataFactory.createTopData());
    }
    return panel;
  }

  private Action[] createTableActions() {
    Action[] createdActions = new Action[actionFactories.size()];
    for (int i = 0; i < createdActions.length; i++) {
      ITableActionFactory factory = actionFactories.get(i);
      createdActions[i] = new DisableableProxyAction(factory.createAction(this));
    }
    return createdActions;
  }

  private JPanel createButtonPanel(Action[] additionalActions) {
    JPanel buttonPanel = new JPanel(new GridDialogLayout(1, false));
    for (Action action : additionalActions) {
      buttonPanel.add(new JButton(action), GridDialogLayoutDataFactory.createHorizontalFillNoGrab());
    }
    return buttonPanel;
  }

  private void fireSelectionActionEvent() {
    List<ActionListener> clone;
    synchronized (this) {
      clone = new ArrayList<ActionListener>(selectionActionListeners);
    }
    ActionEvent actionEvent = new ActionEvent(table, -1, "select"); //$NON-NLS-1$
    for (ActionListener element : clone) {
      element.actionPerformed(actionEvent);
    }
  }

  public void stopCellEditing() {
    TableCellEditor cellEditor = getTable().getCellEditor();
    if (cellEditor != null) {
      cellEditor.stopCellEditing();
    }
  }

  public int getSelectedRowIndex() {
    // Bugfix for JDK1.4 JTable Bug 4905083: Pressing enter on empty table
    // moves selection to 1
    // (fixed in Java 1.5 / Tiger)
    int selectedRowIndex = table.getSelectedRow();
    return selectedRowIndex > table.getRowCount() ? -1 : selectedRowIndex;
  }

}