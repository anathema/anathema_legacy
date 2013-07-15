package net.sf.anathema.hero.sheet.text;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.MultiColumnText;
import net.sf.anathema.hero.model.Hero;

public interface HeroTextEncoder {

  void createParagraphs(MultiColumnText columnText, Hero hero) throws DocumentException;
}
