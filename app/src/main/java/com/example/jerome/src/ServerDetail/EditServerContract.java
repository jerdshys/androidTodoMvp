package com.example.jerome.src.ServerDetail;
import androidx.annotation.NonNull;
import sql.Models.Server;

/**
 * Created by jerome on 25/10/2017.
 */
public interface EditServerContract {

    interface View  {
        void showServerDetail(@NonNull Server server);
        String getServerId();
    }

    interface Presenter  {
        void saveEditServer(Long id, String name, String description);
    }
}