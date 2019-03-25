package com.example.jerome.src.Server;

import androidx.annotation.NonNull;
import sql.Models.Server;
import sql.Repositories.ServerRepository;

import java.util.ArrayList;

/**
 * Created by jerome on 26/10/2017.
 */
public class ServerPresenter implements ServerContract.Presenter {

    private ServerRepository mServerRepository;
    private ServerContract.View mServerView;


    public ServerPresenter(@NonNull ServerRepository mServerRepository, @NonNull ServerContract.View mServerView) {
        this.mServerRepository =  mServerRepository;
        this.mServerView = mServerView;
        this.loadServers();
    }

    @Override
    public void loadServers() {
       ArrayList<Server> servers = mServerRepository.getAll();
       this.mServerView.showServers(servers);
    }

    @Override
    public void addNewServer() {

    }

    @Override
    public void deleteServer(Long id) {
        mServerRepository.delete(id);
        this.loadServers();
    }





}
