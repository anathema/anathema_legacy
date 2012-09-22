package net.sf.anathema.character.reporting.pdf.util;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import net.sf.anathema.character.generic.magic.IMagic;

import java.util.List;

public class MagicLearnUtilities {

  public static boolean isCharmLearned(List<IMagic> allLearnedMagic, final String charmId) {
    Optional<? extends IMagic> optional = Iterables.tryFind(allLearnedMagic, new Predicate<IMagic>() {
      @Override
      public boolean apply(IMagic value) {
        return charmId.equals(value.getId());
      }
    });
    return optional.isPresent();
  }
}
