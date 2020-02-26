package com.dt.dubai.core.comm;

import java.util.List;
import java.util.Map;

public class ConstantValue {

    public static final long LONG_0 = 0;
    public static final long LONG_1 = 1;
    public static final long LONG_2 = 2;

    public static final int INT_0 = 0;
    public static final int INT_1 = 1;
    public static final int INT_2 = 2;
    public static final int INT_3 = 3;
    public static final int INT_4 = 4;
    public static final int INT_5 = 5;
    public static final int INT_8 = 8;
    public static final int INT_10 = 10;
    public static final int INT_11 = 11;
    public static final int INT_255 = 255;

    public static final Long OneDay = Long.valueOf(86400000);

    public static final boolean TRUE = true;
    public static final boolean FALSE = false;

    public static final String SPLIT_ = " "; // 空格分隔
    public static final String SPLIT__ = "_"; // 下划线分隔
    public static final String EMPTY_ = "";  // 空字符串
    public static final String NULL_s = null;
    public static final Map NULL_map = null;
    public static final List NULL_list = null;

    // eq相等   ne、neq不相等，   gt大于， lt小于 gte、ge大于等于 ba开始至结束  lte、le 小于等于   not非   mod求模
    public static final String String_eq = "eq"; // =
    public static final String String_lt = "lt"; // 小于
    public static final String String_gt = "gt"; // 大于
    public static final String String_ba = "ba"; // 大于等于 和 小于等于
    public static final String String_lk = "lk"; // like
    public static final String String_nlk = "nl"; // not like
    public static final String String_lk_s = "%";

    public static final String STRING_EQ = "eq"; // =
    public static final String STRING_LT = "lt"; // 小于
    public static final String STRING_GT = "gt"; // 大于
    public static final String STRING_BA = "ba"; // 大于等于 和 小于等于
    public static final String STRING_LK = "lk"; // like
    public static final String STRING_NLK = "nl"; // not like
    public static final String STRING_LK_S = "%";


    public static final String Str_date_start = "dateStart";
    public static final String Str_date_end = "dateEnd";

    public static final String LONG = "Long";
    public static final String LONG_L = "Long";
    public static final String INT = "int";
    public static final String INTEGE_I = "Integer";
    public static final String SHORT = "short";
    public static final String SHORT_S = "Short";
    public static final String BOOLEAN = "Boolean";
    public static final String BOOLEAN_B = "Boolean";
    public static final String DTAE = "Date";
    public static final String DTAE_D = "Date_D";
    public static final String DATE_DT = "Date_DT";
    public static final String CHAR = "char";
    public static final String STRING = "String";
    public static final String STRING_T = "String_T";
    public static final String TxStame = "TxStamp";
    public static final String STAME = "Stamp";
    public static final String Entity = "Entity";

    public static final String recordId = "recordId";
    public static final String userRecordId = "userRecordId";
    public static final String lastupTxStamp = "lastupTxStamp";

    public static final String STR_DATE_START = "dateStart";
    public static final String STR_DATE_END = "dateEnd";

    public static final String CharGBK = "GBK";
    public static final String CharUTF = "UTF-8";
    public static final String CharASCII = "ASCII";
    public static final String CharGB = "GB2312";
    public static final String CharISO = "ISO-8859-1";

    public static final String InviteUrl = "localhost:8087/user/Invite/useShareLink?invitationCode=";
    public static final String LinkInvitation = "&inviteWay=0";
    public static final String EmailInvitation = "&inviteWay=1";

    public static final String OriginsValue = "http://192.168.1.104:8087";

}
