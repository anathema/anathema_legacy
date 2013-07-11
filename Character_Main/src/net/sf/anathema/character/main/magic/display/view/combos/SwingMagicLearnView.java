package net.sf.anathema.character.main.magic.display.view.combos;

import net.miginfocom.layout.CC;
import net.sf.anathema.hero.magic.display.MagicLearnProperties;
import net.sf.anathema.hero.magic.display.MagicLearnView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.ui.ConfigurableListCellRenderer;
import net.sf.anathema.swing.interaction.ActionInteraction;

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

  private final JList availableMagicList = new JList(new DefaultListModel());
  private final JList learnedMagicList = new JList(new DefaultListModel());
  private final JPanel centerButtons = new JPanel(new GridLayout(0, 1));
  private final JPanel endButtons = new JPanel(new GridLayout(0, 1));

  public SwingMagicLearnView(final MagicLearnProperties properties) {
    availableMagicList.setCellRenderer(new LegalityCheckListCellRenderer(properties.getLegalityCheck(), properties.getAvailableMagicRenderer()));
    availableMagicList.setSelectionMode(SINGLE_SELECTION);
    ListCellRenderer renderer = new ConfigurableListCellRenderer(properties.getLearnedMagicRenderer());
    learnedMagicList.setCellRenderer(renderer);
  }

  @Override
  public Tool addMainTool() {
    ActionInteraction interaction = new ActionInteraction();
    interaction.addTo(new AddToButtonPanel(centerButtons));
    return interaction;
  }

  @Override
  public List getSelectedLearnedValues() {
    return learnedMagicList.getSelectedValuesList();
  }

  @Override
  public List getSelectedAvailableValues() {
    return availableMagicList.getSelectedValuesList();
  }

  @Override
  public void addAvailableMagicSelectedListener(final ChangeListener changeListener) {
    availableMagicList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        changeListener.changeOccurred();
      }
    });
  }

  @Override
  public void addLearnedMagicSelectedListener(final ChangeListener changeListener) {
    learnedMagicList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
       changeListener.changeOccurred();
      }
    });
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

  @Override
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

  private boolean hasAtLeastElementsLearned(int amount) {
    return learnedMagicList.getModel().getSize() >= amount;
  }

}