package net.sf.anathema.hero.template.parser;

import junit.framework.TestCase;
import net.sf.anathema.character.main.template.essence.FactorizedTrait;
import net.sf.anathema.character.main.template.essence.IEssenceTemplate;
import net.sf.anathema.hero.dummy.DummyGenericTrait;
import net.sf.anathema.hero.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.character.main.xml.essence.EssenceTemplateParser;
import net.sf.anathema.character.main.xml.essence.GenericEssenceTemplate;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Document;

public class EssenceTemplateParserTest extends TestCase {

  private static final String TESTID = "TestDocument";

  private static Document createTestDocument() throws AnathemaException {
    String xml =
            "<essence isEssenceUser=\"true\">" + "<personalPool>" + "   <essencePart multiplier=\"3\" />" + "   <willpowerPart multiplier=\"1\" />" +
            "</personalPool>" + "<peripheralPool>" + "   <essencePart multiplier=\"7\" />" + "   <willpowerPart multiplier=\"1\" />" +
            "   <virtuePart>" + "       <virtueRanks count=\"4\" multiplier=\"1\" />" + "   </virtuePart>" + "</peripheralPool>" + "</essence>";
    return DocumentUtilities.read(xml);
  }

  private static Document createUsesTestDocument() throws AnathemaException {
    String xml = "<essence uses=\"" + TESTID + "\"/>";
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
    templateRegistry = new DummyXmlTemplateRegistry<>();
  }

  private EssenceTemplateParser createEssenceTemplateParser() {
    return new EssenceTemplateParser(templateRegistry);
  }

  public void testPersonalPool() throws Exception {
    Document testDocument = createTestDocument();
    IEssenceTemplate essenceTemplate = createEssenceTemplateParser().parseTemplate(testDocument.getRootElement());
    ValuedTraitType[] testVirtues =
            new ValuedTraitType[]{new DummyGenericTrait(VirtueType.Compassion, 2), new DummyGenericTrait(VirtueType.Temperance, 3),
                    new DummyGenericTrait(VirtueType.Valor, 1), new DummyGenericTrait(VirtueType.Conviction, 4)};
    DummyGenericTrait testWillpower = new DummyGenericTrait(OtherTraitType.Willpower, 2);
    FactorizedTrait[] personalTraits =
            essenceTemplate.getPersonalTraits(testWillpower, testVirtues, new DummyGenericTrait(OtherTraitType.Essence, 3));
    assertEquals(9, personalTraits[0].getCalculateTotal());
    assertEquals(2, personalTraits[1].getCalculateTotal());
    assertEquals(2, personalTraits.length);
  }

  public void testPeripheralPool() throws Exception {
    Document testDocument = createTestDocument();
    IEssenceTemplate essenceTemplate = createEssenceTemplateParser().parseTemplate(testDocument.getRootElement());
    ValuedTraitType[] testVirtues =
            new ValuedTraitType[]{new DummyGenericTrait(VirtueType.Compassion, 2), new DummyGenericTrait(VirtueType.Temperance, 3),
                    new DummyGenericTrait(VirtueType.Valor, 1), new DummyGenericTrait(VirtueType.Conviction, 4)};
    DummyGenericTrait testWillpower = new DummyGenericTrait(OtherTraitType.Willpower, 2);
    FactorizedTrait[] peripheralTraits =
            essenceTemplate.getPeripheralTraits(testWillpower, testVirtues, new DummyGenericTrait(OtherTraitType.Essence, 3));
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