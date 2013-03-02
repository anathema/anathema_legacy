package net.sf.anathema.character.impl.view;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationViewListener;
import net.sf.anathema.character.view.advance.IExperienceConfigurationViewProperties;
import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.table.SmartTable;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledIntegerValueView;
import org.jmock.example.announcer.Announcer;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import java.awt.Component;
import java.awt.Dimension;

public class ExperienceConfigurationView implements IExperienceConfigurationView, IInitializableContentView<IExperienceConfigurationViewProperties> {
  private final Announcer<IExperienceConfigurationViewListener> listeners = Announcer.to(IExperienceConfigurationViewListener.class);
  private SmartTable smartTable;
  private Action deleteAction;
  private LabelledIntegerValueView labelledIntValueView;
  private JPanel smartTablePanel;

  @Override
  public final void initGui(final IExperienceConfigurationViewProperties properties) {
    smartTable = new SmartTable(properties.getTableModel(), properties.getColumnSettings());
    smartTable.addAction(new SmartAction(properties.getAddIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        fireAddRequested();
      }
    });
    deleteAction = new SmartAction(properties.getDeleteIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        smartTable.stopCellEditing();
        fireRemoveRequested();
      }
    };
    smartTable.addAction(deleteAction);
    smartTable.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        fireSelectionChanged();
      }
    });
    this.smartTablePanel = smartTable.getComponent();
    smartTablePanel.setPreferredSize(new Dimension(700, 400));
    TableColumn descriptionColumn = smartTable.getTable().getTableHeader().getColumnModel().getColumn(0);
    descriptionColumn.setPreferredWidth(450);
    descriptionColumn.setWidth(descriptionColumn.getPreferredWidth());
    setRemoveButtonEnabled(false);
    JPanel totalPanel = new JPanel(new MigLayout(new LC().fillX()));
    labelledIntValueView = new LabelledIntegerValueView(properties.getTotalString(), 0, false, 7);
    labelledIntValueView.addComponents(totalPanel);
    labelledIntValueView.getValueLabel().setHorizontalAlignment(SwingConstants.RIGHT);
    smartTablePanel.add(totalPanel, new CC().growX().newline().alignY("top"));
  }

  @Override
  public final JComponent getComponent() {
    return smartTablePanel;
  }

  protected void fireSelectionChanged() {
    listeners.announce().selectionChanged(smartTable.getSelectedRowIndex());
  }

  private void fireRemoveRequested() {
    listeners.announce().removeRequested(smartTable.getSelectedRowIndex());
  }

  private void fireAddRequested() {
    listeners.announce().addRequested();
  }

  @Override
  public void addExperienceConfigurationViewListener(IExperienceConfigurationViewListener listener) {
    listeners.addListener(listener);
  }

  @Override
  public void setRemoveButtonEnabled(boolean enabled) {
    deleteAction.setEnabled(enabled);
  }

  @Override
  public void setTotalValueLabel(int overallExperiencePoints) {
    labelledIntValueView.setValue(overallExperiencePoints);
  }
}