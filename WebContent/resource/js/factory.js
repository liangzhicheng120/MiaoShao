// 构造函数模式
/*
 * 构造函数模式用于创建特定类型的对象——不仅声明了使用的对象,构造函数还可以接受参数以便第一次
 * 创建对象的时候设置对象的成员值。你可以自定义自己的构造函数,然后在里面声明自定义类型对象的属性或方法.
 * javascriptl里,构造函数通常是认为用来实现实例的,javascript没有类的概念,但是有特殊的构造函数.
 * 通过new关键字来调用自定义的构造函数,在构造函数内部,this关键字引用的是新创建的对象.
 * 
 * 模式作用：
 * 1.用于创建特殊类型的对象
 * 2.第一次声明的时候给对象赋值
 * 3.自己声明构造函数,赋予属性和方法
 * 注意事项：
 * 1.声明函数的时候处理业务逻辑.
 * 2.区分和单例的区别,配合单例实现初始化
 * 3.构造函数字母开头尽量以大写字母开头
 * 4.注意new的成本
 * 
 */
// 用于创建特定的类型
// js开发尽量使用单引号
// js构造函数比较特殊的地方new
// zaomen就是构造函数,又充当了类的概念
var AA = {
function zaomen()
{
	if (!(this instanceof zaomen))
	{
		return new zaomen;
	}
	var _huawen = '普通';
	if (huawen)
	{
		_huawen = huawen;
	}
	this.suo = '普通';
	this.huawen = _huawen;
	this.create = function()
	{
		return '【锁头】' + this.suo + '【花纹】' + this.huawen;}
}

}
var xiaozhang = new zaomen();
console.log('xiaozhang' + xiaozhang.create());
var xiaoli = new zaomen('绚丽');
console.log('xiaoli' + xiaoli.create());