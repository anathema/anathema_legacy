package net.sf.anathema.character.generic.framework.xml.magic.test;

import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplate;
import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.test.DummyXmlTemplateRegistry;
import net.sf.anathema.character.generic.impl.magic.test.DummyMartialArtsCharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class MagicTemplateParserTest extends BasicTestCase {
  String xml = "<magicTemplate>" //$NON-NLS-1$
      + "<freePicksPredicate type=\"Default\"/>"//$NON-NLS-1$
      + "<charmTemplate martialArtsLevel=\"Mortal\" charmType=\"None\"/>" //$NON-NLS-1$
      + "<spellTemplate maximumSorceryCircle=\"None\" maximumNecromancyCircle=\"None\"/>" //$NON-NLS-1$
      + "</magicTemplate>"; //$NON-NLS-1$

  private DummyXmlTemplateRegistry<GenericMagicTemplate> templateRegistry;
  private GenericMagicTemplateParser parser;

  @Override
  protected void setUp() throws Exception {
    templateRegistry = new DummyXmlTemplateRegistry<GenericMagicTemplate>();
    parser = new GenericMagicTemplateParser(templateRegistry);
  }

  public void testDefaultFreePicksPredicate() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertTrue(template.canBuyFromFreePicks(new DummyMartialArtsCharm("TestCharm"))); //$NON-NLS-1$
  }

  public void testMortalCharmTemplate() throws Exception {
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    ICharmTemplate charmTemplate = template.getCharmTemplate();
    assertEquals(MartialArtsLevel.Mortal, charmTemplate.getMartialArtsLevel());
    assertFalse(charmTemplate.knowsCharms());
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
        "<charmTemplate martialArtsLevel=\"Terrestrial\" charmType=\"None\"/>" //$NON-NLS-1$
        + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertFalse(template.getCharmTemplate().isMartialArtsCharmAllowed(new DummyMartialArtsCharm("Dummy"), null, false)); //$NON-NLS-1$
  }

  public void testHighLevelSettingModified() throws Exception {
    String celestialXml = "<magicTemplate>" + //$NON-NLS-1$
        "<charmTemplate martialArtsLevel=\"Terrestrial\" charmType=\"None\" highLevelMartialArts=\"true\"/>" //$NON-NLS-1$
        + "</magicTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(celestialXml).getRootElement();
    GenericMagicTemplate template = parser.parseTemplate(templateElement);
    assertTrue(template.getCharmTemplate().isMartialArtsCharmAllowed(new DummyMartialArtsCharm("Dummy"), null, false)); //$NON-NLS-1$
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

}