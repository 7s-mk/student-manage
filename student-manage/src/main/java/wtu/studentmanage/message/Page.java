package wtu.studentmanage.message;

import lombok.Data;

import java.util.List;

/**
 * 描述：
 *
 * @author LPC_MI
 * @version 1.0 2021/5/17
 * @since 2021/5/17-11:13
 */
@Data
public class Page<T> {

    // 实际结果
    private List<T> rest;

    // 总数
    private Long count;

    // 当前页
    private Long page;

    // 每页时间
    private Long size;

    // 总页数
    private Long page_all;

    // 搜索关键字
    private String key;

    public Page() {

    }

    public Page(List<T> rest, String key, Long page, Long size, Long count) {
        this();
        this.rest = rest;
        if (key == null || key.length() <= 2) {
            key = "";
        } else {
            key = key.substring(1, key.length() - 1);
        }
        this.key = key;
        this.page = page;
        this.size = size == 0 ? 10 : size;
        this.count = count;
        if (size != 0) {
            this.page_all = (count % size == 0 ? 0 : 1) + count / size;
        } else {
            this.page_all = 1L;
        }
    }

    public static <T> Page<T> rest(List<T> rest, String key, Integer page, Integer size, long count) {
        return new Page<>(rest, key, page == null ? 1 : page.longValue(), size == null ? count : size.longValue(), count);
    }
}
