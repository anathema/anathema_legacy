package net.sf.anathema.campaign.persistence;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.BasicItemData;
import net.sf.anathema.framework.itemdata.model.IBasicItemData;
import net.sf.anathema.framework.persistence.AbstractSingleFileItemPersister;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.repository.AnathemaDataItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class BasicDataItemPersister extends AbstractSingleFileItemPersister {

  private final BasicsPersister basicItemDataPersister = new BasicsPersister();
  private final RepositoryItemPersister repositoryItemPerister = new RepositoryItemPersister();
  private final String rootTagName;
  private final IItemType type;

  public BasicDataItemPersister(IItemType type, String rootTagName) {
    this.type = type;
    this.rootTagName = rootTagName;
  }

  @Override
  public void save(OutputStream stream, IItem item) throws IOException {
    Element rootElement = DocumentHelper.createElement(rootTagName);
    repositoryItemPerister.save(rootElement, item);
    basicItemDataPersister.save(((IBasicItemData) item.getItemData()), rootElement);
    Document document = DocumentHelper.createDocument(rootElement);
    DocumentUtilities.save(document, stream);
  }

  @Override
  public IItem load(Document itemXml) throws PersistenceException {
    Element rootElement = itemXml.getRootElement();
    BasicItemData data = new BasicItemData();
    AnathemaDataItem item = new AnathemaDataItem(type, data);
    repositoryItemPerister.load(rootElement, item);
    basicItemDataPersister.load(rootElement, (IBasicItemData) item.getItemData());
    return item;
  }

  public IItem createNew(IAnathemaWizardModelTemplate template) {
    return new AnathemaDataItem(type, new BasicItemData());
  }
}