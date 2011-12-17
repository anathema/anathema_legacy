package net.sf.anathema.character.reporting.extended.common.magic;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.magic.CharmUtilities;
import net.sf.anathema.character.generic.magic.*;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.extended.util.IPdfTableEncoder;
import net.sf.anathema.character.reporting.stats.magic.CharmStats;
import net.sf.anathema.character.reporting.stats.magic.MultipleEffectCharmStats;
import net.sf.anathema.character.reporting.stats.magic.SpellStats;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.ArrayList;
import java.util.List;

public class PdfMagicEncoder implements IPdfContentBoxEncoder
{
  static IIdentificate KNACK = new Identificate("Knack");
  
  public static List<IMagicStats> collectPrintCharms(final IGenericCharacter character) {
    return collectPrintMagic(character, false, true);
  }
  
  public static List<IMagicStats> collectPrintMagic(final IGenericCharacter character) {
    return collectPrintMagic(character, true, true);
  }
  
  public static List<IMagicStats> collectPrintSpells(final IGenericCharacter character) {
    return collectPrintMagic(character, true, false);
  }

  private static List<IMagicStats> collectPrintMagic(final IGenericCharacter character,
                                                     final boolean includeSpells,
                                                     final boolean includeCharms) {
    final List<IMagicStats> printStats = new ArrayList<IMagicStats>();
    if (includeCharms) {
      for (IMagicStats stats : character.getGenericCharmStats()) {
        printStats.add(stats);
      }
    }
    
    IMagicVisitor statsCollector = new IMagicVisitor(){
      public void visitCharm(ICharm charm) {
        if (!includeCharms) {
          return;
        }
        if (CharmUtilities.isGenericCharmFor(charm, character)) {
          return;
        }
        if (charm.hasAttribute(KNACK))
          return;
        
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
        if (includeSpells) {
          printStats.add(new SpellStats(spell, character.getRules().getEdition()));
        }
      }
    };
    for (IMagic magic : character.getAllLearnedMagic()) {
      magic.accept(statsCollector);
    }
    return printStats;
  }

  private final PdfMagicTableEncoder tableEncoder;
  private final List<IPdfTableEncoder> additionalTables;
  private final String headerKey;

  public PdfMagicEncoder(IResources resources, BaseFont baseFont, List<IMagicStats> printMagic) {
    this(resources, baseFont, printMagic, new ArrayList<IPdfTableEncoder>(), false, "Charms"); //$NON-NLS-1$
  }

  public PdfMagicEncoder(IResources resources, BaseFont baseFont,
                         List<IMagicStats> printMagic,
                         List<IPdfTableEncoder> additionalTables,
                         boolean sectionHeaderLines,
                         String headerKey) {
    this.tableEncoder = new PdfMagicTableEncoder(resources, baseFont, printMagic, sectionHeaderLines);
    this.additionalTables = additionalTables;
    this.headerKey = headerKey;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return headerKey; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character,
                     IGenericDescription description, Bounds bounds)
      throws DocumentException {
    float top = bounds.getMinY();
    for (IPdfTableEncoder additionalTable : additionalTables) {
      if (additionalTable.hasContent(character)) {
        Bounds tableBounds = new Bounds(bounds.getMinX(), top,
                                        bounds.getWidth(), bounds.getMaxY() - top);
        float tableHeight = additionalTable.encodeTable(directContent, character, tableBounds);
        top += tableHeight + IVoidStateFormatConstants.PADDING;
      }
    }
    
    Bounds remainingBounds = new Bounds(bounds.getMinX(), top,
                                        bounds.getWidth(), bounds.getMaxY() - top);
    tableEncoder.encodeTable(directContent, character, remainingBounds);
  }
  
  public boolean hasContent(IGenericCharacter character) {
	  return true;
  }
}