package net.sf.anathema.character.ghost.passions.persistence;

import java.util.List;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.ghost.passions.model.IGhostPassionsModel;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class GhostPassionsPersister implements IAdditionalPersister {

  private static final String TAG_PASSION = "Passion"; //$NON-NLS-1$
  private static final String ATTRIB_NAME = "name";
  private static final String ATTRIB_VIRTUE = "virtue";
  private final TraitPersister persister = new TraitPersister();

  public void save(Element parent, IAdditionalModel model)
  {
	  IGhostPassionsModel passionsModel = (IGhostPassionsModel) model;
	  //Element passionsElement = parent.addElement(TAG_PASSIONS);
	  for (VirtueType virtue : VirtueType.values())
	    for (ISubTrait passion : passionsModel.getPassionContainer(virtue).getSubTraits())
	    {
	        Element passionElement = persister.saveTrait(parent, TAG_PASSION, passion);
	        passionElement.addAttribute(ATTRIB_NAME, passion.getName());
	        passionElement.addAttribute(ATTRIB_VIRTUE, virtue.getId());
	    }
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException
  {
	  IGhostPassionsModel passionsModel = (IGhostPassionsModel) model;
	  List<Element> passionsElements = ElementUtilities.elements(parent, TAG_PASSION);
	  for (Element passionElement : passionsElements) {
	      String passionName = passionElement.attributeValue(ATTRIB_NAME);
	      VirtueType virtue = VirtueType.valueOf(passionElement.attributeValue(ATTRIB_VIRTUE));
	      ISubTraitContainer passionContainer = passionsModel.getPassionContainer(virtue);
	      ISubTrait passion = passionContainer.addSubTrait(passionName);
	      persister.restoreTrait(passionElement, passion);
	  }
  }
}