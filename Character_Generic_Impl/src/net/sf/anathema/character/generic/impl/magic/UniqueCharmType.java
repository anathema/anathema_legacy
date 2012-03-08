package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.template.magic.IUniqueCharmType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class UniqueCharmType implements IUniqueCharmType
{
	private final String label;
	private final String type;
	private final String keyword;
	private final boolean canFavor;
	
	public UniqueCharmType(String type, String label, String keyword, boolean canFavor)
	{
		this.keyword = keyword;
		this.label = label;
		this.type = type;
		this.canFavor = canFavor;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public IIdentificate getId()
	{
		return new Identificate(type);
	}
	
	public String getKeyword()
	{
		return keyword;
	}
	
	public boolean canCountAsFavored()
	{
		return canFavor;
	}
}
