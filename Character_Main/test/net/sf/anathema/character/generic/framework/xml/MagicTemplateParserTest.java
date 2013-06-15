package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.dummy.DummyCasteType;
import net.sf.anathema.character.generic.dummy.DummyCharacterTypes;
import net.sf.anathema.character.generic.dummy.magic.DummyMartialArtsRules;
import net.sf.anathema.character.generic.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplate;
import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplateParser;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.magic.dummy.DummyCharm;
import net.sf.anathema.character.magic.dummy.DummyCharmUtilities;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.apache.commons.lang3.ArrayUtils;
import org.dom4j.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MagicTemplateParserTest {

  String xml = "<magicTemplate>" + "<freePicksPredicate defaultResponse=\"true\"/>" + "<charmTemplate charmType=\"None\">" +
               "<martialArts level=\"Mortal\"/></charmTemplate>" + "<spellTemplate maximumSorceryCircle=\"None\" maximumNecromancyCircle=\"None\"/>" +
               "</magicTemplate>";
  private GenericMagicTemplateParser parser;

  @Before
  public void setUp() throws Exception {
    DummyXmlTemplateRegistry<GenericMagicTemplate> templateRegistry = new DummyXmlTemplateRegistry<>();
    parser = new GenericMagicTemplateParser(templateRegistry, null, null, new DummyCharacterTypes());
  }

  @Test
  public void testDefaultFreePicksPredicate() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertTrue(template.canBuyFromFreePicks(DummyCharmUtilities.createCharm("TestCharm", "Group")));
  }

  @Test
  public void testFalseFreePicksPredicate() throws Exception {
    String customXml = "<magicTemplate>" + "<freePicksPredicate defaultResponse=\"false\"/>" + "<charmTemplate charmType=\"None\">" +
                       "<martialArts level=\"Mortal\"/></charmTemplate>" +
                       "<spellTemplate maximumSorceryCircle=\"None\" maximumNecromancyCircle=\"None\"/>" + "</magicTemplate>";
    Element templateElement = DocumentUtilities.read(customXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertFalse(template.canBuyFromFreePicks(DummyCharmUtilities.createCharm("TestCharm", "Group")));
  }

  @Test
  public void testIdExceptionInFreePicksPredicate() throws Exception {
    String customXml = "<magicTemplate>" + "<freePicksPredicate defaultResponse=\"false\"><idException id=\"ExpectedId\"/></freePicksPredicate>" +
                       "<charmTemplate charmType=\"None\">" + "<martialArts level=\"Mortal\"/></charmTemplate>" +
                       "<spellTemplate maximumSorceryCircle=\"None\" maximumNecromancyCircle=\"None\"/>" + "</magicTemplate>";
    Element templateElement = DocumentUtilities.read(customXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertFalse(template.canBuyFromFreePicks(DummyCharmUtilities.createCharm("BadId", "Group")));
    assertTrue(template.canBuyFromFreePicks(DummyCharmUtilities.createCharm("ExpectedId", "Group")));
  }

  @Test
  public void testGroupExceptionInFreePicksPredicate() throws Exception {
    String customXml =
            "<magicTemplate>" + "<freePicksPredicate defaultResponse=\"false\"><groupException id=\"ExpectedGroup\"/></freePicksPredicate>" +
            "<charmTemplate charmType=\"None\">" + "<martialArts level=\"Mortal\"/></charmTemplate>" +
            "<spellTemplate maximumSorceryCircle=\"None\" maximumNecromancyCircle=\"None\"/>" + "</magicTemplate>";
    Element templateElement = DocumentUtilities.read(customXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertFalse(template.canBuyFromFreePicks(DummyCharmUtilities.createCharm("ExpectedId", "UnexpectedGroup")));
    assertTrue(template.canBuyFromFreePicks(DummyCharmUtilities.createCharm("ExpectedId", "ExpectedGroup")));
  }

  @Test
  public void testMortalCharmTemplate() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    ICharmTemplate charmTemplate = template.getCharmTemplate();
    assertEquals(MartialArtsLevel.Mortal, charmTemplate.getMartialArtsRules().getStandardLevel());
    assertFalse(charmTemplate.canLearnCharms());
  }

  @Test
  public void testMortalSpellTemplate() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertFalse(template.getSpellMagic().canLearnSpellMagic());
  }

  @Test
  public void testParsesMaximumNecromancyCircle() throws Exception {
    String celestialXml = "<magicTemplate>" +
                          "<spellTemplate maximumSorceryCircle=\"Solar\" maximumNecromancyCircle=\"Labyrinth\"/>" + "</magicTemplate>";
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertTrue(ArrayUtils.contains(template.getSpellMagic().getNecromancyCircles(), CircleType.Shadowlands));
    assertTrue(ArrayUtils.contains(template.getSpellMagic().getNecromancyCircles(), CircleType.Labyrinth));
    assertFalse(ArrayUtils.contains(template.getSpellMagic().getNecromancyCircles(), CircleType.Void));
  }

  @Test
  public void testHighLevelSettingUnmodified() throws Exception {
    String celestialXml = "<magicTemplate>" +
                          "<charmTemplate charmType=\"None\"><martialArts level=\"Terrestrial\"/></charmTemplate>" + "</magicTemplate>";
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    DummyCharm dummyMartialArtsCharm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identified attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Celestial");
      }
    };
    assertFalse(template.getCharmTemplate().getMartialArtsRules().isCharmAllowed(dummyMartialArtsCharm, null, false));
  }

  @Test
  public void testHighLevelSettingModified() throws Exception {
    String celestialXml = "<magicTemplate>" +
                          "<charmTemplate charmType=\"None\" ><martialArts level=\"Terrestrial\" highLevel=\"true\"/></charmTemplate>" +
                          "</magicTemplate>";
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    DummyCharm dummyMartialArtsCharm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identified attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Celestial");
      }
    };
    assertTrue(template.getCharmTemplate().getMartialArtsRules().isCharmAllowed(dummyMartialArtsCharm, null, false));
  }

  @Test
  public void testDefaultRulesSetting() throws Exception {
    String celestialXml = "<magicTemplate>" +
                          "<charmTemplate charmType=\"None\" ><martialArts level=\"Terrestrial\" /></charmTemplate>" + "</magicTemplate>";
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    Assert.assertTrue(template.getCharmTemplate().getMartialArtsRules() instanceof DefaultMartialArtsRules);
  }

  @Test
  public void picksUpMartialArtsRules() throws Exception {
    String celestialXml = "<magicTemplate>" +
                          "<charmTemplate charmType=\"None\" ><martialArts rulesClass=\"net.sf.anathema.character.generic.dummy.magic.DummyMartialArtsRules\" level=\"Terrestrial\" /></charmTemplate>" +
                          "</magicTemplate>";
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    Assert.assertTrue(template.getCharmTemplate().getMartialArtsRules() instanceof DummyMartialArtsRules);
  }

  @Test
  public void testAlienCharmsAllowed() throws Exception {
    String typeXml = "<magicTemplate>" + "<charmTemplate charmType=\"None\">" + " <alienCharms> <caste type=\"DummyCaste\"/></alienCharms>" +
                     "<martialArts level=\"Celestial\" highLevel=\"false\" />" + "</charmTemplate>" + "</magicTemplate>";
    Element templateElement = DocumentUtilities.read(typeXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertTrue(template.getCharmTemplate().isAllowedAlienCharms(new DummyCasteType("DummyCaste")));
  }
}