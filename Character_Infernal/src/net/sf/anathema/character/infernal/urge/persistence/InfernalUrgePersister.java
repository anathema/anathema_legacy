package net.sf.anathema.character.infernal.urge.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.infernal.urge.model.IInfernalUrgeModel;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class InfernalUrgePersister implements IAdditionalPersister {

  public static final String TAG_URGE = "urge"; //$NON-NLS-1$
  public static final String ATTRIB_DESCRIPTION = "description";

	@Override
	public void load(Element parent, IAdditionalModel model)
			throws PersistenceException
	{
		IInfernalUrgeModel urgeModel = (IInfernalUrgeModel)model;
		Element urgeElement = parent.element(TAG_URGE);
		urgeModel.setUrge(urgeElement.attributeValue(ATTRIB_DESCRIPTION));
	}
	
	@Override
	public void save(Element parent, IAdditionalModel model)
	{
		IInfernalUrgeModel urgeModel = (IInfernalUrgeModel)model;
		Element urgeElement = parent.addElement(TAG_URGE);
		urgeElement.addAttribute(ATTRIB_DESCRIPTION, urgeModel.getUrge());
	}
}