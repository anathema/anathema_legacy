package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.resources.IResources;

public class MagicNameStringBuilder implements IMagicTooltipStringBuilder
{
	private final IResources resources;
	
	public MagicNameStringBuilder(IResources resources)
	{
		this.resources = resources;
	}

	@Override
	public void buildStringForMagic(StringBuilder builder, IMagic magic, Object details)
	{
		builder.append("<b>");
		builder.append(resources.getString(magic.getId()));
		builder.append("</b>");
		builder.append(HtmlLineBreak);
	}
	
}
