package net.sf.anathema.hero.template.parser;

import junit.framework.TestCase;
import net.sf.anathema.character.main.traits.groups.AllAbilityTraitTypeGroup;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.xml.trait.GenericRestrictedTraitTemplate;
import net.sf.anathema.character.main.xml.trait.alternate.AlternateMinimumTraitTemplateParser;
import net.sf.anathema.lib.xml.DocumentUtilities;

public class AlternateMinimumTraitTemplateParserTest extends TestCase {

  public void test() throws Exception {
    String xml = "<alternateMinimumTraits count=\"1\" value=\"1\">" +
                 "           <trait id=\"Archery\" startValue=\"0\" lowerableState=\"Default\" zeroLevel=\"0\">" +
                 "               <limitation type=\"Essence\"/>" + "               <minimum value=\"0\"/>" + "           </trait>" +
                 "           <trait id=\"MartialArts\" startValue=\"1\" lowerableState=\"Default\" zeroLevel=\"0\">" +
                 "               <limitation type=\"Essence\"/>" + "               <minimum value=\"0\"/>" + "           </trait>" +
                 "       </alternateMinimumTraits>";
    AlternateMinimumTraitTemplateParser parser = new AlternateMinimumTraitTemplateParser(AllAbilityTraitTypeGroup.getInstance());
    GenericRestrictedTraitTemplate[] templates = parser.parseAlternateMinimumTraits(DocumentUtilities.read(xml).getRootElement());
    assertEquals(2, templates.length);
    assertEquals(AbilityType.Archery, templates[0].getTraitType());
    assertEquals(AbilityType.MartialArts, templates[1].getTraitType());
    assertEquals(1, templates[1].getStartValue());
  }
}