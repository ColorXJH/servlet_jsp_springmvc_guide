package example15_springframework;


public class Person1 {
	private String name;
	private Integer age;
	private String birthday;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "Person1 [name=" + name + ", age=" + age + ", birthday=" + birthday + "]";
	}
	
}
