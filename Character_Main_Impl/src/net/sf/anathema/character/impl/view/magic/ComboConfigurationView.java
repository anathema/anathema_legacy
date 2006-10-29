package net.sf.anathema.character.impl.view.magic;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.view.magic.IComboConfigurationView;
import net.sf.anathema.character.view.magic.IComboView;
import net.sf.anathema.character.view.magic.IComboViewListener;
import net.sf.anathema.character.view.magic.IComboViewProperties;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import com.l2fprod.common.swing.JTaskPane;

public class ComboConfigurationView implements IComboConfigurationView {

  private static final int TEXT_COLUMNS = 20;
  private final MagicLearnView magicLearnView = new MagicLearnView();
  private JComponent content;
  private final List<IComboViewListener> comboViewListeners = new ArrayList<IComboViewListener>();
  private final JPanel namePanel = new JPanel(new GridDialogLayout(1, false));
  private JButton clearButton;
  private JButton finalizeButton;
  private int learnedListModelSize;
  private boolean isNameEntered;
  private boolean isDescriptionEntered;
  private final JTaskPane comboPane = new JTaskPane() {
    @Override
    public Dimension getPreferredScrollableViewportSize() {
      return new Dimension(0, 0);
    }
  };
  private JScrollPane comboScrollPane;
  private IComboViewProperties properties;

  public void initGui(final IComboViewProperties viewProperties) {
    this.properties = viewProperties;
    magicLearnView.init(viewProperties);
    finalizeButton = createFinalizeComboButton(viewProperties.getFinalizeButtonIcon());
    finalizeButton.setToolTipText(viewProperties.getFinalizeButtonToolTip());
    clearButton = createClearButton(viewProperties.getClearButtonIcon());
    clearButton.setToolTipText(viewProperties.getClearButtonToolTip());
    final ListModel learnedListModel = magicLearnView.getLearnedListModel();
    learnedListModel.addListDataListener(new ListDataListener() {
      public void intervalAdded(ListDataEvent e) {
        learnedListModelSize = learnedListModel.getSize();
        finalizeButton.setEnabled(learnedListModelSize > 1);
        clearButton.setEnabled(isDescriptionEntered || isNameEntered || learnedListModelSize > 0);
      }

      public void intervalRemoved(ListDataEvent e) {
        learnedListModelSize = learnedListModel.getSize();
        finalizeButton.setEnabled(learnedListModelSize > 1);
        clearButton.setEnabled(isDescriptionEntered || isNameEntered || learnedListModelSize > 0);
      }

      public void contentsChanged(ListDataEvent e) {
        learnedListModelSize = learnedListModel.getSize();
        finalizeButton.setEnabled(learnedListModelSize > 1);
        clearButton.setEnabled(isDescriptionEntered || isNameEntered || learnedListModelSize > 0);
      }
    });
    JPanel viewPort = new JPanel(new GridDialogLayout(5, false));
    viewPort.setBorder(new EmptyBorder(6, 6, 6, 6));

    viewPort.add(new JLabel(viewProperties.getAvailableComboCharmsLabel()));
    viewPort.add(new JLabel());
    viewPort.add(new JLabel(viewProperties.getComboedCharmsLabel()));
    viewPort.add(new JLabel());

    GridDialogLayoutData nameData = new GridDialogLayoutData();
    nameData.setVerticalAlignment(GridAlignment.BEGINNING);
    nameData.setVerticalSpan(2);
    viewPort.add(namePanel, nameData);
    magicLearnView.addTo(viewPort);
    GridDialogLayoutData taskPaneData = new GridDialogLayoutData(GridDialogLayoutData.FILL_BOTH);
    taskPaneData.setHorizontalSpan(5);
    comboPane.setBackground(viewPort.getBackground());
    comboScrollPane = new JScrollPane(comboPane);
    viewPort.add(comboScrollPane, taskPaneData);
    content = new JScrollPane(viewPort);
  }

  private JButton createClearButton(Icon icon) {
    Action smartAction = new SmartAction(icon) {
      @Override
      protected void execute(Component parentComponent) {
        fireComboCleared();
      }
    };
    smartAction.setEnabled(false);
    return magicLearnView.addAdditionalAction(smartAction);
  }

  private void fireComboCleared() {
    for (IComboViewListener listener : new ArrayList<IComboViewListener>(comboViewListeners)) {
      listener.comboCleared();
    }
  }

  private JButton createFinalizeComboButton(Icon icon) {
    Action smartAction = new SmartAction(icon) {
      @Override
      protected void execute(Component parentComponent) {
        fireComboFinalized();
      }
    };
    smartAction.setEnabled(false);
    return magicLearnView.addAdditionalAction(smartAction);
  }

  private synchronized void fireComboFinalized() {
    for (IComboViewListener listener : new ArrayList<IComboViewListener>(comboViewListeners)) {
      listener.comboFinalized();
    }
  }

  public void setAllCharms(Object[] charms) {
    magicLearnView.setMagicOptions(charms);
  }

  public JComponent getComponent() {
    return content;
  }

  public void addComboViewListener(final IComboViewListener listener) {
    magicLearnView.addMagicViewListener(new IMagicViewListener() {
      public void magicRemoved(Object[] removedMagic) {
        listener.charmRemoved(removedMagic);
      }

      public void magicAdded(Object[] addedMagic) {
        Ensure.ensureTrue("Only one charm may be added.", addedMagic.length == 1); //$NON-NLS-1$
        listener.charmAdded(addedMagic[0]);
      }
    });
    comboViewListeners.add(listener);
  }

  public void setComboCharms(Object[] charms) {
    magicLearnView.setLearnedMagic(charms);
  }

  public ITextView addComboNameView(String viewTitle) {
    ITextView textView = new LineTextView(TEXT_COLUMNS);
    textView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        isNameEntered = isDescriptionEntered = newValue != null && !newValue.equals(""); //$NON-NLS-1$
        clearButton.setEnabled(isDescriptionEntered || isNameEntered || learnedListModelSize > 0);
      }
    });
    return addTextView(viewTitle, textView);
  }

  public IComboView addComboView(String name, String description, Action deleteAction, Action editAction) {
    ComboView comboView = new ComboView(deleteAction, editAction);
    comboView.initGui(name, description);
    comboPane.add(comboView.getTaskGroup());
    revalidateView();
    return comboView;
  }

  private void revalidateView() {
    GuiUtilities.revalidate(comboPane);
    GuiUtilities.revalidate(comboScrollPane);
  }

  private ITextView addTextView(String viewTitle, ITextView textView) {
    namePanel.add(new JLabel(viewTitle));
    namePanel.add(textView.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    return textView;
  }

  public ITextView addComboDescriptionView(String viewTitle) {
    ITextView textView = new AreaTextView(5, TEXT_COLUMNS);
    textView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        isDescriptionEntered = newValue != null && !newValue.equals(""); //$NON-NLS-1$
        clearButton.setEnabled(isDescriptionEntered || isNameEntered || learnedListModelSize > 0);
      }
    });
    return addTextView(viewTitle, textView);
  }

  public void deleteView(IComboView view) {
    ComboView comboView = (ComboView) view;
    comboPane.remove(comboView.getTaskGroup());
    revalidateView();
  }

  public void setEditState(boolean editing) {
    clearButton.setIcon(editing ? properties.getCancelEditButtonIcon() : properties.getClearButtonIcon());
    clearButton.setToolTipText(editing ? properties.getCancelButtonEditToolTip() : properties.getClearButtonToolTip());
    finalizeButton.setToolTipText(editing
        ? properties.getFinalizeButtonEditToolTip()
        : properties.getFinalizeButtonToolTip());
  }
}