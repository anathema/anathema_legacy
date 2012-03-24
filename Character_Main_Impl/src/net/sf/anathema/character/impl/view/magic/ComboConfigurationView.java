package net.sf.anathema.character.impl.view.magic;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.view.magic.IComboConfigurationView;
import net.sf.anathema.character.view.magic.IComboView;
import net.sf.anathema.character.view.magic.IComboViewListener;
import net.sf.anathema.character.view.magic.IComboViewProperties;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;

public class ComboConfigurationView implements IComboConfigurationView {

  // TODO Move listener management / button initialization to presenter
  private static final int TEXT_COLUMNS = 20;
  private MagicLearnView magicLearnView = new MagicLearnView();
  private JComponent content;
  private final GenericControl<IComboViewListener> comboViewListeners = new GenericControl<IComboViewListener>();
  private final JPanel namePanel = new JPanel(new GridDialogLayout(1, false));
  private JButton clearButton;
  private JButton finalizeButton;
  private int learnedListModelSize;
  private boolean isNameEntered;
  private boolean isDescriptionEntered;
  private final JXTaskPaneContainer comboPane = new JXTaskPaneContainer();
  private JScrollPane comboScrollPane;
  private IComboViewProperties properties;

  @Override
  public void initGui(final IComboViewProperties viewProperties) {
    this.properties = viewProperties;
    magicLearnView = new MagicLearnView() {
      @Override
      protected ListSelectionListener createLearnedListListener(final JButton button, final JList list) {
        return new ListSelectionListener() {
          @Override
          public void valueChanged(ListSelectionEvent e) {
            button.setEnabled(
                    !list.isSelectionEmpty() && viewProperties.isRemoveButtonEnabled((ICharm) list.getSelectedValue()));
          }
        };
      }
    };
    magicLearnView.init(viewProperties);
    finalizeButton = createFinalizeComboButton(viewProperties.getFinalizeButtonIcon());
    finalizeButton.setToolTipText(viewProperties.getFinalizeButtonToolTip());
    clearButton = createClearButton(viewProperties.getClearButtonIcon());
    clearButton.setToolTipText(viewProperties.getClearButtonToolTip());
    final ListModel learnedListModel = magicLearnView.getLearnedListModel();
    learnedListModel.addListDataListener(new ListDataListener() {
      @Override
      public void intervalAdded(ListDataEvent e) {
        updateClearAndFinalize();
      }

      @Override
      public void intervalRemoved(ListDataEvent e) {
        updateClearAndFinalize();
      }

      @Override
      public void contentsChanged(ListDataEvent e) {
        updateClearAndFinalize();
      }

      private void updateClearAndFinalize() {
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

    GridDialogLayoutData nameData = GridDialogLayoutDataFactory.createTopData();
    nameData.setVerticalSpan(2);
    viewPort.add(namePanel, nameData);
    magicLearnView.addTo(viewPort);
    comboPane.setBackground(viewPort.getBackground());
    comboScrollPane = new JScrollPane(comboPane);
    viewPort.add(comboScrollPane,
            GridDialogLayoutDataFactory.createHorizontalSpanData(5, GridDialogLayoutData.FILL_BOTH));
    content = new JScrollPane(viewPort);
  }

  private JButton createClearButton(Icon icon) {
    Action smartAction = new SmartAction(icon) {
      private static final long serialVersionUID = -2774898496141408790L;

      @Override
      protected void execute(Component parentComponent) {
        fireComboCleared();
      }
    };
    smartAction.setEnabled(false);
    return magicLearnView.addAdditionalAction(smartAction);
  }

  private void fireComboCleared() {
    comboViewListeners.forAllDo(new IClosure<IComboViewListener>() {
      @Override
      public void execute(IComboViewListener input) {
        input.comboCleared();
      }
    });
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

  private void fireComboFinalized() {
    comboViewListeners.forAllDo(new IClosure<IComboViewListener>() {
      @Override
      public void execute(IComboViewListener input) {
        input.comboFinalized();
      }
    });
  }

  @Override
  public void setAllCharms(Object[] charms) {
    magicLearnView.setMagicOptions(charms);
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void addComboViewListener(final IComboViewListener listener) {
    magicLearnView.addMagicViewListener(new IMagicViewListener() {
      @Override
      public void magicRemoved(Object[] removedMagic) {
        listener.charmRemoved(removedMagic);
      }

      @Override
      public void magicAdded(Object[] addedMagic) {
        Ensure.ensureTrue("Only one charm may be added.", addedMagic.length == 1); //$NON-NLS-1$
        listener.charmAdded(addedMagic[0]);
      }
    });
    comboViewListeners.addListener(listener);
  }

  @Override
  public void setComboCharms(Object[] charms) {
    magicLearnView.setLearnedMagic(charms);
  }

  @Override
  public ITextView addComboNameView(String viewTitle) {
    ITextView textView = new LineTextView(TEXT_COLUMNS);
    textView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        isNameEntered = newValue != null && !newValue.equals(""); //$NON-NLS-1$
        clearButton.setEnabled(isDescriptionEntered || isNameEntered || learnedListModelSize > 0);
      }
    });
    return addTextView(viewTitle, textView);
  }

  @Override
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

  @Override
  public ITextView addComboDescriptionView(String viewTitle) {
    ITextView textView = new AreaTextView(5, TEXT_COLUMNS);
    textView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        isDescriptionEntered = newValue != null && !newValue.equals(""); //$NON-NLS-1$
        clearButton.setEnabled(isDescriptionEntered || isNameEntered || learnedListModelSize > 0);
      }
    });
    return addTextView(viewTitle, textView);
  }

  @Override
  public void deleteView(IComboView view) {
    ComboView comboView = (ComboView) view;
    comboPane.remove(comboView.getTaskGroup());
    revalidateView();
  }

  @Override
  public void setEditState(boolean editing) {
    clearButton.setIcon(editing ? properties.getCancelEditButtonIcon() : properties.getClearButtonIcon());
    clearButton.setToolTipText(editing ? properties.getCancelButtonEditToolTip() : properties.getClearButtonToolTip());
    finalizeButton.setToolTipText(
            editing ? properties.getFinalizeButtonEditToolTip() : properties.getFinalizeButtonToolTip());
  }
}
