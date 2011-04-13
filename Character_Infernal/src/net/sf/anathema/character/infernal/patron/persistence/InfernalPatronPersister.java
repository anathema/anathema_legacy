package net.sf.anathema.character.infernal.patron.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.infernal.patron.presenter.IInfernalPatronModel;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class InfernalPatronPersister implements IAdditionalPersister {

  private static final String TAG_PATRON_YOZI = "patronYozi";
  private static final String ATTRIB_FAVORED = "favored";

  public void save(Element parent, IAdditionalModel model)
  {
	  Element element = parent.addElement(TAG_PATRON_YOZI);
	  
	  IInfernalPatronModel patronModel = (IInfernalPatronModel)model;
	  String favored = patronModel.getFavoredYozi();
	  if (favored != null)
		  element.addAttribute(ATTRIB_FAVORED, favored);
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException
  {
	  IInfernalPatronModel patronModel = (IInfernalPatronModel)model;
	  Element element = parent.element(TAG_PATRON_YOZI);
	  String favored = element.attributeValue(ATTRIB_FAVORED);
	  
	  if (favored != null)
		  patronModel.setFavoredYozi(favored);
  }
}