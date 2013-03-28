package net.sf.anathema.framework.persistence;

import com.google.common.base.Preconditions;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import static net.sf.anathema.framework.persistence.IAnathemaXmlConstants.ATTRIB_REPOSITORY_ID;
import static net.sf.anathema.framework.persistence.IAnathemaXmlConstants.ATTRIB_REPOSITORY_PRINT_NAME;

public class RepositoryItemPersister {

  public void save(Element element, IItem item) {
    String repositoryId = item.getId();
    Preconditions.checkNotNull(repositoryId, "Repository item must have an id for saving."); //$NON-NLS-1$
    element.addAttribute(ATTRIB_REPOSITORY_ID, repositoryId);
    element.addAttribute(ATTRIB_REPOSITORY_PRINT_NAME, item.getDisplayName());
  }

  public void load(Element element, IItem item) throws PersistenceException {
    item.getRepositoryLocation().setId(ElementUtilities.getRequiredAttrib(element, ATTRIB_REPOSITORY_ID));
    item.setPrintName(ElementUtilities.getRequiredAttrib(element, ATTRIB_REPOSITORY_PRINT_NAME));
  }
}