package net.sf.anathema.framework.itemdata.persistence;

import static net.sf.anathema.framework.itemdata.persistence.IBasicItemPersistenceConstants.TAG_NAME;
import static net.sf.anathema.framework.itemdata.persistence.IBasicItemPersistenceConstants.TAG_SUMMARY;
import net.sf.anathema.framework.itemdata.model.IBasicItemData;
import net.sf.anathema.framework.persistence.TextPersister;

import org.dom4j.Element;

public class BasicsPersister {

  private final TextPersister textPersister = new TextPersister();

  public void save(IBasicItemData item, Element rootElement) {
    textPersister.saveTextualDescription(rootElement, TAG_NAME, item.getDescription().getName());
    textPersister.saveTextualDescription(rootElement, TAG_SUMMARY, item.getDescription().getContent());
  }

  public void load(Element parent, IBasicItemData item) {
    textPersister.restoreTextualDescription(parent, TAG_NAME, item.getDescription().getName());
    textPersister.restoreTextualDescription(parent, TAG_SUMMARY, item.getDescription().getContent());
  }
}