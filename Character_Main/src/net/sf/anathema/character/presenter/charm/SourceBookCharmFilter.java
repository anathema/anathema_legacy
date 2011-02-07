package net.sf.anathema.character.presenter.charm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.dom4j.Element;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.filters.SourceBookCharmFilterPage;
import net.sf.anathema.lib.resources.IResources;

public class SourceBookCharmFilter implements ICharmFilter
{
	Map<IExaltedEdition,ArrayList<IExaltedSourceBook>> allMaterial =
		new HashMap<IExaltedEdition,ArrayList<IExaltedSourceBook>>();
	Map<IExaltedEdition,ArrayList<IExaltedSourceBook>> excludedMaterial =
		new HashMap<IExaltedEdition,ArrayList<IExaltedSourceBook>>();
	ArrayList<IExaltedSourceBook> workingExcludedMaterial;
	IExaltedEdition edition;
	ICharmConfiguration characterSet;
	
	boolean includePrereqs;
	boolean[] workingIncludePrereqs = new boolean[1];
	
	static final String TAG_FILTERNAME = "SourceFilter";
	static final String TAG_SOURCEBOOK = "SourceBook";
	static final String ATTRIB_NAME = "name";
	static final String ATTRIB_EDITION = "edition";
	static final String ATTRIB_SHOWPREREQ = "showprereqs";
	
	public SourceBookCharmFilter(IExaltedEdition edition)
	{
		this(edition, null, true, null);
	}
	
	public SourceBookCharmFilter(IExaltedEdition edition, ICharmConfiguration characterSet)
	{
		this(edition, null, true, characterSet);
	}
	
	public SourceBookCharmFilter(IExaltedEdition edition, ArrayList<IExaltedSourceBook> material, boolean includePrereqs)
	{
		this(edition, material, includePrereqs, null);
	}
	
	public SourceBookCharmFilter(IExaltedEdition edition, ArrayList<IExaltedSourceBook> material, boolean includePrereqs,
			ICharmConfiguration characterSet)
	{
		this.edition = edition;
		this.includePrereqs = includePrereqs;
		this.characterSet = characterSet;
		prepareEdition(edition);
		setExcludedMaterial(edition, material);
	}
	
	public void setEdition(IExaltedEdition edition)
	{
		this.edition = edition;
		if (allMaterial.get(edition) == null)
			prepareEdition(edition);
	}
	
	private void setExcludedMaterial(IExaltedEdition edition, ArrayList<IExaltedSourceBook> material)
	{
		if (allMaterial.get(edition) == null)
			prepareEdition(edition);
		
		if (material != null)
			excludedMaterial.put(edition, material);
	}
	
	private List<IExaltedSourceBook> prepareEdition(IExaltedEdition edition)
	{
		ArrayList<IExaltedSourceBook> materialList = new ArrayList<IExaltedSourceBook>();
		IExaltedSourceBook[] bookSet = ExaltedSourceBook.getSourcesForEdition(edition);
		for (IExaltedSourceBook book : bookSet)
			materialList.add(book);
		allMaterial.put(edition, materialList);
		
		ArrayList<IExaltedSourceBook> materialExcluded = new ArrayList<IExaltedSourceBook>();
		excludedMaterial.put(edition, materialExcluded);
		
		return excludedMaterial.get(edition);
	}
	
	private ArrayList<IExaltedSourceBook> getApprovedList(IExaltedEdition edition)
	{
		ArrayList<IExaltedSourceBook> approvedMaterial = new ArrayList<IExaltedSourceBook>(
				allMaterial.get(edition));
		approvedMaterial.removeAll(excludedMaterial.get(edition));
		return approvedMaterial;
	}
	
	@Override
	public boolean acceptsCharm(ICharm charm, boolean isAncestor)
	{
		if (isAncestor && includePrereqs)
			return true;
		
		if (characterSet != null)
			for (ICharm learnedCharm : characterSet.getLearnedCharms(true))
				if (learnedCharm == charm)
					return true;
		
		IExaltedEdition edition = charm.getSource().getEdition();
		List<IExaltedSourceBook> excludedSourceList = excludedMaterial.get(edition);
		if (excludedSourceList == null)
			excludedSourceList = prepareEdition(edition);
		
		if (!excludedSourceList.contains(charm.getSource()))
			return true;
		
		return false;
	}
	
	@Override
	public JPanel getFilterPreferencePanel(IResources resources)
	{
		workingExcludedMaterial = new ArrayList<IExaltedSourceBook>(excludedMaterial.get(edition));
		workingIncludePrereqs[0] = includePrereqs;
		SourceBookCharmFilterPage page = new SourceBookCharmFilterPage(resources, getApprovedList(edition),
				workingExcludedMaterial, workingIncludePrereqs);
		
		return page.getContent();
	}

	@Override
	public void apply()
	{
		excludedMaterial.put(edition, workingExcludedMaterial);
		includePrereqs = workingIncludePrereqs[0];
		reset();
	}

	@Override
	public boolean isDirty() {
		return workingExcludedMaterial != null ||
		includePrereqs != workingIncludePrereqs[0];
	}

	@Override
	public void reset()
	{
		workingExcludedMaterial = null;
	}
	
	public void save(Element parent)
	{
		Element sourceBookFilter = parent.addElement(TAG_FILTERNAME);
		for (IExaltedEdition edition : ExaltedEdition.values())
		{
			List<IExaltedSourceBook> list = excludedMaterial.get(edition);
			if (list != null)
				for (IExaltedSourceBook book : list)
				{
					Element bookElement = sourceBookFilter.addElement(TAG_SOURCEBOOK);
					bookElement.addAttribute(ATTRIB_NAME, book.getId());
					bookElement.addAttribute(ATTRIB_EDITION, edition.getId());
				}
		}
		sourceBookFilter.addAttribute(ATTRIB_SHOWPREREQ, includePrereqs ? "true" : "false");
	}
	
	public boolean load(Element node)
	{
		if (node.getName().equals(TAG_FILTERNAME))
		{
			for (Object bookNode : node.elements())
			{
				try
				{
					Element sourceBook = (Element) bookNode;
					String editionString = sourceBook.attributeValue(ATTRIB_EDITION);
					String idString = sourceBook.attributeValue(ATTRIB_NAME);
					IExaltedEdition edition = ExaltedEdition.valueOf(editionString);
					IExaltedSourceBook book = ExaltedSourceBook.valueOf(idString);
					
					if (allMaterial.get(edition) == null)
						prepareEdition(edition);
					
					excludedMaterial.get(edition).add(book);
				}
				catch (Exception e)
				{
					excludedMaterial.get(edition).clear();
					return false;
				}
			}
			
			if (node.attributeValue(ATTRIB_SHOWPREREQ).equals("true"))
				includePrereqs = true;
			else
				includePrereqs = false;
			
			return true;
		}
		return false;
	}
}
