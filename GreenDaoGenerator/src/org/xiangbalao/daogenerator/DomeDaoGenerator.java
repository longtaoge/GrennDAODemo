/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.xiangbalao.daogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Generates entities and DAOs for the example project DaoExample.
 * <p/>
 * Run it as a Java application (not Android).
 * 
 * @author longtaoge
 */
public class DomeDaoGenerator {
	public static void main(String[] args) throws Exception {
		// 设置实体类包名
		Schema schema = new Schema(1, "org.xiangbalao.bean");
		// 设置DAO层包名
		schema.setDefaultJavaPackageDao("org.xiangbalao.dao");
		// 定义实体类
		addNote(schema);
		// 生成代码后的存放路径，这里放到了 GreenDaoDome 项目下的src 目录下
		new DaoGenerator().generateAll(schema, "../GreenDaoDome/src");// "../GreenDaoDome/src/main/java"
	}

	private static void addNote(Schema schema) {
		// 实体类名
		Entity person = schema.addEntity("Person");
		// 添加id
		person.addIdProperty();
		// 添加 firstname 字段
		person.addStringProperty("firstname").notNull();
		// 添加 lastname 字段
		person.addStringProperty("lastname");
		// 添加 age 安段
		person.addStringProperty("age");

	}

}
