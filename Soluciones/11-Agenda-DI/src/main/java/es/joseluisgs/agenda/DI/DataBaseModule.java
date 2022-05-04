package es.joseluisgs.agenda.DI;

import dagger.Module;
import dagger.Provides;
import es.joseluisgs.agenda.managers.DataBaseManager;

import javax.inject.Singleton;

@Module
public class DataBaseModule {

    @Singleton
    @Provides
    public DataBaseManager provideDataBase() {
        return DataBaseManager.getInstance();
    }
}
