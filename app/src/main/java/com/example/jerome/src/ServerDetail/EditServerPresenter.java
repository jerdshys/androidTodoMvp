package com.example.jerome.src.ServerDetail;


import androidx.annotation.NonNull;
import sql.Repositories.ServerRepository;

/**
 * Created by jerome on 26/10/2017.
 */
public class EditServerPresenter implements EditServerContract.Presenter {

    private EditServerContract.View mEditServerView;
    private ServerRepository mServerRepository;

    // le presenter doit connaître la vue
    public EditServerPresenter(@NonNull ServerRepository ServerRepository, @NonNull EditServerContract.View editServerView) {
        mEditServerView = editServerView;
        mServerRepository = ServerRepository;
        mEditServerView.showServerDetail( mServerRepository.get(Long.parseLong(mEditServerView.getServerId())));

    }

    @Override
    public void saveEditServer(Long id, String name, String description) {
        mServerRepository.put(id, name, description);
    }

}
