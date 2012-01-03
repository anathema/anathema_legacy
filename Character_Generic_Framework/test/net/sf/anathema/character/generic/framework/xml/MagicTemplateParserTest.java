package net.sf.anathema.character.generic.framework.xml;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.dummy.DummyCasteType;
import net.sf.anathema.character.generic.dummy.magic.DummyMartialArtsRules;
import net.sf.anathema.character.generic.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplate;
import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplateParser;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.dummy.character.magic.DummyCharmUtilities;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MagicTemplateParserTest {

  String xml = "<magicTemplate>" //$NON-NLS-1$
    + "<freePicksPredicate defaultResponse=\"true\"/>" //$NON-NLS-1$
    + "<charmTemplate charmType=\"None\">" //$NON-NLS-1$
    + "<martialArts level=\"Mortal\"/></charmTemplate>" //$NON-NLS-1$
    + "<spellTemplate maximumSorceryCircle=\"None\" maximumNecromancyCircle=\"None\"/>" //$NON-NLS-1$
    + "</magicTemplate>"; //$NON-NLS-1$
  private DummyXmlTemplateRegistry<GenericMagicTemplate> templateRegistry;
  private GenericMagicTemplateParser parser;

  @Before
  public void setUp() throws Exception {
    templateRegistry = new DummyXmlTemplateRegistry<GenericMagicTemplate>();
    parser = new GenericMagicTemplateParser(templateRegistry, null);
  }

  @Test
  public void testDefaultFreePicksPredicate() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertTrue(template.canBuyFromFreePicks(DummyCharmUtilities.createCharm("TestCharm", "Group"))); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void testFalseFreePicksPredicate() throws Exception {
    String customXml = "<magicTemplate>" //$NON-NLS-1$
      + "<freePicksPredicate defaultResponse=\"false\"/>" //$NON-NLS-1$
      + "<charmTemplate charmType=\"None\">" //$NON-NLS-1$
      + "<martialArts level=\"Mortal\"/></charmTemplate>" //$NON-NLS-1$
      + "<spellTemplate maximumSorceryCircle=\"None\" maximumNecromancyCircle=\"None\"/>" //$NON-NLS-1$
      + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(customXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertFalse(template.canBuyFromFreePicks(DummyCharmUtilities.createCharm("TestCharm", "Group"))); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void testIdExceptionInFreePicksPredicate() throws Exception {
    String customXml = "<magicTemplate>" //$NON-NLS-1$
      + "<freePicksPredicate defaultResponse=\"false\"><idException id=\"ExpectedId\"/></freePicksPredicate>" //$NON-NLS-1$
      + "<charmTemplate charmType=\"None\">" //$NON-NLS-1$
      + "<martialArts level=\"Mortal\"/></charmTemplate>" //$NON-NLS-1$
      + "<spellTemplate maximumSorceryCircle=\"None\" maximumNecromancyCircle=\"None\"/>" //$NON-NLS-1$
      + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(customXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertFalse(template.canBuyFromFreePicks(DummyCharmUtilities.createCharm("BadId", "Group"))); //$NON-NLS-1$ //$NON-NLS-2$
    assertTrue(template.canBuyFromFreePicks(DummyCharmUtilities.createCharm("ExpectedId", "Group"))); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void testGroupExceptionInFreePicksPredicate() throws Exception {
    String customXml = "<magicTemplate>" //$NON-NLS-1$
      + "<freePicksPredicate defaultResponse=\"false\"><groupException id=\"ExpectedGroup\"/></freePicksPredicate>" //$NON-NLS-1$
      + "<charmTemplate charmType=\"None\">" //$NON-NLS-1$
      + "<martialArts level=\"Mortal\"/></charmTemplate>" //$NON-NLS-1$
      + "<spellTemplate maximumSorceryCircle=\"None\" maximumNecromancyCircle=\"None\"/>" //$NON-NLS-1$
      + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(customXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertFalse(template.canBuyFromFreePicks(DummyCharmUtilities.createCharm("ExpectedId", "UnexpectedGroup"))); //$NON-NLS-1$ //$NON-NLS-2$
    assertTrue(template.canBuyFromFreePicks(DummyCharmUtilities.createCharm("ExpectedId", "ExpectedGroup"))); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void testMortalCharmTemplate() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    ICharmTemplate charmTemplate = template.getCharmTemplate();
    assertEquals(MartialArtsLevel.Mortal, charmTemplate.getMartialArtsRules().getStandardLevel());
    assertFalse(charmTemplate.canLearnCharms(ExaltedRuleSet.CoreRules));
  }

  @Test
  public void testMortalSpellTemplate() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertFalse(template.getSpellMagic().canLearnSpellMagic());
  }

  @Test
  public void testParsesMaximumNecromancyCircle() throws Exception {
    String celestialXml = "<magicTemplate>" + //$NON-NLS-1$
      "<spellTemplate maximumSorceryCircle=\"Solar\" maximumNecromancyCircle=\"Labyrinth\"/>" //$NON-NLS-1$
      + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertTrue(ArrayUtilities.containsValue(template.getSpellMagic().getNecromancyCircles(), CircleType.Shadowlands));
    assertTrue(ArrayUtilities.containsValue(template.getSpellMagic().getNecromancyCircles(), CircleType.Labyrinth));
    assertFalse(ArrayUtilities.containsValue(template.getSpellMagic().getNecromancyCircles(), CircleType.Void));
  }

  @Test
  public void testHighLevelSettingUnmodified() throws Exception {
    String celestialXml = "<magicTemplate>" + //$NON-NLS-1$
      "<charmTemplate charmType=\"None\"><martialArts level=\"Terrestrial\"/></charmTemplate>" //$NON-NLS-1$
      + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    DummyCharm dummyMartialArtsCharm = new DummyCharm("Dummy") { //$NON-NLS-1$
      @Override
      public boolean hasAttribute(IIdentificate attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Celestial"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    };
    assertFalse(template.getCharmTemplate().getMartialArtsRules().isCharmAllowed(dummyMartialArtsCharm, null, false));
  }

  @Test
  public void testHighLevelSettingModified() throws Exception {
    String celestialXml = "<magicTemplate>" + //$NON-NLS-1$
      "<charmTemplate charmType=\"None\" ><martialArts level=\"Terrestrial\" highLevel=\"true\"/></charmTemplate>" //$NON-NLS-1$
      + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    DummyCharm dummyMartialArtsCharm = new DummyCharm("Dummy") { //$NON-NLS-1$
      @Override
      public boolean hasAttribute(IIdentificate attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Celestial"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    };
    assertTrue(template.getCharmTemplate().getMartialArtsRules().isCharmAllowed(dummyMartialArtsCharm, null, false));
  }

  @Test
  public void testDefaultRulesSetting() throws Exception {
    String celestialXml = "<magicTemplate>" + //$NON-NLS-1$
      "<charmTemplate charmType=\"None\" ><martialArts level=\"Terrestrial\" /></charmTemplate>" //$NON-NLS-1$
      + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    Assert.assertTrue(template.getCharmTemplate().getMartialArtsRules() instanceof DefaultMartialArtsRules);
  }

  @Test
  public void picksUpMartialArtsRules() throws Exception {
    String celestialXml = "<magicTemplate>" + //$NON-NLS-1$
      "<charmTemplate charmType=\"None\" ><martialArts rulesClass=\"net.sf.anathema.character.generic.dummy.magic.DummyMartialArtsRules\" level=\"Terrestrial\" /></charmTemplate>" //$NON-NLS-1$
      + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    Assert.assertTrue(template.getCharmTemplate().getMartialArtsRules() instanceof DummyMartialArtsRules);
  }

  @Test
  public void testFavoringTraitTypeUnmodified() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertEquals(FavoringTraitType.AbilityType, template.getFavoringTraitType());
  }

  @Test
  public void testFavoringTraitTypeModified() throws Exception {
    String typeXml = "<magicTemplate>" //$NON-NLS-1$
      + "<freePicksPredicate type=\"Default\"/>"//$NON-NLS-1$
      + "<favoringTraitType type =\"AttributeType\"/>" //$NON-NLS-1$
      + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(typeXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertEquals(FavoringTraitType.AttributeType, template.getFavoringTraitType());
  }

  @Test
  public void testAlienCharmsAllowed() throws Exception {
    String typeXml = "<magicTemplate>" //$NON-NLS-1$
      + "<charmTemplate charmType=\"None\">" //$NON-NLS-1$
      + " <alienCharms> <caste type=\"DummyCaste\"/></alienCharms>" //$NON-NLS-1$
      + "<martialArts level=\"Celestial\" highLevel=\"false\" />" //$NON-NLS-1$
      + "</charmTemplate>" //$NON-NLS-1$
      + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(typeXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertTrue(template.getCharmTemplate().isAllowedAlienCharms(new DummyCasteType("DummyCaste"))); //$NON-NLS-1$
  }
}