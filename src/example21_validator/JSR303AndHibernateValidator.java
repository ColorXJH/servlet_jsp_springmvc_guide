package example21_validator;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class JSR303AndHibernateValidator implements Serializable{

	private static final long serialVersionUID = 1L;
		@Size(min=1,max=5)
		private String name;
		private int age;
		private String isbn;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getIsbn() {
			return isbn;
		}
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
		
}
