package edu.stanford.bmir.protege.web.client.editor;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import edu.stanford.bmir.protege.web.client.change.ChangeListViewPresenter;
import edu.stanford.bmir.protege.web.client.progress.HasBusy;
import edu.stanford.bmir.protege.web.resources.WebProtegeClientBundle;
import edu.stanford.bmir.protege.web.shared.access.BuiltInAction;
import edu.stanford.bmir.protege.web.shared.entity.EntityDisplay;
import edu.stanford.bmir.protege.web.shared.event.WebProtegeEventBus;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;
import org.semanticweb.owlapi.model.OWLEntity;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 8 Oct 2018
 */
public class EditorPaneEntityChangesPresenter implements EditorPanePresenter {

    @Nonnull
    private final ProjectId projectId;

    @Nonnull
    private final ChangeListViewPresenter changesPresenter;

    @Nonnull
    private WebProtegeClientBundle clientBundle;

    @Inject
    public EditorPaneEntityChangesPresenter(@Nonnull ProjectId projectId,
                                            @Nonnull ChangeListViewPresenter changesPresenter,
                                            @Nonnull WebProtegeClientBundle clientBundle) {
        this.projectId = projectId;
        this.changesPresenter = checkNotNull(changesPresenter);
        this.clientBundle = checkNotNull(clientBundle);
    }

    @Nonnull
    @Override
    public String getCaption() {
        return "Changes";
    }

    @Nonnull
    @Override
    public String getAdditionalStyles() {
        return clientBundle.buttons().changes();
    }

    @Override
    public void start(@Nonnull AcceptsOneWidget container, WebProtegeEventBus eventBus) {
        container.setWidget(changesPresenter.getView());
    }

    @Nonnull
    @Override
    public BuiltInAction getRequiredAction() {
        return BuiltInAction.VIEW_CHANGES;
    }

    @Override
    public void setEntity(@Nonnull OWLEntity entity) {
        changesPresenter.setChangesForEntity(projectId, entity);
    }

    @Override
    public void setHasBusy(@Nonnull HasBusy hasBusy) {
        changesPresenter.setHasBusy(hasBusy);
    }

    @Override
    public void setEntityDisplay(@Nonnull EntityDisplay entityDisplay) {
        // Nothing to do
    }

    @Override
    public void dispose() {
        // Nothing to do
    }
}