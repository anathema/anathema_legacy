package net.sf.anathema.character.generic.framework.magic.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

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
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.gui.list.ComponentEnablingListSelectionListener;

public class MagicLearnView implements IMagicLearnView {

  private final GenericControl<IMagicViewListener> control = new GenericControl<IMagicViewListener>();
  private final JList learnOptionsList = new JList(new DefaultListModel());
  private final JList learnedList = new JList(new DefaultListModel());
  private JButton addButton;
  private JButton removeButton;

  public void init(final IMagicLearnProperties properties) {
    learnOptionsList.setCellRenderer(properties.getAvailableMagicRenderer());
    learnOptionsList.setSelectionMode(properties.getAvailableListSelectionMode());
    learnedList.setCellRenderer(properties.getLearnedMagicRenderer());
    addButton = createAddMagicButton(properties.getAddButtonIcon());
    addButton.setToolTipText(properties.getAddButtonToolTip());
    learnOptionsList.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        addButton.setEnabled(properties.isMagicSelectionAvailable(learnOptionsList.getSelectedValue()));
      }
    });
    removeButton = createRemoveMagicButton(properties.getRemoveButtonIcon());
    removeButton.setToolTipText(properties.getRemoveButtonToolTip());
    learnedList.addListSelectionListener(createLearnedListListener(removeButton, learnedList));
  }

  protected ListSelectionListener createLearnedListListener(JButton button, JList list) {
    return new ComponentEnablingListSelectionListener(button, list);
  }

  private JButton createAddMagicButton(Icon icon) {
    return new JButton(new SmartAction(null, icon) {
      {
        setEnabled(false);
      }

      @Override
      protected void execute(Component parentComponent) {
        fireMagicAdded(learnOptionsList.getSelectedValues());
      }
    });
  }

  private JButton createRemoveMagicButton(Icon icon) {
    return new JButton(new SmartAction(null, icon) {
      {
        setEnabled(false);
      }

      @Override
      protected void execute(Component parentComponent) {
        fireMagicRemoved(learnedList.getSelectedValues());
      }
    });
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
        input.magicRemoved(addedMagics);
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

  public void addToGridDialogLayoutPanel(JPanel viewPort, JButton[] additionalButtons) {
    viewPort.add(createScrollPane(learnOptionsList));
    viewPort.add(addButton);
    viewPort.add(createScrollPane(learnedList));

    JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
    buttonPanel.add(removeButton);
    for (JButton additonalButton : additionalButtons) {
      buttonPanel.add(additonalButton);
    }
    viewPort.add(buttonPanel);
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
}