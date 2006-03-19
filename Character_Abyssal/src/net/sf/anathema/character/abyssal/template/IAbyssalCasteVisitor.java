package net.sf.anathema.character.abyssal.template;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;

public interface IAbyssalCasteVisitor extends ICasteTypeVisitor {

  void visitDusk(AbyssalCaste caste);

  void visitMidnight(AbyssalCaste caste);

  void visitDaybreak(AbyssalCaste caste);

  void visitDay(AbyssalCaste caste);

  void visitMoonshadow(AbyssalCaste caste);
}