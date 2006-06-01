package net.sf.anathema.character.reporting.sheet.common.magic;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.magic.CharmUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfMagicEncoder implements IPdfContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public PdfMagicEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    List<IMagic> printMagic = collectPrintMagic(character);
    new PdfMagicTableEncoder(resources, baseFont, printMagic).encodeTable(directContent, character, bounds);

  }

  private List<IMagic> collectPrintMagic(final IGenericCharacter character) {
    final CharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    final List<IMagic> printMagic = new ArrayList<IMagic>();
    for (IMagic magic : character.getAllLearnedMagic()) {
      magic.accept(new IMagicVisitor() {
        public void visitCharm(ICharm charm) {
          if (!CharmUtilities.isGenericCharmFor(charm, characterType)) {
            printMagic.add(charm);
          }
        }

        public void visitSpell(ISpell spell) {
          printMagic.add(spell);
        }
      });
    }
    return printMagic;
  }
}