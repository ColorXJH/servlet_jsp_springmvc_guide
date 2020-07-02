package example19_data_binding_and_form_taglib;

public class DataBindingAndFormTaglibGuide {
		//数据绑定和表单标签库
			//数据绑定是将用户输入绑定到领域模型的一种特性，有了数据绑定，类型总是为string的http的请求参数，可用于填充不同类型的对象属性
			//数据绑定使得form-bean(前面章节的ProductForm)变成了多余的
			//为了高效的使用数据绑定。还需要spring的表单标签库，下面将学习spring表单标签库的用法
		
			//数据绑定概览：
				//基于http的特性，所有的http请求的参数类型都是字符串，有了数据绑定，就不再需要ProductForm类了，，也不需要解析Product对象的price属性了
				//数据绑定的另一个好处是：当输入验证失败时，他会重新生成一个html表单，之前手工编写html时必须记住用户之前输入的值，有了spring的数据绑定和表单标签库之后，它会替你完成这些工作
	
			//表单标签库
				//表单标签库中包含了可以在jsp页面中渲染html元素的标签，为了使用这些标签，必须在jsp页面的开头处声明这个taglib指令：
					//<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
				//表单标签库中的标签如下：
					//1:form		渲染表单元素	
					//2:input		渲染<input type="text"/>元素
					//3:password	渲染<input type="password"/>元素
					//4:hidden		渲染<input type="hidden"/>元素
					//5:textarea	渲染textarea元素
					//6:checkbox	渲染<input type="checkbox"/>元素
					//7:checkboxes  渲染多个<input type="checkbox"/>元素
					//8:radiobutton 渲染一个<input type="radio"/>元素
					//9:radiobuttons渲染多个<input type="radio"/>元素
					//10:select		渲染一个选择元素
					//11:option		渲染一个可选元素
					//12:options	渲染一个可选元素列表
					//13:Errors		在span元素中渲染错误字段
			
				//form标签
					//渲染html表单，这些表单中也可以包含html属性：method,action,form表单的属性如下：
						//acceptCharset			定义服务器接受的zi'fu编码列表
						//commandName			显示表单对象之模型属性名称，默认为command
						//cssClass				定义要应用到被渲染form元素的css类
						//cssStyle				定义要应用到被渲染form元素的css样式
						//htmlEscape			接受true或者false，标识被渲染的值是否应该进行html转义
						//modelAttribute		显示form backing object的模型属性名称，默认为command
							//其中commandName:定义了模型属性的名称，其中包含一个backing object，其属性将用于填充所生成的表单，如果该属性存在，
							//则 必须在返回包含该表单的视图的请求处理方法中添加相应的模型属性
								//<form commandName="book" action="book_save" methdo="post"></form>
								//@RequestMapping("/book_save")
								//public String inputBook(Model model){model.addAttribute("book",new Book());return "book/list.jsp"}
		
				//input标签
					//渲染<input type="text"/>元素，这个标签最重要的属性就是path，它将这个输入字段绑定到form backing object的一个属性，例如，
					//若随附<form>边沁啊的commandName="book"，并且input标签的path属性值为isbn,那么input标签将被绑定到book对象的isbn属性
					//input标签的属性如下：
						//cssClass			定义要应用到被渲染input元素的css类		
						//cssStyle			定义要应用到被渲染input元素的css样式
						//cssErrorClass		定义要应用到被渲染input元素的css类	如果bound属性中包含错误则覆盖cssClass属性值
						//htmlEscape		接受true或者false，表示是否应该对被渲染的值进行HTML转义
						//path				要绑定的属性路径
							//例如：下面这个input标签被绑定到form backing object的isbn属性
							//<form:input id="isbn" path="isbn" cssErrorClass="errorBox"/>
							//他将被渲染成先下面的样子：
								//<input type="text" id="isbn" name="isbn"/>
									//cssErrorClass属性不起作用,除非isbn属性中有输入验证错误，并且采用同一个表单重新显示用户输入,他会被显示成如下：
										//<input type="text" id="isbn" name="isbn" class="errorBox"/>
							//input标签也可以绑定到嵌套对象的属性
								//<form:input id="sss" path="catagroy.id"/> //form backing object的catagroy属性的id属性
			
				//password标签
					//渲染<input type="password"/>元素。他的标签属性与input相似，只不过多了一个
						//showPassword属性：表示应该显示或遮掩密码，默认为false
	
				//hidden标签
					//渲染<input type="hidden"/>元素，属性如下
						//htmlEscape   path
						//因为没有可视的外观，所以，因此不支持cssClass/cssStyle
		
				//textarea标签
					//渲染一个html的textarea元素，它基本上是一个支持多行输入的input元素，属性如下
						//同input元素
							//<form:textarea path="note" tabindex="4" rows="5" cols="80"/>
				
				//checkbox标签
					//渲染<input type="checkbox"/>元素，属性比input元素多了一个label属性
						//label			要作为label用于被渲染复选框的值
							//<form:checkbox path="cks" value="xxas"/>
				
				//radiobutton标签
					//渲染<input type="radio"/>元素，属性与checkbox标签一致
					
				//checkboxes标签
					//渲染多个<input type="checkbox"/>元素，元素属性相比于checkbox标签多了以下属性：
						//delimiter			定义两个input之间的分隔符，默认没有分隔符
						//element			给每个被渲染的input元素都定义一个html元素，默认为span
						//items				用于生成input元素的对象的collection,map或者array
						//itemLabel			items属性中定义的collection,map或者array中的对象属性，为每个input元素提供label
						//itemValue			item属性中定义的Collection、Map或者Array中的对象属性，为每个input元素提供值
	
				//radiobuttons标签
					//渲染多个<input type="radio"/>元素,属性与checkboxes标签相同
	
				//select标签
					//渲染一个HTML的select元素，items属性的一个Collection、Map、Array，或者来自一个嵌套的option或者options标签，属性和radiobuttons标签相同
						/*<form:select id="category" path="category.id"
									items="${categories}" itemLabel="name"
									itemValue="id"/>*/

							//下面的select标签绑定到form backing object的category属性的id属性。它的选项来自model属性categories。
							//每个选项的值均来自categories  collection/map/array的id属性，它的Label来自name属性
			
				//option标签
					//渲染select元素中用的一个HTML的option元素，属性如下：
						//cssClass,cssStyle,cssErrorClass,htmlEscape
									/*<form:select id="category" path="category.id"
											items="${categories}" itemLabel="name"
													itemValue="id">
										<option value="0">-- Please select --</option>
									</form:select>*/
						//这个代码片断是渲染一个select元素，其选项来自model属性categories，以及option标签
				
				//options标签
					//生成html的一个option元素列表，属性和select标签一样
			
				//errors标签
					//渲染一个或多个html的span元素，每个span元素都包含一个字段错误，可以用来显示一个特定的字段错误，或者所有的字段错误
					//元素属性为：cssClass,cssStyle,delimiter,element,htmlEscape,path
						//显示所有的字段错误:<form:errors path="*"/>
						//显示一个与form backing object的author属性相关的字段错误：<form:errors path="author"/>

				//数据绑定范例19-a:

			//小结：本章介绍了是数据绑定和spring表单标签库中的标签



}
