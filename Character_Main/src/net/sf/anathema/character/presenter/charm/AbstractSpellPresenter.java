package net.sf.anathema.character.presenter.charm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.MagicInfoStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.ScreenDisplayInfoStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.SpellSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.IMagicLearnListener;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.compare.I18nedIdentificateComparator;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public abstract class AbstractSpellPresenter implements IContentPresenter {

  private final ISpellConfiguration spellConfiguration;
  private final ICharacterStatistics statistics;
  private final MagicInfoStringBuilder creator;
  private final ICharacterTemplate characterTemplate;
  private final IResources resources;
  private CircleType circle;
  private final IMagicSourceStringBuilder<ISpell> sourceStringBuilder;
  private final SpellViewProperties properties;
  private final ISpellView view;

  public AbstractSpellPresenter(ICharacterStatistics statistics, IResources resources, IMagicViewFactory factory) {
    this.statistics = statistics;
    this.properties = new SpellViewProperties(resources, statistics);
    this.resources = resources;
    this.creator = new ScreenDisplayInfoStringBuilder(resources);
    this.sourceStringBuilder = new SpellSourceStringBuilder(resources, statistics.getRules().getEdition());
    this.spellConfiguration = statistics.getSpells();
    this.characterTemplate = statistics.getCharacterTemplate();
    this.view = factory.createSpellView(properties);
    circle = getCircles()[0];
  }

  public void initPresentation() {
    IIdentificate[] allowedCircles = getCircles();
    initDetailsView();
    view.initGui(allowedCircles);
    view.addMagicViewListener(new IMagicViewListener() {
      public void magicRemoved(Object[] removedSpells) {
        List<ISpell> spellList = new ArrayList<ISpell>();
        for (Object spellObject : removedSpells) {
          spellList.add((ISpell) spellObject);
        }
        spellConfiguration.removeSpells(spellList.toArray(new ISpell[spellList.size()]));
      }

      public void magicAdded(Object[] addedSpells) {
        List<ISpell> spellList = new ArrayList<ISpell>();
        for (Object spellObject : addedSpells) {
          if (spellConfiguration.isSpellAllowed((ISpell) spellObject)) {
            spellList.add((ISpell) spellObject);
          }
        }
        spellConfiguration.addSpells(spellList.toArray(new ISpell[spellList.size()]));
      }
    });
    view.addCircleSelectionListener(new IObjectValueChangedListener<CircleType>() {
      public void valueChanged(CircleType circleType) {
        circle = circleType;
        view.setMagicOptions(getSpellsToShow());
      }
    });
    spellConfiguration.addMagicLearnListener(new IMagicLearnListener<ISpell>() {
      public void magicForgotten(ISpell[] magic) {
        forgetSpellListsInView(view, magic);
      }

      public void magicLearned(ISpell[] magic) {
        learnSpellListsInView(view, magic);
      }
    });
    initSpellListsInView(view);
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        view.clearSelection();
      }
    });
  }

  public IViewContent getTabContent() {
    String header = resources.getString(getTabTitleResourceKey());
    return new SimpleViewContent(new ContentProperties(header), view);
  }

  private void initDetailsView() {
    final JLabel titleView = view.addDetailTitleView();
    titleView.setText(" "); //$NON-NLS-1$
    final IValueView<String> circleView = view.addDetailValueView(properties.getCircleString() + ":"); //$NON-NLS-1$
    final IValueView<String> costView = view.addDetailValueView(properties.getCostString() + ":"); //$NON-NLS-1$
    final IValueView<String> targetView = view.addDetailValueView(properties.getTargetString() + ":"); //$NON-NLS-1$
    final IValueView<String> sourceView = view.addDetailValueView(properties.getSourceString() + ":"); //$NON-NLS-1$
    final ListSelectionListener detailListener = new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        final ISpell spell = (ISpell) ((JList) e.getSource()).getSelectedValue();
        if (spell == null) {
          return;
        }
        titleView.setText(resources.getString(spell.getId()));
        circleView.setValue(resources.getString(spell.getCircleType().getId()));
        costView.setValue(creator.createCostString(spell));
        if (spell.getTarget() == null) {
          targetView.setValue(properties.getUndefinedString());
        }
        else {
          targetView.setValue(resources.getString("Spells.Target." + spell.getTarget())); //$NON-NLS-1$
        }
        sourceView.setValue(sourceStringBuilder.createSourceString(spell));
      }
    };
    view.addOptionListListener(detailListener);
    view.addSelectionListListener(detailListener);
  }

  protected abstract CircleType[] getCircles();

  protected abstract String getTabTitleResourceKey();

  private void initSpellListsInView(final ISpellView spellView) {
    spellView.setLearnedMagic(getCircleFilteredSpellList(spellConfiguration.getLearnedSpells()));
    spellView.setMagicOptions(getSpellsToShow());
  }

  private void forgetSpellListsInView(final ISpellView spellView, ISpell[] spells) {
    spellView.removeLearnedMagic(spells);
    ISpell[] supportedSpells = getSpellsOfCurrentCircle(spells);
    spellView.addMagicOptions(supportedSpells, new I18nedIdentificateComparator(resources));
  }

  private void learnSpellListsInView(final ISpellView spellView, ISpell[] spells) {
    ISpell[] supportedSpells = getSpellsOfCurrentCircle(spells);
    spellView.addLearnedMagic(supportedSpells);
    spellView.removeMagicOptions(supportedSpells);
  }

  private ISpell[] getSpellsOfCurrentCircle(ISpell[] spells) {
    List<ISpell> supportedSpells = new ArrayList<ISpell>();
    for (ISpell spell : spells) {
      if (circle == spell.getCircleType()) {
        supportedSpells.add(spell);
      }
    }
    return supportedSpells.toArray(new ISpell[supportedSpells.size()]);
  }

  private ISpell[] getSpellsToShow() {
    List<ISpell> showSpells = new ArrayList<ISpell>();
    Collections.addAll(showSpells, spellConfiguration.getSpellsByCircle(circle));
    showSpells.removeAll(Arrays.asList(spellConfiguration.getLearnedSpells()));
    int count = showSpells.size();
    ISpell[] sortedSpells = new ISpell[count];
    sortedSpells = new I18nedIdentificateSorter<ISpell>().sortAscending(
        showSpells.toArray(new ISpell[count]),
        sortedSpells,
        resources);
    return sortedSpells;
  }

  private ISpell[] getCircleFilteredSpellList(ISpell[] spells) {
    List<ISpell> spellList = new ArrayList<ISpell>();
    for (ISpell spell : spells) {
      if (ArrayUtilities.containsValue(getCircles(), spell.getCircleType())) {
        spellList.add(spell);
      }
    }
    return spellList.toArray(new ISpell[spellList.size()]);
  }

  protected ICharacterTemplate getCharacterTemplate() {
    return characterTemplate;
  }
}