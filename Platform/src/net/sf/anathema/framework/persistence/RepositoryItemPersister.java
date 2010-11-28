package net.sf.anathema.framework.persistence;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class RepositoryItemPersister implements IAnathemaXmlConstants {

  public void save(Element element, IItem item) {
    String repositoryId = item.getId();
    Ensure.ensureNotNull("Repository item must have an id for saving.", repositoryId); //$NON-NLS-1$
    element.addAttribute(ATTRIB_REPOSITORY_ID, repositoryId);
    element.addAttribute(ATTRIB_REPOSITORY_PRINT_NAME, item.getDisplayName());
  }

  public void load(Element element, IItem item) throws PersistenceException {
    item.getRepositoryLocation().setId(ElementUtilities.getRequiredAttrib(element, ATTRIB_REPOSITORY_ID));
    item.setPrintName(ElementUtilities.getRequiredAttrib(element, ATTRIB_REPOSITORY_PRINT_NAME));
  }
}