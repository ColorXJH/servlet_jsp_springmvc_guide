package example20_converter_and_formatter;

public class ConverterAndFormatterGuide {
		//转换器和格式化
			//spring的数据绑定没有任何限制，他在数据半丁方面是杂乱无章的，例如spring总是试图使用默认的语言区域将日期输入绑定到java.util.date
			//如果想让spring使用不同的日期样式，就需要 一个 converter或者formatter来协助spring完成
				//这两者据=均可以将一种对象类型转换为另一种对象类型，converter是通用元件，而formatter是专门为web层设计的
				
			//converter:spring的converter是一个可以将一个类型转换为另一个类型的对象
				//例如用户输入的日期可能有多种形式，“decemeter25, 2019”,"12/25/2019","2019-12-25"
				//默认情况下spring会期待使用与当前语言取域相同的日期样式
				
				//为了创建一个converter,必须实现org.springframework.core.convert.converter.Converter接口的java类，这个接口声明如下：
					//public interface Converter<S,T>
						//S:源类型，//T:目标类型 
					//创建一个可以将Long转化为Date的Converter,按如下形式声明：
						//public class MyConverter implement Converter<Long,Date>{}
						//在类的body中，要实现该接口的convert方法实现
							//T convert(S s)
				//wEileen在springMVC中使用这个Converter,需要在xml配置文件中配置该类的一个bean,bean的类名称必须为
				//org.springframework.context.support.ConversionServiceFactoryBean,他将在converters属性中列出所有的自定义的converter
				//随后要给annotation-driven元素的conversion-service属性赋予上面bean的名称
			
			//Formatter
				//像converter一样也是将一种类型转换为另一种数据类型，但是formatter的源必须是string,而converter则适用于任意数据类型，formatter更适合于web层
				//converter则可以在任意层使用，在springMVC中转换表单应该使用formatter而不是converter
				//为了创建一个Formatter,要编写一个实现org.springframework.format.Formatter接口的java类，接口声明如下
					//public interface Formatter<T>{}	//T表示要转换的目标类型
					//该接口有parse和print两个方法，所有实现都必须覆盖
						//T parse (String text,java.util.Locale locale):利用指定的locale将一个string解析成目标类型
						//String print(T object,java.util.Locale locale)；与上面相反，返回目标对象的字符串表示法
				//为了在springMVC中使用该formatter,需要利用conversionService bean 对他进行注册，bean的名称必须为：org.springframework.format.support.FormattingConversionServiceFactoryBean
					//这个bean有一个属性formatters用来注册Formatter,有一个属性converters用来注册Converters
					//注意，还要给这个formatter添加一个component-scan元素
			
			//用Registrar注册Formatter
				//注册Formatter的另一种方法是使用Registrar,见类MyRegistrar
				//有了Registrar在xml文件中将formatter类替换成Registrar就可以了
				
			//选择Converter还是Formatter
				//Converter是一般的工具,可以将一种类型转换为另一种类型,如将String转换为Date,Long转换为Date,Converter既可以用在web层，也可以用在其他层
				//Formatter只能将String转换为另一种类型，例如Date,Long..,Formatter适合于web层，在springMVC应用程序中选择Formatter比Converter更合适
		
		//小结：Converter/Formatter可以用来数据转换和格式化
	
	
}
