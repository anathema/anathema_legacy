package net.sf.anathema.character.framework.xml.registry;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.framework.ICharacterTemplateExtensionResourceCache;
import net.sf.anathema.character.framework.xml.ITemplateParser;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlTemplateRegistry<T> implements IXmlTemplateRegistry<T> {

  private final List<String> idsInProgress = new ArrayList<>();
  private final IRegistry<String, T> templateRegistry = new Registry<>();
  private final ICharacterTemplateExtensionResourceCache cache;
  private ITemplateParser<T> templateParser;

  public XmlTemplateRegistry(ICharacterTemplateExtensionResourceCache cache) {
    this.cache = cache;
  }

  @Override
  public void setTemplateParser(ITemplateParser<T> templateParser) {
    this.templateParser = templateParser;
  }

  @Override
  public T get(String id) throws PersistenceException {
    return get(id, "");
  }

  @Override
  public T get(final String id, final String prefix) throws PersistenceException {
    ResourceFile resource = loadIntegratedSourceFile(id, prefix);
    if (resource == null) {
      resource = loadUserDefinedSourceFile(id);
    }
    return get(resource);
  }

  private ResourceFile loadUserDefinedSourceFile(String id) {
    return cache.getTemplateResource(id);
  }

  private ResourceFile loadIntegratedSourceFile(String id, String prefix) {
    return cache.getTemplateResource(prefix + "data/" + id);
  }

  @Override
  public T get(final ResourceFile resource) throws PersistenceException {
    return get(resource.getFileName(), () -> resource.getURL().openStream());
  }

  private T get(String id, IDocumentStreamOpener opener) throws PersistenceException {
    T template = templateRegistry.get(id);
    if (template != null) {
      return template;
    }
    Preconditions.checkNotNull(templateParser);
    if (idsInProgress.contains(id)) {
      throw new PersistenceException("Illegal recursion in template file:" + id);
    }
    idsInProgress.add(id);
    Document document = readDocument(id, opener);
    T parsedTemplate = templateParser.parseTemplate(document.getRootElement());
    templateRegistry.register(id, parsedTemplate);
    idsInProgress.remove(id);
    return parsedTemplate;
  }

  private Document readDocument(String id, IDocumentStreamOpener opener) {
    InputStream documentStream = null;
    try {
      documentStream = opener.openDocument();
      return DocumentUtilities.read(documentStream);
    } catch (Exception e) {
      throw new PersistenceException("Unable to find file " + id);
    } finally {
      IOUtils.closeQuietly(documentStream);
    }
  }

  private interface IDocumentStreamOpener {
    InputStream openDocument() throws Exception;
  }
}