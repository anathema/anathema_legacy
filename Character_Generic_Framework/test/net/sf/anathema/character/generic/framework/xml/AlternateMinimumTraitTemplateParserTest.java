package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.trait.GenericRestrictedTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.alternate.AlternateMinimumTraitTemplateParser;
import net.sf.anathema.character.generic.traits.groups.AllAbilityTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

public class AlternateMinimumTraitTemplateParserTest extends BasicTestCase {

  public void test() throws Exception {
    String xml = "<alternateMinimumTraits count=\"1\" value=\"1\">" //$NON-NLS-1$
        + "           <trait id=\"Archery\" startValue=\"0\" lowerableState=\"Default\" zeroLevel=\"0\">" //$NON-NLS-1$
        + "               <limitation type=\"Essence\"/>" //$NON-NLS-1$
        + "               <minimum value=\"0\"/>" //$NON-NLS-1$
        + "           </trait>" //$NON-NLS-1$
        + "           <trait id=\"Brawl\" startValue=\"1\" lowerableState=\"Default\" zeroLevel=\"0\">" //$NON-NLS-1$
        + "               <limitation type=\"Essence\"/>" //$NON-NLS-1$
        + "               <minimum value=\"0\"/>" //$NON-NLS-1$
        + "           </trait>" //$NON-NLS-1$
        + "       </alternateMinimumTraits>"; //$NON-NLS-1$
    AlternateMinimumTraitTemplateParser parser = new AlternateMinimumTraitTemplateParser(
        AllAbilityTraitTypeGroup.getInstance());
    GenericRestrictedTraitTemplate[] templates = parser.parseAlternateMinimumTraits(DocumentUtilities.read(xml)
        .getRootElement());
    assertEquals(2, templates.length);
    assertEquals(AbilityType.Archery, templates[0].getTraitType());
    assertEquals(AbilityType.Brawl, templates[1].getTraitType());
    assertEquals(1, templates[1].getStartValue());
  }
}