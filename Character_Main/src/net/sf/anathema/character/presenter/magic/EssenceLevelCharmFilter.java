package net.sf.anathema.character.presenter.magic;

import javax.swing.JPanel;

import org.dom4j.Element;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.charmtree.filters.EssenceLevelCharmFilterPage;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.xml.ElementUtilities;

public class EssenceLevelCharmFilter implements ICharmFilter
{
	private int essenceLevel = 5;
	boolean enabled;
	
	boolean[] workingEnabled = new boolean[1];
	int[] workingEssenceLevel = new int[1];
	
	static final String TAG_FILTERNAME = "EssenceFilter";
	static final String ATTRIB_ENABLED = "enabled";
	static final String ATTRIB_ESSENCE = "essence";
	
	@Override
	public boolean acceptsCharm(ICharm charm, boolean isAncestor) {
		return !enabled || charm.getEssence().getCurrentValue() <= essenceLevel;
	}

	@Override
	public boolean isDirty()
	{
		return enabled != workingEnabled[0] || essenceLevel != workingEssenceLevel[0];
	}
	
	@Override
	public void apply()
	{
		enabled = workingEnabled[0];
		essenceLevel = workingEssenceLevel[0];
	}

	@Override
	public void reset()
	{
		workingEnabled[0] = enabled;
		workingEssenceLevel[0] = essenceLevel;
	}

	@Override
	public void save(Element parent)
	{
		if (enabled)
		{
			Element sourceElement = parent.addElement(TAG_FILTERNAME);
			sourceElement.addAttribute(ATTRIB_ENABLED, "true");
			sourceElement.addAttribute(ATTRIB_ESSENCE, "" + essenceLevel);
		}
	}
	
	public boolean load(Element node)
	{
		if (node.getName().equals(TAG_FILTERNAME))
		{
			if (node.attribute(ATTRIB_ENABLED).getValue().equals("true"))
				enabled = true;
			try {
				essenceLevel = ElementUtilities.getIntAttrib(node, ATTRIB_ESSENCE, 5);
			} catch (PersistenceException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	@Override
	public JPanel getFilterPreferencePanel(IResources resources) {
		workingEnabled[0] = enabled;
		workingEssenceLevel[0] = essenceLevel;
		return new EssenceLevelCharmFilterPage(resources, workingEnabled, workingEssenceLevel).getContent();
	}

}
