package function;

/**
 * 自定义函数
 *
 * interface做注解的注解类型，被定义成java语言规
 * 一个被它注解的接口只能有一个抽象方法，有两种例外
 *  第一是接口允许有实现的方法，这种实现的方法是用default关键字来标记的(java反射中java.lang.reflect.Method#isDefault()方法用来判断是否是default方法)
 *  第二如果声明的方法和java.lang.Object中的某个方法一样，它可以不当做未实现的方法，不违背这个原则: 一个被它注解的接口只能有一个抽象方法, 比如: java public interface Comparator<T> { int compare(T o1, T o2); boolean equals(Object obj); }
 * 如果一个类型被这个注解修饰，那么编译器会要求这个类型必须满足如下条件:
 *  这个类型必须是一个interface，而不是其他的注解类型、枚举enum或者类class
 *  这个类型必须满足function interface的所有要求，如你个包含两个抽象方法的接口增加这个注解，会有编译错误。
 * 编译器会自动把满足function interface要求的接口自动识别为function interface。# 如何自定义函数接口？
 */
@FunctionalInterface
public interface IMyInterface {
    void study();
}
