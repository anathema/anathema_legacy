package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.lib.util.IIdentificate;

public interface IUniqueCharmType
{
	public IIdentificate getId();
	
	public String getLabel();
	
	public String getKeyword();
	
	public boolean canCountAsFavored();
}
