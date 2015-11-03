package com.example.trip.util;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.trip.entity.BeenCityModel;
import com.example.trip.entity.CityModel;
import com.example.trip.entity.Order;
import com.example.trip.entity.PlayMethod;
import com.example.trip.entity.PlayNote;
import com.example.trip.entity.Schedule;
import com.example.trip.entity.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseOpenHelper extends OrmLiteSqliteOpenHelper {
	private static String DB_NAME = "trip.db";
	private static int DB_VERSION = 1;
	public static DatabaseOpenHelper helper;

	private DatabaseOpenHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DB_NAME, null, DB_VERSION);
	}

	public static DatabaseOpenHelper getInstance(Context context) {
		synchronized (DatabaseOpenHelper.class) {
			if (helper == null) {
				synchronized (DatabaseOpenHelper.class) {
					if (helper == null) {
						helper = new DatabaseOpenHelper(context);
					}
				}
			}
		}
		return helper;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		// TODO Auto-generated method stub
		try {
			// TableUtils.createTable(arg1, Schedule.class);
			TableUtils.createTable(arg1, PlayMethod.class);
			TableUtils.createTable(arg1, CityModel.class);
			TableUtils.createTable(arg1, PlayNote.class);
			// TableUtils.createTable(arg1, User.class);
			TableUtils.createTable(arg1, BeenCityModel.class);
			TableUtils.createTable(arg1, Order.class);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
	}

	// private Dao<Schedule, Integer> dateDao = null;
	private Dao<PlayMethod, Integer> collectionArticleDao = null;
	private Dao<CityModel, Integer> collectionCityDao = null;
	private Dao<BeenCityModel, Integer> beenDao = null;

	public Dao<BeenCityModel, Integer> getBeenDao() {
		if (beenDao == null) {
			try {
				beenDao = getDao(BeenCityModel.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return beenDao;
	}

	public Dao<CityModel, Integer> getCollectionCityDao() {
		if (collectionCityDao == null) {
			try {
				collectionCityDao = getDao(CityModel.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return collectionCityDao;
	}

	public Dao<PlayMethod, Integer> getCollectionArticleDao() {
		if (collectionArticleDao == null) {
			try {
				collectionArticleDao = getDao(PlayMethod.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return collectionArticleDao;
	}

	private Dao<PlayNote, Integer> noteDao = null;
	private Dao<User, Integer> userDao = null;

	public Dao<User, Integer> getUserDao() {
		if (userDao == null) {
			try {
				userDao = getDao(User.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userDao;
	}

	// 新增
	private Dao<Order, Integer> orderDao = null;

	public Dao<Order, Integer> getOrderDao() {
		if (orderDao == null) {
			try {
				orderDao = getDao(Order.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orderDao;
	}

	public Dao<PlayNote, Integer> getPlayNoteDao() {
		if (noteDao == null) {
			try {
				noteDao = getDao(PlayNote.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return noteDao;
	}

	// public Dao<Schedule, Integer> getDateDao() {
	// if (dateDao == null) {
	// try {
	// dateDao = getDao(Schedule.class);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return dateDao;
	// }

	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
		// dateDao = null;
		collectionArticleDao = null;
		collectionCityDao = null;
		noteDao = null;
		userDao = null;
		beenDao = null;
		orderDao = null;

	}
}
