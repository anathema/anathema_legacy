package net.sf.anathema.charmtree.filters;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.resources.IResources;

public class SourceBookCharmFilterPage implements ICharmFilterPage
{
	ArrayList<IExaltedSourceBook> allowedBooks;
	ArrayList<IExaltedSourceBook> excludedBooks;
	boolean[] includePrereqs;
	IResources resources;
	
	Map<String,IExaltedSourceBook> namesToBooksMap = new HashMap<String,IExaltedSourceBook>();
	
	JList allowedList;
	JList excludedList;
	
	public SourceBookCharmFilterPage(IResources resources, ArrayList<IExaltedSourceBook> allowed,
			ArrayList<IExaltedSourceBook> excluded,
			boolean[] includePrereqs)
	{
		this.allowedBooks = allowed;
		this.excludedBooks = excluded;
		this.includePrereqs = includePrereqs;
		this.resources = resources;
	}
	
	public JPanel getContent()
	{
		JPanel panel = new JPanel(new GridDialogLayout(1,false));
		JPanel swapPanel = new JPanel();
		
		JPanel allowedPanel = new JPanel(new GridDialogLayout(1,false));
		allowedPanel.add(new JLabel(resources.getString("CharmFilters.SourceBook.Included")));
		allowedList = new JList(convertBooksToNames(allowedBooks));
		allowedList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		allowedPanel.setSize(300, 100);
		allowedPanel.add(new JScrollPane(allowedList));
		swapPanel.add(allowedPanel);
		
		JPanel buttonGrid = new JPanel();
		buttonGrid.setLayout(new GridLayout(2,1));
		JButton addSourceButton = new JButton();
		JButton removeSourceButton = new JButton();
		buttonGrid.add(addSourceButton);
		buttonGrid.add(removeSourceButton);
		swapPanel.add(buttonGrid);
		
		removeSourceButton.setAction(new SmartAction()
		{
			protected void execute(Component parentComponent)
			{
				removeBooks(allowedList.getSelectedValues());
			}
		});
		removeSourceButton.setText(">>");
		
		addSourceButton.setAction(new SmartAction()
		{
			protected void execute(Component parentComponent)
			{
				addBooks(excludedList.getSelectedValues());
			}
		});
		addSourceButton.setText("<<");
		
		JPanel deniedPanel = new JPanel(new GridDialogLayout(1,false));
		deniedPanel.add(new JLabel(resources.getString("CharmFilters.SourceBook.Excluded")));
		excludedList = new JList(convertBooksToNames(excludedBooks));
		excludedList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		deniedPanel.setSize(300, 100);
		deniedPanel.add(new JScrollPane(excludedList));
		swapPanel.add(deniedPanel);
		
		panel.add(swapPanel);
		
		JRadioButton prereqShownButton = new JRadioButton();
		JRadioButton prereqHiddenButton = new JRadioButton();
		panel.add(prereqShownButton);
		panel.add(prereqHiddenButton);
		ButtonGroup prereqButtonGroup = new ButtonGroup();
		prereqButtonGroup.add(prereqShownButton);
		prereqButtonGroup.add(prereqHiddenButton);
		prereqShownButton.setAction(new SmartAction()
		{
			protected void execute(Component parentComponent)
			{
				includePrereqs[0] = true;
			}
		});
		prereqHiddenButton.setAction(new SmartAction()
		{
			protected void execute(Component parentComponent)
			{
				includePrereqs[0] = false;
			}
		});
		if (includePrereqs[0])
			prereqShownButton.setSelected(true);
		else
			prereqHiddenButton.setSelected(true);
		prereqShownButton.setText(resources.getString("CharmFilters.SourceBook.ShowPrereqs"));
		prereqHiddenButton.setText(resources.getString("CharmFilters.SourceBook.HidePrereqs"));
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder(resources.getString("CharmFilters.SourceBook.SourceFilters"));
		panel.setBorder(title);
				
		return panel;
	}
	
	private Object[] convertBooksToNames(ArrayList<IExaltedSourceBook> bookList)
	{
		ArrayList<String> bookNames = new ArrayList<String>();
		for (IExaltedSourceBook book : bookList)
		{
			String name = resources.getString("ExaltedSourceBook." + book.toString());
			bookNames.add(name);
			
			if (namesToBooksMap.get(name) == null)
				namesToBooksMap.put(name, book);
			
		}
		return bookNames.toArray();
	}
	
	private void removeBooks(Object[] books)
	{
		for (Object bookName : books)
		{
			String name = (String) bookName;
			IExaltedSourceBook book = namesToBooksMap.get(name);
			allowedBooks.remove(book);
			excludedBooks.add(book);
		}
		allowedList.setListData(convertBooksToNames(allowedBooks));
		excludedList.setListData(convertBooksToNames(excludedBooks));
	}
	
	private void addBooks(Object[] books)
	{
		for (Object bookName : books)
		{
			String name = (String) bookName;
			IExaltedSourceBook book = namesToBooksMap.get(name);
			allowedBooks.add(book);
			excludedBooks.remove(book);
		}
		allowedList.setListData(convertBooksToNames(allowedBooks));
		excludedList.setListData(convertBooksToNames(excludedBooks));
	}
}
