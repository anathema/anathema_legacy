package net.sf.anathema.acceptance.fixture.character;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

import net.sf.anathema.character.generic.impl.magic.persistence.CharmCompiler;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class AcceptanceCharmCompiling {

  private static final String TAG_EXTENSION = "extension"; //$NON-NLS-1$
  private static final String ATTRIB_POINT_ID = "point-id"; //$NON-NLS-1$
  private static final String VALUE_CHARMLIST = "CharmList"; //$NON-NLS-1$
  private static final String TAG_PARAMETER = "parameter"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_VALUE = "value"; //$NON-NLS-1$
  private static final String VALUE_TYPE = "type"; //$NON-NLS-1$
  private static final String VALUE_RULES = "rules"; //$NON-NLS-1$
  private static final String VALUE_PATH = "path"; //$NON-NLS-1$

  public void registerCharmFiles() throws FileNotFoundException, AnathemaException {
    CharmCompiler charmCompiler = new CharmCompiler();
    registerCharmFile(charmCompiler, new File("../Character_Main/resources/plugin.xml")); //$NON-NLS-1$
    registerCharmFile(charmCompiler, new File("../Character_Abyssal/resources/plugin-fragment.xml")); //$NON-NLS-1$
    registerCharmFile(charmCompiler, new File("../Character_Lunar/resources/plugin-fragment.xml")); //$NON-NLS-1$
    registerCharmFile(charmCompiler, new File("../Character_Sidereal/resources/plugin-fragment.xml")); //$NON-NLS-1$
    registerCharmFile(charmCompiler, new File("../Character_Solar/resources/plugin-fragment.xml")); //$NON-NLS-1$
    registerCharmFile(charmCompiler, new File("../Character_Db/resources/plugin-fragment.xml")); //$NON-NLS-1$
    registerCharmFile(charmCompiler, new File("../Character_MartialArts/resources/plugin-fragment.xml")); //$NON-NLS-1$
    charmCompiler.buildCharms();
  }

  private void registerCharmFile(CharmCompiler charmCompiler, File xmlFile)
      throws FileNotFoundException,
      AnathemaException {
    Element root = DocumentUtilities.read(xmlFile).getRootElement();
    for (Element element : ElementUtilities.elements(root, TAG_EXTENSION)) {
      String pointId = element.attributeValue(ATTRIB_POINT_ID);
      if (VALUE_CHARMLIST.equals(pointId)) {
        for (Element parameterElement : ElementUtilities.elements(element, TAG_PARAMETER)) {
          registerCharmFile(parameterElement, charmCompiler);
        }
      }
    }
  }

  private void registerCharmFile(Element parent, CharmCompiler charmCompiler) throws CharmException {
    List<Element> subParameterList = ElementUtilities.elements(parent, TAG_PARAMETER);
    String type = getParameterValue(subParameterList, VALUE_TYPE);
    String rules = getParameterValue(subParameterList, VALUE_RULES);
    String path = getParameterValue(subParameterList, VALUE_PATH);
    URL resourceURL = getClass().getClassLoader().getResource(path);
    charmCompiler.registerCharmFile(type, rules, resourceURL);
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
