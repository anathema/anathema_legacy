package net.sf.anathema.character.main.persistence;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.framework.item.HeroNameFetcher;
import net.sf.anathema.character.main.framework.item.Item;
import net.sf.anathema.framework.repository.access.printname.XmlPrintNameFileAccess;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class RepositoryIdPersister {

  private static final String ATTRIB_REPOSITORY_ID = XmlPrintNameFileAccess.ATTRIBUTE_REPOSITORY_ID;
  private static final String ATTRIB_REPOSITORY_PRINT_NAME = XmlPrintNameFileAccess.ATTRIBUTE_REPOSITORY_PRINTNAME;

  public void save(Element element, Item item) {
    String repositoryId = item.getRepositoryLocation().getId();
    Preconditions.checkNotNull(repositoryId, "Repository item must have an id for saving.");
    element.addAttribute(ATTRIB_REPOSITORY_ID, repositoryId);
    element.addAttribute(ATTRIB_REPOSITORY_PRINT_NAME, new HeroNameFetcher().getName((Hero) item.getItemData()));
  }

  public void load(Element element, Item item) throws PersistenceException {
    item.getRepositoryLocation().setId(ElementUtilities.getRequiredAttrib(element, ATTRIB_REPOSITORY_ID));
  }
}