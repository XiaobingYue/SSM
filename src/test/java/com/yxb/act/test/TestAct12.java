package com.yxb.act.test;

import java.util.List;

import com.yxb.common.util.ActUtil;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;


public class TestAct12 {

	public static void main(String[] args) {
		ActUtil.deploy("MyProcess07.bpmn");

		ProcessInstance pi = ActUtil.start("myProcess");
		
		// 所谓的网关其实就是流程中逻辑分支判断
		// 并行网关，多条逻辑分支都会执行，一个分支执行完毕后，流程不会结束
		//         需要等待其他的流程分支的结束
		// 会签
		TaskService taskService = ActUtil.getTaskService();
		TaskQuery query = taskService.createTaskQuery();
		
		List<Task> tasks = query.taskAssignee("zhangsan").list();
		List<Task> tasks1 = query.taskAssignee("lisi").list();
		
		System.out.println( "zhangsan的任务数量 = " + tasks.size() );
		System.out.println( "lisi的任务数量 = " + tasks1.size() );
		
		for ( Task task : tasks ) {
			System.out.println( "zhangsan完成任务 = " + task.getName() );
			taskService.complete(task.getId());
		}
		
		tasks = query.taskAssignee("zhangsan").list();
		tasks1 = query.taskAssignee("lisi").list();
		
		System.out.println( "zhangsan的任务数量 = " + tasks.size() );
		System.out.println( "lisi的任务数量 = " + tasks1.size() );
		
		// 判断流程是否结束
		HistoryService historyService = ActUtil.getHistoryService();
		HistoricProcessInstance hpi =
			historyService
			    .createHistoricProcessInstanceQuery()
			    .processInstanceId(pi.getId())
			    .finished()
			    .singleResult();
		
		System.out.println( "流程是否结束 ：" + (hpi != null) );
		
		for ( Task task : tasks1 ) {
			System.out.println( "lisi完成任务 = " + task.getName() );
			taskService.complete(task.getId());
		}
		
		tasks = query.taskAssignee("zhangsan").list();
		tasks1 = query.taskAssignee("lisi").list();
		
		System.out.println( "zhangsan的任务数量 = " + tasks.size() );
		System.out.println( "lisi的任务数量 = " + tasks1.size() );
		
		historyService = ActUtil.getHistoryService();
		hpi =
			historyService
			    .createHistoricProcessInstanceQuery()
			    .processInstanceId(pi.getId())
			    .finished()
			    .singleResult();
		
		System.out.println( "流程是否结束 ：" + (hpi != null) );
	}

}
