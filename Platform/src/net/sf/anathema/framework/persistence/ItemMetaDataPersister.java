package net.sf.anathema.framework.persistence;

import com.google.common.base.Preconditions;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import static net.sf.anathema.framework.persistence.IAnathemaXmlConstants.ATTRIB_REPOSITORY_ID;
import static net.sf.anathema.framework.persistence.IAnathemaXmlConstants.ATTRIB_REPOSITORY_PRINT_NAME;

public class ItemMetaDataPersister {

  public void save(Element element, Item item) {
    String repositoryId = item.getId();
    Preconditions.checkNotNull(repositoryId, "Repository item must have an id for saving.");
    element.addAttribute(ATTRIB_REPOSITORY_ID, repositoryId);
    element.addAttribute(ATTRIB_REPOSITORY_PRINT_NAME, item.getDisplayName());
  }

  public void load(Element element, Item item) throws PersistenceException {
    item.getRepositoryLocation().setId(ElementUtilities.getRequiredAttrib(element, ATTRIB_REPOSITORY_ID));
    item.setPrintName(ElementUtilities.getRequiredAttrib(element, ATTRIB_REPOSITORY_PRINT_NAME));
  }
}