package net.sf.anathema.acceptance.fixture.character;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

import net.sf.anathema.character.generic.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;
import net.sf.anathema.test.character.DemoDataFileProvider;

import org.dom4j.Element;

import fit.Fixture;
import fit.Parse;

public class SetupCharacterPlatformFixture extends Fixture {

  private static final String TAG_EXTENSION = "extension"; //$NON-NLS-1$
  private static final String ATTRIB_POINT_ID = "point-id"; //$NON-NLS-1$
  private static final String VALUE_CHARMLIST = "CharmList"; //$NON-NLS-1$
  private static final String TAG_PARAMETER = "parameter"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_VALUE = "value"; //$NON-NLS-1$
  private static final String VALUE_TYPE = "type"; //$NON-NLS-1$
  private static final String VALUE_RULES = "rules"; //$NON-NLS-1$
  private static final String VALUE_PATH = "path"; //$NON-NLS-1$

  @Override
  public void doTable(Parse parse) {
    IResources resources = new AnathemaResources();
    IDataFileProvider dataFileProvider = new DemoDataFileProvider();
    CharacterSummary characterSummary = new CharacterSummary(summary);
    characterSummary.setResources(resources);
    CharacterModuleContainer container;
    try {
      registerCharmFiles();
      container = new CharacterModuleContainerInitializer().initContainer(resources, dataFileProvider);
    }
    catch (Exception e) {
      throw new RuntimeException("Failed to initialize Character Sub-Modules.", e); //$NON-NLS-1$
    }
    characterSummary.setCharacterModuleContainer(container);
  }

  private void registerCharmFiles() throws FileNotFoundException, AnathemaException {
    registerCharmFile(new File("../Character_Main/resources/plugin.xml")); //$NON-NLS-1$
    registerCharmFile(new File("../Character_Abyssal/resources/plugin-fragment.xml")); //$NON-NLS-1$
    registerCharmFile(new File("../Character_Lunar/resources/plugin-fragment.xml")); //$NON-NLS-1$
    registerCharmFile(new File("../Character_Sidereal/resources/plugin-fragment.xml")); //$NON-NLS-1$
    registerCharmFile(new File("../Character_Solar/resources/plugin-fragment.xml")); //$NON-NLS-1$
    registerCharmFile(new File("../Character_Db/resources/plugin-fragment.xml")); //$NON-NLS-1$
    CharmCache.getInstance().buildCharms();
  }

  private void registerCharmFile(File xmlFile) throws FileNotFoundException, AnathemaException {
    Element root = DocumentUtilities.read(xmlFile).getRootElement();
    for (Element element : ElementUtilities.elements(root, TAG_EXTENSION)) {
      String pointId = element.attributeValue(ATTRIB_POINT_ID);
      if (VALUE_CHARMLIST.equals(pointId)) {
        for (Element parameterElement : ElementUtilities.elements(element, TAG_PARAMETER)) {
          registerCharmFile(parameterElement);
        }
      }
    }
  }

  private void registerCharmFile(Element parent) {
    List<Element> subParameterList = ElementUtilities.elements(parent, TAG_PARAMETER);
    String type = getParameterValue(subParameterList, VALUE_TYPE);
    String rules = getParameterValue(subParameterList, VALUE_RULES);
    String path = getParameterValue(subParameterList, VALUE_PATH);
    URL resourceURL = getClass().getClassLoader().getResource(path);
    CharmCache.getInstance().registerCharmFile(type, rules, resourceURL);
  }

  private String getParameterValue(List<Element> parameters, String id) {
    for (Element parameter : parameters) {
      String idValue = parameter.attributeValue(ATTRIB_ID);
      if (id.equals(idValue)) {
        return parameter.attributeValue(ATTRIB_VALUE);
      }
    }
    return null;
  }
}