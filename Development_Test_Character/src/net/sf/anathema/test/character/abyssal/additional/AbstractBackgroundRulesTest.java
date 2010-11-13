package net.sf.anathema.test.character.abyssal.additional;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.backgrounds.SimpleBackgroundTemplate;
import net.sf.anathema.character.generic.impl.magic.Spell;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.dummy.character.trait.DummyGenericTraitCollection;
import net.sf.anathema.lib.testing.BasicTestCase;

public abstract class AbstractBackgroundRulesTest extends BasicTestCase {

  private IBackgroundTemplate backgroundTemplate;
  private DummyGenericTraitCollection traitCollection = new DummyGenericTraitCollection();

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    backgroundTemplate = new SimpleBackgroundTemplate("Test"); //$NON-NLS-1$
  }

  protected final void initTraitCollectionControlForValue(int value) {
    traitCollection.setValue(getBackgroundTemplate(), value);
  }

  protected final IGenericTraitCollection getTraitCollection() {
    return traitCollection;
  }

  protected final IBackgroundTemplate getBackgroundTemplate() {
    return backgroundTemplate;
  }

  protected final ISpell createSpell(CircleType type) {
    return new Spell(null, type, null, null, null);
  }

  protected final ICharm createCharm() {
    return new DummyCharm("id"); //$NON-NLS-1$
  }
}