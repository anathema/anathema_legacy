package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import java.text.MessageFormat;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;

public class MagicDescriptionStringBuilder implements IMagicTooltipStringBuilder
{
	private final IResources resources;
	
	private final int MAX_DESCRIPTION_LENGTH = 80;
	
	public MagicDescriptionStringBuilder(IResources resources)
	{
		this.resources = resources;
	}

	@Override
	public void buildStringForMagic(StringBuilder builder, IMagic magic,
			Object specialDetails)
	{
		if (getDescriptionString(magic) != null)
		{
			String description = resources.getString("CharmTreeView.ToolTip.Description"); //$NON-NLS-1$
			description += ColonSpace;
			description += getDescriptionString(magic); //$NON-NLS-1$
			description = AnathemaStringUtilities.createFixedWidthParagraph(description, HtmlLineBreak, MAX_DESCRIPTION_LENGTH);
			builder.append(description);
		}
	}
	
	private String getDescriptionString(IMagic magic)
	{
		  String id = magic.getId();
		  String genericId = id.substring(0, id.lastIndexOf('.'));
		  
		  String description = null;
		  if (resources.supportsKey(genericId + ".Description")) //$NON-NLS-1$
			  description = resources.getString(genericId + ".Description"); //$NON-NLS-1$
		  if (resources.supportsKey(id + ".Description")) //$NON-NLS-1$
			  description = resources.getString(id + ".Description"); //$NON-NLS-1$
		  
		  if (description != null && magic instanceof ICharm)
			  description = MessageFormat.format(description,
					  new Object[] { resources.getString(((ICharm)magic).getPrimaryTraitType().getId()) });
		  
		  return description;
	  }
}
