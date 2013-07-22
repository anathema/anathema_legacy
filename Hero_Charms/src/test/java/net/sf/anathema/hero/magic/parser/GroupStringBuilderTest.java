package net.sf.anathema.hero.magic.parser;

import net.sf.anathema.character.main.magic.charm.CharmException;
import net.sf.anathema.character.main.magic.parser.charms.GroupStringBuilder;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.character.main.traits.types.ValuedTraitType;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GroupStringBuilderTest {

  private GroupStringBuilder builder = new GroupStringBuilder();

  @Test
  public void testBuildGroupFromAttribute() throws Exception {
    String xml = "<charm group=\"group\"/>";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String id = builder.build(rootElement, null);
    assertEquals("group", id);
  }

  @Test
  public void testBuildGroupFromPrimaryTrait() throws Exception {
    String xml = "<charm />";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String id = builder.build(rootElement, new ValuedTraitType(AttributeType.Appearance, 5));
    assertEquals("Appearance", id);
  }

  @Test(expected = CharmException.class)
  public void testBuildGroupWithoutPrimaryTrait() throws Exception {
    String xml = "<charm />";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    builder.build(rootElement, null);
  }
}