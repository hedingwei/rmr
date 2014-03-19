rmr
===

> **rmr** is A Lightweight Realtime MapReduce Framework

> **rmr** 是一个轻量级的实时MapReduce框架

## 简介 ##
	

## 快速开始 ##
1. 获取源码

	`cd /opt`
	
	`git clone https://github.com/hedingwei/rmr`
	
	`cd /opt/rmr`
	
	`mvn install`


3. 创建一个简单的Mapper 

	`mvn archetype:generate -DarchetypeGroupId=com.ambimmort \`
                ` -DarchetypeArtifactId=rmr-archetype-mapper \`
                `-DgroupId=com.ambimmort.demo -DartifactId=demo-mapper \` 
				`-Dversion=1.0.0-SNAPSHOT`
	
	该命令创建了一个基于rmr-archetype-mapper模版的java项目。
	
	看看创建的项目如何

4. 创建一个简单的Reducer

	`mvn archetype:generate -DarchetypeGroupId=com.ambimmort \`
                ` -DarchetypeArtifactId=rmr-archetype-reducer \`
                `-DgroupId=com.ambimmort.demo -DartifactId=demo-mapper \` 
				`-Dversion=1.0.0-SNAPSHOT`
	
	该命令创建了一个基于rmr-archetype-reducer模版的java项目。



5. 看看效果如何



## 开发者指南 ##
- 概念
- 设计
