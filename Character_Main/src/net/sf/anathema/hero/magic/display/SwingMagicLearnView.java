package net.sf.anathema.hero.magic.display;

import net.miginfocom.layout.CC;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.ui.ConfigurableListCellRenderer;
import net.sf.anathema.swing.interaction.ActionInteraction;
import org.jmock.example.announcer.Announcer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

public class SwingMagicLearnView implements MagicLearnView {

  private final Announcer<MagicViewListener> control = Announcer.to(MagicViewListener.class);
  private final JList availableMagicList = new JList(new DefaultListModel());
  private final JList learnedMagicList = new JList(new DefaultListModel());
  private final JPanel centerButtons = new JPanel(new GridLayout(0, 1));
  private final JPanel endButtons = new JPanel(new GridLayout(0, 1));

  public void init(final MagicLearnProperties properties) {
    availableMagicList.setCellRenderer(new LegalityCheckListCellRenderer(properties.getLegalityCheck(), properties.getAvailableMagicRenderer()));
    availableMagicList.setSelectionMode(SINGLE_SELECTION);
    ListCellRenderer renderer = new ConfigurableListCellRenderer(properties.getLearnedMagicRenderer());
    learnedMagicList.setCellRenderer(renderer);
    createAddMagicButton(properties.getAddButtonIcon(), properties.getAddButtonToolTip(), properties);
    createRemoveMagicButton(properties.getRemoveButtonIcon(), properties.getRemoveButtonToolTip(), properties);
  }

  private Tool createButtonFromInteraction(RelativePath icon, String tooltip, Command command) {
    Tool interaction = addCenterTool();
    interaction.setIcon(icon);
    interaction.setTooltip(tooltip);
    interaction.setCommand(command);
    return interaction;
  }

  public Tool addCenterTool() {
    ActionInteraction interaction = new ActionInteraction();
    interaction.addTo(new AddToButtonPanel(centerButtons));
    return interaction;
  }

  @Override
  public void setAvailableMagic(List magics) {
    exchangeObjects((DefaultListModel) availableMagicList.getModel(), magics.toArray(new Object[magics.size()]));
  }

  @Override
  public void setLearnedMagic(List magics) {
    exchangeObjects((DefaultListModel) learnedMagicList.getModel(), magics.toArray(new Object[magics.size()]));
  }

  @Override
  public void addMagicViewListener(MagicViewListener listener) {
    control.addListener(listener);
  }

  @Override
  public boolean hasMoreThanOneElementLearned() {
    return hasAtLeastElementsLearned(2);
  }

  @Override
  public boolean hasAnyElementLearned() {
    return hasAtLeastElementsLearned(1);
  }

  @Override
  public void addLearnedListListener(final ChangeListener changeListener) {
    learnedMagicList.getModel().addListDataListener(new ListDataListener() {
      @Override
      public void intervalAdded(ListDataEvent e) {
        changeListener.changeOccurred();
      }

      @Override
      public void intervalRemoved(ListDataEvent e) {
        changeListener.changeOccurred();
      }

      @Override
      public void contentsChanged(ListDataEvent e) {
        changeListener.changeOccurred();
      }
    });
  }

  public Tool addAdditionalTool() {
    ActionInteraction interaction = new ActionInteraction();
    interaction.addTo(new AddToButtonPanel(endButtons));
    return interaction;
  }

  public void addTo(JPanel panel) {
    panel.add(createScrollPane(availableMagicList), new CC().grow().push());
    panel.add(centerButtons);
    panel.add(createScrollPane(learnedMagicList), new CC().grow().push());
    panel.add(endButtons);
  }

  private JScrollPane createScrollPane(JList list) {
    JScrollPane scrollPane = new JScrollPane(list);
    scrollPane.setPreferredSize(new Dimension(200, 300));
    return scrollPane;
  }

  private void exchangeObjects(DefaultListModel listModel, Object[] magic) {
    listModel.clear();
    for (Object spell : magic) {
      listModel.addElement(spell);
    }
  }

  private void createRemoveMagicButton(RelativePath icon, String tooltip, final MagicLearnProperties properties) {
    Command command = new Command() {
      @Override
      public void execute() {
        fireMagicRemoved(learnedMagicList.getSelectedValuesList());
      }
    };
    final Tool tool = createButtonFromInteraction(icon, tooltip, command);
    learnedMagicList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        List selectedValues = learnedMagicList.getSelectedValuesList();
        boolean allowed = properties.isRemoveAllowed(selectedValues);
        if (allowed) {
          tool.enable();
        } else {
          tool.disable();
        }
      }
    });

  }

  private void fireMagicRemoved(List<Object> removedMagics) {
    Object[] objects = removedMagics.toArray(new Object[removedMagics.size()]);
    control.announce().magicRemoved(objects);
  }

  private void createAddMagicButton(RelativePath icon, String tooltip, final MagicLearnProperties properties) {
    Command command = new Command() {
      @Override
      public void execute() {
        fireMagicAdded(availableMagicList.getSelectedValuesList());
      }
    };
    final Tool tool = createButtonFromInteraction(icon, tooltip, command);
    availableMagicList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        boolean available = properties.isMagicSelectionAvailable(availableMagicList.getSelectedValue());
        if (available) {
          tool.enable();
        } else {
          tool.disable();
        }
      }
    });
  }

  private void fireMagicAdded(List<Object> addedMagics) {
    Object[] objects = addedMagics.toArray(new Object[addedMagics.size()]);
    control.announce().magicAdded(objects);
  }

  private boolean hasAtLeastElementsLearned(int amount) {
    return learnedMagicList.getModel().getSize() >= amount;
  }

}