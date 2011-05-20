// Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.util.ButtonPanelBuilder;
import net.disy.commons.swing.layout.util.LayoutDirection;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.table.actions.ITableActionFactory;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

// NOT_PUBLISHED
public class SmartTable implements IView {

	private boolean enabled = true;
	private final JTable table;
	private JPanel content;
	private final List<ActionListener> selectionActionListeners = new ArrayList<ActionListener>();
	private final List<ITableActionFactory> actionFactories = new ArrayList<ITableActionFactory>();
	private boolean toolbarStyleButtons = true;

	private Action[] actions = new Action[0];

	public SmartTable(TableModel tableModel, ITableColumnViewSettings[] settings) {
		Ensure.ensureNotNull(tableModel);
		Ensure.ensureNotNull(settings);

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
		Ensure.ensureNotNull(actionFactory);
		if (content != null) {
			throw new IllegalStateException(
					"Adding actions after creating content."); //$NON-NLS-1$
		}
		actionFactories.add(actionFactory);
	}

	public void setSelectionMode(ListSelectionMode selectionMode) {
		table.setSelectionMode(selectionMode.getListSelectionMode());
	}

	public JTable getTable() {
		return table;
	}

	public TableModel getModel() {
		return table.getModel();
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		updateEnabled();
	}

	public boolean isEnabled() {
		return enabled;
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
			private static final long serialVersionUID = -8660768514467856637L;
			@Override
			public void setEnabled(boolean enabled) {
				super.setEnabled(enabled);
				SmartTable.this.setEnabled(enabled);
			}
		};
		scrollPane.setColumnHeaderView(table.getTableHeader());
		int preferredWidth = table.getPreferredSize().width
				+ scrollPane.getInsets().left + scrollPane.getInsets().right;
		scrollPane.setPreferredSize(new Dimension(preferredWidth, 150));
		this.actions = createTableActions();
		JPanel panel = new JPanel(new GridDialogLayout(2, false)) {
			private static final long serialVersionUID = 5547203636645981124L;

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
			panel.add(createButtonPanel(actions),
					GridDialogLayoutDataUtilities.createTopData());
		}
		return panel;
	}

	private Action[] createTableActions() {
		Action[] createdActions = new Action[actionFactories.size()];
		for (int i = 0; i < createdActions.length; i++) {
			ITableActionFactory factory = actionFactories.get(i);
			createdActions[i] = new DisableableProxyAction(
					factory.createAction(this));
		}
		return createdActions;
	}

	private JPanel createButtonPanel(Action[] additionalActions) {
		return isToolbarStyleButtons() ? createToolbarStyleButtons(additionalActions)
				: createNonToolbarStyleButtons(additionalActions);
	}

	private JPanel createNonToolbarStyleButtons(Action[] additionalActions) {
		ButtonPanelBuilder builder = new ButtonPanelBuilder(
				LayoutDirection.VERTICAL);
		for (Action action : additionalActions) {
			builder.add(action);
		}
		JPanel panel = builder.createPanel();
		panel.setBorder(null);
		return panel;
	}

	private JPanel createToolbarStyleButtons(Action[] additionalActions) {
		JPanel buttonPanel = new JPanel(new GridDialogLayout(1, false));
		for (Action action : additionalActions) {
			buttonPanel.add(new JButton(action),
					GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
		}
		return buttonPanel;
	}

	public void requestFocus() {
		table.requestFocus();
	}

	public synchronized void addSelectionActionListener(ActionListener listener) {
		selectionActionListeners.add(listener);
	}

	public synchronized void removeSelectionActionListener(
			ActionListener listener) {
		selectionActionListeners.remove(listener);
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

	public final void scrollToAndSelect(int rowIndex) {
		getTable().scrollRectToVisible(
				getTable().getCellRect(rowIndex, 0, true));
		getTable().getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
		getTable().requestFocus();
	}

	public void stopCellEditing() {
		TableCellEditor cellEditor = getTable().getCellEditor();
		if (cellEditor != null) {
			cellEditor.stopCellEditing();
		}
	}

	public boolean isToolbarStyleButtons() {
		return toolbarStyleButtons;
	}

	public void setToobarStyleButtons(boolean toobarStyleButtons) {
		this.toolbarStyleButtons = toobarStyleButtons;
	}

	public int getSelectedRowIndex() {
		// Bugfix for JDK1.4 JTable Bug 4905083: Pressing enter on empty table
		// moves selection to 1
		// (fixed in Java 1.5 / Tiger)
		int selectedRowIndex = table.getSelectedRow();
		return selectedRowIndex > table.getRowCount() ? -1 : selectedRowIndex;
	}

	public boolean isSelectionEmpty() {
		return getSelectedRowIndex() == -1;
	}
}