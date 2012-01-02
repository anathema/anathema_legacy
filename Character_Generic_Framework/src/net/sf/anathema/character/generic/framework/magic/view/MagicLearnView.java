package net.sf.anathema.character.generic.framework.magic.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.gui.list.ComponentEnablingListSelectionListener;
import net.sf.anathema.lib.util.IIdentificate;

public class MagicLearnView implements IMagicLearnView {

  private final GenericControl<IMagicViewListener> control = new GenericControl<IMagicViewListener>();
  private JList learnOptionsList = new JList(new DefaultListModel()) {
    public String getToolTipText(MouseEvent evt) {
      int index = locationToIndex(evt.getPoint());
      if (index == -1){
        return "";
      }
      Object item = getModel().getElementAt(index);
      return properties.getToolTipText(item);
    }
  };
  private final JList learnedList = new JList(new DefaultListModel());
  private final List<JButton> endButtons = new ArrayList<JButton>();
  private IMagicLearnProperties properties;
  private JPanel boxPanel;
  private JButton addButton;

  public void init(final IMagicLearnProperties properties) {
    this.properties = properties;
    learnOptionsList.setCellRenderer(properties.getAvailableMagicRenderer());
    learnOptionsList.setSelectionMode(properties.getAvailableListSelectionMode());
    learnedList.setCellRenderer(properties.getLearnedMagicRenderer());
    addButton = createAddMagicButton(properties.getAddButtonIcon(), properties.getAddButtonToolTip());
    addOptionListListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        addButton.setEnabled(properties.isMagicSelectionAvailable(learnOptionsList.getSelectedValue()));
      }
    });
    JButton removeButton = createRemoveMagicButton(
            properties.getRemoveButtonIcon(),
            properties.getRemoveButtonToolTip());
    endButtons.add(removeButton);
    addSelectionListListener(createLearnedListListener(removeButton, learnedList));
  }

  protected ListSelectionListener createLearnedListListener(JButton button, JList list) {
    return new ComponentEnablingListSelectionListener(button, list);
  }

  private JButton createAddMagicButton(Icon icon, String tooltip) {
    final SmartAction smartAction = new SmartAction(icon) {
      private static final long serialVersionUID = 1L;

      @Override
      protected void execute(Component parentComponent) {
        fireMagicAdded(learnOptionsList.getSelectedValues());
      }
    };
    return createButton(tooltip, smartAction);
  }

  private JButton createRemoveMagicButton(Icon icon, String tooltip) {
    final SmartAction smartAction = new SmartAction(icon) {
      private static final long serialVersionUID = 1L;

      @Override
      protected void execute(Component parentComponent) {
        fireMagicRemoved(learnedList.getSelectedValues());
      }
    };
    return createButton(tooltip, smartAction);
  }

  public void addAdditionalOptionsPanel(JPanel panel) {
    this.boxPanel = panel;
  }

  private JButton createButton(String tooltip, final SmartAction smartAction) {
    smartAction.setEnabled(false);
    smartAction.setToolTipText(tooltip);
    return new JButton(smartAction);
  }

  private void fireMagicRemoved(final Object[] removedMagics) {
    control.forAllDo(new IClosure<IMagicViewListener>() {
      public void execute(IMagicViewListener input) {
        input.magicRemoved(removedMagics);
      }
    });
  }

  private void fireMagicAdded(final Object[] addedMagics) {
    control.forAllDo(new IClosure<IMagicViewListener>() {
      public void execute(IMagicViewListener input) {
        input.magicAdded(addedMagics);
      }
    });
  }

  public void setMagicOptions(Object[] magics) {
    exchangeObjects((DefaultListModel) learnOptionsList.getModel(), magics);
  }

  private void exchangeObjects(DefaultListModel listModel, Object[] magic) {
    listModel.clear();
    for (Object spell : magic) {
      listModel.addElement(spell);
    }
  }

  public void setLearnedMagic(Object[] magics) {
    exchangeObjects((DefaultListModel) learnedList.getModel(), magics);
  }

  public void addMagicViewListener(IMagicViewListener listener) {
    control.addListener(listener);
  }

  public JButton addAdditionalAction(Action action) {
    JButton button = new JButton(action);
    endButtons.add(button);
    return button;
  }

  /**
   * Takes up 4 columns in GridDialogLayouted-Panel
   */
  public void addTo(JPanel panel) {
    if (boxPanel != null) {
      panel.add(boxPanel);
      panel.add(new EndOfLineMarkerComponent());
    }
    panel.add(createScrollPane(learnOptionsList));
    panel.add(addButton);
    panel.add(createScrollPane(learnedList));
    JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
    for (JButton button : endButtons) {
      buttonPanel.add(button);
    }
    panel.add(buttonPanel);
  }

  private JScrollPane createScrollPane(JList list) {
    JScrollPane scrollPane = new JScrollPane(list);
    scrollPane.setPreferredSize(new Dimension(200, 300));
    return scrollPane;
  }

  public ListModel getLearnedListModel() {
    return learnedList.getModel();
  }

  public void clearSelection() {
    learnedList.clearSelection();
  }

  public void addSelectionListListener(ListSelectionListener listener) {
    learnedList.addListSelectionListener(listener);
  }

  public void addOptionListListener(ListSelectionListener listener) {
    learnOptionsList.addListSelectionListener(listener);
  }

  public void addLearnedMagic(Object[] magics) {
    DefaultListModel listModel = (DefaultListModel) learnedList.getModel();
    for (Object spell : magics) {
      listModel.addElement(spell);
    }
  }

  public void addMagicOptions(IIdentificate[] magics, Comparator<IIdentificate> comparator) {
    DefaultListModel listModel = (DefaultListModel) learnOptionsList.getModel();
    for (IIdentificate spell : magics) {
      boolean isInserted = false;
      for (int index = 0; index < listModel.getSize(); index++) {
        if (isInserted) {
          break;
        }
        IIdentificate magicOption = (IIdentificate) listModel.get(index);
        if (comparator.compare(spell, magicOption) < 0) {
          listModel.add(index, spell);
          isInserted = true;
          break;
        }
      }
      if (!isInserted) {
        listModel.addElement(spell);
      }
    }
  }

  public void removeLearnedMagic(Object[] magics) {
    DefaultListModel listModel = (DefaultListModel) learnedList.getModel();
    for (Object spell : magics) {
      listModel.removeElement(spell);
    }
  }

  public void removeMagicOptions(Object[] magics) {
    DefaultListModel listModel = (DefaultListModel) learnOptionsList.getModel();
    for (Object spell : magics) {
      listModel.removeElement(spell);
    }
  }
}