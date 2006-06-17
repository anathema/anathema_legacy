package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.essence.EssenceTemplateParser;
import net.sf.anathema.character.generic.framework.xml.essence.GenericEssenceTemplate;
import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.test.character.dummy.DummyGenericTrait;
import net.sf.anathema.test.character.dummy.DummyXmlTemplateRegistry;

import org.dom4j.Document;

public class EssenceTemplateParserTest extends BasicTestCase {

  private static final String TESTID = "TestDocument"; //$NON-NLS-1$

  private static Document createTestDocument() throws AnathemaException {
    String xml = "<essence isEssenceUser=\"true\">" //$NON-NLS-1$
        + "<personalPool>" //$NON-NLS-1$
        + "   <essencePart multiplier=\"3\" />" //$NON-NLS-1$
        + "   <willpowerPart multiplier=\"1\" />" //$NON-NLS-1$
        + "</personalPool>" //$NON-NLS-1$
        + "<peripheralPool>" //$NON-NLS-1$
        + "   <essencePart multiplier=\"7\" />" //$NON-NLS-1$
        + "   <willpowerPart multiplier=\"1\" />" //$NON-NLS-1$
        + "   <virtuePart>" //$NON-NLS-1$
        + "       <virtueRanks count=\"4\" multiplier=\"1\" />" //$NON-NLS-1$
        + "   </virtuePart>" //$NON-NLS-1$
        + "</peripheralPool>" //$NON-NLS-1$
        + "</essence>"; //$NON-NLS-1$
    return DocumentUtilities.read(xml);
  }

  private static Document createUsesTestDocument() throws AnathemaException {
    String xml = "<essence uses=\"" + TESTID + "\"/>"; //$NON-NLS-1$ //$NON-NLS-2$
    return DocumentUtilities.read(xml);
  }

  private DummyXmlTemplateRegistry<GenericEssenceTemplate> templateRegistry;

  public void testEssenceUser() throws Exception {
    Document testDocument = createTestDocument();
    IEssenceTemplate essenceTemplate = createEssenceTemplateParser().parseTemplate(testDocument.getRootElement());
    assertTrue(essenceTemplate.isEssenceUser());
  }

  @Override
  protected void setUp() throws Exception {
    templateRegistry = new DummyXmlTemplateRegistry<GenericEssenceTemplate>();
  }

  private EssenceTemplateParser createEssenceTemplateParser() {
    return new EssenceTemplateParser(templateRegistry);
  }

  public void testPersonalPool() throws Exception {
    Document testDocument = createTestDocument();
    IEssenceTemplate essenceTemplate = createEssenceTemplateParser().parseTemplate(testDocument.getRootElement());
    IGenericTrait[] testVirtues = new IGenericTrait[] {
        new DummyGenericTrait(VirtueType.Compassion, 2),
        new DummyGenericTrait(VirtueType.Temperance, 3),
        new DummyGenericTrait(VirtueType.Valor, 1),
        new DummyGenericTrait(VirtueType.Conviction, 4) };
    DummyGenericTrait testWillpower = new DummyGenericTrait(OtherTraitType.Willpower, 2);
    FactorizedTrait[] personalTraits = essenceTemplate.getPersonalTraits(
        testWillpower,
        testVirtues,
        new DummyGenericTrait(OtherTraitType.Essence, 3));
    assertEquals(9, personalTraits[0].getCalculateTotal());
    assertEquals(2, personalTraits[1].getCalculateTotal());
    assertEquals(2, personalTraits.length);
  }

  public void testPeripheralPool() throws Exception {
    Document testDocument = createTestDocument();
    IEssenceTemplate essenceTemplate = createEssenceTemplateParser().parseTemplate(testDocument.getRootElement());
    IGenericTrait[] testVirtues = new IGenericTrait[] {
        new DummyGenericTrait(VirtueType.Compassion, 2),
        new DummyGenericTrait(VirtueType.Temperance, 3),
        new DummyGenericTrait(VirtueType.Valor, 1),
        new DummyGenericTrait(VirtueType.Conviction, 4) };
    DummyGenericTrait testWillpower = new DummyGenericTrait(OtherTraitType.Willpower, 2);
    FactorizedTrait[] peripheralTraits = essenceTemplate.getPeripheralTraits(
        testWillpower,
        testVirtues,
        new DummyGenericTrait(OtherTraitType.Essence, 3));
    assertEquals(21, peripheralTraits[0].getCalculateTotal());
    assertEquals(2, peripheralTraits[1].getCalculateTotal());
    assertEquals(10, peripheralTraits[2].getCalculateTotal());
  }

  public void testUses() throws Exception {
    Document document = createTestDocument();
    EssenceTemplateParser essenceTemplateParser = createEssenceTemplateParser();
    IEssenceTemplate referencedTemplate = essenceTemplateParser.parseTemplate(document.getRootElement());
    templateRegistry.register(TESTID, (GenericEssenceTemplate) referencedTemplate);
    Document usesDocument = createUsesTestDocument();
    IEssenceTemplate templateUnderTest = essenceTemplateParser.parseTemplate(usesDocument.getRootElement());
    assertEquals(referencedTemplate, templateUnderTest);
  }
}