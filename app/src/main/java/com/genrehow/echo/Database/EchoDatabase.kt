package com.genrehow.echo.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.view.autofill.AutofillId
import com.genrehow.echo.Database.EchoDatabase.Staticated.TABLE_NAME
import com.genrehow.echo.Songs

class EchoDatabase: SQLiteOpenHelper {

    object Staticated{
        var DB_VERSION=1
        var _songList = ArrayList<Songs>()
        val DB_NAME ="FavouriteDatabase"
        val TABLE_NAME ="FavouriteTable"
        val COLUMN_ID="SongID"
        val COLUMN_SONG_TITLE="SongTitle"
        val COLUMN_SONG_ARTIST="SongArtist"
        val COLUMN_SONG_PATH ="SongPath"

    }

    override fun onCreate(sqliteDatabse: SQLiteDatabase?) {
        sqliteDatabse?.execSQL(
                "CREATE TABLE" + Staticated.TABLE_NAME + "( "+Staticated.COLUMN_ID + " INTEGER," + Staticated.COLUMN_SONG_ARTIST + " STRING,"
                        + Staticated.COLUMN_SONG_TITLE + " STRING," + Staticated.COLUMN_SONG_PATH + " STRING);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : super(context, name, factory, version)
    constructor(context: Context?) : super(context,Staticated.DB_NAME, null, Staticated.DB_VERSION)

    fun storeAsFavorite(id: Int?, artist: String?, songTitle: String?, path: String?){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Staticated.COLUMN_ID,id)
        contentValues.put(Staticated.COLUMN_SONG_ARTIST, artist)
        contentValues.put(Staticated.COLUMN_SONG_TITLE, songTitle)
        contentValues.put(Staticated.COLUMN_SONG_PATH, path)

        db.insert(TABLE_NAME, null,contentValues)
        db.close()
    }

    fun queryDBList(): ArrayList<Songs>?{
        try{

            val db = this.readableDatabase
            val query_params = "SELECT * FROM " + TABLE_NAME
            val cSor = db.rawQuery(query_params,null)
            if(cSor.moveToFirst()){
                do{
                    var _id = cSor.getInt(cSor.getColumnIndexOrThrow(Staticated.COLUMN_ID))
                    var _artist = cSor.getString(cSor.getColumnIndexOrThrow(Staticated.COLUMN_SONG_ARTIST))
                    var _title = cSor.getString(cSor.getColumnIndexOrThrow(Staticated.COLUMN_SONG_TITLE))
                    var _songPath = cSor.getString(cSor.getColumnIndexOrThrow(Staticated.COLUMN_SONG_PATH))
                    Staticated._songList.add(Songs(_id.toLong(), _title, _artist, _songPath, 0))
                }
                while (cSor.moveToNext())
            }
            else{
                return null
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return  Staticated._songList
    }
    fun checkifIdExists(_id: Int): Boolean{
        var storeId = -1090
        val db = this.readableDatabase
        val query_params = "SELECT * FROM " + TABLE_NAME + "WHERE SongId = '$_id'"
        val cSor = db.rawQuery(query_params, null)
        if(cSor.moveToFirst()) {
            do {
                storeId = cSor.getInt(cSor.getColumnIndexOrThrow(Staticated.COLUMN_ID))
            } while (cSor.moveToNext())
        }else{
            return false
        }
        return storeId != -1090
    }
    fun deleteFavourite(_id: Int){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,Staticated.COLUMN_ID+ "="+ _id,null)
        db.close()
    }
    fun checkSize():Int{
        var counter = 0
        val db = this.readableDatabase
        var query_params = "SELECT * FROM " + TABLE_NAME
        val cSor = db.rawQuery(query_params, null)
        if (cSor.moveToFirst()) {
            do {
                counter = counter + 1
            } while (cSor.moveToNext())
        } else {
            return 0
        }
 return counter
    }
}