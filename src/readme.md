 关于cronExpression表达式，这里讲解一下： 
字段 允许值 允许的特殊字符 
秒 0-59 , - * / 
分 0-59 , - * / 
小时 0-23 , - * / 
日期 1-31 , - * ? / L W C 
月份 1-12 或者 JAN-DEC , - * / 
星期 1-7 或者 SUN-SAT , - * ? / L C # 
年（可选） 留空, 1970-2099 , - * / 
表达式意义 
"0 0 12 * * ?" 每天中午12点触发 
"0 15 10 ? * *" 每天上午10:15触发 
"0 15 10 * * ?" 每天上午10:15触发 
"0 15 10 * * ? *" 每天上午10:15触发 
"0 15 10 * * ? 2005" 2005年的每天上午10:15触发 
"0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发 
"0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发 
"0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发 
"0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发 
"0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发 
"0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发 
"0 15 10 15 * ?" 每月15日上午10:15触发 
"0 15 10 L * ?" 每月最后一日的上午10:15触发 
"0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发 
"0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发 
"0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发 
每天早上6点 
0 6 * * * 
每两个小时 
0 */2 * * * 
晚上11点到早上8点之间每两个小时，早上八点 
0 23-7/2，8 * * * 
每个月的4号和每个礼拜的礼拜一到礼拜三的早上11点 
0 11 4 * 1-3 
1月1日早上4点 
0 4 1 1 * 



<!--配置调度程序quartz ，其中配置JobDetail有两种方式-->
	    
	    <!--方式一：使用JobDetailBean，任务类必须实现Job接口  -->
	<bean id="myjob"  class="org.springframework.scheduling.quartz.JobDetailBean" >
		<property name="name"  value="exampleJob"></property> 
		<property name="jobClass"  value="com.ncs.hj.SpringQtz"></property>
		<property name="jobDataAsMap"> 
			<map>
				<entry  key="service">
					<value>simple is the beat</value>
				</entry>  
			</map>	    
	   </property>
	</bean>
	
 
	   
	    <!--运行时请将方式一注释掉！  -->
	    <!-- 方式二：使用MethodInvokingJobDetailFactoryBean，任务类可以不实现Job接口，通过targetMethod指定调用方法 -->
	    <!-- 定义目标bean和bean中的方法  -->
 
	<bean id="SpringQtzJob" class="com.ncs.hj.SpringQtz" />
	<bean  id="SpringQtzJobMethod"  
		class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
		<property  name="targetObject">        
			<ref  bean="SpringQtzJob" />    
		</property>
  
		<property name="targetMethod">
			  <!-- 要执行的方法名称  --> 
			<value>execute</value>   
		</property>  
	</bean>
	<!-- ======================== 调度触发器 ========================  -->
	<bean id="CronTriggerBean"  
		class="org.springframework.scheduling.quartz.CronTriggerBean">  
		<property  name="jobDetail"   ref="SpringQtzJobMethod"></property>
		<property  name="cronExpression"   value="10 0/1 * * * ?"></property>  
	</bean>
	  
	<!-- ======================== 调度工厂 ========================  --> 
	<bean  id="SpringJobSchedulerFactoryBean"  
		class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
		<property  name="triggers">
			<list>          
				<ref  bean="CronTriggerBean" />       
			</list>
		</property>  
	</bean>