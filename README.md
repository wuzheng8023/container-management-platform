## 创建于2020年7月28日  
## 可视化容器管理系统


`2020年7月30日出现错误,一个错误记录。`
在运行test->VMMessageServiceTest中的test_getMemoryMessage方法的时候，出现了如下的错误：
```
java.lang.IllegalStateException: Failed to load ApplicationContext

	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:132)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:123)
	at org.springframework.test.context.web.ServletTestExecutionListener.setUpRequestContextIfNecessary(ServletTestExecutionListener.java:190)
	at org.springframework.test.context.web.ServletTestExecutionListener.prepareTestInstance(ServletTestExecutionListener.java:132)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:244)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:98)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$5(ClassBasedTestDescriptor.java:341)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:346)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$6(ClassBasedTestDescriptor.java:341)
```
发现原因是因为这个语句放在方法外面会引发错误。
````
@Service
public class VMMessageService {
    @Autowired
    private VMMetrics vmMetrics;
    List<String> metricsList = vmMetrics.getVMMetrics();//这一句放在这个位置会引发错误

    public String getMemoryMessage() {
        List<String> metricsList = vmMetrics.getVMMetrics();
````
将这一句放在这里的时候，则排除了异常
````
@Service
public class VMMessageService {
    @Autowired
    private VMMetrics vmMetrics;
   

    public String getMemoryMessage() {
 List<String> metricsList = vmMetrics.getVMMetrics();//这一句放置在方法内
        List<String> metricsList = vmMetrics.getVMMetrics();
````
***总结：***

个人认为，出现这样原因可能是@service组件中的内容不允许有单独的变量出现，具体的原因还需要细究；