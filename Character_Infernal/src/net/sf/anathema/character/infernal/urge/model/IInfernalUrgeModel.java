package net.sf.anathema.character.infernal.urge.model;

import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IInfernalUrgeModel extends IVirtueFlawModel
{
	ITextualDescription getDescription();
	
	void setDescription(String urge);
}