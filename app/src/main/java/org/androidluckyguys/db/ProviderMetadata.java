package org.androidluckyguys.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by XKL0275 on 5/10/2015.
 */
public class ProviderMetadata {

    public static final String AUTHORITY = "org.androidluckyguys.MposProvider";
    public static final String DATABASE_NAME = "androidluckyguys.db";
    public static final int DATABASE_VERSION = 10;

    private ProviderMetadata() {}


    public static final class PersonObjectMetaData implements BaseColumns {

        private PersonObjectMetaData() {
        }

        public static final String TABLE_NAME = "person_data";
        // uri and MIME type definitions
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/person_data");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.androidluckyguys.person_data";
        public static final String DEFAULT_SORT_ORDER = "";

        //COLUMNS                 <!--com.xchanging.ycms.mpos.db-->

        public static final String REC_MASTER_NAME = "name";
        public static final String REC_MASTER_EMAIL = "email";
        public static final String REC_MASTER_PHONE = "phone";

    }

}
