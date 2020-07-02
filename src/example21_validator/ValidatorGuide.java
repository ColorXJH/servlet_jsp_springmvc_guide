package example21_validator;

public class ValidatorGuide {
		//验证器
			//输入验证是spring最重要的web开发任务之一，在springMVC中有两种方式可以验证输入：
				//1:利用spring自带的框架验证
				//2:利用JSR303实现
			
			//验证概览
				//Converter/Formatter最用于Field级别，在mvc中，他们将String类型转换为java的另一种类型,验证其则作用域Object级别
				//它决定一个object对象中的所有field是否都有效，以及是否遵循某些规则
				//如果一个程序既使用的了Formatter又使用了Validator,那么事件的顺序是这样的：在调用Controller期间，将会有一个或多个Formatter
				//将输入字符串转化为domain对象中的field值，一旦格式化成功，验证器就会介入
			//验证器不同于转换器，，验证其可以设定规则，而转换器是普遍的基础方法
			
			//spring验证器
				//一开始spring就有了验证器，为了创建验证器，要实现org.springframework.validation.Validator接口，这个接口有supports和validate两个方法
					//public interface Validator{
					// boolean supports(Class<?> clazz):如果验证器可以处理指定的class,将返回true
					// void validate(Object target,Errors errors):会验证目标对象，并将错误填入errors对象
					//}
				//Errors对象是org.springframework.validation.Errors接口的一个实例，该对象包含了一系列FieldError和ObjectError对象
				//FieldError表示与被验证对象中某个属性相关的错误，
				//在编写验证器时，不需要直接实例化Error对象，实例化他们会花费大量的时间，因为他们有四个参数的构造器，给Errors对象添加错误的最容易的方法是
					//在Errors对象上调用一个reject/rejectValue方法，调用reject，向FiledError中添加一个ObjectError和rejectValue
						//void reject(String errorCode)
						//void reject(String errorCode,String defaultMessage)
						//void rejectValue(String field,String errorCode)
						//void rejectValue(String field,String errorCode,String defaultMessage)
							//大多数时候只给他们传入一个错误码，spring就会在属性文件中查找错误码，获得相应的错误信息，还可以传入一个默认消息，没有找到错误码时使用该消息
							//Errors对象中的错误消息，可以利用表单标签库中的Errors标签显示在html中，错误消息可以通过spring支持的国际化特性进行本地化
	
				//ValidationUtils类
					//org.springframework.validation.ValidationUtils类是哟个工具类，有助于编写spring验证器
					//下面方法可以使用该工具类的某些方法代替简化：
						//if(x==null||x.isEmpty()){...}===>ValidationUtils.rejectIfEmpty("price")
						//if(x==null||x.trim().isEmpty()){errors.rejectVlaue("price")}====>ValidationUtils.rejectIfEmptyOrWhitespace()
							//ValidationUtils有很多重载方法
					//此外它还有一个invokeValidator方法，用来调用验证器：
						//public static void invokeValidator(Validator validator,Object object,Errors errors)
	
				//示例：见包21中的BookValidator实例
				//验证器在controller中可以new出来然后调用validate方法（validate(object,bindingResult)）
				
				//源文件
					//验证器不需要显示注册，但是如果想从某个属性文件中获取错误信息，则需要声明messageSource bean,告诉spring去哪里寻找这个文件
						//<bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBoundleMessageSource">
								//<property name="basename" value="/wen-inf/resource/messages/"></property>
						//</bean>
						//这个bean本质上是说：错误码和错误信息可以在/web-inf/resource/目录下的messages.properties文件中找到
	
				//使用spring的验证器的另一种方法是，在controller中编写initBinder方法，并将验证器传到WebDateBinder,并调用其validate方法
					//public void initBinder(WebDateBinder binder){binder.setValidator(new BookValidator;binder.validate();)}
					//将验证器传到webDateBinder会使用该验证器类应用于Controller类中所有处理请求的方法
				
				//或者利用@javax.validation.Valid对要验证的对象参数进行注解，Valid注解类型实在JSR303中定义的
	
		//JSR303验证
			//JSR303(BeanValidation)和JSR349(BeanValidation1.1)指定了一整套API,通过注解给对象属性添加约束，他们只是一个规范文档，本身用处不大，除非编写了实现
				//目前有两个实现，1：HibernateValidator，JSR的两种规范它都实现了，2：ApacheBVal它只实现了JSR303
				//http://beanvalidation.org 可以参考这个网站产看java bean validation
				//JSR303不需要编写验证器，但是需要利用注解类型嵌入约束，约束如下
					//@AssertFalse:应用于Boolean属性，该属性值必须为false		@AssertFalse boolean flag
					//@AssertTrue:应用于Boolean属性，该属性值必须为true		@AssertTrue boolean flag
					//@DecimalMax:该指定值必须为小于或等于指定值的小数	@DecimalMax("0.22")BigDecimal price
					//@DecimalMin:该指定值必须为大于或等于指定值的小数	@DecimalMin("0.04")BigDecimal price
					//@Digits:该属性值必须在指定范围内，integer属性定义该属性的最大整数部分fraction定义该属性的最大小数部分		@Digits(integer=5,fraction=2)BigDecimal price
					//@Future:该属性值必须是未来的一个日期			@Future Date futureDate
					//@Max:该属性必须是一个小于或等于指定值的整数		@Max(100)int age
					//@Min:该属性必须是一个大于或等于指定值的整数		@Min(150) int age
					//@NotNull:该属性值不能为null				@NotNull String name
					//@Null:该属性值必须为null					@Null String name
					//@Past:该属性值必须是过去的一个日期			@Past Date oldDate
					//@Pattern:该属性值必须与指定的常规表达式相匹配		@Pattern(regext="\d{3}")String code
					//@@Size:该属性值必须在指定范围内		@Size(min=2,max=140)String description
	
				//一旦了解了jsr303 validation的使用方法，使用起来会比spring验证器更容易一些，像使用spring验证器一样，可以在属性文件中使用下列格式的property键，来覆盖jsr303验证器的错误信息
					//constraint.object.property
						//Size.book.isbn可以覆盖@Size在book类中的错误信息
						//Past.book.bookname键后面的消息可以覆盖@Past注解在book类上如果出错的消息
	
				//JSR303规范
					//我们添加了Hibernate Validator库(该规范的实现)的jar文件到lib 文件夹，因此我们不再需要Validator类了
			
			//小结：可以zaispringMVC中使用两种验证器，spring框架的validator和JSR303验证器，由于JSR303是标准规范，所以建议使用JSR303
	
}	
