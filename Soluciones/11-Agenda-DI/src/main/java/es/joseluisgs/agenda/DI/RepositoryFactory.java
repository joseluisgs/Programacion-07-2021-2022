package es.joseluisgs.agenda.DI;

import dagger.Component;
import es.joseluisgs.agenda.controllers.AgendaController;
import es.joseluisgs.agenda.repositories.PersonasRepository;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        DataBaseModule.class,
        StorageModule.class
})
public interface RepositoryFactory {
    PersonasRepository build();

    // ;ostrar esta alternativa para poder usar el controlador
    void inject(AgendaController agendaController);
}

