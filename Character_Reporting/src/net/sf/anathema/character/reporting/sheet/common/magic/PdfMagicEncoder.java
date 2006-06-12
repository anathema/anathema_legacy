package net.sf.anathema.character.reporting.sheet.common.magic;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.magic.CharmUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.magic.generic.solar.EssenceFlow;
import net.sf.anathema.character.reporting.sheet.common.magic.generic.solar.FirstExcellency;
import net.sf.anathema.character.reporting.sheet.common.magic.generic.solar.InfiniteMastery;
import net.sf.anathema.character.reporting.sheet.common.magic.generic.solar.SecondExcellency;
import net.sf.anathema.character.reporting.sheet.common.magic.generic.solar.ThirdExcellency;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.CharmStats;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.IMagicStats;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.SpellStats;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfMagicEncoder implements IPdfContentEncoder {

  public static List<IMagicStats> collectPrintMagic(final IGenericCharacter character) {
    final IExaltedEdition edition = character.getRules().getEdition();
    final CharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    final List<IMagicStats> printStats = new ArrayList<IMagicStats>();
    printStats.add(new FirstExcellency());
    printStats.add(new SecondExcellency());
    printStats.add(new ThirdExcellency());
    printStats.add(new InfiniteMastery());
    printStats.add(new EssenceFlow());
    for (IMagic magic : character.getAllLearnedMagic()) {
      magic.accept(new IMagicVisitor() {
        public void visitCharm(ICharm charm) {
          if (CharmUtilities.isGenericCharmFor(charm, characterType)) {
            return;
          }
          if (character.isMultipleEffectCharm(charm)) {
            // character.getLearnedEffects(charm);
          }
          else {
            printStats.add(new CharmStats(charm, character));
          }
        }

        public void visitSpell(ISpell spell) {
          printStats.add(new SpellStats(spell, edition));
        }
      });
    }
    return printStats;
  }

  private final IResources resources;
  private final BaseFont baseFont;
  private final List<IMagicStats> printMagic;

  public PdfMagicEncoder(IResources resources, BaseFont baseFont, List<IMagicStats> printMagic) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.printMagic = printMagic;
  }

  public String getHeaderKey() {
    return "Charms"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    new PdfMagicTableEncoder(resources, baseFont, printMagic).encodeTable(directContent, character, bounds);
  }
}