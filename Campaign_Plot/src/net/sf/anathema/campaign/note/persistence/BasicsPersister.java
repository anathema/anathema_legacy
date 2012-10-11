package net.sf.anathema.campaign.note.persistence;

import net.sf.anathema.campaign.note.model.IBasicItemData;
import net.sf.anathema.framework.persistence.TextPersister;
import org.dom4j.Element;

public class BasicsPersister {

  private final TextPersister textPersister = new TextPersister();

  public void save(IBasicItemData item, Element rootElement) {
    textPersister.saveTextualDescription(rootElement, IBasicItemPersistenceConstants.TAG_NAME, item.getDescription().getName());
    textPersister.saveTextualDescription(rootElement, IBasicItemPersistenceConstants.TAG_SUMMARY, item.getDescription().getContent());
  }

  public void load(Element parent, IBasicItemData item) {
    textPersister.restoreTextualDescription(parent, IBasicItemPersistenceConstants.TAG_NAME, item.getDescription().getName());
    textPersister.restoreTextualDescription(parent, IBasicItemPersistenceConstants.TAG_SUMMARY, item.getDescription().getContent());
  }
}