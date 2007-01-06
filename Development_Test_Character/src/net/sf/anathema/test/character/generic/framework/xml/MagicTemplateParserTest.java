package net.sf.anathema.test.character.generic.framework.xml;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.db.template.TerrestrialMartialArtsRules;
import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplate;
import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplateParser;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.dummy.character.DummyCasteType;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.dummy.character.template.DummyXmlTemplateRegistry;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;
import org.junit.Assert;
import org.junit.Test;

public class MagicTemplateParserTest extends BasicTestCase {
  String xml = "<magicTemplate>" //$NON-NLS-1$
      + "<freePicksPredicate type=\"Default\"/>"//$NON-NLS-1$
      + "<charmTemplate charmType=\"None\">" //$NON-NLS-1$
      + "<martialArts level=\"Mortal\"/></charmTemplate>" //$NON-NLS-1$
      + "<spellTemplate maximumSorceryCircle=\"None\" maximumNecromancyCircle=\"None\"/>" //$NON-NLS-1$
      + "</magicTemplate>"; //$NON-NLS-1$

  private DummyXmlTemplateRegistry<GenericMagicTemplate> templateRegistry;
  private GenericMagicTemplateParser parser;

  @Override
  protected void setUp() throws Exception {
    templateRegistry = new DummyXmlTemplateRegistry<GenericMagicTemplate>();
    parser = new GenericMagicTemplateParser(templateRegistry, ExaltedEdition.FirstEdition);
  }

  public void testDefaultFreePicksPredicate() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertTrue(template.canBuyFromFreePicks(new DummyCharm("TestCharm"))); //$NON-NLS-1$
  }

  public void testMortalCharmTemplate() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    ICharmTemplate charmTemplate = template.getCharmTemplate();
    assertEquals(MartialArtsLevel.Mortal, charmTemplate.getMartialArtsRules().getStandardLevel());
    assertFalse(charmTemplate.knowsCharms(ExaltedRuleSet.CoreRules));
  }

  public void testMortalSpellTemplate() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertFalse(template.getSpellMagic().knowsSpellMagic());
  }

  public void testParsesMaximumNecromancyCircle() throws Exception {
    String celestialXml = "<magicTemplate>" + //$NON-NLS-1$
        "<spellTemplate maximumSorceryCircle=\"Solar\" maximumNecromancyCircle=\"Labyrinth\"/>" //$NON-NLS-1$
        + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertTrue(ArrayUtilities.contains(template.getSpellMagic().getNecromancyCircles(), CircleType.Shadowlands));
    assertTrue(ArrayUtilities.contains(template.getSpellMagic().getNecromancyCircles(), CircleType.Labyrinth));
    assertFalse(ArrayUtilities.contains(template.getSpellMagic().getNecromancyCircles(), CircleType.Void));
  }

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
  public void testTerrestrialRulesSetting() throws Exception {
    String celestialXml = "<magicTemplate>" + //$NON-NLS-1$
        "<charmTemplate charmType=\"None\" ><martialArts rulesClass=\"net.sf.anathema.character.db.template.TerrestrialMartialArtsRules\" level=\"Terrestrial\" /></charmTemplate>" //$NON-NLS-1$
        + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    Assert.assertTrue(template.getCharmTemplate().getMartialArtsRules() instanceof TerrestrialMartialArtsRules);
  }

  public void testFavoringTraitTypeUnmodified() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertEquals(FavoringTraitType.AbilityType, template.getFavoringTraitType());
  }

  public void testFavoringTraitTypeModified() throws Exception {
    String typeXml = "<magicTemplate>" //$NON-NLS-1$
        + "<freePicksPredicate type=\"Default\"/>"//$NON-NLS-1$
        + "<favoringTraitType type =\"AttributeType\"/>" //$NON-NLS-1$
        + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(typeXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertEquals(FavoringTraitType.AttributeType, template.getFavoringTraitType());
  }

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