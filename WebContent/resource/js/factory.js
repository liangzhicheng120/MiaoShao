// ���캯��ģʽ
/*
 * ���캯��ģʽ���ڴ����ض����͵Ķ��󡪡�����������ʹ�õĶ���,���캯�������Խ��ܲ����Ա��һ��
 * ���������ʱ�����ö���ĳ�Աֵ��������Զ����Լ��Ĺ��캯��,Ȼ�������������Զ������Ͷ�������Ի򷽷�.
 * javascriptl��,���캯��ͨ������Ϊ����ʵ��ʵ����,javascriptû����ĸ���,����������Ĺ��캯��.
 * ͨ��new�ؼ����������Զ���Ĺ��캯��,�ڹ��캯���ڲ�,this�ؼ������õ����´����Ķ���.
 * 
 * ģʽ���ã�
 * 1.���ڴ����������͵Ķ���
 * 2.��һ��������ʱ�������ֵ
 * 3.�Լ��������캯��,�������Ժͷ���
 * ע�����
 * 1.����������ʱ����ҵ���߼�.
 * 2.���ֺ͵���������,��ϵ���ʵ�ֳ�ʼ��
 * 3.���캯����ĸ��ͷ�����Դ�д��ĸ��ͷ
 * 4.ע��new�ĳɱ�
 * 
 */
// ���ڴ����ض�������
// js��������ʹ�õ�����
// js���캯���Ƚ�����ĵط�new
// zaomen���ǹ��캯��,�ֳ䵱����ĸ���
var AA = {
function zaomen()
{
	if (!(this instanceof zaomen))
	{
		return new zaomen;
	}
	var _huawen = '��ͨ';
	if (huawen)
	{
		_huawen = huawen;
	}
	this.suo = '��ͨ';
	this.huawen = _huawen;
	this.create = function()
	{
		return '����ͷ��' + this.suo + '�����ơ�' + this.huawen;}
}

}
var xiaozhang = new zaomen();
console.log('xiaozhang' + xiaozhang.create());
var xiaoli = new zaomen('Ѥ��');
console.log('xiaoli' + xiaoli.create());