package net.sf.anathema.character.infernal.urge.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.virtueflaw.model.VirtueFlaw;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class InfernalUrge extends VirtueFlaw
{
	private final ITextualDescription description = new SimpleTextualDescription(""); //$NON-NLS-1$
	
	public InfernalUrge(ICharacterModelContext context)
	{
		super(context);
	}
	
	@Override
	protected String getLimitString()
	{
		  return "VirtueFlaw.Torment";
	}
	
	public ITextualDescription getDescription()
	{
	    return description;
	}
}
