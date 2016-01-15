package org.xiangbalao;

import java.util.List;


import org.xiangbalao.bean.Person;
import org.xiangbalao.dao.DaoMaster;
import org.xiangbalao.dao.DaoMaster.DevOpenHelper;
import org.xiangbalao.dao.DaoSession;
import org.xiangbalao.dao.PersonDao;
import org.xiangbalao.dao.PersonDao.Properties;
import org.xiangbalao.greendaodome.R;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * 
 * @author longtaoge
 *
 */
public class GreenDaoActivity extends Activity implements OnClickListener {

	private SQLiteDatabase db;
	private DaoMaster daoMaster;
	private DaoSession daoSession;

	/**
	 * PersonDAO 类
	 */
	private PersonDao mPersonDao;

	/**
	 * 数据库名称及路径
	 */
	private static String databasesPath = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ "/GreenDaotest.db";

	private EditText etFirstName;
	private EditText etlastname;
	// 增
	private Button btnSave;
	// 删除
	private Button btnDel;
	// 改
	private Button btnUpDate;
	// 查
	private Button btnQuery;
	private TextView tvFirstName;
	private TextView tvLastName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cipher);
		initView();
		initDb();
	}

	private void initDb() {

		DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, databasesPath,
				null);
		db = helper.getWritableDatabase();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
		mPersonDao = daoSession.getPersonDao();

	}

	private void initView() {

		etFirstName = (EditText) findViewById(R.id.etFirstName);
		etlastname = (EditText) findViewById(R.id.lastname);
		tvFirstName = (TextView) findViewById(R.id.tvFirstName);
		tvLastName = (TextView) findViewById(R.id.tvLastName);

		btnSave = (Button) findViewById(R.id.btnSave);
		btnDel = (Button) findViewById(R.id.btndel);
		btnUpDate = (Button) findViewById(R.id.btnupdate);

		btnQuery = (Button) findViewById(R.id.btnQuery);

		btnSave.setOnClickListener(this);
		btnDel.setOnClickListener(this);
		btnUpDate.setOnClickListener(this);
		btnQuery.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSave:

			save();
			break;
		case R.id.btndel:
			del();
			break;

		case R.id.btnupdate:
			upDate();
			break;
		case R.id.btnQuery:
			btnQuery();

			break;

		default:
			break;
		}

	}

	/**
	 * 改
	 */
	private void upDate() {

		Person mp = new Person();
		mp.setId(Long.valueOf(88));
		mp.setAge("22");
		mp.setFirstname(etFirstName.getText().toString());
		mp.setLastname(etlastname.getText().toString());

		mPersonDao.insertOrReplace(mp);

	}

	/**
	 * 删除
	 */
	private void del() {

		mPersonDao.deleteByKey(Long.valueOf(88));

	}

	/**
	 * 查
	 */
	private void btnQuery() {

		// 根据条件查询
		QueryBuilder<Person> qb = mPersonDao.queryBuilder();
		qb.where(Properties.Firstname.eq("安"), Properties.Lastname.eq("卓"));
		List<Person> mPersons = qb.list();

		if (mPersons != null) {
			if (mPersons.size() > 0) {
				tvFirstName.setText(mPersons.get(0).getFirstname());
				tvLastName.setText(mPersons.get(0).getLastname());

			}

		}
		
		
		// 根据ID查询
		Person mPerson = mPersonDao.load(Long.valueOf(88));
		if (mPerson != null) {
			tvFirstName.setText(mPerson.getFirstname());
			tvLastName.setText(mPerson.getLastname());
		}else {
			tvFirstName.setText("");
			tvLastName.setText("");
		}

		

	}

	/**
	 * 增
	 */

	private void save() {

		Person mp = new Person();
		mp.setId(Long.valueOf(88));
		mp.setAge("22");
		mp.setFirstname(etFirstName.getText().toString());
		mp.setLastname(etlastname.getText().toString());
		//mPersonDao.insert(mp);
		mPersonDao.insertOrReplace(mp);

	}

}