

/**
 *
 * @author Zhipeng Liang
 */
public class Comment {
    /**
     * 本章介绍Bean中的数据校验
     * ReportAsSingleViolation:将返回组合标注错误报告。单个的约束错误报告被忽略。
     * 
     * constraints.xml是从validation.xml中分裂出的bean配置文件，使用xml格式代替＠注释形式的校验
     * 不知道部分校验Groups有什么作用
     * 部分校验通过加入一个类型参数
     * Set<ConstraintViolation<CD>> violations = validator. validate (cd, Default.class )
     * 整体校验省略参数
     * Set<ConstraintViolation<CD>> violations = validator. validate (cd)
     * 
     */
}
