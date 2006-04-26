package net.sf.anathema.campaign.persistence;

import static net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants.TAG_NAME;
import static net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants.TAG_SUMMARY;
import net.sf.anathema.framework.itemdata.model.IBasicItemData;
import net.sf.anathema.framework.persistence.AbstractPersister;

import org.dom4j.Element;

public class BasicsPersister extends AbstractPersister {

  public void save(IBasicItemData item, Element rootElement) {
    saveTextualDescription(rootElement, TAG_NAME, item.getDescription().getName());
    saveTextualDescription(rootElement, TAG_SUMMARY, item.getDescription().getContent());
  }

  public void load(Element parent, IBasicItemData item) {
    restoreTextualDescription(parent, TAG_NAME, item.getDescription().getName());
    restoreTextualDescription(parent, TAG_SUMMARY, item.getDescription().getContent());
  }
}