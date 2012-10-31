package net.sf.anathema.charmtree.filters;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SourceBookCharmFilterPage implements ICharmFilterPage {
  private List<IExaltedSourceBook> allowedBooks;
  private List<IExaltedSourceBook> excludedBooks;
  private boolean[] includePrereqs;
  private IResources resources;
  private Map<String, IExaltedSourceBook> namesToBooksMap = new HashMap<>();
  private JList allowedList;
  private JList excludedList;

  public SourceBookCharmFilterPage(IResources resources, List<IExaltedSourceBook> allowed,
                                   ArrayList<IExaltedSourceBook> excluded, boolean[] includePrereqs) {
    this.allowedBooks = allowed;
    this.excludedBooks = excluded;
    this.includePrereqs = includePrereqs;
    this.resources = resources;
  }

  @Override
  public JPanel getContent() {
    JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(3).fill()));
    this.allowedList = addTitledJList(panel, allowedBooks, "CharmFilters.SourceBook.Included");
    createButtonPanel(panel);
    this.excludedList = addTitledJList(panel, excludedBooks, "CharmFilters.SourceBook.Excluded");
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

  private JList addTitledJList(JPanel panel, List<IExaltedSourceBook> books, String title) {
    panel.add(new JLabel(resources.getString(title)), new CC().split(2).flowY());
    JList list = new JList(convertBooksToNames(books));
    list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
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
        removeBooks(allowedList.getSelectedValues());
      }
    });
    removeSourceButton.setText(">>");
    addSourceButton.setAction(new SmartAction() {
      @Override
      protected void execute(Component parentComponent) {
        addBooks(excludedList.getSelectedValues());
      }
    });
    addSourceButton.setText("<<");
  }

  private Object[] convertBooksToNames(List<IExaltedSourceBook> bookList) {
    List<String> bookNames = new ArrayList<>();
    for (IExaltedSourceBook book : bookList) {
      String name = resources.getString("ExaltedSourceBook." + book.getId());
      bookNames.add(name);
      if (namesToBooksMap.get(name) == null) {
        namesToBooksMap.put(name, book);
      }
    }
    Collections.sort(bookNames);
    return bookNames.toArray();
  }

  private void removeBooks(Object[] books) {
    for (Object bookName : books) {
      String name = (String) bookName;
      IExaltedSourceBook book = namesToBooksMap.get(name);
      allowedBooks.remove(book);
      excludedBooks.add(book);
    }
    allowedList.setListData(convertBooksToNames(allowedBooks));
    excludedList.setListData(convertBooksToNames(excludedBooks));
  }

  private void addBooks(Object[] books) {
    for (Object bookName : books) {
      String name = (String) bookName;
      IExaltedSourceBook book = namesToBooksMap.get(name);
      allowedBooks.add(book);
      excludedBooks.remove(book);
    }
    allowedList.setListData(convertBooksToNames(allowedBooks));
    excludedList.setListData(convertBooksToNames(excludedBooks));
  }
}
