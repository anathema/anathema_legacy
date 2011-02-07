package net.sf.anathema.character.presenter.charm;

import javax.swing.JPanel;

import org.dom4j.Element;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.filters.ObtainableCharmFilterPage;
import net.sf.anathema.lib.resources.IResources;

public class ObtainableCharmFilter implements ICharmFilter
{
	ICharmConfiguration characterSet;
	boolean enabled;
	
	boolean[] workingEnabled = new boolean[1];
	
	static final String TAG_FILTERNAME = "ObtainFilter";
	static final String ATTRIB_ENABLED = "enabled";
	
	public ObtainableCharmFilter(ICharmConfiguration characterSet)
	{
		this(characterSet, false);
	}
	
	public ObtainableCharmFilter(ICharmConfiguration characterSet, boolean enabled)
	{
		this.characterSet = characterSet;
		this.enabled = enabled;
	}
	
	@Override
	public boolean acceptsCharm(ICharm charm, boolean isAncestor)
	{
		if (!enabled)
			return true;
		
		for (ICharm learnedCharm : characterSet.getLearnedCharms(true))
			if (learnedCharm == charm)
				return true;
		
		return characterSet.isLearnable(charm);
	}

	@Override
	public void apply()
	{
		enabled = workingEnabled[0];
	}

	@Override
	public JPanel getFilterPreferencePanel(IResources resources)
	{
		workingEnabled[0] = enabled;
		return new ObtainableCharmFilterPage(resources, workingEnabled).getContent();
	}

	@Override
	public boolean isDirty()
	{
		return enabled != workingEnabled[0];
	}

	@Override
	public void reset()
	{
		workingEnabled[0] = enabled;
	}

	@Override
	public void save(Element parent)
	{
		if (enabled)
		{
			Element sourceElement = parent.addElement(TAG_FILTERNAME);
			sourceElement.addAttribute(ATTRIB_ENABLED, "true");
		}
	}
	
	public boolean load(Element node)
	{
		if (node.getName().equals(TAG_FILTERNAME))
		{
			if (node.attribute(ATTRIB_ENABLED).getValue().equals("true"))
				enabled = true;
			return true;
		}
		return false;
	}

}
