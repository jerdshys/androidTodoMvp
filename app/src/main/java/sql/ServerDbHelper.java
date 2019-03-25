package sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jerome on 25/10/2017.
 */
public class ServerDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_CREATE = "CREATE TABLE Servers ( _id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, description TEXT NOT NULL)";
    public static final String DB_NAME = "myDB.db";
    public static final int DB_VERSION = 2;

    public ServerDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS Servers");
        //db.execSQL("DELETE FROM Servers");
        db.execSQL(DATABASE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Servers");
        onCreate(db);
    }
}
