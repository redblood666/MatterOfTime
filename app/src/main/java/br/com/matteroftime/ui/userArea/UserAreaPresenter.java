package br.com.matteroftime.ui.userArea;

import android.content.Context;
import android.widget.Toast;

import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import br.com.matteroftime.core.MatterOfTimeApplication;
import br.com.matteroftime.core.listeners.OnDatabaseOperationCompleteListener;
import br.com.matteroftime.models.Musica;
import br.com.matteroftime.ui.selectMusic.SelectMusicDialogFragment;

/**
 * Created by RedBlood on 30/03/2017.
 */

public class UserAreaPresenter implements UserAreaContract.Actions, OnDatabaseOperationCompleteListener{

    private final UserAreaContract.View view;
    @Inject UserAreaContract.Repository repository;
    @Inject Bus bus;

    public UserAreaPresenter(UserAreaContract.View view) {
        this.view = view;
        MatterOfTimeApplication.getInstance().getAppComponent().inject(this);
        bus.register(this);
    }



    @Override
    public void loadMusics(){
        List<Musica> availableMusics = repository.getAllMusics();
        if (availableMusics != null && availableMusics.size() > 0){
            view.hideEmptyText();
            view.showMusicas(availableMusics);
        } else {
            view.showEmptyText();
        }
    }

    @Override
    public Musica getMusica(long id) {
        return repository.getMusicById(id);
    }

    @Override
    public void pesquisaMusica(String nomeMusica, Context context) {
//        repository.pesquisaMusica(nomeMusica, this, context);
        view.showMusicas(repository.pesquisaMusica(nomeMusica, this, context));
    }

    @Override
    public void baixaMusica(Musica musica, Context context) {
        repository.baixaMusica(musica, this, context);
    }

    @Override
    public void onSQLOperationFailed(String error) {
        view.showMessage(error);
    }

    @Override
    public void onSQLOperationSucceded(String message) {
        view.showMessage(message);
    }
}
