package net.sf.anathema.character.generic.impl.magic.persistence.test;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class GenericCharmBuilderTest extends BasicTestCase {

  private GenericCharmBuilder builder = new GenericCharmBuilder();

  public void testReadGenericCharmId() throws Exception {
    String xml = "<genericCharm id=\"Solar.Generic\" />"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    Charm charm = builder.buildCharm(rootElement, AbilityType.Archery);
    assertEquals("Solar.GenericArchery", charm.getId()); //$NON-NLS-1$
  }

  public void testReadGenericCharmGroup() throws Exception {
    String xml = "<genericCharm id=\"Solar.Generic\" />"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    Charm charm = builder.buildCharm(rootElement, AbilityType.Archery);
    assertEquals("Archery", charm.getGroupId()); //$NON-NLS-1$
  }

  public void testReadGenericPrerequisiteFirst() throws Exception {
    String xml = "<genericCharm id=\"Solar.Generic\">" //$NON-NLS-1$
        + "<prerequisites>" //$NON-NLS-1$
        + "<trait value=\"1\"/>" //$NON-NLS-1$
        + "</prerequisites>" //$NON-NLS-1$
        + "</genericCharm>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    Charm charm = builder.buildCharm(rootElement, AbilityType.Archery);
    assertEquals(AbilityType.Archery, charm.getPrerequisites()[0].getType());
  }

  public void testReadGenericPrerequisiteSecond() throws Exception {
    String xml = "<genericCharm id=\"Solar.Generic\">" //$NON-NLS-1$
        + "<prerequisites>" //$NON-NLS-1$
        + "<trait value=\"1\"/>" //$NON-NLS-1$
        + "</prerequisites>" //$NON-NLS-1$
        + "</genericCharm>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    Charm charm = builder.buildCharm(rootElement, AbilityType.Athletics);
    assertEquals(AbilityType.Athletics, charm.getPrerequisites()[0].getType());
  }

  // Fails
  public void testReadGenericPrerequisiteHigherValue() throws Exception {
    String xml = "<genericCharm id=\"Solar.Generic\">" //$NON-NLS-1$
        + "<prerequisites>" //$NON-NLS-1$
        + "<trait value=\"3\"/>" //$NON-NLS-1$
        + "</prerequisites>" //$NON-NLS-1$
        + "</genericCharm>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    Charm charm = builder.buildCharm(rootElement, AbilityType.Awareness);
    assertEquals(AbilityType.Awareness, charm.getPrerequisites()[0].getType());
    assertEquals(3, charm.getPrerequisites()[0].getCurrentValue());
  }
}