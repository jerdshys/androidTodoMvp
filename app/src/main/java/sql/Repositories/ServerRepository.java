package sql.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import sql.FeedReaderContract;
import sql.Models.Server;
import sql.RepoInterface;
import sql.ServerDbHelper;

/**
 * Created by jerome on 25/10/2017.
 */
@Module
public class ServerRepository implements RepoInterface {

    private final ServerDbHelper mDbHelper;
    private FeedReaderContract.Servers mFeedReader;

    @Singleton
    public ServerRepository(@NonNull Context context) {

        mDbHelper = new ServerDbHelper(context);
        // @todo : dynamic add
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

/*        ContentValues values = new ContentValues();
        values.put(mFeedReader.COLUMN_NAME_TITLE, "serveur1");
        values.put(mFeedReader.COLUMN_NAME_DESCRIPTION, "192.168.0.1");
        long newRowId = db.insert(mFeedReader.TABLE_NAME, null, values);

        values.put(mFeedReader.COLUMN_NAME_TITLE, "serveurZ");
        values.put(mFeedReader.COLUMN_NAME_DESCRIPTION, "192.168.0.Z");
        long newRowId2 = db.insert(mFeedReader.TABLE_NAME, null, values);*/
    }

    public ArrayList<Server> getAll() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ArrayList<Server> servers= new ArrayList<Server>();
        Cursor result = db.rawQuery("select _id , title, description from "+mFeedReader.TABLE_NAME , null);
        while(result.moveToNext()){
            servers.add( new Server( result.getLong(0),
                                     result.getString(result.getColumnIndex(mFeedReader.COLUMN_NAME_TITLE)),
                                     result.getString(result.getColumnIndex(mFeedReader.COLUMN_NAME_DESCRIPTION))));
        }
        return servers;
    }

    public Server get(long id) {
        Server server = null;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + mFeedReader.TABLE_NAME + " WHERE "+mFeedReader.COLUMN_ID+" = ?";
        Cursor result = db.rawQuery(query, new String[] { Long.toString(id) });
        if(result.moveToFirst()){
            server = new Server( result.getLong(0),
                    result.getString(result.getColumnIndex(mFeedReader.COLUMN_NAME_TITLE)),
                    result.getString(result.getColumnIndex(mFeedReader.COLUMN_NAME_DESCRIPTION)));
        }
        return server;

    }

    public void put(Long id,String name , String description) {
        SQLiteDatabase db = this.mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(mFeedReader.COLUMN_NAME_TITLE, name);
        values.put(mFeedReader.COLUMN_NAME_DESCRIPTION, description);
        db.update(mFeedReader.TABLE_NAME, values, "_id="+id, null);

    }

    public void post(String name, String description) {
        System.out.println("*************************");
        System.out.println("*************************");
        System.out.println("*************************");
        System.out.println("*************************");
        System.out.println("*************************");
        System.out.println(name+" "+description);
        System.out.println("*************************");
        System.out.println("*************************");
        // @todo : dynamic add
        SQLiteDatabase db = this.mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(mFeedReader.COLUMN_NAME_TITLE, name);
        values.put(mFeedReader.COLUMN_NAME_DESCRIPTION, description);
        db.insert(mFeedReader.TABLE_NAME, null, values);

    }

    public void deleteAll() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        db.execSQL("delete from "+ mFeedReader.TABLE_NAME);
    }

    public void delete(Long id) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        db.delete(mFeedReader.TABLE_NAME, mFeedReader.COLUMN_ID + " = ?",
                new String[]{Long.toString(id)} );
        db.close();
    }

}
