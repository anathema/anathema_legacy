package net.sf.anathema.character.reporting.sheet.common.magic;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.magic.CharmUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.CharmStats;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.MultipleEffectCharmStats;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.SpellStats;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfMagicEncoder implements IPdfContentBoxEncoder {

  public static List<IMagicStats> collectPrintMagic(final IGenericCharacter character) {
    final ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    final List<IMagicStats> printStats = new ArrayList<IMagicStats>();
    for (IMagicStats stats : character.getGenericCharmStats()) {
      printStats.add(stats);
    }
    for (IMagic magic : character.getAllLearnedMagic()) {
      magic.accept(new IMagicVisitor() {
        public void visitCharm(ICharm charm) {
          if (CharmUtilities.isGenericCharmFor(charm, characterType)) {
            return;
          }
          if (character.isMultipleEffectCharm(charm)) {
            String[] effects = character.getLearnedEffects(charm);
            for (String effect : effects) {
              printStats.add(new MultipleEffectCharmStats(charm, effect));
            }
          }
          else {
            printStats.add(new CharmStats(charm, character));
          }
        }

        public void visitSpell(ISpell spell) {
          printStats.add(new SpellStats(spell, character.getRules().getEdition()));
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