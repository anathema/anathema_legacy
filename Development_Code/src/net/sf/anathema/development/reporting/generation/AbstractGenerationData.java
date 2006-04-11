package net.sf.anathema.development.reporting.generation;

import net.sf.anathema.character.generic.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.character.generic.impl.backgrounds.SimpleBackgroundTemplate;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterDescription;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.character.model.concept.INature;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;
import net.sf.anathema.character.model.concept.NatureType;
import net.sf.anathema.framework.repository.AnathemaItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.resources.AnathemaResources;

public abstract class AbstractGenerationData implements IGenerationData {

  protected final CharacterModuleContainer container = new CharacterModuleContainerInitializer().initContainer(new AnathemaResources());

  protected final IItem createItem(ICharacter character) {
    return new AnathemaItem(ExaltedCharacterItemTypeConfiguration.createCharacterItemType(), character);
  }

  protected final ExaltedCharacter createEmptyCharacter() {
    return new ExaltedCharacter();
  }

  protected final void createStatistics(ExaltedCharacter character, ICharacterTemplate template) throws SpellException {
    character.createCharacterStatistics(template, container.getCharacterGenerics(), ExaltedRuleSet.CoreRules);
  }

  protected final void fillBasicStatistics(ICharacterStatistics statistics) {
    statistics.getTraitConfiguration().getTrait(AttributeType.Strength).setCurrentValue(2);
    ITrait endurance = statistics.getTraitConfiguration().getTrait(AbilityType.Endurance);
    endurance.setCurrentValue(3);
    statistics.getTraitConfiguration().getTrait(AttributeType.Dexterity).setCurrentValue(5);
    statistics.getTraitConfiguration().getTrait(AttributeType.Wits).setCurrentValue(5);
    IFavorableTrait occult = statistics.getTraitConfiguration().getFavorableTrait(AbilityType.Occult);
    occult.getSpecialtiesContainer().addSpecialty("Unconquered Sun"); //$NON-NLS-1$
    IBackgroundConfiguration backgrounds = statistics.getTraitConfiguration().getBackgrounds();
    backgrounds.addBackground(new SimpleBackgroundTemplate("Allies")).setCurrentValue(3);
    backgrounds.addBackground("Plot Device").setCurrentValue(4); //$NON-NLS-1$    
    statistics.getCharacterConcept().getConcept().setText("Held"); //$NON-NLS-1$
    statistics.getCharacterConcept().getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      public void accept(INature nature) {
        nature.getDescription().setType(new NatureType(null, null, null));
      }
    });
    // "Gallant",
    // "whenever your deeds are particularly impressive, awe-inspiring or likely to land you smack in the
    // center of attention.";
    // return "Gallant";

    statistics.getTraitConfiguration().getTrait(VirtueType.Compassion).setCurrentValue(5);
  }

  protected final void createTestDescription(ICharacterDescription description) {
    description.getName().setText("Kenful Just"); //$NON-NLS-1$
    description.getPeriphrase().setText("Oh heile Welt wie wunderbar."); //$NON-NLS-1$
    description.getCharacterization()
        .setText(
            "Kenful Just ist ohne Zweifel eine der einflussreichsten Personen in ganz Fair und mit Sicherheit " //$NON-NLS-1$
                + "der Mann mit dem größten Einfluss auf die Herrscherfamilie.\n" //$NON-NLS-1$
                + "Als Diplomat hat er Kontakte in aller Welt und verbringt viel Zeit auf diplomatischen Missionen im Auftrag Fairs.\n" //$NON-NLS-1$
                + "Seine stoische Ruhe ist beinahe sprichwörtlich, doch hat man auch ihn in den letzten Wochen des Öfteren die Fassung verlieren hören – besonders die ambitionierten Pläne seines früheren Schützlings Emerald bereiten ihm Mal um Mal Kopfschmerzen, doch er bemüht sich, den frisch Exaltierten gegen seine äußeren und inneren Feinde zu unterstützen.\n" //$NON-NLS-1$
                + "Dem Volk Fairs und sogar seinen Schützlingen unbekannt ist seine Identität als Sidereal Exalted, die er mühevoll geheimhält. Seine Loyalität gilt der Gold Faction und damit der Wiedererstarkung der Solar Exalted – ein Punkt, der immer wieder zu Zwist mit seinem Mentor, Eni Marut, geführt hat.\n" //$NON-NLS-1$
                + "Kenful Just hat gemeinsam mit Idol Crane dessen inszenierten Tod und folgendes Verschwinden geplant, und Nachfolgend in der Rolle Cynis Puranis auf fethanischer Seite für den Beginn des Krieges gesorgt, indem er mit expansionistischen Gedanken auf die Kaiser eingewirkt hat."); //$NON-NLS-1$
    description.getPhysicalDescription().setText("Appearance"); //$NON-NLS-1$
  }
}