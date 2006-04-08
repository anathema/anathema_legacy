package net.sf.anathema.character.meritsflaws.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsViewProperties;
import net.sf.anathema.character.meritsflaws.presenter.IPerkListener;
import net.sf.anathema.character.meritsflaws.presenter.view.IPerkDetailsView;
import net.sf.anathema.character.meritsflaws.presenter.view.IPerkView;
import net.sf.anathema.framework.presenter.view.ObjectSelectionView;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.list.SmartJList;
import net.sf.anathema.lib.gui.list.actionview.ActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.SingleSelectionActionAddableListView;

public class PerkView implements IPerkView {

  private JPanel content;
  private JPanel detailsPanel = new JPanel(new GridDialogLayout(2, false));
  private final SmartJList perkList = new SmartJList();
  private ObjectSelectionView typeFilterView;
  private ObjectSelectionView categoryFilterView;
  private final GenericControl<IPerkListener> control = new GenericControl<IPerkListener>();
  private final JButton addButton = new JButton();
  private Action removeAction;
  private IPerkDetailsView detailsView;
  private final ActionAddableListView selectedPerksView = new SingleSelectionActionAddableListView(null);
  private final IMeritsFlawsViewProperties properties;

  public PerkView(IMeritsFlawsViewProperties properties) {
    this.properties = properties;
    perkList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }

  public JComponent getContent() {
    if (content == null) {
      content = createContent();
    }
    return content;
  }

  private IGridDialogPanel createFilterPanel() {
    IGridDialogPanel panel = new DefaultGridDialogPanel();
    typeFilterView = new ObjectSelectionView(properties.getTypeFilters());
    categoryFilterView = new ObjectSelectionView(properties.getCategoryFilters());
    typeFilterView.addTo(
        properties.getTypeString(),
        properties.getTypeFilterListRenderer(),
        panel,
        GridDialogLayoutData.FILL_HORIZONTAL);
    categoryFilterView.addTo(
        properties.getCategoryString(),
        properties.getCategoryFilterListRenderer(),
        panel,
        GridDialogLayoutData.FILL_HORIZONTAL);
    return panel;
  }

  private JPanel createContent() {
    initAddButton();
    initDetailsPanel();
    JPanel availablePanel = createAvailableOptionsPanel();
    JPanel selectedPanel = createSelectedPanel();
    JPanel panel = new JPanel(new GridDialogLayout(2, true));
    panel.add(availablePanel, GridDialogLayoutData.FILL_BOTH);
    JPanel rightHandPanel = new JPanel(new GridDialogLayout(1, false));
    rightHandPanel.add(detailsPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    rightHandPanel.add(selectedPanel, GridDialogLayoutData.FILL_BOTH);
    panel.add(rightHandPanel, GridDialogLayoutData.FILL_BOTH);
    initListening();
    return panel;
  }

  private JPanel createAvailableOptionsPanel() {
    perkList.setCellRenderer(properties.getAvailableListRenderer());
    JPanel availablePanel = new JPanel(new GridDialogLayout(1, false));
    availablePanel.setBorder(new TitledBorder(properties.getSelectionString()));
    availablePanel.add(createFilterPanel().getContent(), GridDialogLayoutData.FILL_HORIZONTAL);
    availablePanel.add(new JScrollPane(perkList), GridDialogLayoutData.FILL_BOTH);
    return availablePanel;
  }

  private void initAddButton() {
    addButton.setIcon(properties.getAddIcon());
  }

  private void initDetailsPanel() {
    detailsPanel.setBorder(new TitledBorder(properties.getDetailsString()));
  }

  private JPanel createSelectedPanel() {
    JPanel selectedPanel = new JPanel(new GridDialogLayout(1, false));
    selectedPanel.setBorder(new TitledBorder(properties.getSelectedString()));
    removeAction = new SmartAction(properties.getRemoveIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        control.forAllDo(new IClosure<IPerkListener>() {
          public void execute(IPerkListener input) {
            Object object = selectedPerksView.getSelectedItems()[0];
            if (object != null) {
              input.perkRemoved(object);
            }
          }
        });
      }
    };
    selectedPerksView.addAction(removeAction);
    selectedPerksView.setListCellRenderer(properties.getSelectionListRenderer());
    selectedPanel.add(selectedPerksView.getContent(), GridDialogLayoutData.FILL_HORIZONTAL);
    return selectedPanel;
  }

  private void initListening() {
    perkList.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        control.forAllDo(new IClosure<IPerkListener>() {
          public void execute(IPerkListener input) {
            input.perkSelected(perkList.getSelectedValue());
          }
        });
      }
    });

    IObjectValueChangedListener<Object> filterListener = new IObjectValueChangedListener<Object>() {
      public void valueChanged(Object oldValue, Object newValue) {
        control.forAllDo(new IClosure<IPerkListener>() {
          public void execute(IPerkListener input) {
            input.filterChanged(typeFilterView.getComboBox().getSelectedItem(), categoryFilterView.getComboBox()
                .getSelectedItem());
          }
        });
      }
    };
    typeFilterView.addObjectSelectionChangedListener(filterListener);
    categoryFilterView.addObjectSelectionChangedListener(filterListener);

    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        control.forAllDo(new IClosure<IPerkListener>() {
          public void execute(IPerkListener input) {
            input.perkAdded(perkList.getSelectedValue(), detailsView);
          }
        });
      }
    });

    selectedPerksView.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        control.forAllDo(new IClosure<IPerkListener>() {
          public void execute(IPerkListener input) {
            Object[] selectedItems = selectedPerksView.getSelectedItems();
            if (selectedItems.length > 0) {
              input.selectionSelected(selectedItems[0]);
            }
            else
              input.selectionSelected(null);
          }
        });
      }
    });
  }

  public void setAvailablePerks(Object[] perks) {
    perkList.setObjects(perks);
  }

  public void addPerkListener(IPerkListener listener) {
    control.addListener(listener);
  }

  public void setSelectedPerks(Object[] selectedPerks) {
    selectedPerksView.setListItems(selectedPerks);
  }

  public void setPerkDetails(IPerkDetailsView view) {
    this.detailsView = view;
    detailsPanel.removeAll();
    detailsPanel.add(view.getContent(), GridDialogLayoutData.FILL_HORIZONTAL);
    GridDialogLayoutData addData = new GridDialogLayoutData();
    addData.setHorizontalAlignment(GridAlignment.END);
    detailsPanel.add(addButton, addData);
    detailsPanel.revalidate();
    detailsPanel.repaint();
  }

  public void setAddEnabled(boolean enabled) {
    addButton.setEnabled(enabled);
  }

  public void setRemoveEnabled(boolean enabled) {
    removeAction.setEnabled(enabled);
  }

  public void setAvailableListSelection(Object perk) {
    perkList.setSelectedValue(perk, true);
  }
}