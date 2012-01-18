package net.sf.anathema.character.model.concept;

import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.apache.commons.io.IOUtils.closeQuietly;

public class NatureProvider implements INatureProvider {

  private final List<INatureType> natures = new ArrayList<INatureType>();
  private static final INatureProvider instance = new NatureProvider();
  private static final String TAG_NATURE = "nature"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$

  private NatureProvider() {
    // Nothing to do
  }

  public static INatureProvider getInstance() {
    return instance;
  }

  public void init() throws PersistenceException {
    File natureFile = new File("./data/natures.xml"); //$NON-NLS-1$
    if (natureFile.exists()) {
      InputStream externalStream = null;
      try {
        externalStream = new FileInputStream(natureFile);
        createNaturesFromStream(externalStream, true);
      } catch (FileNotFoundException e) {
        // Nothing to do
      }
      closeQuietly(externalStream);
    }
    String language = Locale.getDefault().getLanguage();
    InputStream i18nNatureStream = NatureProvider.class.getClassLoader().getResourceAsStream(
            "data/natures_" + language + ".xml"); //$NON-NLS-1$ //$NON-NLS-2$
    if (i18nNatureStream != null) {
      createNaturesFromStream(i18nNatureStream, false);
    }
    InputStream defaultNatureStream = NatureProvider.class.getClassLoader().getResourceAsStream("data/natures.xml"); //$NON-NLS-1$
    createNaturesFromStream(defaultNatureStream, false);
  }

  private void createNaturesFromStream(InputStream stream, boolean replace) throws PersistenceException {
    try {
      SAXReader saxReader = new SAXReader();
      Document defaultNatureDocument = saxReader.read(stream);
      Element root = defaultNatureDocument.getRootElement();
      for (Object elementObject : root.elements(TAG_NATURE)) {
        createNatureFromElementObject(replace, elementObject);
      }
    } catch (DocumentException e) {
      throw new PersistenceException("Error while parsing natures.xml", e); //$NON-NLS-1$
    }
  }

  private void createNatureFromElementObject(boolean replace, Object elementObject) {
    Element element = (Element) elementObject;
    INatureType nature = buildNature(element);
    for (INatureType existingNature : natures) {
      if (!replace && existingNature.getId().equals(nature.getId())) {
        return;
      }
    }
    natures.add(nature);
  }

  private INatureType buildNature(final Element element) {
    return new NatureType(element.attributeValue(ATTRIB_ID));
  }

  public INatureType[] getNatures() {
    return natures.toArray(new INatureType[natures.size()]);
  }

  public INatureType getById(final String id) {
    for (INatureType nature : natures) {
      if (nature.getId().equals(id)) {
        return nature;
      }
    }
    final NatureType natureType = new NatureType(id);
    natures.add(natureType);
    return natureType;
  }
}