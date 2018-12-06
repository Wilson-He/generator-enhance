package ${baseDAO}.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BaseDAO
 */
public interface BaseDAO<T> extends BaseMapper<T> {
}
