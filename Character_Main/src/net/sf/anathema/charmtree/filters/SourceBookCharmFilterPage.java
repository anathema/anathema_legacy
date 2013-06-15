package net.sf.anathema.charmtree.filters;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.ui.ObjectUiListCellRenderer;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;

public class SourceBookCharmFilterPage implements ICharmFilterPage {
  private List<IExaltedSourceBook> allowedBooks;
  private List<IExaltedSourceBook> excludedBooks;
  private boolean[] includePrereqs;
  private Resources resources;
  private JList<IExaltedSourceBook> allowedList;
  private JList<IExaltedSourceBook> excludedList;

  public SourceBookCharmFilterPage(Resources resources, List<IExaltedSourceBook> allowed,
                                   ArrayList<IExaltedSourceBook> excluded, boolean[] includePrereqs) {
    this.allowedBooks = allowed;
    this.excludedBooks = excluded;
    this.includePrereqs = includePrereqs;
    this.resources = resources;
  }

  @Override
  public JPanel getContent() {
    JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(3).fill()));
    this.allowedList = addTitledJList(panel, "CharmFilters.SourceBook.Included");
    createButtonPanel(panel);
    this.excludedList = addTitledJList(panel, "CharmFilters.SourceBook.Excluded");
    fillDisplayLists();
    JRadioButton prereqShownButton = new JRadioButton();
    JRadioButton prereqHiddenButton = new JRadioButton();
    panel.add(prereqShownButton, new CC().spanX());
    panel.add(prereqHiddenButton, new CC().spanX());
    ButtonGroup prereqButtonGroup = new ButtonGroup();
    prereqButtonGroup.add(prereqShownButton);
    prereqButtonGroup.add(prereqHiddenButton);
    prereqShownButton.setAction(new SmartAction() {
      @Override
      protected void execute(Component parentComponent) {
        includePrereqs[0] = true;
      }
    });
    prereqHiddenButton.setAction(new SmartAction() {
      @Override
      protected void execute(Component parentComponent) {
        includePrereqs[0] = false;
      }
    });
    if (includePrereqs[0]) {
      prereqShownButton.setSelected(true);
    } else {
      prereqHiddenButton.setSelected(true);
    }
    prereqShownButton.setText(resources.getString("CharmFilters.SourceBook.ShowPrereqs"));
    prereqHiddenButton.setText(resources.getString("CharmFilters.SourceBook.HidePrereqs"));
    String title = resources.getString("CharmFilters.SourceBook.SourceFilters");
    TitledBorder border = BorderFactory.createTitledBorder(title);
    panel.setBorder(border);
    return panel;
  }

  private JList<IExaltedSourceBook> addTitledJList(JPanel panel, String title) {
    panel.add(new JLabel(resources.getString(title)), new CC().split(2).flowY());
    JList<IExaltedSourceBook> list = new JList<>();
    list.setCellRenderer(new ObjectUiListCellRenderer(new BookUI(resources)));
    list.setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
    list.setSize(300, 100);
    JScrollPane allowedPane = new JScrollPane(list);
    allowedPane.setSize(300, 100);
    panel.add(allowedPane, new CC().grow().push());
    return list;
  }

  private void createButtonPanel(JPanel panel) {
    JPanel buttonGrid = new JPanel();
    buttonGrid.setLayout(new GridLayout(2, 1));
    JButton addSourceButton = new JButton();
    JButton removeSourceButton = new JButton();
    buttonGrid.add(addSourceButton);
    buttonGrid.add(removeSourceButton);
    panel.add(buttonGrid);
    removeSourceButton.setAction(new SmartAction() {
      @Override
      protected void execute(Component parentComponent) {
        removeBooks(allowedList.getSelectedValuesList());
      }
    });
    removeSourceButton.setText(">>");
    addSourceButton.setAction(new SmartAction() {
      @Override
      protected void execute(Component parentComponent) {
        addBooks(excludedList.getSelectedValuesList());
      }
    });
    addSourceButton.setText("<<");
  }

  private void removeBooks(List<IExaltedSourceBook> books) {
    for (IExaltedSourceBook book : books) {
      allowedBooks.remove(book);
      excludedBooks.add(book);
    }
    fillDisplayLists();
  }

  private void addBooks(List<IExaltedSourceBook> books) {
    for (IExaltedSourceBook book : books) {
      allowedBooks.add(book);
      excludedBooks.remove(book);
    }
    fillDisplayLists();
  }

  private void fillDisplayLists() {
    BookTitleComparator comparator = new BookTitleComparator(resources);
    Collections.sort(allowedBooks, comparator);
    Collections.sort(excludedBooks, comparator);
    allowedList.setListData(getBookArray(allowedBooks));
    excludedList.setListData(getBookArray(excludedBooks));
  }

  private IExaltedSourceBook[] getBookArray(List<IExaltedSourceBook> books) {
    return books.toArray(new IExaltedSourceBook[books.size()]);
  }

  private static class BookTitleComparator implements Comparator<IExaltedSourceBook> {
    private final Resources resources;

    public BookTitleComparator(Resources resources) {
      this.resources = resources;
    }

    @Override
    public int compare(IExaltedSourceBook o1, IExaltedSourceBook o2) {
      String title1 = resources.getString("ExaltedSourceBook." + o1.getId());
      String title2 = resources.getString("ExaltedSourceBook." + o2.getId());
      return title1.compareTo(title2);
    }
  }
}