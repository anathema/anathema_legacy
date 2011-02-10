package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.template.magic.IUniqueRequiredCharmType;

public class UniqueRequiredCharmType implements IUniqueRequiredCharmType
{
	private final String name;
	private final String type;
	private final boolean canFavor;
	
	public UniqueRequiredCharmType(String name, String type, boolean canFavor)
	{
		this.name = name;
		this.type = type;
		this.canFavor = canFavor;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}
	
	public boolean canCountAsFavored()
	{
		return canFavor;
	}
}
