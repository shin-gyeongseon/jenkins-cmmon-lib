import org.shin.example.jenkins.MyClass

def call(String name = 'World!') {
    def clazz = new MyClass()
    return clazz.sayHello(name)
}