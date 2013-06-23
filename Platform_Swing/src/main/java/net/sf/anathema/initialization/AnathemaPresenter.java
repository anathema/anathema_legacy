package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.messaging.IMessageContainer;
import net.sf.anathema.framework.module.ItemTypePresentationFactory;
import net.sf.anathema.framework.module.PreferencesElementsExtensionPoint;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.Resources;

import java.util.Collection;

public class AnathemaPresenter {

  private final IApplicationModel model;
  private final ApplicationView view;
  private final Resources resources;
  private final ObjectFactory objectFactory;

  public AnathemaPresenter(IApplicationModel model, ApplicationView view, Resources resources, ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() throws InitializationException {
    registerItemTypePresentations();
    initializePreferences();
    runBootJobs();
    initializeReports();
    IMessageContainer messageContainer = model.getMessageContainer();
    init(messageContainer);
  }

  private void registerItemTypePresentations() {
    Collection<ItemTypePresentationFactory> presentationFactories = objectFactory.instantiateAll(RegisteredItemTypePresentation.class);
    for (ItemTypePresentationFactory factory : presentationFactories) {
      IItemTypeViewProperties properties = factory.createItemTypeCreationProperties(model, resources);
      ItemTypeCreationViewPropertiesExtensionPoint itemCreationExtensionPoint =
              (ItemTypeCreationViewPropertiesExtensionPoint) model.getExtensionPointRegistry().get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
      String itemType = factory.getClass().getAnnotation(RegisteredItemTypePresentation.class).itemType();
      itemCreationExtensionPoint.register(model.getItemTypeRegistry().getById(itemType), properties);
    }
  }

  private void init(final IMessageContainer messageContainer) {
    messageContainer.addChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        showLatestMessage(messageContainer);
      }
    });
    showLatestMessage(messageContainer);
  }

  private void showLatestMessage(IMessageContainer messageContainer) {
    view.getStatusBar().setLatestMessage(messageContainer.getLatestMessage());
  }

  private void runBootJobs() throws InitializationException {
    Collection<IBootJob> jobs = objectFactory.instantiateOrdered(BootJob.class);
    for (IBootJob bootJob : jobs) {
      bootJob.run(resources, model);
    }
  }

  private void initializePreferences() throws InitializationException {
    PreferencesElementsExtensionPoint extensionPoint =
            (PreferencesElementsExtensionPoint) model.getExtensionPointRegistry().get(PreferencesElementsExtensionPoint.ID);
    Collection<IPreferencesElement> elements = objectFactory.instantiateOrdered(PreferenceElement.class);
    for (IPreferencesElement element : elements) {
      extensionPoint.addPreferencesElement(element);
    }
  }

  private void initializeReports() throws InitializationException {
    Collection<IReportFactory> factories = objectFactory.instantiateOrdered(ReportFactoryAutoCollector.class);
    for (IReportFactory factory : factories) {
      model.getReportRegistry().addReports(factory.createReport(resources, model));
    }
  }
}