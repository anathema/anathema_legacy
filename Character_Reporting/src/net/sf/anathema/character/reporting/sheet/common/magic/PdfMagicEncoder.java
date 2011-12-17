package net.sf.anathema.character.reporting.sheet.common.magic;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.magic.CharmUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.stats.magic.CharmStats;
import net.sf.anathema.character.reporting.common.stats.magic.MultipleEffectCharmStats;
import net.sf.anathema.character.reporting.common.stats.magic.SpellStats;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.ArrayList;
import java.util.List;

public class PdfMagicEncoder implements IPdfContentBoxEncoder {
  static IIdentificate KNACK = new Identificate("Knack");

  public static List<IMagicStats> collectPrintMagic(final IGenericCharacter character) {
    final List<IMagicStats> printStats = new ArrayList<IMagicStats>();
    for (IMagicStats stats : character.getGenericCharmStats()) {
      printStats.add(stats);
    }
    for (IMagic magic : character.getAllLearnedMagic()) {
      magic.accept(new IMagicVisitor() {
        public void visitCharm(ICharm charm) {
          if (CharmUtilities.isGenericCharmFor(charm, character)) {
            return;
          }
          if (charm.hasAttribute(KNACK)) {
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

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Charms"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description,
                     Bounds bounds) throws DocumentException {
    new PdfMagicTableEncoder(resources, baseFont, printMagic).encodeTable(directContent, character, bounds);
  }

  public boolean hasContent(IGenericCharacter character) {
    return true;
  }
}
