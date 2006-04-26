package net.sf.anathema.campaign.persistence;

import static net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants.ATTRIB_ITEM_TYPE;
import static net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants.ATTRIB_REPOSITORY_ID;
import static net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants.ATTRIB_REPOSITORY_PRINT_NAME;
import static net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants.TAG_CONTENT;
import static net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants.TAG_CONTENT_ITEM;
import static net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants.TAG_NAME;
import static net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants.TAG_PLOT;
import static net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants.TAG_SERIES_ROOT;
import static net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants.TAG_SUMMARY;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.campaign.concrete.Series;
import net.sf.anathema.campaign.model.ISeries;
import net.sf.anathema.campaign.model.ISeriesContentModel;
import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.campaign.model.plot.IPlotModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.framework.itemdata.model.ItemDescription;
import net.sf.anathema.framework.persistence.AbstractPersister;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.repository.AnathemaItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SeriesPersister extends AbstractPersister implements IRepositoryItemPersister {

  private final RepositoryItemPersister repositoryItemPerister = new RepositoryItemPersister();

  private final IItemType campaignType;
  private final IItemType[] supportedTypes;
  private final IItemTypeRegistry registry;
  private final IPrintNameFileAccess printNameFileAccess;

  public SeriesPersister(
      IPrintNameFileAccess printNameFileAccess,
      IItemType campaignType,
      IItemType[] supportedTypes,
      IItemTypeRegistry registry) {
    this.printNameFileAccess = printNameFileAccess;
    this.campaignType = campaignType;
    this.supportedTypes = supportedTypes;
    this.registry = registry;
  }

  public void save(IRepositoryWriteAccess writeAccess, IItem item) throws RepositoryException, IOException {
    Element rootElement = DocumentHelper.createElement(TAG_SERIES_ROOT);
    repositoryItemPerister.save(rootElement, item);
    saveSeries(writeAccess, rootElement, (ISeries) item.getItemData());
    Document mainDocument = DocumentHelper.createDocument(rootElement);
    DocumentUtilities.save(mainDocument, writeAccess.createMainOutputStream());
  }

  private void saveSeries(IRepositoryWriteAccess writeAccess, Element rootElement, ISeries series) throws RepositoryException, IOException {
    saveItemDescription(rootElement, series.getPlot().getRootElement().getDescription());
    saveContents(series.getContentModel(), rootElement);
    savePlot(series.getPlot(), rootElement, writeAccess);
  }

  private void saveItemDescription(Element rootElement, IItemDescription description) {
    saveTextualDescription(rootElement, TAG_NAME, description.getName());
    saveTextualDescription(rootElement, TAG_SUMMARY, description.getContent());
  }

  private void savePlot(IPlotModel plot, Element parent, IRepositoryWriteAccess writeAccess)
      throws RepositoryException,
      IOException {
    saveSubElements(plot.getRootElement(), parent.addElement(TAG_PLOT), writeAccess);
  }

  private void saveSubElements(IPlotElement plotElement, Element parent, IRepositoryWriteAccess writeAccess)
      throws RepositoryException,
      IOException {
    if (!plotElement.getTimeUnit().hasSuccessor()) {
      return;
    }
    String successorId = plotElement.getTimeUnit().getSuccessor().getId();
    for (IPlotElement subElement : plotElement.getChildren()) {
      Element plotItemElement = parent.addElement(successorId);
      plotItemElement.addAttribute(ATTRIB_REPOSITORY_ID, subElement.getId());
      IItemDescription description = subElement.getDescription();
      if ((description.getName().isDirty()) || (description.getContent().isDirty())) {
        savePlotFile(subElement, writeAccess);
      }
      saveSubElements(subElement, plotItemElement, writeAccess);
    }
  }

  private void savePlotFile(IPlotElement subElement, IRepositoryWriteAccess writeAccess)
      throws RepositoryException,
      IOException {
    Element plotXMLElement = DocumentHelper.createElement(subElement.getTimeUnit().getId());
    plotXMLElement.addAttribute(ATTRIB_REPOSITORY_ID, subElement.getId());
    plotXMLElement.addAttribute(ATTRIB_REPOSITORY_PRINT_NAME, subElement.getDescription().getName().getText());
    saveItemDescription(plotXMLElement, subElement.getDescription());
    Document document = DocumentHelper.createDocument(plotXMLElement);
    DocumentUtilities.save(document, writeAccess.createSubOutputStream(subElement.getId()));
  }

  private void saveContents(ISeriesContentModel contentModel, Element parent) {
    Element contentElement = parent.addElement(TAG_CONTENT);
    for (IItemType type : contentModel.getAllItemTypes()) {
      for (PrintNameFile file : contentModel.getPrintNameFiles(type)) {
        Element contentItemElement = contentElement.addElement(TAG_CONTENT_ITEM);
        contentItemElement.addAttribute(ATTRIB_ITEM_TYPE, file.getItemType().getId());
        contentItemElement.addAttribute(ATTRIB_REPOSITORY_ID, file.getRepositoryId());
      }
    }
  }

  protected final void restoreItemDescription(Element documentRoot, IItemDescription description) {
    restoreTextualDescription(documentRoot, TAG_NAME, description.getName());
    restoreTextualDescription(documentRoot, TAG_SUMMARY, description.getContent());
  }

  public final IItem load(IRepositoryReadAccess readAccess) throws PersistenceException, RepositoryException {
    InputStream stream = null;
    try {
      if (readAccess == null) {
        return null;
      }
      stream = readAccess.openMainInputStream();
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(stream);
      return load(document, readAccess);
    }
    catch (DocumentException e) {
      throw new PersistenceException(e);
    }
    finally {
      IOUtilities.close(stream);
    }
  }

  private IItem load(Document xmlDocument, IRepositoryReadAccess readAccess)
      throws PersistenceException,
      RepositoryException {
    ISeries seriesData = new Series(supportedTypes);
    IItem item = new AnathemaItem(campaignType, seriesData);
    Element documentRoot = xmlDocument.getRootElement();
    repositoryItemPerister.load(documentRoot, item);
    restoreItemDescription(documentRoot, seriesData.getPlot().getRootElement().getDescription());
    loadContents(seriesData.getContentModel(), xmlDocument.getRootElement());
    loadPlot(seriesData.getPlot(), xmlDocument.getRootElement(), readAccess);
    return item;
  }

  private void loadPlot(IPlotModel plot, Element parent, IRepositoryReadAccess readAccess)
      throws RepositoryException,
      PersistenceException {
    Element plotElement = parent.element(TAG_PLOT);
    if (plotElement == null) {
      return;
    }
    loadPlotElement(plotElement, plot.getRootElement(), readAccess);
  }

  private void loadPlotElement(Element element, IPlotElement parentElement, IRepositoryReadAccess readAccess)
      throws RepositoryException,
      PersistenceException {
    List subXMLElements = element.elements();
    for (Object elementObject : subXMLElements) {
      Element plotItemXMLElement = (Element) elementObject;
      String repositoryId = plotItemXMLElement.attributeValue(ATTRIB_REPOSITORY_ID);
      IPlotElement subElement = parentElement.addChild(
          loadPlotElementDescription(repositoryId, readAccess),
          repositoryId);
      loadPlotElement(plotItemXMLElement, subElement, readAccess);
    }
  }

  private IItemDescription loadPlotElementDescription(String repositoryId, IRepositoryReadAccess readAccess)
      throws RepositoryException,
      PersistenceException {
    InputStream stream = null;
    try {
      stream = readAccess.openSubInputStream(repositoryId);
      SAXReader saxReader = new SAXReader();
      Element subFileRootElement = saxReader.read(stream).getRootElement();
      IItemDescription description = new ItemDescription();
      restoreItemDescription(subFileRootElement, description);
      return description;
    }
    catch (DocumentException e) {
      throw new PersistenceException(e);
    }
    finally {
      IOUtilities.close(stream);
    }
  }

  private void loadContents(ISeriesContentModel contentModel, Element parent) {
    Element contentElement = parent.element(TAG_CONTENT);
    if (contentElement == null) {
      return;
    }
    List<Element> elementList=ElementUtilities.elements(contentElement, TAG_CONTENT_ITEM); 
    for (Iterator<Element> contentItemElements = elementList.iterator(); contentItemElements.hasNext();) {
      Element contentItemElement = contentItemElements.next();
      String repositoryId = contentItemElement.attributeValue(ATTRIB_REPOSITORY_ID);
      IItemType itemType = registry.getById(contentItemElement.attributeValue(ATTRIB_ITEM_TYPE));
      PrintNameFile printNameFile = printNameFileAccess.getPrintNameFile(itemType, repositoryId);
      // todo : printNameFile == null
      if (printNameFile != null) {
        contentModel.addItem(printNameFile);
      }
    }
  }
}