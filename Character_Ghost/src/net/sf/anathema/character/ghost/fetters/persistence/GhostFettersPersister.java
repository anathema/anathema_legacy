package net.sf.anathema.character.ghost.fetters.persistence;

import java.util.List;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.ghost.fetters.model.Fetter;
import net.sf.anathema.character.ghost.fetters.model.IGhostFettersModel;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class GhostFettersPersister implements IAdditionalPersister
{
  private static final String TAG_FETTER = "Fetter"; //$NON-NLS-1$
  private static final String ATTRIB_NAME = "name";
  private final TraitPersister persister = new TraitPersister();

  public void save(Element parent, IAdditionalModel model)
  {
	  IGhostFettersModel fettersModel = (IGhostFettersModel) model;
	  for (Fetter fetter : fettersModel.getFetters())
	  {
		  Element fetterElement = persister.saveTrait(parent, TAG_FETTER, fetter);
		  fetterElement.addAttribute(ATTRIB_NAME, fetter.getName());
	  }
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException
  {
	  IGhostFettersModel fettersModel = (IGhostFettersModel) model;
	  List<Element> fetterElements = ElementUtilities.elements(parent, TAG_FETTER);
	  for (Element fetterElement : fetterElements) {
	      String fetterName = fetterElement.attributeValue(ATTRIB_NAME);
	      fettersModel.setCurrentFetterName(fetterName);
	      Fetter fetter = fettersModel.commitSelection();
	      persister.restoreTrait(fetterElement, fetter);
	  }
  }
}