package net.sf.anathema.charmtree.filters;

import javax.swing.JPanel;

import org.dom4j.Element;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.resources.IResources;

public interface ICharmFilter
{
	public boolean acceptsCharm(ICharm charm, boolean isAncestor);
	
	public boolean isDirty();
	
	public void apply();
	
	public void reset();
	
	public void save(Element parent);
	public boolean load(Element node);
	
	public JPanel getFilterPreferencePanel(IResources resources);
}
