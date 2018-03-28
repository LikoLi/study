package org.liko.event.util;

import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lan on 2017/3/21.
 */
public class DBUtil {
    public static <T> void saveOrUpdate(Mapper<T> dao, T obj) {
        if (dao.existsWithPrimaryKey(obj)) {
            dao.updateByPrimaryKeySelective(obj);
        } else {
            dao.insertSelective(obj);
        }
    }

    public static List<String> toSqlStringList(List<String> list) {
        int quantity = 500;
        List<String> sqlList = new ArrayList<>();
        if (null != list && list.size()>0){
            int count = 0;
            while (count < list.size()) {
                sqlList.add(toSqlString(list.subList(count, (count + quantity) > list.size() ? list.size() : count + quantity)));
                count += quantity;
            }
        }
        return sqlList;
    }
    public static String toSqlString(List<String> list) {
        if (null == list)
            return "('')";
        StringBuilder sql = new StringBuilder("('");
        sql.append(StringUtils.join(list, "','"));
        sql.append("')");
        return sql.toString();
    }
}
