package net.sf.anathema.hero.sheet.pdf.session;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentRegistry;
import net.sf.anathema.hero.sheet.pdf.content.SubContent;

import java.util.ArrayList;
import java.util.List;

public class ReportSession {

  private Hero hero;
  private final ReportContentRegistry registry;
  private final List<Object> mnemonics = new ArrayList<>();
  private PageBreakChecker pageBreakChecker = new NoPageBreak();

  public ReportSession(ReportContentRegistry registry, Hero hero) {
    this.registry = registry;
    this.hero = hero;
  }

  public Hero getHero() {
    return hero;
  }

  public void storeMnemonic(Object mnemonic) {
    mnemonics.add(mnemonic);
  }

  public <T> T retrieveMnemonic(Class<T> mnemonicClass) {
    for (Object mnemonic : mnemonics) {
      if (mnemonic.getClass() == mnemonicClass) {
        return (T) mnemonic;
      }
    }
    return null;
  }

  public <T> boolean knowsMnemonic(Class<T> mnemonicClass) {
    return retrieveMnemonic(mnemonicClass) != null;
  }

  public void setPageBreakChecker(PageBreakChecker checker) {
    this.pageBreakChecker = checker;
  }

  public boolean isPageBreakRequired() {
    return pageBreakChecker.isRequired();
  }

  public <C extends SubContent> C createContent(Class<C> contentClass) {
    ReportContentFactory<C> factory = registry.getFactory(contentClass);
    return factory.create(this);
  }
}
